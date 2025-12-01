class FundUtilizationProject
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
            
    const url = this.contextPath + this.apiPath + "month-wise-utilization-project-details?finYear="+this.finYear;
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
        var prjData = [];

        $.each(this.data, function(k, v) {
          months.push(v.monthName);
          prjData.push(parseFloat(v.projectCount));
        });

        var options = {
          series: [{
          name: 'Project',
          data: prjData
        }],
          chart: {
          type: 'bar',
          height: 327
        },
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: '30%',
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
            text: 'Projects'
          }
        },
        fill: {
          opacity: 1
        },
        tooltip: {
          y: {
            formatter: function (val) {
              return val
            }
          }
        }
        };
    
        this.destroyChartById('fundUtilizationProject');
        this.chart = new ApexCharts(document.querySelector("#fundUtilizationProject"), options);
        this.chart.render();  
      }
      else
      {
        this.destroyChartById('fundUtilizationProject');
      }
    }
    else
    {
      this.destroyChartById('fundUtilizationProject');
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