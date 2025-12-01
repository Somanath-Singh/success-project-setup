/**
 * 
 */
 
/* function selectObjectEntity(targetValue){
 const selectElement = document.getElementById("objectEntityIdAndTypeId");

    const targetValue = targetValue;

    // Check if the value exists in the dropdown options
    const optionExists = Array.from(selectElement.options).some(option => option.value === targetValue);

    if (optionExists) {
        // Set the dropdown's value to the target
        selectElement.value = targetValue;

        // Trigger the onchange event if needed
        const event = new Event("change");
        selectElement.dispatchEvent(event);
    } else {
        console.log(`Option with value "${targetValue}" not found in the dropdown.`);
    }
 }*/
 
 function featchProjectListByEntity(entityIdLevelCombo,selectedProjId,targetId){
	var html = "<option value=''>- Select -</option>";
	    $.ajax({
	        type: "GET",
	        url: contextPath + "/common/api/featchProjectListByEntity",
	        data: { "entityIdLevelCombo": entityIdLevelCombo },
	        success: function(response) {
	            var obj = response.result;
	            $.each(obj, function(index, value) {
                   html += '<option value="' + value.projectId + '">' + value.projectNameCode + '</option>';
	            });
	            $("#" + targetId).empty().append(html);
	            if (selectedProjId != 0 && selectedProjId != '') {
	                $("#" + targetId).val(selectedProjId);
	                
	            }
	        },
	        error: function(error) {
	            console.error("Error fetching featchProjectListByEntity:", error);
	            bootbox.alert("Error found at featchProjectListByEntity");
	        }
	    });
}
 
 
 
 function featchWorkEstimationByProject(projectId,selectedEstId,targetId){
	var html = "<option value=''>- Select -</option>";
	    $.ajax({
	        type: "GET",
	        url: contextPath + "/common/api/featchWorkEstimationByProject",
	        data: { "projectId": projectId },
	        success: function(response) {
	            var obj = response.result;
	            $.each(obj, function(index, value) {
	                html += '<option value="' + value.estimationId + '">' + value.estimationNo + '</option>';
	            });
	            $("#" + targetId).empty().append(html);
	            if (selectedEstId != 0 && selectedEstId != '') {
	                $("#" + targetId).val(selectedEstId);
	            }
	        },
	        error: function(error) {
	            console.error("Error fetching featchWorkEstimationByProject:", error);
	            bootbox.alert("Error found at featchWorkEstimationByProject");
	        }
	    });
}

 function featchWorkOrdersByProject(projectId, selectedWoId, targetId) {
	    var html = "<option value=''>- Select -</option>";
	    $.ajax({
	        type: "GET",
	        url: contextPath + "/common/api/featchWorkOrdersByProject",
	        data: { "projectId": projectId },
	        success: function(response) {
	            var obj = response.result;
	            $.each(obj, function(index, value) {
	                html += '<option value="' + value.workOrderId + '">' + value.workOrderNo + '</option>';
	            });
	            $("#" + targetId).empty().append(html);
	            if (selectedWoId != 0 && selectedWoId != '') {
	                $("#" + targetId).val(selectedWoId);
	            }
	        },
	        error: function(error) {
	            console.error("Error fetching work orders:", error);
	            bootbox.alert("Error found at featchWorkOrdersByProject");
	        }
	    });
	}

	function featchBoqListByWorkOrder(workOrderId, selectedBoqId, targetId) {
	    var html = "<option value=''>- Select -</option>";
	    $.ajax({
	        type: "GET",
	        url: contextPath + "/common/api/featchBoqListByWorkOrder",
	        data: { "workOrderId": workOrderId },
	        success: function(response) {
	            var obj = response.result;
	            $.each(obj, function(index, value) {
	                html += '<option value="' + value.boqMstId + '">' + value.boqCode + '</option>';
	            });
	            $("#" + targetId).empty().append(html);
	            if (selectedBoqId != 0 && selectedBoqId != '') {
	                $("#" + targetId).val(selectedBoqId);
	            }
	        },
	        error: function(error) {
	            console.error("Error fetching BOQ list:", error);
	            bootbox.alert("Error found at featchBoqListByWorkOrder");
	        }
	    });
	}

	function featchMeasurementListByBoq(boqMstId, selectedMwasurementId, targetId) {
	debugger;
	    var html = "<option value=''>- Select -</option>";
	    $.ajax({
	        type: "GET",
	        url: contextPath + "/common/api/featchMeasurementListByBoq",
	        data: { "boqMstId": boqMstId },
	        success: function(response) {
	            var obj = response.result;
	            $.each(obj, function(index, value) {
	                html += '<option value="' + value.msmntMstId + '">' + value.measurementCode + '</option>';
	            });
	            $("#" + targetId).empty().append(html);
	            if (selectedMwasurementId != 0 && selectedMwasurementId != '') {
	                $("#" + targetId).val(selectedMwasurementId);
	            }
	        },
	        error: function(error) {
	            console.error("Error fetching Measurement list:", error);
	            bootbox.alert("Error found at featchMeasurementListByBoq");
	        }
	    });
	}