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
    	<h5>Add Financial Year</h5>
	</div>
	<div class="col-md-6">
	    <nav aria-label="breadcrumb" style="float: right;">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${contextPath}/home">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Financial Year</li>
            </ol>
        </nav>
    </div>
</div>

<hr style="margin-top: 5px;" />
<h6 class="headingbg mb-4">Add Financial Year</h6>
<form class="form-horizontal" action="${contextPath}/mst/saveFinancialYear" method="POST" id="financialYearForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="finyearId" value="${finYearData.finyearId}">
	<div class="row">
		<div class="form-group col-md-3">
			<label class="required">Financial Year Name</label>
			<div id="hideUserId"> 
				<div class="col-md-12">
					<input type="text" class="form-control form-control-sm " id="finYearId" name="finYear" value="${finYearData.finYear}" onchange="validateAndSetDates(),DupliCateFinYearCheck(this)"  maxlength="9" placeholder="YYYY-YYYY" required>
				</div>
			</div>
		</div>
		<div class="form-group col-md-2">
			<label class="required">Start Date</label>
			<div class="col-md-12 datepicker-box">
				<input type="text" class="form-control form-control-sm freeze" id="startDateId" name="fyStartDate" readonly value="<fmt:formatDate pattern="dd/MM/yyyy" value="${finYearData.startDate}"/>  "/>
			</div>
		</div>
		
		<div class="form-group col-md-2">
			<label class="required">End Date</label>
			<div class="col-md-12 datepicker-box">
				<input type="text" class="form-control form-control-sm freeze" id="endDateId" name="fyEndDate" readonly value="<fmt:formatDate pattern="dd/MM/yyyy" value="${finYearData.endDate}"/> "/>
			</div>
		</div>
		
		<div class="form-group col-md-2">
			<label class="required">Is Current Year</label>
			<div class="col-md-12">
				<select class="form-control form-select" id="currFinYearId" name="currFinYear">
					<option value="0"><spring:message code="label.common.select"></spring:message></option>
					<option value="YES" ${finYearData.currFinYear eq 'TRUE' ? 'selected' : ''}>Yes</option>
					<option value="NO" ${finYearData.currFinYear eq 'FALSE' ? 'selected' : ''}>No</option>
				</select>
			</div>
		</div>
		
		<div class="col-md-12 mt-2 text-center">
			<button type="button" class="btn-submit" onclick="submitForm('${empty finYearData.finyearId ? 'Submit' : 'Update'}')"><spring:message code="${empty finYearData.finyearId ? 'site.common.submit' : 'site.common.update'}"/></button>
			<a href="${contextPath}/home" type="button" class="btn btn-warning btn-sm">Back</a>
		</div>
	</div>
</form>	
</div>
<div class="cardcontainer cardContainerNotFirst">
	<div class="row mt-2"> 
	
		<div class="col-md-12">
		
			<div class="card full-height">
			
				<div class="card-header">
					<h4 class="card-title"> Financial Year List</h4>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="display table table-bordered table-hover exportbtn" style="width: 100%;table-layout: fixed;" id="tableId1">
								<thead>
									<tr>
										<th>Sl.No</th>
										<th>Financial Year</th>
										<th>Start Date</th>
										<th>End Date</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${finYearList}" var="finYear" varStatus="count">    
										<tr> 
										  	<td>${count.count}</td>
											<td>${finYear.finYear}</td>
											<td><fmt:formatDate value="${finYear.startDate}" pattern="dd/MM/yyyy"/></td>
										 	<td><fmt:formatDate value="${finYear.endDate}" pattern="dd/MM/yyyy"/></td>
										 	<td><button	type="button" onclick="editfinYearDtls(${finYear.finyearId})"class="btn btn-info btn-sm" title="edit" style="height:22px; width:26px; padding: 0px 7px !important;line-height: 7px"> <i class=" fa fa-edit " aria-hidden="true"></i></button></td>
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

<form action = "${contextPath}/mst/editFinancialYear" method="get" id="editFinYearForm">
	<input type="hidden" name="finYrId" id="finYrId">
</form> 

<script>


function submitForm(status){
	var finYear = $("#finYearId").val();
	if (finYear == '') {
        bootbox.alert('Financial Year is required.');
        return false;
    }else{
    	var message = (status == 'SUBMIT' ? 'submit' : 'update');
    	bootbox.confirm("Do you want to "+message+" ?",
				
			function(result){
				if(result){
					
					$('#financialYearForm').submit();
				}
		});
	}
}
 function validateAndSetDates() {
	 debugger;
            const finYearInput = document.getElementById('finYearId').value;
            const startDateInput = document.getElementById('startDateId');
            const endDateInput = document.getElementById('endDateId');
            const finYearRegex = /^\d{4}-\d{4}$/;

            if (finYearRegex.test(finYearInput)) {
                const years = finYearInput.split('-');
                const startYear = parseInt(years[0]);
                const endYear = parseInt(years[1]);

                if (!isNaN(startYear) && !isNaN(endYear) && endYear === startYear + 1) {
                	 
                	 document.getElementById('startDateId').value = `01/04/`+startYear;
                	 document.getElementById('endDateId').value = `31/03/`+endYear;

                   
                    document.getElementById('finYearId').addEventListener('input', validateAndSetDates);
                } else {
                    bootbox.alert('Invalid Financial Year range. The second year should be the next year of the first year.');
                    $('#finYearId').val('');
                   
                }
            } else {
                bootbox.alert('Invalid Financial Year format. Please use YYYY-YYYY format.');
                $('#finYearId').val('');
              
            }
        }
 
 function editfinYearDtls(id){
// 	 $("#finYearId").val(enc_password(id));
// 	 $("#editFinYearForm").append('<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />');
	 
     $("#finYrId").val(id);
		showLoader();
	 $("#editFinYearForm").submit();
 }
 
 function DupliCateFinYearCheck(that){
	 debugger;
	 var finYear = $('#' + that.id).val();
	    $.ajax({
	        type: "GET",
	        url: "${contextPath}/mst/duplicate_fynYear",
	        data: {
	            "finYear": finYear,
	        },
	        success: function (response) {
	            var val = JSON.parse(response);
	            if (val.isDuplicate) {
	                if (finYear === val.fynYear) {
	                    bootbox.alert("Financial year already exist.");
	                    $("#" + that.id).val('');
	                    $("#startDateId").val('');
	                    $("#endDateId").val('');
	                }
	            }
	        },
	        error: function (error) {
	            bootbox.alert('Something went wrong');
	        }
	    });
 }
    </script>