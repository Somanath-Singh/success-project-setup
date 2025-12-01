/*district wise project count*/

document.addEventListener('DOMContentLoaded', function () {

const pdistricts = [
  "ANGUL",
  "BALANGIR",
  "BARGARH",
  "BHADRAK",
  "BOUDH",
  "CUTTACK",
  "DEOGARH",
  "DHENKANAL",
  "GAJAPATI",
  "GANJAM",
  "JAJPUR",
  "JHARSUGUDA",
  "KALAHANDI",
  "KANDHAMAL",
  "KENDUJHAR",
  "KORAPUT",
  "MALKANGIRI",
  "MAYURBHANJ",
  "NAYAGARH",
  "NUAPADA",
  "PURI",
  "RAYAGADA",
  "SAMBALPUR",
  "SONEPUR",
  "SUNDARGARH",
  "JAGATSINGHPUR",
  "NABARANGPUR",
  "BALASORE",
  "KENDRAPARA",
  "KHORDHA"
];

  const projectChart = new ApexCharts(document.querySelector("#project-district-graph"), {
	  chart: {
      type: 'bar',
      height: 400,
      toolbar: { show: false }
    },
    series: [{
      name: 'Projects',
      data: [] 
    }],
    xaxis: {
      categories: [],
      labels: {
        rotate: -45,
        style: {
          fontSize: '10px'
        }
      }
    },
    colors: ['#A46B4D'],
    plotOptions: {
      bar: {
        borderRadius: 4,
        columnWidth: '75%',
      }
    },
    dataLabels: {
      enabled: true,
      style: {
        colors: ['#fff']
      }
    },
    tooltip: {
      y: {
        formatter: function (val) {
          return val + " Projects";
        }
      }
    },
    grid: {
      yaxis: {
        lines: { show: true }
      }
    },
    legend: {
      show: false
    }
  });

  projectChart .render();
  
  function loadDistrictWiseProjectCount(financialYearId) {
	  $.ajax({
	    type: "GET",
	    url: contextPath + "/scst/getDistrictWiseProjectCount",
	    data: financialYearId ? { finYearId: financialYearId } : {},
	    success: function (dataList) {
	    	
	      const msg = document.getElementById('project-status');
	      const allCountsZero = dataList.every(d => Number(d.count) === 0);
	      
    	  if (!dataList || dataList.length === 0 || allCountsZero) {
    		msg.style.display = 'block';
    	    msg.style.textAlign = 'center';
    	    msg.style.padding = '20px';
    	    msg.style.fontSize = '16px';
    	    msg.style.color = '#ff0000';
    	    msg.innerText  = 'No project data found for the selected financial year.';
    	    projectChart .updateSeries([{ name: 'Projects', data: [] }]);
    	    return;
    	  }else{
	   		 msg.style.display = 'none';
	   	  } 
	    	  
	      const countMap = {};
	      dataList.forEach(d => countMap[d.districtName.toUpperCase()] = d.count);
	      const values = pdistricts.map(dn => countMap[dn] || 0);
	
	      projectChart .updateOptions({
	        xaxis: {
	          categories: pdistricts
	        }
	      });
	
	      projectChart .updateSeries([{
	        name: "Projects",
	        data: values
	      }]);
	    },
	    error: function () {
	      console.error("Failed to load district data.");
	    }
	  });
  }
  
  loadDistrictWiseProjectCount();

  // Attach dropdown change listener
  document.getElementById("financialYear").addEventListener("change", function () {
    const selectedYear = this.value;
    loadDistrictWiseProjectCount(selectedYear || null);
  });
  
});
