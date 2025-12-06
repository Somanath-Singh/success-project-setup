$("input, textarea, select").focus(function(){
	var padding=2;
	// Check their position relative to the window's scroll
	var elementBottom = $(this).offset().top + $(this).height();
	var windowScroll = $(window).scrollLeft();
	var windowBottom = windowScroll + $(window).height();

	if(elementBottom + padding > windowBottom){
		$(window).scrollLeft( windowScroll + padding);
	}
});

// Amount field validation
$('.amount-field').on('blur', function() {
    // Get the current value
    var currentValue = $(this).val();
    if (currentValue !== '' && !isNaN(currentValue)) {
        // Format the value to 10.00 using toFixed(2)
        var formattedValue = parseFloat(currentValue).toFixed(2);

        // Set the formatted value back to the input field
        $(this).val(formattedValue);
    }else{
        $(this).val('0.00');
    }
});



function toUppercase(that) 
{
	that.value = that.value.toUpperCase();
}

function isLetter(str) 
{
	return str.length === 1 && str.match(/[a-z]/i);
}

var rptOfficeTypeName = null;

function isNumber(evt) 
{
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) 
	{
		return false;
	}
	return true;
}

function isNumber1(evt) 
{
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode==46)
	{
		return true;
	}
	else if (charCode > 31 && (charCode<48 || charCode>57)) 
	{
		return false;
	}
	else
	{
		return true;
	}
}

function isNumericValue(field)
{ 
	argvalue = field.value ;
	var validChars = "0123456789.-";
	var startFrom = 0;	
	for (var n = startFrom; n < argvalue.length; n++) 
	{
		if (validChars.indexOf(argvalue.substring(n, n+1)) == -1)
		{
			field.value = field.value.substring(0,n) ;
			return false ;
		}
	}
	return true;
}
function isNumeric(field)
{ 
	argvalue = field.value ;
	var validChars = "0123456789";
	var startFrom = 0;	
	for (var n = startFrom; n < argvalue.length; n++) 
	{
		if (validChars.indexOf(argvalue.substring(n, n+1)) == -1)
		{
			field.value = field.value.substring(0,n) ;
			return false ;
		}
	}
	return true;
}

function validIfscCode(obj)
{
	var mobNo = $("#ifscCode1").val();

	if (mobNo.length>11 || mobNo.length<11)
	{
		bootbox.alert("IFSC code should be 11 digit");
		obj.value ='';
		return false;
	}
}

function validAadharNo(obj)
{
	var aadharNo = $("#aadharNo1").val();
	if (aadharNo.length>12 || aadharNo.length<12)
	{
		
		bootbox.alert("Aadhar No should be 12 digit");
		obj.value ='';
		return false;
	}
}

function validGSTNo(obj)
{
	var gstNo = $("#gstNo1").val();

	if(gstNo.length>15 || gstNo.length<15){
		bootbox.alert("GSTIN number should be 15 digit");
		obj.value= '';
		return false;
	}
}

function validateMobileNo(element) 
{
	var re = /^[0-9]+$/;
	var str = element.toString();
	var str1 = element.value;
	element = (element) ? element : window.event;
	var charCode = (element.which) ? element.which : element.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) 
	{
		return false;
	}
	
	if(isNaN(str1)||str1.indexOf(" ")!=-1)
	{
		bootbox.alert("Invalid Mobile No.");
		var result = element.value.replace(/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g,'');
		element.value =result;
		return false;
	}
	
	if (str1.length>10 || str1.length<10)
	{
		bootbox.alert("Mobile No. should be 10 digit");
		var result = element.value.replace(/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g,'');
	 	element.value =result;
		// document.getElementById('mobileNo').focus();
		element.value = '';
		return false;
	}
	
	if (!str.match(re)) 
	{
		var result = element.value.replace(/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g,'');
		element.value =result;
		return false;
	}
	return true;
}


function contactNo(element)
{
	var result = element.value.replace(/^[0-9]\d{2,4}-\d{6,8}$]/g,'');
	element.value =result;
}

function validateName(element)
{
	var result = element.value.replace(/[0-9\u200B-\u200D\u201A-\u201E\u2013-\u2122\uFEFF\!@#$^&*+<>\\\/|\?~{}())(\:%]/g,'');
	element.value =result;
}

function validateContactNo(element)
{
	var result = element.value.replace(/[a-zA-Z\u200B-\u200D\u201A-\u201E\u2013-\u2122\uFEFF\!@#$^&*+=-_<>\\\/|\?~{}())(\:%]/g,'');
	element.value =result;
}

function validateNameAndCode(element)
{
	var result = element.value.replace(/[\u200B-\u200D\u201A-\u201E\u2013-\u2122\uFEFF\!@#$^&*+<>\\\/|\?~{}())(\:%]/g,'');
	element.value =result;
}

function validateAddress(element)
{
	var result = element.value.replace(/[\u200B-\u200D\u201A-\u201E\u2013-\u2122\uFEFF\!@$^&*+<>\\\|\?~{}())(\:%]/g,'');
	element.value =result;
}

function validateIFSC(Obj) 
{//alsert('IFSC val : '+$("#ifscCode1").val());
	if (Obj == null) 
		Obj = $("#ifscCode1").val();
	if (Obj.value != "") 
	{
		ObjVal = Obj.value;
		var ifscPat = /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})$/;
		var code = /([C,P,H,F,A,T,B,L,J,G])/;
		var code_chk = ObjVal.substring(3,4);
		if (code.test(code_chk) == false) 
		{
			bootbox.alert("Invaild IFSC Code. Please try a correct code..");
 			Obj.value = '';
 			return false;
 		}
	}
}

/*
	a. First two positions will be numeric
	b. Third to Sixth positions will be Alphabets
	c. Seventh position will be either alphabet or numeric
	d. Eighth to Eleventh positions will be numeric
	e. Twelfth position will be an alphabet
	f. Thirteenth to Fifteenth positions will be alphanumeric
*/
//* @description : Validate GST No.
//* @author : Tapan
//* @since : 08 Jan 2019
function isValidGSTno(vGSTnoObj)
{
	if (vGSTnoObj == null) 
		vGSTnoObj = window.event.srcElement;

	var gstRegEx = /^([0-9]{2}[a-zA-Z]{4}([a-zA-Z]{1}|[0-9]{1})[0-9]{4}[a-zA-Z]{1}([a-zA-Z]|[0-9]){3}){0,15}$/
	
	if(vGSTnoObj.value != "" && !gstRegEx.test(vGSTnoObj.value))
	{
		bootbox.alert("GST Identification Number is not valid. It should be in the format : '00ABCDE12345Z1ZZ' OR 'XXABCDE12345Z1X1' .");
		vGSTnoObj.value = '';
		return false;
	}
	else
		return true;
}

/*
	a. First to Fifth positions will be Alphabets
	b. Sixth to Ninth positions will be numeric
	d. Tenth position will be an alphabet
*/
//* @description : Validate PAN No.
//* @author : Tapan
//* @since : 08 Jan 2019
function validatePAN_INCORRECT(vPANnoObj) 
{
	if (vPANnoObj == null) 
		vPANnoObj = window.event.srcElement;

	if (vPANnoObj.value != "") 
	{
		ObjVal = vPANnoObj.value;
		var panRegEx = /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})$/;
		var code = /([C,P,H,F,A,T,B,L,J,G])/;
		var code_chk = ObjVal.substring(3,4);
		if (code.test(code_chk) == false) 
		{
			bootbox.alert("PAN Number is not valid. It should be in the format : 'XXXXX0000X' .");
			vPANnoObj.value = '';
 			return false;
 		}
	}
}

function validatePAN(vPANnoObj)
{
	if (vPANnoObj == null) 
		vPANnoObj = window.event.srcElement;

	var panVal = vPANnoObj.value;
	var regpan = /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/;

	if(regpan.test(panVal))
	{
	}
	else 
	{
		bootbox.alert("PAN Number is not valid. It should be in the format : 'ABCDE1234F' .");
		vPANnoObj.value = '';
		return false;
	}
}

  function validatePanNo()
{
var panNo = document.getElementById("panNo");

if (panNo.value != "") {
      PanNo = panNo.value;
      var panPattern = /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})$/;
      if (PanNo.search(panPattern) == -1) {
    	  bootbox.alert("Invalid Pan No. It should be in the format : 'ABCDE1234F' .");
          panNo.focus();
          panNo.value='';
          return false;
      }
    
  }
}  

//* @description : Get PAN from GST No.
//* @author : Tapan
//* @since : 08 Jan 2019
function fetchPANfromGST(vGSTnoObj,vTargetPanObj) 
{
	panNo = "";

	if(isValidGSTno(vGSTnoObj))
	{
		if (vGSTnoObj == null) 
			vGSTnoObj = window.event.srcElement;
	
		if (vGSTnoObj.value != "") 
		{
			panNo = vGSTnoObj.value.substring(2,12);
			document.getElementById(vTargetPanObj).value = panNo;
		}
		else
			document.getElementById(vTargetPanObj).value = "";
	}
	else
	{
		document.getElementById(vTargetPanObj).value = "";
	}
}

function lpadToValue(inputValue, totalDigits, padWith) 
{
	padWith = padWith || '0';
	inputValue = inputValue + '';
	return inputValue.length >= totalDigits ? inputValue : new Array(totalDigits - inputValue.length + 1).join(padWith) + inputValue;
}

//* @description : Return the last day of a month
//* @author : Tapan
//* @since : 15 Apr 2017
function getLastDayOfMonth(inDateString)
{
	// GET THE MONTH AND YEAR OF THE SELECTED DATE.
	var month = new Date(inDateString).getMonth();
	var year = new Date(inDateString).getFullYear();

//	alert("Month==>" +month+ "<< --- Year ===>" +year);
	
	var FirstDay = new Date(year, month, 1);
	var LastDay = new Date(year, month + 1, 0);

//	alert("FirstDay==>" +FirstDay+ "<< --- LastDay ===>" +LastDay);

}

// * @description : validate all decimal text-boxes
// * @author : Tapan
// * @since : 15 Apr 2017
function validateDecimalField(fieldId,decimalLength,maxLength,msgLabel)
{
	// Decimal Length : Scale of the datatype of the field.
	// Max Length : Max Length including decimal.
	if($("#"+fieldId).val() != "" && parseFloat($("#"+fieldId).val()) > 0)
	{
		var fieldValue = $("#"+fieldId).val();
		if(isNaN(fieldValue))
		{
			bootbox.alert("Please enter a numeric value for " +msgLabel+ ".");
			$("#"+fieldId).val("");
			setTimeout(function(){$("#"+fieldId).focus();}, 100);
			//$("#"+fieldId).focus();
			return false;
		}

		var result = "";

		/*if(fieldValue.indexOf(".") == -1)
		{
			alert("inside if block...");
			result = (parseFloat(fieldValue)).toFixed(decimalLength);
		}
		*/
		result = (parseFloat(fieldValue)).toFixed(decimalLength);

		$("#"+fieldId).val(result);
	
		if($("#"+fieldId).val().length > maxLength)
		{
			bootbox.alert("Invalid entry found for " +msgLabel+ ".");
			$("#"+fieldId).val("");
			//$("#"+fieldId).focus();
			return false;
		}
		return true;
	}
	else if(isNaN($("#"+fieldId).val()))
	{
		bootbox.alert("Invalid entry found for " +msgLabel+ ".");
		$("#"+fieldId).val("");
		//$("#"+fieldId).focus();
		return false;
	}
	else
		return true;
}

//* @description : get current-date in dd-mm-yyyy
//* @author : Tapan
//* @since : 15 Apr 2017
function getCurrentDate()
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
	var currDate = dd+'-'+mm+'-'+yyyy;
	return currDate;
}

//* @description : get current-date in dd/mm/yyyy
//* @author : Tapan
//* @since : 24 Jan 2019
function getCurrentDateAsDDMMYYYYHyphenSeparated()
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

//* @description : compares 2 dates for bigger or smaller (Format : dd/mm/yyyy)
//* @author : Tapan
//* @since : 15 Apr 2017
function checkFirstDateIsBigger(smallerDate,biggerDate)
{
	if(typeof smallerDate == "undefined" || smallerDate == "")
		smallerDate = getCurrentDate();
	if(typeof biggerDate == "undefined" || biggerDate == "")
		biggerDate = getCurrentDate();
	
	var biggerYear = biggerDate.substring(6,10);
	var biggerMonth=biggerDate.substring(3,5);
	var biggerDay=biggerDate.substring(0,2);

	eIntMonth = parseInt(biggerMonth);
	endMonth=("00"+eIntMonth.toString()).slice(-2);
	var biggerDateFinal=biggerYear+endMonth+biggerDay;
	biggerDateFinal=parseInt(biggerDateFinal);	

	var smallerYear=smallerDate.substring(6,10);
	var smallerMonth=smallerDate.substring(3,5);
	var smallerDay=smallerDate.substring(0,2);
	
	var sIntMonth=parseInt(smallerMonth);
	var startMonth=("00"+sIntMonth.toString()).slice(-2);
	var smallerDateFinal=smallerYear+startMonth+smallerDay;
	smallerDateFinal=parseInt(smallerDateFinal);
	if (smallerDateFinal > biggerDateFinal)
	{
		return true;
	}
	else
	{
		return false;
	}
}

//* @description : compares 2 dates for bigger or smaller (Format : dd/mm/yyyy)
//* @author : Tapan
//* @since : 15 Apr 2017
function checkFirstDateIsBiggereq(smallerDate,biggerDate)
{
	if(typeof smallerDate == "undefined" || smallerDate == "")
		smallerDate = getCurrentDate();
	if(typeof biggerDate == "undefined" || biggerDate == "")
		biggerDate = getCurrentDate();
	
	var biggerYear = biggerDate.substring(6,10);
	var biggerMonth=biggerDate.substring(3,5);
	var biggerDay=biggerDate.substring(0,2);

	eIntMonth = parseInt(biggerMonth);
	endMonth=("00"+eIntMonth.toString()).slice(-2);
	var biggerDateFinal=biggerYear+endMonth+biggerDay;
	biggerDateFinal=parseInt(biggerDateFinal);	

	var smallerYear=smallerDate.substring(6,10);
	var smallerMonth=smallerDate.substring(3,5);
	var smallerDay=smallerDate.substring(0,2);
	
	var sIntMonth=parseInt(smallerMonth);
	var startMonth=("00"+sIntMonth.toString()).slice(-2);
	var smallerDateFinal=smallerYear+startMonth+smallerDay;
	smallerDateFinal=parseInt(smallerDateFinal);
	if (smallerDateFinal >= biggerDateFinal)
	{
		return true;
	}
	else
	{
		return false;
	}
}

//* @description : compares 2 dates for bigger or smaller (Format : dd/mm/yyyy)
//* @author : Tapan
//* @since : 15 Apr 2017
function checkFirstDateIsSmaller(smallerDate,biggerDate)
{
	if(typeof smallerDate == "undefined" || smallerDate == "")
		smallerDate = getCurrentDate();
	if(typeof biggerDate == "undefined" || biggerDate == "")
		biggerDate = getCurrentDate();
	
	var biggerYear = biggerDate.substring(6,10);
	var biggerMonth=biggerDate.substring(3,5);
	var biggerDay=biggerDate.substring(0,2);

	eIntMonth = parseInt(biggerMonth);
	endMonth=("00"+eIntMonth.toString()).slice(-2);
	var biggerDateFinal=biggerYear+endMonth+biggerDay;
	biggerDateFinal=parseInt(biggerDateFinal);	

	var smallerYear=smallerDate.substring(6,10);
	var smallerMonth=smallerDate.substring(3,5);
	var smallerDay=smallerDate.substring(0,2);
	
	var sIntMonth=parseInt(smallerMonth);
	var startMonth=("00"+sIntMonth.toString()).slice(-2);
	var smallerDateFinal=smallerYear+startMonth+smallerDay;
	smallerDateFinal=parseInt(smallerDateFinal);
	if (smallerDateFinal < biggerDateFinal)
	{
		return true;
	}
	else
	{
		return false;
	}
}

//* @description : Convert value to INR Format (i.e. 1,23,45,789.00)
//* @author : Tapan
//* @since : 06 Dec 2017
function convertValueToINRFormat(strInputAmount) 
{
	strInputAmount=strInputAmount.toString();
	var decimalVal = '';

	if(strInputAmount.indexOf('.') > 0)
	   decimalVal = strInputAmount.substring(strInputAmount.indexOf('.'),strInputAmount.length);
	strInputAmount = Math.floor(strInputAmount);
	strInputAmount=strInputAmount.toString();

	var lastThree = strInputAmount.substring(strInputAmount.length-3);
	var otherNumbers = strInputAmount.substring(0,strInputAmount.length-3);

	if(otherNumbers != '')
	    lastThree = ',' + lastThree;

	if(decimalVal.substr(1).length == 0)
		decimalVal = ".00";
	else if(decimalVal.substr(1).length == 1)
		decimalVal = decimalVal + "0";
	else
		decimalVal = decimalVal;

	var formattedValue = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree + decimalVal;

	return formattedValue;
}

//* @description : Convert value to INR Format (i.e. 1,23,45,789), but no Decimal
//* @author : Tapan
//* @since : 06 Dec 2017
function convertValueToINRFormatWithoutDecimal(strInputAmount) 
{
	strInputAmount=strInputAmount.toString();
	var decimalVal = '';

	if(strInputAmount.indexOf('.') > 0)
	   decimalVal = strInputAmount.substring(strInputAmount.indexOf('.'),strInputAmount.length);
	strInputAmount = Math.floor(strInputAmount);
	strInputAmount=strInputAmount.toString();

	var lastThree = strInputAmount.substring(strInputAmount.length-3);
	var otherNumbers = strInputAmount.substring(0,strInputAmount.length-3);

	if(otherNumbers != '')
	    lastThree = ',' + lastThree;

	var formattedValue = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree;

	return formattedValue;
}



//* @description : Convert value to INR Format (i.e. 1,23,45,789.00)
//* @author : Tapan
//* @since : 06 Dec 2017
function convertValueToINRFormat_OLD(strInputAmount) 
{
	strInputAmount += '';
	x = strInputAmount.split('.');
	numericVal = x[0];
	decimalVal = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	var z = 0;
	var len = String(numericVal).length;
	var num = parseInt((len/2)-1);

	while (rgx.test(numericVal))
	{
		if(z > 0)
		{
			numericVal = numericVal.replace(rgx, '$1' + ',' + '$2');
		}
		else
		{
			numericVal = numericVal.replace(rgx, '$1' + ',' + '$2');
			rgx = /(\d+)(\d{2})/;
		}
		z++;
		num--;
		if(num == 0)
		{
			break;
		}
	}
	//alert("numericVal & decimalVal ---->>"+numericVal +"<<< >>>>>"+ decimalVal +"<<< length====>"+ decimalVal.substr(1).length);
	
	if(decimalVal.substr(1).length == 0)
		decimalVal = ".00";
	else if(decimalVal.substr(1).length == 1)
		decimalVal = decimalVal + "0";
	else
		decimalVal = decimalVal;
	
	return numericVal + decimalVal;
}

//* @description : Convert value to INR Format (i.e. 1,23,45,789), but no Decimal
//* @author : Tapan
//* @since : 06 Dec 2017
function convertValueToINRFormatWithoutDecimal_OLD(strInputAmount) 
{
	strInputAmount += '';
	x = strInputAmount.split('.');
	numericVal = x[0];
	decimalVal = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	var z = 0;
	var len = String(numericVal).length;
	var num = parseInt((len/2)-1);

	while (rgx.test(numericVal))
	{
		if(z > 0)
		{
			numericVal = numericVal.replace(rgx, '$1' + ',' + '$2');
		}
		else
		{
			numericVal = numericVal.replace(rgx, '$1' + ',' + '$2');
			rgx = /(\d+)(\d{2})/;
		}
		z++;
		num--;
		if(num == 0)
		{
			break;
		}
	}
	return numericVal;
}

/**
 * @author Tapan
 * @version 1.0
 * @since 30-05-2017
 * @param 
 * @description Convert the amount to Words (Rupees & Paise)
 */

function convertAmountToWords(amountToConvert) 
{
	var amountInWordsOut = "";
	if(parseFloat(amountToConvert)==0)
	{
		amountInWordsOut = "";
	}
	else if(parseFloat(amountToConvert)>0)
	{
		var fraction = Math.round(getFraction(amountToConvert)*100);
		var paiseInWords  = "";
	
		var amountInWordsWithoutPaise = convertToNumber(amountToConvert);
		
		if(fraction > 0)
		{
			if(amountInWordsWithoutPaise.lastIndexOf(" and ") != -1)
			{
				amountInWordsWithoutPaise = amountInWordsWithoutPaise.replace(" and "," ");
			}
			paiseInWords = "and "+convertToNumber(fraction)+" Paise ";
		}
		amountInWordsOut = "Rupees " + amountInWordsWithoutPaise +" "+ paiseInWords +"only";
	}
	else
	{
		amountInWordsOut = "Incorrect amount !!!";
	}
	
	return amountInWordsOut;
}

/**
 * @author Tapan
 * @description Part of the Convert to Words function
 */
function getFraction(fractionAmount) 
{
	return fractionAmount % 1;
}

/**
 * @author Tapan
 * @description Part of the Convert to Words function
 */
function convertToNumber(amountInNum)
{
	/*if ((amountInNum < 0) || (amountInNum > 999999999)) 
	{
		return "Incorrect amount found !!!";
	}*/
	var amtInCrore = Math.floor(amountInNum / 10000000);	/* Crore */ 
	amountInNum -= amtInCrore * 10000000;

	var amtInLakhs = Math.floor(amountInNum / 100000);		/* lakhs */ 
	amountInNum -= amtInLakhs * 100000;
	
	var amtInThousands = Math.floor(amountInNum / 1000);	/* thousand */ 
	amountInNum -= amtInThousands * 1000;
	
	var amtInHundreds = Math.floor(amountInNum / 100);		/* Tens (deca) */ 
	amountInNum = amountInNum % 100;						/* Ones */ 
	
	var amtInTens = Math.floor(amountInNum / 10);
	var amtInOnes = Math.floor(amountInNum % 10);
	
	var result = "";

	if (amtInCrore>0)
	{
		result += (convertToNumber(amtInCrore) + " Crore");
	}
	if (amtInLakhs>0)
	{
		result += (((result=="") ? "" : " ") + 
		convertToNumber(amtInLakhs) + " Lakh");
	}
	if (amtInThousands>0) 
	{
		result += (((result=="") ? "" : " ") + convertToNumber(amtInThousands) + " Thousand");
	}

	if (amtInHundreds) 
	{
		result += (((result=="") ? "" : " ") + convertToNumber(amtInHundreds) + " Hundred");
	}

	var arrValuesInOnes = Array("", "One", "Two", "Three", "Four", "Five", "Six","Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen","Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen","Nineteen");
	var arrValuesInTens = Array("", "", "Twenty", "Thirty", "Fourty", "Fifty", "Sixty","Seventy", "Eighty", "Ninety");

	if (amtInTens>0 || amtInOnes>0) 
	{
/*
		if (result!="")
		{
			result += " and ";
		}
*/
		if (result!="")
			result += " ";

		if (amtInTens < 2) 
		{
			result += arrValuesInOnes[amtInTens * 10 + amtInOnes];
		}
		else 
		{
			result += arrValuesInTens[amtInTens];
			if (amtInOnes>0) 
			{
				result += (" " + arrValuesInOnes[amtInOnes]);
			}
		}
	}

	if (result=="")
	{
		result = "zero";
	}
	return result;
}

//* @description : Lpad or Rpad to a String based on No of Digits
//* @author : Tapan
//* @since : 04 Jul 2018
//* @param : The String that is to be padded, Exact padding format, true if Lpad else false.
function padToStringLorR(stringToBePadded, paddingFormat, lpadTrue, targetFieldId)
{
	if (typeof stringToBePadded === 'undefined') 
		return paddingFormat;

	if (lpadTrue)
	{
		return (paddingFormat + stringToBePadded).slice(-paddingFormat.length);
	} 
	else 
	{
		return (stringToBePadded + paddingFormat).substring(0, paddingFormat.length);
	}
}

function validateDateFormatAsDDMMYYYY(strInputDateId) 
{
	var dob = strInputDateId.value.split('/');
	var dd = dob[0];
	var mm = dob[1];
	var yy = dob[2];

	if(strInputDateId.value != "")
	{
		if (dd == '' || mm == '' || yy == '' || isNaN(dd) || isNaN(mm) || isNaN(yy) || dd == 0 || mm == 0 || yy == 0) 
		{
			alert("Please enter a valid date.");
			$("#"+strInputDateId.id).val("");
			return false;
		}
		else 
		{
			if (dob.length != 3) 
			{
				alert("Please enter the Date in 'dd/mm/yyyy' format only.");
				$("#"+strInputDateId.id).val("");
				return false;
			}
			else 
			{
				if ((mm == 4 || mm == 6 || mm == 9 || mm == 11) && dd > 30) 
				{
					alert("Please enter Day less than equal to 30.");
					$("#"+strInputDateId.id).val("");
					return false;
				}
				if ((mm == 1 || mm == 3 || mm == 5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) && dd > 31) 
				{
					alert("Please enter Day less than equal to 31.");
					$("#"+strInputDateId.id).val("");
					return false;
				}
				if (mm == 2) 
				{
					var lyear = false;
					if ((!(yy % 4) && yy % 100) || !(yy % 400)) 
					{
						lyear = true;
					}
					if ((lyear == false) && (dd >= 29)) 
					{
						alert('Invalid date entered in February.');
						$("#"+strInputDateId.id).val("");
						return false;
					}
					if ((lyear == true) && (dd > 29)) 
					{
						alert('Invalid date format in February.');
						$("#"+strInputDateId.id).val("");
						return false;
					}
				}
				if (parseFloat(dob[1]) > 12) 
				{
					alert("Please enter Month less than equal to 12.");
					$("#"+strInputDateId.id).val("");
					return false;
				}
				if (dob[2].length != 4) 
				{
					alert("Please enter Year in Four Digit (yyyy).");
					$("#"+strInputDateId.id).val("");
					return false;
				}
			}
		}
	}
}

/**  ----- START ::  ########################## FINANCIAL ACCOUNTING -- COMMON FUNCTIONS   ##########################  **/
function showHideOBcolumnInTrialBalance(rowPosition) 
{
	if(document.getElementById("ob").checked == false)
	{
		var col = parseInt("2",10);
		col = col - 1;
		var tbl = document.getElementById("table");

        if (tbl != null) 
        {
            for (var i = 0; i < tbl.rows.length; i++) 
            {
            	if(i == rowPosition)
            	{
                	for (var j = 0; j < tbl.rows[i].cells.length; j++) 
                	{
                        tbl.rows[i].cells[j].style.display = "";
                        if (j == col)
                            tbl.rows[i].cells[j].style.display = "none";
                	}
                }
                if(i > rowPosition)
                {
                	for (var j = 0; j < tbl.rows[i].cells.length; j++) 
                	{
                        tbl.rows[i].cells[j].style.display = "";
                        if (j == col || (j == col+1))
                            tbl.rows[i].cells[j].style.display = "none";
                	}
                }
            }
        }
    }
	else if(document.getElementById("ob").checked == true)
	{
    	var col = parseInt("2",10);
		col = col - 1;
		var tbl = document.getElementById("table");
		if (tbl != null) 
		{
            for (var i = 0; i < tbl.rows.length; i++) 
            {
            	if(i == rowPosition)
            	{
                	for (var j = 0; j < tbl.rows[i].cells.length; j++) 
                	{
                        tbl.rows[i].cells[j].style.display = "";
                        if (j == col)
                            tbl.rows[i].cells[j].style.display = "display";
					}
				}
				if(i > rowPosition)
				{
                	for (var j = 0; j < tbl.rows[i].cells.length; j++) 
                	{
                        tbl.rows[i].cells[j].style.display = "";
                        if (j == col || (j == col+1))
							tbl.rows[i].cells[j].style.display = "display";
					}
				}
			}
		}
	}
}

function getAcctTypeFromAccountHead(rowNum, acctFieldId, debitAmtFieldId, creditAmtFieldId)
{
	if(rowNum.value=='')
	{
		rowNum=0;
	}
	var accountName = $("#"+acctFieldId+rowNum).val();
	
	if( $("#"+acctFieldId+rowNum).val() !="")
	{
		$.ajax({
			url : './getAccountTypeByAccountName',
			type:'GET',
			data : ({
				accountId : $("#"+acctFieldId+rowNum).val()
			}),
			cache:false,
			asynch:false,
			success : function(response) 
			{
				if(response !="")
				{
					var obj=jQuery.parseJSON(response);		
					$.each(obj, function(i, value) 
					{
						if(value.accountType!='CR')
						{
							document.getElementById(creditAmtFieldId+rowNum).value = "0.00";
							document.getElementById(debitAmtFieldId+rowNum).readOnly = false;
							document.getElementById(creditAmtFieldId+rowNum).readOnly = true;
							document.getElementById(creditAmtFieldId+rowNum).tabIndex = -1;
						}
						else
						{
							document.getElementById(debitAmtFieldId+rowNum).value = "0.00";
							document.getElementById(creditAmtFieldId+rowNum).readOnly = false;
							document.getElementById(debitAmtFieldId+rowNum).readOnly = true;
							document.getElementById(debitAmtFieldId+rowNum).tabIndex = -1;
						}
					});
				}
				else
				{
				}
			},
			error:function(transport)
			{
			}
		});
	}
	else
	{
	}
}

function enableDisableDrCrAmountFieldAsOnDrCrType(that, drcrtye, debitAmtFieldId, creditAmtFieldId, voucherType)
{
	rowNum=that.lang;
	if(rowNum.value=='')
	{
		rowNum=0;
	}
	
	var drcrtye = $("#"+drcrtye+rowNum).val();
	
	if(drcrtye!='Cr')
	{
		document.getElementById(creditAmtFieldId+rowNum).value = "0.00";
		document.getElementById(debitAmtFieldId+rowNum).readOnly = false;
		document.getElementById(creditAmtFieldId+rowNum).readOnly = true;
		document.getElementById(creditAmtFieldId+rowNum).tabIndex = -1;
	}
	else
	{
		document.getElementById(debitAmtFieldId+rowNum).value = "0.00";
		document.getElementById(creditAmtFieldId+rowNum).readOnly = false;
		document.getElementById(debitAmtFieldId+rowNum).readOnly = true;
		document.getElementById(debitAmtFieldId+rowNum).tabIndex = -1;
	}
}

//Get the Account Heads on change of Txn Mode *****
function getAccount(accountCategory,officeId) 
{
	showAjaxProcess();
	$.ajax({
		url : 'getAccountLedgerList.htm',
		dataType: "json",
		data : {
			accountCategory : accountCategory,
			officeId : officeId,
		},
		success : function(response) 
		{
			var data=response;
			var singleSelValue = "";
			var recCount = 0;
			var htmlSelect = '<option value="">' +labelDropdownTextSelect+ '</option>';
			var trHTML="";
			var htmlData="";
			
			$.each(data, function(i, value) 
			{
				recCount++;
				htmlData +='<option value="'+value.accountId+'">'+value.accountCode+" | "+value.accountName+'</option>';
				singleSelValue = value.accountId;
			});
			if(recCount == 1)
			{
				trHTML = htmlData;
				checkApplicableByAcctId(singleSelValue);
			}
			else
			{
				if(document.getElementById("currBalLabelId") != null)
					document.getElementById("currBalLabelId").style.display="none";
				trHTML = htmlSelect + htmlData;
			}

			$('#account').empty().append(trHTML); 
			hideAjaxProcess();
		}
	});

	if(typeof voucherType != 'undefined')
	{
		if(voucherType != "")
		{
			if(voucherType.toUpperCase() == "RECEIPT" || voucherType.toUpperCase() == "PAYMENT")
			{
				getProvVoucherNoBasedOnParams();
			}
		}
	}
}

// Get the Account Heads on change of Txn Mode *****
function getAccountType(accountCategory,officeId) 
{
	showAjaxProcess();
	$.ajax({
		url : 'getAccountLedgerList.htm',
		dataType: "json",
		data : {
			accountCategory : accountCategory,
			officeId : officeId,
		},
		success : function(response) 
		{
			var data=response;
			var singleSelValue = "";
			var recCount = 0;
			var htmlSelect = '<option value="">' +labelDropdownTextSelect+ '</option>';
			var trHTML="";
			var htmlData="";
			$.each(data, function(i, value) 
			{
				recCount++;
				htmlData +='<option value="'+value.accountId+'">'+value.accountCode+" | "+value.accountName+'</option>';
				singleSelValue = value.accountId;
			});
			if(recCount == 1)
			{
				trHTML = htmlData;
				checkApplicableByAcctId(singleSelValue);
			}
			else
			{
				if(document.getElementById("currBalLabelId") != null)
					document.getElementById("currBalLabelId").style.display="none";
				trHTML = htmlSelect + htmlData;
			}
			$('#account').empty().append(trHTML);
			
			if(accountCategory == "Bank")
			{
				$('#bank').show(200);
			}
			else
			{
				$('#bank').hide(200);
			}
			hideAjaxProcess();
		}
	});
}

function getVoucher(voucherNo,id) 
{
	$.ajax({
		url : 'getVoucherNumber.htm',
		dataType: "json",
		data : {
			voucherNo : voucherNo,
		},
		success : function(response) 
		{
			var data=response;
			if(data)
			{
				bootbox.alert(errMsgVchrNoExists);
				$("#"+id).val('');
			}
		}
	});
}

function getWorkDetails(project)
{
	$.ajax({
		url : 'getWorkDetails.htm',
		dataType: "json",
		data : {
			project : project,
		},
		success : function(response) 
		{
			var data=response;
			var trHTML='<option value="">' +labelDropdownTextSelect+ '</option>';
			$.each(data, function(i, value) 
			{
				trHTML +='<option value="'+value.workId+'">'+value.workCode+" | "+value.workName+'</option>';
			});
			$('#workId').empty().append(trHTML); 
		}
	});
}

function getDepartmentList(officeId,departmentId)
{
	var ofcType = "";
	$.ajax({
		url : 'getDepartmentList.htm',
		dataType: "json",
		data : {
			officeId : officeId,
		},
		success : function(response) 
		{
			var data=response;
			if(data.length > 0)
			{
				var trHTML='<option value="">' +labelDropdownTextSelect+ '</option>';
				$.each(data, function(i, value) 
				{
					trHTML +='<option value="'+value.departmentId+'">'+value.departmentCode+" | "+value.departmentName+'</option>';
					rptOfficeTypeName = value.officeTypeName;
					ofcType = rptOfficeTypeName;
				});
				$('#'+departmentId).empty().append(trHTML);
			}
			if(ofcType == "HO")
			{
				$('#'+departmentId).prop("disabled",false);
			}
			else
			{
				$('#'+departmentId).select2("val", "");
				$('#'+departmentId).prop("disabled",true);
			}
		}
	});
}

function checkAccount(id){
}

function getTransactionVoucher(voucherNo,id) 
{
	$.ajax({
		url : 'getTransactionVoucher.htm',
		dataType: "json",
		data : {
			voucherNo : voucherNo,
		},
		success : function(response) 
		{
			var data=response;
			if(data)
			{
				bootbox.alert(errMsgVchrNoExists);
				$("#"+id).val('');
			}
		}
	});
}

function generatePdf(voucherId)
{
	document.getElementById("printVoucherId").value=voucherId;
	$('#generatepdf').submit();
}

function generateProvVchrPdf(voucherId)
{
	if (typeof voucherId != 'undefined')
	{
		document.getElementById("voucherIdProv").value=voucherId;
		$('#generateProvPdf').submit();
	}
	else
	{
		bootbox.alert("Problem occured while opening the Voucher.");
		return false;
	}
}

function generateMoneyRecptPDF(voucherId,moneyRcptType)
{
	if (typeof voucherId != 'undefined')
	{
		document.getElementById("printReceiptVoucherId").value=voucherId;
		document.getElementById("hdnMoneyRcptType").value=moneyRcptType;
		
		$('#generateReceipt').submit();
	}
	else
	{
		alert("Problem occured while opening the Money Receipt.");
		return false;
	}
}
function generateInvoicePdf(invoiceId,invoiceType)
{
	if (typeof invoiceId != 'undefined')
	{
		document.getElementById("printInvoiceId").value=invoiceId;
		document.getElementById("hdnInvoiceType").value=invoiceType;
		$("#frmInvoicePDF").submit();
	}
	else
	{
		alert("Problem occured while opening the Invoice.");
		return false;
	}
}

/**
 * @author Tapan
 * @version 1.0
 * @since 05-01-2019
 * @param VoucherID
 * @description Delete (Inactive) the selected Voucher
 */
function showDeleteVoucherDiv(voucherType, voucherId, isFinalApproved)
{
	if(isFinalApproved == "Y")
	{
		bootbox.alert("This Voucher can not be deleted as Final No. is already generated. Please contact the Finance Administrator.");
		return false;
	}
	else
	{
		if(document.getElementById("deleteVchrDivId") != null)
		{
			document.getElementById("deleteVchrDivId").style.display = "block";
			$("html, body").animate({"scrollTop": "0px"}, 1000);
		}
		document.getElementById("hdnVoucherIdToDelete").value = voucherId;
		document.getElementById("hdnVoucherType").value = voucherType;
		
		$.ajax({
			url : 'getVoucherDetails.htm',
			type:'GET',
			data : ({
				voucherId : voucherId
			}),
			cache:false,
			asynch:false,
			success : function(response) 
			{
				if(response !="")
				{
					var resultArr = response.split("#");
					$("#vchrTypeLabelId").html(resultArr[0]);
					$("#provNoLabelId").html(resultArr[1]);
					$("#provDateLabelId").html(resultArr[2]);
					$("#vchrDrAmtLabelId").html(convertValueToINRFormat(resultArr[3]));
					$("#vchrCrAmtLabelId").html(convertValueToINRFormat(resultArr[4]));
					$("#vchrNarrationLabelId").html(resultArr[5]);
				}
			}
		});
	}
}

function deleteVoucher(voucherId)
{
	if(document.getElementById("txtDeleteReason") != null && document.getElementById("txtDeleteReason").value == "")
	{
		bootbox.alert("Please provide the Reason to delete this Voucher.");
		return false;
	}
	else
	{
		bootbox.confirm({
	        title : labelConfirm,
	        message : errMsgConfirmDelete,
	        buttons : {
	            confirm : {label : labelYes,className: "btn-success"},
	            cancel : {label : labelNo,className: "btn-warning"}
	        },
	        callback: function(result) 
	        {
	        	if (result == true) 
				{
					showAjaxLoader();
					$('#frmDeleteVoucher').submit();
				}
	        }
		})
	}
}

function closeVoucherDeleteModal()
{
	if(document.getElementById("deleteVchrDivId") != null)
	{
		document.getElementById("deleteVchrDivId").style.display = "none";
	}
}

/**
 * @author Tapan
 * @version 1.0
 * @since 05-05-2017
 * @param CompanyID, DivisionID, VoucherType and FinYear
 * @description Find the next voucher no. based on the parameters
 * 
 * @modify Biswa
 * @since 08-07-2018
 */

function getProvVoucherNoBasedOnParams()
{
	var companyId = $("#companyId").val();
	//var divisionId = $("#divisionId").val();
	var voucherTypeId = $("#voucherTypeId").val();
	var finYear = $("#finYear").val();
	var sectionId = $("#sectionId").val();
	var txnMode = $("#txnMode").val();
	var moduleCode = "FIN";
	//console.log("CompanyId====>>"+companyId+ "<< --- VoucherTypeId ==>"+voucherTypeId+ "<< --- FinYear ===>" +finYear);
	$.ajax({
		url : 'getProvVoucherNoBasedOnParams.htm',
		dataType: "json",
		data : {
			companyId : companyId,
			sectionId : sectionId,
			voucherTypeId : voucherTypeId,
			finYear : finYear,
			txnMode : txnMode,
			moduleCode : moduleCode,
		},
		success : function(response) 
		{
			$.each(response, function(index, value) 
			{
				//console.log("-- RESPONSE ---- nextVoucherNoGenerated ----->>"+value.nextVoucherNoGenerated);
				if(value.nextVoucherNoGenerated.indexOf("Not Configured") != -1)
				{
					$("#voucherNo").css({ 'color': 'red'});
					$("#voucherNo").css({ 'border': '1px solid red'});
					$("#voucherNo").css({ 'box-shadow': '0px 0px 3px red;'});
				}
				else
				{
					$("#voucherNo").css({ 'color': '#3a3a3a'});
					$("#voucherNo").css({ 'border': '1px solid #a5a2a2'});
				}
				$("#voucherNo").val(value.nextVoucherNoGenerated);
			});
		},
		error : function(errRespponse)
		{
			var data=errRespponse;
		}
	});
}

/**
 * @author Tapan
 * @version 1.0
 * @since 12-05-2017
 * @param 
 * @description Set the default value to Office & Division as per the Current User mapping
 */
function checkLoggedUserDeptDivision()
{
	var compOptions = document.getElementById("companyId");
	var deptOptions = document.getElementById("departmentId");
	//var divOptions = document.getElementById("divisionId");

	for(var i=0; i<compOptions.length; i++)
	{
		var optText = compOptions.options[i].text;
		if(compOptions.options[i].text == "IDCO - Bhubaneswar")
		{
			compOptions.options[i].selected = true;
		}
	}
	for(var j=0; j<deptOptions.length; j++)
	{
		if((deptOptions.options[j].text).indexOf("Finance") != -1)
		{
			deptOptions.options[j].selected = true;
		}
	}
	/*for(var k=0; k<divOptions.length; k++)
	{
		if((divOptions.options[k].text).indexOf("BCD-I") != -1 
			&& (divOptions.options[k].text).indexOf("BCD-II") == -1
			&& (divOptions.options[k].text).indexOf("BCD-III") == -1
		)
		{
			divOptions.options[k].selected = true;
		}
	}*/
	compOptions.disabled = true;
	deptOptions.disabled = true;
	//divOptions.disabled = true;
}

/**
 * @author Tapan
 * @version 1.0
 * @since 30-05-2017
 * @param 
 * @description Convert the amount to Words (Rupees & Paise)
 */

function convertAmountToWords(amountToConvert) 
{
	var amountInWordsOut = "";
	if(parseFloat(amountToConvert)==0)
	{
		amountInWordsOut = "";
	}
	else if(parseFloat(amountToConvert)>0)
	{
		var fraction = Math.round(getFraction(amountToConvert)*100);
		var paiseInWords  = "";
	
		var amountInWordsWithoutPaise = convertToNumber(amountToConvert);
		
		if(fraction > 0)
		{
			if(amountInWordsWithoutPaise.lastIndexOf(" and ") != -1)
			{
				amountInWordsWithoutPaise = amountInWordsWithoutPaise.replace(" and "," ");
			}
			paiseInWords = "and "+convertToNumber(fraction)+" Paise ";
		}
		amountInWordsOut = "Rupees " + amountInWordsWithoutPaise +" "+ paiseInWords +"only";
	}
	else
	{
		amountInWordsOut = "Incorrect amount !!!";
	}
	
	return amountInWordsOut;
}

/**
 * @author Tapan
 * @description Part of the Convert to Words function
 */
function getFraction(fractionAmount) 
{
	return fractionAmount % 1;
}

/**
 * @author Tapan
 * @description Part of the Convert to Words function
 */
function convertToNumber(amountInNum)
{
	/*if ((amountInNum < 0) || (amountInNum > 999999999)) 
	{
		return "Incorrect amount found !!!";
	}*/
	var amtInCrore = Math.floor(amountInNum / 10000000);	/* Crore */ 
	amountInNum -= amtInCrore * 10000000;

	var amtInLakhs = Math.floor(amountInNum / 100000);		/* lakhs */ 
	amountInNum -= amtInLakhs * 100000;
	
	var amtInThousands = Math.floor(amountInNum / 1000);	/* thousand */ 
	amountInNum -= amtInThousands * 1000;
	
	var amtInHundreds = Math.floor(amountInNum / 100);		/* Tens (deca) */ 
	amountInNum = amountInNum % 100;						/* Ones */ 
	
	var amtInTens = Math.floor(amountInNum / 10);
	var amtInOnes = Math.floor(amountInNum % 10);
	
	var result = "";

	if (amtInCrore>0)
	{
		result += (convertToNumber(amtInCrore) + " Crore");
	}
	if (amtInLakhs>0)
	{
		result += (((result=="") ? "" : " ") + 
		convertToNumber(amtInLakhs) + " Lakh");
	}
	if (amtInThousands>0) 
	{
		result += (((result=="") ? "" : " ") + convertToNumber(amtInThousands) + " Thousand");
	}

	if (amtInHundreds) 
	{
		result += (((result=="") ? "" : " ") + convertToNumber(amtInHundreds) + " Hundred");
	}

	var arrValuesInOnes = Array("", "One", "Two", "Three", "Four", "Five", "Six","Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen","Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen","Nineteen");
	var arrValuesInTens = Array("", "", "Twenty", "Thirty", "Fourty", "Fifty", "Sixty","Seventy", "Eighty", "Ninety");

	if (amtInTens>0 || amtInOnes>0) 
	{
		if (result!="")
		{
			result += " and ";
		}
		if (amtInTens < 2) 
		{
			result += arrValuesInOnes[amtInTens * 10 + amtInOnes];
		}
		else 
		{
			result += arrValuesInTens[amtInTens];
			if (amtInOnes>0) 
			{
				result += (" " + arrValuesInOnes[amtInOnes]);
			}
		}
	}

	if (result=="")
	{
		result = "zero";
	}
	return result;
}

function goBack()
{
	 window.history.back();
}

function goPrintScreen()
{
	var toPrint = document.getElementById('report');
	var popupWin = window.open('', 'PRINT', 'width=1000, height=600, location=no, left=125px, top=25px');
	popupWin.document.open();
	popupWin.document.write('<html>\n<head>\n');
	popupWin.document.write('<link rel="stylesheet" type="text/css" href="resources/stylesheets/financialaccounting/fa-common-report-style.css"/>\n');
	popupWin.document.write('<link rel="stylesheet" type="text/css" href="resources/stylesheets/financialaccounting/fa-common-styles.css"/>\n');
	popupWin.document.write('</head>\n<body onload="window.print();">');
	popupWin.document.write(toPrint.innerHTML);
	popupWin.document.write('</body>\n</html>\n');
	popupWin.document.close();
}

/**
 * @author Tapan
 * @version 1.0
 * @since 21-05-2018
 * @param 
 * @description View the documents attached (FAMS/Digitization Modules). Common to all to view the uploaded Doc.
 */

function viewUploadedDocumentNonFms(unqPrimaryKey, uploadedDocName, uploadedDocFullPath) 
{
	$("#hdnUnqPrimaryKey").val(unqPrimaryKey);
	$("#hdnUpldDocName").val(uploadedDocName);
	$("#hdnUpldDocNameAndPath").val(uploadedDocFullPath);
	$("#frmViewUploadedNonFmsDoc").submit();		
}

/**
 * @author Tapan
 * @version 1.0
 * @since 21-05-2018
 * @param 
 * @description Remove the attached document from Folder and update in DB as well. Common to all to view the uploaded Doc.
 */
function removeCurrentUploadedDoc(unqIdentityKey, uploadedDocName, uploadedDocFullPath, moduleCode, moduleCodeAddnl1, moduleCodeAddnl2)
{
	bootbox.confirm({
        title : labelConfirm,
        message : "Are you sure you want to remove the uploaded Document ?",
        buttons : {
            confirm : {label: labelYes,className: "btn-success"},
            cancel : {label: labelNo,className: "btn-warning"}
        },
        callback: function(result) 
        {
        	if (result == true) 
			{
        		if(typeof showAjaxLoader == "function")
        			showAjaxLoader();
        		removeAttachedNonFmsDocFromFolderAndDb(unqIdentityKey, uploadedDocName, uploadedDocFullPath, moduleCode, moduleCodeAddnl1, moduleCodeAddnl2);
			}
        }
	});
}

function removeAttachedNonFmsDocFromFolderAndDb(unqIdentityKey, uploadedDocName, uploadedDocFullPath, moduleCode, moduleCodeAddnl1, moduleCodeAddnl2)
{
	//if(document.getElementById("waitDiv") != null)
		//document.getElementById("waitDiv").style.display="block";
	$.ajax({
		url : 'removeAttachedNonFmsDocFromFolderAndDb.htm',
		dataType: "json",
		data : {
			"unqIdentityKey" : unqIdentityKey,
			"upldDocName" : uploadedDocName,
			"upldDocNameAndPath" : uploadedDocFullPath,
			"moduleCode" : moduleCode,
			"moduleCodeAddnl1" : moduleCodeAddnl1,
			"moduleCodeAddnl2" : moduleCodeAddnl2,
		},
		success : function(response)
		{
			if(typeof hideAjaxLoader == "function")
    			hideAjaxLoader();

			var data=response;
			
			$.each(data, function(index, value) 
			{
				if(value.result.toUpperCase() == "SUCCESS")
				{
					if(document.getElementById("uploadNewDocDiv") != null)
						document.getElementById("uploadNewDocDiv").style.display="";
					if(document.getElementById("SuccMsgDiv") != null)
						document.getElementById("SuccMsgDiv").style.display="";
					if(document.getElementById("ErrMsgDiv") != null)
						document.getElementById("ErrMsgDiv").style.display="none";
					if(document.getElementById("UploadedDocNameDiv") != null)
						document.getElementById("UploadedDocNameDiv").style.display="none";
				}
				else if(value.result.toUpperCase() == "FAILURE")
				{
					if(document.getElementById("uploadNewDocDiv") != null)
						document.getElementById("uploadNewDocDiv").style.display="none";
					if(document.getElementById("SuccMsgDiv") != null)
						document.getElementById("SuccMsgDiv").style.display="none";
					if(document.getElementById("ErrMsgDiv") != null)
						document.getElementById("ErrMsgDiv").style.display="";
					if(document.getElementById("UploadedDocNameDiv") != null)
						document.getElementById("UploadedDocNameDiv").style.display="";
				}
				else
				{
					if(document.getElementById("uploadNewDocDiv") != null)
						document.getElementById("uploadNewDocDiv").style.display="none";
					if(document.getElementById("SuccMsgDiv") != null)
						document.getElementById("SuccMsgDiv").style.display="none";
					if(document.getElementById("ErrMsgDiv") != null)
						document.getElementById("ErrMsgDiv").style.display="none";
					if(document.getElementById("UploadedDocNameDiv") != null)
						document.getElementById("UploadedDocNameDiv").style.display="";
				}
			});
		},
		error:function(func)
		{
			if(document.getElementById("uploadNewDocDiv") != null)
				document.getElementById("uploadNewDocDiv").style.display="none";
		}
	});
}

/* ################################################################################################################################################## */

	function getFinYearStartEndDates(finYear,pageReloadFlag)
	{
	debugger
		$.ajax({
			url : contextPath + '/common/api/getFinYearStartEndDates',
			type:'GET',
			data : ({
				year : finYear
			}),
			cache:false,
			asynch:false,
			success : function(response) 
			{
				//alert(response);
				if(response !="")
				{
					var obj = response;
					if(pageReloadFlag != "Y")
					{
						$("#startDate").val(obj.startDate);
						$("#endDate").val(obj.endDate);
					}
				}
			}
		});
		
	}

function checkItemAvailabilityByAcctId(accountId)
{
	$.ajax({
		url : './getItemApplicableFromAccountHead.htm',
		type:'GET',
		dataType: "json",
		data : ({
			accountId : accountId,
		}),
		cache:false,
		asynch:false,
		success : function(response) 
		{
			applicable = "";
			var openingBalance = null;
			var closingBalance = null;
			var obType = null;
			var cbType = null;
			if(response !="")
			{
				var obj=response;
				openingBalance = obj.openingBalance;
				closingBalance = obj.closingBalance;
				if(obj.virtualAccount == 'Y')
				{	
					applicable = applicable+"VirtualAccount,";
				}
				if(obj.depreciation == 'Y')
				{	
					applicable = applicable+"Depreciation,";
				}
				if(obj.costCenter == 'Y')
				{
					applicable = applicable+"Cost Center,";
				}
				if(obj.project == 'Y')
				{
					applicable = applicable+"Project,";
				}
				if(obj.work == 'Y')
				{
					applicable = applicable+"Work,";
				}
				if(obj.party == 'Y')
				{	
					applicable = applicable+"Party,";
				}
				if(obj.budget == 'Y')
				{
					applicable = applicable+"Budget,";
				}
			}
			if(openingBalance > 0)
			{
				obType = "(Dr)";
			}
			else if(openingBalance < 0)
			{
				obType = "(Cr)";
				openingBalance = 0 - openingBalance;
			}
			else
			{
				obType = "";
				openingBalance = "0.00";
			}

			if(closingBalance > 0)
			{
				cbType = "(Dr)";
			}
			else if(closingBalance < 0)
			{
				cbType = "(Cr)";
				closingBalance = 0 - closingBalance;
			}
			else
			{
				cbType = "";
				closingBalance = "0.00";
			}
			applicable = applicable.slice(0, -1) + "& Opening Balance: "+openingBalance +" "+ obType+", Closing Balance: "+closingBalance +" "+ cbType;
			return applicable;
		}
	});
}

/**
* @author Tapan
* @version 1.0
* @since 13-06-2017
* @param Virtual Account Flag
* @description Shows/Hides the Instrument details labels based on the input value.
*/
function checkForVirtualAcAndShowHideReqdFieldLabels(isVirtualAccount)
{
	if(isVirtualAccount=="Y")
	{
		if(document.getElementById("txnTypeNotRqdLabelId") != null)
		{
			document.getElementById("txnTypeNotRqdLabelId").style.display = "block";
			document.getElementById("txnNoNotRqdLabelId").style.display = "block";
			document.getElementById("txnDateNotRqdLabelId").style.display = "block";
	
			document.getElementById("txnTypeRqdLabelId").style.display = "none";
			document.getElementById("txnNoRqdLabelId").style.display = "none";
			document.getElementById("txnDateRqdLabelId").style.display = "none";
		}
	}
	else
	{
		if(document.getElementById("txnTypeNotRqdLabelId") != null)
		{
			document.getElementById("txnTypeNotRqdLabelId").style.display = "none";
			document.getElementById("txnNoNotRqdLabelId").style.display = "none";
			document.getElementById("txnDateNotRqdLabelId").style.display = "none";
	
			document.getElementById("txnTypeRqdLabelId").style.display = "block";
			document.getElementById("txnNoRqdLabelId").style.display = "block";
			document.getElementById("txnDateRqdLabelId").style.display = "block";
		}
	}
}

/**
* @author Tapan
* @version 1.0
* @since 01-07-2017
* @param Table ID
* @description Returns the count of rows in a table except the Header Row
*/
function getDataTableRowLengthExceptTHcells(tableId)
{
	var totalRowCount = 0;
	var rowCount = 0;

	var table = document.getElementById(tableId);
	var rows = table.getElementsByTagName("tr")
	for (var i = 0; i < rows.length; i++) 
	{
		totalRowCount++;
		if (rows[i].getElementsByTagName("td").length > 0) 
		{
			rowCount++;
		}
	}
	return rowCount;
}

/**
* @author Tapan
* @version 1.0
* @since 01-07-2017
* @param Table ID
* @description Returns the count of rows in a table except the Header Row
*/
function validateIfscCodeFormat(fieldObj)
{
	var ifscCode = document.getElementById(fieldObj).value;
	var reg = /^[A-Za-z]{4}[0-9]{6,7}$/;
	
	if (ifscCode.match(reg)) 
	{
		return true;
	} 
	else 
	{
		return false;
	}
}

/**  ----- END ::  ########################## FINANCIAL ACCOUNTING -- COMMON FUNCTIONS   ##########################  **/

/**
 * @author Tapan
 * @version 1.0
 * @since 10-05-2017
 * @param Date Field ClassName
 * @description Generate the new Date Picker
 */
$(function() 
{
	$('.jqueryNDatePicker').datepick({
		dateFormat: 'dd/mm/yyyy', 
		showOnFocus: true, 
		showTrigger: '<button type="button" class="trigger">' + '<i class="fa fa-calendar"></i></button>'
	});	
/*			
	$('.jqueryNDatePicker').datepick({
		dateFormat: 'dd/mm/yyyy', 
		showOnFocus: false, 
		showTrigger: '<button type="button" class="trigger">' + '<i class="fa fa-calendar"></i></button>'
	});	
*/
});

/**
 * @author Tapan
 * @version 1.0
 * @since 08-05-2017
 * @param FormID
 * @description Reset the form elements
 */
function resetFormElements(formId)
{
	var totalElementsLength = document.getElementById(formId).elements.length;

	console.log("totalElementsLength =============>> "+totalElementsLength);

	for(i=0; i<totalElementsLength; i++)
	{
		var formElementObj = document.getElementById(formId).elements[i];

		if(formElementObj.type.toUpperCase().indexOf("TEXT") != -1)
		{
			if(document.getElementById(formElementObj.id) != null)
				document.getElementById(formElementObj.id).value = "";
		}
		if(formElementObj.type.toUpperCase().indexOf("SELECT") != -1)
		{
			if(document.getElementById(formElementObj.id) != null)
			{
				var selectId = document.getElementById(formElementObj.id);
				var optionsText = selectId.options[0].text;
	
				if(optionsText.toUpperCase().indexOf("SELECT") != -1)
					selectId.options[0].selected = true;
				else if(optionsText.toUpperCase().indexOf("NONE") != -1)
					selectId.options[0].selected = true;
				else if(optionsText.toUpperCase().indexOf("ALL") != -1)
					selectId.options[0].selected = true;
				else if(optionsText.toUpperCase().indexOf("ACTIVE") != -1)
					selectId.options[0].selected = true;
				else
				{
					formElementObj.options.length = 0;
					var option = document.createElement("option");
					option.text = labelDropdownTextSelect;
					formElementObj.add(option,formElementObj[0]);
					selectId.options[0].selected = true;
				}
			}
		}
	}
}

function getTxnItemDetailsOnChange(account)
{
	document.getElementById("accGrpName").innerHTML="";
	//document.getElementById("accSubGrpName").innerHTML="";
	document.getElementById("accSchName").innerHTML="";
	//document.getElementById("accSubSchName").innerHTML="";
	document.getElementById("accGLHead").innerHTML="";
	if(account != ""){
		$.ajax({
			url : 'getAccountLedgerDetails.htm',
			data : {
				account : account,
			},
			success : function(response) 
			{
				var details=response.split("<###>");
				document.getElementById("accGrpName").innerHTML=details[1];
				//document.getElementById("accSubGrpName").innerHTML=details[3];
				document.getElementById("accSchName").innerHTML=details[6];
				//document.getElementById("accSubSchName").innerHTML=details[7];
				document.getElementById("accGLHead").innerHTML=details[4];
			}
		});
	}
}
function toUppercase(that) 
{
	that.value = that.value.toUpperCase();
}

//function validateEmail(emailField)
//{
//	var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
//
//if (reg.test(emailField.value) == false)
//{
//	bootbox.alert("Invalid Email Address ");
//		var result = emailField.value.replace(/[a-zA-Z!&#@^/#+()$~%&\\\|\.''"":;*?<>{}]/g,'');
//		emailField.value =result;
//		emailField.value = '';
//		emailField.focus();
//		return false;
//}
function validateEmail(email) {
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}


function validE(e) {
	debugger;
	const patt = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	return patt.test(e);
  }

/*function AllowIFSC() 
{
	var ifsc = document.getElementById('ifsc').value;
	
	var reg= /[A-Z|a-z]{4}/;
	if (ifsc.match(reg)) 
	{
		return true;
	}
	else 
	{
		bootbox.alert("Incorrect IFSC code is entered.");
		document.getElementById("ifsc").value="";
		return false;
	}
}*/

function AllowPASSPORT() 
{
	var passport = document.getElementById('passportNo').value;
	var reg = /^[A-PR-WY][1-9]\d\s?\d{4}[1-9]$/ig;
	 
	if (passport.match(reg)) 
	{
		return true;
	}
	else 
	{
		bootbox.alert("You Entered Wrong Passport No");
		document.getElementById('passportNo').value="";
		return false;
	}
}

function checkUID(uid) 
{
	var id = document.getElementById('adharNo').value;
	var adha = id.length;

	if (adha!= 12) 
	{
		bootbox.alert("Please enter 12 digit Adhar No.");
		document.getElementById('adharNo').value="";
		return false;
	} 
	else 
	{
		return true;
	}
}

//added by surya for payband by commission type
function getLookUpList(commissionType,id)
{
	$.ajax({
		url : './hrms-getLookUpList.htm',
		dataType: "json",
		type:'GET',
		data: ({
			commissionType : commissionType,
		}),
		cache:false,
		asynch:false,
		success : function(response) 
		{
			var data=response;
			var html="<option value=''>- Select -</option>";
			$.each(data, function(index, value) 
			{
				html=html+"<option value="+value.code+">"+value.code+"</option>";
			});
			$('#'+id).empty().append(html);
		}
	});
		
}

function getLookUpListValue(commissionType,id)
{
	$.ajax({
		url : './hrms-getLookUpListValue.htm',
		dataType: "json",
		type:'GET',
		data: ({
			commissionType : commissionType,
		}),
		cache:false,
		asynch:false,
		success : function(response) 
		{
			var html="<option value=''>- Select -</option>";
			if(response != "")
			{
				var data=response;
				$.each(data, function(index, value) 
				{
					html=html+"<option value="+value.code+">"+Math.round(value.price) +" - ("+value.code+")</option>";
				});
				$('#'+id).empty().append(html);
			}
			else
			{
				$('#'+id).empty().append(html);
				bootbox.alert("No Grade Pay defined under this Pay Commission.");
				return false;
			}
		}
	});
}

function commonFileTypeChecker(inputId, fileType) 
{
    // Get the file upload control file extension
	
	var file = document.querySelector('#'+inputId);
	
	var ext = file.files[0].name.split('.');
	if(ext.length > 2) {
		bootbox.alert("File not allowed with multiple extensions !"); 
		file.value = null;
	} 
	else
	{
     var extn = $('#' + inputId).val().split('.').pop().toLowerCase();
    
     if (extn != '') 
     {
		//debugger;        
		// Create array with the files extensions to upload
        var fileListToUpload;
        if (parseInt(fileType) == 1)
            fileListToUpload = new Array('pdf');
        else if (parseInt(fileType) == 2)
            fileListToUpload = new Array('gif', 'jpg', 'jpeg', 'png');
        else if (parseInt(fileType) == 3)
        	 fileListToUpload = new Array('xlsx', 'xls');
        else
            fileListToUpload = new Array('pdf');

        //Check the file extension is in the array.               
        var isValidFile = $.inArray(extn, fileListToUpload);

        // isValidFile gets the value -1 if the file extension is not in the list.  
        if (isValidFile == -1) 
        {
            if (parseInt(fileType) == 1)
            	 bootbox.alert('Please select a valid file of type pdf.');
            else if (parseInt(fileType) == 2)
            	 bootbox.alert('Please select a valid file of type gif/jpeg/jpg/png.');
            else if (parseInt(fileType) == 3)
           	 	 bootbox.alert('Please select a valid file of type xlsx/xsl.');
            else
            	 bootbox.alert('Please select a valid pdf file only');
            $('#' + inputId).replaceWith($('#' + inputId).val('').clone(true));
        }
        else 
        {
			// Restrict the file size to 5 MB.
            if ($('#' + inputId).get(0).files[0].size > (1024 * 1024 * 500)) {
            		bootbox.alert('File size should not exceed 500 KB.');
                $('#' + inputId).replaceWith($('#' + inputId).val('').clone(true));
            }
/*
            else if(typeof ($('#' + inputId).get(0).files[0].name) != 'undefined')
            {
	            if ($('#' + inputId).get(0).files[0].name.length > 100) {
	            		bootbox.alert('File Name should be maximum 100 Characters');
	                $('#' + inputId).replaceWith($('#' + inputId).val('').clone(true));
	            }
            }
*/
            else
                return true;
        }
    }
    else
        return true;
	}
}
function getProvDateAsPerFinyear(currYear,prvsYear)
{
	var previousYear;
	var nextYear;
	var year;
	if(currYear==prvsYear){
		document.getElementById("voucherDate").value=getCurrentDateAsDDMMYYYYHyphenSeparated();
		document.getElementById("voucherDate").readOnly='';
	}else{
		year=prvsYear.split('-');
		previousYear = year[0];
		nextYear = parseInt(previousYear)+1;
		document.getElementById("voucherDate").value = '31/03/'+nextYear;
		document.getElementById("voucherDate").readOnly='readOnly';
	}
	
	if(document.getElementById("sectionId").value != "0"){
		getProvVoucherNoBasedOnParams();
	}
}
function getInvoiceDateAsPerFinyear(currYear,prvsYear,invoiceType)
{
	var previousYear;
	var nextYear;
	var year;
	if(currYear==prvsYear){
		if(invoiceType=="R"){
			document.getElementById("collectionDate").value=getCurrentDateAsDDMMYYYYHyphenSeparated();
			document.getElementById("provisionalDate").value=getCurrentDateAsDDMMYYYYHyphenSeparated();
		}
		document.getElementById("invoiceDate").value=getCurrentDateAsDDMMYYYYHyphenSeparated();
	}else{
		year=prvsYear.split('-');
		previousYear = year[0];
		nextYear = parseInt(previousYear)+1;
		if(invoiceType=="R"){
			document.getElementById("collectionDate").value = '31/03/'+nextYear;
			document.getElementById("provisionalDate").value = '31/03/'+nextYear;
		}
		document.getElementById("invoiceDate").value = '31/03/'+nextYear;
	}
	//alert(invoiceType);
	if(invoiceType=="R"){
		$("#invoiceType").val(invoiceType);
		$("#finYear").val(prvsYear);
		getInvoiceNoBasedOnParams();
	}
}
function getInvoiceNoBasedOnParams()
{
	var invoiceType = $("#invoiceType").val();
	var finYear = $("#finYear").val();
	$.ajax({
		url : 'getInvoiceNoBasedOnParams.htm',
		dataType: "json",
		type:'GET',
		data : {
			invoiceType : invoiceType,
			finYear : finYear,
		},
		success : function(response) 
		{
			$.each(response, function(index, value) 
			{
				if(value.nextInvoiceNoGenerated.indexOf("Not Configured") != -1)
				{
					$("#invoiceNo").css({ 'color': 'red'});
					$("#invoiceNo").css({ 'border': '1px solid red'});
					$("#invoiceNo").css({ 'box-shadow': '0px 0px 3px red;'});
				}
				else
				{
					$("#invoiceNo").css({ 'color': '#3a3a3a'});
					$("#invoiceNo").css({ 'border': '1px solid #848484'});
				}
				$("#invoiceNo").val(value.nextInvoiceNoGenerated);
				if(document.getElementById("sectionId").value != "0"){
					getProvVoucherNoBasedOnParams();
				}
			});
		},
		error : function(errRespponse)
		{
			var data=errRespponse;
		}
	});
}

/**
 * @author Bikash
 * @version 1.0
 * @since 10-06-2019
 * @param invoiceID
 * @description Delete (Inactive) the selected Invoice
 */
function showDeleteInvoiceDiv(invoiceId, invoiceType)
{
	if(document.getElementById("deleteInvoiceDivId") != null)
	{
		document.getElementById("deleteInvoiceDivId").style.display = "block";
		$("html, body").animate({"scrollTop": "0px"}, 1000);
	}
	document.getElementById("hdnInvoiceIdToDelete").value = invoiceId;
	document.getElementById("hdnInvoiceTypeToDelete").value = invoiceType;

	$.ajax({
		url : 'getInvoiceDetails.htm',
		type:'GET',
		data : ({
			invoiceId : invoiceId
		}),
		cache:false,
		asynch:false,
		success : function(response) 
		{
			if(response !="")
			{
				var resultArr = response.split("#");
				$("#partyLabelId").html(resultArr[0]);
				$("#invoiceNoLabelId").html(resultArr[1]);
				$("#invoiceDateLabelId").html(resultArr[2]);
				$("#invoiceAmtLabelId").html(convertValueToINRFormat(resultArr[3]));
				$("#invoiceNarrationLabelId").html(resultArr[4]);
			}
		}
	});
}

function deleteInvoice(invoiceId)
{
	if(document.getElementById("txtDeleteReason") != null && document.getElementById("txtDeleteReason").value == "")
	{
		bootbox.alert("Please provide the Reason to delete this Invoice.");
		return false;
	}
	else
	{
		bootbox.confirm({
	        title : labelConfirm,
	        message : errMsgConfirmDelete,
	        buttons : {
	            confirm : {label : labelYes,className: "btn-success"},
	            cancel : {label : labelNo,className: "btn-warning"}
	        },
	        callback: function(result) 
	        {
	        	if (result == true) 
				{
					showAjaxLoader();
					$('#frmDeleteInvoice').submit();
				}
	        }
		})
	}
}

function closeInvoiceDeleteModal()
{
	if(document.getElementById("deleteInvoiceDivId") != null)
	{
		document.getElementById("deleteInvoiceDivId").style.display = "none";
	}
}

/**
 * @author Chidananda
 * @version 1.0
 * @since 01-08-2019
 * @param date
 * @description convert GMT Date to dd/mm/yyyy
 */
function convertGMTDateToNormalDate(strDate) {
    var date = new Date(strDate),
    	yr = date.getFullYear(),
        mnth = ("0" + (date.getMonth()+1)).slice(-2),
        day  = ("0" + date.getDate()).slice(-2);
       //  hours  = ("0" + date.getHours()).slice(-2);
       // minutes = ("0" + date.getMinutes()).slice(-2);
     return [ day,mnth,yr].join("/");
}

/**
 * @author Chidananda
 * @version 1.0
 * @since 01-08-2019
 * @param date
 * @description calculate last day based on selected date
 */
function getLastDateFromSelectedDate(strDate) {
    var date = new Date(strDate),
    	yr = date.getFullYear(),
        mnth = date.getMonth();
    	var lastDay = new Date(yr, mnth + 1, 0);
    
     return convertGMTDateToNormalDate(lastDay);
}