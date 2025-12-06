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
	<label class="levelFS required" >Modules Belongs to This Entity :</label>
	<div class="col-md-12">
		<select class="form-control form-control-sm  requiredFieldSelectpicker" name="appEntityApps" multiple data-live-search="true" id="entityAppsId">
			<option value="" aria-selected="false" disabled>Select Modules</option>
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
	// call getEntityLevelCodeList function
	getEntityLevelCodeList($('#objectIdAndTypeId'), selectedEntityIdAndType);

});


	async function getEntityLevelCodeList(that, selectedEntityIdAndType = ""){
		let value = $(that).val();
		debugger;
		if(value != ""){
			let url = "${contextPath}/getPublicAndEntity/specificEntityLevelCodeListList";
			let data = {
				"entityIdAndType" : btoa(value),
				"selectedEntityIdAndType" : btoa(selectedEntityIdAndType),
			};
			let method = "GET";
			let response = await asyncAjaxForData(url, data, method);
			if(response.outcome){
				let data = response.data;
				let allApps = data.allApps;
				let selectedApps = data.selectedApps;
				let html = "<option value='' disabled>Select Modules</option>";

				$.each(allApps, function(index, value){
					let selected = "";
					if(selectedApps.find(x => x.code === value.code)){
						selected = "selected";
					}
					html += "<option value='"+value.code+"' "+selected+">"+value.name+"</option>";
				});


				$('#entityAppsId').html(html);
				$('#entityAppsId').selectpicker('refresh');
			}
		}
	}



</script>