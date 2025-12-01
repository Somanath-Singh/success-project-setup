/**
tableOrTbodyKeys - Ex->['roomDetailsList', 'staffDetailsList'];Corresponding keys for the containers -->key Name of the List Object
tableOrTbodyIDS - Ex->['tableId1', 'tableId'];// List of container IDs ->tableId Or TbodyId Or Div Id 
if have  a input field with  id named as ('encodedData' + formId) otherwise get the data with 
the key named as 'encodedData'
 */
 
 
//**************ENCRIPTION OF FORM DATA START****************

   /**
	 * @author Debapriya
	 * @since  10/02/2024
	 * @return boolean value with set the encoded data in a hidden field 
	 * @vs:1.2
   */ 
 var tableOrTbodyIDS=[]; /* ADD TABLE ID OR TBODY ID OR PARENT DIV ID */
 var tableOrTbodyKeys=[];  /* ADD KEY NAME FOR THE JSON ARRAY SEQUENTIALLY ACCOUDING TO THE TABLE */
	function extractContainerData(containerId) {
    var dataArray = [];
    var container = document.getElementById(containerId);

    if (container) {
        // Determine the row start index based on the container type
        var rowStartIndex = (container.tagName.toLowerCase() === 'table') ? 1 : 0;   /*if table ID is in parameter start rows from 1 if  tbody Id is in parameter start row from 0 */

        // Get rows or divs
        var rows = (container.tagName.toLowerCase() === 'table' || container.tagName.toLowerCase() === 'tbody') ? 
                    container.getElementsByTagName('tr') : 
                    container.getElementsByClassName('child'); // In the evry row should be in a div having the class name  'child'

        for (var i = rowStartIndex; i < rows.length; i++) {
            var rowObject = {};
            var inputsAndSelects = rows[i].querySelectorAll('input, select, textarea, datalist, output, progress, keygen, meter');
            for (var j = 0; j < inputsAndSelects.length; j++) {
                var fieldName = inputsAndSelects[j].getAttribute('name');
                
                if (!fieldName || fieldName.trim() === '') {
                    continue; 
                }

                var fieldValue = inputsAndSelects[j].value;

                if (inputsAndSelects[j].tagName === 'SELECT' && inputsAndSelects[j].multiple) {
                    fieldValue = [];
                    var options = inputsAndSelects[j].selectedOptions;
                    for (var k = 0; k < options.length; k++) {
                        fieldValue.push(options[k].value);
                    }
                } else {
                    fieldValue = inputsAndSelects[j].value;
                }

                var fieldNameParts = fieldName.split('.');
                var currentObject = rowObject;
                for (var partIndex = 1; partIndex < fieldNameParts.length; partIndex++) {
                    var partName = fieldNameParts[partIndex];

                    if (partIndex === fieldNameParts.length - 1) {
                        currentObject[partName] = fieldValue;
                    } else {
                        if (!currentObject[partName]) {
                            currentObject[partName] = {};
                        }
                        currentObject = currentObject[partName];
                    }
                }
            }

            if (Object.keys(rowObject).length > 0) {
                dataArray.push(rowObject);
            }
        }
    }

    return dataArray;
}



	function encryptFormData(formId, tableOrTbodyIDS, tableOrTbodyKeys) {
	    var formData = new FormData(document.getElementById(formId));
	    var formDataJson = {};
         debugger
	  
			
		formData.forEach(function(value, key) {
        var element = document.querySelector(`[name="${key}"]`);

        if (element && element.multiple) {//multiple select
            
            if (!formDataJson[key]) {
                formDataJson[key] = [];
            }
            formDataJson[key].push(value);
        } else if (formDataJson.hasOwnProperty(key)) {
            if (!Array.isArray(formDataJson[key])) {// same key found 
               
                formDataJson[key] = [formDataJson[key]];
            }
           
            formDataJson[key].push(value);
        } else {
           
            formDataJson[key] = value;
        }
    });

          
        
	    for (var i = 0; i < tableOrTbodyIDS.length; i++) {
	        var dataList = extractContainerData(tableOrTbodyIDS[i]);
	        formDataJson[tableOrTbodyKeys[i]] = dataList;
	    }

	    var encryptedData = encryptJSONobj(formDataJson);
	    
	    
	    
	     var encodedDataField = $("#encodedData" + formId);
		    if (encodedDataField.length > 0) {
		        encodedDataField.val(encryptedData);
		    } else {
		        // If the input field doesn't exist, create a new hidden input field and set its value
		        // name:"encodedData"
		        $('<input>').attr({
		            type: 'hidden',
		            id: 'encodedData' + formId,
		            name: 'encodedData',
		            value: encryptedData
		        }).appendTo('form#' + formId);
		    }
	    

	    return true;
	}
	

	 
 function encryptJSONobj(formDataJson) {
			
	    var formDataString = JSON.stringify(formDataJson);
	    
        var encryptedData = enc_password(formDataString);
        //var encryptedData =AES256.encrypt(formDataString);
        
	    return encryptedData; 
	}
	
	
 function removeFieldsFromFormBYClass(formId, className) {
    var form = document.getElementById(formId);
    if (form) {
        var fields = form.getElementsByClassName(className);
        var fieldsArray = Array.from(fields); 

        fieldsArray.forEach(function (field) {
            field.remove();
        });
    }
}
	
	//**************ENCRIPTION OF FORM DATA END****************