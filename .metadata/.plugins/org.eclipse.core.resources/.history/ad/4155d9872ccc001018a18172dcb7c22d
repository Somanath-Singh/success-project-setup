<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
</style>

<%@ include file="/WEB-INF/views/stageConfig/message.jsp"%>

    <h3>NativeQuery And ShareEntity Details</h3>
    <div class="panel-body">
        <div class="row " style="display: flex; justify-content: flex-center; flex-wrap: wrap;">
            <div class="col-md-12">
                <div class="bagGroundColor px-3">
                <form action="${contextPath}/stageConfig/saveAndUpdateNativeQueryShareEntityDetails" method="post" id="saveAndUpdateNativeQueryShareEntityDetailsId" class="row" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="hidden" name="shareEntityId" id="shareEntityIdId" value="${nativeQueryAndShareEntityDto.shareEntityId}">
                    <div class="row">

                    	<div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label required">ShareEntity Name</label>
                                <input type="text" class="col-md-3 form-control" id="shareEntityNameId" name="shareEntityName" value="${nativeQueryAndShareEntityDto.shareEntityName}"/>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label required">ShareEntity Code</label>
                                <input type="text" class="col-md-3 form-control" id="shareEntityCodeId" name="shareEntityCode" value="${nativeQueryAndShareEntityDto.shareEntityCode}" onchange="fetchNativeQueryByCode(this.value);"/>
                            </div>
                        </div>

                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="control-label required">Native Query</label>
                                <textarea class="col-md-3 form-control" id="nativeQueryId" name="nativeQuery">${nativeQueryAndShareEntityDto.nativeQuery}</textarea>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Is From Api</label>
                                <input type="checkbox" id="isFromApiId" name="isFromApi" value="true" onclick="this.previousSibling.value = this.checked ? 'true' : 'false';">
                            </div>
                        </div>


                    </div>
                    <!-- Submit butoon in center -->
			        <div class="row" style="display: flex; justify-content: center; flex-wrap: wrap; margin-top: 20px;">
			            <div class="col-md-12 text-center">
			                <button type="button" id="apiDataBtnId" class="btn btn-success" onclick="saveAndUpdateNativeQueryShareEntityDetails('${empty nativeQueryAndShareEntityDto.shareEntityId ? 'Save':'Update'}')" >${empty nativeQueryAndShareEntityDto.shareEntityId ? 'Save':'Update'}</button>
			            </div>
			        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped" id="sct">
                    <thead class="text-center bagGroundColorRvr" style="color: #fff;">
                        <tr>
                            <th>ShareEntity Name</th>
                            <th>ShareEntity Code</th>
                            <th>NativeQuery Name</th>
                            <th>NativeQuery</th>
                            <th>Is From Api</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="actionListBody">
                        <c:forEach items="${nativeQueryAndShareEntityDataList}" var="nativeQueryAndShareEntityDataList">
                            <tr>
                                <td>${nativeQueryAndShareEntityDataList.shareEntityName}</td>
                                <td>${nativeQueryAndShareEntityDataList.shareEntityCode}</td>
                                <td>${nativeQueryAndShareEntityDataList.queryName}</td>
                                <td>${nativeQueryAndShareEntityDataList.nativeQuery}</td>
                                <td>${nativeQueryAndShareEntityDataList.isFromApi}</td>
                                <td><button type="button" onclick="editNativeQueryAndShareEntityData('${nativeQueryAndShareEntityDataList.shareEntityCode}');" title="Edit">
                                     <i class=" fa fa-pen " aria-hidden="true"></i></button></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

<form action="${contextPath}/stageConfig/editNativeQueryAndShareEntityData" method="get" id="editNativeQueryAndShareEntityDataId">
<input type="hidden" name="hdnShareEntityCode" id="hdnShareEntityCodeId">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<script>

function fetchNativeQueryByCode(value) {
    $.ajax({
            type: "get",
            url: "${contextPath}/stageConfig/fetchNativeQueryByCode",
            data: {
                "shareEntityCode": value
            },
            success: function(response) {
                $("#nativeQueryId").val(response);
            },
            error: function(xhr, status, error) {
                console.error("AJAX request failed with status: " + status + "\nError: " + error);
                reject(error); // Reject on error
            }
        });
}



function saveAndUpdateNativeQueryShareEntityDetails(btnValue) {
    var shareEntityName = $("#shareEntityNameId").val();
    var shareEntityCode = $("#shareEntityCodeId").val();
    var nativeQuery = $("#nativeQueryId").val();

    if (shareEntityName.trim() === "") {
        bootbox.alert("Please provide share entity name.");
        $("#shareEntityNameId").val('');
        return false;
    } else if (shareEntityCode.trim() === "") {
        bootbox.alert("Please provide share entity code.");
        $("#shareEntityCodeId").val('');
        return false;
    } else if (nativeQuery.trim() === "") {
        bootbox.alert("Please provide native query.");
        $("#nativeQueryId").val('');
        return false;
    }
	bootbox.confirm("Do you want to "+btnValue+" wfm api details ?" ,
            function(result){
              if(result){
                $("#saveAndUpdateNativeQueryShareEntityDetailsId").submit();
              }
            });
}

function editNativeQueryAndShareEntityData(shareEntityCode) {
    $("#hdnShareEntityCodeId").val(shareEntityCode);
    $("#editNativeQueryAndShareEntityDataId").submit();
}

</script>