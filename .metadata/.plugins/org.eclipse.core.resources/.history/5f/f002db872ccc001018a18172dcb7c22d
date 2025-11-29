<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
	.patch {
	background: #f1f1f1;
    border-bottom: 1px solid #ccc;
    /* padding-bottom: 5px; */
    display: flex;
    align-items: center;
    flex-wrap: wrap;
	
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    padding: 20px;
	background-color: white;
  	border-radius: 10px;
	border-radius: 10px;
	margin-bottom: 20px;
	}

	.disabled-field {
		pointer-events: none;
		opacity: 0.7; /* You can adjust the opacity to your preference */
	}

	.error-message {
		color: red;
	}

	.uuidIcon{
		position: absolute;
    	right: 0px;
    	top: 50%;
    	transform: translate(-15px, -50%);
    	background: #0346e8;
    	color: #fff;
    	padding: 4px;
	}

	.home-section {
		z-index: 0 !important;
	}

	/* Make all level bold tag*/
	label{
		margin-bottom: 5px;
	}
	.remove-btn-custom{
		position: absolute;
	    right: 0;
	    top: 50%;
	    transform: translate(-3px, -50%);
	    height: 26px;
	    width: 26px;
	    background: #dc3545;
	    color: #fff;
	    font-size: 23px;
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    font-weight: 700;
	    padding: 3px;
	}
	.add-remove-sec{
		position:relative;
	}
	.checkbox-flex{
	    display: flex;
	    margin-top: 23px;
	    align-items: center;
	    justify-content: space-around;
	    background: #ECECEC;
	    padding: 5px;
	    border-radius: 4px;
	    border: 1px solid #badfff;
	}
	.main {
	    overflow: unset !important;
	}

</style>
<div class="container-fluid mainfluid mt-3">
<div class="breadcrumb_conatiner">
	<div class="col-md-6">
		<h4 class="change-color">Stage Config</h4>
	</div>
	<div class="col-md-6">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${contextPath}/home"><i class="fa fa-home"></i></a></li>
	
					<li class="breadcrumb-item active" aria-current="page">Stage Config</li>
				</ol>
			</nav>
	</div>
</div>
    <div class="row">
        <div class="col-md-12">
            <div class="card">
				<div class="card-header d-flex justify-content-between">
					<h5 class="card-title">Stage Configuration</h5>
					<!-- Note in right side coroner -->
					<div class="text-end">
					<p class="text-primary" style="margin-bottom:0;">Get Some Key Notes for Stage Configuration <span class="fa fa-info-circle" style="color: blue;" data-bs-toggle="modal" data-bs-target="#staticBackdrop"></span></p>
					</div>
				</div>
				
                <div class="card-body">
					<div class="row " style="display: flex; justify-content: flex-center; flex-wrap: wrap;">
						<div class="col-md-12">
							<div class="bagGroundColor">
								<div class="row">
									<sec:authentication var="principal" property="principal" />
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label">Select Parent Activity</label>
											<select class="col-md-3 form-control selectpicker" id="parentStageName" onchange="getChildStageData(this)" name="parentModalName" data-live-search="true">
												<option value="" selected disabled>Select</option>
												<c:forEach items="${parentWorkFlowModulesList}" var="pStage">
													<option value="${pStage.id}">${pStage.parentModuleName}</option>
												</c:forEach>
											</select>
										</div>
									</div>


									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label">Select Activity</label>
											<select class="col-md-3 form-control selectpicker" id="stageName" onchange="getStageConfigData(this)" name="modalName" data-live-search="true">
												<option value="" selected disabled>Select</option>
												
											</select>
										</div>
									</div>

									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label">Master Stage</label>
											<select class="col-md-3 form-control selectpicker" id="masterStageNameId" name="modalName" multiple data-live-search="true">
												<option value="" selected disabled >Select Master Stage</option>
											</select>
										</div>
									</div>
									<div class="col-md-3"></div>
									<div class="col-md-3">
										<label class="control-label">Effective Start Date</label>
										<div class="col-md-12 ">
		                                    <input type="date" autocomplete="off" class="form-control " id="startDateId" name="startDate" placeholder="dd-mm-yyyy" value="" >
		                                 </div>
									</div>
			
									<div class="col-md-3 checkbox-flex" style="height:31px;">
										
										<span class="">Present Date</span>
										<input type="Checkbox" id="presentDateId" onchange="presentDateChange(this)">
									</div>
									<div class="col-md-3">
										<label class="control-label">Effective End Date</label>
										<!-- <input type="date" class="form-control" id="endDateId"> -->
										<div class="col-md-12 ">
		                                    <input type="date" autocomplete="off" class="form-control " id="endDateId" name="endDate" placeholder="dd-mm-yyyy" value="" >
		                                    <!-- <input type="text" autocomplete="off" class="form-control datepicker form-control-sm" name="endDateId" id="endDateId"placeholder="dd-mm-yyyy" value="" > -->
		                                 </div>
									</div>
									
								</div>
							</div>
						</div>
					</div>

					<!-- Start date and End Date picker -->
					<div class="row mt-3">

						<!-- Does step by step approval need -->
						<div class="col-md-3 d-flex justify-content-left align-items-center" style="margin-top:12px;">
							<input type="checkbox" id="stepByStepApprovalId" checked onclick="stepToStepApproval(this)">
							<span class="check-box-label">Step By Step Approval</span>
						</div>

						<!-- Should Primery Role check -->
						<div class="col-md-3 d-flex justify-content-left align-items-center" style="margin-top:12px;">
							<input type="checkbox" id="shouldPrimeryRoleCheckId" checked>
							<span class="check-box-label">Should Primary Role Check</span>
						</div>
							
						<!-- Does have Flow Code -->
						<div class="col-md-3" style="margin-top:17px;display: none;" id="flowCodeCheckBoxDivId">
							<input type="checkbox" id="doesHaveFlowCodeId">
							<span class="check-box-label">Does have Flow Code</span>
						</div>
						<div class="col-md-3" id="flowCodeDivId" style="display: none;">
							<label class="control-label">Flow Code</label>
							<select class="form-control selectpicker" id="flowCodeId" multiple>
								<option value="" disabled>Select Flow Code</option>
							</select>
						</div>

						<div class="col-md-3" style="margin-top:26px;display: none;" id="flowCodeBtnDivId">
							<button type="button" class="btn btn-primary btn-sm" onclick="getFlowCodeValue()" title="Get Flow Code Value"><i class="far fa-paper-plane"></i></button>
						</div>

						<div class="col-md-3" id="flowCodeValueDivId" style="display: none;">
							<div class="col-md-12 " id="flowCodeDynamicDivId">
								
							</div>
						</div>

						

						
						
					</div>

					<div class="row" id="roleLevelDivId" style="display: none;">
						<div class="col-md-12 mt-3 mb-3">
							<label class="control-label" style="color: red;">Please Configure Role Level Hierachy in butom to top order</label>
						</div>
						
						<div class="col-md-2">
							<select class="form-control" id="roleLevelId0" name="roleLevelName0">
								<option value="">Select Role Level</option>
								<c:forEach items="${allEntityRoles}" var="loop">
									<option value="${loop.roleId}">${loop.displayName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2" style="display: none;" id="addRoleLevelDivId">
							<button type="button" class="btn btn-primary" onclick="addRoleLevel()">Add Role Level</button>
						</div>
					</div>

					<!-- View Roles -->
					<div class="row mt-3">
						<div class="col-md-12">
							<div class="bagGroundColor">
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label">View Roles</label>
											<select class="form-control selectpicker " id="viewRolesId" name="modalName" multiple data-live-search="true">
												<option value="" selected disabled>Select Entity</option>
											</select>
										</div>
									</div>

									<div class="col-md-3" id="dyanmicEntityDivId0">
										<div class="form-group">
											<label class="control-label">Share Entity Type</label>
											<select class="form-control form-select " id="dynamicEntityId0" onchange="getEntityListOnShareEntity(this, true)">
												<option value="" selected disabled>Select Entity</option>
												<c:forEach items="${shareEntityList}" var="loop">
													<option value="${loop.id}">${loop.displayName}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label">Get Entity List</label>
											<div class="col-md-12">
												<button style=" width: 100%; height: 30px; padding: 0px; border-bottom: none !important; type="button" class="btn btn-primary" onclick="fetchEntityListByShareEntity(this, true)">Fetch</button>
											</div>
										</div>
									</div>

									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label">Select Entity</label>
											<c:set var="currentUserEntityAndType" value="${currentUserDto.orgId}##${currentUserDto.orgLevel}" />
											<select class="form-control selectpicker " id="clgOrUnOrHDId" multiple data-live-search="true" onchange="getEntityRoleList(this)">
												<option value="" selected disabled>Select Entity</option>
												<c:forEach items="${dynamicEntityDtoList}" var="entity">
													<c:set var="idAndType" value="${entity.entityId}##${entity.entityType}" />
													<option value="${idAndType}" <c:if test="${currentUserEntityAndType == idAndType}">selected</c:if>>${entity.entityName}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<!-- two radio button enable and disable -->
									<div class="col-md-3" id="enableDisableTableDivIdE">
										<div class="form-group checkbox-flex" style="">
											<label class="control-label" style="margin-bottom:0px;">Enable Row</label>
											<input type="checkbox" name="allAnableDisableTable" onchange="allAnableDisableTableChange(this)">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


	
					<div class="row mt-3" id="dataSection">
						<div class="col-lg-12">
							<div class="table-responsive table-container-custom">
								<table class="table table-bordered table-hover exportbtn" id="sct">
									<thead >
										<tr>
											<th>From Role</th>
											<th>Activity</th>
											<th>To Role</th>
											<th style="display: none;">Unique Id</th>
											<th>Write Roles</th>
											<th>Is User Specific</th>
											<th>is Entity Specific</th>
											<th>Does Remark Required</th>
											<th>Bulk Approval</th>
											<th>Bulk Codes</th>
											<th style="width: 100px;">Other Entity</th>
											<th style="width: 100px;">Level</th>
											<th>First Stage</th>
											<th>Next Stage</th>
											<th>Display Remark</th>
											<th style="width: 180px">Stage</th>
											<th class="width100">Action</th>
										</tr>
									</thead>
									<tbody id="sctBody"></tbody>
								</table>
							</div>
						</div>

						<div class="row mt-3" id="forwardSectionDivId" style="display: none;">
								<div class="col-md-6">
									<h2 class="text-center">Forward To</h2>
								</div>
								<div class="col-md-6 text-end">
									<button type="button" class="btn btn-danger" onclick="removeForwardSection()">Remove</button>
								</div>
							<div class="col-md-12">
								<div class="row align-items-center">
								<!-- Choose Entity -->
								<div class="col-md-4" id="dynamicEntityDivId">
									<h3>Dynamic Entity List</h3>
								</div>
								<!-- Dynamic Entity List -->
								<div class="col-md-4 text-center">
									<label class="text-start">Choose Entity</label>
									<select class="form-control" id="dynamicEntitySelctId" name="dynamicEntityId">
										<option value="">Select Entity</option>
									</select>
								</div>
								<!-- Dynamic Entity Role List-->
								<div class="col-md-4 text-end">
									<label class="text-start">Choose Role</label>
									<select class="form-control form-select" id="dynamicEntityRoleSelctId" name="dynamicEntityRoleId">
										<option value="">Select Role</option>
									</select>
								</div>
							</div>
							</div>
						</div>
					</div>

					<!-- Submit button in center -->
					<div class="row" style="display: flex; justify-content: center; flex-wrap: wrap; margin-top: 20px;">
						<div class="col-md-12 text-center">
							<button type="button" id="satgeConfigBtnId" class="btn btn-success" onclick="saveStageConfigData()">Save</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Custom beautiful Page Loader using bootstarp -->
<div class="loader" id="loaderId" style="display: none;">
	<div class="loader-inner ball-pulse">
		<div></div>
		<div></div>
		<div></div>
	</div>
</div>

	<!-- Modal -->




	<form action="${contextPath}/stageConfig/saveAndUpdateNewStageConfigObjectModify" method="post" id="saveAndUpdateNewStageConfigObjectModify" modelAttribute="saveAndUpdateNewStageConfigObjectStr">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<input type="hidden" name="saveAndUpdateNewStageConfigObjectStr" id="saveAndUpdateNewStageConfigObjectStrId">
	</form>

</section>
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
	  <div class="modal-content">
		<div class="modal-header">
		  <h5 class="modal-title" id="staticBackdropLabel">Key Notes for Stage Configuration</h5>
		  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		</div>
		<div class="modal-body">
			<p>1. Select Activity: Select the activity for which you want to configure the stage.</p>
			<p>2. Start Date: Select the start date of the stage.</p>
			<p>3. Present Date: If you want to set the present date as the start date, then check this checkbox.</p>
			<p>4. End Date: Select the end date of the stage.</p>
		</div>
		<div class="modal-footer">
		  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		</div>
	  </div>
	</div>
  </div>
<script>



	function allAnableDisableTableChange(that){
		if ($(that).prop("checked")) {
			$("#sctBody tr").each(function(){
				$(this).find("td").each(function(){
					// select, input, button
					$(this).find("select").removeClass("disabled-field");
					$(this).find("input").removeClass("disabled-field");
					$(this).find("button").removeClass("disabled-field");

				});
			});
		}else{
			$("#sctBody tr").each(function(){
				$(this).find("td").each(function(){
					// select, input, button
					$(this).find("select").addClass("disabled-field", "");
					$(this).find("input").addClass("disabled-field");
					$(this).find("button").addClass("disabled-field");
				});
			});

			// last row enable
			$("#sctBody tr:last").find("td").each(function(){
				// select, input, button
				$(this).find("select").removeClass("disabled-field");
				$(this).find("input").removeClass("disabled-field");
				$(this).find("button").removeClass("disabled-field");
			});


		}
		refreshAllSelectOption();
	}

	// destroy select2 plugin
	function refreshSelect2Plugin(){
		$('.selectpicker').select2('destroy').select2();
	}

	function refreshSelectpicker(){
		$('.selectpicker').selectpicker('refresh');
	}



	function refreshAllSelectOption(){
		try {
			refreshSelect2Plugin();
		} catch (error) {
			console.log("Error in refreshSelect2Plugin");
		}
		// refresh selectpicker
		try {
			refreshSelectpicker();
		} catch (error) {
			console.log("Error in refreshSelectpicker");
		}
	}

	function refreshAllSelectOptionById(){

	}


	function stepToStepApproval(that){
		if ($(that).prop("checked")) {
			$("#addRoleLevelDivId").hide();
			$("#roleLevelDivId").hide();
		} else {
			$("#addRoleLevelDivId").show();
			$("#roleLevelDivId").show();
		}

	}
	let roleLevelDiv = 0;
	function addRoleLevel(){
		// check roleLevelDivId value is selected or not
		let roleLevelId = $("#roleLevelId" + roleLevelDiv).val();
		if (roleLevelId == "") {
			alert("Please select role level");
			return;
		}else{
			roleLevelDiv++;
			let roleLevelDivId = $("#roleLevelDivId");
			let html = '<div class="col-md-2">';
			html += '<div class="add-remove-sec"';
			html += '<label class="control-label" style="color: red;"></label>';
			html += '<button type="button" class="btn btn-danger remove-btn-custom" onclick="removeRoleLevel(this)" id="removeRoleLevelId'+roleLevelDiv+'">-</button>';
			html += '<select class="form-control" id="roleLevelId'+roleLevelDiv+'" name="roleLevelName'+roleLevelDiv+'">';
			html += '<option value="">Select Role Level</option>';
			<c:forEach items="${allEntityRoles}" var="loop">
				html += '<option value="${loop.roleId}">${loop.displayName}</option>';
			</c:forEach>
			html += '</select>';
			html += '</div>';
			html += '</div>';
			$(roleLevelDivId).append(html);
			// make previous remove button disable
			$("#removeRoleLevelId" + (roleLevelDiv - 1)).prop("disabled", true);
		}
	}

	function removeRoleLevel(that){
		$(that).parent().remove();
		// make previous remove button enable
		$("#removeRoleLevelId" + (roleLevelDiv - 1)).prop("disabled", false);
		roleLevelDiv--;
	}


	function presentDateChange(that){
		if ($(that).prop("checked")) {
			$("#endDateId").val("");
			$("#endDateId").addClass("disabled-field");
		} else {
			$("#endDateId").removeClass("disabled-field");
		}
	}

	async function getEntityRoleList(that) {
		let entityAndType = $(that).val();
		if (entityAndType == "") {
			return;
		}
		getStageConfigData($("#stageName"));
	}

	async function getStageConfigData(that){

		// get Select Entity values
		let selectEntityIds = $("#clgOrUnOrHDId").val();
		if(selectEntityIds == null || selectEntityIds.length == 0 || selectEntityIds == ""){
			return;
		}
		
		let getSpecificRoleList = await asyncAjaxCall("${contextPath}/system/getRoleListByEntityId", "GET", {"entityId": btoa(selectEntityIds)});

		let specificRoleList = [];
		if(getSpecificRoleList.outcome){
			specificRoleList = getSpecificRoleList.data;
			// remove duplicate role
			specificRoleList = specificRoleList.filter((v,i,a)=>a.findIndex(t=>(t.roleId === v.roleId))===i);
		}
		

		// clear table tbody
		$("#sctBody").empty();
		//ajax call
		if ($("#stageName").val() == "" || $("#stageName").val() == null || $("#stageName").val() == undefined) {
			displayErrorMsg("stageName", "Please select stage");
			return;
		}
		$.ajax({
			url : "${contextPath}/stageConfig/getStageConfigData",
			type : "GET",
			data : {
				"entityClassName" : $("#stageName").val()
			},
			success : function(data) {
				debugger;
				fromRoleList = data.fromRoleList;
				toRoleList = data.toRoleList;

				// fromRoleList remove other which is not in specificRoleList
				let fromRoleListTemp = [];
				for(let i = 0; i < fromRoleList.length; i++){
					for(let j = 0; j < specificRoleList.length; j++){
						if(fromRoleList[i].roleId == specificRoleList[j].roleId){
							fromRoleListTemp.push(fromRoleList[i]);
							break;
						}
					}
				}
				fromRoleList = fromRoleListTemp;

				// toRoleList remove other which is not in specificRoleList
				let toRoleListTemp = [];
				for(let i = 0; i < toRoleList.length; i++){
					for(let j = 0; j < specificRoleList.length; j++){
						if(toRoleList[i].roleId == specificRoleList[j].roleId){
							toRoleListTemp.push(toRoleList[i]);
							break;
						}
					}
				}
				toRoleList = toRoleListTemp;




				toRoleList.push({roleId: -1, roleName: "Other Level Entity", roleCode: "OTHER_LEVEL_ENTITY"});
				toRoleList.push({roleId: -2, roleName: "Other Runtime Entity", roleCode: "OTHER_RUNTIME_ENTITY"});
				toRoleList.push({roleId: -999999999, roleName: "Previous Entity", roleCode: "PREVIOUS_ENTITY"});
				fromRoleList.push({roleId: -2, roleName: "Other Runtime Entity", roleCode: "OTHER_RUNTIME_ENTITY"});
				stageDtoList = data.stageDtoList;
				actionTypeDtoList = data.actionTypeDtoList;
				let masterStageList = data.stageForwardedRuleMasterDtoList;
				if(masterStageList == undefined){
					masterStageList = [];
				}

				let viewStatusDtoList = data.viewStatusDtoList;
				if(viewStatusDtoList == undefined){
					viewStatusDtoList = [];
				}
				tabCodes = viewStatusDtoList;



				// does have flow code
				let doesHaveFlowCode = data.doesHaveFlowCode;
				if (doesHaveFlowCode) {
					// flowCodeCheckBoxDivId make checked
					

					// flowVersionCodeList
					let flowVersionCodeList = data.flowVersionCodeList;
					$("#flowCodeId").empty();
					$(flowCodeId).append('<option value="" disabled>Select Flow Code</option>');
					for (let i = 0; i < flowVersionCodeList.length; i++) {
						$(flowCodeId).append('<option value="' + flowVersionCodeList[i].flowVersionCodeId + '">' + flowVersionCodeList[i].flowVersionName + '</option>');
					}
					// refresh All select option
					refreshAllSelectOption();
					$("#flowCodeCheckBoxDivId").show();
					// make checked
					$("#doesHaveFlowCodeId").prop("checked", true);
					// make disable check box
					$("#doesHaveFlowCodeId").addClass("disabled-field");

					$("#flowCodeDivId").show();
					$("#flowCodeBtnDivId").show();
					$("#flowCodeValueDivId").show();
				} else {

					// make disable check box
					$("#doesHaveFlowCodeId").addClass("disabled-field");
					// make unchecked
					$("#doesHaveFlowCodeId").prop("checked", false);

					// empty flow code select
					$("#flowCodeId").empty();
					$(flowCodeId).append('<option value="" disabled>Select Flow Code</option>');
					
					// refresh All select option
					refreshAllSelectOption();
					$("#flowCodeCheckBoxDivId").hide();
					$("#flowCodeDivId").hide();
					$("#flowCodeBtnDivId").hide();
					$("#flowCodeValueDivId").hide();
				}

				
				
				$("#masterStageNameId").empty();
				$(masterStageNameId).append('<option value="">Select Master Stage</option>');
				for (let i = 0; i < masterStageList.length; i++) {
					$(masterStageNameId).append('<option value="' + masterStageList[i].srmId + '">' + masterStageList[i].ruleName + '</option>');
				}
				// refresh All select option
				refreshAllSelectOption();

				$("#sctBody").empty();
				createTable();
				viewRoles(fromRoleList);
			},
			error : function(e) {
				alert("Error! " + e);
			}
		});
	}

	function viewRoles(fromRoleList){
		let viewRolesId = $("#viewRolesId");
		$(viewRolesId).empty();
		$(viewRolesId).append('<option value="">Select Role</option>');
		for (let i = 0; i < fromRoleList.length; i++) {
			$(viewRolesId).append('<option value="' + fromRoleList[i].roleId + '">' + fromRoleList[i].roleName + '</option>');
		}
		// refresh All select option
		refreshAllSelectOption();
	}

	function generateUUID() { // Public Domain/MIT
		var d = new Date().getTime();//Timestamp
		var d2 = ((typeof performance !== 'undefined') && performance.now && (performance.now()*1000)) || 0;//Time in microseconds since page-load or 0 if unsupported
		return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
			var r = Math.random() * 16;//random number between 0 and 16
			if(d > 0){//Use timestamp until depleted
				r = (d + r)%16 | 0;
				d = Math.floor(d/16);
			} else {//Use microseconds since page-load if supported
				r = (d2 + r)%16 | 0;
				d2 = Math.floor(d2/16);
			}
			return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
		});
	}


	let rowId = 0;
	let fromRoleList = [];
	let toRoleList = [];
	let stageDtoList = [];
	let actionTypeDtoList = [];
	let level = 1;
	let otherEntityValue = 0;
	var tabCodes = [];
	function createTable(){
		// get table body
		let tableBody = "";
		tableBody += "<tr id='rowId"+rowId+"'>";
		// from role select
		tableBody += "<td>";
		tableBody += "<select class='form-control' id='fromRoleId"+rowId+"' onchange='fromRoleChange(this)'>";
		tableBody += "<option value=''>Select Role</option>";
		for (let i = 0; i < fromRoleList.length; i++) {
			tableBody += "<option value='" + fromRoleList[i].roleId + "'>" + fromRoleList[i].roleName + "</option>";
		}
		tableBody += "</select>";
		tableBody += "</td>";

		// action type select
		tableBody += "<td>";
		tableBody += "<select class='form-control form-select ' id='actionTypeId"+rowId+"' onchange='operationTypeChange(this)'>";
		tableBody += "<option value=''>Select Action</option>";
		for (let i = 0; i < actionTypeDtoList.length; i++) {
			tableBody += "<option value='" + actionTypeDtoList[i].actionCode + "'>" + actionTypeDtoList[i].actionName + "</option>";
		}
		tableBody += "</select>";
		tableBody += "</td>";

		// to role select
		tableBody += "<td>";
		tableBody += "<select class='form-control form-select' id='toRoleId"+rowId+"' onchange='toRoleChange2(this)'>";
		tableBody += "<option value=''>Select Role</option>";
		for (let i = 0; i < toRoleList.length; i++) {
			tableBody += "<option value='" + toRoleList[i].roleId + "'>" + toRoleList[i].roleName + "</option>";
		}
		tableBody += "</select>";
		tableBody += "</td>";

		//UUID
		tableBody += "<td style='position: relative; display:none'>";
		tableBody += "<input type='text' class='form-control' id='uuidId"+rowId+"' readonly value='"+generateUUID()+"'>";
		// copy icon
		tableBody += "<i class='fa fa-copy copy-icon uuidIcon' onclick='copyToClipboard(this)' id='copyIconId"+rowId+"'></i>";
		tableBody += "</td>";


		// can write check box
		tableBody += "<td style='text-align: center;vertical-align: middle;'>";
		tableBody += "<input type='checkbox' class='' id='canWriteId"+rowId+"'>";
		tableBody += "</td>";

		// is user specific check box
		tableBody += "<td style='text-align: center;vertical-align: middle;'>";
		tableBody += "<input type='checkbox' class='' id='isUserSpecificId"+rowId+"' onchange='isUserSpecificChange(this)'>";
		tableBody += "</td>";

		// is entity specific check box
		tableBody += "<td style='text-align: center;vertical-align: middle;'>";
		tableBody += "<input type='checkbox' class='' id='isEntitySpecificId"+rowId+"' onchange='isEntitySpecificChange(this)'>";
		tableBody += "</td>";


		// Remark Required check box
		tableBody += "<td style='text-align: center;vertical-align: middle;'>";
		tableBody += "<input type='checkbox' class='' id='remarkRequiredId"+rowId+"'>";
		tableBody += "</td>";

		//bulk approval
		tableBody += "<td style='text-align: center;vertical-align: middle;'>";
		tableBody += "<input type='checkbox' class='' id='bulkApprovalId"+rowId+"'>";
		tableBody += "</td>";

		//bulk codes
		tableBody += "<td>";
		tableBody += "<select class='form-control selectpicker ' id='bulkCodesId"+rowId+"' multiple>";
		tableBody += "<option value=''>Select Bulk Codes</option>";
		// loop tabCodes
		for (let i = 0; i < tabCodes.length; i++) {
			tableBody += "<option value='" + tabCodes[i].statusCode + "'>" + tabCodes[i].displayName + "</option>";
		}
		tableBody += "</select>";

		// other entity level
		tableBody += "<td style='text-align: center;vertical-align: middle;'>";
		tableBody += "<input type='text' class='form-control' id='otherEntityId"+rowId+"' value='"+otherEntityValue+"'>";

		// level
		tableBody += "<td style='text-align: center;vertical-align: middle;'>";
		tableBody += "<input type='text' class='form-control' id='levelId"+rowId+"' value='"+level+"'>";
		tableBody += "</td>";

		// is init next stage
		tableBody += "<td  style='text-align: center;vertical-align: middle;'>";
		tableBody += "<input type='checkbox' class='' id='isInitStageId"+rowId+"'>";
		tableBody += "</td>";

		// is next stage start from here
		tableBody += "<td  style='text-align: center;vertical-align: middle;'>";
		tableBody += "<input type='checkbox' class='' id='isNextStageStartFromHereId"+rowId+"'>";
		tableBody += "</td>";

		

		// Display Remark
		tableBody += "<td>";
		tableBody += "<input type='text' class='form-control' id='displayRemarkId"+rowId+"'>";
		tableBody += "</td>";


		// action
		tableBody += "<td>";
		tableBody += "<div class=''>";
		tableBody += "<select class='form-control form-select' id='actionId"+rowId+"' onchange='nextRowAdd(this)'>";
		tableBody += "<option value=''>Select Action</option>";
		for (let i = 0; i < stageDtoList.length; i++) {
			tableBody += "<option value='" + stageDtoList[i].stageCode + "'>" + stageDtoList[i].stageNameEn + "</option>";
		}
		tableBody += "</select>";
		tableBody += "</div>";
		tableBody += "</td>";

		tableBody += "<td>";
		tableBody += "<button type='button' style='margin-right: 5px;' class='btn btn-danger ' id='nextRowId"+rowId+"' onclick='removeRow(this)'><i class='fa fa-trash'></i></button>";
		tableBody += "<button type='button' class='btn btn-primary ' id='addNewRowId"+rowId+"' onclick='addNewRow(this)'><i class='fa fa-plus'></i></button>";
		tableBody += "</td>"; 
		

		// Display order 
		tableBody += "<input type='hidden' id='displayOrder"+rowId+"' value='"+rowId+"'>";	

		tableBody += "</tr>";
		rowId++;

		$("#sctBody").append(tableBody);

		// refresh All select option
		refreshAllSelectOption();

	}

	function isUserSpecificChange(that){
		let isUserSpecificId = $(that).attr("id");
		let rowId = isUserSpecificId.substring(16);
		if ($(that).prop("checked")) {
			$("#isEntitySpecificId" + rowId).prop("checked", false);
			$("#isEntitySpecificId" + rowId).addClass("disabled-field");
		}else{
			$("#isEntitySpecificId" + rowId).removeClass("disabled-field");
		}
	}

	function isEntitySpecificChange(that){
		let isEntitySpecificId = $(that).attr("id");
		let rowId = isEntitySpecificId.substring(18);
		if ($(that).prop("checked")) {
			$("#isUserSpecificId" + rowId).prop("checked", false);
			$("#isUserSpecificId" + rowId).addClass("disabled-field");

			// make to role -2 as selected
			$("#toRoleId" + rowId).val("-2");

		}else{
			$("#isUserSpecificId" + rowId).removeClass("disabled-field");
			$("#toRoleId" + rowId).val("");
		}
	}

	function getBulkApprovalCodes(that){
		if ($(that).prop("checked")) {
			// tabCodes
			let bulkApprovalId = $(that).attr("id");
			let rowId = bulkApprovalId.substring(14);
			createBulkApprovalModal();
			
		}
	}

	// create a modal for bulk approval  codes
	function createBulkApprovalModal(){
		// create modal
		let modal = "";
		modal += '<div class="modal fade" id="bulkApprovalModal" tabindex="-1" aria-labelledby="bulkApprovalModalLabel" aria-hidden="true">';
		modal += '<div class="modal-dialog">';
		modal += '<div class="modal-content">';
		modal += '<div class="modal-header">';
		modal += '<h5 class="modal-title" id="bulkApprovalModalLabel">Bulk Approval Codes</h5>';
		modal += '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>';
		modal += '</div>';
		modal += '<div class="modal-body">';
		modal += '<div class="row">';
		modal += '<div class="col-md-12">';
		modal += '<div class="table-responsive">';
		modal += '<table class="table table-bordered table-hover table-striped">';
		modal += '<thead>';
		modal += '<tr>';
		modal += '<th>Tab Code</th>';
		modal += '<th>Tab Name</th>';
		modal += '<th>Display Order</th>';
		modal += '</tr>';
		modal += '</thead>';
		modal += '<tbody id="bulkApprovalModalBody">';

		// tabCodes loops
		for (let i = 0; i < tabCodes.length; i++) {
			modal += '<tr id="modalTabRowId'+i+'">';
			modal += '<td>'+tabCodes[i].statusCode+'</td>';
			modal += '<td>'+tabCodes[i].displayName+'</td>';
			modal += '<td><input type="checkbox" value="'+tabCodes[i].tabCode+'" onchange="bulkApprovalCodeChange(this)"></td>';
			modal += '</tr>';
		}



		modal += '</tbody>';
		modal += '</table>';
		modal += '</div>';
		modal += '</div>';
		modal += '</div>';
		modal += '</div>';
		modal += '<div class="modal-footer">';
		modal += '<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>';
		modal += '</div>';
		modal += '</div>';
		modal += '</div>';
		modal += '</div>';

		$("body").append(modal);
		$("#bulkApprovalModal").modal("show");
	}

	function copyToClipboard(that){
		// get id
		let id = $(that).attr("id");
		let rowId = id.substring(10);
		let copyText = $("#uuidId" + rowId);
		alert($(copyText).val());
		let textArea = document.createElement("textarea");
		textArea.value = $(copyText).val();
		document.body.appendChild(textArea);
		textArea.select();
		document.execCommand("Copy");
		textArea.remove();

	}


	//function removeRow(that){
		// if only one row then return
		//if ($("#sct tbody tr").length == 1) {
		//	return;
		//}
		// get last row id
		//let lastRowId = $("#sct tbody tr:last").attr("id");
		//let lastRowIdNum = lastRowId.substring(5);
		// remove row
		//$("#rowId" + lastRowIdNum).remove();
		//rowId--;
		// enable last row
		//enableFullRow(lastRowIdNum - 1);
	//}

	function removeRow(that) {
        // if only one row then return
        if ($("#sct tbody tr").length == 1) {
            return;
        }
        // remove the clicked row
        $(that).closest("tr").remove();
    }



	function fromRoleChange(that){

	$(this).attr('data-live-search','true');
		let rowId = $(that).parent().parent().attr("id"); // rowId0 -> 0
		rowId = rowId.substring(5); // 0
		// if value "" then return
		if ($(that).val() == "") {
			$("#actionTypeId" + rowId).val("");
			$("#actionTypeId" + rowId).addClass("disabled-field");
			$("#toRoleId" + rowId).val("");
			$("#toRoleId" + rowId).addClass("disabled-field");
			$("#canWriteId" + rowId).prop("checked", false);
			$("#canWriteId" + rowId).addClass("disabled-field");
			$("#isUserSpecificId" + rowId).prop("checked", false);
			$("#isUserSpecificId" + rowId).addClass("disabled-field");
			$("#canBeReopendId" + rowId).prop("checked", false);
			$("#canBeReopendId" + rowId).addClass("disabled-field");
			$("#remarkRequiredId" + rowId).prop("checked", false);
			$("#remarkRequiredId" + rowId).addClass("disabled-field");
			$("#bulkApprovalId" + rowId).prop("checked", false);
			$("#bulkApprovalId" + rowId).addClass("disabled-field");

			$("#actionId" + rowId).val("");
			$("#actionId" + rowId).addClass("disabled-field");
			return;
		}else{
			$("#actionTypeId" + rowId).removeClass("disabled-field");
		}
	}

	async function operationTypeChange(that){
		debugger;
		let actionTypeId = $(that).val();
		let selectstageName = $("#stageName").val();
		let rowId = $(that).parent().parent().attr("id"); // rowId0 -> 0
		rowId = rowId.substring(5); // 0
		let ajaxResponse = await asyncAjaxCall("${contextPath}/stageConfig/getActionTypeById", "GET", {"actionTypeCode" : actionTypeId, "entityClassName" : selectstageName});
		if(ajaxResponse.outcome){
			ajaxResponse = ajaxResponse.data;
		}
		let actionTypeDtoData = ajaxResponse.actionTypeDto;
		let shareEntityData = ajaxResponse.wfmShareEntities;
		let actionCode = actionTypeDtoData.actionCode;
		if(actionCode == "SUBMIT_TO_NEXT_LEVEL" || actionCode == "VERIFY_AT_NEXT_LEVEL"){
			// disable all row select action as CNT_NEXT_LEVEL actionId and to role as other level entity
			$("#toRoleId" + rowId).val("-1");
			$("#actionId" + rowId).val("CNT_NEXT_LEVEL");
			//disableFullRow(rowId);
			$("#nextRowId" + rowId).addClass("disabled-field");

			if(actionCode == "SUBMIT_TO_NEXT_LEVEL"){
				// make init stage as true
				$("#isInitStageId" + rowId).prop("checked", true);
			}else{
				// make init stage as false
				$("#isInitStageId" + rowId).prop("checked", false);
			}
			setForwardSection(shareEntityData);
			return;
		}else if(actionCode == "DRAFT"){
			// make to role as from role and action as DRAFT and write as true
			$("#toRoleId" + rowId).val($("#fromRoleId" + rowId).val());
			$("#actionId" + rowId).val("DRAFT");
			$("#canWriteId" + rowId).prop("checked", true);
			// init also make true
			$("#isInitStageId" + rowId).prop("checked", true);
			return;
		}else if(actionCode == "REJECT" || actionCode == "APPROVE"){ 
			// make to role as from role and action as REJECT and write as true
			$("#toRoleId" + rowId).val($("#fromRoleId" + rowId).val());
			$("#actionId" + rowId).val("FINAL");
			return;
		}else if(actionCode == "SEND_FOR_APPROVAL"){
			// make init stage as true
			$("#isInitStageId" + rowId).prop("checked", true);
			// and stage as NEXT_LEVEL
			$("#actionId" + rowId).val("NEXT");
		}else if(actionCode == "REVERT"){
			// and stage as NEXT_LEVEL
			$("#actionId" + rowId).val("PREVIOUS");
		}


		let actionTypeName = $("#actionTypeId" + rowId).val();
		if (actionTypeName == "") {
			$("#toRoleId" + rowId).val("");
			$("#toRoleId" + rowId).addClass("disabled-field");
			$("#canWriteId" + rowId).prop("checked", false);
			$("#canWriteId" + rowId).addClass("disabled-field");
			$("#isUserSpecificId" + rowId).prop("checked", false);
			$("#isUserSpecificId" + rowId).addClass("disabled-field");
			$("#canBeReopendId" + rowId).prop("checked", false);
			$("#canBeReopendId" + rowId).addClass("disabled-field");
			$("#remarkRequiredId" + rowId).prop("checked", false);
			$("#remarkRequiredId" + rowId).addClass("disabled-field");
			$("#bulkApprovalId" + rowId).prop("checked", false);
			$("#bulkApprovalId" + rowId).addClass("disabled-field");

			$("#actionId" + rowId).val("");
			$("#actionId" + rowId).addClass("disabled-field");
			return;
		}else{
			$("#toRoleId" + rowId).removeClass("disabled-field");
		}
	}

	let isForwardSectionActive = false;
	function setForwardSection(shareEntityData){
		let dynamicEntityDivId = $("#dynamicEntityDivId");
		$("#dynamicEntityDivId").empty();
		// set radio button for each entity
		for (let i = 0; i < shareEntityData.length; i++) {
			debugger;
			let entityId = shareEntityData[i].id;
			let entityName = shareEntityData[i].wfmShareEntityName;
			$(dynamicEntityDivId).append('<input type="radio" name="dynamicEntity" value="'+entityId+'" onclick="getDynamicEntityList(this)">'+entityName+'<br>');
		}
		$("#forwardSectionDivId").show();
		isForwardSectionActive = true;
	}

	function removeForwardSection(){
		// let enable last row
		let lastRowId = $("#sct tbody tr:last").attr("id");
		let lastRowIdNum = lastRowId.substring(5);
		$("#actionId" + lastRowIdNum).val("");
		enableFullRow(lastRowIdNum);
		$("#nextRowId" + lastRowIdNum).removeClass("disabled-field");

		// make forward section empty
		$("#dynamicEntityDivId").empty();
		$("#dynamicEntitySelctId").empty();
		$("#forwardSectionDivId").hide();
		// make operation type select as tregger operation select with value ""
		$("#actionTypeId" + lastRowIdNum).val("");
		$("#actionTypeId" + lastRowIdNum).trigger("change");



		isForwardSectionActive = false;
	}


	async function getDynamicEntityList(that){
		debugger;
		let entityId = $(that).val();
		let ajaxResponse = await asyncAjaxCall("${contextPath}/stageConfig/getDynamicEntityList", "GET", {"entityId" : entityId});
		let roleDtos = [];
		let html = "<option value=''>Select</option>";
		for (let i = 0; i < ajaxResponse.length; i++) {
			let entityId = ajaxResponse[i].entityId;
			let entityName = ajaxResponse[i].entityName;
			html += "<option value='"+entityId+"'>"+entityName+"</option>";
			if (i == 0) {
				roleDtos = ajaxResponse[i].roleDtos;
			}
		}
		// if entity list is 1 then set it as selected
		if (ajaxResponse.length == 1) {
			$("#dynamicEntitySelctId").val(ajaxResponse[0].entityId);
		}
		$("#dynamicEntitySelctId").empty().append(html);
		// set role list
		let roleHtml = "<option value=''>Select</option>";
		for (let i = 0; i < roleDtos.length; i++) {
			let roleId = roleDtos[i].roleId;
			let roleName = roleDtos[i].displayName;
			roleHtml += "<option value='"+roleId+"'>"+roleName+"</option>";
		}
		// if role list is 1 then set it as selected
		if (roleDtos.length == 1) {
			$("#dynamicEntityRoleSelctId").val(roleDtos[0].roleId);
		}
		$("#dynamicEntityRoleSelctId").empty().append(roleHtml);
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

	function toRoleChange2(that){
		let rowId = $(that).parent().parent().attr("id"); // rowId0 -> 0
		rowId = rowId.substring(5); // 0
		let toRoleId = $("#toRoleId" + rowId).val();
		if (toRoleId == "") {
			$("#canWriteId" + rowId).prop("checked", false);
			$("#canWriteId" + rowId).addClass("disabled-field");
			$("#isUserSpecificId" + rowId).prop("checked", false);
			$("#isUserSpecificId" + rowId).addClass("disabled-field");
			
			$("#remarkRequiredId" + rowId).prop("checked", false);
			$("#remarkRequiredId" + rowId).addClass("disabled-field");
			$("#bulkApprovalId" + rowId).prop("checked", false);
			$("#bulkApprovalId" + rowId).addClass("disabled-field");
			$("#actionId" + rowId).val("");
			$("#actionId" + rowId).addClass("disabled-field");
			return;
		}else{
			$("#canWriteId" + rowId).removeClass("disabled-field");
			$("#isUserSpecificId" + rowId).removeClass("disabled-field");
			$("#remarkRequiredId" + rowId).removeClass("disabled-field");
			$("#bulkApprovalId" + rowId).removeClass("disabled-field");
			$("#actionId" + rowId).removeClass("disabled-field");
		    
			//get all view roles options and remove selected to role
			let viewRolesIdHtml = $("#viewRolesId").html();
			let viewRolesId = $("#viewRolesId");
			$(viewRolesId).empty();
			$(viewRolesId).append(viewRolesIdHtml);
			$(viewRolesId).find("option[value='" + toRoleId + "']").remove();
			// refresh All select option
			refreshAllSelectOption();

		}
	}

	function actionTypeChange(that){
		let actionTypeId = $(that).val();
		let rowId = $(that).parent().parent().attr("id"); // rowId0 -> 0
		rowId = rowId.substring(5); // 0
		// if action type name is draft then make to as from role and disable it
		let actionTypeName = $("#actionTypeId" + rowId).find("option:selected").text();
		if (actionTypeName == "Draft") {
			$("#toRoleId" + rowId).val($("#fromRoleId" + rowId).val());
			addDisabled($("#toRoleId" + rowId));

			// write check box true
			$("#canWriteId" + rowId).prop("checked", true);
			addDisabled($("#canWriteId" + rowId));

			// read roles
			$("#readRoleId" + rowId).val($("#fromRoleId" + rowId).val());
			addDisabled($("#readRoleId" + rowId));

		} else if (actionTypeId == ""){
			$("#toRoleId" + rowId).val("");
			addDisabled($("#toRoleId" + rowId));

		} else {
			$("#toRoleId" + rowId).val("");
			removeDisabled($("#toRoleId" + rowId));
			// write check box false
			$("#canWriteId" + rowId).prop("checked", false);
			removeDisabled($("#canWriteId" + rowId));

			// read roles
			$("#readRoleId" + rowId).val("");
			removeDisabled($("#readRoleId" + rowId));
		}
	}


	// addClass("disabled-field");
	function addDisabled(that){
		$(that).addClass("disabled-field");
		// refresh All select option
		refreshAllSelectOption();
	}

	function removeDisabled(that){
		$(that).removeClass("disabled-field");

		// if parent div has disabled-field then remove it
		if ($(that).parent().hasClass("disabled-field")) {
			$(that).parent().removeClass("disabled-field");
		}


		// refresh All select option
		refreshAllSelectOption();
	}


	function toRoleChange(that){

		// check if to role is same as from role then make write check box true	
		let rowId = $(that).parent().parent().attr("id"); // rowId0 -> 0
		rowId = rowId.substring(5); // 0
		if ($("#fromRoleId" + rowId).val() == $("#toRoleId" + rowId).val()) {
			$("#canWriteId" + rowId).prop("checked", true);
			addDisabled($("#canWriteId" + rowId));
		} else {
			$("#canWriteId" + rowId).prop("checked", false);
			removeDisabled($("#canWriteId" + rowId));
		}

		// read roles
		$("#readRoleId" + rowId).val($("#toRoleId" + rowId).val());
		// refresh All select option
		refreshAllSelectOption();
		
	}

	function addNewRow(that){
		// get last row id
		let lastRowId = $("#sct tbody tr:last").attr("id");
		let lastRowIdNum = lastRowId.substring(5);
		// get values
		let fromRoleId = $("#fromRoleId" + lastRowIdNum).val();
		let actionTypeId = $("#actionTypeId" + lastRowIdNum).val();
		let toRoleId = $("#toRoleId" + lastRowIdNum).val();
		let criteriaJson = $("#criteriaJsonId" + lastRowIdNum).val();

		if (fromRoleId == "") {
			displayErrorMsg("fromRoleId" + lastRowIdNum, "Please select from role");
			return;
		}
		if (actionTypeId == "") {
			displayErrorMsg("actionTypeId" + lastRowIdNum, "Please select action");
			return;
		}
		if (toRoleId == "") {
			displayErrorMsg("toRoleId" + lastRowIdNum, "Please select to role");
			return;
		}

		// disable current row full row
		disableFullRow(lastRowIdNum);
		// add new row
		createTable();
	}


	function disableFullRow(lastRowIdNum){
		$("#fromRoleId" + lastRowIdNum).addClass("disabled-field");
		$("#actionTypeId" + lastRowIdNum).addClass("disabled-field");
		$("#toRoleId" + lastRowIdNum).addClass("disabled-field");
		$("#canWriteId" + lastRowIdNum).addClass("disabled-field");
		$("#isUserSpecificId" + lastRowIdNum).addClass("disabled-field");
		$("#remarkRequiredId" + lastRowIdNum).addClass("disabled-field");
		$("#bulkApprovalId" + lastRowIdNum).addClass("disabled-field");
		$("#actionId" + lastRowIdNum).addClass("disabled-field");
		$("#nextRowId" + lastRowIdNum).addClass("disabled-field");
		$("#addNewRowId" + lastRowIdNum).addClass("disabled-field");


		// refresh All select option
		refreshAllSelectOption();
	}

	function enableFullRow(lastRowIdNum){
		$("#fromRoleId" + lastRowIdNum).removeClass("disabled-field");
		$("#actionTypeId" + lastRowIdNum).removeClass("disabled-field");
		$("#toRoleId" + lastRowIdNum).removeClass("disabled-field");
		$("#canWriteId" + lastRowIdNum).removeClass("disabled-field");
		$("#isUserSpecificId" + lastRowIdNum).removeClass("disabled-field");
		$("#remarkRequiredId" + lastRowIdNum).removeClass("disabled-field");
		$("#bulkApprovalId" + lastRowIdNum).removeClass("disabled-field");
		$("#actionId" + lastRowIdNum).removeClass("disabled-field");
		$("#nextRowId" + lastRowIdNum).removeClass("disabled-field");
		$("#addNewRowId" + lastRowIdNum).removeClass("disabled-field");
		// refresh All select option
		refreshAllSelectOption();
	}

	 



	function validateFROptTRReads(that){
		let isValidate = true;
		// validate from role, operation, to role, read roles has value
		let rowId = $(that).parent().parent().parent().attr("id"); // rowId0 -> 0
		rowId = rowId.substring(5); // 0
		// from role id
		let fromRoleId = $("#fromRoleId" + rowId).val();
		if (fromRoleId == "") {
			displayErrorMsg("fromRoleId" + rowId, "Please select from role");
			$("#actionId" + rowId).val("");
			isValidate = false;
		}else{
			clearError("fromRoleId" + rowId);
		}

		// action type id
		let actionTypeId = $("#actionTypeId" + rowId).val();
		if (actionTypeId == "") {
			displayErrorMsg("actionTypeId" + rowId, "Please select action");
			$("#actionId" + rowId).val("");
			isValidate = false;
		}else{
			clearError("actionTypeId" + rowId);
		}

		// to role id
		let toRoleId = $("#toRoleId" + rowId).val();
		if (toRoleId == "") {
			displayErrorMsg("toRoleId" + rowId, "Please select to role");
			$("#actionId" + rowId).val("");
			isValidate = false;
		}else{
			clearError("toRoleId" + rowId);
		}

		// read roles
		let readRoleId = $("#readRoleId" + rowId).val();
		if (readRoleId == "") {
			displayErrorMsg("readRoleId" + rowId, "Please select read role");
			$("#actionId" + rowId).val("");
			isValidate = false;
		}else{
			clearError("readRoleId" + rowId);
		}

		return isValidate;
	}

	function nextRowAdd(that){
		debugger;
		let isValidate = validateFROptTRReads(that);
		if(!isValidate){
			return;
		}
		// get last row id
		let lastRowId = $("#sct tbody tr:last").attr("id");
		let lastRowIdNum = lastRowId.substring(5);
		let actionId = $("#actionId" + lastRowIdNum).val();
		// if action type is next then add new row
		if (actionId == "NEXT") {
			addNewRow(that);
		}else if(actionId == "CNT_NEXT_LEVEL"){
			// let get this object from Operation actionTypeId by this id
			let thisObj = $("#actionTypeId" + lastRowIdNum);
			let value = $(thisObj).val();
			if(value == "SUBMIT_TO_NEXT_LEVEL" || value == "VERIFY_AT_NEXT_LEVEL"){
				// disable all row select action as CNT_NEXT_LEVEL actionId and to role as other level entity
				$("#toRoleId" + lastRowIdNum).val("-1");
				$("#actionId" + lastRowIdNum).val("CNT_NEXT_LEVEL");
				disableFullRow(lastRowIdNum);
				$("#nextRowId" + lastRowIdNum).addClass("disabled-field");
				setForwardSection();
			}else{
				// please select next level action first
				alert("Please select next level action first");
				$("#actionId" + lastRowIdNum).val("");
			}
		}
	}

	function validationCheckForEachRow(rowId){
		let isValidate = true;
		// from role id
		let fromRoleId = $("#fromRoleId" + rowId).val();
		if (fromRoleId == "") {
			displayErrorMsg("fromRoleId" + rowId, "Please select from role");
			isValidate = false;
		}else{
			clearError("fromRoleId" + rowId);
		}

		// action type id
		let actionTypeId = $("#actionTypeId" + rowId).val();
		if (actionTypeId == "") {
			displayErrorMsg("actionTypeId" + rowId, "Please select action");
			isValidate = false;
		}else{
			clearError("actionTypeId" + rowId);
		}

		// to role id
		let toRoleId = $("#toRoleId" + rowId).val();
		if (toRoleId == "") {
			displayErrorMsg("toRoleId" + rowId, "Please select to role");
			isValidate = false;
		}else{
			clearError("toRoleId" + rowId);
		}

		// uuid
		let uuid = $("#uuidId" + rowId).val();
		if (uuid == "") {
			displayErrorMsg("uuidId" + rowId, "Please select uuid");
			isValidate = false;
		}else{
			clearError("uuidId" + rowId);
		}

		//action
		let actionId = $("#actionId" + rowId).val();
		if (actionId == "") {
			displayErrorMsg("actionId" + rowId, "Please select action");
			isValidate = false;
		}else{
			clearError("actionId" + rowId);
		}

		return isValidate;
	
	}

	

	async function getFlowCodeValue(){
		// if flowCodeId is disabled then return make it enable and return
		if ($("#flowCodeId").prop("disabled")) {
			$("#flowCodeId").prop("disabled", false);
			$("#flowCodeValueDivId").hide();
			$("#flowCodeDynamicDivId").empty();
			// refresh All select option
			refreshAllSelectOption();
			fcLength = 0;
			return;
		}
		let flowCodeId = $("#flowCodeId").val() != "" ? $("#flowCodeId").val() :[];
		if (flowCodeId.length == 0) {
			alert("Please select flow code");
			return;
		}
		let flowCodeArr = [];
		for (let i = 0; i < flowCodeId.length; i++) {
			if (flowCodeId[i] == "") {
				continue;
			}
			flowCodeArr.push(flowCodeId[i]);
		}

		let finalObj = {};
		finalObj["flowCodeArr"] = flowCodeArr;

		// base 64 encode
		let dataEncode = btoa(JSON.stringify(finalObj));

		let url = "${contextPath}/stageConfig/getFlowCodeValue";
		let type = "GET";
		let data = {"versionCodeEncode" : dataEncode};

		let response = await asyncAjaxCall(url, type, data);

		if(response.outcome){
			let dataArr = response.data;
			let html = "";
			for (let i = 0; i < dataArr.length; i++) {
				let lebelName = dataArr[i].name;
				html += '<div class="col-md-3">';
				html += '<label class="control-label">'+lebelName+'</label>';
				html += '<select class="form-control selectpicker " id="flowCodeValueId'+i+'" multiple>';
				html += '<option value="" disabled>Select Flow Code Value</option>';
				let dataArr2 = dataArr[i].data;
				for (let j = 0; j < dataArr2.length; j++) {
					let value = dataArr2[j].entityId;
					let name = dataArr2[j].entityName;
					html += '<option value="'+value+'">'+name+'</option>';
				}
				html += '</select>';
				html += '</div>';
				fcLength++;
			}
			$("#flowCodeDynamicDivId").empty().append(html);
			// make flowCodeId disabled
			$("#flowCodeId").prop("disabled", true);
		}



		let flowCodeValue = response.data;

		let html = "<option value=''>Select Flow Code Value</option>";
		flowCodeValue.forEach(function(flowCode){
			// versionCodeId, versionName, versionCode
			html += "<option value='"+flowCode.entityId+"'>"+flowCode.entityName+"</option>";
		});
		$("#flowCodeValueId").empty().append(html);
		// show flow code value div
		$("#flowCodeValueDivId").show();
		// refresh All select option
		refreshAllSelectOption();

	}

	function generateCombinations(arrays) {
		if (arrays.length === 0) return [];
		
		function helper(arr, i) {
			if (i === arrays.length) {
				return [arr];
			}
			
			let result = [];
			for (let j = 0; j < arrays[i].length; j++) {
				result = result.concat(helper([...arr, arrays[i][j]], i + 1));
			}
			return result;
		}
		
		const combinations = helper([], 0);
		// do not include the empty array
		// return array be like [{flow:[combinations]}]
		return combinations.map(combination =>{
			let obj = {};
			obj["flow"] = combination;
			return obj;
		});
	}
	
	var fcLength = 0;
	function saveStageConfigData(){
		debugger;
		
		//stageName
		if ($("#stageName").val() == "") {
			displayErrorMsg("stageName", "Please select stage");
			return;
		}

		let finalJsonObj = {};
		// get all data start date, present date(true/false), end date;
		let startDate = $("#startDateId").val();
		if (startDate == "") {
			displayErrorMsg("startDateId", "Please select start date");
			return;
		}else{
			clearError("startDateId");
		}
		let presentDate = $("#presentDateId").prop("checked");
		let endDate = $("#endDateId").val();
		if (presentDate == false && endDate == "") {
			displayErrorMsg("endDateId", "Please select end date");
			return;
		}else{
			clearError("endDateId");
		}

		let selectActive = $("#stageName").val();

		let fstJsonObj = {};
		fstJsonObj["startDate"] = startDate;
		fstJsonObj["presentDate"] = presentDate;
		fstJsonObj["endDate"] = endDate;
		fstJsonObj["selectActive"] = selectActive;
		let masterStageNameIds = $("#masterStageNameId").val() != "" && $("#masterStageNameId").val() != null ? $("#masterStageNameId").val() : [];
		let masterStageNameIdsTemp = [];
		for (let i = 0; i < masterStageNameIds.length; i++) {
			if (masterStageNameIds[i] == "") {
				continue;
			}
			masterStageNameIdsTemp.push(masterStageNameIds[i]);
		}
		fstJsonObj["stgFrwRuleMsIds"] = masterStageNameIdsTemp;

		let readRoleId = $("#viewRolesId").val() != "" && $("#viewRolesId").val() != null ? $("#viewRolesId").val() : []; 
		let readRoleIdTemp = [];
		for (let i = 0; i < readRoleId.length; i++) {
			if (readRoleId[i] == "") {
				continue;
			}
			readRoleIdTemp.push(readRoleId[i]);
		}
		fstJsonObj["readRoleIds"] = readRoleIdTemp;

		let fromShareEntityIds = $("#clgOrUnOrHDId").val() != null && $("#clgOrUnOrHDId").val() != "" ? $("#clgOrUnOrHDId").val() : [];
		let fromShareEntityIdsTemp = [];
		for (let i = 0; i < fromShareEntityIds.length; i++) {
			if (fromShareEntityIds[i] == "") {
				continue;
			}
			let entityName = $("#clgOrUnOrHDId option[value='"+fromShareEntityIds[i]+"']").text();
			fromShareEntityIdsTemp.push(fromShareEntityIds[i] + "##" + entityName);
		}
		fstJsonObj["fromShareEntityIds"] = fromShareEntityIdsTemp;

		// 

		// ittionate over table and get all data fromRole, operation, toRole, writeRoles, readRoles, criteriaJson, action
		let tableData = [];
		let isValidate = true;
		$("#sct tbody tr").each(function(){
			let rowId = $(this).attr("id"); // rowId0 -> 0
			rowId = rowId.substring(5); // 0
			isValidate = validationCheckForEachRow(rowId);
			if(!isValidate){
				return;
			}
			let fromRoleId = $("#fromRoleId" + rowId).val();
			let actionTypeId = $("#actionTypeId" + rowId).val();
			let toRoleId = $("#toRoleId" + rowId).val();
			let actionId = $("#actionId" + rowId).val();
			let uuid = $("#uuidId" + rowId).val();

			

		


			let canWrite = $("#canWriteId" + rowId).prop("checked");
			let isUserSpecific = $("#isUserSpecificId" + rowId).prop("checked");
			let isEntitySpecific = $("#isEntitySpecificId" + rowId).prop("checked");
			let remarkRequired = $("#remarkRequiredId" + rowId).prop("checked");
			let isBulkApproval = $("#bulkApprovalId" + rowId).prop("checked");

			let bulkActionCodes = $("#bulkCodesId" + rowId).val() != "" && $("#bulkCodesId" + rowId).val() != null ? $("#bulkCodesId" + rowId).val() : [];
			let bulkActionCodesTemp = [];
			for (let i = 0; i < bulkActionCodes.length; i++) {
				let v = bulkActionCodes[i];
				if (v == "") {
					continue;
				}
				bulkActionCodesTemp.push(v);
			}



			let otherEntityValue = $("#otherEntityId" + rowId).val();
			let level = $("#levelId" + rowId).val();
			let isNextStageStartFromHere = $("#isNextStageStartFromHereId" + rowId).prop("checked");
			let isInitStage = $("#isInitStageId" + rowId).prop("checked");
			let displayRemark = $("#displayRemarkId" + rowId).val();
			
			
			let displayOrder = $("#displayOrder" + rowId).val();


		
			let selectActiveText = $("#stageName").find("option:selected").text();
			// selectActiveText -> selectOperation
			let selectedOpertion = $("#actionTypeId" + rowId).find("option:selected").text();
			let routeType = selectActiveText + " -> " + selectedOpertion;

			// fromRoleText -> toRoleText
			let fromRoleText = $("#fromRoleId" + rowId).find("option:selected").text();
			let toRoleText = $("#toRoleId" + rowId).find("option:selected").text();
			let frwCode = fromRoleText + "->" + toRoleText;
			
			let tableRowData = {};
			tableRowData["fromRoleId"] = fromRoleId;
			tableRowData["actionTypeId"] = actionTypeId;
			tableRowData["toRoleId"] = toRoleId;
			tableRowData["uuid"] = uuid;
			tableRowData["canWrite"] = canWrite;
			tableRowData["isUserSpecific"] = isUserSpecific;
			tableRowData["isEntitySpecific"] = isEntitySpecific;
			tableRowData["remarkRequired"] = remarkRequired;
			tableRowData["isBulkApproval"] = isBulkApproval;
			tableRowData["bulkActionCodes"] = bulkActionCodesTemp;
			tableRowData["otherEntityValue"] = otherEntityValue;
			tableRowData["level"] = level;
			tableRowData["isNextStageStartFromHere"] = isNextStageStartFromHere;
			tableRowData["isInitStage"] = isInitStage;
			tableRowData["displayRemark"] = displayRemark;
			tableRowData["actionCode"] = actionId;


			tableRowData["displayOrder"] = displayOrder;
			tableRowData["routeType"] = routeType;
			tableRowData["frwCode"] = frwCode;

			tableData.push(tableRowData);
		});

		if(!isValidate){
			return;
		}

		if (tableData.length == 0) {
			alert("Please add at least one row");
			return;
		}

		finalJsonObj["fstJsonObj"] = fstJsonObj;
		finalJsonObj["tableData"] = tableData;
		
		// stepByStepApprovalId
		let stepByStepApprovalId = $("#stepByStepApprovalId").prop("checked") ? "true" : "false";
		finalJsonObj["doesStepByStepApproval"] = stepByStepApprovalId;

		//shouldPrimeryRoleCheckId
		let shouldPrimeryRoleCheckId = $("#shouldPrimeryRoleCheckId").prop("checked") ? "true" : "false";
		finalJsonObj["shouldPrimaryRoleCheck"] = shouldPrimeryRoleCheckId;


		// doesHaveFlowCodeId
		let doesHaveFlowCodeId = $("#doesHaveFlowCodeId").prop("checked") ? "true" : "false";
		finalJsonObj["doesHaveFlowCode"] = doesHaveFlowCodeId;
		let allCombinationsStr = "";
		let dynamicFlowCodeArr = [];
		if (doesHaveFlowCodeId == "true") {
			let flowCodeId = $("#flowCodeId").val() != "" ? $("#flowCodeId").val() :[];
			if (flowCodeId.length == 0) {
				alert("Please select flow code");
				return;
			}
			
			// loop on fcLength
			let allSeletedFc = [];
			for (let i = 0; i < fcLength; i++) {
				let flowCodeValueIds = $("#flowCodeValueId" + i).val() != "" ? $("#flowCodeValueId" + i).val() : [];
				let flowCodeValueIdsTemp = [];
				for (let j = 0; j < flowCodeValueIds.length; j++) {
					if (flowCodeValueIds[j] == "") {
						continue;
					}
					flowCodeValueIdsTemp.push(flowCodeValueIds[j]);
				}
				allSeletedFc.push(flowCodeValueIdsTemp);
			}

			// get all combinations
			let allCombinations = generateCombinations(allSeletedFc);
			// convert allCombinations to string
			allCombinationsStr = JSON.stringify(allCombinations);
			
		}
		finalJsonObj["allCombinations"] = allCombinationsStr;

		// Role level hierarchy Data
		let roleLevelHierarchyData = [];
		// get all selected role level hierarchy by name start with roleLevelName
		$("[name^='roleLevelName']").each(function(){
			let roleLevelVal = $(this).val();
			if (roleLevelVal != "") {
				roleLevelHierarchyData.push(roleLevelVal);
			}
		});
		finalJsonObj["roleLevelHierarchyData"] = roleLevelHierarchyData;

		


		// forward section data
		let forwardSectionData = {};
		if (isForwardSectionActive) {
			let dynamicEntity = $("input[name='dynamicEntity']:checked").val();
			if (dynamicEntity == undefined) {
				alert("Please select entity");
				return;
			}
			let dynamicEntitySelct = $("#dynamicEntitySelctId").val();
			if (dynamicEntitySelct == "") {
				alert("Please select entity");
				return;
			}
			let dynamicEntityRoleSelct = $("#dynamicEntityRoleSelctId").val();
			if (dynamicEntityRoleSelct == "") {
				alert("Please select role");
				return;
			}
			
			forwardSectionData["dynamicEntity"] = dynamicEntity;
			forwardSectionData["dynamicEntitySelectedId"] = dynamicEntitySelct;
			forwardSectionData["dynamicEntitySelectedRoleId"] = dynamicEntityRoleSelct;
			finalJsonObj["forwardSectionData"] = forwardSectionData;
		}else{
			finalJsonObj["forwardSectionData"] = forwardSectionData;
		}


		debugger;
		// encode json
		let finalJsonStr = btoa(JSON.stringify(finalJsonObj));
		$("#saveAndUpdateNewStageConfigObjectStrId").val(finalJsonStr);

		bootbox.confirm({
			message: "Are you sure you want to save?",
			buttons: {
				confirm: {
					label: 'Yes',
					className: 'btn-success'
				},
				cancel: {
					label: 'No',
					className: 'btn-danger'
				}
			},
			callback: function (result) {
				if(result){
					$("#saveAndUpdateNewStageConfigObjectModify").submit();
				}
			}
		});
	}



	function displayErrorMsg(id, message) {
	clearError(id);
	var element = document.getElementById(id);
	if (element) {
		var errorSpan = document.createElement('span');
		errorSpan.className = 'error-message';
		errorSpan.textContent = message;
		errorSpan.id = id + '-error';
		element.parentNode.insertBefore(errorSpan, element.nextSibling);
	} else {
		console.error('Element with id ' + id + ' not found.');
	}
}

function clearError(id) {
	var existingError = document.getElementById(id + '-error');
	if (existingError) {
		existingError.parentNode.removeChild(existingError);
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
let dyanmicEntityDivIdCount = 0;
async function getEntityListOnShareEntity(that, isDynamic){
	let value = $(that).val();
	if (value == "") {
		return;
	}
	let requestData =  {};

	// get all dynamicEntityId${dyanmicEntityDivIdCount} and get all value and make parent->child->grandChild .. so on dynamicEntityId0->dynamicEntityId1->dynamicEntityId2
	let dynamicEntityId = "";
	// loop over dyanmicEntityDivIdCount
	if(!isDynamic){
		for (let i = 0; i <= dyanmicEntityDivIdCount; i++) {
			let dynamicEntityIdVal = $("#dynamicEntityId" + i).val();
			if (dynamicEntityIdVal == "") {
				break;
			}
			dynamicEntityId += dynamicEntityIdVal + ",";
		}
	}else{
		dynamicEntityId = $(that).val() + ",";
	}
	// remove last comma
	if (dynamicEntityId != "") {
		dynamicEntityId = dynamicEntityId.substring(0, dynamicEntityId.length - 1);
	}

	// base64 encode
	//requestData["dynamicEntityIds"] = btoa(dynamicEntityId);
	requestData["dynamicEntityIds"] = dynamicEntityId;



	let ajaxResponse = await asyncAjaxCall("${contextPath}/stageConfig/getEntityListOnShareEntity", "GET", requestData);
	console.log(ajaxResponse);
	debugger;
	let outcome = ajaxResponse.outcome;
	if (outcome){
		let data = ajaxResponse.data;
		let entityProfileList = data.entityProfileList;
		let isRequireDynamicParams = data.isRequireDynamicParams;
		if(isRequireDynamicParams){
			// except dyanmicEntityDivId0 remove all
			if(isDynamic){
				for (let i = 1; i <= dyanmicEntityDivIdCount; i++) {
					$("#dynamicEntityDivId" + i).remove();
				}
				dyanmicEntityDivIdCount = 0;
			}
			// show dynamic params
			for (let i = 0; i < entityProfileList.length; i++) {
				dyanmicEntityDivIdCount++;
				let profiles = entityProfileList[i];
				let html = "<div class='col-md-2' id='dynamicEntityDivId"+dyanmicEntityDivIdCount+"'>";
				html += "<label>Next Level Entity Type</label>";
				html += "<select class='form-control form-select' id='dynamicEntityId"+dyanmicEntityDivIdCount+"' onchange='getEntityListOnShareEntity(this, false)'>";
				html += "<option value=''>Select</option>";
				for (let j = 0; j < profiles.length; j++) {
					html += "<option value='"+profiles[j].entityId+"'>"+profiles[j].entityName+"</option>";
				}
				html += "</select>";
				html += "</div>";
				// append next to that div
				$("#dyanmicEntityDivId" + (dyanmicEntityDivIdCount - 1)).after(html);
			}
		}else{
				// remove all dyanmicEntityDivId
				for (let i = 1; i <= dyanmicEntityDivIdCount; i++) {
					$("#dynamicEntityDivId" + i).remove();
				}
				dyanmicEntityDivIdCount = 0;
			}
	}
}


	let currentUserPrimaryRole = "${principal.dbUser.primaryRole.roleCode}";
	async function fetchEntityListByShareEntity(that, isTrueORFlase){
		// get dynamicEntityId0 value
		let dynamicEntityId = $("#dynamicEntityId0").val();
		if (dynamicEntityId == "") {
			return;
		}
		let requestData = {};
		requestData["particularEntityId"] = dynamicEntityId;
		// get all other dynamicEntityIds
		let depentEntityIds = [];
		for (let i = 1; i <= dyanmicEntityDivIdCount; i++) {
			let dynamicEntityIdVal = $("#dynamicEntityId" + i).val();
			if (dynamicEntityIdVal == "") {
				break;
			}
			depentEntityIds.push({"entityId" : dynamicEntityIdVal, "index" : i});
		}
		requestData["dependingEntityIds"] = depentEntityIds;
		
		// base64 encode
		let finalJsonStr = btoa(JSON.stringify(requestData));
		let childEntityList = await asyncAjaxCall("${contextPath}/system/getCurrentEntityChildListEntityHierarchyList", "GET", {});
		let childEntityListOtcome = childEntityList.outcome;
		let entityList = [];
		if (childEntityListOtcome){
			let x = childEntityList.data;
			x.forEach(function(entity){
				let levelIdAndType = entity.levelIdAndType;
				entityList.push(levelIdAndType);
			});
		}
		let ajaxResponse = await asyncAjaxCall("${contextPath}/stageConfig/getEntityListByShareEntity", "GET", {"finalJsonStr" : finalJsonStr});
		let outcome = ajaxResponse.outcome;
		if (outcome){
			let data = ajaxResponse.data;
			// loop over data
			let html = '<option value="" disabled >Select</option>';
			for (let i = 0; i < data.length; i++) {
				// entityId, entityName, entityType
				let entityId = data[i].entityId;
				let entityName = data[i].entityName;
				let entityType = data[i].entityType;

				let selected = "";
				let et = entityId + "##" + entityType;
				if (!entityList.includes(et) && currentUserPrimaryRole != "ROLE_ADMIN") {
					continue;
				}
				html += "<option value='"+entityId+"##"+entityType+"' "+selected+">"+entityName+"</option>";
			}
			// select
			$("#clgOrUnOrHDId").empty().append(html);
			// refresh All select option
			refreshAllSelectOption();
		}
	}

	function getChildStageData(that){
		let parentId = $(that).val();
		if (parentId == "") {
			return;
		}
		$.ajax({
			url: "${contextPath}/stageConfig/getChildStageData",
			type: "GET",
			data: {"parentId" : parentId},
			success: function(response){
				let outcome = response.outcome;
				if (outcome){
					let data = response.data;
					// loop over data
					let html = '<option value="" disabled selected>Select</option>';
					for (let i = 0; i < data.length; i++) {
						// entityId, entityName, entityType
						let className = data[i].className;
						let packageName = data[i].packageName;
						html += "<option value='"+packageName+"'>"+className+"</option>";
					}

					$("#stageName").empty().append(html);
					// refresh All select option
					refreshAllSelectOption();
				}
			},
			error: function(response){
				console.log(response);
			}
		});
	}



</script>