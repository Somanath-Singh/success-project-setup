<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${contextPath}/assets/vendor/jquery_autocomplete/jquery.autocomplete.css" />
<%-- <script src="${contextPath}/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script> --%>
<script src="${contextPath}/assets/vendor/jquery_autocomplete/jquery.autocomplete.min.js"></script>
<script src="${contextPath}/assets/appJs/framework/englishTokenizer.js"></script>
<script src="${contextPath}/assets/appJs/admin/aclConfig/aclConfig.js"></script>
<div class="breadcrumb_conatiner">
              <div class="col-md-6">
                <h4 class="change-color">Configure Access</h4>
              </div>
              <div class="col-md-6">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">
                    
                    <li class="breadcrumb-item"><a href="${contextPath}/home"><i class="fa fa-home"></i></a></li>
                 
						    <li class="breadcrumb-item active" aria-current="page">Configure Access</li>
                  </ol>
                </nav>
              </div>
            </div>
	
		<div class="row mt-2">
			<div class="col-md-12">

                  <div class="card">
                    <div class="card-header">
                    <h5 class="card-title">Configure Access</h5>
                    </div>
        <div class="card-body">
	
	<div class="row">
	     <div class="form-group col-md-2">
	        <label class="required" ><spring:message code="site.admin.user.id"></spring:message> :</label>
	        <div class="col-md-12">
	        	 <input type="hidden" id="userId" name="userId" />
	           <input type="text" id="userName" name="userName" class="form-control form-control-sm" autocomplete="off">
	        </div>
	     </div>
	     <div class="form-group col-md-2">
	        <label class="required" ><spring:message code="site.admin.acl.role"></spring:message> :</label>
	        <div class="col-md-12">
	        	 <select class="form-select" id="roleId" name="roleId" disabled>
	        	 	<option value="" selected="selected"><spring:message code="label.common.select"></spring:message></option>
	        	 </select>
	        </div>
	     </div>
	     <div class="form-group col-md-2">
	        <label class="required" ><spring:message code="site.admin.acl.level"></spring:message> :</label>
	        <div class="col-md-12">
	        	 <select class="form-control form-select form-control-sm" id="accessLevelId" name="accessLevelId" disabled>
	        	 	<option value="" selected="selected"><spring:message code="label.common.select"></spring:message></option>
	        	 </select>
	        </div>
	     </div>
	     <div class="form-group col-md-2">
	   		 <label>&nbsp;</label>
	   		 <div class="col-md-12">
	   		 	<button class="btn btn-primary btn-sm" id="btnSearch">Go</button>
	   		 </div>
	     </div>
	</div>

	<div class="col-md-12 table-responsive">
		<!--  BEG Custom Dt Header -->
		<div class="dt-header  d-flex justify-content-between align-items-center">
			<div class="dt-header-left">Show 
				<select class="dt-control" id="pageSize">
					<option value="10">10</option>
					<option value="25">25</option>
					<option value="50">50</option>
					<option value="100">100</option>
				</select>
				entries
			</div>
			<div class="dt-header-right">
				Search :<input type="text" class="dt-control" id="searchTerm" name="searchTerm" value="" />
			</div>
		</div>
		<!-- END Custom Dt Header -->
		<div id="pnl">No data to display.</div>
		<!--  BEG Custom Dt Footer -->
		<div class="dt-footer d-flex justify-content-between align-items-center">
			<div class="dt-footer-left">
				Showing page <span id="dt-counter"></span>
			</div>
			<div class="dt-footer-right">
				<a href="#" id="dt-start" class="dt-navigate">First</a>
				<a href="#" id="dt-previous" class="dt-navigate">Previous</a>
				<a href="#" id="dt-next" class="dt-navigate">Next</a>
				<a href="#" id="dt-end" class="dt-navigate">Last</a>
			</div>
		</div>
		<!-- END Custom Dt Header -->
	</div>
</div>
</div>
</div>
</div>


<script type="text/javascript">
	$().ready(function(){
		var aclConfigHelper = new ACLConfigPageController({ 
			csrf : '${_csrf.token}'
		});
		
		aclConfigHelper.setupAutoComplete();
		
		$(document).on('change','.x-config', function(){
			aclConfigHelper.saveConfig(this);
		});
		
		$('#btnSearch').click(function(){
			aclConfigHelper.fetchConfigData();
		});

	});
</script>