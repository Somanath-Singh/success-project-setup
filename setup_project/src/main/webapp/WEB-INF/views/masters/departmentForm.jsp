<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<script src="${contextPath}/assets/appJs/validation/commonMaster.js"></script>
<sec:authentication var="principal" property="principal" />
<c:set var="roleCode" value="${principal.primaryRole.roleCode}"/>
    
 <div class="cardcontainer">
    
    <div class="breadcrumb_conatiner">
			<div class="col-md-6"><h4 class="change-color">Add Or Update Department</h4></div>
			
			<div class="col-md-6">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="${contextPath}/home"><i class="fa fa-home"></i></a></li>
						<li class="breadcrumb-item active" aria-current="page">Add Or Update Department</li>
					</ol>
				</nav>
			</div>
		</div>
    
<div class="row">
	<div class="col-md-12">
		<div class="card">
				<div class="card-header">
					<h6 class="card-title">Add Or Update Department</h6>
					
					
				</div>

			<div class="card-body">
			    <form action="${contextPath}/department/save" method="post" id="saveFormId" class="row">
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			        <input type="hidden" name="departmentId" value="${department.departmentId}" id="departmentId" />
			
			        <c:set var="functionList" value="getRoleList(this)"/>
					<!-- Pass this functions in include page -->
					<%@ include file="/WEB-INF/views/masters/entityAndUser/entityIdAndUserLevelListForMst.jsp"%>
			
			        <div class="form-group col-md-3">
			            <label class="required">Department Name :</label>
			            <input type="text" class="form-control form-control-sm require" id="departmentName" name="departmentName" maxlength="20" value="${department.departmentName}">
			        </div>
			
			        <div class="form-group col-md-3">
			            <label class="required">Department Code :</label>
			            <input type="text" class="form-control form-control-sm capitalizeEachLetter require" id="departmentCode" name="departmentCode" maxLength="20" value="${department.departmentCode}" <c:if test="${!empty department.departmentCode}">readonly</c:if> />
			        </div>
			
			        <div class="form-group col-md-3">
			            <label class="">Department Description :</label>
			            <textarea class="form-control form-control-sm " id="departmentDescription" name="departmentDescription" maxLength="100">${department.departmentDescription}</textarea>
			        </div>
			
			        <%@ include file="/WEB-INF/views/masters/roleEntityMap.jsp"%>
					<%@ include file="/WEB-INF/views/masters/appEntityMap.jsp"%>
			        
			        <div class="form-group col-md-12 text-center mt-3" style="">
			            <button type="button" class="btn btn-submit" onclick="submitForm()">
			                ${empty department.departmentId ? 'Submit' : 'Update'}
			            </button>
			            <button type="button" class="btn btn-cancel btn-warning" onclick="history.back()">Back</button>
			        </div> 
			    </form>
			</div> 
		</div>
	</div>
</div>

<div class="cardcontainer cardContainerNotFirst">
	<div class="row mt-3">
		<div class="col-md-12">
			<div class="card">
				<div class="card-header">
	                    <h6 class="card-title">Department List</h6>
				</div>
			    <div class="card-body">
					<%@ include file="/WEB-INF/views/masters/entityAndUser/entityIdAndUserLevelListDrpForMst.jsp"%>
					<table class="datatable table table-striped table-bordered exportbtn mt-5">
				    	<thead>
					        <tr>
					            <th style="width: 65px;text-align:center;">Sl. No</th>
					            <th>Department Name</th>
					            <th>Department Code</th>
					            <th>Action</th>
					            
					        </tr>
				    	</thead>
				    	<tbody>
					        <c:forEach items="${departmentMasterList}" var="dept" varStatus="count">
					            <tr>
					                <td style="text-align:center;">${count.count}</td>
					                <td>${dept.departmentName}</td>
					                 <td>${dept.departmentCode}</td>
					                
					                <td><a href="#" onclick="editDepartment('${dept.departmentId}')" class="btn btn-primary btn-xs" title="Edit">
			                                    <i class="fa fa-edit"></i>
			                                </a>
			                                <button type="button" class="btn ${!dept.isActive ? 'btn-danger' : 'btn-success'} btn-xs" data-toggle="tooltip" onclick="updateStatus(${dept.departmentId},${dept.isActive ?false:true})" title="${dept.isActive ? 'Deactivate' : 'Activate'}">
			                                    <i class="${!dept.isActive ? 'fa fa-lock' : 'fa fa-unlock'}"></i>
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
</div>

<form action="${contextPath}/department/edit" method="get" id="editForm">
    <input type="hidden" name="departmentIdHd" id="departmentIdHd" />
</form>

<form action="${contextPath}/department/update-status" method="post" id="updateStatusForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" id="departmentIdd" name="departmentIdd"/>
    <input type="hidden" name="status" id="isActiveStatus" />  
</form>

<!-- View Section form -->
<form action="${contextPath}/section/add" method="get" id="viewSectionForm">
    <input type="hidden" id="encodedDepartmentId" name="encodedDepartmentId"/>
</form>



<script>

    function viewSections(departmentId){
        if(departmentId==''){
            bootbox.alert('Department id empty'); 
        }else{
            $('#encodedDepartmentId').val(btoa(departmentId));
            $('#viewSectionForm').submit();
        }
    }

    

document.addEventListener("DOMContentLoaded", function() {
	
	var departmentid= '${department.departmentId}';
	if(departmentid===''){
		document.getElementById("municipalityField").style.display = "none";
	    document.getElementById("governingBodyField").style.display = "none";
	}else{
		objectAccoudingToTheObjectType();
	}
    
});

function submitForm(){
	let otherRoleCodes = $('#otherRoleCodes').val();
	if(otherRoleCodes == null || otherRoleCodes.length == 0){
		bootbox.alert('Please select at least one role');
		return;
	}
	let entityAppsId = $('#entityAppsId').val();
	if(entityAppsId == null || entityAppsId.length == 0){
		bootbox.alert('Please select at least one module');
		return;
	}
	if(validateForm() ){
	if(encryptFormData('saveFormId', tableOrTbodyIDS,tableOrTbodyKeys)){
		confirmation('saveFormId');
	}
   else{
		
		bootbox.alert('SomeThing error happens');
	}
	}
} 

function editDepartment(departmentId){
	if(departmentId==''){
		bootbox.alert('Department id empty');	
	}else{
		$('#departmentIdHd').val(departmentId);
		if(encryptFormData('editForm', tableOrTbodyIDS,tableOrTbodyKeys)){
			$('#editForm').submit();
		}else{
			bootbox.alert('Something error happens');
		}
		
	}
	
}
function updateStatus(departmentId,isActive){
	debugger
	
		$("#isActiveStatus").val(isActive);
		$("#departmentIdd").val(departmentId);
		if(encryptFormData('updateStatusForm', tableOrTbodyIDS,tableOrTbodyKeys)){
		$("#updateStatusForm").submit();
		}else{
			bootbox.alert('Something error happens');
		}
	
	 
}


document.addEventListener('DOMContentLoaded', function() {
    var elements = document.getElementsByClassName('capitalizeEachLetter');
    for (var i = 0; i < elements.length; i++) {
        elements[i].addEventListener('input', function() {
            this.value = capitalizeEachLetter(this.value);
        });
    }
});

function capitalizeEachLetter(value) {
    return value.toUpperCase();
}


function objectAccoudingToTheObjectType() {
	debugger
    var objectType = document.getElementById("objectType").value;
    var municipalityField = document.getElementById("municipalityField");
    var governingBodyField = document.getElementById("governingBodyField");

    if (objectType === "MUN") {
        municipalityField.style.display = "block";
        governingBodyField.style.display = "none";
    } else if (objectType === "GOV") {
        municipalityField.style.display = "none";
        governingBodyField.style.display = "block";
    } else {
        municipalityField.style.display = "none";
        governingBodyField.style.display = "none";
    }
}
</script>


