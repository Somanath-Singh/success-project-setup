class FundUtilizationAmount
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
            
    const url = this.contextPath + this.apiPath + "month-wise-utilization-amount-details?finYear="+this.finYear;
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
        var months = [];
        var utilzData = [];

        $.each(this.data, function(k, v) {
          months.push(v.monthName);
          utilzData.push(parseFloat(v.utilizedAmount));
        });

        var options = {
          series: [
          {
            name: "Utilization",
            data: utilzData
          }
        ],
          chart: {
          height: 327,
          type: 'line',
          dropShadow: {
            enabled: true,
            color: '#000',
            top: 18,
            left: 7,
            blur: 10,
            opacity: 1
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
            opacity: 1
          },
        },
        markers: {
          size: 1
        },
        xaxis: {
          categories: months,
          title: {
           
          }
        },
        yaxis: {
          title: {
            text: 'Rupees (In lakhs)'
          },
          min: 1,
          max: 15
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
              return "Rs." + val + " lakhs"
            }
          }
        }
        };
    
        this.destroyChartById('fundUtilizationAmount');
        this.chart = new ApexCharts(document.querySelector("#fundUtilizationAmount"), options);
        this.chart.render();  
      }
      else
      {
        this.destroyChartById('fundUtilizationAmount');
      }
    }
    else
    {
      this.destroyChartById('fundUtilizationAmount');
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