$(function() {

	$('.AlphabetsOnly').keypress(function(e) {
		var regex = new RegExp(/^[a-zA-Z\s]+$/);
		var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		if (regex.test(str)) {
			return true;
		} else {
			e.preventDefault();
			return false;
		}
	});

	$('.emailsOnly').keypress(function (e) {
	    var regex = new RegExp("/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/");
	    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	    if (regex.test(str)) {
	        return true;
	    }
	    else {
	        e.preventDefault();
	        return false;
	    }
	});

	$('.NumbersOnly').on(
			'keypress',
			function(event) {
				var regex = new RegExp("^[0-9]+$");
				var key = String.fromCharCode(!event.charCode ? event.which
						: event.charCode);
				if (!regex.test(key)) {
					event.preventDefault();
					return false;
				}
			});

	$('.AlphaNumericOnly').keypress(function(e) {
		var regex = new RegExp(/^[a-zA-Z0-9@#*%_\s]+$/);
		var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		console.log(str);
		if (regex.test(str)) {
			return true;
		} else {
			e.preventDefault();
			return false;
		}
	});

	$('.NumbersOnlyForAmount').keypress(
		function(event) {
			var regex = new RegExp("^[0-9.]+$");
			var key = String.fromCharCode(!event.charCode ? event.which
					: event.charCode);
			if (!regex.test(key)) {
				event.preventDefault();
				return false;
			}
});
});

function validateEmail(emailField) {
	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
//	var reg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

	if (reg.test(emailField.value) == false) {
		bootbox.alert("Please Provide a Vaild Email Id");
		var result = emailField.value.replace(
				/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g, '');
		emailField.value = result;
		emailField.value = '';
		emailField.focus();
		return false;
	}
	return true;
}

function validateMobileNo(element) {
	/*alert("mobile vaildate function id---------->"+element);*/
	var re = /^[0-9]+$/;

	var str = element.toString();

	var str1 = element.value;

	element = (element) ? element : window.event;
	var charCode = (element.which) ? element.which : element.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}

	if (isNaN(str1) || str1.indexOf(" ") != -1) {
		bootbox.alert("Please Provide a Vaild Mobile Number");
		var result = element.value.replace(
				/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g, '');
		element.value = result;
		element.value='';
		this.value='';
		//document.getElementById('txtMobile').focus();
		return false;
	}

	if (str1.length > 10 || str1.length < 10) {
		bootbox.alert("Please Provide a Vaild Mobile Number");
		var result = element.value.replace(
				/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g, '');
		//element.value = result;
		element.value='';
		this.value='';
		//document.getElementById('txtMobile').value = '';
		return false;
	}

	/*$("input[type='text']").on('keypress', function (event) {
	    var regex = new RegExp("^[a-zA-Z0-9_ ]+$");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	    }
	});
	
	$('textarea').on('keypress', function (event) {
	    var regex = new RegExp("^[a-zA-Z0-9. ]+$");
	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
	    if (!regex.test(key)) {
	       event.preventDefault();
	       return false;
	    }
	});*/
}

function imageCheck(that){
	var ValidFileExtension = [ 'jpg','jpeg','pdf'];
	if ($.inArray($("#"+that.id).val().split('.').pop().toLowerCase(), ValidFileExtension) == -1) {
		$("#"+that.id).val("");
		bootbox.alert("Sorry! allowed format is jpg or jpeg or pdf only.");
		event.preventDefault();
		return false;
	}
	if ((that.files[0].size) > 2097152) {
		$(that).val("");
		bootbox.alert("File size exceeds maximum image size of 1 MB!");
		return false;
	}
	
}

// Only image format allowed
function onlyImage(that) {
	var ValidFileExtension = [ 'jpg','jpeg','png','svg'];
	if ($.inArray($("#"+that.id).val().split('.').pop().toLowerCase(), ValidFileExtension) == -1) {
		$("#"+that.id).val("");
		bootbox.alert("Sorry! allowed format is jpg or jpeg or png or svg only.");
		event.preventDefault();
		return false;
	}
	if ((that.files[0].size) > 2097152) {
		$(that).val("");
		bootbox.alert("File size exceeds maximum image size of 1 MB!");
		return false;
	}
}


function currencyConverter(that,fieldId){
	if(that!=""){
		var value = that.replaceAll(",","");
		if(value.includes(".")){
			var splitValues = value.split(".");
			if(splitValues.length>=2){
				if(splitValues[0]==""){
					splitValues[0]="0";
				}
				value=splitValues[0]+"."+splitValues[1];
				if(splitValues[1].length==1){
					value=splitValues[0]+"."+splitValues[1]+"0";		
				}
				if(splitValues[1].length>2 || splitValues[1]==""){
					value=splitValues[0]+".00";		
				}
			}else{
				value=splitValues[0];
			}
		}else{
			value=value+".00";
		}
		var hiddenFieldId = fieldId.split("Conv")[0];
		$("#"+hiddenFieldId).val(value);
		value = value.replace(/(\d)(?=(\d{2})+\d\.)/g, '$1,');
		$("#"+fieldId).val(value);
	}
}

$('.AlphaNumericForCode').keypress(function(e) {
	var regex = new RegExp(/^[a-zA-Z0-9]+$/);
	var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	if (regex.test(str)) {
		return true;
	} else {
		e.preventDefault();
		return false;
	}
});


function getMonths(monthId,financialYear){
	showAjaxLoader();
	var htm = '<option value="" >--Select--</option>';
	$.ajax({
	   	 	url: window.contextPath+'/pcMpr/getMonthList',
	   	 	type : 'GET',
	   	 	data : ({
	   	 	financialYear : financialYear
			}),
			cache : false,
			asynch : false,
			success : function(response) {
				hideAjaxLoader();
				if(response!=""){
					var obj = jQuery.parseJSON(response);
					$.each(obj, function(key,value) {
						htm = htm+"<option value="+value.monthId+">"+value.monthName+"</option>";
					});
				}else{
					bootbox.alert("Error while getting month by financial year.");
					return false;
				}
				$("#month").empty().append(htm);
				if(monthId!=0){
					$("#month").val(monthId);
				}
			}
	       ,
			error : function(error) {
				hideAjaxLoader();
				bootbox.alert("Something went wrong while month by financial year.");
				return false;
			}
	    
	      });
	      


	      function reportWiseDetails(url, type, data) {
              return new Promise((resolve, reject) => {
                  $.ajax({
                      url: url,
                      type: type,
                      async: true,
                      data: data,
                      success: function (response) {
                          resolve(response);
                      },
                      error: function (response) {
                          reject(response);
                      }
                  });
              });
          }
}

$('.DoublesOnly').on('input', function(event) {
	var getkey = $(event.target);
	var value = getkey.val();

	var regex = /^\d+(\.\d{0,2})?$/;

	if (!regex.test(value)) {
		getkey.val(
			value
				.replace(/[^0-9.]/g, '')
				.replace(/(\..*)\./g, '$1')
				.replace(/^(\d*\.\d{2}).*/, '$1')
				.replace(/^\./g, '')
		);
	}
});

$('.PercentageOnly').on('input', function(event) {
	var getkey = $(event.target);
	var value = getkey.val();

	// First clean the input of invalid characters
	var cleanedValue = value
		// Remove any non-numeric characters except decimal point
		.replace(/[^0-9.]/g, '')
		// Remove extra decimal points
		.replace(/(\..*)\./g, '$1')
		// Limit to 2 decimal places
		.replace(/^(\d*\.\d{2}).*/, '$1')
		// Remove leading decimal point
		.replace(/^\./g, '');

	// Convert to number for range checking
	var numValue = parseFloat(cleanedValue);

	// If it's over 100, set it to 100
	if (numValue > 100) {
		cleanedValue = '100';
	}

	// If it's not a valid number, default to empty
	if (isNaN(numValue)) {
		cleanedValue = '';
	}

	// Update the input value
	getkey.val(cleanedValue);
});


$('.AmountField')
    .on('keypress', function (event) {
        var regex = new RegExp("^[0-9]*\\.?[0-9]{0,2}$"); // Regex for numbers with up to 2 decimals
        var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
        var currentValue = $(this).val() + key; // Simulate the new value after the keypress

        // Prevent input if it doesn't match the regex
        if (!regex.test(currentValue)) {
            event.preventDefault();
            return false;
        }
    })
    .on('input', function () {
        var value = $(this).val();
        // Replace invalid patterns with a valid value
        $(this).val(value.match(/^[0-9]*\.?[0-9]{0,2}/)?.[0] || '');
    })

    .on('blur', function () {
        var value = $(this).val();
        if (value !== '') {
            // Format the value to 2 decimal places
            $(this).val(parseFloat(value).toFixed(2));
        }
    });





