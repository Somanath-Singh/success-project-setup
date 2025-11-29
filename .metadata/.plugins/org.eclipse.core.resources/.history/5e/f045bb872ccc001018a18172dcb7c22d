<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Integrated Project Management System</title>
<style>
@import url('https://fonts.googleapis.com/css?family=Roboto');

body, html {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
}

body {
	background-color: #214c23;
	font-family: 'Roboto', sans-serif;
}

.main-container {
	position: fixed;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	overflow: auto;
	background: rgba(0, 0, 0, 0.32);
}

.logo-con {
	float: left;
	width: 100%;
}

.logo-con img:nth-child(1) {
	float: left;
	width: 90px;
	margin: 10px;
	opacity: .80;
}

.logo-con img:nth-child(2) {
	float: right;
	width: 270px;
	margin: 10px;
	opacity: .80;
}

.contents-con {
	float: left;
	width: 100%;
	background: url(${contextPath}/assets/images/error.jpg);
	background-repeat: no-repeat;
	background-size: 100% 100%;
	/* margin-left: 14%; */
	/* padding: 40px; */
	position: absolute;
	height: 100%;
	overflow: hidden;
}

.contents-con .contents-left {
	float: left;
	width: 60%;
	margin: 10px;
	overflow: hidden;
}

.contents-con .contents-left h1 {
	font-size: 79px;
	color: rgba(255, 255, 255, 0.87);
	font-weight: 100;
	margin: 40px 0 10px 0;
	line-height: normal;
}

.contents-con .contents-left h1 b {
	font-weight: 100;
	font-size: 110px;
	color: #fff;
	text-shadow: 5px 5px 5px rgba(0, 0, 0, 0.84);
}

.contents-con .contents-left p {
	color: rgba(249, 249, 249, 0.72);
	font-size: 15px;
	padding: 10px 0 0px 130px;
}

.contents-con .contents-left a {
	display: block;
	border: 0;
	background: rgb(68, 17, 80);
	color: rgba(253, 253, 253, 0.95);
	padding: 5px 0px 5px 5px;
	margin-top: -3px;
	width: 126px;
	text-align: center;
	font-size: 15px;
	text-decoration: none !important;
	box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.6);
	transition: all .5s;
	float: right;
}

.contents-con .contents-left a:hover {
	background: rgba(255, 255, 255, 0.68);
	color: #004c03;
	cursor: pointer;
}

.contents-con .contents-right {
	float: left;
	width: 35%;
}

.count-con {
	width: 150px;
	height: 150px;
	border: 4px solid rgba(255, 255, 255, 0.43);
	text-align: center;
	border-radius: 50%;
	margin: 0 auto;
	margin-top: 100px;
	box-shadow: 0px 0px 20px #000;
}

.count-con .count {
	font-size: 100px;
	font-weight: 100;
	color: rgba(255, 255, 255, 0.79);
	padding-top: 15px;
}

.contents-con .contents-right h3 {
	text-align: center;
	color: rgba(255, 255, 255, 0.85);
	font-size: 35px;
	margin-top: 10px;
	font-weight: 100;
}

.contents-con .contents-left a:hover {
	background: rgb(231, 245, 254);
	color: #090d8c;
	cursor: pointer;
	font-weight: 600;
}

@media ( max-width : 767px) {
	.logo-con img:nth-child(1) {
		width: 60px;
	}
	.logo-con img:nth-child(2) {
		width: 140px;
	}
	.contents-con .contents-left, .contents-con .contents-right {
		width: 100%;
	}
	.contents-con .contents-left h1 {
		font-size: 37px;
	}
	.contents-con .contents-left h1 b {
		font-size: 50px;
	}
	.contents-con .contents-left p {
		font-size: 14px;
	}
	.count-con {
		width: 130px;
		height: 130px;
		margin-top: 30px;
	}
	.count-con .count {
		font-size: 80px;
	}
	.contents-con .contents-right h3 {
		font-size: 30px;
		margin-top: 6px;
	}
}
</style>
</head>
<body>

	<div class="main-container">

		<div class="contents-con">
			<div class="contents-left">
				<p>It seems your request is not good.</p>
				<a href="${contextPath}/" id="btHome">Back to Home</a>
			</div>
			<div class="contents-right">
				<div class="count-con">
					<div id="timecount" class="count"></div>
				</div>
				<h3>Seconds Left</h3>
			</div>
		</div>
	</div>
	<form action="${contextPath}/" method="get" id="formlogin"></form>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script type="text/javascript">
(function($) {
	$.fn.jQuerySimpleCounter = function( options ) {
	    var settings = $.extend({
	        start:  10,
	        end:    -1,
	        easing: 'swing',
	        duration: 10000,
	        complete: ''
	    }, options );

	    var thisElement = $(this);

	    $({count: settings.start}).animate({count: settings.end}, {
			duration: settings.duration,
			easing: settings.easing,
			step: function() {
				var mathCount = Math.ceil(this.count);
				thisElement.text(mathCount);
				if(mathCount==0)
					{
					 $("#formlogin").submit();	
					}
			},
			complete: settings.complete
		});
	};

}(jQuery));
$('#timecount').jQuerySimpleCounter();
$("#bclogin").click(function(){	
	$("#formlogin").submit();	
});
 
</script>
</body>
</html>
