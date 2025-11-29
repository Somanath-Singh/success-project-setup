<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
</style>

<%@ include file="/WEB-INF/views/stageConfig/message.jsp"%>

    <h1>Create Actions</h1>
    <div class="panel-body">
        <div class="row " style="display: flex; justify-content: flex-center; flex-wrap: wrap;">
            <div class="col-md-12">
                <div class="bagGroundColor px-3">
                <form action="${contextPath}/stageConfig/saveActionType" method="post" id="saveActionTypeId" class="row" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="hidden" id="actionHdnId" name="actionTypeId" value="${actionTypeDetails.actionTypeId}">
                    <div class="row">
                    
                    	<div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label required">Action Name</label>
                                <input class="col-md-3 form-control" id="actionNameId" name="actionName" value="${actionTypeDetails.actionName}" />
                            </div>
                        </div>
                        
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label required">Action Code</label>
                                <input class="col-md-3 form-control" id="actionCodeId" name="actionCode" value="${actionTypeDetails.actionCode}" />
                            </div>
                        </div>
                        
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label required">Status Display Name</label>
                                <input class="col-md-3 form-control" id="statusDisplayNameId" name="statusDisplayName" value="${actionTypeDetails.statusDisplayName}" />
                            </div>
                        </div>
                    
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label required">Select Action Button Color</label>
                                <select class="col-md-3 form-control" id="actionButtonCollorId" name="actionButtonCollor">
                                    <option value="">---Select---</option>
                                    <option style="color: #0dcaf0;" value="btn btn-sm btn-info" ${actionTypeDetails.actionButtonCollor eq 'btn btn-sm btn-info' ? 'selected':''} >btn btn-sm btn-info</option>
                                    <option style="color: #000;" value="btn btn-sm btn-dark" ${actionTypeDetails.actionButtonCollor eq 'btn btn-sm btn-dark' ? 'selected':''} >btn btn-sm btn-dark</option>
                                    <option style="color: #0dcaf0;" value="btn btn-sm btn-success" ${actionTypeDetails.actionButtonCollor eq 'btn btn-sm btn-success' ? 'selected':''} >btn btn-sm btn-success</option>
                                    <option style="color: #0dcaf0;" value="btn btn-sm btn-warning" ${actionTypeDetails.actionButtonCollor eq 'btn btn-sm btn-warning' ? 'selected':''} >btn btn-sm btn-warning</option>
                                    <option style="color: #0dcaf0;" value="btn btn-sm btn-danger" ${actionTypeDetails.actionButtonCollor eq 'btn btn-sm btn-danger' ? 'selected':''} >btn btn-sm btn-danger</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label required">Select Action Type</label>
                                <select class="col-md-3 form-control" id="actionTypeId" name="actionType" onchange="showHideDivision();" >
                                    <option value="">---Select---</option>
                                    <option value="COMMON" ${actionTypeDetails.actionType eq 'COMMON' ? 'selected':''}>COMMON</option>
                                    <option value="NEXT" ${actionTypeDetails.actionType eq 'NEXT' ? 'selected':''}>NEXT</option>
                                    <option value="PRIVATE" ${actionTypeDetails.actionType eq 'PRIVATE' ? 'selected':''}>PRIVATE</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="col-md-4" id="showHideEntityId" style="display: none;">
                            <div class="form-group">
                                <label class="control-label required">Select Entity</label>
                                <select class="col-md-3 form-control selectpicker" id="entityNameId" name="workFlowModulesIds" multiple data-live-search="true">
                                    <c:forEach items="${workFlowModulesList}" var="workFlowModulesList">
                                    	<option value="${workFlowModulesList.id}"
                                            <c:forEach items="${actionTypeDetails.workFlowModulesIds}" var="workFlowModulesIds">
                                                ${workFlowModulesList.id eq workFlowModulesIds ? 'selected':''}
								            </c:forEach>
                                    	>${workFlowModulesList.tableDisplayName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!-- Submit butoon in center -->
			        <div class="row" style="display: flex; justify-content: center; flex-wrap: wrap; margin-top: 20px;">
			            <div class="col-md-12 text-center">
			                <button type="button" id="satgeConfigBtnId" class="btn btn-success" onclick="saveActionTypes('${empty actionTypeDetails.actionTypeId ? 'Save':'Update'}')">${empty actionTypeDetails.actionTypeId ? 'Save':'Update'}</button>
			            </div>
			        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="table-responsiv">
                <table class="table table-bordered table-hover table-striped" id="sct">
                    <thead class="text-center bagGroundColorRvr" style="color: #fff;">
                        <tr>
                            <th>Action Name</th>
                            <th>Action Code</th>
                            <th>Status Display Name</th>
                            <th>Action Button Color</th>
                            <th>Action Type</th>
                            <th>Is Active</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="actionListBody"></tbody>
                    <c:forEach items="${actionTypeList}" var="actionTypeList">
                        <tr>
                            <td>${actionTypeList.actionNameEn}</td>
                            <td>${actionTypeList.actionCode}</td>
                            <td>${actionTypeList.displayName}</td>
                            <td>${actionTypeList.color}</td>
                            <td>${actionTypeList.type}</td>
                            <td>${actionTypeList.isActive}</td>
                            <td ><button type="button" onclick="editActionTypeData('${actionTypeList.actionTypeId}');" title="Edit">
								 <i class=" fa fa-pen " aria-hidden="true"></i></button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

<form action="${contextPath}/stageConfig/editActionType" method="get" id="editActionTypeId">
<input type="hidden" name="actionTypeId" id="editactionId">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<script>

function editActionTypeData(actionTypeId) {
    $("#editactionId").val(actionTypeId);
    $("#editActionTypeId").submit();
}


function showHideDivision() {
    var actionType = $("#actionTypeId").val();
    if (actionType === 'PRIVATE' || actionType === 'NEXT') {
        $("#showHideEntityId").css("display", "block");
    } else {
        $("#showHideEntityId").css("display", "none");
    }
}

$(document).ready(function() {
    // Check if actionTypeDetails.workFlowModulesIds is not empty
    if (${not empty actionTypeDetails.workFlowModulesIds}) {
        $("#showHideEntityId").css("display", "block");
    } else {
        $("#showHideEntityId").css("display", "none");
    }

    // Attach change event to the actionType dropdown to call the function
    $("#actionTypeId").change(showHideDivision);
});


function saveActionTypes(btnValue) {
    var actionName = $("#actionNameId").val();
    var actionCode = $("#actionCodeId").val();
    var statusDisplayName = $("#statusDisplayNameId").val();
    var actionButtonCollor = $("#actionButtonCollorId").val();
    var actionType = $("#actionTypeId").val();
    var entityName = $("#entityNameId").val();

    if (actionName.trim() === "") {
        bootbox.alert("Please provide action name.");
        $("#actionNameId").val('');
        return false;
    } else if (actionCode.trim() === "") {
        bootbox.alert("Please provide action code.");
        $("#actionCodeId").val('');
        return false;
    } else if (statusDisplayName.trim() === "") {
        bootbox.alert("Please provide status display name.");
        $("#statusDisplayNameId").val('');
        return false;
    } else if (actionButtonCollor.trim() === "") {
        bootbox.alert("Please select action button color.");
        $("#actionButtonCollorId").val('');
        return false;
    } else if (actionType.trim() === "") {
        bootbox.alert("Please select action type.");
        $("#actionTypeId").val('');
        return false;
    } else if (entityName.length === 0 && (actionType === 'PRIVATE' || actionType === 'NEXT')) {
        bootbox.alert("Please select entity name.");
        $("#entityNameId").val('');
        return false;
    } else {
        checkDuplicateActionCode(actionCode).then(function(isDuplicate) {
            if (isDuplicate) {
                bootbox.alert("Duplicate action codes are not allowed.");
                $("#actionCodeId").val('');
                return false;
            } else {
                bootbox.confirm("Do you want to "+btnValue+" action type details?", function(result) {
                    if (result) {
                        $("#saveActionTypeId").submit();
                    }
                });
            }
        });
    }
}

function checkDuplicateActionCode(actionCode) {
    return new Promise(function(resolve, reject) {
        var actionHdnId = $("#actionHdnId").val();
        $.ajax({
            type: "get",
            url: "${contextPath}/stageConfig/duplicateCheckForActionCode",
            data: {
                "actionHdnId": actionHdnId,
                "actionCode": actionCode,
            },
            success: function(response) {
                if (response === 'Duplicate Action Code Found.') {
                    resolve(true); // Duplicate found
                } else {
                    resolve(false); // No duplicate
                }
            },
            error: function(xhr, status, error) {
                console.error("AJAX request failed with status: " + status + "\nError: " + error);
                reject(error); // Reject on error
            }
        });
    });
}

</script>