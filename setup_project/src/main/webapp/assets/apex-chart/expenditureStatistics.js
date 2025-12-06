 var options = {
          series: [44, 55, 13],
          chart: {
          width: 380,
          type: 'pie',
        },
        labels: ['Fund Allocated', 'Fund Utilized', 'Pending Amount'],
        responsive: [{
          breakpoint: 480,
          options: {
            chart: {
              width: 300
            },
            legend: {
              position: 'bottom'
            }
          }
        }]
        };

        var chart = new ApexCharts(document.querySelector("#expenditureStatistics"), options);
        chart.render();