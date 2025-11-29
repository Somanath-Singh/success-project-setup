<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<sec:authentication var="principal" property="principal" />
<style>
.fc-dd {
	position: relative;
}

.fc-dd > i {
	position: absolute;
    bottom: 8px;
    z-index: 999;
    right: 1px;
}
</style>

<aside class="sidebar">
      <h3 class="menu-letter">MENU</h3>
      <ul class="nav-links">
        <li class="active">
          <a href="${contextPath}/home">
            <i class='bx bx-grid-alt'></i>
            <span class="link_name">Dashboard</span>
          </a>
        </li>
        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-pointer'></i>
              <span class="link_name">Beneficiary Registration</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="shg_registration.html">SHG Registration</a></li>
            <li><a href="individual_registration.html">Individual
                Registration</a>
            </li>
            <li><a href="beneficiary_list.html">Beneficiary List</a></li>
          </ul>
        </li>
        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-user-plus'></i>
              <span class="link_name">Facility Management</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="facility_management_add_facility.html">Add
                Facility</a>
            </li>
          </ul>
        </li>
        <li>
          <div class="iocn-link">
            <a href="#">
              <i class='bx bx-crop'></i>
              <span class="link_name">Facility Master</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="facility_master_add_section.html">Add
                Section</a>
            </li>
            <li><a href="facility_master_add_shelves.html">Add Shelve</a></li>
            <li><a href="facility_master_add_rack.html">Add Rack</a></li>
          </ul>
        </li>
        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-message-square-dots'></i>
              <span class="link_name">Quality Check</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="quality_check_quality_parameters.html">Quality
                Parameters</a>
            </li>
            <li><a href="quality_check_order_quality_check.html">Order Quality
                Check</a>
            </li>
          </ul>
        </li>
        <li>
          <div class="iocn-link">
            <a href="javascript:void(0)">
              <i class='bx bx-message-square-dots'></i>
              <span class="link_name">Product Management</span>
            </a>
            <i class='bx bxs-chevron-down arrow'></i>
          </div>
          <ul class="sub-menu">
            <li><a href="Product Management_Add Product.html">Add Product</a>
            </li>
            <li><a href="Product Barcode_Generate Barcode.html">Product
                Barcode</a>
            </li>
          </ul>
        </li>

      </ul>
    </aside>



<!--<section class="sidebar_conatiner">
<div class="sidebar">
			<h5>
              <span>Navigation Menu</span>
              <button type="btn" id="btn_navToggle">
                <i class="fa-solid fa-angles-left"></i>
              </button>
            </h5>
	<ul class="nav-links mt-2">

		<li>
                <a href="${contextPath}/home">
                  <i class="fa-solid fa-circle-right"></i>
                  <span class="link_name">Dashboard</span>
                </a>
              </li>
		<sec:authorize access="isAuthenticated()">
			  <c:forEach items="${sessionScope.USER_MENUS}" var="node">
				  <c:if test="${node.isDisplay eq true}">
					  <li>
						  <c:choose>
							  <c:when test="${node.isParent eq true}">
								<div class="iocn-link">
								  <a href="javascript:void(0)">
									<i class='${node.menuIcon}'></i>
									<span class="link_name">${node.menuText}</span>
								  </a>
								  <i class='bx bxs-chevron-down arrow'></i>
								</div>
								
								
								<ul class="sub-menu">
									<c:forEach items="${node.children}" var="menu">
									  <c:set var="node" value="${menu}" scope="request" />
									  <jsp:include page="node.jsp" />
								  </c:forEach>
								</ul>
						   </c:when>
						<c:when test="${node.isParent eq false}">
						   <li>
							   <a href="${contextPath}${node.menuURL}">
								   <i class="${node.menuIcon}"></i>
								  <p>${node.menuText}</p>
							  </a>
						  </li>
						 </c:when>
					  </c:choose>
				  </li>
			  </c:if>
		  </c:forEach>
	    </sec:authorize>
	</ul>
  </div>
</section>-->



<form method="post" action="${contextPath}/umt/switchRole" id="switchFrm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="roleId" id="switchRoleId" value="" />
	<input type="hidden" name="appCode" id="switchAppCode" value="" />
</form>


  <script>

	function switchRole() {
	    var roleId = $("#roleId").val();
		var appCode = $("#appCode").val();
		$("#switchRoleId").val(roleId);
		$("#switchAppCode").val(appCode);
		$("#switchFrm").submit();

	}
	
  </script>