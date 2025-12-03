<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<sec:authentication var="principal" property="principal" />

<style>

/* ────────────────────────────────────────────────
   MODULE GRID MENU + FULLSCREEN + CUSTOM SCROLLBAR
   FULLY DRIVEN BY :root VARIABLES (ZERO HARD-CODED COLORS)
   ──────────────────────────────────────────────── */
.dot-icon { display: inline-block; }

.dot-grid:hover { opacity: 0.8; }

.menu {
    position: absolute;
    top: 60px;
    right: 0;
    transform: translateX(-50%);
    display: flex;
    flex-wrap: wrap;
    gap: 12px;
    justify-content: center;
    background: var(--gray-200);
    padding: 15px;
    box-shadow: 0 0 0 8px rgba(187, 200, 232, 0.3);   /* #bbc8e8 with transparency */
    border-radius: var(--border-radius);
    z-index: 9999;
    max-height: 320px;
    overflow-y: auto;
    width: 300px;
    scrollbar-width: none;           /* Firefox */
}
.menu::-webkit-scrollbar { display: none; }

.menu.hidden { display: none; }

.menu a {
    min-height: 90px;
    width: 80px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    text-decoration: none;
    border-radius: 20px;
    transition: var(--transition);
    position: relative;
    overflow: hidden;
}
.menu a::after {
    content: "";
    position: absolute;
    inset: 0;
    background: rgba(187, 200, 232, 0.3);   /* same as box-shadow color */
    border-radius: 20px;
    transition: var(--transition);
    z-index: -1;
}
.menu a:hover::after { inset: 0; }
.menu a:hover img { transform: scale(1.2); }

.menu a img {
    width: 35px;
    height: 35px;
    object-fit: contain;
    transition: var(--transition);
}

.menu a h3 {
    color: var(--gray-900);
    font-size: 12px;
    margin: 10px 0 0;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    max-width: 8ch;
    transition: var(--transition);
}
.menu a:hover h3 {
    white-space: normal;
    max-width: none;
}

.expandBtn i {
    font-size: 22px;
    color: var(--white);
    transition: var(--transition);
}
.expandBtn:hover i { opacity: 0.6; }

/* ────────────────────────────────────────────────
   FULLSCREEN MODE – uses only root variables
   ──────────────────────────────────────────────── */
#body:fullscreen {
    overflow-y: auto;
    position: relative;
}
#body:fullscreen::before {
    content: "";
    position: fixed;
    inset: 0;
    /* background: url(../assets/images/erpbg.jpg) center bottom / cover no-repeat,
                rgba(125, 125, 125, 0.5); */
    background-blend-mode: multiply;
    background-attachment: fixed;
    z-index: -1;
}
#body:fullscreen .navbar {
    background: var(--primary-dark) !important;   /* deep blue in fullscreen */
}
:not(:root):fullscreen::backdrop { background: transparent; }

/* ────────────────────────────────────────────────
   CUSTOM SCROLLBAR – fully variable-based
   ──────────────────────────────────────────────── */
::-webkit-scrollbar { width: 5px; height: 5px; }
::-webkit-scrollbar-track {
    background: var(--gray-200);
    border-radius: 5px;
}
::-webkit-scrollbar-thumb {
    background: var(--accent);           /* gold/yellow from your theme */
    border-radius: 5px;
}
::-webkit-scrollbar-thumb:hover {
    background: #e5cd7b;                 /* slightly lighter gold – you can change via --accent if you want */
}
::-webkit-scrollbar-corner { background: var(--gray-200); }

/* Small icon spacing fix */
.fa-signout-inmenu:before { margin-left: 2px !important; }

</style>

<nav class="navbar navbar-expand px-2">
	<!-- Button for sidebar toggle -->
	<button class="btn sidebarBtn" type="button" data-bs-theme="dark"
		style="margin-right: 10px;">
		<span class="fa-solid fa-bars"></span>
	</button>
	<div class="logo-section">
		<img src="${contextPath}/assets/images/favicon.png"
			style="width: 100%;">
	</div>
	<div class="d-flex flex-column home-content">
		<span class="text">Management System</span>
		<p class="my-0" style="border-top: #ffffff 2px solid;">
			<small>Govt. of Odisha</small>
		</p>
	</div>
	<a href="javascript:void()" class="expandBtn ms-3 mt-1"
		onclick="toggleFullscreen()" title="Toggle Fullscreen" style=""> <i
		class="fa-solid fa-expand"></i>
	</a> <a href="javascript:void()" class="expandBtn ms-3 mt-1"
		id="restoreMessage" title="Restore Message" style="display: none;">
		<i class="fa-solid fa-expand"></i>
	</a>

	<h4 class="page-title"></h4>
	<ul class="riteside">
		<li class="select-role"><select class="form-control" name="role"
			id="roleSwitcher">
				<c:forEach items="${principal.dbUser.roles}" var="role">
					<option value="${role.roleId}"
						${role.roleId eq principal.dbUser.primaryRole.roleId ? 'selected': '' }>${role.displayName}</option>
				</c:forEach>
		</select></li>
		<!-- If principal.currentUserVo.dynamicEntities is size one then make readonly  -->
		<li class="select-role" style="display: none;"><label
			for="entitySwitcher" class="form-label"
			style="color: #ffffff; font-size: 14px; line-height: 10px;">Entity:</label>
			<select class="form-control" name="role" id="entitySwitcher">
				<option value="">Select Entity</option>
				<c:forEach items="${principal.currentUserVo.dynamicEntities}"
					var="entite">
					<option value="${entite.entityIds}"
						${entite.entityIds eq principal.currentUserVo.entityIdStr ? 'selected': '' }>${entite.entityName}</option>
				</c:forEach>
		</select></li>
		<li class="dot-icon">
			<div class="dot-grid" title="All Module">
				<div class="dot"></div>
				<div class="dot"></div>
				<div class="dot"></div>
				<div class="dot"></div>
				<div class="dot"></div>
				<div class="dot"></div>
				<div class="dot"></div>
				<div class="dot"></div>
				<div class="dot"></div>
			</div>
			<div id="icon-menu" class="menu hidden">
				<c:forEach var="module" items="${USER_MODULES}">
					<div class="col-md-4 module-data">
						<a href="#" class="single-small-card"
							onclick="navigateToModule('${module.moduleCode}' , ${module.isUnderDevelopment})">
							<div class="main-img-div">
								<img alt="img-1"
									src="${contextPath}/assets/images/appIcons/${module.moduleIcon}"
									class="main-card-img">
							</div>
							<div class="single-small-card-content">
								<h3>${module.moduleName}</h3>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
		</li>
		<li class="dropdown activeUser"><img class="dropdown-toggle" data-bs-toggle="dropdown"
			src="${contextPath}/assets/images/profile.png" alt="Profile">
			<ul class="dropdown-menu dropdown-menu-end"
				aria-labelledby="dropdownMenuLink"
				style="box-shadow: 0px 0px 10px 0px #003a7a !important;">
				<li><a class="dropdown-item" href="#"><i
						class="fa-regular fa-user"></i>${principal.dbUser.firstName}${principal.dbUser.lastName}</a></li>
				<li><a class="dropdown-item" href="#">
					<i class="fas fa-user-shield"></i>${principal.primaryRole.displayName}</a></li>
				<li><a class="dropdown-item" href="#"
					onclick="changePasswordModalOpen()"><i
						class="fa-solid fa-gear"></i>Change Password</a></li>
				<li><a class="dropdown-item" href="${contextPath}/home">
					<i class="fas fa-home"></i>Back To Home</a>
				<li><a class="dropdown-item" href="#" id="switchlogout"
					type="button"><i class="fas fa-sign-out-alt"></i>Sign Out</a></li>
			</ul></li>
	</ul>
</nav>

<!-- Modal -->
<div class="modal fade" id="resetPasswordModal"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="staticBackdropLabel">Change
					Password</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form>
					<div class="mb-3">
						<label for="newPassword" class="form-label">New Password</label> <input
							type="password" class="form-control" id="newPassword"
							placeholder="Enter new password">
					</div>
					<div class="mb-3">
						<label for="confirmPassword" class="form-label">Confirm
							Password</label> <input type="password" class="form-control"
							id="confirmPassword" placeholder="Confirm new password">
					</div>
				</form>

				<div class="col-md-12" style="background-color: burlywood;">
					<h3>Password Validation Criteria</h3>
					<ul class="">
						<li>Contains at least one uppercase letter (A-Z)</li>
						<li>Contains at least one lowercase letter (a-z)</li>
						<li>Contains at least one digit (0-9)</li>
						<li>Contains at least one special character from the list: <br>!#$%&'()*+,-./:;<=>?@[]^_`{|}
						</li>
						<li>Length must be between 8 and 15 characters</li>
					</ul>
					<p style="color: green; font-weight: bold;">Status:
						Valid/Invalid based on your input</p>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary"
					onclick="changePassword()">Save changes</button>
			</div>
		</div>
	</div>
</div>

<form method="post" action="${contextPath}/umt/logout" id="frmLogout">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> <input type="submit" style="display: none" />
</form>

<form method="post" action="${contextPath}/overwrite/umt/switchRole"
	id="frmSwitchRole">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> <input type="hidden" name="roleId"
		id="hdnRoleId" /> <input type="hidden" name="entityId"
		id="hdnEntityId" />
</form>

<form method="post"
	action="${contextPath}/umt/user/captcha/change/password"
	id="frmChangePassword">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> <input type="hidden" name="txtPass"
		id="newPasswordId" /> <input type="hidden" name="txtRePass"
		id="confirmPasswordId" />
</form>

<form id="moduleForm" action="${contextPath}/moduleDirectory"
	method="post">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> <input type="hidden" name="moduleCode"
		id="moduleId" />
</form>

<script>
	function navigateToModule(moduleCode, isUnderDevelopment){
		if(isUnderDevelopment){
			bootbox.alert("This module is under development. We know you are excited to use this module, but please wait for some time. We will notify you once it is ready.");
			return;
		}


		if(moduleCode != null && moduleCode != ''){
			$('#moduleId').val(enc_password(moduleCode));
			$('#moduleForm').submit();
		}
	}
</script>

<script>

function changePasswordModalOpen(){
    $('#resetPasswordModal').modal('show');
}

function changePassword(){
	var newPassword = $('#newPassword').val();
	var confirmPassword = $('#confirmPassword').val();
	if(newPassword == '' || confirmPassword == ''){
		bootbox.alert("Please enter new password and confirm password");
		return;
	}
	if(newPassword != confirmPassword){
		bootbox.alert("New password and confirm password does not match");
		return;
	}
	bootbox.confirm("Are you sure you want to change password ?",function(result){
		if(result){
			$('#newPasswordId').val(newPassword);
			$('#confirmPasswordId').val(confirmPassword);
			$('#frmChangePassword').submit();
		}
	});
}

	$(function() {
		$('#siteLangSelector').change(function() {
			const lang = $(this).val();
			switchLanguage(lang);
		});
	});

	$('#switchlogout').on('click', function() {
		bootbox.confirm("Are you sure you want to logout ?",function(result){
			if(result){
				showLoader();
				$("#frmLogout").submit();
			}
		});
	});

	$('#logoutFromMenu').on('click', function() {
		bootbox.confirm("Are you sure you want to logout ?",function(result){
			if(result){
				showLoader();
				$("#frmLogout").submit();
			}
		});
	});

	$("#langId1").change(function(){
		$('#lang').val(this.value);
		$('#change-lang').submit();
	});
	$(document).ready(function() {
		$('#roleSwitcher').change(function(){
			var roleId = $('#roleSwitcher').val();
			var entityIds = $('#entitySwitcher').val();
			if(roleId == null || roleId == ''){
				return;
			}

			// entityIds is like ?:?##?##... so on
			// first split by : to get the entity id and entity type
			let entityArr = entityIds.split(":");
			let entityId = entityArr[0];



			$('#hdnRoleId').val(roleId);
			$('#hdnEntityId').val(entityId);
			$('#frmSwitchRole').submit();
			
		});

		$('#entitySwitcher').change(function(){
			var roleId = $('#roleSwitcher').val();
			var entityIds = $('#entitySwitcher').val();
			if(entityIds == null || entityIds == ''){
				return;
			}

			if(roleId == null || roleId == ''){
				return;
			}

			// entityIds is like ?:?##?##... so on
			// first split by : to get the entity id and entity type
			let entityArr = entityIds.split(":");
			let entityId = entityArr[0];

			$('#hdnRoleId').val(roleId);
			$('#hdnEntityId').val(entityId);
			$('#frmSwitchRole').submit();
		
		});
	});

	// check on page load if entity is not selected and only one entity is there then select that entity and trigger change event
	$(document).ready(function() {
		if($('#entitySwitcher').val() == '' && $('#entitySwitcher option').length == 2){
			$('#entitySwitcher option').eq(1).prop('selected', true);
			$('#entitySwitcher').trigger('change');
		}
	});
	const toggler = document.querySelector(".sidebarBtn");
	toggler.addEventListener("click",function(){
	    document.querySelector("#sidebar").classList.toggle("sidebar_collapsed");
	    document.querySelector("body").classList.toggle("body_collapsed");
	});

		$(document).ready(function () {
          $('.dot-icon').on('click', function (event) {
            event.stopPropagation(); // Prevent the click from propagating to the document
            $('#icon-menu').toggleClass('hidden');
          });

          $(document).on('click', function () {
            if (!$('#icon-menu').hasClass('hidden')) {
              $('#icon-menu').addClass('hidden'); // Hide the menu if it's visible
            }
          });

          $('#icon-menu').on('click', function (event) {
            event.stopPropagation(); // Prevent clicks inside the menu from closing it
          });
        });

</script>

