<style>
	/* HTML: <div class="loader"></div> */
.loader {
  --w:10ch;
  font-weight: bold;
  font-family: monospace;
  font-size: 30px;
  letter-spacing: var(--w);
  width:var(--w);
  overflow: hidden;
  white-space: nowrap;
  text-shadow: 
    calc(-1*var(--w)) 0, 
    calc(-2*var(--w)) 0, 
    calc(-3*var(--w)) 0, 
    calc(-4*var(--w)) 0,
    calc(-5*var(--w)) 0, 
    calc(-6*var(--w)) 0, 
    calc(-7*var(--w)) 0, 
    calc(-8*var(--w)) 0, 
    calc(-9*var(--w)) 0;
  animation: l16 2s infinite;
}
.loader:before {
  content:"Loading...";
}
@keyframes l16 {
  20% {text-shadow: 
    calc(-1*var(--w)) 0, 
    calc(-2*var(--w)) 0 red, 
    calc(-3*var(--w)) 0, 
    calc(-4*var(--w)) 0 #ffa516,
    calc(-5*var(--w)) 0 #63fff4, 
    calc(-6*var(--w)) 0, 
    calc(-7*var(--w)) 0, 
    calc(-8*var(--w)) 0 green, 
    calc(-9*var(--w)) 0;}
  40% {text-shadow: 
    calc(-1*var(--w)) 0, 
    calc(-2*var(--w)) 0 red, 
    calc(-3*var(--w)) 0 #e945e9, 
    calc(-4*var(--w)) 0,
    calc(-5*var(--w)) 0 green, 
    calc(-6*var(--w)) 0 orange, 
    calc(-7*var(--w)) 0, 
    calc(-8*var(--w)) 0 green, 
    calc(-9*var(--w)) 0;}
  60% {text-shadow: 
    calc(-1*var(--w)) 0 lightblue, 
    calc(-2*var(--w)) 0, 
    calc(-3*var(--w)) 0 #e945e9, 
    calc(-4*var(--w)) 0,
    calc(-5*var(--w)) 0 green, 
    calc(-6*var(--w)) 0, 
    calc(-7*var(--w)) 0 yellow, 
    calc(-8*var(--w)) 0 #ffa516, 
    calc(-9*var(--w)) 0 red;}
  80% {text-shadow: 
    calc(-1*var(--w)) 0 lightblue, 
    calc(-2*var(--w)) 0 yellow, 
    calc(-3*var(--w)) 0 #63fff4, 
    calc(-4*var(--w)) 0 #ffa516,
    calc(-5*var(--w)) 0 red, 
    calc(-6*var(--w)) 0, 
    calc(-7*var(--w)) 0 grey, 
    calc(-8*var(--w)) 0 #63fff4, 
    calc(-9*var(--w)) 0 ;}
}
</style>


<!-- Create a modal havibg table slno, action tacken by, remark, date -->
<div class="modal fade" id="historyTableRemarksModal" tabindex="-1" aria-labelledby="actionTakenModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="text-white" id="actionTakenModalLabel">Data Flow History</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			
			<div class="modal-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered">
						<thead class="">
							<tr>
								<th style="min-width:60px;text-align:center;">Sl No</th>
								<th>Stage</th>
								<th>Action Taken By</th>
								<th>Action Taken Role</th>
								<th>Remark</th>
								<th>Date</th>
							</tr>
						</thead>
						<tbody id="historyTableBodyId">
							
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
			</div>
			
		</div>
	</div>
</div>


<div class="modal fade" id="userSpecificRuleModal" tabindex="-1" role="dialog" aria-labelledby="userSpecificRuleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
				<div class="modal-header">
					<h5 class="text-white" id="userSpecificRuleModalLabel">User Specific Rule</h5>
					<button type="button" class="close unfreezeField" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="userSpecificRuleModalBody">
					<table class="table table-bordered table-striped table-hover">
						<input type="hidden" id="stageInUserSpecificRuleModal" value="">
						<input type="hidden" id="formIdInUserSpecificRuleModal" value="">
						<input type="hidden" id="remarksInUserSpecificRuleModal" value="">
						<input type="hidden" id="isBulkInUserSpecificRuleModal" value="">
						<thead>
							<tr>
								<th style="min-width:60px;text-align:center;">Sl No</th>
								<th>User Name</th>
								<th>Role Name</th>
								<th>Check</th>
							</tr>
						</thead>
						<tbody id="userSpecificRuleModalBodyTbody">
							
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" onclick="buttonActionWithUser(this)" class="btn btn-primary unfreezeField">Submit</button>
					<button type="button" class="btn btn-secondary unfreezeField" data-dismiss="modal">Close</button>
				</div>
		</div>
	</div>
</div>



<div class="modal fade" id="entitySpecificRuleModal" tabindex="-1" role="dialog" aria-labelledby="userSpecificRuleModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
				<div class="modal-header">
					<h5 class="text-white" id="userSpecificRuleModalLabel">Entity Specific Rule</h5>
					<button type="button" class="close unfreezeField" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="entitySpecificRuleModalBody">
					 <div class="row" style="margin-bottom: 10px;" id="entityTypeListDivId"></div>
					<!-- Entity list selct option -->
					<div class="row" style="margin-bottom: 10px;" id="entityListDivId">
						<div class="col-md-4">
							<select class="form-control" id="entityListSelectId" onchange="getDynamicEntityObjectList(this.value)">
								<option value="">Select Entity</option>
							</select>
						</div>
					</div>


					<table class="table table-bordered table-striped table-hover">
						
						<thead>
							<tr>
								<th style="min-width:60px;text-align:center;"s>Sl No</th>
								<th>Role Name</th>
								<th>Type</th>
								<th>Check</th>
								<th>Is User Specific</th>
							</tr>
						</thead>
						<tbody id="entitySpecificRuleModalBodyTbodyForRole">
							
						</tbody>
					</table>

					<table class="table table-bordered table-striped table-hover">
					
						<thead>
							<tr>
								<th style="min-width:60px;text-align:center;">Sl No</th>
								<th>User Name</th>
								<th>Type</th>
								<th>Check</th>
							</tr>
						</thead>
						<tbody id="entitySpecificRuleModalBodyTbodyForUser">
							
						</tbody>
					</table>


				</div>
				<div class="modal-footer">
					<button type="button" onclick="buttonActionWithUser(this)" class="btn btn-submit unfreezeField">Submit</button>
					<button type="button" class="btn btn-secondary unfreezeField" data-dismiss="modal">Close</button>
				</div>
		</div>
	</div>
</div>


<script type="text/javascript">

	function addHiddenField(name, value) {
    	return "<input type='hidden' name='" + name + "' value='" + value + "'>";
	}

	function buttonActionWithUser(){
	   let selectedUserIds = [];
	   $("input[name='userSpecificRule']:checked").each(function(){
		   selectedUserIds.push($(this).val());
	   });
	   if(selectedUserIds.length == 0){
		   bootbox.alert("Please select at least one user");
		   return;
	   }
	   
	   let stageId = $("#stageInUserSpecificRuleModal").val();
	   let formId = $("#formIdInUserSpecificRuleModal").val();
	   let remarks = $("#remarksInUserSpecificRuleModal").val();
	   let isBulk = $("#isBulkInUserSpecificRuleModal").val();
	   $("#userSpecificRuleModal").modal("hide");
	   if(isBulk == "true"){
		   bulkUpdateWorkFlowStageOnSpecificUsers(formId, stageId, remarks, selectedUserIds, true);
	   }else{
			saveWorkFlowStageOnSpecificUsers(formId, stageId, remarks, selectedUserIds, true);
	   }
   }

	function workFlowForViewOfSpecificData(formId, entityObjectId, formSubmit = false){
		if(formId == null || formId == undefined || formId == ""){
			bootbox.alert("Please provide form id where you want to view the data");
			return;
		}
		if(entityObjectId != null && entityObjectId != undefined && entityObjectId != "" && parseInt(entityObjectId) > 0){
			let entityObjectIdHtml = addHiddenField("dynamicDataParamEntityObjectId", entityObjectId);
			$("#"+formId).append(entityObjectIdHtml);
		}
		if(formSubmit){
			$("#"+formId).submit();
		}
	}


	function saveWorkFlowStage(formId, stageId, remarks="", formSubmit = false){
		if(formId == null || formId == undefined || formId == ""){
			bootbox.alert("Please provide form id where you want to submit the data");
			return;
		}
		if(stageId != null && stageId != undefined && stageId != "" && parseInt(stageId) > 0){
			let stageIdHtml = addHiddenField("currentWorkflowStageParamName", stageId);
			$("#"+formId).append(stageIdHtml);
		}
		if(remarks != null && remarks != undefined && remarks != ""){
			let remarksHtml = addHiddenField("currentWorkflowStageParamRemarks", remarks);
			$("#"+formId).append(remarksHtml);
		}
		if(formSubmit){
			$("#"+formId).submit();
		}
	}

	function setWorkFlowStage(formId, stageId){
		saveWorkFlowStage(formId, stageId, "", false);
	}

	function updateWorkFlowStage(formId, stageId, remarks, formSubmit = false, entityObjectId){
		saveWorkFlowStage(formId, stageId, remarks, false);
	   if(entityObjectId != null && entityObjectId != undefined && entityObjectId != "" && parseInt(entityObjectId) > 0){
		   let entityObjectIdHtml = "<input type='hidden' name='dynamicDataParamEntityObjectId'  value='"+entityObjectId+"'>";
		   $("#"+formId).append(entityObjectIdHtml);
	   }
	   if(formSubmit){
		   $("#"+formId).submit();
	   }
   }

	function userSpecificWorkFlow(formId, stageId, remarks, isBulk = false){
	   if(formId == null || formId == undefined || formId == ""){
		   bootbox.alert("Form id is required");
		   return;
	   }
	   if(stageId == null || stageId == undefined || stageId == ""){
		   bootbox.alert("Stage id is required");
		   return;
	   }
	   if(remarks == null || remarks == undefined || remarks == ""){
		   remarks = "";
	   }
	   $.ajax({
		url: "${pageContext.request.contextPath}/stageConfig/getAllUsersOnParticularStage",
		type: "GET",
		data: {
			stageId: stageId
		},
		success: function(response){
				if(response.length > 0){
					let userSpecificRuleModalBodyTbody = $("#userSpecificRuleModalBodyTbody");
					userSpecificRuleModalBodyTbody.empty();
					let slNo = 1;
					for(let i=0; i<response.length; i++){
						let user = response[i];
						let tr = $("<tr></tr>");
						let td1 = $("<td></td>").text(slNo);
						let td2 = $("<td></td>").text(user.userFirstName + " " + user.userLastName);
						let td3 = $("<td></td>").text(user.primaryRole.displayName);
						let td4 = $("<td></td>");
						let input = $("<input type='checkbox' name='userSpecificRule' value='"+user.userId+"'>");
						td4.append(input);
						tr.append(td1, td2, td3, td4);
						userSpecificRuleModalBodyTbody.append(tr);
						slNo++;
					}
					$("#stageInUserSpecificRuleModal").val(stageId);
					$("#formIdInUserSpecificRuleModal").val(formId);
					$("#remarksInUserSpecificRuleModal").val(remarks);
					$("#isBulkInUserSpecificRuleModal").val(isBulk);
					$("#userSpecificRuleModal").modal("show");
				}
			},
		error: function(response){
				bootbox.alert("Something went wrong");
			}
		});
	}

	function saveWorkFlowStageOnSpecificUsers(formId, stageId, remarks, userIds = [], formSubmit = false){
		if(formId == null || formId == undefined || formId == ""){
			bootbox.alert("Please provide form id where you want to submit the data");
			return;
		}
		if(stageId != null && stageId != undefined && stageId != "" && parseInt(stageId) > 0){
			let stageIdHtml = addHiddenField("currentWorkflowStageParamName", stageId);
			$("#"+formId).append(stageIdHtml);
		}
		if(remarks != null && remarks != undefined && remarks != ""){
			let remarksHtml = addHiddenField("currentWorkflowStageParamRemarks", remarks);
			$("#"+formId).append(remarksHtml);
		}
		if(userIds != null && userIds != undefined && userIds.length > 0){
			let specificUserIds = userIds.join(",");
			let userIdsHtml = addHiddenField("specificUserParamRemarks", specificUserIds);
			$("#"+formId).append(userIdsHtml);
		}
		if(formSubmit){
			$("#"+formId).submit();
		}
	}


	function bulkUpdateWorkFlowStage(formId, stageId, remarks, size, formSubmit = false){
		if(formId == null || formId == undefined || formId == ""){
			bootbox.alert("Please provide form id where you want to submit the data");
			return;
		}
		if(remarks != null && remarks != undefined && remarks != ""){
			let remarksHtml = addHiddenField("currentWorkflowStageParamRemarks", remarks);
			$("#"+formId).append(remarksHtml);
		}

		if(size != null && size != undefined && size != "" && parseInt(size) > 0){
			let bulkStgaeIdArr = [];
			for(let i=1; i<=size; i++){
				bulkStgaeIdArr.push(stageId);
			}
			// conver array to string with comma separated
			let bulkStgaeIdStr = bulkStgaeIdArr.join(",");
			if(bulkStgaeIdStr != null && bulkStgaeIdStr != undefined && bulkStgaeIdStr != ""){
				let bulkStgaeIdHtml = addHiddenField("currentWorkflowStageParamBulkName", bulkStgaeIdStr);
				$("#"+formId).append(bulkStgaeIdHtml);
			}
		}
		if(formSubmit){
			$("#"+formId).submit();
		}
	}

	function bulkUpdateWorkFlowStageOnSpecificUsers(formId, stageId, remarks, size, userIds = [], formSubmit = false){
		if(formId == null || formId == undefined || formId == ""){
			bootbox.alert("Please provide form id where you want to submit the data");
			return;
		}
		if(remarks != null && remarks != undefined && remarks != ""){
			let remarksHtml = addHiddenField("currentWorkflowStageParamRemarks", remarks);
			$("#"+formId).append(remarksHtml);
		}

		if(size != null && size != undefined && size != "" && parseInt(size) > 0){
			let bulkStgaeIdStr = "";
			for(let i=1; i<=size; i++){
				let stageId = $("#bulkStageId"+i).val();
				if(stageId != null && stageId != undefined && stageId != "" && parseInt(stageId) > 0){
					bulkStgaeIdStr += stageId + ",";
				}
			}
			if(bulkStgaeIdStr != null && bulkStgaeIdStr != undefined && bulkStgaeIdStr != ""){
				bulkStgaeIdStr = bulkStgaeIdStr.substring(0, bulkStgaeIdStr.length-1);
				let bulkStgaeIdHtml = addHiddenField("currentWorkflowStageParamBulkName", bulkStgaeIdStr);
				$("#"+formId).append(bulkStgaeIdHtml);
			}
		}

		if(userIds != null && userIds != undefined && userIds.length > 0){
			let specificUserIds = userIds.join(",");
			let userIdsHtml = addHiddenField("specificUserParamRemarks", specificUserIds);
			$("#"+formId).append(userIdsHtml);
		}
		if(formSubmit){
			$("#"+formId).submit();
		}
	}

	function bulkUserSpecificWorkFlow(formId, size, remarks){
		// use same function for bulk update and user specific work flow
		userSpecificWorkFlow(formId, size, remarks, true);
	}

	async function asyncCallForData(url, type, data){
		return new Promise((resolve, reject) => {
			$.ajax({
			url: url,
			type: type,
			async: true,
			data: data,
			success: function(response){
				resolve(response);
			},
			error: function(response){
				reject(response);
			}
			});
		});
    }

	// on change of select call this function by class name "getStageButtonsOnForm"
	$(document).ready(function(){
		$(".getStageButtonsOnForm").change(function(){
			debugger;
			let valueArry = [];
			// get all the values having class name "getStageButtonsOnForm"
			$(".getStageButtonsOnForm").each(function(){
				let value = $(this).val();
				if(value != null && value != undefined && value != ""){
					valueArry.push(value);
				}
			});

			console.log(valueArry);
			$("#ajaxDynamicButtonsId").html("");
			if(valueArry.length > 0){
				let activeTabObj = $(".getActiveTabCodeForStage a.active");
				let activeTabCode = '';
				if(activeTabObj != null && activeTabObj != undefined && activeTabObj.length > 0){
					activeTabCode = activeTabObj[0].getAttribute("data-tab-code");
				}
				getStageButtonsOnForm(valueArry, activeTabCode);
			}
		});
	});

	// ... stage actions loading html
	function createActionLoadingHtml(){
		return "<div class='loader'></div>";
	}

	// also call this function on page load
	$(document).ready(function(){
		let valueArry = [];
		$(".getStageButtonsOnForm").each(function(){
			let value = $(this).val();
			if(value != null && value != undefined && value != ""){
				valueArry.push(value);
			}
		});
		$("#ajaxDynamicButtonsId").html("");
		if(valueArry.length > 0){
			let activeTabObj = $(".getActiveTabCodeForStage a.active");
			let activeTabCode = '';
			if(activeTabObj != null && activeTabObj != undefined && activeTabObj.length > 0){
				activeTabCode = activeTabObj[0].getAttribute("data-tab-code");
			}
			getStageButtonsOnForm(valueArry, activeTabCode);
		}
	});


	// Entity Class Name
	var dynamicWorkFlowEntityClassName = "${dynamicWorkFlowEntityClassName}";
	var dynamicDataParamEntityObjectId = "${dynamicDataParamEntityObjectId}";
	var dynamicUrlForDynamicAction = "${dynamicUrlForDynamicAction}";
	// on change call this function by class name "getStageButtonsOnForm"
	async function getStageButtonsOnForm(vl, activeTabCode = ''){
		$("#ajaxDynamicButtonsId").html(createActionLoadingHtml());
		
		let value = vl;
		if(value != null && value != undefined && value != ""){
			let orgIdAndOrgLevel = $("#orgIdAndOrgLevel").val() != null && $("#orgIdAndOrgLevel").val() != undefined && $("#orgIdAndOrgLevel").val() != "" ? $("#orgIdAndOrgLevel").val() : "";
			let url = "${pageContext.request.contextPath}"+dynamicUrlForDynamicAction;
			let type = "GET";
			let data = {
				entityClassName: dynamicWorkFlowEntityClassName,
				flowCodeValue: value,
				entityObjectId: dynamicDataParamEntityObjectId != null && dynamicDataParamEntityObjectId != undefined && dynamicDataParamEntityObjectId != "" ? dynamicDataParamEntityObjectId : 0,
				activeTabCode: activeTabCode,
				orgIdAndOrgLevel: orgIdAndOrgLevel,
			};
			// encrpt form data base64
			let encryPtData = btoa(JSON.stringify(data));

			let finalData = {
				encryptObject: encryPtData
			};
			let response = await asyncCallForData(url, type, finalData);
			if(response != null && response != undefined){
				let data = response.data;
				let stageButtonsHtml = "";
				let canTakeAction = true;
				for(let i=0; i<data.length; i++){
					if(i == 0){
						canTakeAction = data[i].canTakeAction;
					}
					let buttonName = data[i].buttonName;
					let stageId = data[i].stageId;
					stageButtonsHtml += "<button type='button' class='btn btn-primary unfreezeField' onclick='submitFormData("+stageId+")'>"+buttonName+"</button>";
				}
				//stageButtonsHtml +="<button type='button' class='btn btn-danger unfreezeField back' onclick='goBack();' >Back</button>";
				$("#ajaxDynamicButtonsId").html(stageButtonsHtml);
                //$(".back").hide();
				// get the form id from the form having class name "getStageButtonsOnForm"
				let dynamicFromId = $(".getStageButtonsOnForm").closest("form").attr("id");
				if(!canTakeAction){
					frezzFormBycanTakeAction(dynamicFromId);
				}else{
					unfrezzFormBycanTakeAction(dynamicFromId);
				}
				

			}
		}
	}

	function goBack() {
		if(window.history.length > 1){
			window.history.back();
		}else{
			window.close();
		}
	}

 
//**************ENCRIPTION OF FORM DATA START****************

 var tableOrTbodyIDS=[]; /*if table ID is in parameter start rows from 1 if  tbody Id is in parameter start row from 0 */
 var tableOrTbodyKeys=[];
		  function extractTableData(tableORtbodyId) {
		    var dataArray = [];
		    console.log("Processing row...");
		    var tableOrTbody = document.getElementById(tableORtbodyId);
		
		    var rowStartIndex = (tableOrTbody.tagName.toLowerCase() === 'table') ? 1 : 0;
		
		    if (tableOrTbody) {
		        var rows = tableOrTbody.getElementsByTagName('tr');
		        for (var i = rowStartIndex; i < rows.length; i++) {
		            var rowObject = {};
		            var inputsAndSelects = rows[i].querySelectorAll('input, select, textarea, datalist, output, progress, keygen, meter');
		            for (var j = 0; j < inputsAndSelects.length; j++) {
		                var fieldName = inputsAndSelects[j].getAttribute('name');
		                
		                if (!fieldName || fieldName.trim() === '') {
		                    continue; 
		                }
		                
		                var fieldNameParts = fieldName.indexOf('.') !== -1 ? fieldName.split('.').pop() : fieldName;
		                var fieldValue = inputsAndSelects[j].value;
		
		                if (inputsAndSelects[j].tagName === 'SELECT' && inputsAndSelects[j].multiple) {
		                    fieldValue = [];
		                    var options = inputsAndSelects[j].selectedOptions;
		                    for (var k = 0; k < options.length; k++) {
		                        fieldValue.push(options[k].value);
		                    }
		                } else {
		                    fieldValue = inputsAndSelects[j].value;
		                }
		
		                rowObject[fieldNameParts] = fieldValue;
		            }
		
		            if (Object.keys(rowObject).length > 0) {
		                dataArray.push(rowObject);
		            }
		        }
		    }
		    
		    return dataArray;
		}


	function encryptFormData(formId, tableOrTbodyIDS, tableOrTbodyKeys) {
	    var formData = new FormData(document.getElementById(formId));
	    var formDataJson = {};
         debugger
	    /*formData.forEach(function(value, key){
	        formDataJson[key] = value;
	    });*/
          formData.forEach(function(value, key){
			    if (formDataJson.hasOwnProperty(key)) {

			        if (!Array.isArray(formDataJson[key])) {// same key found
			            formDataJson[key] = [formDataJson[key]];// first cnvrt  the key to array
			        }
			        formDataJson[key].push(value); // then push the value to array
			    } else {
			        formDataJson[key] = value;
			    }
			});



	    for (var i = 0; i < tableOrTbodyIDS.length; i++) {
	        var dataList = extractTableData(tableOrTbodyIDS[i]);
	        formDataJson[tableOrTbodyKeys[i]] = dataList;
	    }

	    var encryptedData = encryptJSONobj(formDataJson);
	    
	    $("#encodedData"+formId).val(encryptedData);

	    return true;
	}
	
	  function removeFieldsFromFormBYClass(formId, className) {
                var form = document.getElementById(formId);
                if (form) {
                    var fields = form.getElementsByClassName(className);
                    for (var i = fields.length - 1; i >= 0; i--) {
                        form.removeChild(fields[i]);
                    }
                }
            }
	
		function encryptJSONobj(formDataJson) {
			
	    var formDataString = JSON.stringify(formDataJson);
        var encryptedData = enc_password(formDataString);
        
	    return encryptedData;
	}
	//**************ENCRIPTION OF FORM DATA END****************

	// 
	function getWorkflowHistoryOfEntity(modelClassWithId){
		// split the string by ## to get model class and id
		var modelClassWithIdArray = modelClassWithId.split("##");
		var modelClass = modelClassWithIdArray[0];
		var id = modelClassWithIdArray[1];

		// ajax call to get the history table data
		$.ajax({
			url: "${pageContext.request.contextPath}/stageConfig/get-workFlow-history",
			type: "GET",
			data: {
				"entityClassName": modelClass,
				"entityId": parseInt(id)
			},
			success: function(response){
				console.log(response);
				var historyTableBody = $("#historyTableBodyId");
				historyTableBody.empty();
				if(response != null && response != undefined && response != "" && response.length > 0){
					let html = "";
					$.each(response, function(index, value){
						html += "<tr>";
						html += "<td>"+(index+1)+"</td>";
						html += "<td>"+value.stageName+"</td>";
						html += "<td>"+value.actionTakenByName+"</td>";
						html += "<td>"+value.actionTakenOnRoleName+"</td>";
						html += "<td>"+value.remark+"</td>";
						html += "<td>"+value.actionTakenDate+"</td>";
						html += "</tr>";
					});
					historyTableBody.append(html);
				}
				// show the modal
				$("#historyTableRemarksModal").modal("show");
			},
			error: function(response){
				console.log(response);
			}
		});
	}


	async function asyncAjaxCall(url, type, data){
		return new Promise((resolve, reject) => {
			$.ajax({
			url: url,
			type: type,
			async: true,
			data: data,
			success: function(response){
				resolve(response);
			},
			error: function(response){
				reject(response);
			}
			});
		});
    }

	async function formSubmitForStageChange(formId, stageId){
		debugger;
		if(formId == null || formId == undefined || formId == ""){
			bootbox.alert("Please provide form id where you want to submit the data");
			return;
		}
		if(stageId != null && stageId != undefined && stageId != "" && parseInt(stageId) > 0){
			let stageIdHtml = addHiddenField("currentWorkflowStageParamName", stageId);
			$("#"+formId).append(stageIdHtml);
		}

		// api call to get the next stage details
		let url = "${pageContext.request.contextPath}/stageConfig/getStageRuleDetailsByRuleId";
		let type = "GET";
		let data = {
			"ruleId": stageId
		};
		let response = await asyncAjaxCall(url, type, data);
		if(response != null && response != undefined && response != "" && response.outcome){
			let data = response.data;
			// isUserSpecific, isEntitySpecific, isRemarkRequired, isBulkAction
			let isUserSpecific = data.isUserSpecific;
			let isEntitySpecific = data.isEntitySpecific;
			let isRemarkRequired = data.isRemarkRequired;
			let isBulkAction = data.isBulkAction;

			if(isUserSpecific){
				userSpecificWorkFlow(formId, stageId, "", false);
			}else if(isEntitySpecific){
				let entities = data.entities;
				entitySpecificWorkFlow(entities);
			}
		
		}
		
	}

	function entitySpecificWorkFlow(entities){
		let html = "";
		for(let i=0; i<entities.length; i++){
			let entity = entities[i];
			html += "<div class='col-md-3'>";
			html += "<input type='radio' name='entityType' value='"+entity.id+"' onclick='getDynamicEntityList("+entity.id+")'> "+entity.wfmShareEntityName;
			html += "</div>";
		}
		$("#entityTypeListDivId").html(html);
		$("#entityListSelectId").html("<option value=''>Select Entity</option>");

		// show the entity specific rule modal
		$("#entitySpecificRuleModal").modal("show");
	}

	async function getDynamicEntityList(entityId){
		
		let url = "${pageContext.request.contextPath}/stageConfig/getDynamicEntityListByEntityId";
		let type = "GET";
		let data = {
			"entityId": entityId
		};
		let response = await asyncAjaxCall(url, type, data);
		if(response != null && response != undefined && response != "" && response.outcome){
			let dataList = response.data;
			let html = "<option value=''>Select Entity</option>";
			for(let i=0; i<dataList.length; i++){
				let data = dataList[i];
				let id = data.id + "##" + data.type;
				html += "<option value='"+id+"'>"+data.name+"</option>";
			}
			$("#entityListSelectId").html(html);
		}
	}

	async function getDynamicEntityObjectList(entityId){
		let htmlForRole = "";
		let entityArray = entityId.split("##");
		let id = entityArray[0];
		let code = entityArray[1];
		let url = "${pageContext.request.contextPath}/stageConfig/getDynamicEntityObjectListByEntityId";
		let type = "GET";
		let data = {
			"entityId": id,
			"entityType": code
		};
		let response = await asyncAjaxCall(url, type, data);
		if(response != null && response != undefined && response != "" && response.outcome){
			let data = response.data;
			for(let i=0; i<data.length; i++){
				let role = data[i];
				htmlForRole += "<tr id='roleRow"+role.entityId+"'>";
				htmlForRole += "<td>"+(i+1)+"</td>";
				htmlForRole += "<td>"+role.entityName+"</td>";
				htmlForRole += "<td>"+role.entityType+"</td>";
				htmlForRole += "<td><input type='checkbox' name='entitySpecificRule' value='"+role.entityId+"' id='role"+role.entityId+"' onclick='enableDisableUserSpecificRule("+role.entityId+")'></td>";
				htmlForRole += "<td> <input type='checkbox' name='entitySpecificRuleForUser' onclick='getDynamicEntityUserList("+id+", \""+code+"\", "+role.entityId+" , this)' value='"+role.entityId+"' id='user"+role.entityId+"' disabled></td>";
				htmlForRole += "</tr>";
			}
		}
		$("#entitySpecificRuleModalBodyTbodyForRole").html(htmlForRole);
	}

	function enableDisableUserSpecificRule(roleId){
		let role = $("#role"+roleId);
		if(role != null && role != undefined && role.length > 0){
			let checked = role[0].checked;
			let user = $("#user"+roleId);
			if(user != null && user != undefined && user.length > 0){
				if(checked){
					user[0].disabled = false;
				}else{
					user[0].disabled = true;
					// also uncheck the user specific rule and remove the row
					user[0].checked = false;
					$("#entitySpecificRuleModalBodyTbodyForUser tr").each(function(){
						let id = $(this).attr("id");
						if(id != null && id != undefined && id != "" && id.includes("userRow"+roleId)){
							$(this).remove();
						}
					});



					

				}
			}
		}
	}


	let slNo = 1;
	async function getDynamicEntityUserList(entityId, entityType, roleId, that){
		
		if(that != null && that != undefined && that.checked){
			let url = "${pageContext.request.contextPath}/stageConfig/getDynamicEntityUserListByEntityId";
			let type = "GET";
			let data = {
				"entityId": entityId,
				"entityType": entityType,
				"roleId": roleId
			};
			let htmlForUser = "";
			let response = await asyncAjaxCall(url, type, data);
			if(response != null && response != undefined && response != "" && response.outcome){
				let data = response.data;
				for(let i=0; i<data.length; i++){
					let user = data[i];
					// id = userRow+roleId##entityId
					htmlForUser += "<tr id='userRow"+roleId+"##"+user.entityId+"'>";
					htmlForUser += "<td>"+slNo+"</td>";
					htmlForUser += "<td>"+user.entityName+"</td>";
					htmlForUser += "<td>"+user.entityType+"</td>";
					htmlForUser += "<td><input type='checkbox' name='entitySpecificRule' value='"+user.entityId+"'></td>";
					htmlForUser += "</tr>";
					slNo++;
				}
				$("#entitySpecificRuleModalBodyTbodyForUser").append(htmlForUser);
			}
		}else{
			// loop through the user list and remove the whole row containing the userRow+roleId##entityId
			$("#entitySpecificRuleModalBodyTbodyForUser tr").each(function(){
				let id = $(this).attr("id");
				if(id != null && id != undefined && id != "" && id.includes("userRow"+roleId)){
					$(this).remove();
					slNo--;
				}
			});
		}
	}


	function asyncAjaxForData(url, data, method){
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                type: method,
                data: data,
                success: function(data){
                    resolve(data);
                },
                error: function(err){
                    reject(err);
                }
            });
        });
    }

	function getFieldValue(field){
	let value = "";
	if(field.tagName == "INPUT"){
		value = field.value;
	}else if(field.tagName == "SELECT"){
		value = field.value;
	}else if(field.tagName == "TEXTAREA"){
		value = field.value;
	}
	value = value.trim();
	return value;
}

	function validateRequiredFields(className, formId){
		let isValid = true;
		// get all the required fields by class name from the formId
		let requiredFields = $("#"+formId+" ."+className);
		requiredFields.each(function(){
			let value = getFieldValue(this);
			if(value == ""){
				isValid = false;
				this.classList.add("is-invalid");
				displayErrorMsgByField(this, "This field is required");
			}else{
				this.classList.remove("is-invalid");
				// remove the error message
				clearErrorByField(this);
			}
		});

		return isValid;
	}

	function validateAndFormSubmit(formId, className){
		let isValid = validateRequiredFields(className, formId);
		if(isValid){
			bootbox.confirm("Are you sure you want to submit the form?", function(result){
				if(result){
					encryptFormData(formId, tableOrTbodyIDS, tableOrTbodyKeys);
					$("#"+formId).submit();
				}
			});
		}
	}


	function displayErrorMsgByField(field, message) {
	clearErrorByField(field);
	if (field) {
		var errorSpan = document.createElement('span');
		errorSpan.className = 'error-message';
		errorSpan.textContent = message;
		field.parentNode.insertBefore(errorSpan, field.nextSibling);
	} else {
		console.error('Element with id ' + id + ' not found.');
	}
}

function clearErrorByField(field) {
	var existingError = field.nextElementSibling;
	if (existingError) {
		existingError.parentNode.removeChild(existingError);
	}
}

function validEmail(email) {
	var re =  /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	return re.test(email);
}
		



</script>