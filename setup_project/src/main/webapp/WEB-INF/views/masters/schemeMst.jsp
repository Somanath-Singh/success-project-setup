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
            <h5>Add Scheme</h5>
        </div>
        <div class="col-md-6">
            <nav aria-label="breadcrumb" style="float: right;">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${contextPath}/home">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Scheme</li>
                </ol>
            </nav>
        </div>
    </div>
    
    <hr style="margin-top: 5px;" />
    <h6 class="headingbg mb-4">Scheme Details</h6>
    <form class="form-horizontal" action="${contextPath}/mst/saveSchemeDetails" method="POST" id="schemeMstForm"  enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="schemeId" value="${schemeMstData.schemeId}">
        <div class="row">
        	<div class="form-group col-md-2">
                <label class="required">Scheme Name</label>
                <div class="col-md-12">
                    <input type="text" id="schemeName" name="schemeName" placeholder="Max 100 chars" class="form-control form-control-sm" required maxlength="100" value="${schemeMstData.schemeName}">
                </div>
            </div>
            <div class="form-group col-md-2">
                <label class="required">Municipality Name</label>
                <div id="hideUserId">
                    <div class="col-md-12">
                        <select name="municipality.municipalityId" id="municipalityId" class="form-control form-select" required="required" >
                            <option value="0"><spring:message code="label.common.select"></spring:message></option>
                            <c:forEach var="municipality" items="${municipalityList}">
                                 <option value="${municipality.municipalityId}" ${municipality.municipalityId eq schemeMstData.municipality.municipalityId ? 'selected' : ''}>${municipality.municipalityName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group col-md-2">
                <label class="required">Description</label>
                <div class="col-md-12">
                    <input type="text" id="descriptionId" name="description" class="form-control form-control-sm" required placeholder="Max 100 chars" maxlength="100" value="${schemeMstData.description}">
                </div>
            </div>
            
			<div class="row apnd-box">
  				<c:choose>
	              	<c:when test="${empty schemeMstData || empty documentList}">
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
									<input type="file" class="form-control inputPathName attachDocNameCls" maxlength="250" id="attachDocNameId${count.index}" name="docList[${count.index}].attachDocName" onchange="validateImageFormat(this)">
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
            <button type="button" class="btn-submit" onclick="submitForm('${empty schemeMstData.schemeId ? 'SAVE' : 'UPDATE'}')"><spring:message code="${empty schemeMstData.schemeId ? 'site.common.submit' : 'site.common.update'}"/></button>
            <a href="${contextPath}/home" type="button" class="btn btn-warning btn-sm">Back</a>
        </div>
    </form>
</div>
<div class="cardcontainer cardContainerNotFirst">
	<div class="row mt-2"> 
		<div class="col-md-12">
			<div class="card full-height">
				<div class="card-header">
					<h4 class="card-title">Scheme Details</h4>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="display table table-bordered table-hover exportbtn" style="width: 100%;table-layout: fixed;" id="tableId1">
								<thead>
									<tr>
										<th>Sl.No</th>
										<th>Scheme Name</th>
										<th>Municipality Name</th>
										<th>Description</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${schemeList}" var="scheme" varStatus="count">    
										<tr> 
										  	<td>${count.count}</td>
											<td>${scheme.schemeName}</td>
                           					<td>${scheme.municipality.municipalityName}</td>
                            				<td>${scheme.description}</td>
										 	<td><button	type="button" onclick="editSchemeDtls(${scheme.schemeId})"class="btn btn-info btn-sm" title="edit" style="height:22px; width:26px; padding: 0px 7px !important;line-height: 7px"> <i class=" fa fa-edit " aria-hidden="true"></i></button></td>
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

<form action = "${contextPath}/mst/editSchemeDetails" method="get" id="editSchemeDtlsForm">
	<input type="hidden" name="schemeId" id="schemeId">
</form> 

<form action = "${contextPath}/document/viewDocuments" method="get" id="documentFormView" enctype="multipart/form-data" target="_blank">
	<input type="hidden" name="moduleName" id="moduleName"/>
	<input type="hidden" name="filePath" id="filePath"/>
</form>

<form action = "${contextPath}/mst/removeSchemeDoc" method="Post" id="removeDocDetails">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="schemeId" id="schemeId">
	<input type="hidden" name="removeId" id="DocDetailsId">
</form>

<script>

$(document).ready(function(){
	  $("#apnd-btn-plus").click(function(){
		  var count = $(".attachDocNameCls").length;
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
	  var scheme = $('#schemeName').val();
      var municipality = $('#municipalityId').val();
      var description = $('#descriptionId').val();
      
      if (scheme == "") {
          bootbox.alert("Please enter scheme name.");
          return false;
      }

      if (municipality == "") {
          bootbox.alert("Please select municipality.");
          return false;
      }

      if (description.trim() == "") {
          bootbox.alert("Please provide description.");
          return false;
      }
      else {
    	  renumberRows();
  		var msg = status === 'SAVE' ? 'save' : 'update';
  		
  		bootbox.confirm("Do you want to " +msg+" Scheme  data ?",
  			function(result){
  				if(result){
  				showLoader();
  				$('#schemeMstForm').submit();
  			}
  		});
  	}
}

function editSchemeDtls(id){
	debugger;
//	 $("#finYearId").val(enc_password(id));
//	 $("#editFinYearForm").append('<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />');
	 
    $("#schemeId").val(id);
		showLoader();
	 $("#editSchemeDtlsForm").submit();
}

function deleteSchemeDoc(id,schemeId){
	
	$("#DocDetailsId").val(id);
	$("#schemeId").val(schemeId);
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