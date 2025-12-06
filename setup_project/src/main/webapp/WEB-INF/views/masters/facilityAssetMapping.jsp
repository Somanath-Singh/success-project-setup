<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<c:set var="userDetails" value="${serviceOutcome.data}"/> 
<script type="text/javascript" src="${contextPath}/assets/appJs/validation/common-utils.js"></script>

<style>
.alert-danger {
    border-color: #f3acbb !important;
    color: #730503;
}
/*Profile Pic Start*/
.picture-container {
    position: relative;
    cursor: pointer;
    text-align: center;
}
.picture {
    width: 106px;
    height: 106px;
    background-color: #999999;
    border: 4px solid #CCCCCC;
    color: #FFFFFF;
    border-radius: 0px;
    margin: 10px auto;
    overflow: hidden;
    transition: all 0.2s;
    -webkit-transition: all 0.2s;
}
.picture:hover {
    border-color: #2ca8ff;
}
.content.ct-wizard-green .picture:hover {
    border-color: #05ae0e;
}
.content.ct-wizard-blue .picture:hover {
    border-color: #3472f7;
}
.content.ct-wizard-orange .picture:hover {
    border-color: #ff9500;
}
.content.ct-wizard-red .picture:hover {
    border-color: #ff3b30;
}
.picture input[type="file"] {
    cursor: pointer;
    display: block;
    height: 100%;
    left: 0;
    opacity: 0 !important;
    position: absolute;
    top: 0;
    width: 100%;
}
.picture img {
    width: 100%;
    height: 106px;
}
.picture-src {
    width: 100%;
}
.btn-primary {
    color: #fff;
    background-color: #233882 !important;
    border-color: #233882 !important;
    padding: 3px 6px 6px 6px !important;
}
</style>

<style>
.alert {
    padding: 5px 0px 7px 5px;
    border-radius: 1px;
    text-align: center;
    font-size: 14px;
    margin-left: 0px;
    width: 100%;
}

.alert-success {
    background-color: #c3f3af;
    border-color: #9ed289 !important;
    color: #194219;
}

.alert-danger {
    background-color: #e66f70;
    border-color: #f3acbb !important;
    color: #232120;
}

.close {
    font-size: 23px;
    opacity: 0.4;
    margin-right: 5px;
}


</style>

<div class="breadcrumb_conatiner">
    <div class="col-md-6">
        <h4 class="change-color">Facility Asset Map</h4>
    </div>
    <div class="col-md-6">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="${contextPath}/home"><i class="fa fa-home"></i></a>
                </li>
                <li class="breadcrumb-item active" aria-current="page">Facility Asset Map</li>
            </ol>
        </nav>
    </div>
</div>


<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="card-header">
                <h5 class="card-title">Facility Asset Map</h5>
            </div>
            <div class="card-body">
                <form class="form-horizontal" action="${contextPath}/assetFacility/saveAssetFacilityMapDetails" method="POST" id="facilityAssetMapForm">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="objectArrayEncode" id="objectArrayEncodeId"/>
                    <div class="row">
                       <div class="cardcontainer cardContainerNotFirst">
						 	<h6 class="headingbg mt-3">Map Asset</h6>
							<div class="col-md-12 mt-3">
							<table class="table table-bordered exportbtn" id="tableId">
								    <thead>
								        <tr>
								            <th>SlNo.</th>
								            <th>Brand Name</th>
								            <th>Asset Name</th>
                                            <th>Model Name</th>
                                            <th>Serial Number</th>
                                            <th>Select <span>Check All</span> <input type="checkbox" id="allCheckBoxId" onclick="checkUncheckAll(this)" checked/>
								        </tr>
								    </thead>
									<tbody id="tableBodyId">
									<c:forEach items="${assetList}" var="asset" varStatus="count">
                                        <c:if test="${asset.isMapped == false}">
                                            <tr>
                                                <td>${count.count}</td>
                                                <td>${asset.brandName}</td>
                                                <td>${asset.assetName}</td>
                                                <td>${asset.modelNo}</td>
                                                <td>${asset.serialNo}</td>
                                                <td>
                                                    <input type="checkbox" value="${asset.assetFacilityId},${mappingId}" onclick="checkUncheckInd(this)" <c:if test="${asset.isSelected == true}">checked</c:if>/>
                                                </td>
                                            </tr>
                                        </c:if>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="form-group col-md-12 text-center">
							<button type="button" class="btn btn-success btn-sm" id="btnAssign" onclick="AssignAssetDtls()">Assign</button>
							<a href="${contextPath}/facility/addFacilities" class="btn btn-danger btn-sm">Cancel</a>
						</div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>

    function checkUncheckAll(that){
        if(that.checked){
            $('tbody input[type="checkbox"]').each(function(){
                this.checked = true;
            });
        }else{
            $('tbody input[type="checkbox"]').each(function(){
                this.checked = false;
            });
        }
    }

    $(document).ready(function(){
        // if all checkboxes are selected in tbody tr then check the select all checkbox in thead tr
        // by id tableBodyId
        let allTr = $('#tableBodyId tr');
        let allCheckBox = $('#allCheckBoxId');
        // loop through all tr and check if all checkboxes are checked
        allTr.each(function(){
            let tr = $(this);
            let checkBox = tr.find('input[type="checkbox"]');
            if(checkBox.prop('checked') == false){
                allCheckBox.prop('checked', false);
                return false;
            }
        });
        
    });

    function checkUncheckInd(that){
        if(that.checked == false){
            $('#allCheckBoxId').prop('checked', false);
        }else{
            if($('tbody input[type="checkbox"]').length == $('tbody input[type="checkbox"]:checked').length){
                $('#allCheckBoxId').prop('checked', true);
            }
        }
    }


function AssignAssetDtls(){
	
	var objectArray = [];
	var getOnlyChecked = $(' tbody input[type="checkbox"]:checked');
    for (var i = 0; i < getOnlyChecked.length; i++) {
        var value = getOnlyChecked[i].value;
        var splitValue = value.split(",");
        var assetFacilityId = splitValue[0];
        var mappingId = splitValue[1];
        var object = {
            assetFacilityId: assetFacilityId,
            mappingId: mappingId,
            isCheck: true
        };
        objectArray.push(object);
    }
    var getOnlyUnChecked = $(' tbody input[type="checkbox"]:not(:checked)');
    for (var i = 0; i < getOnlyUnChecked.length; i++) {
        var value = getOnlyUnChecked[i].value;
        var splitValue = value.split(",");
        var assetFacilityId = splitValue[0];
        var mappingId = splitValue[1];
        var object = {
            assetFacilityId: assetFacilityId,
            mappingId: mappingId,
            isCheck: false
        };
        objectArray.push(object);
    }

    if (objectArray.length == 0) {
        bootbox.alert({
            message: "Please select atleast one asset to assign",
            buttons: {
                ok: {
                    label: 'Ok',
                    className: 'btn-danger'
                }
            }
        });
        return false;
    }

    var objectArrayEncode = btoa(JSON.stringify(objectArray));

    bootbox.confirm({
        message: "Are you sure you want to assign the selected assets?",
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
            if (result) {
                $("#objectArrayEncodeId").val(objectArrayEncode);
                $("#facilityAssetMapForm").submit();
            }
        }
    });
	
	
	

}

</script>
