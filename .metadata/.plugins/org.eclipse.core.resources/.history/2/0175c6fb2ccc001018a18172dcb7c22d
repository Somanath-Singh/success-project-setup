<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<c:set var="userDetails" value="${serviceOutcome.data}"/> 
<script type="text/javascript" src="${contextPath}/assets/appJs/validation/common-utils.js"></script>
<%-- <script src="${contextPath}/assets/appJs/validation/commonMaster.js"></script> --%>
<style>
.freeze{
		pointer-events: none;

	}
</style>

<style>

</style>


<div class="cardcontainer">
    <div class="row">
        <div class="col-md-6">
            <h5>Add Program</h5>
        </div>
        <div class="col-md-6">
            <nav aria-label="breadcrumb" style="float: right;">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${contextPath}/home">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Program</li>
                </ol>
            </nav>
        </div>
    </div>
    
    <hr style="margin-top: 5px;" />
    <h6 class="headingbg mb-4">Program Details</h6>
    <form class="form-horizontal" action="${contextPath}/mst/saveProgramDetails" method="POST" id="programMstForm"  enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="programId" value="${programMstData.programId}">
        <div class="row">
        	<div class="form-group col-md-2">
                <label class="required">Program Name</label>
                <div class="col-md-12">
                    <input type="text" id="programName" name="programName" placeholder="Max 100 chars" class="form-control form-control-sm" required maxlength="100" value="${programMstData.programName}">
                </div>
            </div>
            <div class="form-group col-md-2">
                <label class="required">Scheme Name</label>
                <div id="hideUserId">
                    <div class="col-md-12">
                        <select name="scheme.schemeId" id="schemeId" class="form-control form-select" required="required" >
                            <option value="0"><spring:message code="label.common.select"></spring:message></option>
                            <c:forEach items="${schemeList}" var="scheme">
                                 <option value="${scheme.schemeId}" ${scheme.schemeId eq programMstData.scheme.schemeId ? 'selected' : ''}>${scheme.schemeName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group col-md-2">
                <label class="required">Description</label>
                <div class="col-md-12">
                    <input type="text" id="descriptionId" name="description" class="form-control form-control-sm" required placeholder="Max 100 chars" maxlength="100" value="${programMstData.description}">
                </div>
            </div>
            	<div class="row apnd-box">
  				<c:choose>
	              	<c:when test="${empty programMstData || empty documentList}">
		                <div class="form-group col-md-2">
		                  <label class="d-flex justify-content-between align-items-center mb-1">Upload Document : <i class="fa fa-plus float:right" id="apnd-btn-plus"></i></label>
	                      <input type="hidden" class="docIdCls" name="docList[0].docId" id="docId0" />
	                      <input type="file" class="form-control inputPathName attachDocNameCls" maxlength="250" name="docList[0].attachDocName" id="attachDocNameId0" onchange="validateImageFormat(this)">
		                </div>
	              	</c:when>
             		<c:otherwise>
	              		<c:forEach items="${documentList}" var="doc" varStatus="count">
				        	<div class="form-group col-md-2">
				                <label class="d-flex justify-content-between align-items-center mb-1">Uploaded Document :
				                	<c:choose>
				                  		<c:when test="${count.count == '1'}">
				                  			<i class="fa fa-plus float:right" id="apnd-btn-plus"></i>
				                  		</c:when>
				                  		<c:otherwise>
				                  			<i class="fa fa-minus remove-occ"></i>
				                  		</c:otherwise>
				                  	</c:choose>
					            </label>
					            	<input type="hidden" class="docIdCls" name="docList[${count.index}].docId" id="docId${count.index}" value="${doc.docId}"/>
									<input type="file" class="form-control inputPathName attachDocNameCls" maxlength="250"  id="attachDocNameId${count.index}" name="docList[${count.index}].attachDocName" onchange="validateImageFormat(this)">
									<c:if test="${not empty doc.docPath}">
				                  		<button type="button" title="View Document" class ="view-btn" onclick = "viewDocument('${doc.docPath}')"><i class="fa-sharp fa-solid fa-file"></i></button>
				                	</c:if>
				            	</div>
	              		</c:forEach>
             		</c:otherwise>
             	</c:choose>
			</div>
        </div>
        <div class="col-md-12 mt-2 text-center">
            <button type="button" class="btn-submit" onclick="submitForm('${empty programMstData.programId ? 'SAVE' : 'UPDATE'}')"><spring:message code="${empty programMstData.programId ? 'site.common.submit' : 'site.common.update'}"/></button>
            <a href="${contextPath}/home" type="button" class="btn btn-warning btn-sm">Back</a>
        </div>
    </form>
</div>
<div class="cardcontainer cardContainerNotFirst">
	<div class="row mt-2"> 
	
		<div class="col-md-12">
		
			<div class="card full-height">
			
				<div class="card-header">
					<h4 class="card-title">Program Details</h4>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="display table table-bordered table-hover exportbtn" style="width: 100%;table-layout: fixed;" id="tableId1">
								<thead>
									<tr>
										<th>Sl.No</th>
										<th>Program Name</th>
										<th>Scheme Name</th>
										<th>Description</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${programList}" var="program" varStatus="count">    
										<tr> 
										  	<td>${count.count}</td>
											<td>${program.programName}</td>
                           					<td>${program.scheme.schemeName}</td>
                            				<td>${program.description}</td>
										 	<td><button	type="button" onclick="editProgramDtls(${program.programId})"class="btn btn-info btn-sm" title="edit" style="height:22px; width:26px; padding: 0px 7px !important;line-height: 7px"> <i class=" fa fa-edit " aria-hidden="true"></i></button></td>
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

<form action = "${contextPath}/mst/editProgramDetails" method="get" id="editProgramDtlsForm">
	<input type="hidden" name="programId" id="programId">
</form> 

<form action = "${contextPath}/document/viewDocuments" method="get" id="documentFormView" enctype="multipart/form-data" target="_blank">
	<input type="hidden" name="moduleName" id="moduleName"/>
	<input type="hidden" name="filePath" id="filePath"/>
</form>

<form action = "${contextPath}/mst/removeProgDoc" method="Post" id="removeDocDetails">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="progId" id="progId">
	<input type="hidden" name="removeId" id="DocDetailsId">
</form>

<script>
/*  $(document).ready(function(){
	  $("#apnd-btn-plus").click(function(){
		  debugger;
		  var firstDocField = $("input[name='docList[0].attachDocName']").val();
	        if (!firstDocField) {
	            bootbox.alert("Please upload the first document before adding another.");
	            return;
	        }
		  $(this).closest(".apnd-box").append('<div class="form-group col-md-2"><label class="d-flex justify-content-between align-items-center mb-1">Upload Document : <i class="fa fa-minus remove-occ"></i></label><input type="file" class="form-control inputPathName" maxlength="250" name="docList[0].attachDocName" onchange="validateImageFormat(this)"></div>');
		  renumberRows();	  
	  });
	
	$("body").on("click", ".remove-occ", function () {
		$(this).closest(".form-group").remove();
		renumberRows();
	});
});
 */
$(document).ready(function(){
	  $("#apnd-btn-plus").click(function(){
		  debugger;
		  var count = $(".attachDocNameCls").length;
		/*   var firstDocField = $("input[name='docList[0].attachDocName']").val();
	        if (!firstDocField) {
	            bootbox.alert("Please upload the first document before adding another.");
	            return;
	        } */
		  $(this).closest(".apnd-box").append('<div class="form-group col-md-2"><label class="d-flex justify-content-between align-items-center mb-1">Upload Document : <i class="fa fa-minus remove-occ"></i></label><input type="hidden" class="docIdCls" name="docList['+count+'].docId" id="docId'+count+'" /><input type="file" class="form-control inputPathName attachDocNameCls" id="attachDocNameId'+count+'" maxlength="250" name="docList['+count+'].attachDocName" onchange="validateImageFormat(this)"></div>');
		  renumberRows();
	  });
	
	$("body").on("click", ".remove-occ", function () {
		$(this).closest(".form-group").remove();
		renumberRows();
	});
});
function submitForm(status){
	debugger;
	  var program = $('#programName').val();
      var scheme = $('#schemeId').val();
      var description = $('#descriptionId').val();
      
      if (program == "") {
          bootbox.alert("Please enter program name.");
          return false;
      }

      if (scheme == "") {
          bootbox.alert("Please select scheme.");
          return false;
      }

      if (description.trim() == "") {
          bootbox.alert("Please provide description.");
          return false;
      }
      var allAdditionalDocumentsFilled = true;
      var documentFields = $(".inputPathName");
      if (documentFields.length > 1) {
          documentFields.slice(1).each(function() {
              if ($(this).val() === "") {
                  allAdditionalDocumentsFilled = false;
                  return false;
              }
          });
      }

      if (!allAdditionalDocumentsFilled) {
          bootbox.alert("Please upload all additional documents before submitting the form.");
          return false;
      }
      else{
    	  renumberRows();
  		var msg = status === 'SAVE' ? 'save' : 'update';
  		
  		bootbox.confirm("Do you want to " +msg+" Program  data ?",
  			function(result){
  				if(result){
  				showLoader();
  				$('#programMstForm').submit();
  			}
  		});
  	}
}

function editProgramDtls(id){
	debugger;
//	 $("#finYearId").val(enc_password(id));
//	 $("#editFinYearForm").append('<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />');
	 
    $("#programId").val(id);
		showLoader();
	 $("#editProgramDtlsForm").submit();
}


/* let uploadFieldCounter = 1;

function addUploadField() {
	debugger;
	if('{programMstData.programId}' != ''){
		const container = document.getElementById('docContainer');
	}
    const container = document.getElementById('uploadContainer');
    const newField = document.createElement('div');
    newField.className = 'form-group col-md-3 upload-field';
    newField.id = 'uploadField' + uploadFieldCounter;
    newField.innerHTML = "<label class='smallInput'>Upload Document:</label>" +
        "<div class='input-group'>" +
        "<input type='file' class='form-control inputPathName' maxlength='250' name='docList[" + uploadFieldCounter + "].attachDocName' onchange='validateImageFormat(this)'>" +
        "<span class='input-group-btn'>" +
        "<button type='button' class='btn btn-danger btn-sm' style='border-radius: 0 5px 5px 0 !important;' onclick='removeUploadField(this, " + uploadFieldCounter + ")'><i class='fa fa-minus'></i></button>" +
        "</span>" +
        "</div>" +
        "<div id='errorText" + uploadFieldCounter + "' style='color: red;'></div>";

    container.appendChild(newField);
    uploadFieldCounter++;
} */

function removeUploadField(element, index) {
    const fieldToRemove = document.getElementById('uploadField' + index);
    if (fieldToRemove) {
        fieldToRemove.remove();
    }
}

function deleteDocument(index) {
    const docContainer = document.getElementById('docContainer' + index);
    if (docContainer) {
        docContainer.remove();
    }
}

function deleteProgramDoc(id,programId){
	
	$("#DocDetailsId").val(id);
	$("#progId").val(programId);
	bootbox.confirm("Do you want to remove this document?", function (result) {
	if (result == true) {
		  showLoader();
		  $("#removeDocDetails").submit();
		}
	});
}
function viewDocument(filePath,moduleName){
	  $('#moduleName').val(moduleName);
	  $('#filePath').val(filePath);
	  $('#documentFormView').submit();
}

function renumberRows()
{
	var listSize = $(".attachDocNameCls").length;
    for (var i = 0; i < listSize; i++) {
        var input = $(".attachDocNameCls")[i];
        $(input).attr("id", $(input).attr("id").replace(/\d+$/, i));
        $(input).attr("name", "docList["+i+"].attachDocName");
        
        var input1 = $(".docIdCls")[i];
        $(input1).attr("id", $(input1).attr("id").replace(/\d+$/, i));
        $(input1).attr("name", "docList["+i+"].docId");
    }
}
</script>