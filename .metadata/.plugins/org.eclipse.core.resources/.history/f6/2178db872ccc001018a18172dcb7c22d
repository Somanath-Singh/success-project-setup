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
		<div class="row mt-3">
			<div class="col-md-12">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="${contextPath}/home"><i class="fa fa-home"></i></a></li>
						<li class="breadcrumb-item"><a href="#">Stage Config</a></li>
					</ol>
				</nav>
			</div>
			<%@ include file="/WEB-INF/views/stageConfig/message.jsp"%>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-header d-flex justify-content-between">
						<h5 class="card-title">Stage Configuration</h5>
						
					</div>
					
					<div class="card-body">
						<div class="row " style="display: flex; justify-content: flex-center; flex-wrap: wrap;">
							<div class="col-md-12">
								<div class="bagGroundColor">
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">Select Activity</label>
												<input type="text" class="form-control" value="${configView4DTO.activityName}" readonly>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">Master Stage</label>
												<input type="text" class="form-control" value="${configView4DTO.masterStageName eq null ? 'N/A' : configView4DTO.masterStageName}" readonly>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">Effective Start Date</label>
												<input type="text" class="form-control" value="${configView4DTO.startDate}" readonly>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">Effective End Date</label>
												<input type="text" class="form-control" value="${configView4DTO.endDate eq null ? 'N/A' : configView4DTO.endDate}" readonly>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<input type="checkbox"  ${configView4DTO.isAStepByStepApproach eq true ? 'checked' : ''} >
												<span class="check-box-label">Step By Step Approval</span>
											</div>
										</div>

										<div class="col-md-3">
											<div class="form-group">
												<input type="checkbox" ${configView4DTO.isPrimaryRoleCheck eq true ? 'checked' : ''} >
												<span class="check-box-label">Should Primery Role Check</span>
											</div>
										</div>

										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">viewRoles</label>
												<select class="form-control selectpicker" multiple >
													<c:forEach items="${configView4DTO.viewRoles}" var="role">
														<option value="${role}" selected>${role}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">entityTypeName</label>
												<input type="text" class="form-control" value="${configView4DTO.entityTypeName}" readonly>
											</div>
										</div>

										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">entityName</label>
												<input type="text" class="form-control" value="${configView4DTO.entityName}" readonly>
											</div>
										</div>

									</div>
								</div>
							</div>
							
							<div class="col-lg-12">
								<div class="table-responsive table-container-custom">
									<table class="table table-bordered table-hover table-striped custom-th-width custom-table-width" id="sct">
										<thead class="text-center bagGroundColorRvr" style="color: #946464;">
											<tr>
												<th>From Role</th>
												<th>Operation</th>
												<th>To Role</th>
												<th>Unique Id</th>
												<th>Write Roles</th>
												<th>Is User Specific</th>
												<th>is Entity Specific</th>
												<th>Does Remark Required</th>
												<th>Bulk Approval</th>
												<th>Bulk Codes</th>
												<th style="width: 100px;">Level</th>
												<th>Init Stage</th>
												<th>Display Remark</th>
												<th style="width: 180px">Stage</th>
											</tr>
										</thead>
										<tbody id="sctBody">
											<c:forEach items="${configView4DTO.stageConfigList}" var="stageConfig" varStatus="stageConfigCnt">
												<tr class="text-center">
													<td>${stageConfig.fromRole}</td>
													<td>${stageConfig.operation}</td>
													<td>${stageConfig.toRole}</td>
													<td>${stageConfig.uniqueId}</td>
													<td>${stageConfig.writeRoles eq true ? 'Yes' : 'No'}</td>
													<td>${stageConfig.isUserSpecific eq true ? 'Yes' : 'No'}</td>
													<td>${stageConfig.isEntitySpecific eq true ? 'Yes' : 'No'}</td>
													<td>${stageConfig.doesRemarkRequired eq true ? 'Yes' : 'No'}</td>
													<td>${stageConfig.bulkApproval eq true ? 'Yes' : 'No'}</td>
													<td>${stageConfig.bulkCodes}</td>
													<td>${stageConfig.level}</td>
													<td>${stageConfig.initStage eq true ? 'Yes' : 'No'}</td>
													<td>${stageConfig.displayRemark}</td>
													<td>${stageConfig.stage}</td>
													
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							
						</div>
					</div>
					<!-- Back -->
					<div class="card-footer">
						<div class="row">
							<div class="col-md-12 text-center">
								<button type="button" class="btn btn-warning btn-sm" onclick="window.history.back()">Back</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



</section>
<script>



</script>