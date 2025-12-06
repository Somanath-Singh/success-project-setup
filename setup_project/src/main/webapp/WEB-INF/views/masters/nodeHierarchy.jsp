<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:if test="${not empty childHierarchy and childHierarchy.size() gt 0}">
    <c:forEach items="${childHierarchy}" var="child">
        <li style="">
            <button type="button" aria-pressed="false" data-js="node">
                <input type="hidden" value="${child.id}" />
                <input type="hidden" value="${child.levelHeight}" />
                <select name="" class="form-control form-control-sm makeNodeReadOnly"  onchange="checkInSameLevelIsExist(this)">
                    <option value="">Select Organization</option>
                    <c:forEach items="${organizationLevelList}" var="organization">
                        <option value="${organization.levelCode}" <c:if test="${organization.levelCode eq child.levelCode}">selected</c:if>>${organization.levelCode}</option>
                    </c:forEach>
                </select>
            </button>
            <c:choose>
              <c:when test="${child.mappingType eq 'ONE-TO-ONE'}">
                <i class="fas fa-link" onclick="getLevelSpecificEntity(this)" style=" top: 28px;position: absolute;"></i>
              </c:when>
              <c:otherwise>
                <i class="fas fa-unlink" style=" top: 28px;position: absolute;"></i>
              </c:otherwise>
            </c:choose>
            <ul>
                <c:set var="childHierarchy" value="${child.children}" scope="request" />
                <jsp:include page="nodeHierarchy.jsp" />
            </ul>
        </li>
    </c:forEach>
</c:if>




