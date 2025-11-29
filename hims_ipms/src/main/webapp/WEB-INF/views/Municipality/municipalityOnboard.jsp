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
<style>
label.required {
    color: black; /* Ensures label text is black */
}

label.required::after {
    content: " *";
    color: red;
}
button.btn-cancel.btn-danger{
	padding: 5px 20px;
    border-radius: 8px;
    border-style: none;
}
.tableMargin{
	vertical-align: middle !important;
}
 .tableMargin td p{
	margin-bottom: 0;
}
</style> 
<div class="breadcrumb_conatiner">
	<div class="col-md-6">
		<h4 class="change-color">On Board Municipality</h4>
	</div>
	<div class="col-md-6">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">

				<li class="breadcrumb-item"><a href="${contextPath}/home"><i class="fa fa-home"></i></a></li>

				<li class="breadcrumb-item active" aria-current="page">On Board Municipality</li>
			</ol>
		</nav>
	</div>
</div>
<div class="row">

	<div class="col-md-12">

		<div class="card">
			<div class="card-header">
	
	
	<h5 class="card-title">On Board Municipality</h5>
	</div>
	<div class="card-body">

    <form action="${contextPath}/municipalities/save" method="post" id="saveFormId" class="row" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="hidden" name="municipalityId" value="${municipality.municipalityId}" id="municipalityId" />

    

        <%@ include file="/WEB-INF/views/entityIdAndUserLevelListForMst.jsp"%>

        <div class="form-group col-md-4">
            <label class="required">Municipality Name</label>
            <input type="text" class="form-control form-control-sm require AlphabetsOnly" id="municipalityName" name="municipalityName" maxlength="200" value="${municipality.municipalityName}">
        </div>

        <div class="form-group col-md-2">
            <label class="required">Municipality Code</label>
            <input type="text" class="form-control form-control-sm require AlphaNumericOnly" id=municipalityCode name="municipalityCode" maxLength="20" value="${municipality.municipalityCode}">
        </div>

        <div class="form-group col-md-2">
            <label class="required">District</label>
            <select class="form-control form-control-sm require"
                    name="district" id="district"">
            <option value="">--select--</option>
            <c:forEach items="${data.districties}" var="district">
                <option value="${district.districtId}"${municipality.district.districtId eq district.districtId?'selected':''}>${district.districtName}</option>
            </c:forEach>
            </select> </div>
        
        <div class="form-group col-md-2">
            <label class="required">Remarks</label>
            <input type="text" class="form-control form-control-sm require AlphaNumericOnly" id="remarks" name="remarks" maxLength="250" value="${municipality.remarks}" >
        </div>
        
        <div class="form-group col-md-2">
            <label class="required">Address</label>
            <input type="text" class="form-control form-control-sm require AlphaNumericOnly" id="addressLine" name="addressLine" maxLength="20" value="${municipality.addressLine}" >
        </div>
        <div class="form-group col-md-2">
            <label class="required">Pincode</label>
            <input type="text" class="form-control form-control-sm require NumbersOnly validPincode6Digit" id="pincode" name="pincode" maxLength="6" value="${municipality.pincode}" >
        </div>
        <div class="form-group col-md-2">
            <label class="required">Mobile No</label>
            <input type="text" class="form-control form-control-sm require NumbersOnly" id="phoneNo" name="phoneNo" maxLength="10" value="${municipality.phoneNo}" onblur="validatePhoneNumber(this)" >
        </div>
        
        <div class="form-group col-md-2">
            <label class="required">Email</label>
            <input type="text" class="form-control form-control-sm require " id="emailId" name="emailId" maxLength="100" value="${municipality.emailId}" onblur="validateEmail(this)"  >
        </div>
        
        <div class="form-group col-md-2">
            <label class="">Website</label>
            <input type="text" class="form-control form-control-sm AlphaNumericOnly" id="website" name="website" value="${municipality.website}" onblur="validateWebsite(this)" >
        </div>
         <div class="form-group col-md-2">
            <label class="">Modules</label>
             <select id="moduleIds" name="moduleIds" class="form-control selectpicker" multiple required>
				<option value="">--select--</option>
				<c:forEach items="${data.modulList}" var="modul">
					<option value="${modul.id}"
						<c:forEach items="${municipality.moduleIds}" var="moduleId"> ${modul.id  eq moduleId ? 'selected' : ''}
				         </c:forEach>>
				    ${modul.moduleName} (${modul.moduleCode})
				    </option>
				</c:forEach>

			</select>
		</div>

        <div class="form-group col-md-3">
			<label class="required" >Roles Belongs to This Entity</label>
			<div class="col-md-12">
				<select class="form-control form-control-sm selectpicker requiredFieldSelectpicker" name="otherRoleCodes" multiple data-live-search="true" id="otherRoleCodes">
					<option value="" disabled>Select Role</option>
					<c:forEach items="${roleList}" var="role">
						<!-- role code contain in entityMapList then selected -->
						<option value="${role.roleCode}" <c:if test="${entityMapList.contains(role.roleCode)}">selected</c:if>>${role.displayName}</option>
					</c:forEach>
				</select>
			</div>
		</div>


        <!-- Primary Role -->
		<div class="form-group col-md-2">
			<label class="required" >Primary Role</label>
			<div class="col-md-12">
				<select class="form-control form-control-sm selectpicker thisRequiredField" name="primaryRoleCode" onchange="removeThisRoleFromOtherRoles(this)" id="primaryRoleCodeForId" data-live-search="true">
					<option value="" disabled selected>Select Role</option>
				</select>
			</div>
		</div>

        <!-- Upload Icon -->
        <div class="form-group col-md-2">
            <label class="">Upload Icon</label><c:if test="${!empty municipality.icon}"><a href="${contextPath}/view-documents?doc=${municipality.icon}&module=ICON" target="_blank" title="Download Icon"><i class="fa fa-download" style="margin-left:10px;"></i></a></c:if>
            <input type="file" class="form-control form-control-sm" id="icon" name="icon" accept="image/*" >
        </div>
		
        <div class="form-group col-md-12 text-center" style="margin-top: 7px;">
            <button type="button" style="line-height: 22px;" class="btn-submit" onclick="submitForm()">
                ${empty municipality.municipalityId ? 'Submit' : 'Update'}
            </button>
            <button type="button" class="btn btn-warning" style="line-height:18px;" onclick="history.back()">Back</button>
        </div>
    </form>
</div> 
</div>
</div>
</div>

<div class="cardcontainer cardContainerNotFirst mt-2">
	 <div class="row">

	<div class="col-md-12">

		<div class="card">
			<div class="card-header">
	
	
	<h5 class="card-title">View Municipality</h5>
	</div>
	<div class="card-body ">
      <%@ include file="/WEB-INF/views/entityIdAndUserLevelListDrpForMst.jsp"%>
		<table class="datatable table table-striped table-bordered exportbtn mt-5">
	    	<thead>
		        <tr>
		            <th>Sl.No</th>
		            <th>Municipality Name</th>
		            <th>Municipality Code</th>
		            <th>District Name</th>
		            <th>Remarks</th>
		            <th>Action</th>
		            
		        </tr>
	    	</thead>
	    	<tbody class="tableMargin">
		        <c:forEach items="${data.municipalities}" var="municipality" varStatus="count">
		            <tr>
		                <td>${count.count}</td>
		                <td>${municipality.municipalityName}</td>
		                 <td>${municipality.municipalityCode}</td>
		                <td>${municipality.district.districtName}</td>
		                 <td>${municipality.remarks}</td>
		                <td><a href="#" onclick="editMunicipality('${municipality.municipalityId}')" class="btn btn-warning btn-xs" title="Edit">
                                    <i class="fa fa-edit"></i>
                                </a>
                                <button type="button" class="btn ${!municipality.isActive ? 'btn-danger' : 'btn-success'} btn-xs" data-toggle="tooltip" onclick="updateStatus(${municipality.municipalityId},${municipality.isActive ?false:true})" title="${municipality.isActive ? 'Deactivate' : 'Activate'}">
                                    <i class="${!municipality.isActive ? 'fa fa-lock' : 'fa fa-unlock'}"></i>
                                </button></td>
		                
		            </tr>
		        </c:forEach>
	    	</tbody>
		</table>
	</div>
</div>
</div>
</div>
</div>

<form action="${contextPath}/municipalities/edit" method="get" id="editForm">
    <input type="hidden" name="municipalityIdHd" id="municipalityIdHd" />
</form>

<form action="${contextPath}/municipalities/update-status" method="post" id="updateStatusForm">   
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
    <input type="hidden" id="municipalityIdd" name="municipalityIdd"/>
    <input type="hidden" name="status" id="isActiveStatus" />  
</form>

<script>
function submitForm(){
    let primaryRoleCodeForId = $('#primaryRoleCodeForId').val();
    if(primaryRoleCodeForId == ''){
        bootbox.alert('Primary Role is required');
        return false;
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

function editMunicipality(municipalityId){
	if(municipalityId==''){
		bootbox.alert('muncipality id empty');	
	}else{
		$('#municipalityIdHd').val(municipalityId);
		if(encryptFormData('editForm', tableOrTbodyIDS,tableOrTbodyKeys)){
			$('#editForm').submit();
		}else{
			bootbox.alert('Something error happens');
		}
		
	}
	
}
function updateStatus(municipalityId,isActive){
	debugger
	
		$("#isActiveStatus").val(isActive);
		$("#municipalityIdd").val(municipalityId);
		if(encryptFormData('updateStatusForm', tableOrTbodyIDS,tableOrTbodyKeys)){
		$("#updateStatusForm").submit();
		}else{
			bootbox.alert('Something error happens');
		}
	
	 
}

$(document).ready(function() {
    updatePrimaryRoleOptions();
    $(document).on('change', '.requiredFieldSelectpicker', function() {
        updatePrimaryRoleOptions();
    });
});

function updatePrimaryRoleOptions() {

// roleDetails.primaryRoleCode is already selected then no need to update
// get selected values
let selectedValues = $('.requiredFieldSelectpicker').val();
if (selectedValues != null && selectedValues != '' && selectedValues != undefined) {
	let valuesArr = [];
	// value and text
	selectedValues.forEach(function(value) {
		$('#otherRoleCodes option').each(function() {
			if ($(this).val() == value) {
				let obj = {
					value: $(this).val(),
					text: $(this).text()
				};
				valuesArr.push(obj);
			}
		});
	});
	

	// append this values to primary role
	let html = '';
	html = '<option value="" disabled selected>Select Role</option>';
	let isDesabled = false;
	valuesArr.forEach(function(item) {
		let isSelect = '';
		if('${roleDetails.primaryRoleCode}' == item.value){
			isSelect = 'selected';
			isDesabled = true;
		}
		html += '<option value="' + item.value + '" '+isSelect+'>' + item.text + '</option>';
	});

	
	if(isDesabled){
		// add class to disable
		$('#primaryRoleCodeForId').addClass('readOnlySelect');
		// in otherRoleCodes which are selected then that readonly by adding class
		$('#otherRoleCodes option').each(function() {
			if ($(this).is(':selected')) {
				$(this).addClass('readOnlySelect');
			}
		});
	}

	$('#primaryRoleCodeForId').empty().append(html);
	// refresh selectpicker
	$('#primaryRoleCodeForId').selectpicker('refresh');
	// refresh selectpicker
	$('#otherRoleCodes').selectpicker('refresh');
}
}

</script>


