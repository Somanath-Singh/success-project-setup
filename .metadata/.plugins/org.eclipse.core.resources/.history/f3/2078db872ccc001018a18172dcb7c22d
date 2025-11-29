<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
.inr-table thead th {
    background: #e3e3ff !important;
}
.inr-table td, .inr-table th {

    font-size: 10px;
    }
	.patch {
	background: #a57b7b;
    border-bottom: 1px solid #ccc;
    /* padding-bottom: 5px; */
    display: flex;
    align-items: center;
    flex-wrap: wrap;
	
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    padding: 20px;
	background-color: white;
  	border-radius: 10px;
	border-radius: 10px;
	margin-bottom: 20px;
	}

	.disabled-field {
		pointer-events: none;
		opacity: 0.7; /* You can adjust the opacity to your preference */
	}

	.error-message {
		color: red;
	}

	.uuidIcon{
		position: absolute;
    	right: 4px;
    	top: 5px;
    	background: #0346e8;
    	color: #fff;
    	padding: 4px;
	}

	


</style>
<section class="main_container">
<div class="breadcrumb_conatiner">
	<div class="col-md-6">
		<h4 class="change-color">Stage Tab Configuration</h4>
	</div>
	<div class="col-md-6">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${contextPath}/home"><i class="fa fa-home"></i></a></li>
					<li class="breadcrumb-item active" aria-current="page">Stage Tab Configuration</li>
				</ol>
			</nav>
	</div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header d-flex justify-content-between">
                <h5 class="card-title">Stage Tab Configuration</h5>
            </div>
            <div class="card-body">
                <div class="row " style="display: flex; justify-content: flex-center; flex-wrap: wrap;">
                    <div class="col-md-12">
                        <div class="bagGroundColor">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label" style="color: #070707;">Select Activity</label>
                                        <select class="col-md-3 form-select form-control" id="workFlowTableId" onchange="getOrgMapListData(this)" name="modalName">
                                            <option value="" selected disabled>Select Stage</option>
                                            <c:forEach items="${allEntityClasses}" var="stage">
                                                <option value="${stage.packageName}" <c:if test="${stage.packageName eq entityClassName}">selected</c:if>>${stage.className}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <!-- select stage -->
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label" style="color: #000000;">Select Stage</label>
                                        <select class="col-md-3 form-select form-control" id="orgMapId" onchange="getStageConfigData(this)" name="modalName">
                                            <option value=""  selected disabled>Select Stage</option>
                                            <c:forEach items="${orgMapList}" var="orgMap">
                                                <option value="${orgMap.id}">${orgMap.dateRange}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mt-3" id="dataSection">
                    <div class="table-responsiv">
                        <table class="table table-bordered table-hover table-striped" id="sct">
                            <thead class="bagGroundColorRvr" style="color: #000000;">
                                <tr>
                                    <th class="text-center" style="width: 60px;">Sl. No</th>
                                    <th>Forword From -> To</th>
                                    <th>Activity Name</th>
                                    <th>Stage Name</th>
                                    <th style="width: 100px;">Action</th>
                                </tr>
                            </thead>
                            <tbody id="actionListBody"></tbody>
                        </table>
                    </div>
                </div>
                <div class="row" style="display: flex; justify-content: center; flex-wrap: wrap; margin-top: 20px;">
                    <div class="col-md-12 text-center">
                        <button type="button" id="satgeConfigBtnId" class="btn btn-success" onclick="saveStageTabConfigData()">Save</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</section>


<form id="stageConfigForm" action="${contextPath}/stageConfig/stageTabConfig" method="Get">
    <input type="hidden" name="entityClassName" id="entityClassNameId">
</form>

<form action="${contextPath}/stageConfig/saveStageTabConfig" method="POST" id="saveStageTabConfigForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="saveStageTabConfigStr" id="saveStageTabConfigId">
</form>


<script>

    function getOrgMapListData(that){
        var entityClassName = $(that).val();
        $("#entityClassNameId").val(entityClassName);
        $("#stageConfigForm").submit();
    }

    function getStageConfigData(obj) {
        var orgMapId = $(obj).val();
        if (orgMapId != "") {
            $.ajax({
                url: "${contextPath}/stageConfig/stageForwardedRules",
                type: "GET",
                data: {orgMapId: orgMapId},
                success: function (response) {
                        console.log(response);
                        let actionListBody = "";
                        if(response.outcome){
                            let data = response.data;
                            for (let i = 0; i < data.length; i++) {
                                actionListBody += "<tr id='actionListRow"+i+"'>";
                                actionListBody += "<td class='text-center'>"+(i+1)+"</td>";
                                actionListBody += "<td>"+data[i].frwFrmTo+"</td>";
                                actionListBody += "<td>"+data[i].activityName+"</td>";
                                actionListBody += "<td>"+data[i].stageName+"</td>";
                                actionListBody += "<td><button type='button' class='btn btn-info' onclick='getTabAndRoles(this, "+data[i].id+")'><i class='fa fa-edit'></i></button></td>";
                                actionListBody += "<input type='hidden' value='"+data[i].id+"' id='ruleId+"+i+"'>";
                                actionListBody += "</tr>";
                            }
                        }


                        $("#actionListBody").html(actionListBody);
                    },
                error: function (xhr, status, error) {
                    alert(xhr.responseText);
                }
            });
        }
    }
    let underTrId = 0;
    let roleListMainRowId = 0;
    async function getTabAndRoles(that, rulId){
        // get seleted orgMapId
        let orgMapId = $("#orgMapId").val();
        if(rulId != ''){
            // once clicked Disable that button
            $(that).attr('disabled', 'disabled');
            $.ajax({
                url: "${contextPath}/stageConfig/getTabAndRolesByRuleId",
                type: "GET",
                data: {ruleId: orgMapId},
                success: function (response) {
                    if(response.outcome){
                        debugger;
                        // if allready underTr added to this tr then return // get current tr id
                        let currentTrId = $(that).closest('tr').attr('id'); //actionListRow0 -> 0
                        let currentTrIdArr = currentTrId.split('actionListRow');
                        let currentTrIdNum = currentTrIdArr[1];
                        // roleListMainRowcurrentTrIdNum -> roleListMainRow0 if this id exist then return
                        if($("#roleListMainRow"+currentTrIdNum).length > 0){
                            // delete this tr
                            $("#roleListMainRow"+currentTrIdNum).remove();
                            $(that).removeAttr('disabled');
                            return;
                        }
                        

                        debugger;
                        let data = response.data;
                        console.log(data);
                        debugger
                        let roleList = data.roles;
                        let status = data.status;
                        let underTr = "";
                        for (let i = 0; i < roleList.length; i++) {
                            let roleViewId = roleList[i].roleViewId;
                            let roleId = roleList[i].roleId;
                            let roleName = roleList[i].roleName;

                            // create green color tr for role
                            underTr += "<tr id='underTrId"+underTrId+"'>";
                            underTr += "<td class='text-center'>"+(i+1)+"</td>";
                            underTr += "<td>"+roleName+"</td>";
                            // loop for status
                            let statusSelect = "<select class='form-control form-select' id='statusSelect"+underTrId+"'>";
                                statusSelect += "<option value=''>Select Status</option>";
                            for (let j = 0; j < status.length; j++) {
                                let selected = status[j].isExist ? 'selected' : '';
                                
                                

                                statusSelect += "<option value='"+status[j].statusId+"' "+selected+">"+status[j].statusName+"</option>";
                            }
                            statusSelect += "</select>";
                            underTr += "<td>"+statusSelect+"</td>";
                            underTr += "<input type='hidden' value='"+roleViewId+"' id='underRvId"+underTrId+"'>";
                            underTr += "<input type='hidden' value='"+roleId+"' id='underRlId"+underTrId+"'>";
                            underTr += "<input type='hidden' value='"+rulId+"' id='underRuId"+underTrId+"'>";
                            underTr += "</tr>";
                            underTrId++;
                        }
                        // add this underTr to main tr with left side margin 20px
                        let mainTr = $(that).closest('tr');
                        mainTr.after("<tr id='roleListMainRow"+roleListMainRowId+"'><td colspan='5'><table class='table inr-table table-bordered table-hover table-striped' style='margin-left: 0px;'><thead ><tr><th class='text-center' style='width:60px;'>Sl. No</th><th>Role Name</th><th>Tab</th></tr></thead><tbody>"+underTr+"</tbody></table></td></tr>");
                        roleListMainRowId++;
                        $(that).removeAttr('disabled');
                    }
                    
                },
                error: function (xhr, status, error) {
                    alert(xhr.responseText);
                    $(that).removeAttr('disabled');
                }
            });
        }
    }

    function saveStageTabConfigData(){
        // get all tr which id start with underTrId
        let underTrList = $("[id^='underTrId']");
        let underTrData = [];
        for (let i = 0; i < underTrList.length; i++) {
            let underTr = underTrList[i];
            let underRvId = $("#underRvId"+i).val();
            let underRlId = $("#underRlId"+i).val();
            let underRuId = $("#underRuId"+i).val();
            let statusSelect = $("#statusSelect"+i).val();
            if(statusSelect == ''){
                continue;
            }
            underTrData.push({roleViewId: underRvId, roleId: underRlId, ruleId: underRuId, statusId: statusSelect});
        }
        let finalData = {};
        finalData['underTrData'] = underTrData;
        finalData['orgMapId'] = $("#orgMapId").val();
        finalData['workFlowTableCode'] = $("#workFlowTableId").val();

        finalData = btoa(JSON.stringify(finalData));


        if(underTrData.length == 0){
            bootbox.alert("Please select status for at least one role.");
            return;
        }

        bootbox.confirm({
            message: "Are you sure you want to save this data?",
            buttons: {
                confirm: {
                    label: 'Yes',
                    className: 'btn-success'
                },
                cancel: {
                    label: 'No',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if(result){
                    $("#saveStageTabConfigId").val(finalData);
                    $("#saveStageTabConfigForm").submit();
                }
            }
        });


    }






</script>