<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<script src="${contextPath}/assets/appJs/validation/commonMaster.js"></script>
<sec:authentication var="principal" property="principal" />
<c:set var="roleCode" value="${principal.primaryRole.roleCode}"/>
<style>
label.required {
    color: black;
}

label.required::after {
    content: " *";
    color: red;
}
.readOnlySelect {
	pointer-events: none;
		background-color: #868181 !important;
}

</style>
<div class="breadcrumb_conatiner">
	<div class="col-md-6">
		<h4 class="change-color">${forMunicipality ? 'Governing-Body' : 'Corporate'} OnBoarding</h4>
	</div>
	<div class="col-md-6">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">

				<li class="breadcrumb-item"><a href="${contextPath}/home"><i class="fa fa-home"></i></a></li>

				<li class="breadcrumb-item active" aria-current="page">${forMunicipality ? 'Governing-Body' : 'Corporartion'} OnBoarding</li>
			</ol>
		</nav>
	</div>
</div>
 <div class="cardcontainer">
    
    <div class="row">

	<div class="col-md-12">

		<div class="card">
			<div class="card-header">
	
	
	<h5 class="card-title">${forMunicipality ? 'Governing-Body' : 'Corporate'} OnBoarding</h5>
	</div>
	<div class="card-body">

    <form action="${contextPath}/govern/save" method="post" id="saveFormId" class="row" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="hidden" name="governingBodyId" value="${governingBody.governingBodyId}" id="governingBodyId" />

        <div class="form-group col-md-3">
            <label class="required">${forMunicipality ? 'Governing-Body' : 'Corporartion'} Name :</label>
            <input type="text" class="form-control form-control-sm require AlphabetsOnly" id="governingBodyName" name="governingBodyName" maxlength="200" value="${governingBody.governingBodyName}">
        </div>

        <div class="form-group col-md-3">
            <label class="required">${forMunicipality ? 'Governing-Body' : 'Corporartion'} Code :</label>
            <input type="text" class="form-control form-control-sm require AlphaNumericOnly" id=governingBodyCode name="governingBodyCode" maxLength="20" value="${governingBody.governingBodyCode}">
        </div>
        
      
        
        <div class="form-group col-md-3">
            <label >Remarks :</label>
            <input type="text" class="form-control form-control-sm" id="remarks" name="remarks" maxLength="250" value="${governingBody.remarks}" >
        </div>
        
        
        <div class="form-group col-md-3">
            <label class="required">Address :</label>
            <input type="text" class="form-control form-control-sm require " id="addressLine" name="addressLine" maxLength="200" value="${governingBody.addressLine}" >
        </div>
        <div class="form-group col-md-3">
            <label class="required">Pin code</label>
            <input type="text" class="form-control form-control-sm require NumbersOnly validPincode6Digit" id="pincode" name="pincode" maxLength="6" value="${governingBody.pincode}" >
        </div>
        <div class="form-group col-md-3">
            <label class="required">Mobile No. :</label>
            <input type="text" class="form-control form-control-sm require NumbersOnly" id="phoneNo" name="phoneNo" maxLength="10" value="${governingBody.phoneNo}" onchange="validatePhoneNumber(this)" >
        </div>
        
        <div class="form-group col-md-3">
            <label class="required">Email :</label>
            <input type="text" class="form-control form-control-sm require " id="emailId" name="emailId" maxLength="100" value="${governingBody.emailId}" onchange="return checkEmail(this)" <c:if test="${!empty governingBody.emailId}">readonly</c:if> />
        </div>
        
        <div class="form-group col-md-3">
            <label class="">Website :</label>
            <input type="text" class="form-control form-control-sm " id="website" name="website" value="${governingBody.website}" onchange="validateWebsite(this)" >
        </div>
        
        
        <div class="form-group col-md-3">
            <label class="">Modules :</label>
             <select id="moduleIds" name="moduleIds" class="form-control selectpicker" multiple required data-live-search="true">
				<option value="" disabled>--select--</option>
				<c:forEach items="${data.modulList}" var="modul">
					<option value="${modul.id}"
						<c:forEach items="${governingBody.moduleIds}" var="moduleId"> ${modul.id  eq moduleId ? 'selected' : ''}
				         </c:forEach>>
				    ${modul.moduleName} (${modul.moduleCode})
				    </option>
				</c:forEach>

			</select>
		</div>

		
		<div class="form-group col-md-3">
			<label class="required" >Roles Belongs to This Entity :</label>
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
		<div class="form-group col-md-3">
			<label class="required" >Primary Role :</label>
			<div class="col-md-12">
				<select class="form-control form-control-sm selectpicker thisRequiredField" name="primaryRoleCode" onchange="removeThisRoleFromOtherRoles(this)" id="primaryRoleCodeForId" data-live-search="true">
					<option value="" disabled selected>Select Role</option>
				</select>
			</div>
		</div>
		

        <!-- Upload Icon -->
        <div class="form-group col-md-3">
            <label class="">Upload Icon :</label>
            <c:if test="${!empty governingBody.icon}"><a href="${contextPath}/view-documents?doc=${governingBody.icon}&module=ICON" target="_blank" class="" title="Download Icon"><i class="fa fa-download"></i></a></c:if>
            <input type="file" class="form-control form-control-sm" id="icon" name="icon" accept="image/*" onchange="onlyImage(this);">
        </div>
        
        
        
        <div class="form-group col-md-12 text-center mt-2" >
            <button type="button" class="btn btn-submit" onclick="submitForm()">
                ${empty governingBody.governingBodyId ? 'Submit' : 'Update'}
            </button>
            <button type="button" class="btn btn-cancel btn-warning" onclick="backToModule()">Back</button>
        </div>
        
       
        
    </form>
</div> 
</div> 
</div> 
</div> 
</div>


<div class="cardcontainer cardContainerNotFirst mt-3">
<div class="row">

	<div class="col-md-12">

		<div class="card">
			<div class="card-header">
	
	
	<h5 class="card-title">View ${forMunicipality ? 'Municipalities' : 'Corporartions'}</h5>
	</div>
	<div class="card-body ">
		<table class="datatable table table-striped table-bordered exportbtn mt-5">
	    	<thead>
		        <tr>
		            <th class="text-center">Sl.No</th>
		            <th>${forMunicipality ? 'Governing-Body' : 'Corporartion'} Name</th>
		            <th>${forMunicipality ? 'Governing-Body' : 'Corporartion'} Code</th>
		            <th>Email</th>
		            <th class="text-center">Action</th>
		        </tr>
	    	</thead>
	    	<tbody class="tableMargin">
		        <c:forEach items="${data.governingBodies}" var="govern" varStatus="count">
		            <tr>
		                <td class="text-center">${count.count}</td>
		                <td>${govern.governingBodyName}</td>
		                 <td>${govern.governingBodyCode}</td>
		                 <td>${govern.emailId}</td>
		                <td class="text-center"><a href="#" onclick="editGoverningBody('${govern.governingBodyId}')" class="btn btn-warning btn-sm" title="Edit">
                                    <i class="fa fa-edit"></i>
                                </a>
                                <button type="button" class="btn ${!govern.isActive ? 'btn-danger' : 'btn-success'} btn-xs" data-toggle="tooltip" onclick="updateStatus(${govern.governingBodyId},${govern.isActive ?false:true})" title="${govern.isActive ? 'Deactivate' : 'Activate'}">
                                    <i class="${!govern.isActive ? 'fa fa-lock' : 'fa fa-unlock'}"></i>
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

<form action="${contextPath}/govern/edit" method="get" id="editForm">
    <input type="hidden" name="governingBodyIdHd" id="governingBodyIdHd" />
</form>

<form action="${contextPath}/govern/update-status" method="post" id="updateStatusForm">   
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
    <input type="hidden" id="governingBodyIdd" name="governingBodyIdd"/>
    <input type="hidden" name="status" id="isActiveStatus" />  
</form>

<script>

    // on change on image will show the image .png, .jpg, .jpeg, .gif
//     $(document).on('change', '#icon', function() {
//         let imageType = ['image/png', 'image/jpg', 'image/jpeg', 'image/gif'];
//         let file = this.files[0];
//         if (imageType.indexOf(file.type) == -1) {
//             bootbox.alert('Please select valid image file');
//             // clear the file
//             $(this).val('');
//             return false;
//         }
//     });

function submitForm(){

	let modules = $('#moduleIds').val();
	if(modules == null || modules == '' || modules == undefined || modules.length == 0){
		bootbox.alert('Atleast one module is required');
		return false;
	}

	let otherRoleCodes = $('#otherRoleCodes').val();
	if(otherRoleCodes == null || otherRoleCodes == '' || otherRoleCodes == undefined || otherRoleCodes.length == 0){
		bootbox.alert('Roles Belongs to This Entity is required');
		return false;
	}


    let primaryRoleCodeForId = $('#primaryRoleCodeForId').val();
    if(primaryRoleCodeForId == '' || primaryRoleCodeForId == null || primaryRoleCodeForId == undefined){
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

function editGoverningBody(governingBodyId){
	if(governingBodyId==''){
		bootbox.alert('governingBody id empty');	
	}else{
		$('#governingBodyIdHd').val(governingBodyId);
		if(encryptFormData('editForm', tableOrTbodyIDS,tableOrTbodyKeys)){
			$('#editForm').submit();
		}else{
			bootbox.alert('Something error happens');
		}
		
	}
	
}
function updateStatus(governingBodyId,isActive){
	debugger
	
		$("#isActiveStatus").val(isActive);
		$("#governingBodyIdd").val(governingBodyId);
		if(encryptFormData('updateStatusForm', tableOrTbodyIDS,tableOrTbodyKeys)){
		$("#updateStatusForm").submit();
		}else{
			bootbox.alert('Something error happens');
		}
}

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

$(document).ready(function() {
    updatePrimaryRoleOptions();
    $(document).on('change', '.requiredFieldSelectpicker', function() {
        updatePrimaryRoleOptions();
    });
});

// checkEmail(this)
async function checkEmail(that){
	let validEmail = validateEmail($(that).val());
	if (!validEmail) {
		bootbox.alert('Please enter valid email');
		$(that).val('');
		return false;
	}
	let email = $(that).val();
	if(email != '' && email != null && email != undefined){
		let url = "${contextPath}/govern/check-duplicate-email";
		let data = {
			"encodedData" : btoa(JSON.stringify(email))
		};
		let method = "GET";
		let response = await asyncAjaxForData(url, data, method);
		if(response){
			bootbox.alert("Email already exists");
			$(that).val('');
		}
		
	}
}


</script>


