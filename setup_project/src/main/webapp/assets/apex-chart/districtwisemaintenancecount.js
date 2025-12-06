/*district wise maintenance count*/

document.addEventListener('DOMContentLoaded', function () {

const mdistricts = [
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

  const maintenanceChart  = new ApexCharts(document.querySelector("#maintenance-district-graph"), {
	  chart : {
      type: 'bar',
      height: 400,
      toolbar: { show: false }
    },
    series: [{
      name: 'Maintenance',
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
        columnWidth: '50%',
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
          return val + " Maintenance";
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

  maintenanceChart .render();

  function loadDistrictWiseMaintenanceCount(financialYearId){
	  $.ajax({
	    type: "GET",
	    url: contextPath + "/scst/getDistrictWiseMaintenanceRepairCount",
	    data: financialYearId ? { finYearId: financialYearId } : {},
	    success: function (dataList) {

	      const msg = document.getElementById('maintenance-status');
	      const allCountsZero = dataList.every(d => Number(d.count) === 0);
	      
	   	  if (!dataList || dataList.length === 0 || allCountsZero) {
	   		msg.style.display = 'block';
	   	    msg.style.textAlign = 'center';
	   	    msg.style.padding = '20px';
	   	    msg.style.fontSize = '16px';
	   	 	msg.style.color = '#ff0000';
	   	    msg.innerText = 'No maintenance data found for the selected financial year.';
	   	 	maintenanceChart .updateSeries([{ name: 'Maintenance', data: [] }]);
	   	    return;
	   	  }else{
	   		msg.style.display = 'none';
	   	  } 
	   	  
	      const countMap = {};
	      dataList.forEach(d => countMap[d.districtName.toUpperCase()] = d.count);
	      const values = mdistricts.map(dn => countMap[dn] || 0);
	
	      maintenanceChart .updateOptions({
	        xaxis: {
	          categories: mdistricts
	        }
	      });
	
	      maintenanceChart .updateSeries([{
	        name: "Maintenance",
	        data: values
	      }]);
	    },
	    error: function () {
	      console.error("Failed to load district data.");
	    }
	  });
	}
  
  loadDistrictWiseMaintenanceCount();

  // Attach dropdown change listener
  document.getElementById("financialYear").addEventListener("change", function () {
    const selectedYear = this.value;
    loadDistrictWiseMaintenanceCount(selectedYear || null);
  });

});
