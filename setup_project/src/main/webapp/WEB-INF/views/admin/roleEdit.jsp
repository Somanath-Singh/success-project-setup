<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userDetails" value="${serviceOutcome.data}" />
<div class="breadcrumb_conatiner">
	<div class="col-md-6">
		<h4 class="change-color">
			<spring:message code="site.admin.mst.role.edit"></spring:message>
		</h4>
	</div>
	<div class="col-md-6">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">

				<li class="breadcrumb-item"><a href="${contextPath}/home"><i
						class="fa fa-home"></i></a></li>

				<li class="breadcrumb-item active" aria-current="page">User
					Management</li>
			</ol>
		</nav>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="card">
			<div class="card-header">
				<h5 class="card-title"><spring:message code="site.admin.mst.role.details"></spring:message></h5>
			</div>
			<div class="card-body">
			<form class="form-horizontal" action="${contextPath}/admin/role/addNupdate" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="hidden" name="roleId" value="${userDetails.roleId}"/>
				<div class="row">
				<div class="form-group col-md-2">
					<label class="required"><spring:message code="site.admin.mst.role.display.name"></spring:message> :</label>
					<div class="col-md-12">
						<input type="text" id="displayName" name="displayName" value="${userDetails.displayName}"  maxlength="40" class="form-control form-control-sm" required="required">
					</div>
				</div>
				<div class="form-group col-md-2">
					<label class="required"><spring:message code="site.admin.mst.role.description"></spring:message> :</label>
					<div class="col-md-12">
						<input type="text" name="description" id="description" class="form-control form-control-sm"   maxlength="40" value="${userDetails.description}" required="required">  
					</div>
				</div>
				<div class="form-group col-md-2">
					<label class="required"><spring:message code="site.admin.mst.role.maxassignments"></spring:message> :</label>
					<div class="col-md-12">
						<input type="number" min="-1" name="maxAssignments" id="maxAssignments" class="form-control form-control-sm"  maxlength="20" value="${userDetails.maxAssignments}" readonly>  
					</div>
				</div>
		
				<div class="form-group col-md-12 text-center mt-2">
					<input type="submit" value="<spring:message code="site.common.submit"></spring:message>" class="btn btn-success btn-sm">
					<a href="${contextPath}/admin/role/list" type="button" class="btn btn-cancel btn-warning"><spring:message code="site.common.back"></spring:message></a>
				</div>
			</div>
		</form>
	  </div>
	  </div>
	</div>
</div>



			
<script>
	$(document).ready(function() {
		$('#basic-datatables').DataTable({});
	});
</script>

