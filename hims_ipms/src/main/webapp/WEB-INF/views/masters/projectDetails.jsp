<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- <c:set var="userDetails" value="${serviceOutcome.data}"/> 
 --%>
<%-- <script type="text/javascript"
	src="${contextPath}/assets/appJs/validation/common-utils.js"></script> --%>
<%-- <script src="${contextPath}/assets/appJs/validation/commonMaster.js"></script> --%>
<style>
.freeze {
	pointer-events: none;
}
</style>
<div class="breadcrumb_conatiner">
	<div class="col-md-6">
		<h4 class="change-color">Project Details</h4>
	</div>
	<div class="col-md-6">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${contextPath}/home"><i class="fa-solid fa-house"></i></a></li>
				<li class="breadcrumb-item active" aria-current="page">Project Details</li>
			</ol>
		</nav>
	</div>
</div>
<div class="row mt-2">
	<div class="col-md-12">
		<div class="card">
			<div class="card-header">
				<h4 class="card-title">Filter Project Data</h4>
			</div>
			<div class="card-body">
		      	<div class="row align-items-end">
             		<div class="col-md-3">
		                 <div class="form-group">
		                     <label class="smallInput required" for="">Financial Year:</label>
		                     <div class="">
		                         <select class="form-control form-control-sm form-select" id="financialYearId" name="financialYearId">
		                             <option value="0">- Select -</option>
		                             <c:forEach items="${financialYearList}" var="fy" varStatus="index">
		                                 <option value="${fy.financialYearId}">${fy.financialYear}</option>
		                             </c:forEach>
		                         </select>
		                     </div>
		                 </div>
	             	</div>
					<div class="col-md-3">
		                 <div class="form-group">
							<input type="button" name="add&ManageApplication" value="Filter" id="add&ManageApplications" class="btn btn-primary btn-sm"
								onclick="loadProjects();">	
							<a href="${contextPath}/home" type="button" class="btn btn-warning btn-sm">Back</a>
						</div>
					</div>
				</div>
			</div>
	    </div>
	  </div>
	</div>

	<div class="row mt-3">

		<div class="col-md-12">

			<div class="card">

				<div class="card-header">
					<h4 class="card-title">Project List</h4>
				</div>
				<div class="card-body">
					<c:if test="${not empty message}">
						<script>
							alert("<c:out value='${message}'/>");
						</script>
					</c:if>
					<nav>
						<div class="nav nav-tabs mb-2 tabnav" id="nav-tab" role="tablist">
							<button class="nav-link active" id="nav-draft-tab" data-bs-toggle="tab" data-bs-target="#nav-stock" type="button" role="tab" aria-controls="nav-profile" aria-selected="true"><spring:message code="site.common.draft" text="Draft" /></button>
							<button class="nav-link" id="nav-pending-tab" data-bs-toggle="tab" data-bs-target="#nav-attch" type="button" role="tab" aria-controls="nav-contact" aria-selected="false"><spring:message code="site.common.pending" text="Pending" /></button>
							<button class="nav-link" id="nav-approved-tab" data-bs-toggle="tab" data-bs-target="#nav-attch" type="button" role="tab" aria-controls="nav-contact" aria-selected="false"><spring:message code="site.common.approved" text="Approved" /></button>
							<button class="nav-link" id="nav-rejected-tab" data-bs-toggle="tab" data-bs-target="#nav-attch" type="button" role="tab" aria-controls="nav-contact" aria-selected="false"><spring:message code="site.common.rejected" text="Rejected" /></button>
							<button class="nav-link" id="nav-reverted-tab" data-bs-toggle="tab" data-bs-target="#nav-attch" type="button" role="tab" aria-controls="nav-contact" aria-selected="false"><spring:message code="site.common.reverted" text="Reverted" /></button>
						</div>
					</nav>
					<%-- <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
						<li class="nav-item" role="presentation"><a
							class="nav-link active" id="pills-draft-tab"
							data-bs-toggle="pill" href="#draft" role="tab"
							aria-controls="draft" aria-selected="true"> <spring:message code="site.common.draft" text="Draft" />
						</a></li>
						<li class="nav-item" role="presentation"><a class="nav-link"
							id="pills-pending-tab" data-bs-toggle="pill" href="#pending"
							role="tab" aria-controls="pending" aria-selected="false"> <spring:message code="site.common.pending" text="Pending" />
						</a></li>
						<li class="nav-item" role="presentation"><a class="nav-link"
							id="pills-approved-tab" data-bs-toggle="pill" href="#approved"
							role="tab" aria-controls="approved" aria-selected="false"> <spring:message code="site.common.approved" text="Approved" />
						</a></li>
						<li class="nav-item" role="presentation"><a class="nav-link"
							id="pills-rejected-tab" data-bs-toggle="pill" href="#rejected"
							role="tab" aria-controls="rejected" aria-selected="false"> <spring:message code="site.common.rejected" text="Rejected" />
						</a></li>
						<li class="nav-item" role="presentation"><a class="nav-link"
							id="pills-reverted-tab" data-bs-toggle="pill" href="#reverted"
							role="tab" aria-controls="reverted" aria-selected="false"> <spring:message code="site.common.reverted" text="Reverted" />
						</a></li>
					</ul> --%>
					<div class="table-responsive">
						<table class="table table-bordered table-hover" id="tableId">
							<thead>
								<tr>
									<th class="text-center" style="min-width:60px;">Sl.No</th>
									<th style="min-width:150px;">Project Name</th>
									<th style="min-width:100px;">Project Type</th>
									<th style="min-width:120px;">Project Status</th>
									<th style="min-width:100px;">Letter No.</th>
									<th style="min-width:100px;">Letter Date</th>
									<th style="min-width:150px;">Confirm Letter Date</th>
									<th style="min-width:100px;">Start Date</th>
									<th style="min-width:140px;">Completion date</th>
									<th style="min-width:150px;">Estimated Cost</th>
									<th style="min-width:150px;">Final Cost</th>
									<th class="text-center" style="min-width:100px;">Action</th>
								</tr>
							</thead>
							<tbody id="projectTableBody">
								<c:forEach items="${projectList}" var="project"
									varStatus="count">
									<tr>
										<td class="text-center">${count.count}</td>
										<td>${project.projectName}</td>
										<td>${project.projectType}</td>
										<td>${project.projectStatus}</td>
										<td>${project.letterNo}</td>
										<td><fmt:formatDate value="${project.letterDate}"
												pattern="dd/MM/yyyy" /></td>
										<td><fmt:formatDate value="${project.confirmLetterDate}"
												pattern="dd/MM/yyyy" /></td>
										<td><fmt:formatDate value="${project.startDate}"
												pattern="dd/MM/yyyy" /></td>
										<td><fmt:formatDate value="${project.completionDate}"
												pattern="dd/MM/yyyy" /></td>
										<td class="text-end">${project.estimatedCost}</td>
										<td class="text-end">${project.finalCost}</td>
										<td class="text-center"><a
											href="${pageContext.request.contextPath}/project/editProject/${project.projectId}"
											class="btn btn-sm btn-primary" title="Edit"> <i
												class="fa fa-edit"></i>
										</a> <a
											href="${pageContext.request.contextPath}/project/deleteProject/${project.projectId}"
											class="btn btn-sm btn-danger" title="Delete"> <i
												class="fa fa-trash"></i>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
<script>

function loadProjects() {
		debugger;
	    const financialYearId = $('#financialYearId').val(); 
		 $.ajax({
		        url: '${pageContext.request.contextPath}/project/fetch-project-details',
		        type: 'GET',
		        data: { financialYearId: financialYearId },
		        success: function(data) {
		        	debugger;
		            let tbody = $('#projectTableBody');
		            tbody.empty();
		            if (data && data.length > 0) {
		                $.each(data, function(index, project) {
		                    let row = '<tr>' +
		                        '<td class="text-center">' + (index + 1) + '</td>' +
		                        '<td>' + (project.projectName || '') + '</td>' +
		                        '<td>' + (project.projectType || '') + '</td>' +
		                        '<td>' + (project.projectStatus || '') + '</td>' +
		                        '<td>' + (project.letterNo || '') + '</td>' +
		                        '<td>' + formatDate(project.letterDate) + '</td>' +
		                        '<td>' + formatDate(project.confirmLetterDate) + '</td>' +
		                        '<td>' + formatDate(project.startDate) + '</td>' +
		                        '<td>' + formatDate(project.completionDate) + '</td>' +
		                        '<td class="text-end">' + (project.estimatedCost ?? 0) + '</td>' +
		                        '<td class="text-end">' + (project.finalCost ?? 0) + '</td>' +
		                        '<td class="text-center">' +
		                            '<a href="${pageContext.request.contextPath}/project/editProject/' + project.projectId + '" class="btn btn-sm btn-primary" title="Edit">' +
		                                '<i class="fa fa-edit"></i>' +
		                            '</a> ' +
		                            '<a href="${pageContext.request.contextPath}/project/deleteProject/' + project.projectId + '" class="btn btn-sm btn-danger" title="Delete">' +
		                                '<i class="fa fa-trash"></i>' +
		                            '</a>' +
		                        '</td>' +
		                    '</tr>';

		                    tbody.append(row);
		                });
		            } else {
		                tbody.append('<tr><td colspan="12" class="text-center">No data found</td></tr>');
		            }
		        },
		        error: function(xhr, status, error) {
		            console.error("Error fetching project details:", error);
		            alert("Something went wrong while fetching data.");
		        }
		    });
		 
		 function formatDate(dateString) {
			    if (!dateString) return '';
			    let date = new Date(dateString);
			    return date.toLocaleDateString('en-GB'); 
			}
	
}

</script>



