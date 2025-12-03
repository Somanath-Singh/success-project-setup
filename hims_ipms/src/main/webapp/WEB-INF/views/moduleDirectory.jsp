<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script>

<style>

/* ────────────────────────────────────────────────
   DASHBOARD CARDS LAYOUT – FULLY :root DRIVEN
   ──────────────────────────────────────────────── */
body {
    margin: 0;
    font-family: 'Segoe UI', sans-serif;
}

.navbar {
    background: var(--primary);
    height: 100px !important;
    padding: 13px 80px !important;
}

.navbar .riteside li:last-child p { color: var(--white); }
.navbar .riteside li:last-child span { color: #aebbe9 !important; } /* kept as-is – light purple accent */

.page-title {
    color: #ffe3ee;
    font-size: 23px;
    font-weight: 500;
    margin-left: 15px;
}

.main {
    position: relative;
    width: 100%;
    left: 0;
    height: 100vh;
    padding: 0;
}

.main::before {
    content: '';
    position: absolute;
    inset: 0;
    /* background: url('${contextPath}/assets/images/Dashboard.png') center/cover no-repeat; */
    backdrop-filter: blur(6px);
    -webkit-backdrop-filter: blur(6px);
    z-index: 0;
}

.sidebarBtn,
#toggleFullscreen,
.dot-icon {
    display: none !important;
}

.clickable-cards-stack {
    width: 100%;
    display: flex;
    padding: 100px 60px;
    position: relative;
    z-index: 1;
    justify-content: center;
}

.module-row {
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
    gap: 30px;
    width: 100%;
    max-width: 1600px;
}

.single-small-card {
    width: 260px;
    padding: 30px 15px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 15px;
    background: var(--card-bg);
    border-radius: 12px;
    box-shadow: 0 5px 15px rgba(0,0,0,0.1);
    transition: transform 0.3s ease;
    border-top: 10px solid var(--primary);
    position: relative;
    overflow: hidden;
}

.single-small-card:hover {
    transform: translateY(-12px);
    box-shadow: 0 25px 50px rgba(48,133,254,0.25);
}

/* Pulse rings */
.single-small-card:hover::before,
.single-small-card:hover::after {
    content: '';
    position: absolute;
    inset: -8px;
    border: 4px solid var(--primary);
    border-radius: 16px;
    animation: pulseRing 1.6s ease-out infinite;
    pointer-events: none;
    opacity: 0;
}
.single-small-card:hover::after {
    animation-delay: 0.4s;
    border-color: var(--primary-light);
}

@keyframes pulseRing {
    0%   { transform: scale(0.9); opacity: 0.7; }
    70%  { transform: scale(1.05); opacity: 0.4; }
    100% { transform: scale(1.15); opacity: 0; }
}

.main-img-div {
    width: 60px;
}

.main-img-div img {
    width: 100%;
    padding: 5px;
    border-radius: 10px;
    background: var(--gray-200);
    box-shadow: 
        inset 3px 3px 5px rgba(210,255,255,0.11),
        inset -3px -3px 10px rgba(15,14,71,0.23),
        0 0 10px rgba(188,255,255,0.29);
    transition: var(--transition);
}

.single-small-card-content {
    text-align: center;
    transition: var(--transition);
}

.single-small-card-content h3 {
    color: var(--dark);
    font-size: 15px;
    font-weight: 500;
    line-height: 20px;
    letter-spacing: 0.5px;
    margin: 0;
}

.single-small-card .single-smallHeader h5 {
    font-weight: 500;
    font-size: 14px;
    color: var(--primary-light);
    background: var(--dark);
    border: 1px solid var(--primary-dark);
    padding: 5px 15px;
    border-radius: 26px;
    display: flex;
    align-items: center;
    gap: 10px;
    min-width: 90px;
    cursor: pointer;
    position: relative;
    transition: var(--transition);
}

.single-small-card .single-smallHeader h5 i {
    position: absolute;
    right: 20px;
    transition: var(--transition);
}

.single-small-card .single-smallHeader h5:hover i {
    right: 7px;
    opacity: 0;
}

.dropdown-toggle::after {
    color: var(--dark) !important;
    font-size: 18px;
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translateY(-50%);
}
</style>

<div class="clickable-cards-stack">
	<%@ include file="/WEB-INF/views/message.jsp"%>
	<div class="row module-row">
		<c:forEach var="module" items="${USER_MODULES}">
			<div class="col-md-2">
				<a href="javascript:void(0);"
					onclick="navigateToModule('${module.moduleCode}' , ${module.isUnderDevelopment})"
					class="single-small-card">
					<div class="main-img-div">
						<img alt="img-1"
							src="${contextPath}/assets/images/appIcons/${module.moduleIcon}"
							class="main-card-img">
					</div>
					<div class="single-small-card-content">
						<h3>${module.moduleName}</h3>
						<%-- <h4>${module.moduleDescription}</h4> --%>
						<c:choose>
							<c:when test="${module.isUnderDevelopment == true}">
								<span class="text-danger">Under Development</span>
								<i class="fas fa-laptop-code text-danger"></i>
							</c:when>
						</c:choose>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>
</div>


<form id="moduleForm" action="${contextPath}/moduleDirectory"
	method="post">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> <input type="hidden" name="moduleCode"
		id="moduleId" />
</form>

<script>
	function navigateToModule(moduleCode, isUnderDevelopment){
		if(isUnderDevelopment){
			bootbox.alert("This module is under development. We know you are excited to use this module, but please wait for some time. We will notify you once it is ready.");
			return;
		}


		if(moduleCode != null && moduleCode != ''){
			localStorage.setItem('currentModuleCode', moduleCode);
			$('#moduleId').val(enc_password(moduleCode));
			$('#moduleForm').submit();
		}
	}
	
</script>