<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<link href="${contextPath}/assets/css/dashboard.css" rel="stylesheet" type="text/css" />
<script src="${contextPath}/assets/appJs/validation/common-utils.js"></script>

<div class="breadcrumb_conatiner">
	<div class="col-md-6">
		<h4 class="change-color">Add Item Of Work</h4>
	</div>
	<div class="col-md-6">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${contextPath}/home"><i class="fa fa-home"></i></a></li>
				<li class="breadcrumb-item active" aria-current="page">Add Item Of Work</li>
			</ol>
		</nav>
	</div>
</div>

<div class="card">
	<div class="card-header">
		<c:choose>
			<c:when test="${not empty item.id}">
				<h5 class="card-title">Update Item Of Work</h5>
			</c:when>
			<c:otherwise>
				<h5 class="card-title">Add Item Of Work</h5>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="card-body">
		<form action="${contextPath}/itemOfWork/save" id="itemOfWorkForm" method="post">
			<input type="hidden" id="cipherText" name="cipherText">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="hidden" id="id" name="id" value="${item.id}" />
			<input type="hidden" id="isActive" name="isActive" value="${item.isActive}" />

			<div class="row">
				<div class="col-md-3">
					<div class="form-group">
						<label class="col-md-12 required" for="name">Work Item Name :</label>
						<div class="col-md-12">
							<input type="text" id="name" name="name" value="${item.name}" maxlength="48" class="form-control form-control-sm AlphaNumericOnly disablecopypaste" required="required" onkeyup="changeCase(this.value,'name');">
						</div>
					</div>
				</div>

				<div class="col-md-4">
					<div class="form-group">
					    <label class="col-md-12" for="description">Description :</label>
					    <div class="col-md-12">
					        <textarea id="description" name="description" 
					                  maxlength="120" 
					                  class="form-control form-control-sm disablecopypaste"
					                  rows="3">${item.description}</textarea>
					    </div>
					</div>
				</div>
						
			<div class="row">
				<div class="col-md-12 text-center" style="margin-top: 20px;">
					<c:choose>
						<c:when test="${not empty item}">
							<input type="button" value="Update" class="btn btn-success btn-sm" onclick="submitItemData()">
							<a href="${contextPath}/itemOfWork/add" type="button" class="btn btn-warning btn-sm">Back</a>
						</c:when>
						<c:otherwise>
							<input type="button" value="Submit" class="btn btn-success btn-sm" onclick="submitItemData()">
							<a href="${contextPath}/home" type="button" class="btn btn-warning btn-sm">Back</a>
							<button type="reset" class="btn btn-danger btn-sm">Reset</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		   </div>
		</form>
	</div>
</div>

<div class="col-md-12 mt-3">
	<c:if test="${empty item}">
		<div class="card">
			<div class="card-header">
				<h5 class="card-title">Item Of Work List</h5>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table id="basic-datatables" class="table table-bordered table-hover exportbtn">
						<thead>
							<tr>
								<th class="text-center" style="width:60px">Sl.No</th>
								<th>Work Item Name</th>
								<th>Description</th>
								<th class="text-center">Status</th>
								<th class="text-center">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="i" items="${itemList}" varStatus="count">
								<tr>
									<td class="text-center">${count.count}</td>
									<td>${i.name}</td>
									<td>${i.description}</td>
									<td class="text-center">
									    <button type="button"
									            class="btn btn-sm toggleStatusBtn ${i.isActive ? 'btn-success' : 'btn-danger'}"
									            onclick="toggleStatus(${i.id}, ${i.isActive})">
									        <i class="fa ${i.isActive ? 'fa-lock' : 'fa-unlock'}"></i>
									    </button>
									</td>
									<td class="text-center">
										<button class="btn btn-warning btn-sm" onclick="editItemForm(${i.id})" title="Edit">
											<i class="fas fa-edit"></i>
										</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</c:if>
</div>

<script>
	function submitItemData() {
		var id = $("#id").val();
		var name = $("#name").val();
		var description = $("#description").val();
		var status = $("#status").val();

		if(name.trim() === ""){
			bootbox.alert("Please Enter Item Of Work Name");
			return false;
		}else{
			$.ajax({
			    type: "GET",
			    url: "${contextPath}/itemOfWork/validate-name",
			    data: { 
			    	name: name, 
			    	id: id 
			    	},
			    success: function(isDuplicate){
			        if(isDuplicate === true){
			            bootbox.alert('Work Item name already exists.');
			            $('#name').val('').focus();
			            return false;
			        }else{
			            submitItemForm();
			        }
			    },
			    error: function(error){
			        console.error("Error validating name:", error);
			    }
			});
		}
	}

	function submitItemForm(){
		var bizContent = $("#itemOfWorkForm").serializeArray();
		$("#cipherText").val(enc_password(JSON.stringify(convertFormToJSONArray(bizContent))));
		bootbox.confirm("Do You Want To Continue?", function (result) {
			if(result){
				showLoader();
				$("#itemOfWorkForm").submit();
			}
		});
	}

	function editItemForm(id) {
		$("#itemIdEdit").val(id);
		var bizContent = $("#editForm").serializeArray();
		$("#editCipherText").val(enc_password(JSON.stringify(convertFormToJSONArray(bizContent))));
		bootbox.confirm("Do You Want To Continue?", function (result) {
			if(result){
				showLoader();
				$("#editForm").submit();
			}
		});
	}
	
	function toggleStatus(id, status) {
	    let msg = status === true 
	        ? "Do you want to In-Activate this Work Item?" 
	        : "Do you want to Activate this Work Item?";

	    bootbox.confirm(msg, function (result) {
	        if (result) {
	            showLoader();
	            $.ajax({
	                type: "GET",
	                url: "${contextPath}/itemOfWork/toggle-status",
	                data: { id: id },
	                success: function (isActive) {
	                    hideLoader();
	                    var button = $("button[onclick*='toggleStatus(" + id + ",']");
	                    if (isActive === true) {
	                        button.removeClass("btn-danger").addClass("btn-success");
	                        button.html('<i class="fa fa-lock"></i>');
	                        button.attr("onclick", "toggleStatus(" + id + ", true)");
	                    }else if(isActive === false) {
	                        button.removeClass("btn-success").addClass("btn-danger");
	                        button.html('<i class="fa fa-unlock"></i>');
	                        button.attr("onclick", "toggleStatus(" + id + ", false)");
	                    }else {
	                        bootbox.alert("Something went wrong while updating status.");
	                    }
	                },
	                error: function (error) {
	                    hideLoader();
	                    console.error("Error toggling status:", error);
	                    bootbox.alert("Server error occurred. Please try again.");
	                }
	            });
	        }
	    });
	}

</script>

<form action="${contextPath}/itemOfWork/edit" method="post" id="editForm">
	<input type="hidden" id="editCipherText" name="cipherText">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="id" id="itemIdEdit" />
</form>
