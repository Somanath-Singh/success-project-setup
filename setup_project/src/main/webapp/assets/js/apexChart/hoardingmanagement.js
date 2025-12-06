class HoardingManagement
{
  constructor(apiPath, contextPath,finYear, areaId, hoardingId)
  {
    this.apiPath =  apiPath;
    this.worker = null;
    this.contextPath = contextPath;
    this.data = null;
    this.finYear = finYear;
    this.areaId = areaId;
    this.hoardingId = hoardingId;
    this.chart = null;
  }
          
  doFetch() 
  {
    if (this.worker == null )
    {
      this.worker = new Worker(this.contextPath + '/assets/appJs/worker/CommonWorker.js');
      this.worker.addEventListener('message', (e) => this.processData(e));
    }
            
    const url = this.contextPath + this.apiPath + "hoarding-information-details?finYear="+this.finYear+"&areaId="+this.areaId+"&hoardingId="+this.hoardingId;
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
        var totalData = [];
        var availData = [];
        var bookedData = [];

        $.each(this.data, function(k, v) {
          months.push(v.monthName);
          totalData.push(parseFloat(v.totalHoardingCount));
          availData.push(parseFloat(v.availableHoardingCount));
          bookedData.push(parseFloat(v.bookedHoardingCount));
        });

        var options = {
          series: [{
          name: 'Total',
          data: totalData
        }, {
          name: 'Booked',
          data: bookedData
        }, {
          name: 'Available',
          data: availData
        }
        ],
          chart: {
          type: 'bar',
          height: 325
        },
        colors: ['#1E90FF', '#FF6347','#00f741'],
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
            text: 'No. Of Hoarding'
          }
        },
        fill: {
          opacity: 1
        },
        tooltip: {
          y: {
            formatter: function (val) {
              return "" + val + " Hoarding"
            }
          }
        }
        };
    
        this.destroyChartById('hoardingmanagement');
        this.chart = new ApexCharts(document.querySelector("#hoardingmanagement"), options);
        this.chart.render();  
      }
      else
      {
        this.destroyChartById('hoardingmanagement');
      }
    }
    else
    {
      this.destroyChartById('hoardingmanagement');
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
