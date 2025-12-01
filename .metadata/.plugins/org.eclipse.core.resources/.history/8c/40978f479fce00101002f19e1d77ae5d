<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link href="${contextPath}/assets/css/dashboard.css" rel="stylesheet"
	type="text/css" />
<sec:authentication var="principal" property="principal" />
<c:set var="moduleCodes" value="" />
<style>
.coming-soon {
	text-align: center;
	padding: 50px 30px;
	transition: transform 0.3s ease;
}

.coming-soon:hover {
	transform: scale(1.02);
}

.coming-soon h1 {
	font-size: 2rem;
	color: #343a40;
	margin-bottom: 10px;
}
</style>

<div class="container-fluid">
	<div class="card  coming-soon mt-5">
		<h1>Dash board Coming Soon</h1>
	</div>
	<div class="row mt-3">
		<div class="col-lg-12 col-md-12 mb-2">
			<div class="card">
				<div
					class="card-body d-flex justify-content-between align-items-center pt-1 pb-1">
					<c:set var="startYear"
						value="${fn:substringBefore(currFinYear, '-')}" />
					<c:set var="endYear" value="${fn:substringAfter(currFinYear, '-')}" />
					<c:set var="shortEndYear"
						value="${fn:substring(endYear, 2, fn:length(endYear))}" />
					<c:set var="shortYearRange" value="${startYear}-${shortEndYear}" />

					<h5 class="mb-0">
						Financial Year: <span id="finSpanId">${shortYearRange}</span>
					</h5>
					<div class="row align-items-center">
						<div class="col-md-7">
							<label for="financialYear" class="mb-0 form-label">Select
								Financial Year</label>
						</div>
						<div class="col-md-5">
							<select id="finYearId" class="form-control form-select"
								onchange="fetchDashboard()">
								<c:forEach items="${finYearList}" var="fin">
									<option value="${fin.finYear}"
										${fin.finYear eq finYear ? 'selected' : ''}>${fin.finYear}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row align-items-center">
						<div class="col-auto">
							<label class="mb-0 form-label">Select Module</label>
						</div>
						<div class="col-auto">
							<select id="moduleCode" class="form-control form-select"
								onchange="fetchDashboard()">
								<c:forEach var="module" items="${USER_MODULES}">
									<option value="${module.moduleCode}">${module.moduleName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

<script src="${contextPath}/assets/js/apexChart/apexcharts.min.js"></script>
<script src="${contextPath}/assets/charts/dashboardCommon.js"></script>
<script src="${contextPath}/assets/appJs/worker/BoxInfoPresenter.js"></script>
<script src="${contextPath}/assets/js/apexChart/payroll.js"></script>
<script src="${contextPath}/assets/js/apexChart/payrollMgmt.js"></script>
<script src="${contextPath}/assets/js/apexChart/grievance.js"></script>
<script src="${contextPath}/assets/js/apexChart/grievanceCategory.js"></script>
<script src="${contextPath}/assets/js/apexChart/budgetAllocation.js"></script>
<script src="${contextPath}/assets/js/apexChart/fundUtilizationAmountScheme.js"></script>
<script src="${contextPath}/assets/js/apexChart/fundUtilizationProjectScheme.js"></script>
<script src="${contextPath}/assets/js/apexChart/fundUtilizationAmount.js"></script>
<script src="${contextPath}/assets/js/apexChart/fundUtilizationProject.js"></script>
<script src="${contextPath}/assets/js/apexChart/hoardingmanagement.js"></script>
<script src="${contextPath}/assets/js/apexChart/rentCollection.js"></script>

