<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Project setup</title>

    <!-- Favicon (optional - add your own) -->
    <!-- <link rel="icon" href="${contextPath}/assets/img/favicon.ico" type="image/x-icon"> -->

    <!-- Google Font: Rajdhani (Modern & Professional) -->
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@300;400;500;600;700&display=swap" rel="stylesheet">

    <!-- Font Awesome -->
    <link href="${contextPath}/assets/fonts/css/all.min.css" rel="stylesheet">

    <!-- Bootstrap 5 + Popper (Bundle includes Popper) -->
    <link href="${contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Select Picker -->
    <link href="${contextPath}/assets/vendor/bootstrap-selectpicker/bootstrap-select.min.css" rel="stylesheet">

    <!-- DataTables -->
    <link href="${contextPath}/assets/vendor/datatable/datatables.min.css" rel="stylesheet">

    <!-- Datepicker -->
    <link href="${contextPath}/assets/vendor/datepicker/jquery.datepick.css" rel="stylesheet">

    <!-- Custom Project CSS -->
    <link href="${contextPath}/assets/css/style.css" rel="stylesheet">

    <!-- =================================== -->
    <!-- CRITICAL: Load jQuery & Bootbox FIRST -->
    <!-- =================================== -->
    <script src="${contextPath}/assets/js/jquery.js"></script>
    <script src="${contextPath}/assets/vendor/bootbox/bootbox.all.min.js"></script>
    <script src="${contextPath}/assets/utilsJs/dataEncryption.js"></script>

    <style>
        body {
            font-family: 'Rajdhani', sans-serif;
            background-color: #f8f9fa;
        }
    </style>
</head>

<body>

    <!-- Global Message Include (Success/Error Alerts) -->
    <%@ include file="/WEB-INF/views/message.jsp"%>

    <!-- Dynamic Body Content from Tiles -->
    <tiles:insertAttribute name="body" />

    <!-- Hidden Form for Module Navigation -->
    <form id="moduleForm" action="${contextPath}/moduleDirectory" method="post" style="display: none;">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="hidden" name="moduleCode" id="moduleId" />
    </form>

    <!-- ==================== ALL SCRIPTS AT BOTTOM ==================== -->

    <!-- Bootstrap Bundle (includes Popper) -->
    <script src="${contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Bootstrap Select Picker -->
    <script src="${contextPath}/assets/vendor/bootstrap-selectpicker/bootstrap-select.min.js"></script>

    <!-- DataTables -->
    <script src="${contextPath}/assets/vendor/datatable/datatables.min.js"></script>

    <!-- Datepicker Dependencies -->
    <script src="${contextPath}/assets/vendor/datepicker/jquery.plugin.min.js"></script>
    <script src="${contextPath}/assets/vendor/datepicker/jquery.datepick.min.js"></script>

    <!-- Encryption Libraries -->
    <script src="${contextPath}/assets/js/encrypt/AesUtil.js"></script>
    <script src="${contextPath}/assets/js/encrypt/aes.js"></script>
    <script src="${contextPath}/assets/js/encrypt/pbkdf2.js"></script>
    <script src="${contextPath}/assets/js/encrypt/lbase.js"></script>

    <!-- Custom Utilities & Helpers -->
    <script>
        // Make context path globally available
        window.ctxPath = '${contextPath}';
        window.contextPath = '${contextPath}'; // backward compatibility
    </script>

    <script src="${contextPath}/assets/js/custom.js"></script>
    <script src="${contextPath}/assets/utilsJs/commonFunctions.js"></script>
    <script src="${contextPath}/assets/utilsJs/helperFunctionsScripts.js"></script>
    <script src="${contextPath}/assets/utilsJs/validate.js"></script>

    <!-- Back to Module Functionality (Safe & Improved) -->
    <script type="text/javascript">
        function backToModule() {
            if (typeof enc_password !== 'function') {
                console.error('Encryption function enc_password() is not available!');
                bootbox.alert("System error: Encryption module not loaded.");
                return;
            }

            const currentModuleCode = localStorage.getItem('currentModuleCode');

            if (!currentModuleCode) {
                bootbox.alert("No module selected. Cannot redirect.");
                console.warn("currentModuleCode not found in localStorage");
                return;
            }

            try {
                $('#moduleId').val(enc_password(currentModuleCode.trim()));
                $('#moduleForm').submit();
            } catch (e) {
                console.error("Error during module redirect:", e);
                bootbox.alert("Failed to navigate. Please try again.");
            }
        }
    </script>

</body>
</html>