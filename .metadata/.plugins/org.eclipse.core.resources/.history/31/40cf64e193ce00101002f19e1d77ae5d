<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link href="${contextPath}/assets/css/dashboard.css" rel="stylesheet" type="text/css" />
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>

<div class="breadcrumb_conatiner">
    <div class="col-md-6">
        <h4 class="change-color">Add Agency Registration</h4>
    </div>
    <div class="col-md-6">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${contextPath}/home"><i class="fa fa-home"></i></a></li>
                <li class="breadcrumb-item active">Add Agency Registration</li>
            </ol>
        </nav>
    </div>
</div>

<div class="card">
    <div class="card-header">
        <c:choose>
            <c:when test="${not empty agencyRegistration}">
                <h5 class="card-title">Update Agency Registration</h5>
            </c:when>
            <c:otherwise>
                <h5 class="card-title">Add Agency Registration</h5>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="card-body">
        <form action="${contextPath}/agencyRegistration/save" id="agencyRegistrationForm" method="post">
            <input type="hidden" id="cipherText" name="cipherText">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" id="agnRegId" name="agnRegId" value="${agencyRegistration.agnRegId}" />
            <input type="hidden" id="isActive" name="isActive" value="${agencyRegistration.isActive}" />
            <input type="hidden" id="typeId" name="typeId" value="${agencyRegistration.typeId}" />

            <div class="row">
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="smallInput required" for="agencyTypeId">Agency Type :</label>
                        <div>
                            <select class="form-control form-control-sm form-select" id="agencyTypeId" name="agencyTypeId" onchange="getAgencyTemplatesByType(this.value)">
                                <option value="0" selected disabled>- Select -</option>
                                <c:forEach items="${agencyTypeList}" var="type">
                                    <option value="${type.typeId}" ${type.typeId eq agencyRegistration.typeId ? 'selected' : ''}>${type.typeNameEn}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="smallInput required" for="agnMstId">Agency Template :</label>
                        <div>
                            <select class="form-control form-control-sm form-select" id="agnMstId" name="agnMstId">
                                <option value="0" selected disabled>- Select -</option>
                                <c:if test="${not empty agencyRegistration}">
                                    <c:forEach items="${agencyTemplateList}" var="template">
                                        <option value="${template.agencyId}" ${template.agencyId eq agencyRegistration.agnMstId ? 'selected' : ''}>${template.agencyNameEn}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-md-12 required">Agency Name :</label>
                        <div class="col-md-12">
                            <input type="text" id="agnRegName" name="agnRegName" value="${agencyRegistration.agnRegName}" maxlength="100" class="form-control form-control-sm" required />
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-md-12 required">Agency Code :</label>
                        <div class="col-md-12">
                            <input type="text" id="agnRegCode" name="agnRegCode" value="${agencyRegistration.agnRegCode}" maxlength="20" class="form-control form-control-sm disablecopypaste" required />
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-md-12 required">Email :</label>
                        <div class="col-md-12">
                            <input type="email" id="agnEmail" name="agnEmail" value="${agencyRegistration.agnEmail}" maxlength="100" class="form-control form-control-sm" required />
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-md-12 required">Mobile :</label>
                        <div class="col-md-12">
                            <input type="text" id="agnCellNo" name="agnCellNo" value="${agencyRegistration.agnCellNo}" maxlength="10" class="form-control form-control-sm" required />
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-md-12 required">Access Level :</label>
                        <div class="col-md-12">
                            <select class="form-control form-control-sm form-select" id="accessLevel" name="accessLevel">
                                <option value="" selected disabled>- Select -</option>
                                <option value="CREATOR" ${agencyRegistration.accessLevel eq 'CREATOR' ? 'selected' : ''}>CREATOR</option>
                                <option value="VIEWER" ${agencyRegistration.accessLevel eq 'VIEWER' ? 'selected' : ''}>VIEWER</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>

            <div class="text-center mt-4">
                <c:choose>
                    <c:when test="${not empty agencyRegistration}">
                        <input type="button" value="Update" class="btn btn-success btn-sm" onclick="submitAgencyData()">
                        <a href="${contextPath}/agencyRegistration/add" class="btn btn-warning btn-sm">Back</a>
                    </c:when>
                    <c:otherwise>
                        <input type="button" value="Submit" class="btn btn-success btn-sm" onclick="submitAgencyData()">
                        <a href="${contextPath}/home" class="btn btn-warning btn-sm">Back</a>
                        <button type="reset" class="btn btn-danger btn-sm">Reset</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </div>
</div>

<div class="col-md-12 mt-3">
    <c:if test="${empty agencyRegistration}">
        <div class="card">
            <div class="card-header">
                <h5 class="card-title">Agency Registration List</h5>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table id="basic-datatables" class="table table-bordered table-hover exportbtn">
                        <thead>
                            <tr>
                                <th class="text-center" style="width: 60px">Sl.No</th>
                                <th>Agency Name</th>
                                <th>Agency Code</th>
                                <th>Agency Type</th>
                                <th>Agency Template</th>
                                <th>Email</th>
                                <th>Mobile</th>
                                <th>Access Level</th>
                                <th class="text-center">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${agencyRegistrationList}" var="reg" varStatus="count">
                                <tr>
                                    <td class="text-center">${count.count}</td>
                                    <td>${reg.agnRegName}</td>
                                    <td>${reg.agnRegCode}</td>
                                    <td>${reg.typeNameEn}</td>
                                    <td>${reg.agencyNameEn}</td>
                                    <td>${reg.agnEmail}</td>
                                    <td>${reg.agnCellNo}</td>
                                    <td>${reg.accessLevel}</td>
                                    <td class="text-center">
                                        <button class="btn btn-warning btn-sm" onclick="editAgencyForm(${reg.agnRegId})" title="Edit">
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
    </c:if>
</div>

<form action="${contextPath}/agencyRegistration/edit" method="post" id="editForm">
    <input type="hidden" id="editCipherText" name="cipherText">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" id="agencyRegistrationEditId" name="agnRegId" />
</form>

<script>
function submitAgencyData() {
    var agnRegId = $("#agnRegId").val();
    var agencyTypeId = $("#agencyTypeId").val();
    var agnMstId = $("#agnMstId").val();
    var agnRegName = $("#agnRegName").val();
    var agnRegCode = $("#agnRegCode").val();
    var agnEmail = $("#agnEmail").val();
    var agnCellNo = $("#agnCellNo").val();
    var accessLevel = $("#accessLevel").val();

    if (!agencyTypeId || agencyTypeId === "0") {
        bootbox.alert("Please select an Agency Type");
        $("#agencyTypeId").focus();
        return false;
    } else if (!agnMstId || agnMstId === "0") {
        bootbox.alert("Please select an Agency Template");
        $("#agnMstId").focus();
        return false;
    } else if (!agnRegName || agnRegName.trim() === "") {
        bootbox.alert("Please enter Agency Name");
        $("#agnRegName").focus();
        return false;
    } else if (!agnRegCode || agnRegCode.trim() === "") {
        bootbox.alert("Please enter Agency Code");
        $("#agnRegCode").focus();
        return false;
    } else if (!agnEmail || agnEmail.trim() === "") {
        bootbox.alert("Please enter Email");
        $("#agnEmail").focus();
        return false;
    } else if (!agnCellNo || agnCellNo.trim() === "" || !/^\d{10}$/.test(agnCellNo)) {
        bootbox.alert("Please enter a valid 10-digit Mobile Number");
        $("#agnCellNo").focus();
        return false;
    } else if (!accessLevel || accessLevel.trim() === "") {
        bootbox.alert("Please select Access Level");
        $("#accessLevel").focus();
        return false;
    } else {
        $.ajax({
            type: "GET",
            url: "${contextPath}/agencyRegistration/validate-agnRegCode",
            data: {
                agnRegCode: agnRegCode,
                agnRegId: agnRegId
            },
            success: function(isDuplicate) {
                if (isDuplicate === true) {
                    bootbox.alert("Agency Code already exists. Please provide another.");
                    $("#agnRegCode").val("").focus();
                    return false;
                } else {
                    submitAgencyForm();
                }
            },
            error: function(err) {
                console.error("Error validating agnRegCode:", err);
                submitAgencyForm();
            }
        });
    }
}

function submitAgencyForm() {
    var bizContent = $("#agencyRegistrationForm").serializeArray();
    $("#cipherText").val(enc_password(JSON.stringify(convertFormToJSONArray(bizContent))));
    bootbox.confirm("Do You Want To Continue?", function(result) {
        if (result) {
            showLoader();
            $("#agencyRegistrationForm").submit();
        }
    });
}

function editAgencyForm(id) {
    $("#agencyRegistrationEditId").val(id);
    var bizContent = $("#editForm").serializeArray();
    $("#editCipherText").val(enc_password(JSON.stringify(convertFormToJSONArray(bizContent))));
    bootbox.confirm("Do You Want To Continue?", function(result) {
        if (result) {
            showLoader();
            $("#editForm").submit();
        }
    });
}

function getAgencyTemplatesByType(typeId) {
    $.ajax({
        url: "${contextPath}/agencyRegistration/fetch-templates-by-type",
        type: "GET",
        data: {
            typeId: typeId
        },
        success: function(templates) {
            var templateSelect = $("#agnMstId");
            templateSelect.empty();
            templateSelect.append('<option value="0" selected disabled>- Select -</option>');
            $.each(templates, function(index, template) {
                templateSelect.append('<option value="' + template.agencyId + '">' + template.agencyNameEn + '</option>');
            });
        },
        error: function(error) {
            bootbox.alert("Error fetching agency templates: " + error);
        }
    });
}

$(document).ready(function() {
    // Initialize form if editing
    if ($("#agnRegId").val()) {
        getAgencyTemplatesByType($("#agencyTypeId").val());
    }
});
</script>