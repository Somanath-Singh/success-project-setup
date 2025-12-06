
// aadhar,account,gstin  validation  @Author Dipty ranjan Das



function validateAllDetails1(data, type) {
    var accountNumberValue, aadharId, gstNo, panNo;
    
    if (type === 'BANKACNO') {
        accountNumberValue = data;
        if (accountNumberValue.length < 6 || accountNumberValue.length > 20) {
            bootbox.alert('Account number must be between 6 and 20 characters.');
            $("#bankAcNoReq").val("");
            return false;
        }
    } else if (type === 'AADHARNO') {
        aadharId = data;
        if (!MinimumLengthValidation(aadharId, 12, 'digit valid aadhar number')) {
            bootbox.alert("Please enter a valid aadhar number.");
            $("#aadharNoReq").val("");
            return false;
        } else if (!ValidAadhaarNo(aadharId)) {
            bootbox.alert("Please enter a valid aadhar number.");
            $("#aadharNoReq").val("");
            return false;
        }
    } else if (type === 'GSTNO') {
        gstNo = data;
        var gstPattern = /^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/;
        if (!gstPattern.test(gstNo)) {
            bootbox.alert("Please enter a valid GST number.");
            $("#gstNoReq").val("");
            return false;
        }else{
        $.ajax({
        url: `../mst/duplicateCheck.htm`, // Use template literals for URL
        type: 'GET', // Use 'GET' or 'POST' based on server setup
        data: {
            value: gstNo,
            type: type
        },
        success: function(response) {
            // Handle the response from the server
          var data = JSON.parse(response);
            if (data.status === "exist") {
                // Value exists
                 $("#gstNoReq").val("");
                bootbox.alert("Pan or GSTIn number already exists");
                return false;
               
            } else {
                // Value does not exist
                // Add any necessary handling here
            }
        },
        error: function(xhr, status, error) {
            // Handle any errors that occurred during the AJAX request
           // console.error('An error occurred:', status, error);
        }
    });
        }
    } else if (type === 'PANNO') {
        panNo = data;
        var regpan = /^([a-zA-Z]{5})([0-9]{4})([a-zA-Z]{1})$/;
        if (!regpan.test(panNo)) {
            bootbox.alert("PAN Number is not valid. It should be in the format: 'ABCDE1234F'.");
            $("#panNoReq").val("");
            return false;
        }else{
         $.ajax({
        url: `../mst/duplicateCheck.htm`, // Use template literals for URL
        type: 'GET', // Use 'GET' or 'POST' based on server setup
        data: {
            value: panNo,
            type: type
        },
        success: function(response) {
            // Handle the response from the server
          var data = JSON.parse(response);
            if (data.status === "exist") {
                // Value exists
                $("#panNoReq").val("");
                bootbox.alert("Pan or GSTIn number already exists");
                return false;
                
            } else {
                // Value does not exist
                // Add any necessary handling here
            }
        },
        error: function(xhr, status, error) {
            // Handle any errors that occurred during the AJAX request
           // console.error('An error occurred:', status, error);
        }
    });
        }
        
    } else {
        return true;
    }
    //return true;
}



function MinimumLengthValidation(textboxId, length, msg) {
    try {
        //var textbox = $('#' + textboxId);
        var txtLength = textboxId.length;
        if (txtLength < length) {
            alert("Please enter " + length + " " + msg);
            return false;
        } else {
            return true;
        }
    } catch (e) {
        // console.error(e);
        return false;
    }
}

// multiplication table d
var d = [
    [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
    [1, 2, 3, 4, 0, 6, 7, 8, 9, 5],
    [2, 3, 4, 0, 1, 7, 8, 9, 5, 6],
    [3, 4, 0, 1, 2, 8, 9, 5, 6, 7],
    [4, 0, 1, 2, 3, 9, 5, 6, 7, 8],
    [5, 9, 8, 7, 6, 0, 4, 3, 2, 1],
    [6, 5, 9, 8, 7, 1, 0, 4, 3, 2],
    [7, 6, 5, 9, 8, 2, 1, 0, 4, 3],
    [8, 7, 6, 5, 9, 3, 2, 1, 0, 4],
    [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
];

// permutation table p
var p = [
    [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
    [1, 5, 7, 6, 2, 8, 3, 0, 9, 4],
    [5, 8, 0, 3, 7, 9, 6, 1, 4, 2],
    [8, 9, 1, 6, 0, 4, 3, 5, 2, 7],
    [9, 4, 5, 3, 1, 2, 6, 8, 7, 0],
    [4, 2, 8, 6, 5, 7, 3, 9, 0, 1],
    [2, 7, 9, 3, 8, 0, 6, 4, 1, 5],
    [7, 0, 4, 6, 9, 1, 3, 2, 5, 8]
];

// inverse table inv
var inv = [0, 4, 3, 2, 1, 5, 6, 7, 8, 9];

// converts string or number to an array and inverts it
function generateVerhoeff(array) {

    if (Object.prototype.toString.call(array) == "[object Number]") {
        array = String(array);
    }

    if (Object.prototype.toString.call(array) == "[object String]") {
        array = array.split("").map(Number);
    }

    return array.reverse();

}

// generates checksum
function generate(array) {

    var c = 0;
    var invertedArray = generateVerhoeff(array);

    for (var i = 0; i < invertedArray.length; i++) {
        c = d[c][p[((i + 1) % 8)][invertedArray[i]]];
    }

    return inv[c];
}

// validates checksum
function ValidAadhaarNo(array) {
    var c = 0;
    var invertedArray = generateVerhoeff(array);

    for (var i = 0; i < invertedArray.length; i++) {
        c = d[c][p[(i % 8)][invertedArray[i]]];
    }
    if (c != 0) 
      return false;
    else
     return true;
    
}


function validateNameAndCode(element)
{
debugger
	var result = element.value.replace(/[\u005F\u003D\u200B-\u200D\u201A-\u201E\u2013-\u2122\uFEFF\!@#$^&*+<>\\\/|\?~{}())(\:%]/g,'');
	element.value =result;
}



function searchPartiesBasedOnInputParams()
	{
		//showAjaxLoader();
		var srchByPartyCode = $('#srchByPartyCode').val();
		var srchByPartyName = $('#srchByPartyName').val();
		var srchByPartyGstNo = $('#srchByGstNo').val();
		var srchByPartyMobNo = $('#srchByMobNo').val();

		$.ajax({
			url : '../mst/searchPartiesBasedOnInputParams',
			type : 'GET',
			data : ({
				srchByPartyCode : srchByPartyCode,
				srchByPartyName : srchByPartyName,
				srchByPartyGstNo : srchByPartyGstNo,
				srchByPartyMobNo : srchByPartyMobNo
			}),
			cache : false,
			asynch : false,
			success : function(response) 
			{
				if (response != "") 
				{
					//hideAjaxLoader();
					
					document.getElementById("partyListTable").style = "display: inline-table;";
					count = 0;
					var obj = jQuery.parseJSON(response);
					
					if (obj != "")
					{
						$("#partyListId").empty();
						$("#hideAndShow").hide();
						$("#partyListId").show();
						$("#showMessage").show();
						$.each(obj, function(i, value) 
						{
							count++;
							
							var partyListRecords = "<tr id='row"+count+"'>";
							partyListRecords = partyListRecords + "<td>"+count+"</td>";
							partyListRecords = partyListRecords + "<td>"+value.partyName+"</td>";
							partyListRecords = partyListRecords + "<td>"+value.partyCode+"</td>";
							partyListRecords = partyListRecords + "<td>"+value.mobileNo+"</td>";
							partyListRecords = partyListRecords + "<td>"+value.gstNo+"</td>";
							partyListRecords = partyListRecords + "<td>"+value.emailID+"</td>";
							
							var editCell = '<a class="btn btn-xs btn-circle btn-warning" title="Edit Record" onclick="editPartyDetails(' +value.partyId+ ')">'
												+ '<i class="fa fa-pencil" aria-hidden="true"></i>'
												+ '</a>';
							partyListRecords = partyListRecords + "<td align='center'>"+editCell+"</td></tr>";
							
					 	    $("#partyListId").append(partyListRecords);
							document.getElementById("TotalRecCountDiv").innerHTML = count;
						});
					}
					else
					{
						document.getElementById("partyListTable").style = "display: none;";
						document.getElementById("TotalRecCountDiv").innerHTML = "0";
					}
				} 
				else 
				{
					bootbox.alert("No Records found.");
					document.getElementById("partyListTable").style = "display: none;";
					document.getElementById("TotalRecCountDiv").innerHTML = "0";
				}
			},
			error : function(transport) {
				bootbox.alert("No Records found.");
				document.getElementById("partyListTable").style = "display: none;";
				document.getElementById("TotalRecCountDiv").innerHTML = "0";
			}
		});
	}
   
 
   	
   
   
   

