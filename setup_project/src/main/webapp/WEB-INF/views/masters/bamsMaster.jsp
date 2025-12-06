<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<script src="${contextPath}/assets/appJs/validation/commonMaster.js"></script>


<!-- Breadcrumb -->
<div class="breadcrumb_conatiner">
    <div class="col-md-6"><h4 class="change-color">Add BAMS Ward</h4></div>
    <div class="col-md-6">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="${contextPath}/home"><i class="fa fa-home"></i></a>
                </li>
                <li class="breadcrumb-item active">Add BAMS Ward</li>
            </ol>
        </nav>
    </div>
</div>

<!-- Card Form -->
<div class="card">
    <div class="card-header">
        <c:choose>
            <c:when test="${not empty bams}">
                <h5 class="card-title">Update BAMS Ward</h5>
            </c:when>
            <c:otherwise>
                <h5 class="card-title">Add BAMS Ward</h5>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="card-body">
        <form action="${contextPath}/bams/save" id="bamsmasterForm" method="post">
            <input type="hidden" id="cipherText" name="cipherText">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" id="wardId" name="wardId" value="${bams.wardId}"/>

            <div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label class="required">District :</label> <select id="districtId"
							name="districtId" class="form-control form-control-sm form-select" onchange="LoadMuniciplityByDistrict(this.value);">
							<option value="">- Select -</option>
							<c:forEach var="district" items="${districtList}">
								<option value="${district.districtId}"
									${bams.districtId == district.districtId ? 'selected' : ''}>
									${district.districtName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label class="required">Municipality :</label> <select
							id="municipalityId" name="municipalityId" class="form-control form-control-sm form-select">
							<option value="">- Select -</option>
							<c:forEach var="municipality" items="${municipalityList}">
								<option value="${municipality.municipalityId}"
									${bams.municipalityId == municipality.municipalityId ? 'selected' : ''}>
									${municipality.municipalityName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				                
					<div class="col-md-3">
						<div class="form-group">
							<label class="required">Ward Name :</label> <input type="text" id="wardName" 
								name="wardName" maxlength="25" value="${bams.wardName}"
								class="form-control form-control-sm disablecopypaste"/>
						</div>
					</div>
					
					<div class="col-md-3">
						<div class="form-group">
							<label class="required">Pin Code :</label> <input type="text" id="pinCode"
								name="pinCode" maxlength="7"  value="${bams.pinCode}"
								class="form-control form-control-sm NumbersOnly disablecopypaste"/>
						</div>
					</div>
            </div>

            <!-- Buttons -->
            <div class="text-center mt-4">
                <c:choose>
                    <c:when test="${not empty bams}">
                        <input type="button" value="Update" class="btn btn-success btn-sm" onclick="submitBamsWardData()">
                        <a href="${contextPath}/bams/add" class="btn btn-warning btn-sm">Back</a>
                    </c:when>
                    <c:otherwise>
                        <input type="button" value="Submit" class="btn btn-success btn-sm" onclick="submitBamsWardData()">
                        <a href="${contextPath}/home" class="btn btn-warning btn-sm">Back</a>
                        <button type="reset" class="btn btn-danger btn-sm">Reset</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </div>
</div>

<!-- BAMS ward List -->
<div class="col-md-12 mt-3">
        <div class="card">
            <div class="card-header"><h5 class="card-title">BAMS Ward List</h5></div>
            <div class="card-body">
                <div class="table-responsive">
                    <table id="basic-datatables" class="table table-bordered table-hover exportbtn">
                        <thead>
                        <tr>
                            <th class="text-center" style="width:60px">Sl.No</th>
                            <th>Ward Name</th>
                            <th>Ward Code</th>
<!--                              <th>Lgd Code</th>
 -->                        <th>Pin Code</th>
                            <th>District</th>
                            <th>Municipality</th>
                             <th class="text-center">Status</th>
                            <th class="text-center">Action</th>
                        </tr>
                        </thead>
                        <tbody>
							<c:forEach items="${bamsWardList}" var="bamsWard"
								varStatus="count">
								<tr>
									<td class="text-center">${count.count}</td>
									<td>${bamsWard.wardName}</td>
									<td>${bamsWard.wardCode}</td>
<%-- 									<td>${bamsWard.lgdCode}</td>
 --%>								<td>${bamsWard.pinCode}</td>
									<td>${bamsWard.districtName}</td>
									<td>${bamsWard.municipalityName}</td>

									<td class="text-center">
										<button type="button"
											class="btn btn-sm toggleStatusBtn ${bamsWard.isActive ? 'btn-success' : 'btn-danger'}"
											onclick="toggleWardStatus(${bamsWard.wardId}, ${bamsWard.isActive})">
											<i class="fa ${bamsWard.isActive ? 'fa-unlock' : 'fa-lock'}"></i>
										</button>
									</td>

									<!-- Action Buttons -->
									<td class="text-center">
										<button class="btn btn-warning btn-sm"
											onclick="editBamsWardForm(${bamsWard.wardId})" title="Edit">
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
<form action="${contextPath}/bams/edit" method="post" id="editForm">
    <input type="hidden" id="editCipherText" name="cipherText">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" id="bamsWardId" name="wardId" />
    
</form>
<!-- Scripts -->
<script>
function submitBamsWardData() {
	debugger;
    var districtId = $("#districtId").val();
    var municipalityId = $("#municipalityId").val();
    var wardName = $("#wardName").val();
    var pinCode = $("#pinCode").val();

     if(districtId == "" || districtId === "0") {
        bootbox.alert("Please select District.");
        return false;
    } else if(municipalityId == "" || municipalityId === "0") {
        bootbox.alert("Please select Municipality.");
        return false;
    } else if(wardName == "") {
        bootbox.alert("Please Enter Ward Name.");
        return false;
    }else if(pinCode == "") {
        bootbox.alert("Please Enter Pin Code.");
        return false;
    }
    else {
    	submitBAMSEncryptForm();
    }
}




function submitBAMSEncryptForm() {
    var bizContent = $("#bamsmasterForm").serializeArray();
    $("#cipherText").val(enc_password(JSON.stringify(convertFormToJSONArray(bizContent))));
    bootbox.confirm("Do You Want To Continue?", function(result) {
        if (result) {
            showLoader();
            $("#bamsmasterForm").submit();
        }
    });
}

    
    function LoadMuniciplityByDistrict(distId) {
    	debugger;
        $.ajax({
            type: "GET",
            url: "${contextPath}/common/getMuniciplaityByDistrict",
            data: { districtId: distId },
            success: function (municipality) {
                var municiplaity = $('#municipalityId');
                municiplaity.empty();
                municiplaity.append('<option value="" selected>- Select -</option>');
                $.each(municipality, function (index, municipalityData) {
                	municiplaity.append('<option value="' + municipalityData.municipalityId + '">' + municipalityData.municipalityName + '</option>');
                });
            },
            error: function (error) {
                console.error("Error fetching municipality:", error);
            }
        });
    }

    
    function toggleWardStatus(wardId, status) {
    	debugger;
        let msg = status === true 
            ? "Do you want to In-Activate this BAMS Ward?" 
            : "Do you want to Activate this BAMS Ward ?";

        bootbox.confirm(msg, function (result) {
            if (result) {
                showLoader();
                $.ajax({
                    type: "GET",
                    url: "${contextPath}/bams/toggle-status",
                    data: { 
                    	wardId: wardId 
                    	},
                    success: function (isActive) {
                       hideLoader();
                        var button = $("button[onclick*='toggleWardStatus(" + wardId + ",']");
                        if (isActive === true) {
                            button.removeClass("btn-danger").addClass("btn-success");
                            button.html('<i class="fa fa-unlock"></i>');
                            button.attr("onclick", "toggleWardStatus(" + wardId + ", true)");
                        } else if (isActive === false) {
                            button.removeClass("btn-success").addClass("btn-danger");
                            button.html('<i class="fa fa-lock"></i>'); 
                            button.attr("onclick", "toggleWardStatus(" + wardId + ", false)");
                        } else {
                            bootbox.alert("Something went wrong while updating BAMS Ward status.");
                        }
                    },
                    error: function (error) {
                        hideLoader();
                        console.error("Error toggling BAMS Ward status:", error);
                        bootbox.alert("Server error occurred. Please try again.");
                    }
                });
            }
        });
    }
    
    
    function editBamsWardForm(id) {
    	debugger;
        $("#bamsWardId").val(id);
         var bizContent = $("#editForm").serializeArray();
        $("#editCipherText").val(enc_password(JSON.stringify(convertFormToJSONArray(bizContent))));
        bootbox.confirm("Do You Want To Continue?", function(result) {
            if (result) {
                showLoader();
                $("#editForm").submit();
            }
        });      
      
    }
    
</script>

