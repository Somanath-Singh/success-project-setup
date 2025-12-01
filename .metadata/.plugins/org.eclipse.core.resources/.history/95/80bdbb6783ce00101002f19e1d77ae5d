<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
:root {
    --primary-color: #4361ee;
    --secondary-color: #3f37c9;
    --success-color: #4cc9f0;
    --warning-color: #f8961e;
    --danger-color: #f94144;
    --light-color: #f8f9fa;
    --dark-color: #212529;
    --text-muted: #6c757d;
    --card-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
    --transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
}
.dashboard-container {
    font-family: 'Inter', -apple-system, BlinkMacSystemFont, sans-serif;
   /*  padding: 2rem; */
     background-color: #F5F5F5;
    min-height: 100vh;
    border-radius: 8px;
}
.dashboard-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom:25px;
}
.dashboard-title {
    color: var(--dark-color);
    font-weight: 600;
    font-size: 1.5rem;
    margin: 0;
    position: relative;
}
.year-selector {
    position: relative;
    width: 280px;
}
 .year-selector::after {
    content: '↓';
    position: absolute;
    right: 15px;
    top: 50%;
    transform: translateY(-50%);
    color: var(--text-muted);
    pointer-events: none;
} 
.select-financial-year {
    width: 100%;
    border-radius: 8px;
    border: 1px solid #e0e0e0;
    padding: 0.75rem 1rem;
    font-size: 0.95rem;
    appearance: none;
    background-color: white;
    transition: var(--transition);
    cursor: pointer;
}
.select-financial-year:focus {
    outline: none;
    border-color: var(--primary-color);
    box-shadow: 0 0 0 3px rgba(67, 97, 238, 0.15);
}
.card {
    margin-bottom: 25px;
    border: 1px solid #e6e9eb !important;
    transition: all 0.3s ease;
    letter-spacing: 0.5px;
    position: relative;
    display: flex;
    flex-direction: column;
    background-color: #ffffff;
    border: 1px solid #e0e0e0;
    border-radius: 0.5rem;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
    overflow: hidden;
    padding: 0;
}
.card.loading {
    opacity: 0.5;
    pointer-events: none;
}
.card.loading::after {
    content: "";
    position: absolute;
    top: 50%;
    left: 50%;
    width: 20px;
    height: 20px;
    border: 3px solid #4361ee;
    border-top: 3px solid transparent;
    border-radius: 50%;
    animation: spin 1s linear infinite;
}
@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}
.total_project {
    border-bottom: 2px solid #c28566 !important;
    border-width: 2px !important;
}
.total_project2 {
    border-bottom: 2px solid rgba(255, 193, 7, 0.8) !important;
    border-width: 2px !important;
}
.total_project3 {
    border-bottom: 2px solid rgba(76, 175, 80, 0.8) !important;
    border-width: 2px !important;
}
.card .card-body span {
    /* color: #86909c; */
    font-size: 11px;
    font-weight: 600;
}
.card .card-body .countingcard {
    color: #000;
    font-size: 15px;
    font-weight: 400;
}
.project-details {
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.project-counter h5 {
    font-weight: 600;
    font-size: 26px;
}
.card-body .product-sub {
    height: 45px;
    width: 45px;
    border-radius: 6px;
    display: flex;
    justify-content: center;
    align-items: center;
}
.product-sub i {
    color: #c28566;
    font-family: 20px;
    font-weight: bold;
}
.bg-primary-light {
    background-color: rgba(194, 133, 102, 0.3);
}
.bg-estimation-light {
    background-color: rgba(255, 193, 7, 0.3);
}
.bg-workorder-light {
    background-color: rgba(76, 175, 80, 0.3);
}
.section-title {
    color: #2c3e50;   
    font-weight: 500;
    font-size: 1rem;
    margin-bottom: 1.5rem;
    display: flex;
    align-items: center;
}
.status-badge {
    padding: 0.5rem 0.70rem; 
    border-radius: 6px;
    font-size: 0.70rem;
    font-weight: 600;
    text-transform: uppercase;
    letter-spacing: 0.5px;
    border: none;
    cursor: default;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    min-width: 80px;
}
.status-badge i {
    margin-right: 0.5rem; 
    font-size: 0.65rem;
}
.status-draft {background-color: rgba(135, 206, 250, 0.2);color: #1E90FF;}
.status-revert {background-color: rgba(255, 182, 193, 0.2);color: #FF69B4;}
.status-closed {background-color: rgba(169, 169, 169, 0.2);color: #696969;}
.status-approved { background-color: rgba(40, 167, 69, 0.1); color: #28a745; }
.status-pending { background-color: rgba(255, 133, 27, 0.1); color: #ff851b; }
.status-verify { background-color: rgba(255, 193, 7, 0.1); color: #ffc107; }
.status-rejected { background-color: rgba(220, 53, 69, 0.1); color: #dc3545; }
.status-submit { background-color: rgba(0, 123, 255, 0.1); color: #007bff; }
.status-partial { background-color: rgba(23, 162, 184, 0.1); color: #17a2b8; }
.status-publish { background-color: rgba(255, 193, 7, 0.1); color: #ffc107; }
.status-approve-pub { background-color: rgba(40, 167, 69, 0.1); color: #28a745; }
.status-unknown { background-color: rgba(108, 117, 125, 0.1); color: #6c757d; }
table td {
    font-size: 11px;
    font-weight: 600;
    color: #4b3d3deb;
    box-shadow: unset !important;
}
table thead th, table tfoot th {
    font-size: 13px;
}
.card_header_proj {
    font-size: 20px;
    font-weight: 600;
    color: #000;
}
.chart-container {
    position: relative;
    margin: 0 auto;
    width: 100%;
}
.chart-tooltip {
    position: absolute;
    background: rgba(0, 0, 0, 0.8);
    color: #000;
    padding: 6px 10px;
    border-radius: 4px;
    font-size: 12px;
    pointer-events: none;
    display: none;
    z-index: 100;
    box-shadow: 0 2px 8px rgba(0,0,0,0.15);
}
.chart-tooltip .tooltip-title {
    font-weight: bold;
    margin-bottom: 2px;
    color: #000;
}
.chart-tooltip .tooltip-value {
    color: #000;
}
.legend-container {
    width: 100%;
    padding: 8px 0;
    margin-top: 10px;
    text-align: center;
}
.legend-item {
    display: inline-flex;
    align-items: center;
    padding: 3px 8px;
    margin: 2px;
    border-radius: 4px;
    cursor: pointer;
    transition: all 0.2s;
}
.legend-item:hover {
    transform: translateY(-2px);
}
.legend-color {
    width: 10px;
    height: 10px;
    border-radius: 2px;
    margin-right: 6px;
    display: inline-block;
}
.district-name {
    font-size: 10px;
    margin-right: 4px;
    color: #495057;
}
.count-label {
    font-size: 10px;
    font-weight: bold;
    color: #212529;
}

</style>

<style>

.svgMap {
  width: 100%;
  height: auto;
  float: left;
  margin: 0px 0 0 0;
  overflow: hidden;
}
/* .svgMap #odishaSVGmap {

} */
.svgMap_con {
  position: relative;
  width: 100%;
  float: left;
}
.svgMap_con #odishaSVGmap .singleDistrict {
  stroke: #0f1123;
  stroke-miterlimit: 17;
  fill: #ff9800;
  transition: 0.3s all;
}
.svgMap_con p {
  text-align: center;
  font-weight: 600;
  color: #1db540;
  font-size: 15px;
  margin: 0px 0 8px;
}
.svgMap_con #odishaSVGmap a.shg_high .singleDistrict {
  fill: #dc6e37 !important;
}
.svgMap_con #odishaSVGmap a.shg_medium .singleDistrict {
  fill: #ff8a4f !important;
}
.svgMap_con #odishaSVGmap a.shg_low .singleDistrict {
  fill: #bf511a !important;
}
.map_filter {
  position: absolute;
  right: 0;
  bottom: 0;
  width: 130px;
}
.map_filter .single_filter {
  width: 100%;
  float: left;
  margin: 0 0 12px 0;
}
.map_filter span {
  display: block;
  width: 30px;
  height: 16px;
  float: left;
  background: #07f;
  border-radius: 2px;
}
.map_filter label {
  display: block;
  color: #000 !important;
  font-size: 13px !important;
  float: left;
  margin-left: 3px;
  font-weight: 600;
}
</style>

<div class="dashboard-container mt-3">
    <div class="dashboard-header">
        <h5 class="dashboard-title">Dash-board</h5>
        <div class="year-selector">
            <select class=" select-financial-year" id="financialYear" name="financialYearId"
                onchange="LoadDashBoardDataByFinancialYear(this.value);loadDepartmentWiseProjectCount(this.value);">
                <option value="0">All</option>
                <c:forEach items="${financialYearList}" var="fy" varStatus="index">
                    <option value="${fy.financialYearId}" ${fy.financialYearId eq activeFinYearId ? 'selected' : ''}>${fy.financialYear}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <div class="card-body total_project position-relative">
                    <span class="d-block " style="font-size: 16px; font-weight: 500;">Total Buildings</span>
                    <div class="project-details">
                        <div class="project-counter">
                            <h4 class="m-0 fw-semibold" style="font-size: 28px;" id="projectCount">${dashBoardData.projectCount}</h4>
                        </div>
                        <div class="product-sub">
                            <img src="${contextPath}/assets/images/totalprojects.png" style="width:100%;">
                        </div>
                    </div>
                    <hr class="my-3" style="border-top: 1px solid #dee2e6;" />
                    <div class="d-flex justify-content-between">
                        <small class="fw-medium" style="font-size: 13px;color: #28a745;">Approved: <span class="fw-semibold countingcard" id="approvedProjectCount">${dashBoardData.approvedProjectCount}</span></small>
                        <small class="fw-medium" style="font-size: 13px;color: #ff0000;">Pending: <span class="fw-semibold countingcard" id="pendingProjectCount">${dashBoardData.pendingProjectCount}</span></small>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card">
                <div class="card-body total_project2 position-relative">
                    <span class="d-block " style="font-size: 16px; font-weight: 500;">Total Estimation</span>
                    <div class="project-details">
                        <div class="project-counter">
                            <h4 class="m-0 fw-semibold" style="font-size: 28px;" id="estimationCount">${dashBoardData.estimationCount}</h4>
                        </div>
                        <div class="product-sub">
                            <img src="${contextPath}/assets/images/investment.png" style="width:100%;">
                        </div>
                    </div>
                    <hr class="my-3" style="border-top: 1px solid #dee2e6;" />
                    <div class="d-flex justify-content-between">
                        <small class="fw-medium" style="font-size: 13px;color: #28a745;">Approved: <span class="fw-semibold countingcard" id="approvedEstimationCount">${dashBoardData.approvedestimationCount}</span></small>
                        <small class="fw-medium" style="font-size: 13px;color: #ff0000;">Pending: <span class="fw-semibold countingcard" id="pendingEstimationCount">${dashBoardData.pendingestimationCount}</span></small>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card">
                <div class="card-body total_project3 position-relative">
                    <span class="d-block " style="font-size: 16px; font-weight: 500;">Total Work Orders</span>
                    <div class="project-details">
                        <div class="project-counter">
                            <h4 class="m-0 fw-semibold" style="font-size: 28px;" id="workorderCount">${dashBoardData.workorderCount}</h4>
                        </div>
                        <div class="product-sub">
                            <img src="${contextPath}/assets/images/workorder.png" style="width:100%;">
                        </div>
                    </div>
                    <hr class="my-3" style="border-top: 1px solid #dee2e6;" />
                    <div class="d-flex justify-content-between">
                        <small class="fw-medium" style="font-size: 13px;color: #28a745;">Approved: <span class="fw-semibold countingcard" id="approvedWorkorderCount">${dashBoardData.approvedworkorderCount}</span></small>
                        <small class="fw-medium" style="font-size: 13px;color: #ff0000;">Pending: <span class="fw-semibold countingcard" id="pendingWorkorderCount">${dashBoardData.pendingworkorderCount}</span></small>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row ">

	<!-- Odisha Map -->
		<div class="col-md-6">
		    <div class="card">
		        <div class="card-body">
		            <div class="innerpage-content" style="height: 455px;">
		                <div class="svgMap_con" style="width: 100%; margin-top: -24px; margin-left: 40px;">
		                    <div class="svgMap" id="svgMap">
		                       
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		
		<div class="col-md-6 ">
			<div class="card">
				<div class="card-body">
					<div class="innerpage-content">
						<div class="row">
					        <div class="col-md-6">
					          <h6 class="section-title">Building Type</h6>
					        </div>
					        <div class="col-md-6">
					          <select class="form-control form-control-sm form-select select2"  id="districtId" name="districtId">
					          	<option value="0">- select -</option>
					            <c:forEach items="${districtList}" var="district">
					              <option value="${district.districtId}">${district.districtName}</option>
					            </c:forEach>
					          </select>
					        </div>
				      	</div>
						<div style="width: 100%; height: 400px;" class="justify-content-center align-items-center">
							<!-- Message container (empty initially) -->
    						<div id="buildingType-msg" style="text-align:center; padding: 10px; font-size: 15px; color:red;"></div>
							<div id="buildingType"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="col-md-6">
		    <div class="card">
		        <div class="card-body">
		            <h6 class="section-title">Recent Buildings</h6>
		            <div class="table-responsive" style="max-height: 400px; overflow-y: auto; position: relative;">
		                <table class="table table-bordered table-hover no-footer" style="table-layout: fixed; width: 100%;">
		                    <thead style="position: sticky; top: -1px; z-index: 10; background-color: #ffffff;">
		                        <tr>
		                            <th style="width: 10%;">Sl.No</th>
		                            <th>Project Name</th>
		                            <th style="width: 15%;">Start Date</th>
		                            <th style="width: 20%;">Completion Date</th>
		                            <th>Status</th>
		                        </tr>
		                    </thead>
		                    <tbody id="projectListBody">
		                        <c:forEach items="${dashBoardData.projectList}" var="project" varStatus="index">
		                            <tr>
		                                <td>${index.index + 1}</td>
		                                <td>${project.projectName}</td>
		                                <td>${project.startDate}</td>
		                                <td>${project.completionDate}</td>
		                                <td>
											<button class="status-badge js-status-btn" data-status="${project.status}"></button>
										</td>
		                            </tr>
		                        </c:forEach>
		                    </tbody>
		                </table>
		            </div>
		        </div>
		    </div>
		</div>

		<div class="col-md-6">
            <div class="card">
                <div class="card-body">
                 	<h6 class="section-title mb-4">Total Buildings Status</h6>
                    <div class="d-flex justify-content-center">
                    	<div id="department-pie-chart" style="width: 100%; height: 400px;"></div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h6 class="section-title">Recent Inspections</h6>
                    <div class="table-responsive" style="max-height: 400px; overflow-y: auto;">
                        <table class="table table-bordered table-hover" style="table-layout: fixed; width: 100%;">
                            <thead style="position: sticky; top: -1px; z-index: 10; background: #fff;">
                                <tr>
                                    <th style="width: 10%;">Sl.No</th>
                                    <th>Project Name</th>
                                    <th>Estimation No</th>
                                    <th>Work Order</th>
                                    <th>Inspection Phase</th>
                                </tr>
                            </thead>
                            <tbody id="inspectionListBody">
                                <c:forEach items="${inspectionList}" var="inspection" varStatus="index">
                                    <tr>
                                        <td>${index.index + 1}</td>
                                        <td>${inspection.projectName}</td>
                                        <td>${inspection.planEstimationNo}</td>
                                        <td>${inspection.workOrderName}</td>
                                        <td>${inspection.phaseName}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="col-md-6">
            <div class="card">
                <div class="card-body">
                    <h6 class="section-title">Agency List</h6>
                    <div class="table-responsive" style="max-height: 400px; overflow-y: auto;">
                        <table class="table table-bordered table-hover" style="table-layout: fixed; width: 100%;">
                            <thead style="position: sticky; top:-1px; z-index: 10; background: #fff;">
                                <tr>
                                    <th style="width: 10%;">Sl.No.</th>
                                    <th style="width: 25%;">Agency Name</th>
                                    <th style="width: 15%;">Type</th>
                                    <th style="width: 25%;">Contact</th>
                                    <th style="width: 25%;">Bank</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${dashBoardData.agencyList}" var="agency" varStatus="index">
                                    <tr>
                                        <td>${index.index + 1}</td>
                                        <td>${agency.agencyName}</td>
                                        <td>${agency.agencyType}</td>
                                        <td>${agency.authPerName}</td>
                                        <td>${agency.primaryBank}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="col-md-6">
		  <div class="card">
		    <div class="card-body">
		      <div class="row">
		        <div class="col-md-6">
		          <h6 class="section-title">Buildings Expenditure Status</h6>
		        </div>
		        <div class="col-md-6">
		          <select class="form-control form-control-sm form-select select2"  id="projectId" name="projectId">
		            <option value="0">- All -</option>
		            <c:forEach items="${projectList}" var="project">
		              <option value="${project.projectId}">${project.projectName}</option>
		            </c:forEach>
		          </select>
		        </div>
		      </div>
		
		      <!-- Message container -->
		      <div id="project-expenditure-msg" 
		           style="text-align:center; padding: 20px; font-size: 16px; color: #666;">
		        Please select a Buildings to see the expenditure amount.
		      </div>
		
		      <!-- Chart container -->
		      <div class="d-flex justify-content-center">
		        <div id="project-expenditure-pie" style="width: 100%; height: 400px;"></div>
		      </div>
		    </div>
		  </div>
		</div>
		
		<div class="col-md-6">
		  <div class="card">
		    <div class="card-body">
				<div class="row mb-3">
					<div class="col-md-6">
						<h6 class="section-title">Grievance Status Report</h6>
					</div>
					<div class="col-md-6">
						<select name="errorCategoryId" id="errorCategoryId" class="form-control form-control-sm form-select select2" onchange="loadGrievancesByErrorCategory(this.value)">
							<option value="0" selected>- All -</option>
							<c:forEach items="${grievanceCategoryList}" var="err">
								<option value="${err.errorCategoryId}">${err.categoryType}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="table-responsive mt-3" style="max-height: 450px; overflow-y: auto; position: relative;">
				  <table id="grievanceTable" class="table table-bordered table-hover no-footer" style="table-layout: fixed; width: 100%;">
				    <thead style="position: sticky; top: -1px; z-index: 10; background-color: #ffffff;">
				      <tr>
				        <th style="width: 10%;">Sl.No</th>
				        <th>Token No.</th>
				        <th style="width: 20%;">Contact No</th>
				        <th>Email Id</th>
				        <th>Status</th>
				      </tr>
				    </thead>
				    <tbody id="grievanceTableBody">
				      <c:forEach items="${grievanceList}" var="grvList" varStatus="count">
				        <tr>
				          <td>${count.count}</td>
				          <td>${grvList.tokenNo}</td>
				          <td  style="width:12%;">${grvList.contactNo}</td>
				          <td>${grvList.emailId}</td>
				          <td style="width:30%;">
							 <button class="status-badge js-status-btn" data-status="${grvList.status}"></button>
						  </td>
				        </tr>
				      </c:forEach>
				    </tbody>
				  </table>
				</div>
		    </div>
		  </div>
		</div>

		<div class="col-md-12">
			<div class="card">
				<div class="card-body edjust">
					<h6 class="section-title mb-4">District-wise Buildings Count</h6><span id="project-status"></span>
					<div class="chart-container" style="position: relative;">
						<div id="project-district-graph" style="max-width: 100%; height: 400px;"></div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-12">
			<div class="card">
				<div class="card-body edjust">
					<h6 class="section-title mb-4">District-wise Maintenance Count</h6><span id="maintenance-status"></span>
					<div class="chart-container" style="position: relative;">
						<div id="maintenance-district-graph" style="max-width: 100%; height: 400px;"></div>
					</div>
				</div>
			</div>
		</div>
    </div>
</div>

<!-- Tooltip container with modern styling -->
		<div id="tooltip" style="
		    position: absolute;
		    background: linear-gradient(135deg, #26a69a, #42a5f5);
		    color: #ffffff;
		    border: 2px solid #e0f7fa;
		    border-radius: 10px;
		    padding: 15px;
		    display: none;
		    pointer-events: none;
		    z-index: 1000;
		    max-width: 400px;
		    font-size: 14px;
		    font-family: 'Arial', sans-serif;
		    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
		    transition: transform 0.2s ease;
		">
		</div>

<script src="${contextPath}/assets/js/apexChart/apexcharts.min.js" ></script>
<script src="${contextPath}/assets/js/svgMap.js"></script>
<script src="${contextPath}/assets/js/jbox.js"></script>
<script src="${contextPath}/assets/apex-chart/districtwiseinspectionprojectdata.js"></script>
<script src="${contextPath}/assets/apex-chart/districtwiseprojectcount.js"></script>
<script src="${contextPath}/assets/apex-chart/districtwisemaintenancecount.js"></script>
<script src="${contextPath}/assets/apex-chart/projectwiseexpendituredata.js"></script>
<script src="${contextPath}/assets/apex-chart/departmentwiseprojectcount.js"></script>
<script src="${contextPath}/assets/apex-chart/buildingType.js"></script>
<script src="${contextPath}/assets/apex-chart/sectorwiseexpenducter.js"></script>
<script src="${contextPath}/assets/apex-chart/expenditureStatistics.js"></script>

 
 <script>
 
 	var contextPath = "${pageContext.request.contextPath}";
 
	function LoadDashBoardDataByFinancialYear(finYearId) {
		
	    if (!finYearId) {
	        $('#projectCount, #approvedProjectCount, #pendingProjectCount').text("0");
	        $('#estimationCount, #approvedEstimationCount, #pendingEstimationCount').text("0");
	        $('#workorderCount, #approvedWorkorderCount, #pendingWorkorderCount').text("0");
	        $('#projectListBody').html('<tr><td colspan="4" class="text-center text-muted">Please select a financial year.</td></tr>');
	        return;
	    }
	
	    $.ajax({
	        type: "GET",
	        url: "${contextPath}/scst/getDashBoardDataByFinancialYear",
	        data: {
	            finYearId: finYearId,
	        },
	        beforeSend: function () {
	            $('.card').addClass('loading');
	        },
	        success: function (dashBoard) {
	            console.log("Dashboard Data:", dashBoard);
	
	            $('#projectCount').text(dashBoard.projectCount || 0);
	            $('#approvedProjectCount').text(dashBoard.approvedProjectCount || 0);
	            $('#pendingProjectCount').text(dashBoard.pendingProjectCount || 0);
	
	            $('#estimationCount').text(dashBoard.estimationCount || 0);
	            $('#approvedEstimationCount').text(dashBoard.approvedestimationCount || 0);
	            $('#pendingEstimationCount').text(dashBoard.pendingestimationCount || 0);
	
	            $('#workorderCount').text(dashBoard.workorderCount || 0);
	            $('#approvedWorkorderCount').text(dashBoard.approvedworkorderCount || 0);
	            $('#pendingWorkorderCount').text(dashBoard.pendingworkorderCount || 0);
	            
	         	// Reset pie chart
	            loadProjectExpenditureData(0);
	         	
	             var projectDropDowns = $('#projectId'); 
	             projectDropDowns.empty();
	             projectDropDowns.append('<option value="0">- All -</option>');
	
	             if (!dashBoard.projectDropDownList || dashBoard.projectDropDownList.length === 0) {
	                 $("#project-expenditure-msg").html(
	                     '<div style="text-align:center; font-size: 15px; color: red;">' +
	                     'No projects available for this financial year.' +
	                     '</div>'
	                 );
	             } else {
	                 $("#project-expenditure-msg").html(
	                     '<div style="text-align:center;font-size: 15px; color: #666;">' +
	                     'Please select a project to see the expenditure amount.' +
	                     '</div>'
	                 );
	                 $.each(dashBoard.projectDropDownList, function(index, dropDownData) {
	                     projectDropDowns.append(
	                         '<option value="' + dropDownData.projectId + '">' + dropDownData.projectName + '</option>'
	                     );
	                 });
	             }
	             
	             $('#projectListBody').empty();
	
	             if (dashBoard.projectList && dashBoard.projectList.length > 0) {
	                 dashBoard.projectList.forEach(function (project, index) {
	                     // Get status info from StatusUtil
	                     var statusInfo = StatusUtil.getStatusInfo(project.status);
	
	                     var rowHtml =
	                         "<tr>" +
	                         "<td>" + (index + 1) + "</td>" +
	                         "<td><span>" + (project.projectName || "") + "</span></td>" +
	                         "<td><span>" + (project.startDate || "") + "</span></td>" +
	                         "<td><span>" + (project.completionDate || "") + "</span></td>" +
	                         "<td>" +
	                         '<button class="status-badge ' + statusInfo.cssClass + '">' +
	                         '<i class="fas ' + statusInfo.icon + '"></i> ' + statusInfo.label +
	                         "</button>" +
	                         "</td>" +
	                         "</tr>";
	
	                     $("#projectListBody").append(rowHtml);
	                 });
	             } else {
	                 var noDataHtml =
	                     '<tr>' +
	                     '<td colspan="5" class="text-center text-muted">No projects available for this financial year.</td>' +
	                     '</tr>';
	                 $("#projectListBody").append(noDataHtml);
	             }
	
	            $('.card').removeClass('loading');
	        },
	        error: function (error) {
	            console.error("Error fetching dashboard data:", error);
	            $('.card').removeClass('loading');
	            alert("Failed to load dashboard data. Please try again.");
	        }
	    });
	}

	$(document).ready(function () {
	    showLoader();
	    const defaultYear = $("#financialYear").val();
	    if (defaultYear && defaultYear !== "0") {
	        LoadDashBoardDataByFinancialYear(defaultYear);
	    }
	    const cards = document.querySelectorAll('.card');
	    cards.forEach(card => {
	        card.classList.add('loading');
	    });
	    setTimeout(() => {
	        cards.forEach(card => {
	            card.classList.remove('loading');
	        });
	        $(".loader-div").hide();
	    }, 1500);
	});
	
	function showLoader() {
	    $(".loader-div").css("display", "flex");
	}

  function loadGrievancesByErrorCategory(selectedId) {
    $.ajax({
      url: "${contextPath}/scst/fetchGrievanceListDataByErrorCategoryId",
      type: "GET",
      data: { errorCategoryId: selectedId },
      success: function (data) {
        var tbody = $("#grievanceTableBody");
        tbody.empty();

        if (data && data.length > 0) {
            data.forEach(function (grv, index) {
                // Get status info from StatusUtil
                var statusInfo = StatusUtil.getStatusInfo(grv.status);

                var row = "<tr>"
                    + "<td>" + (index + 1) + "</td>"
                    + "<td>" + (grv.tokenNo || "") + "</td>"
                    + "<td>" + (grv.contactNo || "") + "</td>"
                    + "<td>" + (grv.emailId || "") + "</td>"
                    + "<td>"
                    + "<button class='status-badge " + statusInfo.cssClass + "'>"
                    + "<i class='fas " + statusInfo.icon + "'></i> " + statusInfo.label
                    + "</button>"
                    + "</td>"
                    + "</tr>";

                tbody.append(row);
            });
        } else {
            tbody.append("<tr><td colspan='5' class='text-center'>No data available</td></tr>");
        }
      },
      error: function () {
        alert("Error while fetching grievances.");
      }
    });
  }
</script>

<script>

const StatusUtil = (function () {
	  'use strict';

	  const statusMap = {
	    DRAFT: { label: "Draft", icon: "fa-pencil-alt", cssClass: "status-draft" },
	    APPROVE: { label: "Approved", icon: "fa-check-circle", cssClass: "status-approved" },
	    REJECT: { label: "Reject", icon: "fa-times-circle", cssClass: "status-rejected" },
	    SUBMIT_TO_NEXT_LEVEL: { label: "In Progress", icon: "fa-paper-plane", cssClass: "status-submit" },
	    VERIFY_AT_NEXT_LEVEL: { label: "In Progress", icon: "fa-paper-plane", cssClass: "status-submit" },
	    REVISED: { label: "Revised", icon: "fa-edit", cssClass: "status-revised" },
	    PARTIAL_APPROVE: { label: "Partial", icon: "fa-adjust", cssClass: "status-partial" },
	    PUBLISH: { label: "Published", icon: "fa-bullhorn", cssClass: "status-publish" },
	    VERIFY_AND_SEND_FOR_APPROVAL: { label: "In Progress", icon: "fa-paper-plane", cssClass: "status-submit" },
	    CLOSE: { label: "Closed", icon: "fa-lock", cssClass: "status-closed" },
	    SEND_FOR_APPROVAL: { label: "In Progress", icon: "fa-paper-plane", cssClass: "status-submit" },
	    REVERT: { label: "Reverted", icon: "fa-undo", cssClass: "status-revert" },
	    APPROVE_PUB: { label: "Approve+Pub", icon: "fa-check-double", cssClass: "status-approve-pub" }
	  };

	  var getInfo = function (statusCode) {
	    if (!statusCode) return { label: "Unknown", icon: "fa-question-circle", cssClass: "status-unknown" };
	    var s = statusCode.toString().trim().toUpperCase();
	    return statusMap[s] || { label: statusCode, icon: "fa-question-circle", cssClass: "status-unknown" };
	  };

	  return {
	    getStatusInfo: getInfo,

	    renderStatusButtons: function (container) {
	      container = container || document;
	      var buttons = container.querySelectorAll('.js-status-btn');
	      for (var i = 0; i < buttons.length; i++) {
	        var btn = buttons[i];
	        var statusCode = btn.dataset.status;
	        var info = getInfo(statusCode);

	        btn.innerHTML = '';
	        btn.className = 'status-badge ' + info.cssClass;

	        var icon = document.createElement('i');
	        icon.className = 'fas ' + info.icon;
	        btn.appendChild(icon);

	        var label = document.createElement('span');
	        label.textContent = info.label;
	        btn.appendChild(label);
	      }
	    }
	  };
	})();
	
document.addEventListener("DOMContentLoaded", function () {
	  StatusUtil.renderStatusButtons();
	});

</script>

