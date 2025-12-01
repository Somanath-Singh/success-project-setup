var options = {
  series: [{
    name: "Project",
    data: [10, 41, 35, 51, 49, 62, 61, 54, 45,35,40,25]
}],
  chart: {
  height: 325,
  type: 'line',
  zoom: {
    enabled: false
  }
},
dataLabels: {
  enabled: false
},
stroke: {
  curve: 'straight'
},colors: ['#d66f16'],
title: {
  text: '',
  align: 'left'
},
grid: {
  row: {
    colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
    opacity: 0.5
  },
},
xaxis: {
  categories: ['Phase 1', 'Phase 2', 'Phase 3', 'Phase 4', 'Phase 5', 'Phase 6', 'Phase 7', 'Phase 8', 'Phase 9','Phase 10','Phase 11','Phase 12'],
},
yaxis: {
  title: {
    text: 'Rupees (In Cr.)'
  }
  
},
legend: {
  position: 'top',
  horizontalAlign: 'right',
  floating: true,
  offsetY: -25,
  offsetX: -5
},
tooltip: {
  y: {
    formatter: function (val) {
      return "Rs." + val + " Cr."
    }
  }
}
};
var chart = new ApexCharts(document.querySelector("#fundUtilizationAmountPhase"), options);
chart.render();