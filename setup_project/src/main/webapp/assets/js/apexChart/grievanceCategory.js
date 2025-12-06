class GrievanceCategory
{
  constructor(apiPath, contextPath,finYear, monthId)
  {
    this.apiPath =  apiPath;
    this.worker = null;
    this.contextPath = contextPath;
    this.data = null;
    this.finYear = finYear;
    this.monthId = monthId;
    this.chart = null;
  }
          
  doFetch() 
  {
    if (this.worker == null )
    {
      this.worker = new Worker(this.contextPath + '/assets/appJs/worker/CommonWorker.js');
      this.worker.addEventListener('message', (e) => this.processData(e));
    }
            
    const url = this.contextPath + this.apiPath + "category-wise-grv-details?finYear="+this.finYear+"&monthId="+this.monthId;
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
        var series = [];
        var labels = [];
        var total = 0;

        $.each(this.data, function(k, v) {
            series.push(parseFloat(v.categoryCount));
            labels.push(v.categoryTypeName);
            total += parseFloat(v.categoryCount);
        });

        var options = {
          series: series,
          chart: {
          height: 280,
          type: 'radialBar',
        },
        plotOptions: {
          radialBar: {
            dataLabels: {
              name: {
                fontSize: '22px',
              },
              value: {
                fontSize: '16px',
              },
              total: {
                show: true,
                label: 'Total',
                formatter: function (w) {
                  // By default this function returns the average of all series. The below is just an example to show the use of custom formatter function
                  return total
                }
              }
            }
          }
        },
        labels: labels,
        };
        
        this.destroyChartById('grivanceChart');
        this.chart = new ApexCharts(document.querySelector("#grivanceChart"), options);
        this.chart.render();  
      }
      else
      {
        this.destroyChartById('grivanceChart');
      }
    }
    else
    {
      this.destroyChartById('grivanceChart');
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