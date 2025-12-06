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
		font-weight: bold !important;
	}



</style>
<section class="content">
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
						<h5 class="card-title">Stage Configuration List</h5>
						
					</div>
					
					<div class="card-body">
						<div class="row " style="display: flex; justify-content: flex-center; flex-wrap: wrap;">
							<div class="col-md-12">
								<div class="bagGroundColor">
									<div class="row">

										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">Select Parent Activity</label>
												<select class="col-md-3 form-control selectpicker" id="parentStageName" onchange="getChildStageActivity(this)" name="parentModalName" data-live-search="true">
													<option value="" disabled>Select</option>
													<c:forEach items="${parentWorkFlowModulesList}" var="pStage">
														<option value="${pStage.id}"<c:if test="${pStage.id eq parentStageId}">selected</c:if>>${pStage.parentModuleName}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">Select Activity</label>
												<select class="col-md-3 form-select form-control" id="stageName" onchange="getStageConfigList(this)" name="modalName">
													<option value="">Select Stage</option>
													<c:forEach items="${allEntityClasses}" var="stage">
														<option value="${stage.packageName}" <c:if test="${stage.packageName eq entityClassName}">selected</c:if>>${stage.className}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
						<div class="col-md-12">
							<div class="table-responsive">
								<table class="table table-bordered table-striped exportbtn">
									<thead>
										<tr>
											<th class="text-center">Sl.No.</th>
											<th>Organization Level</th>
											<th>organization Name</th>
											<th>Start Date</th>
											<th>End Date</th>
											<th>Active</th>
											<th class="text-center">Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${orgMapList}" var="stageConfig" varStatus="stageConfigCnt">
											<tr>
												<td class="text-center">${stageConfigCnt.count}</td>
												<td>${stageConfig.organizationLevel}</td>
												<td>${stageConfig.organizationName}</td>
												<td>${stageConfig.startDate}</td>
												<td>${stageConfig.endDate eq null ? 'N/A' : stageConfig.endDate}</td>
												<td>${stageConfig.isActive}</td>
												<td class="text-center">
													<a href="javascript:void(0)" class="btn btn-info btn-sm" onclick="editStageConfig('${stageConfig.id}')"><i class="fa fa-eye"></i></a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

	<form class="form-horizontal" action="${contextPath}/stageConfig/configView4List" method="GET" id="stageConfigForm">
		<input type="hidden" name="entityClassName" value="" id="entityClassName"/>
		<input type="hidden" name="parentStageNameEncode" value="" id="parentStageNameEncodeId"/>
	</form>

	<form class="form-horizontal" action="${contextPath}/stageConfig/viewConfigView4" method="GET" id="editStageConfigForm">
		<input type="hidden" name="stageConfigIdStr" value="" id="stageConfigId"/>
	</form>


</section>
<script>

	function getStageConfigList(obj){
		let entityClassName = obj.value;
		if(entityClassName != ""){
			$("#entityClassName").val(btoa(entityClassName));
			$("#parentStageNameEncodeId").val(btoa($("#parentStageName").val()));
			$("#stageConfigForm").submit();
		}
	}

	function editStageConfig(id){
		$("#stageConfigId").val(btoa(id));
		$("#editStageConfigForm").submit();
	}

	function getChildStageActivity(that){
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