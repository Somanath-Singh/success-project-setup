<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="userDetails" value="${serviceOutcome.data}" />
<script type="text/javascript" src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<script src="${contextPath}/assets/appJs/validation/commonMaster.js"></script>
<style>
.freeze {
	pointer-events: none;
}
</style>
<div class="breadcrumb_conatiner">
	<div class="col-md-6">
		<h4 class="change-color">Add Maintenance</h4>
	</div>
	<div class="col-md-6">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${contextPath}/home"><i
						class="fa-solid fa-house"></i></a></li>
				<li class="breadcrumb-item active" aria-current="page">Add Maintenance</li>
			</ol>
		</nav>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="card">
			<div class="card-header">
				<h5 class="card-title">Add Maintenance</h5>
			</div>
			<div class="card-body">
				<form class="form-horizontal" action="${contextPath}/maintenance/saveMaintenanceDetails" method="POST" id="saveMaintenance" >
					<input type="hidden" id="cipherText" name="cipherText" value="">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="maintenceId" id="maintenceId" value="${maintenance.maintenceId}">

					<div class="row">
						<div class="form-group col-md-3">
							<label class="required">Maintenance Name :</label>
							<div class="col-md-12">
								<input type="text" id="maintenceNameId" name="maintenceName"
									placeholder=" " class="form-control form-control-sm" required
								    value="${maintenance.maintenceName}" onchange="getmaintenceName(this.value)" maxlength="250">
							</div>
						</div>
						
						<div class="form-group col-md-3">
							<label class="required">Maintenance Code :</label>
							<div class="col-md-12">
								<input type="text" id="maintenceCodeId" name="maintenceCode"
									placeholder=" " class="form-control form-control-sm" required
									value="${maintenance.maintenceCode}" onchange="getmaintenceCode(this.value)" maxlength="250">
							</div>
						</div>

					<div class="col-md-3 mt-4">
					  <input type="button" name="add&ManageApplicationScheme" value="${maintenance.maintenceId eq null ? 'Save' : 'Update'}" id="add&ManageApplicationSchemes" class="btn btn-primary btn-sm"  onclick="submitForm('${maintenance.maintenceId eq null ? 'SAVE' : 'UPDATE'}')">
					<a href="${contextPath}/home" type="button" class="btn btn-warning btn-sm">Back</a> 
                     </div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="col-md-12 mt-3">
     <div class="card">
		<div class="card-header">
			<h6 class="card-title">Maintenance List</h6>
		</div>
		<div class="card-body">
			<div class="table-rrsponsive">
				<table class="datatable table table-striped table-bordered exportbtn" id="tableId1">
					<thead>
						<tr>
							<th style="min-width: 20px;" class="text-center">Sl.No.</th>
				            <th style="min-width: 300px;">Maintenance Name</th>
				            <th style="min-width: 300px;">Maintenance Code</th>
				    	    <th style="min-width: 30px;text-align: center;">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${masterList}" var="mas" varStatus="count">
							<tr>
								<td class="text-center">${count.count}</td>
								<td>${mas.maintenceName}</td>
								<td>${mas.maintenceCode}</td>
								<td class="text-center">
									<button class="btn btn-warning btn-sm" id="editBtn" data-maintenace-id="${mas.maintenceId}"
										data-toggle="tooltip" onclick="editMaintenanceMasterForm(${mas.maintenceId})" title="Edit">
										<i class="fas fa-edit"></i>
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

<script>

function submitForm(status){
	
	if($("#maintenceNameId").val().trim() == ""){
		bootbox.alert("Please enter maintenance Code..");
		$("#maintenceNameId").val('');
		return false;
	}
	if ($("#maintenceCodeId").val().trim() == "") {
		bootbox.alert("Please enter Maintenance Name..");
		$("#maintenceCodeId").val('');
		return false;
	}
	else{
		//submit tha form
		var message = (status == 'Save' ? 'save' : 'update');
		var bizContent = $("#saveMaintenance").serializeArray();
		$("#cipherText").val(enc_password(JSON.stringify(convertFormToJSONArray(bizContent))));
		bootbox.confirm("Do you want to " + message + " Maintenance Detials ?", function(result) {
			if (result) {
				showLoader();
				$("#saveMaintenance").submit();
			}
		});
	}
}
	
function getmaintenceCode(name){
	var maintenceId = $("#maintenceId").val() || ''; 
   $.ajax({
	type : "GET",
	url : "./getMaintenceCodeById",
	data: {
		"maintenceCode":name,
		"maintenceId":maintenceId,
	},
	success : function(response) {
		var data = response;
	    if(data == 'yes')
			{
			bootbox.alert("Duplicate Maintenance Code is not allowed.");
			$("#maintenceCodeId").val('');
			}
	    else{
	    	
	    }
		},error : function() {
			alert("ERROR");
		}
});
}

function getmaintenceName(name) {
    var maintenceId = $("#maintenceId").val() || ''; // Add this if you're editing and want to exclude current ID

    $.ajax({
        type: "GET",
        url: "./getMaintenceNameById",
        data: {
            "maintenceName": name,
            "maintenceId": maintenceId
        },
        success: function(response) {
            if (response === 'yes') {
                bootbox.alert("Duplicate Maintenance Name is not allowed.");
                $("#maintenceNameId").val('');
            }
        },
        error: function() {
            alert("ERROR");
        }
    });
}

function convertFormToJSONArray(form) {
	const array = form;
	const json = {};
	$.each(array, function() {
		var contentCheck = this.name;
		if (json[this.name]) {
			if (!json[this.name].push) {
				json[this.name] = [ json[this.name] ];
			}
			json[this.name].push(this.value || "");
		} else if (contentCheck.includes("Array")) {
			json[this.name] = [ this.value ];
		} else {
			json[this.name] = this.value || "";
		}
	});
	return json;
}
  
function showLoader() {
	$(".loader-div").css("display", "flex");
}

function hideLoader() {
	$(".loader-div").css("display", "none");
}

function editMaintenanceMasterForm(id) {
	  $("#maintenceEditId").val(id);
	  showLoader();
	  var bizContent = $("#editForm").serializeArray();
	  $("#editCipherText").val(enc_password(JSON.stringify(convertFormToJSONArray(bizContent))));
	  $("#editForm").submit();
}

</script>

<form action="${contextPath}/maintenance/editMaintenance" method="Post" id="editForm">
 <input type="hidden" id="editCipherText" name="cipherText">
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
 <input type="hidden" name="maintenceId" id="maintenceEditId" />
</form>
