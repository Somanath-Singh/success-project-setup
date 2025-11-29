/*const context = window.contextPath;*/


function getBankBranchByBankId(val,idToAppend,dependentId){
	var html="<option value='0'>--Select--</option>";
    var bank=val;
    if(bank != "0"){
		showLoader();
		$.ajax({
	        url : context+'/ajax/getBranchBybankId',
	        type : 'GET',
	        data : ({
	            bankId : bank
	        }),
	        cache : true,
	        asynch : false,
	        success : function(response) {
				hideLoader();
	            var obj = response.data;
	                 $.each(obj, function(key,value) {
	                	 html += "<option value='"+value.bankBranchId+"'>"+value.branchName+"</option>";
	                }); 
	                $("#"+idToAppend).empty().append(html);
	            },
	        error : function(error) {
				hideLoader();
	            bootbox.alert(error);
	        }
	    });
	}else{
		$("#"+idToAppend).empty().append(html);
		$("#"+dependentId).empty().append(html);
	}
}

function getIFSCByBranchId(val,idToSet) {	
    var branchId=val;
    if(branchId != "0"){
		showLoader();
		$.ajax({
	        url : context+'/ajax/getIfscByBranchId',
	        type : 'GET',
	        data : ({
	            branchId : branchId
	        }),
	        cache : false,
	        asynch : false,
	        success : function(response) {
	           hideLoader();
	            var obj = response.data;
	            $("#"+idToSet).val(obj);
	        },
	        error : function(error) {
				hideLoader();
	            bootbox.alert(error);
	        }
	    });
	}else{
		$("#"+idToSet).val("");
	}

}



function getDemographicDataById(demographyCall,id,idToAppend){
	var data = $("#"+id).val();
	 var html = '<option value="0">-Select-</option>';
	if(data != "0"){
		showLoader();
		  $.ajax({
			    type: 'GET',
			    url: context+'/ajax/findDemographyById',
			    data: {
			      "demoId": data,
			      "dmographyCall": demographyCall,
			    },
			    success: function(response) {
					hideLoader();
			      var data = response;
			      $.each(data, function(index, value) {
			        html += '<option value="' + value.demographyId + '">' + value.demographyName + '</option>';
			      });
			      $("#" + idToAppend).empty().html(html);
			    },
			    error: function() {
					hideLoader();
			      alert("Server Error");
			    }
			});
		
	}else{
		$("#"+idToAppend).empty().html(html);
		if(demographyCall == "STATE"){
			$("#distId").empty().html(html);
			$("#blockId").empty().html(html);
			$("#gpId").empty().html(html);
			$("#villageId").empty().html(html);
		}
		else if(demographyCall == "DISTRICT"){
			$("#blockId").empty().html(html);
			$("#gpId").empty().html(html);
			$("#villageId").empty().html(html);
		}
		else if(demographyCall == "BLOCK"){
			$("#gpId").empty().html(html);
			$("#villageId").empty().html(html);
		}
		else if(demographyCall == "GP"){
			$("#villageId").empty().html(html);
		}
		
	}
}



function getAgencydataByAgencyCall(agencyCall,id,idToAppend,dependentId){
	var data = $("#"+id).val();
	 var html = '<option value="0">-All-</option>';
	if(data != "0"){
		showLoader();
		  $.ajax({
			    type: 'GET',
			    url: context+'/ajax/getAgencyDataByParentAgencyId',
				async: false,
			    data: {
			      "agencyId": data,
			      "agencycall": agencyCall,
			    },
			    success: function(response) {
					hideLoader();
			      var data = response.data;
			      $.each(data, function(index, value) {
			        html += '<option value="' + value.agencyId + '">' + value.agencyName + '</option>';
			      });
			      $("#" + idToAppend).empty().html(html);
			    },
			    error: function() {
					hideLoader();
			      alert("Server Error");
			    }
			});
		
	}else{
		$("#"+id).val("0");
		$("#"+idToAppend).empty().html(html);
		$("#"+dependentId).empty().html(html);
	}
	
}



function getDuplicateCheckData(id,dependentId,formCode,validCall,checkCall){
	var data = $("#"+id).val().trim();
	var dependent = $("#"+dependentId).val();
	if(data != ""){
		showLoader();
	 	$.ajax({
			url: context+'/ajax/validateDuplicateData',
			data: {
				"validData": data,
				"checkCall": checkCall,
				"validCode" : $("#"+formCode).val(),
				"validCall": validCall,
				"dependentId": dependent,
			},
			success: function(response) {
				hideLoader();
				var responseData = response;
				if (responseData.outcome) {
					var msg = validCall === 'ACTIVITY' ? 'Given activity name already exists against the sector' : 'Given sub-activity name already exists against the activity';
					if(responseData.data == "YES"){
						bootbox.alert(msg);
						$("#"+id).val("");
						return false;
					}
					else{
						return true;
					}
				} else {
					bootbox.alert("Something went wrong");
					$("#"+id).val("");
					return false;
				}
			},
		});
	}

}
/**
 * @author Tapan
 * @since 24/01/2019
 * @description Get current-date as dd/mm/yyyy
*/
function getCurrentDateAsDDMMYYYY()
{
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1;
	var yyyy = today.getFullYear();

	if(dd<10)
	{
		dd='0'+dd;
	} 
	if(mm<10)
	{
		mm='0'+mm;
	}
	var currDate = dd+'/'+mm+'/'+yyyy;
	return currDate;
}

/** 
 * @author Akash
 * @since  30/10/2023
 * @description Checks if the 1st date is bigger/smaller than the 2nd date.
 */
function isFirstDateBigger(biggerDate,smallerDate)
{
debugger;
	var firstDateForBiggerValue = moment(biggerDate, 'DD/MM/YYYY');
	var secondDateForSmallerValue = moment(smallerDate, 'DD/MM/YYYY');
	if(firstDateForBiggerValue > secondDateForSmallerValue)
		return true;
	else
		return false;
}


   





