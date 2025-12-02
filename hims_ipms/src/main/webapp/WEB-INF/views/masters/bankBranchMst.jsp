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
<div class="breadcrumb_conatiner">
	<div class="col-md-6">
		<h4 class="change-color">
			Add Bank Branch
		</h4>
	</div>
	<div class="col-md-6">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">

				<li class="breadcrumb-item"><a href="${contextPath}/home"><i
						class="fa fa-home"></i></a></li>

				<li class="breadcrumb-item active" aria-current="page">Bank Branch</li>
			</ol>
		</nav>
	</div>
</div>

<div class="row">

	<div class="col-md-12">

		<div class="card">
			<div class="card-header">


				<h5 class="card-title">Bank Branch Details </h5>
			</div>
			<div class="card-body">
	
    <form class="form-horizontal" action="${contextPath}/mst/saveBankBranch" method="POST" id="bankBranchMstForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="bankBranchMaster.bankBranchId" value="${branchMstData.bankBranchId}">
        <div class="row">
            <div class="form-group col-md-3">
                <label class="required">Bank Name :</label>
                <div id="hideUserId">
                    <div class="col-md-12">
                        <select name="bankBranchMaster.bankId.bankId" id="bankId" class="form-control selectpicker" required="required" data-live-search="true">
                            <option value="0"><spring:message code="label.common.select"></spring:message></option>
                            <c:forEach var="bank" items="${bankList}">
                                <option value="${bank.bankId}" ${bank.bankId eq branchMstData.bankId.bankId ? 'selected' : ''}>${bank.bankName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group col-md-3">
                <label class="required">Branch Name :</label>
                <div class="col-md-12">
                    <input type="text" id="branchName" name="bankBranchMaster.branchName" placeholder="Max 100 chars" class="form-control form-control-sm" required maxlength="100" value="${branchMstData.branchName}" oninput="this.value = this.value.toUpperCase()">
                </div>
            </div>
            <div class="form-group col-md-3">
                <label class="required">IFSC Code :</label>
                <div class="col-md-12">
                    <input type="text" id="ifscCode" name="bankBranchMaster.ifscCode" class="form-control form-control-sm" required placeholder="Max 11 chars" maxlength="11" onchange="validateIfscCode(this),DupliCateIfscCodeCheck(this)" value="${branchMstData.ifscCode}" oninput="this.value = this.value.toUpperCase()">
                </div>
            </div>
            <div class="form-group col-md-3">
                <label class="col-md-12" for="mobile">Branch Contact No. :</label>
                <div class="col-md-12">
                    <input type="text" name="bankBranchMaster.branchMobile" id="branchMobile" class="form-control form-control-sm NumbersOnly mobileNoText" placeholder="Max 10 chars" maxlength="10" onchange="validateMobileNo(this);" value="${branchMstData.branchMobile}">
                </div>
            </div>
            <div class="form-group col-md-3">
                <label class="col-md-12" for="Email">Branch Email Id :</label>
                <div class="col-md-12">
                    <input type="email" name="bankBranchMaster.branchEmail" id="branchEmail" class="form-control form-control-sm emailsOnly" placeholder="Max 100 chars" onchange="validateEmail(this);" maxlength="150" value="${branchMstData.branchEmail}">
                </div>
            </div>
            <div class="form-group col-md-3">
                <label class="col-md-12 required" >Address :</label>
                <div class="col-md-12">
                    <input type="text" name="bankBranchMaster.branchAddress" id="branchAddress" placeholder="Max 100 chars" class="form-control form-control-sm" maxlength="100" value="${branchMstData.branchAddress}">
                </div>
            </div>
        </div>

        <div class="col-md-12 mt-2 text-center">
            <button type="button" class="btn btn-submit" onclick="submitForm('${empty branchMstData.bankBranchId ? 'SAVE' : 'UPDATE'}')"><spring:message code="${empty branchMstData.bankBranchId ? 'site.common.submit' : 'site.common.update'}"/></button>
            <button type="button" class="btn btn-cancel btn-warning" onclick="backToModule()">Back</button>
        </div>
    </form>
</div>
</div>
</div>
</div>

<div class="cardcontainer cardContainerNotFirst">
	<div class="row mt-3"> 
	
		<div class="col-md-12">
		
			<div class="card full-height">
			
				<div class="card-header">
					<h4 class="card-title"> Bank Branch Details</h4>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="display table table-bordered table-hover exportbtn" style="width: 100%;table-layout: fixed;" id="tableId1">
                            <thead>
                                <tr>
                                    <th class="text-center" style="max-width:20px;">Sl.No</th>
                                    <th>Bank Name</th>
                                    <th>Branch Name</th>
                                    <th>IFSC Code</th>
                                    <th>Address</th>
                                    <th class="text-center" style="max-width:20px;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${branchList}" var="branch" varStatus="count">    
                                    <tr> 
                                        <td class="text-center">${count.count}</td>
                                        <td>${branch.bankId.bankName}</td>
                                        <td>${branch.branchName}</td>
                                        <td>${branch.ifscCode}</td>
                                        <td>${branch.branchAddress}</td>
                                        <td class="text-center"><button	type="button" onclick="editBankBranchDtls(${branch.bankBranchId})"class=" btn btn-warning btn-sm" title="Edit"> <i class="fas fa-edit"></i></button></td>
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

<form action = "${contextPath}/mst/editBankBranch" method="get" id="editBankBranchForm">
	<input type="hidden" name="bankBranchId" id="branchId">
</form> 

<script>


function submitForm(status){
	debugger;
	  var bid = $('#bankId').val();
      var branchName = $('#branchName').val();
      var fCode = $('#ifscCode').val();
      var umob = $('#branchMobile').val();
      var email = $('#branchEmail').val();
      var address = $('#branchAddress').val();
     

      if (bid == "0") {
          bootbox.alert("Please select bank name.");
          return false;
      }

      if (branchName.trim() == "") {
          bootbox.alert("Please provide branch name.");
          return false;
      }

      if (fCode.trim() == "") {
          bootbox.alert("Please provide IFSC code.");
          return false;
      }
      if (address.trim() == "") {
          bootbox.alert("Please provide Branch Address.");
          return false;
      }
      
      else{
  		var msg = status === 'SAVE' ? 'save' : 'update';
  		
  		bootbox.confirm("Do you want to " +msg+" Branch  data ?",
  			function(result){
  				if(result){
  				showLoader();
  				$('#bankBranchMstForm').submit();
  			}
  		});
  	}
}
function validateIfscCode(input) {
    var ifscCode = input.value;
    var errorElement = document.getElementById('ifscCodeError');
    if (ifscCode.length === 11 && ifscCode[4] === '0') {
        return true;
    } else {
    	bootbox.alert("Invalid IFSC Code.");
        $("#ifscCode").val('');
        return false;
    }
}
 
 function editBankBranchDtls(id){
// 	 $("#finYearId").val(enc_password(id));
// 	 $("#editFinYearForm").append('<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />');
	 
     $("#branchId").val(id);
		showLoader();
	 $("#editBankBranchForm").submit();
 }
 
 function DupliCateIfscCodeCheck(that){
	 debugger;
	 var ifscCode = $('#' + that.id).val();
	    $.ajax({
	        type: "GET",
	        url: "${contextPath}/mst/duplicate_ifscCode",
	        data: {
	            "ifscCode": ifscCode,
	        },
	        success: function (response) {
	            var val = JSON.parse(response);
	            if (val.isDuplicate) {
	                if (ifscCode === val.ifscCode) {
	                    bootbox.alert("IFSC code already exist.");
	                    $("#" + that.id).val('');
	                    $("#ifscCode").val('');
	                }
	            }
	        },
	        error: function (error) {
	            bootbox.alert('Something went wrong');
	        }
	    });
 }
    </script>