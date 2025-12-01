function subTotalCalculation(rowid,rowid1){
	debugger;
	var vQty=0;
	var vRate=0;
	var vsubTotal=0;
	var vtotal=0;
	var vDisc=0;
	var availDiscount=0;
	var gstAmount=0;
	var vgst=0;
	var	vCESSamt = 0;
	var vtotalsum1 =0;
	let rowCount = document.getElementById("tbd").rows.length; // Ensure rowCount is defined

    for (let i = 0; i < rowCount; i++) {
	vQty=document.getElementById("quantity"+i).value;
	vRate=document.getElementById("price"+i).value;
	vtotal = $("#total" + i).val();
	vgst= $("#gst" +i).val();
	var vTotal =0;
	vDisc=document.getElementById("discount" + i).value;
	if(parseFloat(vQty) > 0 && parseFloat(vRate) > 0)
	{
		vTotal = (parseFloat(vQty)*parseFloat(vRate));
		if(parseFloat(vDisc) > 0){
			vDiscamt = ((parseFloat(vTotal)*parseFloat(vDisc))/100);
			var vdisTotal = (parseFloat(vTotal)-parseFloat(vDiscamt));
			 var vCESSamt = Math.round(parseFloat(vdisTotal) * parseFloat(vgst)/ 100);
			availDiscount += parseFloat(vDiscamt);
		}
	}    
	
	
	
	
	if (parseFloat($("#gst" +i).val()) > 0 && parseFloat(vtotal) > 0) {
		
		
		 if(parseFloat(vDisc) > 0){
			 var vtotal1 = parseFloat(vQty)*parseFloat(vRate)
			 vDiscamt = ((parseFloat(vtotal1)*parseFloat(vDisc))/100);
			var vdisTotal = (parseFloat(vtotal1)-parseFloat(vDiscamt));
			vTotal= parseFloat(vdisTotal);
		}else{
			vTotal = (parseFloat(vQty)*parseFloat(vRate))	
		} 
	    var vCESSamt = Math.round((parseFloat(vTotal) * parseFloat(vgst)/ 100));
	    gstAmount += vCESSamt; // No need to parse vCESSamt again since it's already a number
	}

	
	
	
	var vtotalsum = $("#total" + i).val();
	vtotalsum1 += parseFloat(vtotalsum);
	vsubTotal += (parseFloat(vQty)*parseFloat(vRate));
	vtotal +=parseFloat(vtotal)
    }
   
    $("#availDiscount").val(parseFloat(availDiscount).toFixed(2));
    $("#gstAmount1").val(parseFloat(gstAmount).toFixed(2));
    
    $("#totalAmount").val(parseFloat(vtotalsum1).toFixed(2));
    $("#subTotal").val(parseFloat(vsubTotal).toFixed(2));
	$("#dueAmount").val(parseFloat(vtotalsum1).toFixed(2));
	
	 $("#savailDiscount").val(parseFloat(availDiscount).toFixed(2));
    $("#sgstAmount1").val(parseFloat(gstAmount).toFixed(2));
    
    $("#stotalAmount").val(parseFloat(vtotalsum1).toFixed(2));
    $("#ssubTotal").val(parseFloat(vsubTotal).toFixed(2));
	//$("#dueAmount").val(parseFloat(vtotalsum1).toFixed(2));
	
/*    document.getElementById("sgstAmount1").innerText = parseFloat(gstAmount).toFixed(2);
    document.getElementById("savailDiscount").innerText = parseFloat(availDiscount).toFixed(2);
	
    document.getElementById("stotalAmount").innerText = Math.round(parseFloat(vtotalsum1)).toFixed(2);
    document.getElementById("ssubTotal").innerText = parseFloat(vsubTotal).toFixed(2);
*/
    document.getElementById("totalAmount").value=parseFloat(vtotalsum1).toFixed(2);
	document.getElementById("subTotal").value=parseFloat(vsubTotal).toFixed(2);
}




function total_amount(rowId,rowId2){
	debugger;
	var vQty=0;
	var vRate=0;
	var vDisc=0;
	var vTotal=0;
	var vDiscamt=0;         
	var vSGST=0;
	var vCGST=0;
	var vIGST=0;
	var vCESS=0;
	var vSGSTamt=0;
	var vCGSTamt=0;
	var vIGSTamt=0;
	vQty=document.getElementById("quantity"+rowId).value;
	vRate=document.getElementById("price"+rowId).value;
	vDisc=document.getElementById("discount" + rowId).value;
	if(parseFloat(vQty) > 0 && parseFloat(vRate) > 0)
	{
		vTotal = (parseFloat(vQty)*parseFloat(vRate));
		if(parseFloat(vDisc) > 0){
			vDiscamt = Math.round((parseFloat(vTotal)*parseFloat(vDisc))/100);
			vTotal = Math.round(parseFloat(vTotal)-parseFloat(vDiscamt));
		}
		document.getElementById("price"+rowId).value=parseFloat(vRate).toFixed(2);
		document.getElementById("total"+rowId).value=parseFloat(vTotal).toFixed(2);
	}
	vSGST=document.getElementById("sgst"+rowId).value;
	vCGST=document.getElementById("cgst"+rowId).value;
	vIGST=document.getElementById("igst"+rowId).value;
	vgst= document.getElementById("gst" + rowId).value;
	
	
	/* if(parseFloat(vSGST) > 0 && parseFloat(vTotal) > 0){
		vSGSTamt = Math.round((parseFloat(vTotal)*parseFloat(vSGST))/100);
		vTotal = (parseFloat(vTotal)-parseFloat(vSGSTamt));
		document.getElementById("total"+rowId).value = vTotal.toFixed(2);
	}
	if(parseFloat(vCGST) > 0 && parseFloat(vTotal) > 0){
		vCGSTamt = Math.round((parseFloat(vTotal)*parseFloat(vCGST))/100);
		vTotal = (parseFloat(vTotal)-parseFloat(vCGSTamt));
		document.getElementById("total"+rowId).value = vTotal.toFixed(2);
	}
	if(parseFloat(vIGST) > 0 && parseFloat(vTotal) > 0){
		vIGSTamt = Math.round((parseFloat(vTotal)*parseFloat(vIGST))/100);
		vTotal = (parseFloat(vTotal)-parseFloat(vIGSTamt));
		document.getElementById("total"+rowId).value = vTotal.toFixed(2);
	} */
	if(parseFloat(vgst) > 0 && parseFloat(vTotal) > 0){
		
		if(vDisc > 0){
			vDiscamt = ((parseFloat(vTotal)*parseFloat(vDisc))/100);
			/* vTotal = (parseFloat(vTotal)-parseFloat(vDiscamt)); */
			vCESSamt = Math.round((parseFloat(vTotal)*parseFloat(vgst))/100);
			vTotal = Math.round(parseFloat(vTotal) + parseFloat(vCESSamt));
		}else{
			vCESSamt = Math.round((parseFloat(vTotal)*parseFloat(vgst))/100);
			vTotal = Math.round(parseFloat(vTotal) + parseFloat(vCESSamt));
		}
		
		
		
		document.getElementById("total"+rowId).value = vTotal.toFixed(2);
	}
	
	subTotalCalculation(rowId,rowId2)
}
async function getProductPrice(url,rowid){
	var productName = $("#productName" + rowid).val()
		 if (productName !== "") {
		        try {
		            let response = await fetch(`${url}/inv/getProductPrice?productId=`+productName, {
		                method: 'GET',
		                cache: 'no-cache'
		            });

		            if (response.ok) {
		                let details = await response.json();
		                document.getElementById("price" + rowid).value = details.productPrice;  
		                
		            } else {
		            	bootbox.alert("No Details Found");
		            	return false;
		            }
		        } catch (error) {
		        	bootbox.alert("No Details Found");
		        	return false
		        }
		    } else {
		    	bootbox.alert("No Details Found");
		    	return false
		    }
}






async function getGstTaxBreakupDetails(url,rowid) {
	var gstMappingId = $("#hsnCode" + rowid).val();
    debugger;
  if( document.getElementById("partyGst").value != "" &&  document.getElementById("topartyGst").value != ""){ 
    if (gstMappingId !== "") {
        try {
            let response = await fetch(`${url}/inv/getGstTaxBreakupDetails?gstId=`+gstMappingId, {
                method: 'GET',
                cache: 'no-cache'
            });
        /*   var a  =(document.getElementById("partyGst").value).substr(0,2);
          var c =(document.getElementById("topartyGst").value).substr(0,2); */
            if (response.ok) {
                let details = await response.json();
                if((document.getElementById("partyGst").value).substr(0,2) == (document.getElementById("topartyGst").value).substr(0,2)) {


                	document.getElementById("gst" + rowid).value = details.gst;
                    document.getElementById("sgst" + rowid).value = details.sgst;
                    document.getElementById("cgst" + rowid).value = details.cgst;
                    document.getElementById("igst" + rowid).value = 0;
                }else{
                	document.getElementById("gst" + rowid).value = details.gst;
                    document.getElementById("sgst" + rowid).value = details.sgst;
                    document.getElementById("cgst" + rowid).value = details.cgst;
                    document.getElementById("igst" + rowid).value = details.igst;
                }           
                total_amount(rowid,0)
            } else {
            	bootbox.alert("No Details Found");
            }
        } catch (error) {
        	bootbox.alert("No Details Found");
        }
    } else {
    	bootbox.alert("No Details Found");
    }
  }else{
	  bootbox.alert("Please check the selected State for the Party.");
	  $("#hsnCode" + rowid).val("");
  }
}

function getOneMonthAfterDate(that) {
    var dueIssueDate = that;
    const newDate = new Date(dueIssueDate);
    newDate.setMonth(newDate.getMonth() + 1);

    // Extract the year, month, and day
    const year = newDate.getFullYear();
    const month = String(newDate.getMonth() + 1).padStart(2, '0'); // Months are 0-indexed
    const day = String(newDate.getDate()).padStart(2, '0');

    // Format as yyyy-dd-mm
    const formattedDate = `${year}-${month}-${day}`;

    // Set the value of the input field
    $("#due-date").val(formattedDate);
}


