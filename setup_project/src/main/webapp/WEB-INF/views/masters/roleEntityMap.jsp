<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
	.isRoleObjReadOnly {
		pointer-events: none;
		background-color: #868181 !important;
	}

	.levelFS {
		font-size: 12px;
	}
	.levelFS.required::after{
	    font-size: 10px;
	}

</style>



<div class="form-group col-md-3">
	<label class="levelFS required" >Roles Belongs to This Entity :</label>
	<div class="col-md-12">
		<select class="form-control form-control-sm  requiredFieldSelectpicker" name="otherRoleCodes" multiple data-live-search="true" id="otherRoleCodes">
			<option value="" aria-selected="false" disabled>Select Role</option>
		</select>
	</div>
</div>

<script>
//



// ready function
$(document).ready(function(){
	debugger;
	let entityLevelCode = "${entityLevelCode}";
	let entityIdCode = "${entityIdCode}";
	let selectedEntityIdAndType = "";
	if(entityLevelCode != "" && entityIdCode != "" && entityLevelCode != null && entityIdCode != null){
		selectedEntityIdAndType = entityIdCode + "##" + entityLevelCode
	}
	// call getRoleList function
	getRoleList($('#objectIdAndTypeId'), selectedEntityIdAndType);

});


	async function getRoleList(that, selectedEntityIdAndType = ""){
		let value = $(that).val();
		if(value != ""){
			let url = "${contextPath}/system/getPublicAndEntitySpecificRoleList";
			let data = {
				"entityIdAndType" : btoa(value),
				"selectedEntityIdAndType" : btoa(selectedEntityIdAndType),
			};
			let method = "GET";
			let response = await asyncAjaxForData(url, data, method);
			debugger;
			if(response.outcome){
				let data = response.data;
				let selectedRoleList = JSON.parse(data.selectedRoleList);
				let roleList = JSON.parse(data.roleList);
				let html = "<option value='' disabled>Select Roles</option>";
				
				// if roleList is not empty
				if(roleList != "" && roleList != null && roleList.length > 0){
					$.each(roleList,function(index, value) {
						let selected = "";
						let isRoleObjReadOnly = "";
						// if this role is selected then make it readonly
						for(let i = 0; i < selectedRoleList.length; i++){
							if(selectedRoleList[i].roleCode == value.roleCode){
								selected = "selected";
								isRoleObjReadOnly = "isRoleObjReadOnly";
								break;
							}
						}
						html = html + "<option value="+value.roleCode+" "+selected+" class='"+isRoleObjReadOnly+"'>"+value.roleName+"</option>";
					});
				}
				$('#otherRoleCodes').html(html);
				//refresh selectpicker
				$('#otherRoleCodes').selectpicker('refresh');
			}
		}
	}



</script>