<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.alert {
	width: 100%;
	font-size: 14px;
	margin-left: 0px;
	text-align: center;
	border-radius: 4px;
	padding: 5px 3px 5px 0;
}
.alert-success {
	color: #0c8e0c;
	background-color: #c3f3af;
	border-color: #61d132 !important;
}
.alert-danger {
	color: #d91b1b;
	background-color: #ffc4c4;
	border-color: #fe8ba3 !important;
}
.close {
	float: right;
	opacity: 0.7;
	font-size: 25px;
	margin-right: -10px;
	line-height: 10px;
	color: #ff0000 !important;
	background-color: transparent !important;
}
#messageDiv{
   /* position: fixed;*/
    top: 0;
    left: 0;
    z-index: 9999;
    width: 100%;
    height: 100%;
    overflow-x: hidden;
    overflow-y: auto;
    outline: 0;
    background: #00000000;
}
.alert {
    margin: 0 auto;
	max-width: 900px;
}
#messageDiv button {
	width: 15px;
    height: 15px;
    color: black;
    padding: 0;
    border: none;
    line-height: 0px;
    background: none;
    font-weight: 600;
    text-align: center;
    position: absolute;
    top: 5px;
    right: 20px;
}

#messageDiv .emptySpan {
	width: 03%;
}
#messageDiv .msgTextSpan {
	width: 94%;
	font-weight: 600;
}
#messageDiv .closeBtnSpan {
	width: 03%;
	text-align: right;
}

</style>


<c:if test="${not empty success_msg}">
	<div id="messageDiv" class="">
	    <div class="alert alert-success">
            <span class="emptySpan">&nbsp;</span>
            <span class="msgTextSpan">${success_msg}</span>
            <span class="closeBtnSpan"><button type="button" class="close" data-dismiss="alert" aria-hidden="true" onclick="hide();">x</button></span>
	    </div>
	</div>
</c:if>

<c:if test="${not empty error_msg}">
	<div id="messageDiv" class="">
	    <div class="alert alert-danger">
            <span class="emptySpan">&nbsp;</span>
            <span class="msgTextSpan">${error_msg}</span>
            <span class="closeBtnSpan"><button type="button" class="close" data-dismiss="alert" aria-hidden="true" onclick="hide();">x</button></span>
	    </div>
	</div>
</c:if>

<c:if test="${not empty err_msg}">
	<div id="messageDiv" class="">
		<div class="alert alert-danger">
            <span class="emptySpan">&nbsp;</span>
            <span class="msgTextSpan">${err_msg}</span>
            <span class="closeBtnSpan"><button type="button" class="close" data-dismiss="alert" aria-hidden="true" onclick="hide();">x</button></span>
	    </div>
	</div>
</c:if>

<script>
	function hide() {
		$("#messageDiv").hide();
	}
	// 4 seconds
	setTimeout(function() {
		$('#messageDiv').fadeOut('slow');
	}, 4000);

</script>

