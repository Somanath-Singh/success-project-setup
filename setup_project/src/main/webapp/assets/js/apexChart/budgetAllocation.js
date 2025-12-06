class BudgetAllocation
{
	constructor(apiPath, contextPath,finYear, monthId)
	{
		this.apiPath =  apiPath;
		this.worker = null;
		this.contextPath = contextPath;
		this.data = null;
		this.finYear = finYear;
    this.bdgAmt = 0;
    this.remAmt = 0;
    this.chart = null;
	}
	
	doFetch() 
	{
		if (this.worker == null )
		{
			this.worker = new Worker(this.contextPath + '/assets/appJs/worker/CommonWorker.js');
			this.worker.addEventListener('message', (e) => this.processData(e));
		}
		
		const url = this.contextPath + this.apiPath + "financial-year-wise-budget-details?finYear="+this.finYear;
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
            this.bdgAmt = this.data.budgetAmount != null ? parseFloat(this.data.budgetAmount) : 0;
            this.remAmt = this.data.remainingAmount != null ? parseFloat(this.data.remainingAmount) : 0;

            var options = {
                series: [this.bdgAmt, this.remAmt],
                chart: {
                    width: 400,
                    type: 'pie',
                },
                labels: ['Budget Amount', 'Remaining Amount'],
                responsive: [{
                    breakpoint: 480,
                    options: {
                        chart: {
                            width: 350,
                        },
                        legend: {
                            position: 'bottom'
                        }
                    }
                }],
                legend: {
                    position: 'bottom',
                    itemMargin: {
                        horizontal: 15 // Adjust as needed
                    }
                },
                // Remove stroke
                stroke: {
                    show: false
                },
                colors: ['#0d14fd','#d68916'] // Custom slice colors
            };

            this.destroyChartById('budgetAllocation');
                    
            this.chart = new ApexCharts(document.querySelector("#budgetAllocation"), options);
            this.chart.render();
		}
    else
    {
      this.destroyChartById('budgetAllocation');
    }
        
		window.loadCounter--;
	}
	
	getValueFor(key)
	{
		let retVal = 0;
		var result = Object.entries(this.data);
		result.filter( el => {
			if( el[0] == key)
				retVal =el[1];
		});
		return retVal;
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
            
