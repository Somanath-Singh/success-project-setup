const specialKeys = [
    'Backspace', 'Tab', 'ArrowLeft', 'ArrowRight', 'ArrowUp', 'ArrowDown',
    'Delete', 'Shift', 'Control', 'Alt', 'Escape', 'Enter', 'Home', 'End', 'Delete', 'PageUp', 'PageDown',
];
document.addEventListener('DOMContentLoaded', () => {
    const applyRestrictions = (selector, {maxLength, validateRegex, allowedRegex, toUpperCase, toLowerCase}) => {
        const inputs = document.querySelectorAll(selector);

        const validateInput = (e) => {
            e.target.style.border = validateRegex.test(e.target.value) ? '1px solid #ccc' : '2px solid red';
        };

        const restrictInput = (e) => {
            const specialKeys = ['Backspace', 'Tab', 'Enter', 'Escape', 'ArrowLeft', 'ArrowRight', 'ArrowUp', 'ArrowDown', 'Delete']; // Allow special keys
            if (specialKeys.includes(e.key) || (e.ctrlKey || e.metaKey)) {
                return; // Allow special keys and shortcuts
            }

            if (!allowedRegex.test(e.key)) {
                e.preventDefault();
            }
        };

        const convertCase = (e) => {
            if (toUpperCase) {
                e.target.value = e.target.value.toUpperCase();
            } else if (toLowerCase) {
                e.target.value = e.target.value.toLowerCase();
            }
        };

        inputs.forEach(input => {
            if (maxLength) input.setAttribute('maxlength', maxLength);
            input.addEventListener('input', validateInput);
            input.addEventListener('input', convertCase); // Add case conversion on input
            if (allowedRegex) {
                input.addEventListener('keydown', restrictInput);
            }
        });
    };

    applyRestrictions('.restricted', {
        maxLength: 50,
        validateRegex: /^[A-Za-z\d#$%\-@_]{8,50}$/,
        allowedRegex: /^[A-Za-z\d#$%&\-@_]+$/
    });

    applyRestrictions('.numeric', {
        validateRegex: /^\d*$/,
        allowedRegex: /^\d$/
    });

    applyRestrictions('.mobile-number', {
        maxLength: 10,
        validateRegex: /^\d{10}$/,
        allowedRegex: /^[0-9]$/
    });

    applyRestrictions('.email', {
        maxLength: 254,
        validateRegex: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
        allowedRegex: /^[A-Za-z0-9@._-]$/,
        toLowerCase: true // Convert email addresses to lowercase
    });

    applyRestrictions('.uppercase', {
        validateRegex: /^.*$/, // Ensure only uppercase letters
        allowedRegex: /^.*$/,
        toUpperCase: true // Convert all input to uppercase
    });

    applyRestrictions('.lowercase', {
        validateRegex: /^.*$/, // Ensure only lowercase letters
        allowedRegex: /^.*$/,
        toLowerCase: true // Convert all inputs to lowercase
    });

    applyRestrictions('.alpha-space', {
        validateRegex: /^[A-Za-z\s]*$/, // Ensure only alphabetic characters and spaces
        allowedRegex: /^[A-Za-z\s]$/, // Allow alphabetic characters and spaces for keyboard input
    });

    // Add Alphanumeric condition
    applyRestrictions('.alphanumeric', {
        validateRegex: /^[A-Za-z0-9]*$/, // Ensure only alphanumeric characters
        allowedRegex: /^[A-Za-z0-9]$/, // Allow alphanumeric characters for keyboard input
    });
});


const showNotification = (isError, message) => {
    // Create the notification element
    const notification = document.createElement('div');
    notification.classList.add('notification');
    if (isError) {
        notification.classList.add('error');
    } else {
        notification.classList.add('success');
    }

    // Create close button
    const closeButton = document.createElement('button');
    closeButton.classList.add('close-button');
    closeButton.innerHTML = '&times;'; // Close icon (X)
    notification.appendChild(closeButton);

    // Notification message
    const messageElement = document.createElement('div');
    messageElement.textContent = message;
    notification.appendChild(messageElement);

    // Create load bar
    const loadBar = document.createElement('div');
    loadBar.classList.add('load-bar');
    notification.appendChild(loadBar);

    const loadBarInner = document.createElement('div');
    loadBarInner.classList.add('load-bar-inner');
    loadBar.appendChild(loadBarInner);

    // Append the notification to the body
    document.body.appendChild(notification);

    // Add the show class to display the notification
    notification.classList.add('show');

    // Automatically hide after 5 seconds
    setTimeout(() => {
        hideNotification(notification);
    }, 5000);

    // Close button event listener
    closeButton.addEventListener('click', () => {
        hideNotification(notification);
    });
};

const hideNotification = (notification) => {
    if (!notification) return;
    notification.classList.remove('show');
    notification.classList.add('hide');

    // Remove the notification element from the DOM after hide animation completes (assuming 0.5s duration)
    setTimeout(() => {
        notification.remove();
    }, 500);
};

// aadhar,account,gstin  validation  @Author Dipty ranjan Das



function validateAllDetails(data, type) {
    var accountNumberValue, aadharId, gstNo, panNo;
    
    if (type === 'BANKACNO') {
        accountNumberValue = data;
        if (accountNumberValue.length < 6 || accountNumberValue.length > 20) {
            alert('Account number must be between 6 and 20 characters.');
            $("#bankAcNoReq").val("");
            return false;
        }
    } else if (type === 'AADHARNO') {
        aadharId = data;
        if (!MinimumLengthValidation(aadharId, 12, 'digit valid aadhar number')) {
            alert("Please enter a valid aadhar number.");
            $("#aadharaNumber").val("");
            return false;
        } else if (!ValidAadhaarNo(aadharId)) {
            alert("Please enter a valid aadhar number.");
            $("#aadharaNumber").val("");
            return false;
        }
    } else if (type === 'GSTNO') {
        gstNo = data;
        var gstPattern = /^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/;
        if (!gstPattern.test(gstNo)) {
            alert("Please enter a valid GST number.");
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
            alert("PAN Number is not valid. It should be in the format: 'ABCDE1234F'.");
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


// convertForm data ToJSONArray
function convertFormToJSONArray(form) {
		debugger
		  const array = form; // Encodes the set of form elements as an array of names and values.
		   const json = {};
		    $.each(array, function() {
		    	var contentCheck=this.name;
		        if (json[this.name]) {
		            if (!json[this.name].push) {
		            	json[this.name] = [json[this.name]];
		            }
		            json[this.name].push(this.value || '');
		        } else if(contentCheck.includes("Array")) {
		        	var val;
		        	var values=this.value;
		        	if(values.includes(",")){
		        		val = values.split(",");
		        	}else{
		        		val=[values];
		        	}
		        	
		        	json[this.name] = val;
		        }else{
		        	json[this.name] = this.value || '';
		        }
		    });
		  return json; 
		}
		
        

        // Example usage:
        // let encryptedPassword = enc_password("yourPassword");
        // console.log(encryptedPassword);

        
       function duplicateCheck(value, type) {
    // Perform an AJAX call to check for duplicates
   
}

 //@Debapriya
 function validateInputDecimal(event, inputElement) {
	    	debugger
	    	  var inputValue = inputElement.value;

// 	    	  var isDigitOrDecimal = (event.charCode >= 48 && event.charCode <= 57) || event.charCode == 46;
	    	  var isDigitOrDecimal = (event.charCode >= 48 && event.charCode <= 57) || event.charCode == 46 || event.charCode == 45;


	    	  var decimalIndex = inputValue.indexOf('.');

	    	  if (decimalIndex !== -1) {
	    	    if (event.charCode == 46) {
	    	      event.preventDefault();
	    	      return false;
	    	    }

	    	    var decimalPart = inputValue.substr(decimalIndex + 1);
	    	  }

	    	  return isDigitOrDecimal;
	    	}
