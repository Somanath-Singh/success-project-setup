<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="content">
	<div class="page-inner">
		<div class="page-header">
			<h4 class="page-title">
				<spring:message code="site.dashboard" />
			</h4>
			<ul class="breadcrumbs">
				<li class="nav-home"><a href="${contextPath}/"> <i
						class="flaticon-home"></i>
				</a></li>

				<!-- 				<li class="separator">
					<i class="flaticon-right-arrow"></i>
				</li>
				<li class="nav-item">
					<a href="#">Pages</a>
				</li>
				<li class="separator">
					<i class="flaticon-right-arrow"></i>
				</li>
				<li class="nav-item">
					<a href="#">Starter Page</a>
				</li> -->
			</ul>
		</div>
		
		<div class="page-category">
			Wavy Wow!

			<c:if test="${not empty lstState}">
				<c:forEach items="${lstState}" var="state">
					<li>${state.stateName}</li>
				</c:forEach>
			</c:if>

			<c:if test="${not empty lstDistrict}">
				<c:forEach items="${lstDistrict}" var="district">
					<li>${district.districtName}</li>
				</c:forEach>
			</c:if>
		</div>
	</div>
</div>