//Ajusta tamanho dos elementos de acordo com o parametro passado
$('.toggleTrueFalse').each(function(i) {
    var width = $(this).data('toggle-width');
    $(this).width(width);
    $(this).children('label').width(width * 0.47);
});

//
$('.clickToggle').on('click',function () {
  var obj = $(this).prev();
  if ($(obj).is(":checked")) {

    $(obj).attr("value", "false");
  }
  else {
    $(obj).attr("value", "true");
  }
});


// on key press event on class="noSpecialChar"
$(document).on('keypress', '.noSpecialChar', function(event){
	// not allow any special character like !@#$%^&*()_+|}{:"?><,./
	var regex = new RegExp("^[a-zA-Z0-9\b]+$");
	var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	if (!regex.test(key)) {
		event.preventDefault();
		return false;
	}
});

// on change event on class="validEmail"
$(document).on('change', '.validEmail', function(){
	// valid email address
//	var regex = new RegExp("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$");

//	var email = $(this).val();
//	if (!regex.test(email)) {
//		$(this).val('');
//	}
    var email = $(this).val();
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if(email!=''){
        if (!emailRegex.test(email)) {
            $(this).val('');
            bootbox.alert("Please enter a valid email address");
            return false;
        }
    }
});

// pincode validation must be 6 digit number
$(document).on('change', '.validPincode6Digit', function(){
  // valid Pincode
  var regex = new RegExp("^[0-9]{6}$");
  var pincode = $(this).val();
  if (!regex.test(pincode)) {
    $(this).val('');
  }
});


// on change event on class="validGSTIN"
$(document).on('change', '.validGSTIN', function(){
	// valid GSTIN
	var regex = new RegExp("^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$");
	var gstin = $(this).val();
	if (!regex.test(gstin)) {
		$(this).val('');
	}
});

// on change event on class="validPAN"
$(document).on('change', '.validPAN', function(){
    // valid PAN
    var regex = new RegExp("[A-Z]{5}[0-9]{4}[A-Z]{1}");
    var pan = $(this).val();
    if (!regex.test(pan)) {
        $(this).val('');
    }
});

// on change event on class="validAadhar"
$(document).on('change', '.validAadhar', function(){
    // valid Aadhar Number // 12 digit number
    var regex = new RegExp("^[0-9]{12}$");
    var aadhar = $(this).val();
    if (!regex.test(aadhar)) {
        $(this).val('');
    }
});

// on change event on class="validPhoneNo"
$(document).on('change', '.validPhoneNo', function(){
    // valid Phone Number // 6 to 14 digit number
    var regex = new RegExp("^[0-9]{6,14}$");
    var phoneNo = $(this).val();
    if (!regex.test(phoneNo)) {
        $(this).val('');
    }
});

// on key press event on class="maximunLengthIs10"
$(document).on('keypress', '.maximunLengthIs10', function(event){
  // not allow any special character like !@#$%^&*()_+|}{:"?><,./
  var regex = new RegExp("^[a-zA-Z0-9\b]+$");
  var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
  if (!regex.test(key)) {
    event.preventDefault();
    return false;
  }
  var length = $(this).val().length;
  if (length >= 10) {
    event.preventDefault();
    return false;
  }
});

$(document).on('keypress', '.maximunLengthIs10WithDot', function(event){
  // allow alphanumeric characters and dot (.)
  var regex = new RegExp("^[a-zA-Z0-9.]+$");
  var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
  if (!regex.test(key)) {
    event.preventDefault();
    return false;
  }
  var length = $(this).val().length;
  if (length >= 6) {
    event.preventDefault();
    return false;
  }
});





function NumInWords (number) {
  const first = ['','one ','two ','three ','four ', 'five ','six ','seven ','eight ','nine ','ten ','eleven ','twelve ','thirteen ','fourteen ','fifteen ','sixteen ','seventeen ','eighteen ','nineteen '];
  const tens = ['', '', 'twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety'];
  const mad = ['', 'thousand', 'million', 'billion', 'trillion'];
  let word = '';

  for (let i = 0; i < mad.length; i++) {
    let tempNumber = number%(100*Math.pow(1000,i));
    if (Math.floor(tempNumber/Math.pow(1000,i)) !== 0) {
    if (Math.floor(tempNumber/Math.pow(1000,i)) < 20) {
      word = first[Math.floor(tempNumber/Math.pow(1000,i))] + mad[i] + ' ' + word;
    } else {
      word = tens[Math.floor(tempNumber/(10*Math.pow(1000,i)))] + '-' + first[Math.floor(tempNumber/Math.pow(1000,i))%10] + mad[i] + ' ' + word;
    }
    }

    tempNumber = number%(Math.pow(1000,i+1));
    if (Math.floor(tempNumber/(100*Math.pow(1000,i))) !== 0) word = first[Math.floor(tempNumber/(100*Math.pow(1000,i)))] + 'hundred ' + word;
  }
  // make first letter upper case
  word = word.slice(0, -1);
  word = word.charAt(0).toUpperCase() + word.slice(1);

  // if last two letter is '- ' then remove like hunderd eighty- hunderd eighty
  if (word.trim().slice(-2) === '- ') {
    word = word.slice(0, -2);
  }
    return word;
}

function requiredFieldValidation(requiredFields) {
  var isValid = true;
  requiredFields.forEach(function(field){
		if(field.value.trim() === "" || field.value === null || field.value === undefined || field.value === "0"){
			isValid = false;
			let label = field.closest(".form-group").querySelector("label").innerText;
			displayErrorMsgByField(field, label + " is required");
		}else{
			clearErrorByField(field);
		}
	});
  return isValid;
}


function appendHiddenFieldToFormForWorkFlow(formId, stageId, remarks, userIds, bulkStageIds){
	if(formId == null || formId == undefined || formId == ""){
		bootbox.alert("Form id is required");
		return;
	}
	if(stageId != null && stageId != undefined && stageId != "" && parseInt(stageId) > 0){
		let currentStageIdV2Html = "<input type='hidden' name='currentWorkflowStageParamName' value='"+stageId+"'>";
		$("#"+formId).append(currentStageIdV2Html);
	}
	if(remarks != null && remarks != undefined && remarks != "" && remarks.trim() != ""){
		remarks = remarks.trim();
		let currentStageRemarkV2Html = "<input type='hidden' name='currentWorkflowStageParamRemarks' value='"+remarks+"'>";
		$("#"+formId).append(currentStageRemarkV2Html);
	}

	let specificUserIds = "";
	if(userIds.length > 0){
		specificUserIds = userIds.join(",");
		let specificUserIdsHtml = "<input type='hidden' name='specificUserParamRemarks' value='"+specificUserIds+"'>";
		$("#"+formId).append(specificUserIdsHtml);
	}
	let bulkStageIdsStr = "";
	if(bulkStageIds.length > 0){
		bulkStageIdsStr = bulkStageIds.join(",");
		let bulkStageIdsHtml = "<input type='hidden' name='currentWorkflowStageParamBulkName' value='"+bulkStageIdsStr+"'>";
		$("#"+formId).append(bulkStageIdsHtml);
	}
}

function imagePDFCheck(input) {
	    var validFileExtension = ['jpg', 'jpeg', 'pdf'];
	    var fileExtension = $(input).val().split('.').pop().toLowerCase();

	    if ($.inArray(fileExtension, validFileExtension) == -1) {
	        $(input).val("");
	        bootbox.alert("Sorry! Allowed formats are jpg, jpeg, or pdf only.");
	        return false;
	    }
	}