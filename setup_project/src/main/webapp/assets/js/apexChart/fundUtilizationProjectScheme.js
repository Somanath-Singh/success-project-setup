class FundUtilizationProjectScheme
{
  constructor(apiPath, contextPath,finYear)
  {
    this.apiPath =  apiPath;
    this.worker = null;
    this.contextPath = contextPath;
    this.data = null;
    this.finYear = finYear;
    this.chart = null;
  }
          
  doFetch() 
  {
    if (this.worker == null )
    {
      this.worker = new Worker(this.contextPath + '/assets/appJs/worker/CommonWorker.js');
      this.worker.addEventListener('message', (e) => this.processData(e));
    }
            
    const url = this.contextPath + this.apiPath + "scheme-wise-budget-utilization-details?finYear="+this.finYear;
    window.loadCounter++;
    this.worker.postMessage({
      "method" : "GET" ,
      "url" : url 
    });
  }
          
  processData(e)
  {
    if (e.data != "" && e.data != null)
    {
      this.data = e.data;

      if(this.data.length > 0)
      {
        var categories = [];
        var utilzData = [];

        $.each(this.data, function(k, v) {
          categories.push(v.schemeName);
          utilzData.push(parseFloat(v.utilizedAmount));
        });

        var options = {
          series: [
          {
            name: "Allocated",
            data: utilzData
          }
        ],
          chart: {
          height: 335,
          type: 'line',
          dropShadow: {
            enabled: true,
            color: '#000',
            top: 18,
            left: 7,
            blur: 10,
            opacity: 0.2
          },
          zoom: {
            enabled: false
          },
          toolbar: {
            show: false
          }
        },
        colors: ['#77B6EA', '#545454'],
        dataLabels: {
          enabled: true,
        },
        stroke: {
          curve: 'smooth'
        },
        
        grid: {
          borderColor: '#e7e7e7',
          row: {
            colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
            opacity: 0.5
          },
        },
        markers: {
          size: 1
        },
        xaxis: {
          categories: categories,
          title: {
           
          }
        },
        yaxis: {
          title: {
            text: 'Rupees in lakhs'
          },
          min: 1,
          max: 10
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
              return "Rs." + val + " in lakhs"
            }
          }
        }
        };

        this.destroyChartById('fundUtilizationProjectScheme');
        this.chart = new ApexCharts(document.querySelector("#fundUtilizationProjectScheme"), options);
        this.chart.render();
      }
      else
      {
        this.destroyChartById('fundUtilizationProjectScheme');
      }
    }
    else
    {
      this.destroyChartById('fundUtilizationProjectScheme');
    } 
                
    window.loadCounter--;
  }
          
  destroyChartById(elementId) 
  {
    const chartElement = document.querySelector(`#${elementId}`);
    if (chartElement && chartElement.children.length > 0) 
    {
      while (chartElement.firstChild) 
      {
        chartElement.removeChild(chartElement.firstChild);
      }
    }
  }
}