$(document).ready(function(){
	fetchDashboard();
});

function fetchDashboard()
{
	window.loadCounter = 0;
	const REPORTING_API_BASE = "/dashboard/";
	
	var finYear = $("#finYearId").val();
	if(finYear != '0')
	{
		var parts = finYear.split("-");
		var formattedYear = parts[0] + "-" + parts[1].slice(2);
		$("#finSpanId").text(formattedYear);

		var moduleCode = $("#moduleCode").val();

		if (moduleCode === "IPMS")
		{
			$("#ipmsDivId").removeClass("hidden");
			$("#hrmsDivId").addClass("hidden");
			$("#grvDivId").addClass("hidden");
			$("#ecashbookDivId").addClass("hidden");
			$("#famsDivId").addClass("hidden");
			$("#hoardingDivId").addClass("hidden");
			$("#rentClcDivId").addClass("hidden");


			// IPMS Box Details
			window.boxPresenter = new BoxInfoPresenter(REPORTING_API_BASE, window.contextPath, finYear);
			window.boxPresenter.doFetch();
		}
		else if (moduleCode === "CORE")
		{
			$("#hrmsDivId").removeClass("hidden");
			$("#ipmsDivId").addClass("hidden");
			$("#grvDivId").addClass("hidden");
			$("#ecashbookDivId").addClass("hidden");
			$("#famsDivId").addClass("hidden");
			$("#hoardingDivId").addClass("hidden");
			$("#rentClcDivId").addClass("hidden");

			// CORE details
			var employmentType = $("#employmentType").val();
			window.payrollMgmt = new PayrollMgmt(REPORTING_API_BASE, window.contextPath, finYear, employmentType);
			window.payrollMgmt.doFetch();
		}
		else if (moduleCode === "GRIEVANCE")
		{
			$("#grvDivId").removeClass("hidden");
			$("#hrmsDivId").addClass("hidden");
			$("#ipmsDivId").addClass("hidden");
			$("#ecashbookDivId").addClass("hidden");
			$("#famsDivId").addClass("hidden");
			$("#hoardingDivId").addClass("hidden");
			$("#rentClcDivId").addClass("hidden");

			// Status wise grievance details
			var monthId = $("#stsMonthId").val();
			window.grievance = new Grievance(REPORTING_API_BASE, window.contextPath, finYear, monthId);
			window.grievance.doFetch();

			// Category wise grievance details
			window.grievanceCategory = new GrievanceCategory(REPORTING_API_BASE, window.contextPath, finYear, monthId);
			window.grievanceCategory.doFetch();
		}
		else if (moduleCode === "FDMS")
		{
			$("#ecashbookDivId").removeClass("hidden");
			$("#hrmsDivId").addClass("hidden");
			$("#ipmsDivId").addClass("hidden");
			$("#grvDivId").addClass("hidden");
			$("#famsDivId").addClass("hidden");
			$("#hoardingDivId").addClass("hidden");
			$("#rentClcDivId").addClass("hidden");


			// Financial year wise budget details
			window.budgetAllocation = new BudgetAllocation(REPORTING_API_BASE, window.contextPath, finYear);
			window.budgetAllocation.doFetch();

			// Scheme wise budget allocation
			window.fundUtilizationAmountScheme = new FundUtilizationAmountScheme(REPORTING_API_BASE, window.contextPath, finYear);
			window.fundUtilizationAmountScheme.doFetch();

			// Scheme wise budget utilization
			window.fundUtilizationProjectScheme = new FundUtilizationProjectScheme(REPORTING_API_BASE, window.contextPath, finYear);
			window.fundUtilizationProjectScheme.doFetch();
		}
		else if (moduleCode === "FAMS")
		{
			$("#famsDivId").removeClass("hidden");
			$("#hrmsDivId").addClass("hidden");
			$("#ipmsDivId").addClass("hidden");
			$("#grvDivId").addClass("hidden");
			$("#ecashbookDivId").addClass("hidden");
			$("#hoardingDivId").addClass("hidden");
			$("#rentClcDivId").addClass("hidden");

			// Month wise amount utilization
			window.fundUtilizationAmount = new FundUtilizationAmount(REPORTING_API_BASE, window.contextPath, finYear);
			window.fundUtilizationAmount.doFetch();

			// Month wise project utilization
			window.fundUtilizationProject = new FundUtilizationProject(REPORTING_API_BASE, window.contextPath, finYear);
			window.fundUtilizationProject.doFetch();
		}
		else if (moduleCode === "HOARDING")
		{
			$("#hoardingDivId").removeClass("hidden");
			$("#famsDivId").addClass("hidden");
			$("#hrmsDivId").addClass("hidden");
			$("#ipmsDivId").addClass("hidden");
			$("#grvDivId").addClass("hidden");
			$("#ecashbookDivId").addClass("hidden");
			$("#rentClcDivId").addClass("hidden");

			// Hoarding information details
			var areaId=$("#areaId").val();
			var hoardingId = $("#hoardingId").val();
			window.hoardingManagement = new HoardingManagement(REPORTING_API_BASE, window.contextPath, finYear, areaId, hoardingId);
			window.hoardingManagement.doFetch();
		}
		else if (moduleCode === "RENT_COLLECTION")
		{
			$("#rentClcDivId").removeClass("hidden");
			$("#hoardingDivId").addClass("hidden");
			$("#famsDivId").addClass("hidden");
			$("#hrmsDivId").addClass("hidden");
			$("#ipmsDivId").addClass("hidden");
			$("#grvDivId").addClass("hidden");
			$("#ecashbookDivId").addClass("hidden");

			// Rent collection details
			window.rentCollection = new RentCollection(REPORTING_API_BASE, window.contextPath, finYear);
			window.rentCollection.doFetch();
		}
		else
		{
			$("#rentClcDivId").addClass("hidden");
			$("#hoardingDivId").addClass("hidden");
			$("#famsDivId").addClass("hidden");
			$("#hrmsDivId").addClass("hidden");
			$("#ipmsDivId").addClass("hidden");
			$("#grvDivId").addClass("hidden");
			$("#ecashbookDivId").addClass("hidden");
		}
	}
}

function fetchStatusWiseGrievance()
{
	window.loadCounter = 0;
	const REPORTING_API_BASE = "/dashboard/";
	var finYear = $("#finYearId").val();
	var monthId = $("#stsMonthId").val();
	
	window.grievance = new Grievance(REPORTING_API_BASE, window.contextPath, finYear, monthId);
	window.grievance.doFetch();
}

function fetchCategoryWiseGrievance()
{
	window.loadCounter = 0;
	const REPORTING_API_BASE = "/dashboard/";
	var finYear = $("#finYearId").val();
	var monthId = $("#stsMonthId").val();
	
	window.grievanceCategory = new GrievanceCategory(REPORTING_API_BASE, window.contextPath, finYear, monthId);
	window.grievanceCategory.doFetch();
}

function fetchHoardingInfoDetails()
{
	window.loadCounter = 0;
	const REPORTING_API_BASE = "/dashboard/";
	var finYear = $("#finYearId").val();
	var areaId=$("#areaId").val();
	var hoardingId = $("#hoardingId").val();
	window.hoardingManagement = new HoardingManagement(REPORTING_API_BASE, window.contextPath, finYear, areaId, hoardingId);
	window.hoardingManagement.doFetch();
}

function fetchPayrollDetails()
{
	window.loadCounter = 0;
	const REPORTING_API_BASE = "/dashboard/";
	var finYear = $("#finYearId").val();
	var employmentType = $("#employmentType").val();
	window.payrollMgmt = new PayrollMgmt(REPORTING_API_BASE, window.contextPath, finYear, employmentType);
	window.payrollMgmt.doFetch();
}