
var contextPath = "${pageContext.request.contextPath}";

$(document).ready(function () {
    var tooltip = $("#tooltip");
    var hideTimeout;
    var jBoxTooltips = {}; // Store jBox instances by district name

    // Load SVG and initialize hover effect (Static page load)
    $('.svgMap').each(function() {
        var svgMap = $(this);
        var availableTitles = svgMap.find("[data-title]").map(function() {
            return $(this).attr("data-title");
        }).get();
     
        // Initialize jBox tooltips for hover
		$('.svgMap [data-title]').each(function() {
		    var districtName = $(this).attr("data-title");
		    jBoxTooltips[districtName] = new jBox('Tooltip', {
		        attach: $(this),
		        content: districtName, // Show only district name on hover
		        position: {
		            x: 'left',
		            y: 'center'
		        },
		        outside: 'x',
		        adjustPosition: true,
		        content: "<strong style='font-weight: bold; color: #5C4033;'>" + districtName + "</strong>" // Increased font weight and deep brown color
		    });
		});
    });

    // Click effect with AJAX call
    $(document).on("click", function(e) {
        if (!$(e.target).closest(".svgMap").length) {
            clearTimeout(hideTimeout);
            $("#tooltip").hide(); // Hide AJAX data tooltip
            $(".svgMap").find("path,polygon").css("fill", "");
        }
    });

    $('.svgMap').find("[data-title]").on("click", function(e) {
        e.stopPropagation();
        var districtName = $(this).attr("data-title");
        clearTimeout(hideTimeout);
        // Close the specific jBox tooltip for this district
        if (jBoxTooltips[districtName]) {
            jBoxTooltips[districtName].close();
        }
        $(".svgMap").find("path,polygon").css("fill", "");
        $(this).find("path,polygon").css("fill", "#FFD700");
        loadDistrictData(districtName, e);
    });

    function loadDistrictData(districtName, event) {
        var tooltip = $("#tooltip");
        var hideTimeout;

        $.ajax({
            type: "GET",
            url: contextPath + "/scst/fetchWorkInspectionLocationDataByDistrictName",
            data: { districtName: districtName },
            success: function (data) {
                var tooltipContent = "<strong style='color: #ffffff; text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);'>" + districtName + "</strong><br/>";
                if (data && data.location && data.location.length > 0) {
                    data.location.forEach(function(loc) {
                        tooltipContent += "<span style='color: #e0f7fa;'>• " + loc.projectName 
                            + " (Lat: " + loc.latitude + ", Lon: " + loc.longitude + ")</span><br/>";
                    });
                } else {
                    tooltipContent += "<span style='color: #e0f7fa;'>No projects</span>";
                }
                tooltip.html(tooltipContent).show();
                // Position tooltip using jBox-like logic
                var target = $(event.target).closest("[data-title]");
                tooltip.css({
                    position: "absolute",
                    left: target.offset().left - tooltip.outerWidth() - 10 + "px", // Left of target
                    top: target.offset().top + (target.outerHeight() / 2) - (tooltip.outerHeight() / 2) + "px" // Center vertically
                });
                clearTimeout(hideTimeout);
                hideTimeout = setTimeout(function() {
                    tooltip.hide();
                    $(".svgMap [data-title='" + districtName + "']").find("path,polygon").css("fill", "");
                }, 3000); // Increased to 3 seconds
            },
            error: function (xhr, status, error) {
                tooltip.html("<span style='color: #e0f7fa;'>Error loading data</span>").show();
                // Position tooltip using jBox-like logic
                var target = $(event.target).closest("[data-title]");
                tooltip.css({
                    position: "absolute",
                    left: target.offset().left - tooltip.outerWidth() - 10 + "px", // Left of target
                    top: target.offset().top + (target.outerHeight() / 2) - (tooltip.outerHeight() / 2) + "px" // Center vertically
                });
                clearTimeout(hideTimeout);
                hideTimeout = setTimeout(function() {
                    tooltip.hide();
                }, 3000); // Increased to 3 seconds
            }
        });
    }
});