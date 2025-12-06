<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%
    String originalURI = (String) request.getAttribute("javax.servlet.forward.request_uri");
    if (originalURI == null) {
        originalURI = request.getRequestURI(); // Fallback to normal URI
    }
    pageContext.setAttribute("originalURI", originalURI);
%>
<!-- Display original URI -->
<c:set var="originalURI" value="${originalURI}" />

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:if test="${node.isDisplay eq true}">
    <li class="${originalURI eq contextPath.concat(node.menuURL) ? 'activeMenu' : ''}">
        <div class="sidebar-item">
            <c:if test="${node.isParent eq true}">
                <a href="javascript:void(0)" class="sidebar-link collapsed" data-bs-toggle="collapse" data-bs-target="#pages${node.menuId}"
                    aria-expanded="false" aria-controls="pages${node.menuId}">
                    <i class='${node.menuIcon}'></i>
                    ${node.menuText}
                </a>
            </c:if>
            <c:if test="${node.isParent eq false}">
                <a href="${contextPath}${node.menuURL}" class="sidebar-link">
                    <i class="${node.menuIcon}" aria-hidden="true"></i>
                    ${node.menuText}
                </a>
            </c:if>
        </div>
        <c:if test="${node.isParent eq true}">
            <ul id="pages${node.menuId}" class="sidebar-dropdown list-unstyled collapse rightside" >
                <c:forEach items="${node.children}" var="menu">
                    <c:set var="node" value="${menu}" scope="request" />
                    <li>
                        <jsp:include page="node.jsp" />
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </li>
</c:if>



