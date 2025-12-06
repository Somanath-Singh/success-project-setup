        class PayrollMgmt
        {
          constructor(apiPath, contextPath,finYear, empType)
          {
            this.apiPath =  apiPath;
            this.worker = null;
            this.contextPath = contextPath;
            this.data = null;
            this.finYear = finYear;
            this.empType = empType;
            this.chart = null;
          }
                  
          doFetch() 
          {
            if (this.worker == null )
            {
              this.worker = new Worker(this.contextPath + '/assets/appJs/worker/CommonWorker.js');
              this.worker.addEventListener('message', (e) => this.processData(e));
            }
                    
            const url = this.contextPath + this.apiPath + "payroll-details?finYear="+this.finYear+"&employmentType="+this.empType;
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
                var processedAmt = [];
                var totalStaffCount = [];
                var totalAbsentCount = [];
        
                $.each(this.data, function(k, v) {
                  months.push(v.monthName);
                  processedAmt.push(parseFloat(v.totalProcessedAmount));
                  totalStaffCount.push(parseFloat(v.totalStaffNo));
                  totalAbsentCount.push(parseFloat(v.totalAbsentNo));
                });

                var options = {
                  series: [{
                    name: "Salary Processed",
                    data: processedAmt
                },
                  {
                    name: "Total Staff",
                    data: totalStaffCount
                },
                  {
                    name: "Total Absent ",
                    data: totalAbsentCount
                }
                ],
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
                },
                grid: {
                  row: {
                    colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                    opacity: 0.5
                  },
                },
                xaxis: {
                  categories: months,
                    labels: {
                        rotate: 90,
                        style: {
                          fontSize: '10px'
                        }
                    }
                },
          yaxis: {
            title: {
              text: 'Employee'
            }
          },
                 legend: {
                   position: 'top',
                   horizontalAlign: 'right',
                   floating: true,
                   offsetY: -25,
                   offsetX: -5
                 }
                };
            
                this.destroyChartById('payrollChart');
                this.chart = new ApexCharts(document.querySelector("#payrollChart"), options);
                this.chart.render();  
              }
              else
              {
                this.destroyChartById('payrollChart');
              }
            }
            else
            {
              this.destroyChartById('payrollChart');
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