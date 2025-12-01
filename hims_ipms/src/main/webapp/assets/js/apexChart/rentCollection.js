class RentCollection
{
  constructor(apiPath, contextPath, finYear)
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
            
    const url = this.contextPath + this.apiPath + "rent-collection-details?finYear="+this.finYear;
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
        var clcAmt = [];
        var defltAmt = [];

        $.each(this.data, function(k, v) {
          months.push(v.monthName);
          clcAmt.push(parseFloat(v.collectionAmount));
          defltAmt.push(parseFloat(v.defaulterAmount));
        });


        var options = {
          series: [{
          name: 'Rent Collection',
          data: clcAmt
        }, {
          name: 'Rent Defaulter',
          data: defltAmt
        }
        ],
          chart: {
          type: 'bar',
          height: 350
        },
        colors: ['#1E90FF', '#FF6347'],
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: '55%',
            endingShape: 'rounded'
          },
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          show: true,
          width: 2,
          colors: ['transparent']
        },
        xaxis: {
          categories: months,
        },
        yaxis: {
          title: {
            text: 'Rupees (In Lakhs)'
          }
        },
        fill: {
          opacity: 1
        },
        tooltip: {
          y: {
            formatter: function (val) {
              return "Rs. " + val + " Lakhs"
            }
          }
        }
        };

        this.destroyChartById('rentCollection');
        this.chart = new ApexCharts(document.querySelector("#rentCollection"), options);
        this.chart.render();
      }
      else
      {
        this.destroyChartById('rentCollection');
      }
    }
    else
    {
      this.destroyChartById('rentCollection');
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