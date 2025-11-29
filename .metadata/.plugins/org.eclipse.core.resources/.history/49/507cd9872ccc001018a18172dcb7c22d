<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="shortcut icon" href="${contextPath}/assets/img/favicon.png">

<link rel="stylesheet" href="${contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${contextPath}/assets/vendor/jquery_datepicker/jquery.datepick.css" />
<link href="${contextPath}/assets/css/datatables.min.css" rel="stylesheet" />


<!-- Font awesome cdn -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.6.0/css/all.min.css">

<script src="${contextPath}/assets/js/jquery.min.js"></script>


<style>
	table th {
		background: #c2b8b8;
	}
	.ddBox {
		display: flex;
    justify-content: space-between;
	flex-wrap: wrap;
    flex-direction: column;
	}
	.ddBox ul {
		list-style: none;
		padding: 0;
		margin: 0;
		padding: 5px 0px;
		float: left;
    width: 100%;
	}
	.ddBox ul .sub-menu {
		display: none;
		height: 500px;
		overflow: auto;
	}
	.ddBox i {
		transition: all 0.3s ease-in-out;
	}
	.main-menu > li{
		background: #071d4a;
    border-bottom: 1px solid #bababa;
    position: relative;
    margin-bottom: 5px;
    color: #fff;
	}
	.main-menu > li a {
		color: #fff;
    text-decoration: none;
    padding: 5px 5px;
    display: inline-block;
    width: 100%;
	overflow: hidden;
  position: relative;
  display: inline-block;
  margin: 0 5px 0 5px;
  text-align: center;
  text-decoration: none;
  text-overflow: ellipsis;
  white-space: nowrap;
  text-align: left;
	}
	.main-menu > li > a i {
		color: #071d4a;
    text-decoration: none;
    background-color: #fff;
    right: 30px;
    position: absolute;
    top: 5px;
    height: 21px;
    width: 21px;
    text-align: center;
    line-height: 21px;
    border-radius: 2px;
	}
	.sub-menu {
		background: #e3e3e3;
	}
	.sub-menu li a {
	    color: #000;
    border-bottom: 1px dashed #aaa;
	text-align: left;
	}
	.ddBox .active > i {
		transform: rotate(90deg);
		-webkit-transform: rotate(90deg);
		-moz-transform: rotate(90deg);
		-o-transform: rotate(90deg);
		-ms-transform: rotate(90deg);
	}
	.left-header {
		text-align: center;
    font-size: 18px;
    background: #020d23;
    padding: 16px 0;
	color: #fff;
	margin-top:0;
	}

.ddBox ul .sub-menu::-webkit-scrollbar {
  width: 3px;
}

/* Track */
.ddBox ul .sub-menu::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px grey;
  border-radius: 10px;
}

/* Handle */
.ddBox ul .sub-menu::-webkit-scrollbar-thumb {
  background: #071d49;
  border-radius: 10px;
}
.dynamicTableCls th {
	padding: 2px 2px;
    font-size: 12px;
}
.dynamicTableCls td {
	padding: 2px 2px;
    font-size: 12px;
}
aside {
    left: 0;
    width: 250px;
    height: 100vh;
    background: #071d49;
    position: fixed;
    top: 0;
    z-index: 9999;
}
.content {
	width: calc(100% - 250px);
    padding:15px;
    position: relative;
    left: 250px;
    height: 100vh;
    overflow: auto;
	max-width: auto;
}

.modal{
	z-index: 9999;
}

.readOnlyClass {
	background-color: #e9ecef;
	opacity: 1;
	cursor: not-allowed;
}

.btn-primary{
   background: #007bff;
    border: #ddd 1px solid;
    color: #fff;
    padding: 10px;
}


/* Loder Style Start */

.pencil {
  display: block;
  width: 10em;
  height: 10em;
}

.pencil__body1,
.pencil__body2,
.pencil__body3,
.pencil__eraser,
.pencil__eraser-skew,
.pencil__point,
.pencil__rotate,
.pencil__stroke {
  animation-duration: 3s;
  animation-timing-function: linear;
  animation-iteration-count: infinite;
}

.pencil__body1,
.pencil__body2,
.pencil__body3 {
  transform: rotate(-90deg);
}

.pencil__body1 {
  animation-name: pencilBody1;
}

.pencil__body2 {
  animation-name: pencilBody2;
}

.pencil__body3 {
  animation-name: pencilBody3;
}

.pencil__eraser {
  animation-name: pencilEraser;
  transform: rotate(-90deg) translate(49px,0);
}

.pencil__eraser-skew {
  animation-name: pencilEraserSkew;
  animation-timing-function: ease-in-out;
}

.pencil__point {
  animation-name: pencilPoint;
  transform: rotate(-90deg) translate(49px,-30px);
}

.pencil__rotate {
  animation-name: pencilRotate;
}

.pencil__stroke {
  animation-name: pencilStroke;
  transform: translate(100px,100px) rotate(-113deg);
}

/* Animations */
@keyframes pencilBody1 {
  from,
	to {
    stroke-dashoffset: 351.86;
    transform: rotate(-90deg);
  }

  50% {
    stroke-dashoffset: 150.8;
 /* 3/8 of diameter */
    transform: rotate(-225deg);
  }
}

@keyframes pencilBody2 {
  from,
	to {
    stroke-dashoffset: 406.84;
    transform: rotate(-90deg);
  }

  50% {
    stroke-dashoffset: 174.36;
    transform: rotate(-225deg);
  }
}

@keyframes pencilBody3 {
  from,
	to {
    stroke-dashoffset: 296.88;
    transform: rotate(-90deg);
  }

  50% {
    stroke-dashoffset: 127.23;
    transform: rotate(-225deg);
  }
}

@keyframes pencilEraser {
  from,
	to {
    transform: rotate(-45deg) translate(49px,0);
  }

  50% {
    transform: rotate(0deg) translate(49px,0);
  }
}

@keyframes pencilEraserSkew {
  from,
	32.5%,
	67.5%,
	to {
    transform: skewX(0);
  }

  35%,
	65% {
    transform: skewX(-4deg);
  }

  37.5%, 
	62.5% {
    transform: skewX(8deg);
  }

  40%,
	45%,
	50%,
	55%,
	60% {
    transform: skewX(-15deg);
  }

  42.5%,
	47.5%,
	52.5%,
	57.5% {
    transform: skewX(15deg);
  }
}

@keyframes pencilPoint {
  from,
	to {
    transform: rotate(-90deg) translate(49px,-30px);
  }

  50% {
    transform: rotate(-225deg) translate(49px,-30px);
  }
}

@keyframes pencilRotate {
  from {
    transform: translate(100px,100px) rotate(0);
  }

  to {
    transform: translate(100px,100px) rotate(720deg);
  }
}

@keyframes pencilStroke {
  from {
    stroke-dashoffset: 439.82;
    transform: translate(100px,100px) rotate(-113deg);
  }

  50% {
    stroke-dashoffset: 164.93;
    transform: translate(100px,100px) rotate(-113deg);
  }

  75%,
	to {
    stroke-dashoffset: 439.82;
    transform: translate(100px,100px) rotate(112deg);
  }
}

/* Loader Style end */

/* Raadio Button Style Start */
.wrapper {
  --font-color-dark: #323232;
  --font-color-light: #fff;
  --bg-color: #fff;
  --main-color: #323232;
  --secondary-color: #505050;
  position: relative;
  min-width: 380px;
  height: 36px;
  background-color: var(--bg-color);
  border: 2px solid var(--main-color);
  border-radius: 34px;
  display: flex;
  flex-direction: row;
  box-shadow: 4px 4px var(--main-color);
  /* Right side */
  margin-right: 10px;

}

.option {
  margin-right: 5px;
  width: auto;
  height: 28px;
  position: relative;
  top: 2px;
  left: 2px;
  border-radius: 34px;
  transition: 0.25s cubic-bezier(0, 0, 0, 1);
}

.option:last-child {
  margin-right: 10px;
}

.option:hover {
  background-color: var(--secondary-color);
}

.option:hover .span {
  color: var(--font-color-light);
}

.radioInput {
  width: 100%;
  height: 100%;
  position: absolute;
  left: 0;
  top: 0;
  appearance: none;
  cursor: pointer;
}

.btn {
  width: 100%;
  height: 100%;
  border-radius: 200px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.span {
  color: var(--font-color-dark);
}

.radioInput:checked + .btn {
  background-color: var(--main-color);
  transition: 0.2s cubic-bezier(0, 0, 0, 1);
}

.radioInput:checked + .btn .span {
  color: var(--font-color-light);
  transition: 0.25s cubic-bezier(0, 0, 0, 1);
}



/* Radio Button Style End */


</style>
<section>
	<div class="d-flex justify-content-between">
		<aside style="overflow: auto;">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div class="ddBox">
				<h4 class="left-header">Schema & Tables</h4>
				<c:forEach items="${schemaTableMap}" var="schema">
					<ul class="main-menu">
						<li>
							<a href="javascript:void(0)" draggable="true" data-drag-value="${schema.key}">${schema.key}<i class="fa fa-angle-right ClickToOpen"></i></a>
							<ul class="sub-menu">
								<c:forEach items="${schema.value}" var="table">
									<li style="position:relative;padding-right: 15px;"><a href="javascript:void(0)" draggable="true" data-drag-value="${schema.key}.${table}" class="getColumnsClass" value="${schema.key}##${table}" title="${table}">${table}</a></li>
								</c:forEach>
							</ul>
						</li>
					</ul>
				</c:forEach>
			</div>
		</aside>

		<div class="content">
			<!-- A area where table name will show in comma separated -->
			<div class="row">
				
				<div class="col-md-12" >
					<div class="row" id="criteriaAreaId">
						
					</div>
				</div>

				<div class="col-md-12">
					<div class="form-group">
						<div class="d-flex mb-2 align-items-center">
							<label class="" for="tableList" style="width: 200px;">SQL QUERY PANEL</label>
							<c:if test="${isBeastModeNo eq 'true'}">
								<input type="text" class="form-control" id="beastModeCodeId" style="width: 350px;" placeholder="Please Enter Your Beast Mode Code">
							</c:if>
							<c:if test="${isAiModeOn eq 'true'}">
								<div class="wrapper ms-auto">
									<div class="option">
									  <input value="true" name="aiMode" type="radio" class="radioInput">
									  <div class="btn">
										<span class="span">Activate AI Mode</span>
									  </div>
									</div>
									<div class="option">
									  <input value="false" name="aiMode" type="radio" class="radioInput" checked>
									  <div class="btn">
										<span class="span">Activate SQL Raw Mode</span>
									  </div>  
									</div>
								  </div>
							</c:if>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<textarea class="form-control" id="rawQueryId" placeholder="Enter Raw Query" style="min-height: 200px;"></textarea>
					</div>
				</div>
				<div class="col-md-12" style="text-align: center; margin-top: 5px; margin-bottom: 30px; display: flex; justify-content: center;">
					<button type="button" style="width: 200px !important;" id="executeBtn1" class="btn btn-primary" onclick="executeRawQuery(this)">Execute Raw Query</button>
				</div>

				<div class="loderDivId" style="display: none; justify-content: center;">
					<svg xmlns="http://www.w3.org/2000/svg" height="200px" width="200px" viewBox="0 0 200 200" class="pencil">
						<defs>
							<clipPath id="pencil-eraser">
								<rect height="30" width="30" ry="5" rx="5"></rect>
							</clipPath>
						</defs>
						<circle transform="rotate(-113,100,100)" stroke-linecap="round" stroke-dashoffset="439.82" stroke-dasharray="439.82 439.82" stroke-width="2" stroke="currentColor" fill="none" r="70" class="pencil__stroke"></circle>
						<g transform="translate(100,100)" class="pencil__rotate">
							<g fill="none">
								<circle transform="rotate(-90)" stroke-dashoffset="402" stroke-dasharray="402.12 402.12" stroke-width="30" stroke="hsl(223,90%,50%)" r="64" class="pencil__body1"></circle>
								<circle transform="rotate(-90)" stroke-dashoffset="465" stroke-dasharray="464.96 464.96" stroke-width="10" stroke="hsl(223,90%,60%)" r="74" class="pencil__body2"></circle>
								<circle transform="rotate(-90)" stroke-dashoffset="339" stroke-dasharray="339.29 339.29" stroke-width="10" stroke="hsl(223,90%,40%)" r="54" class="pencil__body3"></circle>
							</g>
							<g transform="rotate(-90) translate(49,0)" class="pencil__eraser">
								<g class="pencil__eraser-skew">
									<rect height="30" width="30" ry="5" rx="5" fill="hsl(223,90%,70%)"></rect>
									<rect clip-path="url(#pencil-eraser)" height="30" width="5" fill="hsl(223,90%,60%)"></rect>
									<rect height="20" width="30" fill="hsl(223,10%,90%)"></rect>
									<rect height="20" width="15" fill="hsl(223,10%,70%)"></rect>
									<rect height="20" width="5" fill="hsl(223,10%,80%)"></rect>
									<rect height="2" width="30" y="6" fill="hsla(223,10%,10%,0.2)"></rect>
									<rect height="2" width="30" y="13" fill="hsla(223,10%,10%,0.2)"></rect>
								</g>
							</g>
							<g transform="rotate(-90) translate(49,-30)" class="pencil__point">
								<polygon points="15 0,30 30,0 30" fill="hsl(33,90%,70%)"></polygon>
								<polygon points="15 0,6 30,0 30" fill="hsl(33,90%,50%)"></polygon>
								<polygon points="15 0,20 10,10 10" fill="hsl(223,10%,10%)"></polygon>
							</g>
						</g>
					</svg>
				</div>

				<!-- Table -->
				<div class="col-md-12 table-responsive ">
					<table id="dynamicTableId" class="datatable table table-bordered table-striped dynamicTableCls"></table>
					<!-- Error Mesage -->
					<div class="alert alert-danger" id="errorMessageId" style="display: none;">
						<strong>Error!</strong> <span id="errorMessageSpanId"></span>
					</div>
				</div>
			</div>
		</div>
	</div>

	


	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<!-- Centered -->
	<div class="modal-dialog modal-dialog-centered modal-xl" role="document">
	  <div class="modal-content">
		<div class="modal-header">
		  <h5 class="modal-title" id="exampleModalLabel">Add <span id="addModalTitleId"></span></h5>
		  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="modal-body" id="modalBodyId">

			<div class="row" id="rowId0">
				<div class="col-md-2">
					<div class="form-group">
						<Select class="form-control readOnlyClass" id="schemaSelectId0">
							<option value="schema1">Schema</option>
						</Select>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<Select class="form-control readOnlyClass" id="tableSelectId0">
							<option value="table1">Table</option>
						</Select>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<Select class="form-control" id="columnSelectId0" onchange="getOperations(this)">
							<option value="column1">Column</option>
						</Select>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<Select class="form-control" id="operationSelectId0">
							<option value="operation1">Operation</option>
						</Select>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<input type="text" class="form-control" id="valueId0" placeholder="Value">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<select class="form-control" id="andOrId0" style="width: 100%;" onchange="addMoreCriteria(this)">
							<option value="">select</option>
							<option value="and">And</option>
							<option value="or">Or</option>
						</select>
					</div>
				</div>
					
			</div>
		  
		</div>
		<div class="modal-footer">
			<!-- Center button -->
		  <button type="button" class="btn btn-success" style="margin: 0 auto;" onclick="executeQuery()"><i class="fas fa-leaf"></i>Execute</button>
		</div>
	  </div>
	</div>
  	</div>

</section>


<script src="${contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${contextPath}/assets/js/datatables.min.js"></script>
<script>
	let arrow = document.querySelectorAll(".arrow");
		for (var i = 0; i < arrow.length; i++) {
		arrow[i].addEventListener("click", (e)=>{
		let arrowParent = e.target.parentElement.parentElement;//selecting main parent of arrow
		arrowParent.classList.toggle("showMenu");
		});
		}

		$( document ).ready(function() {
		$("#btn_navToggle").click(function() {
			$("body").toggleClass("sidebar_collapsed");
		});
			$(document).on("click", '.sub-menu > li > i', function (){ 
			$(this).closest('li').find('.sub-sub-menu').slideToggle();
			});
		});
</script>

<script type="text/javascript">
	$(document).ready(function(){
		$('.main-menu > li > a').click(function(){
			$(this).closest('li').find('.sub-menu').slideToggle();
			$(this).toggleClass('active');
		});
	});

	function getCriteriaList(){
		let crtLst = [
						{"criteria": "filter", "code":"FILTER", "icon":"fa fa-filter", "operation": ["=", ">", "<", ">=", "<=", "!="], "function": "filterFn(this)"},
						{"criteria": "join", "code":"JOIN", "icon":"fa fa-link", "operation": ["=", ">", "<", ">=", "<=", "!="], "function": "joinFn(this)"},
						{"criteria": "group by", "code":"GROUP BY", "icon":"fa fa-link", "operation": ["=", ">", "<", ">=", "<=", "!="], "function": "groupByFn(this)"},
						{"criteria": "sort by", "code":"SORT BY", "icon":"fa fa-link", "operation": ["=", ">", "<", ">=", "<=", "!="], "function": "sortByFn(this)"},
						{"criteria": "sum", "code":"SUM", "icon":"fa fa-link", "operation": ["=", ">", "<", ">=", "<=", "!="], "function": "sumFn(this)"},
						{"criteria": "avg", "code":"AVG", "icon":"fa fa-link", "operation": ["=", ">", "<", ">=", "<=", "!="], "function": "avgFn(this)"},
						{"criteria": "count", "code":"COUNT", "icon":"fa fa-link", "operation": ["=", ">", "<", ">=", "<=", "!="], "function": "countFn(this)"},
						{"criteria": "max", "code":"MAX", "icon":"fa fa-link", "operation": ["=", ">", "<", ">=", "<=", "!="], "function": "maxFn(this)"},
						{"criteria": "min", "code":"MIN", "icon":"fa fa-link", "operation": ["=", ">", "<", ">=", "<=", "!="], "function": "minFn(this)"}
					];
		return crtLst;
	}


	function asyncAjaxForData(url, data, method){
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                type: method,
                data: data,
                success: function(data){
                    resolve(data);
                },
                error: function(err){
                    reject(err);
                }
            });
        });
    } 


	var displayTables = [];
	var criteriaList = ['filter', 'join', 'group by', 'sort by', 'sum', 'avg', 'count', 'max', 'min'];

	var criteriaList = getCriteriaList();

	// double click on the table name by class name get the columns "getColumnsClass"
	$(document).on('dblclick', '.getColumnsClass', async function(event){
		var schemaTable = $(this).attr('value');
		var schema = schemaTable.split('##')[0];
		var table = schemaTable.split('##')[1];
		// replace ## with .
		let formatiseText = schemaTable.replace('##', '.');

		// if the table is already present then return
		if (displayTables.includes(schemaTable)) {
			return;
		}else{
			displayTables.push(schemaTable);
		}
		
		var criteriaHtml = '<div class="col-md-3" style="margin-top: 5px;">';
		criteriaHtml += '<table class="datatable table table-striped table-bordered">';
		// head end
		criteriaHtml += '<head>';
		criteriaHtml += '<tr>';
		criteriaHtml += '<th colspan="3" class="text-center">'+formatiseText+'<span style="float: left;"><i class="fas fa-trash-alt" onclick="removeTable(this)" value="'+schema+'##'+table+'"></i></span><span style="float: right;"><i class="fas fa-eye" onclick="showAllData(this)" value="'+schema+'##'+table+'"></i></span></th>';
		criteriaHtml += '</tr>';
		criteriaHtml += '</head>';

		// body start criteriaList convert this list to three items array like [[filter, join, group by], [sort by, sum, avg], [count, max, min]] if the length is not divisible by 3 then add empty string as the last element **criteriaList**
		let criteriaListArray = [];
		let tempArray = [];
		for (var i = 0; i < criteriaList.length; i++) {
			tempArray.push(criteriaList[i]);
			if (tempArray.length == 3) {
				criteriaListArray.push(tempArray);
				tempArray = [];
			}
		}
		if (tempArray.length > 0) {
			criteriaListArray.push(tempArray);
		}
		debugger;
		// body start
		criteriaHtml += '<tbody style="background: #9f00fc;">';
		for (var i = 0; i < criteriaListArray.length; i++) {
			criteriaHtml += '<tr>';
			for (var j = 0; j < criteriaListArray[i].length; j++) {
				let crtObj = criteriaListArray[i][j];
				criteriaHtml += '<td class="text-center" style="color:#fff" onclick="'+crtObj['function']+'" value="'+schema+'##'+table+'##'+crtObj['code']+'##'+crtObj['operation']+'">'+crtObj['criteria']+'</td>';	
			}
			criteriaHtml += '</tr>';
		}
		// body end
		criteriaHtml += '</tbody>';
		criteriaHtml += '</table>';
		criteriaHtml += '</div>';
		$("#criteriaAreaId").append(criteriaHtml);
		

	});


	let count = 0;

	//  Criteria Functions Start
	function filterFn(that){
		getColumnsAndOpertaions(that);
	}






	function getColumnsAndOpertaions(that){
		var schemaAndTable = $(that).attr('value');
		var schema = schemaAndTable.split('##')[0];
		var table = schemaAndTable.split('##')[1];
		var criteria = schemaAndTable.split('##')[2];
		var operations = schemaAndTable.split('##')[3];
		let operationsArray = operations.split(',');

		// get a tag text
		var criteria = $(that).text();
		// add to modal title
		$('#addModalTitleId').text(criteria);
		// get the modal body
		var modalBody = $('#modalBodyId');
		
		// get the columns
		$.ajax({
			url : "${contextPath}/table/getColumns",
			type : "GET",
			data : {
				"schema" : schema,
				"table" : table
			},
			success : function(response) {
				var columnDetails = response.data;
				var columns = columnDetails.columns;
				var table = columnDetails.tableName;
				var schema = columnDetails.schemaName;
			
				columns = JSON.parse(columns);
				var query = columnDetails.query;

				// add schema to the select box
				$('#schemaSelectId'+count).empty();
				$('#schemaSelectId'+count).append('<option value="'+schema+'">'+schema+'</option>');
				// add table to the select box
				$('#tableSelectId'+count).empty();
				$('#tableSelectId'+count).append('<option value="'+table+'">'+table+'</option>');
				// add columns to the select box
				$('#columnSelectId'+count).empty();
				$('#columnSelectId'+count).append('<option value="">Select</option>');
				for (var i = 0; i < columns.length; i++) {
					$('#columnSelectId'+count).append('<option value="'+columns[i]+'">'+columns[i]+'</option>');
				}

				// add operations to the select box
				$('#operationSelectId'+count).empty();
				$('#operationSelectId'+count).append('<option value="">Select</option>');
				for (var i = 0; i < operationsArray.length; i++) {
					$('#operationSelectId'+count).append('<option value="'+operationsArray[i]+'">'+operationsArray[i]+'</option>');
				}



			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
		// show the modal
		$('#exampleModal').modal('show');
	}
	
	function executeQuery(that){
		// get all chcked table and its schema like {schema1:[table1,table2],schema2:[table1,table2]}
		var tableList = {};
		// get id of the textarea where table names are present
		var textareaId = $(that).parent().parent().find('textarea').attr('id');
		// get the table names from the textarea
		var tableNames = $('#'+textareaId).val();
		// split the table names by comma
		var tables = tableNames.split(',');
		// loop through the tables
		for (var i = 0; i < tables.length; i++) {
			// get the schema name
			var schema = tables[i].split('.')[0].trim();
			// get the table name
			var table = tables[i].split('.')[1];
			// check if the schema is already present in the tableList

			// if schema or table name is empty then continue
			if(schema == '' || table == ''){
				continue;
			}

			if (tableList.hasOwnProperty(schema)) {
				// if present then add the table name to the schema
				tableList[schema].push(table);
			}else{
				// if not present then create a new schema and add the table name to the schema
				tableList[schema] = [table];
			}
		}
		var jsondata = JSON.stringify(tableList);
		$('#jsondata').val(jsondata);
		// show the loader
		loderDivIdShow();

		$.ajax({
			url : "${contextPath}/table/generateDynamicReport",
			type : "GET",
			data : {
				// Base64 encoded
				"encodedSchemaAndTables" : btoa(jsondata)
			},
			success : function(response) {
				var data = response.data;
				$('#dynamicTableId').empty();
				var tableHeader = '';
				var tableBody = '';
				for (var i = 0; i < data.length; i++) {
					var columnList = data[i].columns;
					var columns = JSON.parse(columnList);
					for (var j = 0; j < columns.length; j++) {
						let th = $("<th></th>");
						// filter icon
						th.append('<i class="fa fa-filter" onclick="dataFilter(this)"></i>');
						let colunmNmae = columns[j];
						// scheme_type make scheme Type
						colunmNmae = makeFormatiseText(colunmNmae);
						th.text(colunmNmae);
						// min-width: 50 + length of column name px
						th.css('min-width', 100 + columns[j].length + 'px');
						// text-align: center
						th.css('text-align', 'center');
						// add a icon to the header minus icon
						th.append('<i class="fa fa-minus-circle" onclick="removeColumn(this)"></i>');
						
						

						tableHeader += th.prop('outerHTML');
					}
					var rowDetails = data[i].data;
					var rows = JSON.parse(rowDetails);
					for (var j = 0; j < rows.length; j++) {
						var row = rows[j];
						var tr = $('<tr></tr>');
						for (var k = 0; k < row.length; k++) {
							var td = $('<td></td>');
							td.text(row[k]);
							// text-align: center
							td.css('text-align', 'center');
							tr.append(td);
						}
						tableBody += tr.prop('outerHTML');
					}
				}
				
				loderDivIdHide();
				
				// append the table header
				$('#dynamicTableId').append('<thead style="background: #0af4f4;"><tr>'+tableHeader+'</tr></thead>');
				// append the table body
				$('#dynamicTableId').append('<tbody>'+tableBody+'</tbody>');
			},
			error : function(e) {
				console.log("ERROR: ", e);
				loderDivIdHide();
			}
		});
	}
	

	function showAllData(that){
		var schemaAndTable = $(that).attr('value');
		var schema = schemaAndTable.split('##')[0];
		var table = schemaAndTable.split('##')[1];

		var tableList = {};
		tableList[schema] = [table];
		tableList.schema = schema;
		tableList.table = table;

		loderDivIdShow();

		$.ajax({
			url : "${contextPath}/table/generateDynamicReport",
			type : "GET",
			data : {
				// Base64 encoded
				"encodedSchemaAndTables" : btoa(JSON.stringify(tableList))
			},
			success : function(response) {
				loderDivIdHide();
				var data = response.data;
				let query = data.query;
				$('#rawQueryId').val(query);
				dynamicTableMaking(data);
			},
			error : function(e) {
				loderDivIdHide();
				console.log("ERROR: ", e);
			}
		});

	}

	function dynamicTableMaking(data){
		var tableHeader = '';
		var tableBody = '';
		var columnList = data.columns;
		var columns = JSON.parse(columnList);
		for (var j = 0; j < columns.length; j++) {
			let th = $("<th></th>");
			let colunmNmae = columns[j];
			// scheme_type make scheme Type
			colunmNmae = makeFormatiseText(colunmNmae);
			th.css('min-width', 100 + columns[j].length + 'px');
			th.css('text-align', 'center');
			th.append('<span style="padding-left: 10px; padding-right: 10px;">'+colunmNmae+'</span>');
			th.append('<i class="fa fa-minus-circle" onclick="removeColumn(this)"></i>');
			th.append('<i class="fa fa-filter" onclick="dataFilter(this)"></i>');
			tableHeader += th.prop('outerHTML');
		}
		var rowDetails = data.data;
		var rows = JSON.parse(rowDetails);
		for (var j = 0; j < rows.length; j++) {
			var row = rows[j];
			// if row is not an array then continue
			if (!Array.isArray(row)) {
                var tr = $('<tr></tr>');
                var td = $('<td></td>');
                td.text(row);
                // text-align: center
                td.css('text-align', 'center');
                tr.append(td);
            }else{
                var tr = $('<tr></tr>');
                for (var k = 0; k < row.length; k++) {
                    var td = $('<td></td>');
                    td.text(row[k]);
                    // text-align: center
                    td.css('text-align', 'center');
                    tr.append(td);
                }
			}
			tableBody += tr.prop('outerHTML');
		}

		if(tableBody == ''){
			tableBody = '<tr><td colspan="'+columns.length+'" style="text-align: center;">No Data Found</td></tr>';
		}

		$('#dynamicTableId').empty();

		// append the table header
		$('#dynamicTableId').append('<thead style="background: #0af4f4;"><tr>'+tableHeader+'</tr></thead>');
		// append the table body
		$('#dynamicTableId').append('<tbody>'+tableBody+'</tbody>');

		if(rows.length == 0){
			return;
		}

		// destroy the datatable and reinitialize
		$('.dynamicTableCls').DataTable().destroy();
		$('.dynamicTableCls').DataTable({
			paging: true,
			pagingType: 'full_numbers',
			"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
			export: true,
			"dom": 'Bfrtip',
			"buttons": [
				{
					extend: 'copyHtml5',
					text: 'Copy',
					titleAttr: 'Copy'
				},
				{
					extend: 'excelHtml5',
					text: 'Excel',
					titleAttr: 'Excel'
				},
				{
					extend: 'csvHtml5',
					text: 'CSV',
					titleAttr: 'CSV'
				},
				{
					extend: 'pdfHtml5',
					text: 'PDF',
					titleAttr: 'PDF'
				},
				{
					extend: 'print',
					text: 'Print',
					titleAttr: 'Print'
				}
			]
		});
	}

	function makeFormatiseText(text){
		// scheme_type make scheme Type
		text = text.replace(/_/g, ' ');
		// make first letter capital
		text = text.charAt(0).toUpperCase() + text.slice(1);
		return text;
	}

	function removeTable(that){
		// remove the table from the displayTables
		var table = $(that).attr('value');
		var index = displayTables.indexOf(table);
		if (index > -1) {
			displayTables.splice(index, 1);
		}
		// remove full div from the criteriaAreaId
		$(that).parent().parent().parent().parent().parent().parent().remove();


	}

	function removeColumn(element){
		// get the index of the column
		var index = $(element).parent().index();
		// remove the column from the table
		$('#dynamicTableId tr').find('td:eq('+index+'),th:eq('+index+')').remove();
	}

	function getOperations(that){
		var column = $(that).val();
		var id = $(that).attr('id');
		var id = id.replace('columnSelectId', '');
		var schema = $('#schemaSelectId'+id).val();
		var table = $('#tableSelectId'+id).val(); 
		if (column == '') {
			return;
		}
		$.ajax({
			url : "${contextPath}/table/getOperations",
			type : "GET",
			data : {
				"column" : column,
				"schema" : schema,
				"table" : table
			},
			success : function(response) {
				var operations = response.data;
				// add current div id to the operation select box
				$('#operationSelectId'+id).empty();
				$('#operationSelectId'+id).append('<option value="">Select</option>');
				for (var i = 0; i < operations.length; i++) {
					$('#operationSelectId'+id).append('<option value="'+operations[i].operationName+'">'+operations[i].operationName+'('+operations[i].code+')</option>');
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
	}

	function addMoreCriteria(that){
		// get the id of the button
		var id = $(that).attr('id');
		// get the number from the id
		var id = id.replace('andOrId', '');
		var nextId = parseInt(id) + 1;
		var schema = $('#schemaSelectId'+id).val();
		var table = $('#tableSelectId'+id).val();
		var columnList = $('#columnSelectId'+id).html();

		var column = $('#columnSelectId'+id).val();
		var operation = $('#operationSelectId'+id).val();
		var value = $('#valueId'+id).val();
		if (column == '' || operation == '' || value == '') {
			alert('Please fill all the fields');
			return;
		}

		// add new div
		var appendHtml = '<div class="row" id="rowId'+nextId+'">';
			appendHtml += '<div class="col-md-2">';
				appendHtml += '<div class="form-group">';
					appendHtml += '<Select class="form-control" id="schemaSelectId'+nextId+'">';
						appendHtml += '<option value='+schema+'>'+schema+'</option>';
					appendHtml += '</Select>';
				appendHtml += '</div>';
			appendHtml += '</div>';
			appendHtml += '<div class="col-md-2">';
				appendHtml += '<div class="form-group">';
					appendHtml += '<Select class="form-control" id="tableSelectId'+nextId+'">';
						appendHtml += '<option value='+table+'>'+table+'</option>';
					appendHtml += '</Select>';
				appendHtml += '</div>';
			appendHtml += '</div>';
			appendHtml += '<div class="col-md-2">';
				appendHtml += '<div class="form-group">';
					appendHtml += '<Select class="form-control" id="columnSelectId'+nextId+'" onchange="getOperations(this)">';
						appendHtml += columnList;
					appendHtml += '</Select>';
				appendHtml += '</div>';
			appendHtml += '</div>';
			appendHtml += '<div class="col-md-2">';
				appendHtml += '<div class="form-group">';
					appendHtml += '<Select class="form-control" id="operationSelectId'+nextId+'">';
						appendHtml += '<option value="operation1">Operation</option>';
					appendHtml += '</Select>';
				appendHtml += '</div>';
			appendHtml += '</div>';
			appendHtml += '<div class="col-md-2">';
				appendHtml += '<div class="form-group">';
					appendHtml += '<input type="text" class="form-control" id="valueId'+nextId+'" placeholder="Value">';
				appendHtml += '</div>';
			appendHtml += '</div>';
			appendHtml += '<div class="col-md-2">';
				appendHtml += '<div class="form-group">';
					appendHtml += '<select class="form-control" id="andOrId'+nextId+'" style="width: 100%;" onchange="addMoreCriteria(this)">';
						appendHtml += '<option value="">select</option>';
						appendHtml += '<option value="and">And</option>';
						appendHtml += '<option value="or">Or</option>';
					appendHtml += '</select>';
				appendHtml += '</div>';
			appendHtml += '</div>';
		appendHtml += '</div>';
		$('#rowId'+id).after(appendHtml);
	}


	
	var tdValues = new Set();
	function dataFilter(that){
		let tdValue = new Set();
		// find its tbody tr td value
		var index = $(that).parent().index();
		// get data from entire table
		var tableData = $('#dynamicTableId').DataTable().data();
		// loop through the table data
		for (var i = 0; i < tableData.length; i++) {
			// get the row data
			var rowData = tableData[i];
			// get the td value
			var value = rowData[index];
			// add the value to the set
			tdValue.add(value);
		}
	}

	function executeRawQuery(that){
		var rawQuery = $('#rawQueryId').val();
		var aiMode = $("input[name='aiMode']:checked").val();
		if (aiMode == undefined) {
			aiMode = 'false';
		}
		if (rawQuery == '') {
			alert('Please enter the query');
			return;
		}
		var beastModeCodeId = $('#beastModeCodeId').val();
		// base64 encode the query
		rawQuery = btoa(rawQuery);

		// show the loader
		loderDivIdShow();

		// empty the table
		$('#dynamicTableId').empty();

		$.ajax({
			url : "${contextPath}/table/executeRawQuery",
			type : "GET",
			data : {
				"query" : rawQuery,
				"beastModeCode" : beastModeCodeId,
				"isAiBaseSearch" : aiMode
			},
			success : function(response) {
				debugger;
				// hide the loader
				loderDivIdHide();
				var data = response.data;
				let queryType = data.queryType;
				if (queryType == 'select') {
					dynamicTableMaking(data);
				}else if(queryType == 'update' || queryType == 'delete' || queryType == 'insert'){
					$('#dynamicTableId').empty();
					$('#dynamicTableId').append('<thead style="background: #0af4f4;"><tr><th>Query Type</th><th>Rows Affected</th></tr></thead>');
					$('#dynamicTableId').append('<tbody><tr><td>'+queryType+'</td><td>'+data.rowsAffected+'</td></tr></tbody>');
				}
				
			},
			error : function(e) {
				// show the error message for 5 seconds
				loderDivIdHide();
				$('#errorMessageSpanId').text(e.responseJSON.message);
				$('#errorMessageId').show();
				setTimeout(function(){
					$('#errorMessageId').hide();
				}, 5000);

			}
		});
	}

	function loderDivIdShow(){
		$('.loderDivId').attr('style', 'display: flex; justify-content: center;');
	}

	function loderDivIdHide(){
		$('.loderDivId').attr('style', 'display: none;');
	}

	
	


</script>


