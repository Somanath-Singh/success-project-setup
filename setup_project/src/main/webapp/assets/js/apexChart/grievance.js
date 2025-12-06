class Grievance
{
	constructor(apiPath, contextPath,finYear, monthId)
	{
		this.apiPath =  apiPath;
		this.worker = null;
		this.contextPath = contextPath;
		this.data = null;
		this.finYear = finYear;
        this.monthId = monthId;
        this.progCount = 0;
        this.resCount = 0;
        this.rejCount = 0;
        this.appCount = 0;
        this.chart = null;
	}
	
	doFetch() 
	{
		if (this.worker == null )
		{
			this.worker = new Worker(this.contextPath + '/assets/appJs/worker/CommonWorker.js');
			this.worker.addEventListener('message', (e) => this.processData(e));
		}
		
		const url = this.contextPath + this.apiPath + "status-wise-grv-details?finYear="+this.finYear+"&monthId="+this.monthId;
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
            this.progCount = this.data.progCount != null ? parseFloat(this.data.progCount) : 0;
            this.resCount = this.data.resCount != null ? parseFloat(this.data.resCount) : 0;
            this.rejCount = this.data.rejCount != null ? parseFloat(this.data.rejCount) : 0;
            this.appCount = this.data.appCount != null ? parseFloat(this.data.appCount) : 0;

            var options = {
                series: [this.progCount, this.resCount, this.appCount, this.rejCount],
                chart: {
                    width: 400,
                    type: 'pie',
                },
                labels: ['In Progress', 'Resolved', 'Approved', 'Rejected'],
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
                colors: ['#0d14fd','#d68916', '#008000', '#16c4d6'] // Custom slice colors
            };

            this.destroyChartById('grievance');
            
            this.chart = new ApexCharts(document.querySelector("#grievance"), options);
            this.chart.render();
		}
        else
        {
            this.destroyChartById('grievance');
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
            
