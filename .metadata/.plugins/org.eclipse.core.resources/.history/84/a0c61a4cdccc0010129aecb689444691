var ALLOWED_FILE_TYPES = ['pdf', 'jpg', 'jpeg', 'png', 'xlsx', 'xls'];
var MAX_FILE_SIZE= 1048576;//1MB  //Change the global var accoudingly;
//'doc', 'docx'
//<input type="hidden" value="true" id="labelmsg" name="labelmsg"> add this for featch the lable name for alert msg
//<input type="hidden"  id="employeeIdMSG" name="employeeIdMSG" value="emply should not b empty "> add this for costum msg
//other wise defult msg "Required fields should not be empty."
//**************FORM VALIDATION FOR EMPTY FIELD AND DOC TYPE START****************
	   /*
        */
    
	    function validateFormData() {
	    	

	        var requiredElements = document.getElementsByClassName('require');
	        debugger
	         var validElements = Array.from(requiredElements).filter(function (element) {
        return !element.classList.contains('notRequired');
                 });

	        var fieldIds = Array.from(validElements).map(function(element) {
	            return element.id;
	        });

	        for (var i = 0; i < fieldIds.length; i++) {
	            var fieldId = fieldIds[i];
	               if (!fieldId) {
                      continue;
                  }
	            if (!validateField(fieldId)) {
	                return false;
	            }
	        }

//	        clearErrorMessage();

	        return true;
	    }



	    function validateForm() {
        	         var requiredElements = document.getElementsByClassName('require');
        	        debugger
        	         var validElements = Array.from(requiredElements).filter(function (element) {
                return !element.classList.contains('notRequired');
                         });
        	        var fieldIds = Array.from(validElements).map(function(element) {
        	            return element.id;
        	        });
        	        for (var i = 0; i < fieldIds.length; i++) {
        	            var fieldId = fieldIds[i];
        	               if (!fieldId) {
                              continue;
                          }
        	            if (!validateField(fieldId)) {
        	                return false;
        	            }
        	        }
        //	        clearErrorMessage();
        	        return true;
        	    }
    
    function validateFormByFormID(formId) {
				    
				
				    var form = document.getElementById(formId);
				
				    if (!form) {
				        console.error("Form with ID " + formId + " not found.");
				        bootbox.alert("Form with ID " + formId + " not found to validate");
				        return false; 
				    }
				
				    var requiredElements = form.querySelectorAll('.require');
				
				    var fieldIds = Array.from(requiredElements).map(function(element) {
				        return element.id;
				    });
				
				    for (var i = 0; i < fieldIds.length; i++) {
				        var fieldId = fieldIds[i];
				              if (!fieldId) {
                                continue;
                               }
				        if (!validateField(fieldId)) {
				            return false; 
				        }
				    }
				
				    // clearErrorMessage(); 
				    return true;
		}
	    

	    function validateField(fieldId) {
	        

	        var field = document.getElementById(fieldId);
	       /* var fieldMsgElement = document.getElementById(fieldId + 'MSG');

	        var fieldMsg = (fieldMsgElement && fieldMsgElement.value) ? fieldMsgElement.value.trim() : '';
	        
	        var defaultMsg = 'Required fields should not be empty.';
	        var alertMsg = fieldMsg || defaultMsg;*/
	        
	        var alertMsg = generateAlertMessage(field);

	        if (field.type === 'text' || field.type === 'hidden') {
	            return validateTextField(field, alertMsg);
	        } else if (field.type === 'select-one' || field.type === 'select-multiple') {
	            return validateDropdown(field, alertMsg);
	        } else if (field.type === 'checkbox') {
	            return validateCheckbox(field, alertMsg);
	        } else if (field.type === 'file') {
	            return validateFileInput(field, alertMsg, ALLOWED_FILE_TYPES,MAX_FILE_SIZE);
	        }else if (field.type === 'number') {
                return validateNumberField(field, alertMsg);
            } else if (field.type === 'radio') {
                return validateRadioButton(field, alertMsg);
            }else if (field.type === 'date') {
                return validateDateField(field, alertMsg);
            }
	        

	        return true;
	    }


	    function validateTextField(field,alertmsg) {
	    	
	        var fieldValue = field.value.trim();
	        if (fieldValue === '') {
	            applyErrorStyling(field);
	            displayErrorMessage(alertmsg);
	            return false;
	        }
	        removeErrorStyling(field);
	        return true;
	    }

	    function validateDropdown(field,alertmsg) {
	    
		if (field.multiple) {
		        // For multi-select, check if any option is selected
		        var selectedOptions = Array.from(field.options).filter(option => option.selected);
		        if (selectedOptions.length === 0) {
		            applyErrorStyling(field);
		            displayErrorMessage(alertmsg);
		            return false;
		        }
		    } else {
	
		        if (field.value === '') {
		            applyErrorStyling(field);
		            displayErrorMessage(alertmsg);
		            return false;
		        }
		    }

	        removeErrorStyling(field)
	        return true;
	    }
		
		 function validateCheckbox(field,alertmsg) {
			   	 
			    	removeErrorStyling(field);
			        return true;
			    }
			    
	    function validateNumberField(field, alertmsg) {
            var fieldValue = field.value.trim();

            if (fieldValue === '') { // || !/^\d+$/.test(fieldValue)
            applyErrorStyling(field);
            displayErrorMessage(alertmsg);
             return false;
            }

             removeErrorStyling(field);
              return true;
        }
		
		function validateRadioButton(field, alertmsg) {
	        
	        var radioButtons = document.querySelectorAll('input[type="radio"][name="' + field.name + '"]');
	        var isChecked = Array.from(radioButtons).some(function(radio) {
	            return radio.checked;
	        });

	        if (!isChecked) {
	            applyErrorStyling(field);
	            displayErrorMessage(alertmsg);
	            return false;
	        }
	        removeErrorStyling(field);
	        return true;
	    }
	    function validateDateField(field, alertMsg) {
		    var fieldValue = field.value.trim();
		    
		    if (fieldValue === '') {
		        applyErrorStyling(field);
		        displayErrorMessage(alertMsg);
		        return false;
		    }
		
		    removeErrorStyling(field);
		    return true;
		}

	    function validateFileInput(fileInput, alertmsg, allowedFileTypes,maxSize) {
	        if (!fileInput.files || fileInput.files.length === 0) {
	            applyErrorStyling(fileInput);
	            displayErrorMessage(alertmsg);
	            return false;
	        }

	        if (allowedFileTypes && allowedFileTypes.length > 0) {
		        var file = fileInput.files[0];
	            var fileName = fileInput.files[0].name.toLowerCase();
	            var fileType = fileName.substr(fileName.lastIndexOf('.') + 1);

		            if (!allowedFileTypes.includes(fileType)) {
		                applyErrorStyling(fileInput);
		                displayErrorMessage('Invalid file type. Please select a valid file.');
		                return false;
		            }
		            
		            if (file.size > maxSize) {
			         var maxSizeInMB = maxSize / (1024 * 1024);
				       applyErrorStyling(fileInput);
	
	                   displayErrorMessage('File size exceeds the limit. Please select a file under ' + maxSizeInMB + ' MB.');
				        return false;
	                }
	        }

	        removeErrorStyling(fileInput);
	        return true;
	    }


	    function applyErrorStyling(field) {
	        field.style.borderColor = 'red';
	        
	        
	        setTimeout(function () {
	        	removeErrorStyling(field);
	        }, 5000);
	    }
	    function removeErrorStyling(field) {
	        field.style.borderColor = '';
	    }

	   function displayErrorMessage(message) {
	        var alertDiv = document.getElementById('alertMessage');//add div before with the id named as ->'alertMessage'
	        if(alertDiv!=null){                                    //The Div Where msg will be displayed             
	        alertDiv.innerHTML = message;
	        alertDiv.style.color = 'red';
            alertDiv.classList.add('blink_me');//add the highLight for  message

	        setTimeout(function () {
	            clearErrorMessage();
	        }, 5000);
	        }
	        else{
	         // speakMessage(message);
	          bootbox.alert(message);
	        }
	    }

	    function clearErrorMessage() {
	    
	        var alertDiv = document.getElementById('alertMessage');
	        if(alertDiv!=null){
	        alertDiv.innerHTML = '';
	        alertDiv.style.color = ''; 
	         alertDiv.classList.remove('blink_me');
	         }
	    }  
	    
	    
function generateAlertMessage(field) {
    var fieldMsgElement = document.getElementById(field.id + 'MSG');
    var labelMsgElement = document.getElementById('labelmsg');
    var defaultMsg = 'Required fields should not be empty.';
    var alertMsg = defaultMsg;

    if (fieldMsgElement && fieldMsgElement.value) {
        alertMsg = fieldMsgElement.value.trim();
    } else if (labelMsgElement && labelMsgElement.value === 'true') {
        var label = getNearestLabel(field);
        if (label) {
            var labelText = label.textContent.trim().toLowerCase();
            alertMsg = `The field ${labelText} should not be empty.`;
        }
    }

    return alertMsg;
}




 function getNearestLabel(field) {
    var label = field.closest('label');

    if (!label) {
        // Check if there's a label with a "for" attribute matching the field's id
        var labels = document.getElementsByTagName('label');
        for (var i = 0; i < labels.length; i++) {
            if (labels[i].htmlFor === field.id) {
                label = labels[i];
                break;
            }
        }
    }

    if (!label) {
        // Try to find a sibling label within the same form group
        var parent = field.closest('.form-group');
        if (parent) {
            label = parent.querySelector('label[for="' + field.id + '"]');
            if (!label) {
                // If no label with "for" attribute, try to find the closest label within the parent
                label = parent.querySelector('label');
            }
        }
    }
    
    
    //for table
    var td = field.closest('td');
    if (td) {
        var index = Array.prototype.indexOf.call(td.parentNode.children, td);// find the td index

        var table = td.closest('table');
        if (table) {
            var th = table.querySelectorAll('thead th')[index];// find the corresponding th
            if (th) {
                label= th;
            }
        }
    }
    return label;
}
	    
  function confirmation(formId){
     bootbox.confirm({
         message: "Do you want to proceed ?",
         buttons: {
             confirm: {
                 label: 'OK',
                 className: 'btn-success'
             },
             cancel: {
                 label: 'Cancel',
                 className: 'btn-danger'
             }
         },
         callback: function (result) {
             if (result) {
             	if (typeof showLoader === "function") {
                    showLoader();
                }
                if (typeof removeFieldsFromFormBYClass === "function") {
                    removeFieldsFromFormBYClass(formId, 'remove');
                }
                 $('#'+formId).submit();
             }
         }
     });
}



	 //**************FORM VALIDATION FOR EMPTY FIELD AND DOC TYPE END****************   
	 
	 
	 
	 
	 
	function validatePhoneNumber(inputElement) {
	    const phonePattern = /^\d{10}$/;
	    if (!phonePattern.test(inputElement.value)) {
	        bootbox.alert('Please enter a valid 10-digit phone number.');
	        inputElement.value = '';  // Clear the input field
	        return false;
	    }
	    return true;
	}
	
	function validateEmail(inputElement) {
	    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	    if (inputElement.value && !emailPattern.test(inputElement.value)) {
	        bootbox.alert('Please enter a valid email address.');
	        inputElement.value = '';  // Clear the input field
	        return false;
	    }
	    return true;
	}
	
	function validateWebsite(inputElement) {
	    const websitePattern = /^(http:\/\/|https:\/\/)?([\w-]+(\.[\w-]+)+)([\/?].*)?$/;
	    if (inputElement.value && !websitePattern.test(inputElement.value)) {
	        bootbox.alert('Please enter a valid website URL.');
	        inputElement.value = '';  // Clear the input field
	        return false;
	    }
	    return true;
	}
	    