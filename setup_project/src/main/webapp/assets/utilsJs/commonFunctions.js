function customError(field, message){
	// if already have error message remove it
	if(field.nextElementSibling != null){
		removeError(field);
	}

	field.classList.add("is-invalid");
	var error = document.createElement("div");
	error.classList.add("invalid-feedback");
	error.innerText = message;
	field.insertAdjacentElement("afterend", error);
}
function removeError(field){
	field.classList.remove("is-invalid");
	var error = field.nextElementSibling;
	if(error != null && error.classList.contains("invalid-feedback")){
		error.remove();
	}
}


function errorDisplay(errorMsg, uiId) {
	// create error div and append to given uiId parent
	let errorDiv = '<div class="alert alert-danger alert-dismissible fade show" role="alert" style="z-index: 999999999;">';
	errorDiv += '<strong>Error!</strong> ' + errorMsg;
	errorDiv += '</div>';
	$(uiId).parent().append(errorDiv);
	// add css position relative to parent
	$(uiId).parent().css('position', 'relative');
	// add css position absolute to error div
	$(uiId).parent().find('div[class="alert alert-danger alert-dismissible fade show"]').css('position', 'absolute');
	removeErrorDiv();
}

function removeErrorDiv() {
	setTimeout(function() {
		// remove position absolute from error div by class name and remove error div
		$('div[class="alert alert-danger alert-dismissible fade show"]').parent().css('position', '');
		$('.alert').remove();
	}, 3000);
	
}



function showAlertMessage(message, type) {
	// type = success, info, warning, danger use bootbox alert
	bootbox.alert({
		size : "small",
		title : "Alert",
		message : message,
		className : type,
		callback : function() {
			// console.log("Alert Callback");
		}
	});

}



// common field validation functions
$('.NumbersOnly').on('keyup',function(event) {
		var regex = new RegExp("^[0-9]+$");
		var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
		if (!regex.test(key)) {
			var getkey;
			getkey = $(event.target);
			getkey.val(getkey.val().replace(/[^0-9.]+/g, ""));
			//event.preventDefault();
			return false;
		}
});

$('.NumbersOnlyWithoutDot').keydown(function(e) {
	var regex = new RegExp(/^[0-9]+|[\b]+$/); // space given because of issue
	var str = e.key;
	if (regex.test(str) || str=='Backspace' || str=='Tab') {
		return true;
	} else {
		e.preventDefault();
		return false;
	}
});

$('.AlphaNumericOnly').keydown(function(e) {
	var regex = new RegExp(/^[a-zA-Z0-9._\b\s]+$/); // space given because of issue
	var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	console.log(str);
	if (regex.test(str)) {
		return true;
	} else {
		e.preventDefault();
		return false;
	}
});


function getSubCategoryDetailsByCategoryiD(categoryId) {
	var html='<option value="0">-Select-</option>';
	$.ajax({
		url : window.ctxPath + '/ajax/getSubCategoryByCategory?categoryId='+categoryId,
		success : function(response) {
			var data = JSON.parse(response);
			$.each(data, function(i, item){
				html += '<option value="'+item.id+'">'+item.name+'</option>';
			});
			$("#subcategory").empty().append(html);
		}
	});	
}



function getSubCategoryDtlByCategoryid(categoryId, selectedItems) {
    var html = '<option value="0">-Select-</option>';
    $.ajax({
        url: window.ctxPath + '/ajax/getSubCategoryByCategory?categoryId=' + categoryId,
        success: function (response) {
            var data = JSON.parse(response);
            $.each(data, function (i, item) {
                // Use '==' for loose comparison in JavaScript
                html += '<option value="' + item.id + '" ' + (selectedItems == item.id ? 'selected' : '') + '>' + item.name + '</option>';
            });
            $("#subcategory").empty().append(html);
        }
    });
}



// on key press event on class="nonFractionalNumber"
$(document).on('keypress', '.nonFractionalNumber', function(event){
	// if key is not 0-9 then prevent default
	if(event.which != 8 && isNaN(String.fromCharCode(event.which))){
		event.preventDefault();
	}
});

$(document).on("keypress", ".numberWithTwoDesimal", function(){
	// if key is not 0-9 then prevent default except dot
	if(event.which != 8 && isNaN(String.fromCharCode(event.which)) && event.which != 46){
		event.preventDefault();
	}

	// if decimal is already there then prevent default like 1.1.1, 1.1., 1.. etc but allow 1.1, .1, 1.
	if($(this).val().indexOf('.') != -1 && event.which == 46){
		event.preventDefault();
	}
	// after decimal 2 digit only
	if($(this).val().indexOf('.') != -1 && $(this).val().split(".")[1].length >= 2){
		event.preventDefault();
	}
	 
});

$(document).on("keyup", ".numberWithTwoDesimalButOnlyAllowZero", function(){
    let value = $(this).val();
    let dotIndex = value.indexOf('.');
    if (dotIndex !== -1) {
        // If dot is present, check digits before and after dot
        let digitsBeforeDot = value.substring(0, dotIndex);
        let digitsAfterDot = value.substring(dotIndex + 1);
        // Only allow digits before dot
        digitsBeforeDot = digitsBeforeDot.replace(/[^\d]/g, '');
        // Only allow one digit after dot and ensure it's 0
        digitsAfterDot = digitsAfterDot.replace(/[^\d]/g, '');
        if (digitsAfterDot.length > 1 || (digitsAfterDot.length === 1 && digitsAfterDot !== '0')) {
            digitsAfterDot = '0';
        }
        value = digitsBeforeDot + (digitsAfterDot.length > 0 ? '.' + digitsAfterDot : '');
        $(this).val(value);
    } else {
        // If no dot is present, only allow digits
        $(this).val(value.replace(/[^\d]/g, ''));
    }
});


// discount percentage and tax percentage allow only 0-100
$(document).on('keyup', '.percentageOnly', function(event){
	// if key is not 0-9 then prevent default
	if(event.which != 8 && isNaN(String.fromCharCode(event.which))){
		event.preventDefault();
	}
	// if value is greater than 100 then prevent default
	let value = $(this).val();
	value = parseFloat(value);
	if(value > 100){
		$(this).val('100');
	}
});

// onBlur event on class="onClickInput"
$(document).on('click', '.onClickInput', function(){
	// if value is 0 or 0.00 then make it blank
	if($(this).val() == '0' || $(this).val() == '0.00' || $(this).val() == '0.0'){
		$(this).val('');
	}
});

$(document).on('blur', '.onClickInput', function(){
	// if value is blank then make it 0
	if($(this).val() == ''){
		$(this).val('0');
	}
});

// only allow number 
$(document).on('keypress', '.onlyNumber', function(event){
	// if key is not 0-9 then prevent default
	if(event.which != 8 && isNaN(String.fromCharCode(event.which))){
		event.preventDefault();
	}
});



function displayErrorMsg(id, message) {
	// Clear any existing error messages
	clearError(id);

	// Get the element by id
	var element = document.getElementById(id);

	// Check if the element exists
	if (element) {
		// Create a new span element for the error message
		var errorSpan = document.createElement('span');
		errorSpan.className = 'error-message';
		errorSpan.textContent = message;
		errorSpan.id = id + '-error';

		// Append the error message span after the element
		element.parentNode.insertBefore(errorSpan, element.nextSibling);
	} else {
		console.error('Element with id ' + id + ' not found.');
	}
}



function clearError(id) {
	// Remove any existing error messages
	var existingError = document.getElementById(id + '-error');
	if (existingError) {
		existingError.parentNode.removeChild(existingError);
	}
}



function displayErrorMsgByField(field, message) {
	// Clear any existing error messages
	clearErrorByField(field);

	// we have field now use it 
	// Check if the element exists
	if (field) {
		// Create a new span element for the error message
		var errorSpan = document.createElement('span');
		errorSpan.className = 'error-message';
		errorSpan.textContent = message;

		// if field is input type then use parent node
		// if(field.nodeName == 'INPUT'){
		// 	field = field.parentNode;
		// }

		// Append the error message span after the element
		field.parentNode.insertBefore(errorSpan, field.nextSibling);
	} else {
		console.error('Element with id ' + id + ' not found.');
	}
}

function clearErrorByField(field) {
	// Remove any existing error messages
	var existingError = field.nextElementSibling;
	if (existingError) {
		existingError.parentNode.removeChild(existingError);
	}
}



