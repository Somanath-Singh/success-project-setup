<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
</style>

<%@ include file="/WEB-INF/views/stageConfig/message.jsp"%>

    <h3>WFM Api Details</h3>
    <div class="panel-body">
        <div class="row " style="display: flex; justify-content: flex-center; flex-wrap: wrap;">
            <div class="col-md-12">
                <div class="bagGroundColor px-3">
                <form action="${contextPath}/stageConfig/saveAndUpdateApiDetails" method="post" id="saveAndUpdateApiDetailsId" class="row" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="hidden" name="apiId" id="apiDetailsId" value="${apiDetails.apiId}">
                    <div class="row">

                    	<div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label required">Api Name</label>
                                <input class="col-md-3 form-control" id="apiNameId" name="apiName" value="${apiDetails.apiName}"/>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label required">Api Url</label>
                                <input class="col-md-3 form-control" id="apiUrlId" name="apiBaseUrl" value="${apiDetails.apiBaseUrl}"/>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label required">Api Path</label>
                                <input class="col-md-3 form-control" id="apiPathId" name="apiPath" value="${apiDetails.apiPath}"/>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label required">Api Meta Data Key</label>
                                <input class="col-md-3 form-control" id="apiMetaDataKeyId" name="apiForMetaDataKey" value="${apiDetails.apiForMetaDataKey}"/>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label required">Api Method Type</label>
                                <select class="col-md-3 form-control" id="apiMethodTypeId" name="apiMethod" >
                                    <option value="" >---Select---</option>
                                    <option value="GET" ${apiDetails.apiMethod eq 'GET' ? 'selected':''} >GET</option>
                                    <option value="POST" ${apiDetails.apiMethod eq 'POST' ? 'selected':''} >POST</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label required">Encryption Format</label>
                                <select class="col-md-3 form-control" id="encFormatId" name="dataEncryptionFormat" >
                                    <option value="" >---Select---</option>
                                    <option value="NONE" ${apiDetails.dataEncryptionFormat eq 'NONE' ? 'selected':''} >NONE</option>
                                    <option value="BASE64" ${apiDetails.dataEncryptionFormat eq 'BASE64' ? 'selected':''} >BASE64</option>
                                    <option value="AES" ${apiDetails.dataEncryptionFormat eq 'AES' ? 'selected':''} >AES</option>
                                    <option value="BASE64_AES" ${apiDetails.dataEncryptionFormat eq 'BASE64_AES' ? 'selected':''} >BASE64_AES</option>
                                    <option value="AES_BASE64" ${apiDetails.dataEncryptionFormat eq 'AES_BASE64' ? 'selected':''} >AES_BASE64</option>
                                </select>
                            </div>
                        </div>

                    </div>
                    <!-- Submit butoon in center -->
			        <div class="row" style="display: flex; justify-content: center; flex-wrap: wrap; margin-top: 20px;">
			            <div class="col-md-12 text-center">
			                <button type="button" id="apiDataBtnId" class="btn btn-success" onclick="saveWfmApiDetails('${empty apiDetails.apiId ? 'Save':'Update'}')" >${empty apiDetails.apiId ? 'Save':'Update'}</button>
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
                            <th>Api Name</th>
                            <th>Api Url</th>
                            <th>Api Path</th>
                            <th>Api Method</th>
                            <th>Api MetaData Key</th>
                            <th>Encryption Format</th>
                            <th>Is Active</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="actionListBody">
                      <c:forEach items="${apiDetailsList}" var="apiDetailsList">
                        <tr>
                          <td>${apiDetailsList.apiName}</td>
                          <td>${apiDetailsList.apiBaseUrl}</td>
                          <td>${apiDetailsList.apiPath}</td>
                          <td>${apiDetailsList.apiMethod}</td>
                          <td>${apiDetailsList.apiForMetaDataKey}</td>
                          <td>${apiDetailsList.dataEncryptionFormat}</td>
                          <td>${apiDetailsList.isActive}</td>
                          <td ><button type="button" onclick="editApiDetailsData('${apiDetailsList.apiId}');" title="Edit">
								 <i class=" fa fa-pen " aria-hidden="true"></i></button>
                          </td>
                        </tr>
                      </c:forEach>
                    </tbody>
                </table>
            </div>

<form action="${contextPath}/stageConfig/editWfmApiDetails" method="get" id="editWfmApiDetailsId">
<input type="hidden" name="apiDetailsId" id="apiDtlsId">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<script>


function saveWfmApiDetails(btnValue) {
    var apiName = $("#apiNameId").val();
    var apiUrl = $("#apiUrlId").val();
    var apiPath = $("#apiPathId").val();
    var apiMetaDataKey = $("#apiMetaDataKeyId").val();
    var apiMethodType = $("#apiMethodTypeId").val();
    var encFormat = $("#encFormatId").val();

    if (apiName.trim() === "") {
        bootbox.alert("Please provide api name.");
        $("#apiNameId").val('');
        return false;
    } else if (apiUrl.trim() === "") {
        bootbox.alert("Please provide api url.");
        $("#apiUrlId").val('');
        return false;
    } else if (apiPath.trim() === "") {
        bootbox.alert("Please provide api path.");
        $("#apiPathId").val('');
        return false;
    } else if (apiMetaDataKey.trim() === "") {
        bootbox.alert("Please provide api meta data key.");
        $("#apiMetaDataKeyId").val('');
        return false;
    } else if (apiMethodType.trim() === "") {
        bootbox.alert("Please select api method type.");
        $("#apiMethodTypeId").val('');
        return false;
    } else if (encFormat.trim() === "") {
        bootbox.alert("Please select api encoded format.");
        $("#encFormatId").val('');
        return false;
    }
	bootbox.confirm("Do you want to "+btnValue+" wfm api details ?" ,
            function(result){
              if(result){
                $("#saveAndUpdateApiDetailsId").submit();
              }
            });
}

function editApiDetailsData(apiId) {
    $("#apiDtlsId").val(apiId);
    $("#editWfmApiDetailsId").submit();
}

</script>