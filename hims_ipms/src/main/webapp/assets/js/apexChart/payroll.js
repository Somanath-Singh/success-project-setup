var options = {
  series: [
    {
      name: 'Salary Processed',
      data: [44, 55, 41, 67]
    },
    {
      name: 'Salary On Hold',
      data: [13, 10, 7, 8]
    },
    {
      name: 'Absent',
      data: [5, 3, 7, 4]
    }
  ],
  chart: {
    type: 'bar',
    height: 330,
    stacked: true,
    toolbar: {
      show: true
    },
    zoom: {
      enabled: true
    }
  },
  responsive: [
    {
      breakpoint: 480,
      options: {
        legend: {
          position: 'bottom',
          offsetX: -10,
          offsetY: 0
        }
      }
    }
  ],
  colors: ['#1E90FF', '#00f741', '#FF6347'],
  plotOptions: {
    bar: {
      horizontal: false,
      borderRadius: 5,
      columnWidth: '20px',
      dataLabels: {
        total: {
          enabled: true,
          formatter: function (val, opts) {
            const series = opts.w.config.series;
            const index = opts.dataPointIndex;
            const total = series.reduce((acc, curr) => acc + curr.data[index], 0);
            return `${total} Employee`;
          },
          style: {
            fontSize: '10px',
            fontWeight: 600,
            color: '#000'
          }
        }
      }
    }
  },
  xaxis: {
    type: 'text',
    categories: ['Regular', 'NMR / DLR', 'Work Charge', 'Contractual']
  },
  yaxis: {
    title: {
      text: 'Employee'
    }
  },
  legend: {
    position: 'right',
    offsetY: 40
  },
  fill: {
    opacity: 1
  },tooltip: {
    y: {
      formatter: function (val) {
        return + val + " Employee"
      }
    }
  }
};

var chart = new ApexCharts(document.querySelector("#payroll"), options);
chart.render();
