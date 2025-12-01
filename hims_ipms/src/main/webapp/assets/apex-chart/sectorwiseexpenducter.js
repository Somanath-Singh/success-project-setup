document.addEventListener("DOMContentLoaded", function () {
  const projectCounts = [120, 85, 65, 45, 85];
  const labels = ['hostel', 'new-building', 'sector-1', 'sector-2', 'sector-3'];
  const total = projectCounts.reduce((a, b) => a + b, 0);

  var options = {
    chart: {
      type: 'pie',
      height: 350
    },
    series: projectCounts,
    labels: labels,
    colors: ['#f1c40f', '#2874a6', '#884ea0', '#D66058', '#A61C00'],
    legend: {
      position: 'bottom',
      fontSize: '13px',
      labels: {
        colors: '#000'
      },
      markers: {
        shape: "circle"
      }
    },
    title: {

      align: 'center',
      style: {
        fontSize: '18px',
        fontWeight: 'bold'
      }
    },
    dataLabels: {
      enabled: true,
      formatter: function (val, opts) {
        const count = opts.w.config.series[opts.seriesIndex];
        const percentage = ((count / total) * 100).toFixed(1);
        return count + " (" + percentage + "%)";
      },
      style: {
        fontSize: '10px',
        fontWeight: 'bold',
        colors: ['#fff']
      }
    },
    tooltip: {
      y: {
        formatter: function (count, opts) {
          const percentage = ((count / total) * 100).toFixed(1);
          return count + " (" + percentage + "%)";
        }
      }
    }
  };

  var chart = new ApexCharts(document.querySelector("#sectorwiseexpenducter"), options);
  chart.render();
});