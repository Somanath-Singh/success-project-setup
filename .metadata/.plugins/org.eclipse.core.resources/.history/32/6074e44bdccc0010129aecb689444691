<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:if test="${node.isDisplay eq true}">
	<c:choose>
		<c:when test="${node.isParent eq true}">
			<li class="nav-item submenu"><a data-toggle="collapse"
				href="#mnu${node.menuId}" aria-expanded="false"> <i
					class="${node.menuIcon}"></i>
					<p>${node.menuText}</p> <span class="caret"></span>
			</a>
				<div class="collapse" id="mnu${node.menuId}">
					<ul class="nav nav-collapse">
						<c:forEach items="${node.children}" var="menu">
							<c:set var="node" value="${menu}" scope="request" />
							<jsp:include page="node.jsp" />
						</c:forEach>
					</ul>
				</div></li>
		</c:when>

		<c:when test="${node.isParent eq false}">
			<li class="nav-item"><a href="${contextPath}${node.menuURL}">
					<i class="${node.menuIcon}"></i>
					<p>${node.menuText}</p>
			</a></li>
		</c:when>
	</c:choose>
</c:if>