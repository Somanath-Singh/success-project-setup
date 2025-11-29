<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<style>
.btn-secondary {
    background-color: #1463c0 !important;
        border-color: #1463c0 !important;
}
</style>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 

<div class="breadcrumb_conatiner">
    <div class="col-md-6">
        <h4 class="change-color">Manage Role</h4>
    </div>
    <div class="col-md-6">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${contextPath}/home"><i class="fa fa-home"></i></a></li>
                <li class="breadcrumb-item active" aria-current="page">User Management</li>
            </ol>
        </nav>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
      <div class="card">
        <div class="card-header">
            <h5 class="card-title">Add Role</h5>
        </div>
          <div class="card-body">
            <form class="form-horizontal" action="${contextPath}/admin/role/addNupdate" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="roleId" value="${userDetails.roleId}"/>
                <div class="row">
                    <div class="form-group col-md-2">
                        <label class="required"><spring:message code="site.admin.mst.role.display.name"></spring:message> :</label>
                        <div class="col-md-12">
                            <input type="text" id="displayName" name="displayName" value="${userDetails.displayName}" maxlength="30" class="form-control form-control-sm" required="required">
                        </div>
                    </div>
                    <div class="form-group col-md-2">
                        <label class="required" ><spring:message code="site.admin.mst.role.description"></spring:message> :</label>
                        <div class="col-md-12">
                            <input type="text" name="description" id="description" class="form-control form-control-sm"  maxlength="30" value="${userDetails.description}" required="required">
                        </div>
                    </div>
                    <div class="form-group col-md-2">
                        <label class="required" ><spring:message code="site.admin.mst.role.maxassignments"></spring:message> :</label>
                        <div class="col-md-12">
                            <input type="number" min="-1" name="maxAssignments" id="maxAssignments" class="form-control form-control-sm" maxlength="30"  value="-1" readonly>
                        </div>
                    </div>
                <div class="form-group col-md-12 text-center mt-2">
                        <input type="submit" value="<spring:message code="site.common.submit"></spring:message>"
                     class="btn btn-success btn-sm">
                     <a href="${contextPath}/admin/role/list" class="btn btn-cancel btn-danger">Cancel</a>
                </div>
            </form>
          </div>
        </div>
    </div>

    <div class="col-md-12">
        <div class="card mt-2">
            <div class="card-header">
                <h5 class="card-title">Role List</h5>
            </div>
            <div class="card-body">
                <%-- <h6 class="headingbg mb-4"><spring:message code="site.admin.mst.role.list"></spring:message></h6> --%>
                <div class="table-responsive">
                    <table id="basic-datatables" class="display table table-bordered table-hover exportbtn" >
                        <thead>
                            <tr>
                                <th>Sl no.</th>
                                <th><spring:message code="site.admin.mst.role.code"></spring:message></th>
                                <th><spring:message code="site.admin.mst.role.display.name"></spring:message></th>
                                <th><spring:message code="site.admin.mst.role.description"></spring:message></th>
                                <th style="width: 190px;"><spring:message code="site.common.action"></spring:message></th>
                            </tr>
                        </thead>
                        <tbody id="tbd">
                           <c:forEach items="${roleList}" var="role" varStatus="roleCount">
                                <tr>
                                    <td>${roleCount.count}</td>
                                    <td>${role.roleCode}</td>
                                    <td>${role.displayName}</td>
                                    <td>${role.description}</td>
                                    <td>
                                    <button class="btn btn-success btn-sm" onclick="editApplicationById('${role.roleId}')" title="Edit Role">
                                        <i class="fa fa-edit" ></i>
                                    </button>
                                    <button class="btn btn-primary btn-sm" onclick="viewApplicationById('${role.roleId}')" title="View Role">
                                        <i class="fa fa-eye" ></i>
                                    </button>
                                    <c:if test="${role.isActive eq true}">
                                        <button class="btn btn-danger btn-sm" onclick="lockUser('${role.roleId}')" title="Lock Role" >
                                            <i class="fa fa-lock" aria-hidden="true" ></i>
                                        </button>
                                    </c:if>
                                    <c:if test="${role.isActive eq false}">
                                        <button class="btn btn-success btn-sm" onclick="unlockUser('${role.roleId}')" title="Unlock Role">
                                            <i class="fa fa-unlock-alt" aria-hidden="true"></i>
                                        </button>
                                    </c:if>
                                    <button class="btn btn-secondary btn-sm" onclick="mapRoleLevel('${role.roleId}')" title="Link Role To Level">
                                        <i class="fa fa-link" ></i>
                                    </button>
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

<form  method="GET" id="formId">
</form>
<form action="${contextPath}/admin/role/isActive" method="POST" id="lockNunlockForm">
		<input type="hidden" name="roleId" id="roleId" />
		<input type="hidden" name="isActive" id="isActive" /> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
	
	<script>
	 
		function editApplicationById(id){
				    $("#formId").attr('action','${contextPath}/admin/role/edit/'+ id +'');
				    $("#formId").submit();
		}

		function viewApplicationById(id){
			         $("#formId").attr('action','${contextPath}/admin/role/view/'+ id +'');
					    $("#formId").submit();
		 }

		function unlockUser(roleId){
			 $("#isActive").val(true);
			 $("#roleId").val(roleId);
			    bootbox.confirm("Do you want to unlock this role?",
			            function(result) {
			                    if (result == true) {
			                            $("#lockNunlockForm").submit();
			                    }
			            });
				 }
		function lockUser(roleId){
			 $("#isActive").val(false);
			 $("#roleId").val(roleId);
			    bootbox.confirm("Do you want to lock this role?",
			            function(result) {
			                    if (result == true) {
			                            $("#lockNunlockForm").submit();
			                    }
			            });
				 }
		
		function mapRoleLevel(roleId){
			window.location = "${contextPath}/admin/role/roleLevelMap/" + roleId;
		}

		function getRoleListByOrg(obj){
			var orgId = $(obj).val();
			var url = "${contextPath}/admin/role/list"+ "?filterData=" + btoa(orgId);
			window.location = url;
		}


	</script>
	
