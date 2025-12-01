	function LoadBlockByDistrict(distId) {
		
	    $.ajax({
	        type: "GET",
	        url: contextPath + "/common/fetchBlockByDistrict",
	        data: {
	        	districtId: distId,
	        },
	        success: function (blocks) {
	            var block = $('#blockId'); 
	            block.empty();
	            block.append('<option value="" selected disabled>- Select -</option>');

	            $.each(blocks, function(index, blockData) {
	            	block.append('<option value="' + blockData.blockId + '">' + blockData.blockName + '</option>');
	            });
	        },
	        error: function (error) {
	            console.error("Error fetching blocks:", error);
	        }
	    });
	}
	
	function LoadGrampanchayatByBlock(blockId) {
		
	    $.ajax({
	        type: "GET",
	        url: contextPath + "/common/fetchGrampanchayatByBlock",
	        data: {
	        	blockId: blockId,
	        },
	        success: function (grampanchayats) {
	            var gp = $('#gpId'); 
	            gp.empty();
	            gp.append('<option value="" selected disabled>- Select -</option>');

	            $.each(grampanchayats, function(index, grampanchayat) {
	            	gp.append('<option value="' + grampanchayat.gpId + '">' + grampanchayat.gpName + '</option>');
	            });
	        },
	        error: function (error) {
	            console.error("Error fetching grampanchayat:", error);
	        }
	    });
	}

	function LoadVillageByGrampanchayat(gpId) {
	    $.ajax({
	        type: "GET",
	        url: contextPath + "/common/fetchVillageByGrampanchayat",
	        data: {
	        	gpId: gpId,
	        },
	        success: function (villages) {
	            var gp = $('#villageId'); 
	            gp.empty();
	            gp.append('<option value="" selected disabled>- Select -</option>');

	            $.each(villages, function(index, village) {
	            	gp.append('<option value="' + village.villageId + '">' + village.villageName + '</option>');
	            });
	        },
	        error: function (error) {
	            console.error("Error fetching village:", error);
	        }
	    });
	}
	
	function LoadMunicipalityByDistrict(districtId) {
	    $.ajax({
	        type: "GET",
	        url: contextPath + "/common/fetchMunicipalityByDistrict",
	        data: {
	        	districtId: districtId,
	        },
	        success: function (municipalitys) {
	            var municipality = $('#municipalityId'); 
	            municipality.empty();
	            municipality.append('<option value="" selected disabled>- Select -</option>');

	            $.each(municipalitys, function(index, municipality) {
	            	ward.append('<option value="' + municipality.municipalityId + '">' + municipality.municipalityName + '</option>');
	            });
	        },
	        error: function (error) {
	            console.error("Error fetching municipality:", error);
	        }
	    });
	}
	
	function LoadWardByMunicipality(municipalityId) {
	    $.ajax({
	        type: "GET",
	        url: contextPath + "/common/fetchWardByMunicipality",
	        data: {
	        	municipalityId: municipalityId,
	        },
	        success: function (wards) {
	            var ward = $('#wardId'); 
	            ward.empty();
	            ward.append('<option value="" selected disabled>- Select -</option>');

	            $.each(wards, function(index, ward) {
	            	ward.append('<option value="' + ward.wardId + '">' + ward.wardName + '</option>');
	            });
	        },
	        error: function (error) {
	            console.error("Error fetching ward:", error);
	        }
	    });
	}
	
	
	function uploadFileCheck(that){
		var ValidFileExtension = ['pdf','jpg', 'jpeg', 'png'];

		if($(that).val().split('.').length == 2 ) {
			  if ($.inArray($(that).val().split('.').pop().toLowerCase(), ValidFileExtension) == -1) {
					bootbox.alert("Please upload only .pdf, .jpg/.jpeg/.png files.");
					$(that).val("");
					return false;
				}
			  if ((that.files[0].size) > 512000) {//500KB
					bootbox.alert("File size exceeds maximum file size of 500 KB!");
					$(that).val("");
					return false;
				}
			}else{
			   bootbox.alert("Unsupported file format,Please check your file extension");
			   $(that).val("");
		 }
	}

	
	function fetchRuralLocationCode(){
	
		const districtId = $("#districtId").val();
		const blockId = $("#blockId").val();
	    const gpId = $("#gpId").val();
	    const villageId = $("#villageId").val();
		
		$.ajax({
	        type : "GET",
	        url : contextPath + "/building/get-rural-location-code",
	        data : {
	        	districtId,blockId,gpId,villageId
	        },
	        success : function(response) {
	            $('#locationCode').val(response);
	            $('#buildingCode').val(Number(response.split('-').pop()));
	        },
	        error : function(error) {
	        }
	    });
	}

	function fetchUrbanLocationCode(){
		
		const districtId = $("#districtId").val();
		const municipalityId = $("#municipalityId").val();
	    const wardId = $("#wardId").val();
		
		$.ajax({
	        type : "GET",
	        url : contextPath + "/building/get-rural-location-code",
	        data : {
	        	districtId,municipalityId,wardId
	        },
	        success : function(response) {
	        	$('#locationCode').val(response);
	        	$('#buildingCode').val(Number(response.split('-').pop()));
	        },
	        error : function(error) {
	        }
	    });
	}
		
	function viewDocument(filePath,moduleName){
		debugger
		 $('#moduleName').val(moduleName);
		 $('#filePath').val(filePath);
		 $('#documentFormView').submit();
		}
		