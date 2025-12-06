<style>
	.isEntityObjReadOnly {
		pointer-events: none;
		background-color: #f9f9f9;
	}
</style>

<div class="form-group col-md-3">
	<label>Organization Name :</label>
	<div class="col-md-12">
		<!-- if officeDetails.objectIdAndType is there then readonly -->
		<select class="form-control form-select " onchange="getDownEntityHierarchyList(this)" id="parentEntityIdAndTypeId" >
			<option value="">Select Organization</option>
			<c:forEach items="${entityIdAndUserLevelList}" var="organization">
				<option value="${organization.combineTwo}" <c:if test="${organization.combineTwo eq currentCombineIdAndValue}">selected</c:if>>${organization.organizationName}</option>
			</c:forEach>
		</select>
	</div>
</div>

<div class="form-group col-md-3">
	<label>Entity Name :</label>
	<div class="col-md-12">
		<!-- if officeDetails.objectIdAndType is there then readonly -->
		<select class="form-control form-select " onchange="filterData(this)" id="objectEntityIdAndTypeId" >
			<option value="" disabled>Select Entity</option>
		</select>
	</div>
</div>

<form class="form-horizontal" action="" method="GET" id="dynamicEntityListForm">
	<input type="hidden" name="filterData" value="" id="filterDataEncodeId"/>
	<input type="hidden" name="parentEntityData" value="" id="parentEntityDataEncodeId"/>
</form>

<script>

	
	function asyncAjaxForData(url, data, method) {
		return new Promise((resolve, reject) => {
			$.ajax({
				url: url,
				type: method,
				data: data,
				success: function(data){
					resolve(data);
				},
				error: function(err){
					reject(err);
				}
			});
		});
	}

	// document ready
	$(document).ready(function(){
		debugger;
		let currentClassObjectId = "${selectedFilterEntityDataCombo}";
		getDownEntityHierarchyList($("#parentEntityIdAndTypeId"), currentClassObjectId);
	});
	let isMultipleChoose = "${isMultipleChoose}";
	
	var classObjectValue2 = "${classObjectValue}";

	async function getDownEntityHierarchyList(that, currentClassObjectId = ""){
		let value = $(that).val();
		if(value != ""){
			let url = "${contextPath}/system/getChildListEntityHierarchyList";
			let data = {
				"entityId" : btoa(value),
				"classObjectValue" : btoa(classObjectValue2),
				"currentClassObjectId" : btoa(currentClassObjectId)
			};
			let method = "GET";
			let response = await asyncAjaxForData(url, data, method);
			if(response.outcome){
				let data = response.data;
				let html = "<option value=''>Select Entity</option>";
				$.each(data, function(index, value){
					let selected = "";
					if(value.levelIdAndType == currentClassObjectId){
						selected = "selected";
						onChangeTrigger = true;
					}
					html += '<option value="' + value.levelIdAndType+ '" '+selected+'>' + value.levelName + '</option>';
				});

				if(isMultipleChoose == "true"){
					// add class to parentEntityIdAndTypeId as selectpicker and multiple attribute
					$('#objectEntityIdAndTypeId').attr('multiple', 'multiple');
				}


				$('#objectEntityIdAndTypeId').html(html);
				
				
				
			}
		}
	}


	function filterData(that){
			var filter = that.value;
			if (filter == 0) {
				bootbox.alert("Please select organization");
				return;
			}
			let filterEncodeData = btoa(filter);
			let parentEntityIdAndTypeIdData = btoa($('#parentEntityIdAndTypeId').val());
			// get the current url from the browser
			let currentUrl = window.location.href;
			// split the url by '?' to get the base url
			let baseUrl = currentUrl.split('?')[0];
			// set the filterData value to the hidden input field
			$('#filterDataEncodeId').val(filterEncodeData);
			$('#parentEntityDataEncodeId').val(parentEntityIdAndTypeIdData);
			// submit the form
			$('#dynamicEntityListForm').attr('action', baseUrl).submit();
		}
</script>