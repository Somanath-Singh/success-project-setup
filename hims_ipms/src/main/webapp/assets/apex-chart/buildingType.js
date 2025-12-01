document.addEventListener("DOMContentLoaded", function () {
    // On page load → fetch using selected financial year and districtId=0
    var financialYearId = $("#financialYear").val() || 0;
    loadBuildingTypeData(financialYearId, 0);

    // On district change
    $("#districtId").on("change", function () {
        var districtId = $(this).val() || 0;
        var fyId = $("#financialYear").val() || 0;
        loadBuildingTypeData(fyId, districtId);
    });

   // On financial year change (your select onchange)
	window.loadDepartmentWiseProjectCount = function (financialYearId) {
	    // Reset district dropdown to default value 0
	    $("#districtId").val("0").trigger("change.select2"); 
	    loadBuildingTypeData(financialYearId, 0);
	};

});


function loadBuildingTypeData(financialYearId, districtId) {
    $.ajax({
        type: "GET",
        url: contextPath + "/scst/fetchProjectTypeCountByFilter",
        data: {
            financialYearId: financialYearId,
            districtId: districtId
        },
        success: function (data) {
            if (!data || data.length === 0) {
                $("#buildingType-msg").html(
                    'No building type data found for the selected filters.'
                );
                renderBuildingTypePie([], []);
                return;
            }

            // Clear message when valid data exists
            $("#buildingType-msg").html("");

            var labels = data.map(function (item) { return item.projectType; });
            var values = data.map(function (item) { return item.projectTypeCount; });

            renderBuildingTypePie(labels, values);
        },
        error: function (error) {
            console.error("Error fetching building type data:", error);
            $("#buildingType-msg").html(
                'Error loading building type data.'
            );
            renderBuildingTypePie([], []);
        }
    });
}

function renderBuildingTypePie(labels, values) {
   	const colors = ["#5DADE2", "#48C9B0", "#F5B041", "#EC7063"];
    const total = values.reduce(function (a, b) { return a + b; }, 0);

    const options = {
        chart: {
            type: "pie",
            height: 400,
            toolbar: { show: false }
        },
        series: values.length > 0 ? values.map(function (v) { return v === 0 ? 0.0001 : v; }) : [0.0001],
        labels: labels.length > 0 ? labels : ["No Data"],
        colors: colors,
        legend: {
            position: "bottom",
            fontSize: "13px",
            formatter: function (legendName, opts) {
                var actualValue = values[opts.seriesIndex] || 0;
                return legendName + " (" + actualValue.toLocaleString() + ")";
            }
        },
        tooltip: {
            enabled: true,
            y: {
                formatter: function (_, opts) {
                    var actualValue = values[opts.seriesIndex] || 0;
                    return actualValue.toLocaleString() + " Nos.";
                }
            }
        },
        dataLabels: {
            enabled: true,
            formatter: function (_, opts) {
                var actualValue = values[opts.seriesIndex] || 0;
                var percentage = total ? ((actualValue / total) * 100).toFixed(1) : 0;
                return actualValue === 0
                    ? "0"
                    : percentage + "%\n(" + actualValue.toLocaleString() + ")";
            },
            style: {
                fontSize: "11px",
                fontWeight: "bold",
                colors: ["#fff"]
            },
            dropShadow: {
                enabled: true,
                top: 1,
                left: 1,
                blur: 1,
                opacity: 0.45
            }
        },
        responsive: [
            {
                breakpoint: 480,
                options: {
                    chart: { width: 300 },
                    legend: { position: "bottom" }
                }
            }
        ]
    };

    var chartContainer = document.querySelector("#buildingType");
    chartContainer.innerHTML = "";
    var chart = new ApexCharts(chartContainer, options);
    chart.render();
}