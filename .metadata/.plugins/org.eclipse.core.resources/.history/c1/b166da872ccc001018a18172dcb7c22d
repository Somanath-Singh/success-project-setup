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

	


</style>
<section class="main_container">
	<div class="row">
		<!-- DropDwon -->
		<div class="col-md-12">
			<section class="panel">
				<header class="panel-heading">
					<h2 class="panel-title">Stage Configuration</h2>
				</header>
				<div class="panel-body">
					<div class="row " style="display: flex; justify-content: flex-center; flex-wrap: wrap;">
						<form action="${contextPath}/shareEntity/map-share-entity-modules" method="post" id="saveshareentityMap" >
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="col-md-12">
							<div class="bagGroundColor px-3">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label" style="color: #fff;">Select Module</label>
											<select class="col-md-3 form-control" name="id" id="modId" onchange="getModuleMapDetails(this.value)" name="modalName">
												<option value="0">Select module</option>
												<c:forEach items="${moduleList}" var="module">
													<option value="${module.id}" ${module.id eq moduleId ? 'selected' : ''}>${module.tableDisplayName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<c:if test="${not empty moduleId}">
										<div class="col-md-1">
											<label class="control-label" style="color: #fff;">Is Multi Level</label>
											<input type="hidden" id="isMultiLevel" name="isMultiLevel" value="${module.isMultiLevel}">
											<input  type="checkbox"  onclick="setMultiLevelValue(this)" ${module.isMultiLevel ? 'checked' : ''}>
										</div>
										<div class="col-md-3">
											<label class="control-label" style="color: #fff;">Limit Per Page</label>
											<input class="col-md-3 form-control" type="text" id="limitPerPage" name="limitPerPage" value="${module.limitPerPage}">
										</div>
									</c:if>
									
								</div>
							</div>
						</div>
						<c:if test="${not empty moduleId}">
								<div class="col-md-12 mt-1">
									<c:forEach items="${shareEntityList}" var="shareEntity" varStatus="count">
										<div class="col-md-4 mt-1">
											<div class="bagGroundColor px-3">
												<div class="form-group">
													<div class="row">
														<div class="col-md-12 d-flex align-items-center">
															<label for="chkBx${count.count}" style="margin-top: 20px;">
																<input type="hidden" name="shareEntityModulesMap[${count.index}].shareEntityId" value="${shareEntity.id}">
																<input type="checkbox" name="shareEntityModulesMap[${count.index}].isMapped" id="chkBx${count.count}" ${shareEntity.isMapped ? 'checked' : ''} onclick="enableRole(this,${count.count})" value="${shareEntity.isMapped}">
																${shareEntity.displayName}
															</label>
															<label for="" style="margin-left: 15px;" >
																<label class="control-label" style="color: #333;">Select Module</label>
																<input type="text" name="shareEntityModulesMap[${count.index}].roleCode" id="roleCode${count.count}" class="form-control form-control-sm" ${shareEntity.isMapped ? '' : 'readonly'} value="${shareEntity.isMapped ? shareEntity.mapRoleCode : ''}"/>
															</label>
														</div>
													</div>
												</div>
											</div>
										</div>
								</c:forEach>
							</div>
							<div class="col-md-12 text-center">
									<button type="button" onclick="save()">Save</button>

							</div>
						</c:if>
					</form>
					</div>
				</div>
			</section>
	</div>




	<form action="${contextPath}/shareEntity/map-share-entity-modules" method="get" id="shareentityMap" >
		<input type="hidden" name="moduleId" id="moduleId">
	</form>


</section>
<script>
	function getModuleMapDetails(id){
		if(id!=0){
			$("#moduleId").val(id);
		}
		$("#shareentityMap").submit();
	}
	function setMultiLevelValue(that){
		if (that.checked) {
			$("#isMultiLevel").val(true);
		} else {
			$("#isMultiLevel").val(false);
		}
	}
function enableRole(that,count){
	if (that.checked) {
		that.value=true;
		$("#roleCode"+count).prop('readonly', false)
	} else {
		that.value=false;
		$("#roleCode"+count).val("");
		$("#roleCode"+count).prop('readonly', true)
	}
}

function save(){
	$("#saveshareentityMap").submit();
}

</script>

