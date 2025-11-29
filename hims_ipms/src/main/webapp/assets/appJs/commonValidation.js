// get field value
function getFieldValue(field){
	let value = "";
	if(field.tagName == "INPUT"){
		value = field.value;
	}else if(field.tagName == "SELECT"){
		value = field.value;
	}else if(field.tagName == "TEXTAREA"){
		value = field.value;
	}
	value = value.trim();
	return value;
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
	var regex = new RegExp("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$");
	var email = $(this).val();
	if (!regex.test(email)) {
		$(this).val('');
		displayErrorMsgByField(this, "Invalid email address");
	}else{
		clearErrorByField(this);
	}
});

// on change event on class="validWebSite"
$(document).on('change', '.validWebSite', function(){
    // valid website address
    var regex = /^(http(s)?:\/\/)?(www\.)?[a-zA-Z0-9-]+(\.[a-zA-Z]{2,})+([/?#].*)?$/;
    var website = $(this).val();
    if (!regex.test(website)) {
        $(this).val('');
        displayErrorMsgByField(this, "Invalid website address");
    } else {
        clearErrorByField(this);
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
  if (length >= 10) {
    event.preventDefault();
    return false;
  }
});


function validateRequiredFields(className) {
	let isValid = true;
	let requiredFields = dynamicFromOfWF.find("." + className);
	requiredFields.each(function(){
		let value = getFieldValue(this);
		if(value == ""){
			isValid = false;
			// if this field has form-control then add is-invalid class to it
			if(this.classList.contains("form-control")){
				this.classList.add("is-invalid");
			}
			displayErrorMsgByField(this, "This field is required");
		}else{
			this.classList.remove("is-invalid");
			// remove the error message
			clearErrorByField(this);
		}
	});
	return isValid;
}

function validationForTable(className) {
	let isValid = true;
	let tableValidation = dynamicFromOfWF.find("." + className);
	tableValidation.each(function(){
		let table = this;
		let tableRows = $(table).find("tbody tr");
		if(tableRows.length == 0){
			isValid = false;
			displayErrorMsgByField(this, "At least one row is required");
		}else{
			clearErrorByField(this);
		}
	});
	return isValid;
}

function validationForGreaterThanZero(className) {
	let isValid = true;
	let greaterThanZero = dynamicFromOfWF.find("." + className);
	greaterThanZero.each(function(){
		let value = getFieldValue(this);
		parseFloat(value);
		if(value <= 0){
			isValid = false;
			displayErrorMsgByFieldOnTitel(this, "This field must be greater than zero");
		}else{
			clearErrorByFieldOnTitle(this);
		}
	});
	return isValid;
}

function validationForAtLeastOneGreaterThanZero(className) {
	let isValid = true;
	let atLeastOneGreaterThanZero = dynamicFromOfWF.find("." + className);
	let isAtLeastOneGreaterThanZero = false;
	if(atLeastOneGreaterThanZero.length == 0){
		isAtLeastOneGreaterThanZero = true;
	}else{
		atLeastOneGreaterThanZero.each(function(){
			let value = getFieldValue(this);
			parseFloat(value);
			if(value > 0){
				isAtLeastOneGreaterThanZero = true;
			}
		});
	}
	if(!isAtLeastOneGreaterThanZero){
		isValid = false;
		atLeastOneGreaterThanZero.each(function(){
			// if this field has form-control then add is-invalid class to it
			if(this.classList.contains("form-control")){
				this.classList.add("is-invalid");
			}
			displayErrorMsgByField(this, "At least one field must be greater than zero");
		});
	}else{
		atLeastOneGreaterThanZero.each(function(){
			this.classList.remove("is-invalid");
			clearErrorByField(this);
		});
	}
	return isValid;
}

function displayErrorMsgById(id, message) {
	clearErrorById(id);
	var element = document.getElementById(id);
	if (element) {
		var errorSpan = document.createElement('span');
		errorSpan.className = 'error-message';
		errorSpan.textContent = message;
		errorSpan.id = id + '-error';
		element.parentNode.insertBefore(errorSpan, element.nextSibling);
	} else {
		console.error('Element with id ' + id + ' not found.');
	}
}

function clearErrorById(id) {
	var existingError = document.getElementById(id + '-error');
	if (existingError) {
		existingError.parentNode.removeChild(existingError);
	}
}

function displayErrorMsgByFieldOnTitle(field, message) {
	clearErrorByFieldOnTitle(field);
	if (field) {
		// add the error message into the title of the field
		field.title = message;
		field.classList.add('is-invalid');
	} else {
		console.error('Element with id ' + id + ' not found.');
	}
}

function clearErrorByFieldOnTitle(field) {
	if (field) {
		// remove the error message from the title of the field
		field.title = "";
		field.classList.remove('is-invalid');
	} else {
		console.error('Element with id ' + id + ' not found.');
	}
}

function displayErrorMsgByField(field, message) {
	clearErrorByField(field);
	if (field) {
		var errorSpan = document.createElement('span');
		errorSpan.className = 'error-message';
		errorSpan.textContent = message;
		field.parentNode.insertBefore(errorSpan, field.nextSibling);
	} else {
		console.error('Element with id ' + id + ' not found.');
	}
}

function clearErrorByField(field) {
	var existingError = field.nextElementSibling;
	if (existingError) {
		existingError.parentNode.removeChild(existingError);
	}
}

