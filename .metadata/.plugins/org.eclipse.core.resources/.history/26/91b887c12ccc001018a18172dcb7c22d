<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="${contextPath}/assets/vendor/DataTablesNet/datatables.min.css" rel="stylesheet">
<script src="${contextPath}/assets/vendor/DataTablesNet/datatables.min.js"></script>


<style>
.modal-content {
	position: relative;
	display: flex;
	flex-direction: column;
	width: 100%;
	pointer-events: auto;
	background-color: #fff;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 0.3rem;
	outline: 0;
	margin-top: 80px;
	max-height: 450px !important;
}

.modal-backdrop {
	position: fixed;
	top: 0;
	left: 0;
	z-index: -1;
	width: 100vw;
	height: 100vh;
	background-color: #000;
}

.frezzFormBycanTakeAction {
	pointer-events: none;
}

.error-message {
	color: red;
	font-weight: 400;
	margin-top: 5px;
}

.is-invalid {
	border-color: #dc3545;
}

td a.visited-link {
	color: purple !important;
}
</style>


<%-- <section class="mainContainer">
		<div class="breadcrumb_conatiner">
			<div class="col-md-6">
				<h4 class="change-color">${title}</h4>
			</div>
			<div class="col-md-6">
				<div class="right-wrapper pull-right">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextPath}"> <i
									class="fa fa-home"></i></a></li>
							<li class="breadcrumb-item"><span>${title}</span></li>

						</ol>
					</nav>
				</div>
			</div>
		</div>
	</header> --%>
	<div class="row mt-3">
		<div class="col-md-12">
			<section class="panel">

				<div class="row mb-4">
					<div class="col-md-12">

						<div class="card">
							<div class="card-header">
								<h5 class="card-title">${title}</h5>
							</div>
							<div class="card-body">
								<form method="get" action="${actionUrl}" id="formId">
									<div class="row align-items-center">
										<div class="col-md-12">
											<ul class="nav nav-pills navtab-btn gap-2" role="tablist" style="border-bottom: none;">
												<c:forEach items="${genericDataViewDto.viewStatusList}" var="sts">
													<li role="presentation" class="nav-item ">
														<a class="nav-link ${sts.statusCode eq genericDataViewDto.status ? 'active' : ''}"
														href="#home" aria-controls="home" role="tab" data-toggle="tab" id="${sts.statusCode}TabId"
														onclick="getTableHeaderWithData('${sts.viewStatusId}')">${sts.displayName}</a></li>
												</c:forEach>
											</ul>
										</div>
									</div>

									<div class="mt-2">
										<input type="hidden" name="status" id="statusId" value="${genericDataViewDto.statusId}" /> 
										<input type="hidden" name="pageNo" id="pageNoId" value="${genericDataViewDto.pageNo}" /> 
										<input type="hidden" name="pageSize" id="pageSizeId" value="${genericDataViewDto.pageSize}" /> 
										<input type="hidden" name="entityClassName" id="entityClassNameId" value="${genericDataViewDto.entityClassName}" /> 
										<input type="hidden" name="conTextUrl" id="conTextUrlId" value="${genericDataViewDto.conTextUrl}" />

										<div class="row align-items-end">
											<div class="col-md-12">
												<!-- Tab panes -->
												<div class="tab-content">
													<div role="tabpanel" class="tab-pane active" id="home">
														<div class="row" id="dataSectionxx">
															<div class="col-md-12">
																<div class="table-responsive">
																	<table class=" table table-striped table-bordered mt-3" id="tableId">
																		<thead class="text-center bagGroundColorRvr" style="color: var(- -black);" id="tableHeaderId">
																			<c:forEach items="${genericDataViewDto.theadList}" var="hd">
																				<c:if test="${hd.isHidden eq false}">
																					<th style="">${hd.thName}</th>
																				</c:if>
																			</c:forEach>
																		</thead>
																		<tbody class="" id="tableBodyId">
																			<c:forEach items="${genericDataViewDto.dataValueList}" var="data">
																				<tr>
																					<c:forEach items="${data}" var="value">
																						<td>${value}</td>
																					</c:forEach>
																				</tr>
																			</c:forEach>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
													</div>
												</div>
												<!-- Tab panes -->
												<div class="tab-content">
													<div class="tab-pane fade active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
														<div class="row" id="dataSectionxx">
															<div class="col-md-12">
																<div class="table-responsive">
																	<table class=" table table-striped table-bordered mt-3" id="tableIdxx">
																		<thead class="text-center bagGroundColorRvr" style="color: var(- -black);" id="tableHeaderId">
																			<c:forEach items="${genericDataViewDto.theadList}" var="hd">
																				<c:if test="${hd.isHidden eq false}">
																					<th style="color: rgb(0, 191, 255);">${hd.thName}</th>
																				</c:if>
																			</c:forEach>
																		</thead>
																		<tbody class="text-center" id="tableBodyId">
																			<c:forEach items="${genericDataViewDto.dataValueList}" var="data">
																				<tr>
																					<c:forEach items="${data}" var="value">
																						<td>${value}</td>
																					</c:forEach>
																				</tr>
																			</c:forEach>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</section>

<form method="get" action="${actionUrl}" id="tabChangeFormId">
	<input type="hidden" name="status" value="" id="tabChangeStatusId" />
	<input type="hidden" name="pageSize" value="" id="tabChangePageSizeId" />
	<input type="hidden" id="status-${dynamicValue}" value="${status}" />
</form>

<script>
document.addEventListener("DOMContentLoaded", function () {
    const table = document.getElementById("tableId");
    const headerRow = table.querySelector("thead tr");
    const entityClassName = document.getElementById("entityClassNameId").value; // Get entity class name

    // Check if the last header is "History" before removing
    const lastHeader = headerRow.lastElementChild;
    if (lastHeader && lastHeader.textContent.trim() !== "History") {
        lastHeader.remove();
    }

    table.querySelectorAll("tbody tr").forEach((row, index) => {
        const lastCell = row.lastElementChild;
        const anchors = lastCell.querySelectorAll("a");
        lastCell.remove();

        if (index === 0) {
            // Get existing headers to avoid duplication
            const existingHeaders = Array.from(headerRow.querySelectorAll("th")).map(th => th.textContent.trim());
            
            // Define headers to add
            const headers = ["Action"];
            if (!existingHeaders.includes("History")) {
                headers.push("History");
            }
            if (entityClassName.includes("InfraWorkBill")) {
                headers.push("Initiate Expenditure");
            } else if (entityClassName.includes("BAMSRequisition")) {
                headers.push("Initiate Project");
            }else if (entityClassName.includes("Project")) {
                headers.push("Document1");
                headers.push("Document2");
                headers.push("Document3");
            } else {
                headers.push("View Image");
            }

            // Append missing headers
            headers.forEach(name => {
                if (!existingHeaders.includes(name)) {
                    const th = document.createElement("th");
                    th.textContent = name;
                    th.style.textAlign = "center";
                    headerRow.appendChild(th);
                }
            });
        }

        // Add anchor tags to the row
        anchors.forEach(a => {
            const td = document.createElement("td");
            td.style.textAlign = "center";
            td.appendChild(a);
            row.appendChild(td);
        });

        // Ensure the row has enough cells to match the headers
        const headerCount = headerRow.querySelectorAll("th").length;
        while (row.querySelectorAll("td").length < headerCount) {
            const td = document.createElement("td");
            td.style.textAlign = "center";
            td.textContent = "NA";
            row.appendChild(td);
        }
    });
});
</script>

<script>
	function getTableHeaderWithData(status) {
		$("#tabChangeStatusId").val(status);
		// page size
		let pageSize = $("#tableId_length select").val();
		$("#tabChangePageSizeId").val(pageSize);
		// submit form
		$("#tabChangeFormId").submit();
	}

	// Document ready
	$(document).ready(function(){
	    // Assuming you have the total records available in a variable
	    let totalRecords = '${genericDataViewDto.totalRecords}';
	    let pageNo = '${genericDataViewDto.pageNo}';
	    let pageSize = '${genericDataViewDto.pageSize}';
	    // DataTable initialization with custom options
	    let dataTable = new DataTable("#tableId", {
	        "paging": true,
	        "lengthChange": true,
	        "searching": true,
	        "ordering": true,
	        "info": true,
	        "autoWidth": false,
	        "pageLength": parseInt(pageSize),
	        "language": {
	            // Customization of DataTables language options can go here
	        },
	        // You can add more options as needed
	        // Callback function to customize the DataTables paging information
	        "drawCallback": function(settings) {
	            let api = this.api();
	            let pageInfo = api.page.info();
	            // Update the total records in the table footer
	            $('.dataTables_info').html('Showing ' + (pageInfo.start + 1) + ' to ' + (pageInfo.end) + ' of ' + totalRecords + ' entries');
	            // check if pagination buttons are not null
	            let pageSizes = $("#tableId_length select").val();
	            let totalPages = Math.ceil(totalRecords / pageSizes);
	            

	            // currlty pegination showing like Previous 1 Next but we need to show like Previous 1 2 3 Next 1 2 3 will totalRecords / pageSizes
	            // so we need to update the pagination buttons through DataTables API
	            let paginationContainer = $('.dataTables_paginate');
	            paginationContainer.empty(); 
	            let currentPage = parseInt(pageNo);
	            
	            let paginationHtml = '<ul class="pagination">';
	            paginationHtml += '<li class="paginate_button page-item previous ' + (currentPage === 1 ? 'disabled' : '') + '" id="tableId_previous"><a href="#" class="page-link">Previous</a></li>';
	            
	            const maxVisiblePages = 5; // Adjust to show more/fewer buttons
	            const halfVisible = Math.floor(maxVisiblePages / 2);
	            let startPage = Math.max(1, currentPage - halfVisible);
	            let endPage = Math.min(totalPages, startPage + maxVisiblePages - 1);
	            
	            // Adjust if at start/end to fill visible slots
	            if (endPage - startPage + 1 < maxVisiblePages) {
	                startPage = Math.max(1, endPage - maxVisiblePages + 1);
	            }
	            
	            // Add first page and ellipsis if needed
	            if (startPage > 1) {
	                paginationHtml += '<li class="paginate_button page-item"><a href="#" class="page-link">1</a></li>';
	                if (startPage > 2) {
	                    paginationHtml += '<li class="paginate_button page-item disabled"><a href="#" class="page-link">...</a></li>';
	                }
	            }
	            
	            // Add visible page range
	            for (let i = startPage; i <= endPage; i++) {
	                paginationHtml += '<li class="paginate_button page-item ' + (i === currentPage ? 'active' : '') + '"><a href="#" class="page-link">' + i + '</a></li>';
	            }
	            
	            // Add last page and ellipsis if needed
	            if (endPage < totalPages) {
	                if (endPage < totalPages - 1) {
	                    paginationHtml += '<li class="paginate_button page-item disabled"><a href="#" class="page-link">...</a></li>';
	                }
	                paginationHtml += '<li class="paginate_button page-item"><a href="#" class="page-link">' + totalPages + '</a></li>';
	            }
	            
	            paginationHtml += '<li class="paginate_button page-item next ' + (currentPage === totalPages ? 'disabled' : '') + '" id="tableId_next"><a href="#" class="page-link">Next</a></li>';
	            paginationHtml += '</ul>';
	            
	            paginationContainer.html(paginationHtml); // Use .html() to set the content
	        }
	    });
	});

	// pagination click event
	$(document).on("click", ".pagination .paginate_button", function(e){
	    e.preventDefault();
	    // if disabled then return
	    if($(this).hasClass("disabled")){
	        return;
	    }

	    let page = 0;
	    // if previous button is clicked
	    if($(this).hasClass("previous")){
	        let currentPage = parseInt($("#pageNoId").val());
	        if(currentPage > 1){
	            page = currentPage - 1;
	        }
	    }else if($(this).hasClass("next")){
	        let currentPage = parseInt($("#pageNoId").val());
	        let totalPages = Math.ceil(parseInt('${genericDataViewDto.totalRecords}') / parseInt('${genericDataViewDto.pageSize}'));
	        if(currentPage < totalPages){
	            page = currentPage + 1;
	        }
	    }else{
	        page = parseInt($(this).find("a").text());
	    }
	    $("#pageNoId").val(page);
	    // page size
	    let pageSize = $("#tableId_length select").val();
	    $("#pageSizeId").val(pageSize);
	    $("#formId").submit();
	});

// page size change event
$(document).on("change", "#tableId_length select", function(e){
	// get status
	let status = $("#statusId").val();
	// get page size
	let pageSize = $(this).val();
	// tab change form
	$("#tabChangeStatusId").val(status);
	$("#tabChangePageSizeId").val(pageSize);
	$("#tabChangeFormId").submit();
});

function callJavaScriptFunction(modelClassWithId){
		// split the string by ## to get model class and id
		var modelClassWithIdArray = modelClassWithId.split("##");
		var modelClass = modelClassWithIdArray[0];
		var id = modelClassWithIdArray[1];

		// ajax call to get the history table data
		$.ajax({
			url: "${pageContext.request.contextPath}/stageConfig/get-workFlow-history",
			type: "GET",
			data: {
				"entityClassName": modelClass,
				"entityId": parseInt(id)
			},
			success: function(response){
				console.log(response);
				var historyTableBody = $("#historyTableBodyId");
				historyTableBody.empty();
				if(response != null && response != undefined && response != "" && response.length > 0){
					let html = "";
					$.each(response, function(index, value){
						html += "<tr>";
						html += "<td>"+(index+1)+"</td>";
						html += "<td>"+value.stageName+"</td>";
						html += "<td>"+value.actionTakenByName+"</td>";
						html += "<td>"+value.actionTakenOnRoleName+"</td>";
						html += "<td>"+value.remark+"</td>";
						html += "<td>"+value.actionTakenDate+"</td>";
						html += "</tr>";
					});
					historyTableBody.append(html);
				}
				// show the modal
				$("#historyTableRemarksModal").modal("show");
			},
			error: function(response){
				console.log(response);
			}
		});
	}

	document.addEventListener("click", function (event) {
        if (event.target.closest("td a")) {
            event.target.classList.add("visited-link");
        }
    });
	
	$(document).ready(function () {
	    $("a[id^='btn-']").each(function () {
	        const btn = $(this);
	        const workbillId = btn.attr("id").replace("btn-", "");
	        const status = $(`#status-${workbillId}`).val();

	        // Keep the existing check
	        if (status !== '4') {
	            btn.addClass('disabled');
	            btn.css({
	                'pointer-events': 'none',
	                'opacity': '0.6'
	            });
	        } else {
	            // NEW: Check if expenditureId exists
	            $.ajax({
	                url: "${contextPath}/work-bill/isExpenditureIdExists",
	                type: "GET",
	                data: { workBillId: workbillId },
	                success: function (exists) {
	                    if (exists === true) { // expenditureId exists → disable
	                        btn.addClass('disabled');
	                        btn.css({
	                            'pointer-events': 'none',
	                            'opacity': '0.6'
	                        });
	                    }
	                },
	                error: function () {
	                    console.error("Failed to check expenditureId for workBillId " + workbillId);
	                }
	            });
	        }
	    });
	});

	function checkstatus(workbillId) {
	    const status = document.getElementById(`status-${workbillId}`)?.value;

	    if (status === '4') {
	        $.ajax({
	            url: "${contextPath}/work-bill/isExpenditureIdExists",
	            type: "GET",
	            data: { workBillId: workbillId },
	            success: function (exists) {
	                if (exists === false) { // Only allow if no expenditureId
	                    callFunction(workbillId);
	                }
	            },
	            error: function () {
	                console.error("Failed to check expenditureId before redirect for workBillId " + workbillId);
	            }
	        });
	    }
	}
	
	
	/**
	 * Fetches work bill total amount and securely encrypts the data
	 * before redirecting to the expenditure add form.
	 */
	function callFunction(workbillId) {
	    $.ajax({
	        url: contextPath + "/work-bill/workBillTotalAmount",
	        type: 'GET',
	        data: { workbillId: workbillId }, 
	        success: function (response) {
	            console.log('Success:', response);
	            encryptExpenditureAmountData(response.totalAmount,response.workBillId,response.workBillCode);
	        },
	        error: function () {
	            alert("Failed to fetch work bill total amount.");
	        }
	    });
	}
	
	function encryptExpenditureAmountData(totalAmount,workBillId,workBillCode) {
		  $("#totalAmount").val(totalAmount);
		  $("#workBillId").val(workBillId);
		  $("#workBillCode").val(workBillCode);
		  showLoader();
		  var bizContent = $("#encryptAmountData").serializeArray();
		  $("#editCipherText").val(enc_password(JSON.stringify(convertFormToJSONArray(bizContent))));
		  $("#encryptAmountData").submit();
	}

	function fetchAuditImage(auditId) {
	    debugger;
	    if (!auditId) {
	        bootbox.alert("Invalid Audit ID.");
	        return;
	    }$.ajax({
	        type: "GET",
	        url: "${contextPath}/audit-management/fetchAuditImageByAuditManagementId",
	        data: { auditManagementId: auditId },
	        xhrFields: {
	            responseType: 'blob'
	        },
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
	        },
	        success: function (blob) {
	            if (blob && blob.size > 0) {
	                const mimeType = blob.type || 'image/jpeg'; // use 'image/png' if needed
	                const imageBlob = new Blob([blob], { type: mimeType });
	                const url = URL.createObjectURL(imageBlob);

	                const link = document.createElement('a');
	                link.href = url;
	                link.target = '_blank';
	                link.rel = 'noopener noreferrer';
	                document.body.appendChild(link);
	                link.click();
	                document.body.removeChild(link);

	                setTimeout(() => {
	                    URL.revokeObjectURL(url);
	                }, 2000);
	            } else {
	                bootbox.alert("Image is empty or not available.");
	            }
	        },
	        error: function (xhr, status, error) {
	            console.error("Error fetching audit image:", error);
	            const errorMessage = xhr.responseText || "Image not found or could not be loaded.";
	            bootbox.alert(errorMessage);
	        }
	    });
	}
	
	function fetchLegacyImage(legacyProjectId) {
	    debugger;
	    if (!legacyProjectId) {
	        bootbox.alert("Invalid Audit ID.");
	        return;
	    }$.ajax({
	        type: "GET",
	        url: "${contextPath}/legacy/fetchLegacyImageByLegacyId",
	        data: { legacyProjectId: legacyProjectId },
	        xhrFields: {
	            responseType: 'blob'
	        },
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
	        },
	        success: function (blob) {
	            if (blob && blob.size > 0) {
	                const mimeType = blob.type || 'image/png'; // use 'image/png' if needed
	                const imageBlob = new Blob([blob], { type: mimeType });
	                const url = URL.createObjectURL(imageBlob);

	                const link = document.createElement('a');
	                link.href = url;
	                link.target = '_blank';
	                link.rel = 'noopener noreferrer';
	                document.body.appendChild(link);
	                link.click();
	                document.body.removeChild(link);

	                setTimeout(() => {
	                    URL.revokeObjectURL(url);
	                }, 2000);
	            } else {
	                bootbox.alert("Image is empty or not available.");
	            }
	        },
	        error: function (xhr, status, error) {
	            console.error("Error fetching audit image:", error);
	            const errorMessage = xhr.responseText || "Image not found or could not be loaded.";
	            bootbox.alert(errorMessage);
	        }
	    });
	}
	//BAMS Maintance uc document and pre-work image
	function fetchUcImage(maintenanceId) {
    debugger;

    if (!maintenanceId) {
        bootbox.alert("Invalid Maintenance ID.");
        return;
    }

    $.ajax({
        type: "GET",
        url: "${contextPath}/bams/fetchUcImageByMaintenanceId",
        data: { maintenanceId: maintenanceId },
        xhrFields: {
            responseType: 'blob'
        },
        beforeSend: function(xhr) {
            xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
        },
        success: function (blob) {
            if (blob && blob.size > 0) {
                const mimeType = blob.type || 'image/png';
                const imageBlob = new Blob([blob], { type: mimeType });
                const url = URL.createObjectURL(imageBlob);

                const link = document.createElement('a');
                link.href = url;
                link.target = '_blank';
                link.rel = 'noopener noreferrer';
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);

                setTimeout(() => {
                    URL.revokeObjectURL(url);
                }, 2000);

            } else {
                bootbox.alert("UC Image is empty or not available.");
            }
        },
        error: function(xhr, status, error) {
            console.error("Error fetching UC image:", error);
            const errorMessage = xhr.responseText || "Image not found or could not be loaded.";
            bootbox.alert(errorMessage);
        }
    });
}


	function fetchPreWorkImage(maintenanceId) {
	    debugger;

	    if (!maintenanceId) {
	        bootbox.alert("Invalid Maintenance ID.");
	        return;
	    }

	    $.ajax({
	        type: "GET",
	        url: "${contextPath}/bams/fetchPreWorkImageByMaintenanceId",
	        data: { maintenanceId: maintenanceId },
	        xhrFields: {
	            responseType: 'blob'
	        },
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
	        },
	        success: function (blob) {
	            if (blob && blob.size > 0) {
	                const mimeType = blob.type || 'image/png';
	                const imageBlob = new Blob([blob], { type: mimeType });
	                const url = URL.createObjectURL(imageBlob);

	                const link = document.createElement('a');
	                link.href = url;
	                link.target = '_blank';
	                link.rel = 'noopener noreferrer';
	                document.body.appendChild(link);
	                link.click();
	                document.body.removeChild(link);

	                setTimeout(() => {
	                    URL.revokeObjectURL(url);
	                }, 2000);

	            } else {
	                bootbox.alert("Pre-Work Image is empty or not available.");
	            }
	        },
	        error: function(xhr, status, error) {
	            console.error("Error fetching Pre-Work image:", error);
	            const errorMessage = xhr.responseText || "Image not found or could not be loaded.";
	            bootbox.alert(errorMessage);
	        }
	    });
	}



	function fetchOnGoingProjectImage(onGoingProjectId) {
	    debugger;
	    if (!onGoingProjectId) {
	        bootbox.alert("Invalid Ongoing Project ID.");
	        return;
	    }

	    $.ajax({
	        type: "GET",
	        url: "${contextPath}/ongoing-project/fetchOngoingProjectImageById",
	        data: { onGoingProjectId: onGoingProjectId },
	        xhrFields: {
	            responseType: 'blob'
	        },
	        beforeSend: function (xhr) {
	            xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
	        },
	        success: function (blob) {
	            if (blob && blob.size > 0) {
	                const mimeType = blob.type || 'image/png'; // fallback to png
	                const imageBlob = new Blob([blob], { type: mimeType });
	                const url = URL.createObjectURL(imageBlob);

	                const link = document.createElement('a');
	                link.href = url;
	                link.target = '_blank';
	                link.rel = 'noopener noreferrer';
	                document.body.appendChild(link);
	                link.click();
	                document.body.removeChild(link);

	                setTimeout(() => {
	                    URL.revokeObjectURL(url);
	                }, 2000);
	            } else {
	                bootbox.alert("Ongoing Project image is empty or not available.");
	            }
	        },
	        error: function (xhr, status, error) {
	            console.error("Error fetching Ongoing Project image:", error);
	            const errorMessage = xhr.responseText || "Image not found or could not be loaded.";
	            bootbox.alert(errorMessage);
	        }
	    });
	}

	// Function to handle "Initiate Project" button click
	function initiateProject(encryptedBamsRequisitionId) {
		debugger;				
	    // Direct redirect to project list screen
	 $("#bamsRequisitionIdHdn").val(encryptedBamsRequisitionId);
	 var bizContent = $("#encryptBamsEditForm").serializeArray();		
	$("#editCipherTextHdn").val(enc_password(JSON.stringify(convertFormToJSONArray(bizContent)))); 
	console.log(enc_password(JSON.stringify(convertFormToJSONArray(bizContent))));
	$("#encryptBamsEditForm").submit(); 
	}

</script>

<form action="${contextPath}/project/editProject" method="GET" id="encryptBamsEditForm">
 <input type="hidden" id="editCipherTextHdn" name="cipherText">
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
 <input type="hidden" name="bamsRequisitionId" id="bamsRequisitionIdHdn" />
</form>

<form action="${contextPath}/work-bill/fnd/expenditure/add" method="GET" id="encryptAmountData">
 <input type="hidden" id="editCipherText" name="cipherText">
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
 <input type="hidden" name="totalAmount" id="totalAmount" />
 <input type="hidden" name="workBillId" id=workBillId />
 <input type="hidden" name="workBillCode" id="workBillCode" />
</form>
