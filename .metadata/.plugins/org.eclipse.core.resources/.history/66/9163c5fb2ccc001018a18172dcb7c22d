<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<sec:authentication var="principal" property="principal" />
<c:set var="userDynamicEntityIdAndLvl" value="${principal.currentUserVo.currentUserEntityIdAndUserLevel}"/>

<style>
	:root {
  --line-color: #a13131;
  --line-width: .1em;
  --gutter: .5em;
}

body {
  font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,Oxygen-Sans,Ubuntu,Cantarell,"Helvetica Neue",sans-serif;
}

*, *:before, *:after {box-sizing: border-box;}

	.tree {
  margin: 0 0 calc(var(--gutter) * 2);
  text-align: center;
  &, & ul, & li {
    list-style: none;
    margin: 0;
    padding: 0;
    position: relative;
  }
  &, & ul {display: table;}
  & ul {width: 100%;}
  & li {
    display: table-cell;
    padding: var(--gutter) 0;
    vertical-align: top;
    padding: 8px 10px;
  }
  & li:before {
    content: "";
    left: 0;
    outline: solid calc(var(--line-width) /2) var(--line-color);
    position: absolute;
    right: 0;
    top: 0;    
  }
  & li:first-child:before {left:  50%;}
  & li:last-child:before  {right: 50%;}
  & button {
    border-radius: .2em;
    margin: 0 calc(var(--gutter) / 2) var(--gutter);
    min-height: 2.1em;
    position: relative;
    z-index: 1;
  }
  & [contenteditable] {cursor: text;}
  & .selected {
    border-color: #900;
    border-style: dashed;
    -webkit-box-shadow: 0 0 var(--gutter) var(--line-width) rgba(153,0,0,.3);
       -moz-box-shadow: 0 0 var(--gutter) var(--line-width) rgba(153,0,0,.3);
            box-shadow: 0 0 var(--gutter) var(--line-width) rgba(153,0,0,.3);
  }
  & ul:before,
  & button:before, select:before {
    outline: solid calc(var(--line-width) / 2) var(--line-color);
    content: "";
    height: var(--gutter);
    left: 50%;
    position: absolute;
    top: calc(calc(-1 * var(--gutter)) - calc(var(--line-width) / 2));
  }
  & > li {margin-top: 0;}
  & > li:before, & > li:after, & > li > button:before {outline: none;}  
  
}

button {
  -webkit-appearance: none;
     -moz-appearance: none;
          appearance: none;
  background: #fff;
  border: solid var(--line-width) var(--line-color);
  cursor: pointer;
  font-size: 1em;
  line-height: 1.2em;
  padding: .4em 1em;
  position: relative;
  &:focus, &:hover {
    outline: .1em dotted var(--line-color);
    outline-offset: -.5em;
  }
  width: 184px;
}
input[type="range"] {
  display: block;
  width: 100%;
}
input[type="color"] {
  -webkit-appearance: none;
     -moz-appearance: none;
          appearance: none;
  border: none;
  cursor: pointer;
  display: block;
  height: 2em;
  padding: 0;
  vertical-align: middle;
  width: 100%;
}

.toolbar {
  background: #9cd1f3d4;
  font-size: .9em;
  left: 0;
  margin: 0;
  padding: var(--gutter);
  position: fixed;
  right: 0;
  text-align: center;
  top: 0;
  transform: translate(0,-100%);
  transition: all .2s ease;
  z-index: 2;
}
.toolbar.show {
  transform: translate(0,0);
}

ins {
  background: #fff;
  border: solid calc(var(--line-width) /2) var(--line-color);
  display: inline-block;
  font-size: .8em;
  left: -1em;
  margin: 1em 0 0;
  padding: .2em .5em;
  position: absolute;
  right: -1em;
  text-decoration: none;
  top: 100%;
  
  &:before, &:after {
    border: solid 1em transparent;
    border-top: none;
    content: "";
    left: 50%;
    position: absolute;
    transform: translate(-50%,0);
  }
  &:before {
    border-bottom-color: var(--line-color);
    bottom: 100%;
  }
  &:after  {
    bottom: calc(100% - var(--line-width));
    border-bottom-color: #fff;
  }
}
ins {
  opacity: 0;
  transition: all .2s ease;
  transform: scale(0,0);

}
.js-confirm .confirm, .js-root .root {
  opacity: 1;
  transform: scale(1,1);
}

.grid {
  display: flex;
  width: 100%;
 & > * {
    flex: 1;
    margin-left: .5em;
    margin-right: .5em;
  }
}


.makeNodeReadOnly {
	pointer-events: none;
	background-color: #f9f9f9 !important;
}

.bg-light {
  background-color: #b5b1b1 !important;
  pointer-events: none;
  
}
.nonPointerClass{
  background-color: #fff !important;
  pointer-events: auto;
}
.sidebarBtn{
    width: auto;
}
.modal-footer .btn{
    width: auto;
}
</style>


<div class="breadcrumb_conatiner">
              <div class="col-md-6">
                <h4 class="change-color">Hierarchy Configuration</h4>
              </div>
              <div class="col-md-6">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="${contextPath}/home"><i class="fa fa-home"></i></a></li>
					<li class="breadcrumb-item active" aria-current="page">Management</li>
                  </ol>
                </nav>
              </div>
</div>
<div class="row">
	
	<div class="col-md-12">
		<div class="card">
			<!-- <div class="card-header">
				<h5 class="card-title">Add Shop</h5>
			</div> -->
			<div class="card-body" style="overflow: auto;">
				<p role="toolbar" aria-label="Node tools" aria-hidden="true" class="toolbar">
					<button type="button" data-js="promoteSibling">&laquo; Move left</button>
					<button type="button" data-js="demoteSibling">Move right &raquo;</button>
					<button type="button" data-js="addChild">+ Add new</button>
					<button type="button" data-js="deleteNode">
					  &times; Delete
					  <ins role="alert" aria-hidden="true" class="confirm" aria-label="Please confirm deletion">Are you sure? (press again to continue)</ins>
					  <ins role="alert" aria-hidden="true" class="root" aria-label="You can't delete root">You can't delete the root node</ins>
					</button>
					<!-- <button type="button" data-js="editName"><i class="fa fa-pencil"></i>  Edit name</button> -->
				</p>
				  
				  <details>
					<summary>Customise your tree view</summary>
					<div class="grid">
					  <p><label for="line-color">Line colour</label> <input type="color" id="line-color" name="line-color" value="#666"></p>
					  <p><label for="line-width">Line width</label> <input type="range" id="line-width" name="line-width" value="1" min="1" max="10"></p>
					  <p><label for="gutter">Gutters</label> <input type="range" id="gutter" name="gutter" value="5" min="1" max="10"></p>
					</div>
				  </details>
			    <c:choose>
			      <c:when test="${!empty hierarchyList && hierarchyList.size() > 0}">
			        <h5 style="text-align: right; color: #a13131;">Map Each Entity with its Parent Entity</h5>
			      </c:when>
			      <c:otherwise>
			        <h5 style="text-align: right; color: #a13131;">This configuration is one time setup. Please configure it carefully.</h5>
			      </c:otherwise>
			    </c:choose>
				  
				  
			
				  <div class="row">
					<div class="form-group col-md-2">
						<label>Organization Name <span class="requiredStarMark">${isMandatory ? '*' : ''}</span></label>
						<div class="col-md-12">
							<!-- if officeDetails.objectIdAndType is there then readonly -->
							<select name="objectIdAndType"  class="form-control form-control-sm ${isReadOnly ? 'isEntityObjReadOnly' : ''}  ${isMandatory ? 'requiredField' : ''}" id="objectIdAndTypeId" onchange="getEntitySpecificOrganizationStructure(this)">
								<option value="">Select Organization</option>
								<c:forEach items="${organizationList}" var="organization">
									<option value="${organization.combineTwo}" <c:if test="${organization.combineTwo eq selectedEntityObjectIdAndValue}">selected</c:if>>${organization.organizationName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				  </div>
			
			    <c:choose>
			      <c:when test="${!empty hierarchyList && hierarchyList.size() > 0}">
			        <!-- a OrganizationStructureParentChild wit key value create this type Ui -->
			          <c:set var="firstHierarchy" value="${hierarchyList[0]}" />
			        <ul class="tree">
			          <li>
			            <button type="button" aria-pressed="false" data-js="node">
			            <input type="hidden" value="${firstHierarchy.id}" id="orgHrhId" />
			            <input type="hidden" value="${firstHierarchy.levelHeight}" id="levelHeightId" />
			            <input type="hidden" value="${orgNameId}" id="orgNameId" />
			            <select name=""  class="form-control form-control-sm makeNodeReadOnly" id="organizationLevelListId">
			              <option value="${entityLevel}">${orgName}</option>
			            </select>
			            </button>
			            
			            
			            
			            <!-- Recusive call to crate Ul li tag -->
			            <ul>
			              <c:forEach items="${hierarchyList}" var="hierarchy" varStatus="status">
			                <c:set var="childHierarchy" value="${hierarchy.children}" scope="request" />
			                <c:set var="organizationLevelListPage" value="${organizationLevelList}" scope="request" />
			                <jsp:include page="nodeHierarchy.jsp" />
			              </c:forEach>
			            </ul>
			          </li>
			        </ul>
			      </c:when>
			      
			      <c:otherwise>
			        <ul class="tree">
			          <li>
			            <button type="button" aria-pressed="false" data-js="node">
			              <input type="hidden" value="" id="orgHrhId" />
			              <input type="hidden" value="0" id="levelHeightId" />
			              <input type="hidden" value="${orgNameId}" id="orgNameId" />
			              <select name=""  class="form-control form-control-sm" id="organizationLevelListId" onchange="">
			                <option value="">Select Organization Level</option>
			                <option value="${entityLevel}">${orgName}</option>
			              </select>
			            </button>
			            
			            <ul>
			            
			            </ul>
			          </li>
			        </ul>
			      </c:otherwise>
				  </c:choose>
			
				  <!-- two Button submit and refresh  -->
			    <c:if test="${empty hierarchyList && hierarchyList.size() eq 0}">
			      <div class="form-group col-md-2 d-flex gap-2 mt-5">
			        <input type="button" class="btn btn-primary"  onclick="saveOrganizationStructure()"  value="Save" />
			        <input type="button" class="btn btn-primary"  onclick="reConfigOrganizationStructure()" value="Re-Config" />
			      </div>
			    </c:if>
			</div>
		</div>
	</div>	 
</div>
	
<form action="${contextPath}/system/save-structure" method="POST" id="organizationForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="organizationStructureArr" id="organizationStructureArr" />
</form>

<form action="${contextPath}/office/reConfig-structure" method="POST" id="reConfigForm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="objectIdAndTypeEncode" id="objectIdAndTypeEncode" />
</form>

<form action="${contextPath}/system/create-structure" method="GET" id="getEntitySpecificOrganizationStructureForm">
	<input type="hidden" name="filterData" value="" id="filterDataId" />
  <input type="hidden" name="textOrgName" value="" id="textOrgName" />
</form>


<!-- Create a bootstap5 modal having head and body in body a table sl, Name, chekedbox -->
<div class="modal fade" id="entitySpecificOrganizationStructureModal" tabindex="-1" aria-labelledby="entitySpecificOrganizationStructureModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="entitySpecificOrganizationStructureModalLabel">Entity Specific Mapping</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <h6 class="text-center" id="entitySpecificModalBodyTitle"></h6>
        <input type="hidden" id="crntHierarchyId" />
        <input type="hidden" id="crntHierarchyLevelHeight" />
        <input type="hidden" id="crntHierarchyLevelCode" />
        <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th>Sl.No</th>
              <th>Entity Name</th>
              <th>Is Selected</th>
              <th>Parent Entity</th>
              <!-- <th>Hierarchy View</th> -->
            </tr>
          </thead>
          <tbody id="entitySpecificOrganizationStructureModalBody">
          </tbody>
          
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="saveEntitySpecificOrganizationStructure()">Map Entity</button>
      </div>
    </div>
  </div>
</div>



<script>

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



  let levelData = [];
  async function getLevels(){
    let url = '${pageContext.request.contextPath}/system/getLevels';
    let data = {};
    let method = 'GET';
    let response = await asyncAjaxForData(url, data, method);
    if(response.outcome){
      let data = response.data;
      data.forEach(function(item){
        let levelObjData = {};
        levelObjData.levelCode = item.levelCode;
        levelObjData.mappingType = item.mappingType;
        levelData.push(levelObjData);
      });
    }
  }


var root         = document.documentElement,
    toolBar      = document.querySelector('[role="toolbar"]'),
    colorInput   = document.querySelector('#line-color'),
    lineInput    = document.querySelector('#line-width'),
    gutterInput  = document.querySelector('#gutter'),
    alertRoot    = document.querySelector('[data-js="deleteNode"] .root'),
    alertConfirm = document.querySelector('[data-js="deleteNode"] .confirm');
// All the button and body clicks are intercepted here.
document.addEventListener('click', function (e) {
  var clickType = e.target.getAttribute('data-js');
  // User has selected a node
  if (clickType === 'node') {
    selectNode(e);
  } else if (clickType !== '' && clickType !== null) {
    // Buttons within the toolbar, at the top of the page
    if      (clickType === 'promoteSibling') promoteSibling();
    else if (clickType === 'demoteSibling')  demoteSibling();
   // else if (clickType === 'editName')       editName();
    else if (clickType === 'deleteNode')     deleteNode(e);
    else if (clickType === 'addChild')       addChild();
  } else {
    // User has clicked outside of a node
    deselectNodes();
    hideToolbar();
  }
});

// Customise views events
colorInput.addEventListener('change', lineColor);
lineInput.addEventListener('change', lineWidth);
gutterInput.addEventListener('change', gutterWidth);

function lineColor(e) {
  root.style.setProperty('--line-color', e.target.value);
}
function lineWidth(e) {
  root.style.setProperty('--line-width', (e.target.value / 10) + 'em');
}
function gutterWidth(e) {
  root.style.setProperty('--gutter', (e.target.value / 10) + 'em');
}

// Allows the user to reorder the tree with the keyboard
root.addEventListener('keydown', function (e) {
  var keyPress;
  // New method vs. old method
  if (e.key) keyPress = e.key;
  else       keyPress = e.which;
  // If the user is editing a node name, they might need to use the arrow keys As God Intended
  if (e.target.getAttribute('contenteditable')) {
    if (keyPress === ' ' || keyPress === '32') {
      insertTextAtCursor(' ');
    }
  } else {
    if (keyPress === 'ArrowRight' || keyPress === '37') {
      demoteSibling();
    } else if (keyPress === 'ArrowLeft' || keyPress === '39') {
      promoteSibling();
    }
  }
  // This is useful whether the user is editing the button or not
  if (keyPress === 'ArrowDown' || keyPress === '40') {
    addChild();
  }
});

// Deselects all other nodes, selects the current node and hoyks in the toolber
function selectNode(e) {
  var clicker = e.target;
  // Hang on - do we need to do anything?
  if (clicker.getAttribute('aria-pressed') === 'false') {
    deselectNodes();
    clicker.setAttribute('aria-pressed', 'true');
    clicker.classList.add('selected');
    showToolbar();   
  }
}

// Bit of cleanup, after the user has finished editing the tree.
function deselectNodes() {
  // This needs to run from scratch as new nodes might have been added
  var selectedBtns = [...document.querySelectorAll('.tree [aria-pressed="true"]')],
      btnDelete = document.querySelector('[data-js="deleteNode"]'),
      editBtns = [...document.querySelectorAll('.tree [contenteditable]')];
  // I mean, in theory, there should only be one selected button, but, you know, bugs...
  for (var i = 0; i < selectedBtns.length; i++) {
    selectedBtns[i].setAttribute('aria-pressed', 'false');
    selectedBtns[i].classList.remove('selected');
  }
  // Bit of cleanup, in case the user noped out of deleting a node
  if (btnDelete.classList.contains('js-confirm')) {
    btnDelete.classList.remove('js-confirm');
    alertConfirm.setAttribute('aria-hidden','true');
  }
  if (btnDelete.classList.contains('js-root')) {
    btnDelete.classList.remove('js-root');
    alertRoot.setAttribute('aria-hidden','true');
  }
  // Checks for new nodes which are editable, then turns them off.
  for (var i = 0; i < editBtns.length; i++) {
    editBtns[i].removeAttribute('contenteditable');
  }
}

function showToolbar() {
  toolBar.removeAttribute('aria-hidden');
  toolBar.classList.add('show');
}

function hideToolbar() {
  toolBar.setAttribute('aria-hidden','true');
  toolBar.classList.remove('show');
}

// Moves the sibling to the left
function promoteSibling() {
  if (document.querySelector('.tree .selected')) {
    var favouriteChild = document.querySelector('.tree .selected').parentNode,
        elderChild = favouriteChild.previousElementSibling;
    // Does this selected element have anywhere to go?
    if (elderChild) {
      favouriteChild.parentNode.insertBefore(favouriteChild,elderChild);
    }    
  }
}

// Moves the sibling to the right
function demoteSibling() {
  if (document.querySelector('.tree .selected')) {
    var chosenChild = document.querySelector('.tree .selected').parentNode,
        youngerChild = chosenChild.nextElementSibling;
    // Does this selected element have anywhere to go?
    if (youngerChild) {
      chosenChild.parentNode.insertBefore(youngerChild,chosenChild);
    }    
  }
}

// Allows the user to rename existing nodes
function editName() {
  var chosenChild = document.querySelector('.tree .selected');
  // remove makeNodeReadOnly class from select
  chosenChild.querySelector('select').classList.remove('makeNodeReadOnly');
}

// Removes the node and it's children
function deleteNode(e) {
  var chosenChild  = document.querySelector('.tree .selected'),
      delButton    = e.target,
      isRoot       = chosenChild.parentNode.parentNode.classList.contains('tree');
  
  // Is the user trying to delete the root node?
  if (isRoot) {
    delButton.classList.add('js-root');
    alertRoot.removeAttribute('aria-hidden');
  }
  // Has the user clicked the delete button once already?
  else if (delButton.classList.contains('js-confirm')) {
    // Is there more than one sibling?
    if (chosenChild.parentNode.parentNode.childElementCount > 1) {
      chosenChild.parentNode.remove();
    } else { // Remove the whole list
      chosenChild.parentNode.parentNode.remove();
    }
    deselectNodes();
    hideToolbar();
  } else {
    delButton.classList.add('js-confirm');
    alertConfirm.removeAttribute('aria-hidden');
  }
}

function checkInSameLevelIsExist(that){
  // check if in smae level hight is exist or not
  let value = $(that).val();
  let selectedParent = $(that).parent().parent();
  let allSameLevelSibling = selectedParent.siblings();
  let isExist = false;
  allSameLevelSibling.each(function(){
    let selectValue = $(this).find('select').val();
    if(selectValue == value){
      isExist = true;
    }
  });
  if(isExist){
    bootbox.alert("This organization level is already exist in same level");
    $(that).val('');
  }


}



// page load
document.addEventListener('DOMContentLoaded', function(){
	getLevels();
});



function createOptions(){
	var html = '<option value="">Select Organization Level</option>';
	for(var i=0; i<levelData.length; i++){
		// append options to html
		html += '<option value="'+levelData[i].levelCode+'">'+levelData[i].levelCode+'</option>';
	}
	return html;
}

// Adds a new node under the current node
function addChild() {
  if (document.querySelector('.tree .selected')) {
   let vl =  document.querySelector('.tree .selected').querySelector('select').value;
	// check if selected node has value if not then return
	if(vl == ''){
		return;
	}

  // get that object from levelData where key is vl
  let obj = levelData.find(o => o.levelCode === vl);
  
  if(obj != undefined && obj.mappingType == 'ONE-TO-MANY'){
    bootbox.alert("This organization level is one to many mapping type so you can't add child");
    return;
  }
  


  var chosenNode = document.querySelector('.tree .selected').parentNode,
  listItem = document.createElement('li');
  // second input hidden field for level height
  let selectedParent = document.querySelector('.tree .selected');
  // there is two hidden input field type children first one for id and second is for level height
  var hiddenHrgIdAndLevelHeightArr = selectedParent.querySelectorAll('input[type="hidden"]');
  var levelHeight = parseInt(hiddenHrgIdAndLevelHeightArr[1].value) + 1;

	let html = '<button type="button" aria-pressed="false" data-js="node" contenteditable="true">';
		html += '<input type="hidden" value="" />';
    html += '<input type="hidden" value="'+levelHeight+'" />';
		html += '<select name=""  class="form-control form-control-sm" onchange="checkInSameLevelIsExist(this)">';
		html += createOptions();
    html += '</select>';
		html += '</button>';
		html += '<i class="fas fa-link" onclick="getLevelSpecificEntity(this)" style=" top: 28px; position:absolute"></i>';




	listItem.innerHTML = html;
    // The current node already has kids
    if (chosenNode.querySelector('ul')) {
      var chosenKids = chosenNode.querySelector('ul');
      chosenKids.appendChild(listItem);
      chosenKids.lastChild.querySelector('button').focus();
    } else { // The current node has no kids
      var newDad = document.createElement('ul');
      newDad.appendChild(listItem);
      chosenNode.appendChild(newDad);
      chosenNode.lastChild.querySelector('button').focus();
    }
  }
}

// Because each node is a button tag, the space bar event is captured, when the user is editing.
// This is used as a work-around.
function insertTextAtCursor(text) {
    var sel, range;
    if (window.getSelection) {
        sel = window.getSelection();
        if (sel.getRangeAt && sel.rangeCount) {
            range = sel.getRangeAt(0);
            range.deleteContents();
            range.insertNode( document.createTextNode(text) );
        }
    } else if (document.selection && document.selection.createRange) {
        document.selection.createRange().text = text;
    }
}

function saveOrganizationStructure(){
	let objectIdAndTypeId = document.getElementById('objectIdAndTypeId').value;
	if(objectIdAndTypeId == '' || objectIdAndTypeId == null || objectIdAndTypeId == undefined){
		bootbox.alert("Please select organization");
		return;
	}
	let organizationLevelListId = document.getElementById('organizationLevelListId').value;
	if(organizationLevelListId == '' || organizationLevelListId == null || organizationLevelListId == undefined){
		bootbox.alert("Please select at least one organization level");
		return;
	}
	// get all ul from tree as parent child relation recursively
	var ul = document.querySelector('.tree ul');
	if(ul){
		var parentChildRelation = [];
		// start node value is $organizationLevelListId
		var startNodeValue = document.getElementById('organizationLevelListId').value;
		var inputHrgId = document.getElementById('orgHrhId').value;
    var levelHeight = document.getElementById('levelHeightId').value;
    var orgNameId = document.getElementById('orgNameId').value;
    var orgName = document.getElementById('organizationLevelListId').options[document.getElementById('organizationLevelListId').selectedIndex].text;
		// get parent child relation recursively
		parentChildRelation.push({value: startNodeValue, children: getChildAsTreeByRecursion(ul), hrgId: inputHrgId, levelHeight: levelHeight, orgNameId: orgNameId, orgName: orgName});
		console.log(parentChildRelation);
		// encode base64 
		let finalObj = {};
		finalObj['forObjectIdAndTypeId'] = objectIdAndTypeId;
		finalObj['organizationStructure'] = parentChildRelation;
		let finalObjEncode = btoa(JSON.stringify(finalObj));
		// set the value to form hidden input field
		$('#organizationStructureArr').val(finalObjEncode);
		// submit the form
		bootbox.confirm("Do you want to save this organization structure?",
			function(result) {
				if (result == true) {
					$('#organizationForm').submit();
				}
		});
		
	}
}

// get parent child relation recursively  as tree structure
// Get parent-child relation recursively as a tree structure
function getChildAsTreeByRecursion(ul) {
  debugger;
    var tree = [];
    
    // Get all li elements within the ul (each represents a node)
    var lis = ul.children;
    
    // Iterate over each li element (node)
    for (var i = 0; i < lis.length; i++) {
        var li = lis[i];
        
        // Get the current node value (assuming the node value is in the select element)
        var nodeValue = li.querySelector('select').value;
        var hiddenHrgIdAndLevelHeightArr = li.querySelectorAll('input[type="hidden"]');
        var hrgId = hiddenHrgIdAndLevelHeightArr[0].value;
        var levelHeight = hiddenHrgIdAndLevelHeightArr[1].value;
        var orgNameId = 0;
        var orgName = '';

        if(nodeValue == ''){
          bootbox.alert("Please select Level");
          // throw new Error("Please select organization level");
          throw new Error("Please select Level");
        }


        // Create an object for the current node
        var nodeObject = { value: nodeValue, children: [] , hrgId: hrgId, levelHeight: levelHeight, orgNameId: orgNameId, orgName: orgName};
        
        // If the current li has a nested ul, recurse into it to get children
        var nestedUl = li.querySelector('ul');
        if (nestedUl) {
            // Recursively get child relations for this node
            nodeObject.children = getChildAsTreeByRecursion(nestedUl);
        }
        // Add the nodeObject to the tree array
        tree.push(nodeObject);
    }
    
    return tree;
}

function reConfigOrganizationStructure(){
	// refresh the page
	location.reload();
	// var selectedNode = document.querySelector('.tree .selected');
	// if(selectedNode){
	// 	var selectedNodeValue = selectedNode.querySelector('select').value;
	// 	if(selectedNodeValue != ''){
	// 		$('#objectIdAndTypeEncode').val(btoa(selectedNodeValue));
	// 		$('#reConfigForm').submit();
	// 	}
	// }
}

function getEntitySpecificOrganizationStructure(that) {
	var entityIdAndType = $(that).val();
  var text = $(that).find('option:selected').text();
	if(entityIdAndType != ''){
		$('#filterDataId').val(btoa(entityIdAndType));
    $('#textOrgName').val(btoa(text));
		$('#getEntitySpecificOrganizationStructureForm').submit();
	}
}



async function getLevelSpecificEntity(that){
  debugger;
  // this object sibling a button  that button inside a select tag is present that select option value
  let thatButton = $(that).siblings('button');
  let insideSelect = $(thatButton).find('select');
  var levelCode = $(insideSelect).val();
  var orgIdArr = $(thatButton).find('input[type="hidden"]');
  var orgId = orgIdArr[0].value;
  var levelHeight = orgIdArr[1].value;
  var objectIdAndTypeId = $('#objectIdAndTypeId').val();
    if(levelCode != '' && objectIdAndTypeId != ''){
      let url = '${pageContext.request.contextPath}/system/getEntitySpecificEntity';
      let data = {
          levelCode: btoa(levelCode),
          entityIdAndTypeEncode: btoa(objectIdAndTypeId),
          orgId: btoa(orgId),
          levelHeight: btoa(levelHeight)
        };
      let method = 'GET';
      let response = await asyncAjaxForData(url, data, method);
      if(response.outcome){
      let data = response.data;
      let html = '';
      let firstTime = true;
      let parrentArr = [];
      for(var i=0; i<data.length; i++){

            // no contain null
          if(firstTime){
            parrentArr = data[i].parentData;
            firstTime = false;
          }
          let idAndEntityCode = data[i].parentEntityIdAndCode;
          let isSelectedEntity = data[i].isSelected;

          html += '<tr >';
          html += '<td class="'+(isSelectedEntity ? 'bg-light' : '')+'">'+(i+1)+'</td>';
          html += '<td class="'+(isSelectedEntity ? 'bg-light' : '')+'">'+data[i].entityName+'</td>';
          // if isReadOnly is true then make checkbox readonly and checked
          html += '<input type="hidden" value="'+data[i].entityName+'" id="entityName'+data[i].id+' ">';
          html += '<td class="'+(isSelectedEntity ? 'bg-light' : '')+'"><input type="checkbox" value="'+data[i].id+'" '+(isSelectedEntity ? 'checked' : '')+'></td>';
          
          let parentHtml = '<select class="form-control form-control-sm selectpicker" multiple data-live-search="true">';
          parentHtml += '<option value="">Select Parent</option>';
          
          for(var j=0; j<parrentArr.length; j++){
            let parentEntityIdAndCode = parrentArr[j].idAndEntityCode;
            let isSelected = parentEntityIdAndCode == idAndEntityCode ? 'selected' : '';
            parentHtml += '<option value="'+parentEntityIdAndCode+'" '+isSelected+'>'+parrentArr[j].entityName+'</option>';

            let mappingType = parrentArr[j].mappingType;
            if(mappingType == 'ONE-TO-MANY-FIX' && isSelected == 'selected'){
              // remove this object from parrentArr
              parrentArr.splice(j, 1);
            }else if(mappingType == 'ONE-TO-ONE'){
              // remove selectpicker class from select and multiple attribute
              parentHtml = parentHtml.replace('selectpicker', '');
              parentHtml = parentHtml.replace('multiple', '');
            }
          }

          // if any thing is selected then make checkbox 

          parentHtml += '</select>';
          html += '<td class="'+(isSelectedEntity ? 'bg-light' : '')+'">'+parentHtml+'</td>';
          // html += '<td>';
          // html += isSelectedEntity ? '<i class="fa fa-eye" onclick="showHierarchyView('+data[i].id+')"></i>' : 'N/A';
          // html += '</td>';
          html += '</tr>';


      }
        $('#entitySpecificModalBodyTitle').html(levelCode);
        $('#crntHierarchyId').val(orgId);
        $('#crntHierarchyLevelHeight').val(levelHeight);
        $('#crntHierarchyLevelCode').val(levelCode);
        $('#entitySpecificOrganizationStructureModalBody').html(html);

        // refresh the selectpicker
        $('.selectpicker').selectpicker('refresh');


        $('#entitySpecificOrganizationStructureModal').modal('show');

    }
  } else{
    bootbox.alert("Please select organization and organization level");
  }
}



async function saveEntitySpecificOrganizationStructure(){
  let selectedEntityIds = [];
  // each tr inside tbody of modal ittirate and find checkbox is checked or not
  let isAllelectDropdownValue = true;
  $('#entitySpecificOrganizationStructureModalBody tr').each(function(){
    debugger;
    // if this tr contain makeNodeReadOnly class then continue
    if($(this).hasClass('bg-light')){
      return;
    }

    let isChecked = $(this).find('input[type="checkbox"]').is(':checked');
    if(isChecked){
      let checkedId = $(this).find('input[type="checkbox"]').val();
      let selectedParent = $(this).find('select').val();
      // if selectedParent is not Array type then make it array
      if(!Array.isArray(selectedParent)){
        selectedParent = [selectedParent];
      }
      if(selectedParent == ''){
        isAllelectDropdownValue = false;
      }
      // get Array of selected parent name
      let selectedParentNameArr = $(this).find('select').find('option:selected');
      let selectedParentName = [];
      selectedParentNameArr.each(function(){
        selectedParentName.push($(this).text());
      });


      let entityName = $(this).find('input[type="hidden"]').val();
      selectedEntityIds.push({entityId: checkedId, parentEntityId: selectedParent, parentEntityName: selectedParentName, entityName: entityName});
    }
  });

  if(!isAllelectDropdownValue){
    bootbox.alert("Please select parent for all selected entity");
    return;
  }

  let crntHierarchyId = $('#crntHierarchyId').val();
  let levelHeight = $('#crntHierarchyLevelHeight').val();
  let levelCode = $('#crntHierarchyLevelCode').val();
  let objectIdAndTypeId = $('#objectIdAndTypeId').val();

  if(selectedEntityIds.length > 0 && levelCode != '' && objectIdAndTypeId != ''){
    let url = '${pageContext.request.contextPath}/system/saveEntitySpecificEntity';
    let finalObjEncode = {
      selectedEntityIds: selectedEntityIds,
      levelCode: levelCode,
      entityIdAndType: objectIdAndTypeId,
      orgId: crntHierarchyId,
      levelHeight: levelHeight,
    };
    let method = 'GET';
    let data = {'mappingData': btoa(JSON.stringify(finalObjEncode))};
    console.log(finalObjEncode);
    let response = await asyncAjaxForData(url, data, method);
    if(response.outcome){
      bootbox.alert(response.message);
      $('#entitySpecificOrganizationStructureModal').modal('hide');
    }
  }else{
    bootbox.alert("Please select at least one entity");
  }
  
  
}

  async function showHierarchyView(id) {
    let levl = $('#crntHierarchyLevelCode').val();
    let idAndLevel = id+'##'+levl;
    let url = '${pageContext.request.contextPath}/system/getHierarchyView';
    let data = {entityIdAndType: btoa(idAndLevel)};
    let method = 'GET';
    let response = await asyncAjaxForData(url, data, method);
    debugger;
    if(response.outcome){
      let data = response.data;
      let html = '';
      let parent = data.parent;
      let children = data.child;
      let parentHtml = '';
      
    }
  }



  function createTree(data) {
      const ul = document.createElement('ul');

      data.forEach(member => {
          const li = document.createElement('li');
          const a = document.createElement('a');
          a.href = "javascript:void(0);";

          const memberBox = document.createElement('div');
          memberBox.className = 'member-view-box';
          
          const memberImage = document.createElement('div');
          memberImage.className = 'member-image';
          
          const img = document.createElement('img');
          img.src = 'https://image.flaticon.com/icons/svg/145/145867.svg';
          img.alt = 'Member';

          const memberDetails = document.createElement('div');
          memberDetails.className = 'member-details';
          const h3 = document.createElement('h3');
          h3.textContent = member.name;

          memberDetails.appendChild(h3);
          memberImage.appendChild(img);
          memberImage.appendChild(memberDetails);
          memberBox.appendChild(memberImage);
          a.appendChild(memberBox);
          li.appendChild(a);

          // Recursively create child nodes
          if (member.children && member.children.length > 0) {
              li.appendChild(createTree(member.children));
          }

          ul.appendChild(li);
      });

      return ul;
  }
document.querySelectorAll('.tree li ul li .fa-link').forEach((element, index) => {
    const parentLi = element.closest('li'); // Find the closest parent <li>
    const parentUl = parentLi.parentNode;  // Get the parent <ul>
    const firstChild = parentUl.querySelector('li:first-child .fa-link'); // First child .fa-link

    // if (element !== firstChild) {
    //     element.style.display = 'none'; // Hide all except first
    // }
});










</script>
	
