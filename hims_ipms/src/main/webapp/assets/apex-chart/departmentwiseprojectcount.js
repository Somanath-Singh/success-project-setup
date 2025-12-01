/*department wise project count*/

document.addEventListener('DOMContentLoaded', function () {
	
	loadDepartmentWiseProjectCount();

  // Attach dropdown change listener
  document.getElementById("financialYear").addEventListener("change", function () {
    const selectedYear = this.value;
    loadDepartmentWiseProjectCount(selectedYear || null);
  });

  function loadDepartmentWiseProjectCount(financialYearId) {
    $.ajax({
      type: "GET",
      url: contextPath + "/scst/getDepartmentWiseProjectCount",
      data: financialYearId ? { finYearId: financialYearId } : {},
      success: function (data) {
    	  const chartContainer = document.querySelector("#department-pie-chart");
    	  chartContainer.innerHTML = ''; 

    	  if (!data || data.length === 0) {
    	    chartContainer.innerHTML = '<div style="text-align:center; padding: 20px; font-size: 16px; color: #666;">No projects found for the selected financial year.</div>';
    	    return;
    	  }
    	  
        const labels = data.map(item => item.departmentName);
        const values = data.map(item => item.projectCount);
        renderDepartmentPieChart(labels, values);
      },
      error: function (error) {
        console.error("Error fetching department data:", error);
      }
    });
  };

  function generateDynamicColors(count) {
    const palette = ['#FF007F', '#4D84B4', '#A7D52B', '#F3CC1E', '#F7941D'];
    return Array.from({ length: count }, (_, i) => palette[i % palette.length]);
  }

  function renderDepartmentPieChart(labels, values) {
    const total = values.reduce((sum, val) => sum + val, 0);
    const colors = generateDynamicColors(values.length);

    const options = {
      chart: {
        type: 'pie',
        height: 400,
        toolbar: { show: false }
      },
      series: values,
      labels: labels,
      colors: colors,
      legend: {
        position: 'bottom',
        fontSize: '13px',
        formatter: function (legendName, opts) {
          return legendName + ' (' + opts.w.globals.series[opts.seriesIndex] + ')';
        }
      },
      tooltip: {
        enabled: true,
        style: {
          fontSize: '12px',
          colors: ['#ffffff']
        }
      },
      dataLabels: {
        enabled: true,
        formatter: function (val, opts) {
          const count = opts.w.config.series[opts.seriesIndex];
          const percentage = ((count / total) * 100).toFixed(1);
          return percentage + '%\n(' + count + ')';
        },
        style: {
          fontSize: '10px',
          fontWeight: 'bold',
          colors: ['#ffffff']
        },
        dropShadow: {
          enabled: true,
          top: 1,
          left: 1,
          blur: 1,
          opacity: 0.45
        }
      },
      responsive: [{
        breakpoint: 480,
        options: {
          chart: { width: 300 },
          legend: { position: 'bottom' }
        }
      }]
    };

    const chartContainer = document.querySelector("#department-pie-chart");
    chartContainer.innerHTML = '';
    const chart = new ApexCharts(chartContainer, options);
    chart.render();
  }
});

