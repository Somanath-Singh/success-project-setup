/*project wise expenditure data */

 document.addEventListener("DOMContentLoaded", function () {
	  
	  loadProjectExpenditureData(0);
	 
	  $("#projectId").on("change", function () {
	    const projectId = $(this).val();
	    if (projectId && projectId !== "0") {
	      loadProjectExpenditureData(projectId);
	    } else {
	    	loadProjectExpenditureData(0);
	    }
	  });
	});
 
 function loadProjectExpenditureData(projectId) {
	    $.ajax({
	      type: "GET",
	      url: contextPath + "/scst/getProjectWiseExpenditureAllAmount",
	      data: { projectId: projectId },
	      success: function (data) {
            if (!data ||
         		    	((data.expenditureAmount == null || data.expenditureAmount === 0) &&
         		        (data.usedAllocationAmount == null || data.usedAllocationAmount === 0) &&
         		        (data.usedBudgetAmount == null || data.usedBudgetAmount === 0))
         			) {
         		    $("#project-expenditure-msg").html(
         		        '<div style="text-align:center;font-size: 15px; color: red;">'+
         		        'No data found for this project.'+
         		        '</div>'
         		    );
         		    renderProjectExpenditurePie(["Expenditure", "Allocated", "Budget"],[0, 0, 0]);
         		    return;
         		}else{
         			var projectDropDowns = $('#projectId');

         		    if (!projectDropDowns || projectDropDowns.find("option").length <= 1) {
         		        $("#project-expenditure-msg").html(
         		            '<div style="text-align:center;font-size: 15px; color: red;">' +
         		            'No projects available for this financial year.' +
         		            '</div>'
         		        );
         		    } else {
         		        $("#project-expenditure-msg").html(
         		            '<div style="text-align:center;font-size: 15px; color: #666;">' +
         		            'Please select a project to see the expenditure amount.' +
         		            '</div>'
         		        );
         		    }
         		}

	        const labels = ["Expenditure", "Allocated", "Budget"];
	        const values = [data.expenditureAmount,data.usedAllocationAmount,data.usedBudgetAmount];

	        renderProjectExpenditurePie(labels, values);
	      },
	      error: function (error) {
	        console.error("Error fetching project expenditure:", error);
	        $("#project-expenditure-pie").html(
	          '<div style="text-align:center; padding: 20px; font-size: 16px; color: red;">Error loading data.</div>'
	        );
	        renderProjectExpenditurePie(
	          ["Expenditure", "Allocated", "Budget"],
	          [0, 0, 0]
	        );
	      }
	    });
	  }
 
 function renderProjectExpenditurePie(labels, values) {
	    const colors = ["#4D84B4", "#A7D52B", "#F7941D"];
	    const total = values.reduce((a, b) => a + b, 0);

	    const options = {
	      chart: {
	        type: "pie",
	        height: 400,
	        toolbar: { show: false }
	      },
	      series: values.map(v => (v === 0 ? 0.0001 : v)),
	      labels: labels,
	      colors: colors,
	      legend: {
	        position: "bottom",
	        fontSize: "13px",
	        formatter: function (legendName, opts) {
	          const actualValue = values[opts.seriesIndex];
	          return legendName + " (" + actualValue.toLocaleString() + ")";
	        }
	      },
	      tooltip: {
	        enabled: true,
	        y: {
	          formatter: function (_, opts) {
	            const actualValue = values[opts.seriesIndex];
	            return actualValue.toLocaleString() + " ₹";
	          }
	        }
	      },
	      dataLabels: {
	        enabled: true,
	        formatter: function (_, opts) {
	          const actualValue = values[opts.seriesIndex];
	          const percentage = total ? ((actualValue / total) * 100).toFixed(1) : 0;
	          return actualValue === 0
	            ? "0 ₹"
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

	    const chartContainer = document.querySelector("#project-expenditure-pie");
	    chartContainer.innerHTML = "";
	    const chart = new ApexCharts(chartContainer, options);
	    chart.render();
	  }

