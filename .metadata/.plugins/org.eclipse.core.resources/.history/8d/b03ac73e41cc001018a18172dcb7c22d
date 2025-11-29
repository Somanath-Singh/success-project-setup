<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- 🔥 CRITICAL FIX: JQUERY & BOOTBOX FIRST - BEFORE ANYTHING -->
    <script src="${contextPath}/assets/js/jquery.js"></script>
    <script src="${contextPath}/assets/vendor/bootbox/bootbox.all.js"></script>
    <script src="${contextPath}/assets/utilsJs/dataEncryption.js"></script>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Building &amp; Asset Management System</title>
    <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
    <meta name="keywords" content="CMC-SWP" />
    <meta name="description" content="CMC-SWP">
    <meta name="author" content="Aashdit Technologies">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="cache-control" content="max-age=0" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="cache-control" content="no-store" />
    <meta http-equiv="cache-control" content="pre-check=0" />
    <meta http-equiv="cache-control" content="post-check=0" />
    <meta http-equiv="cache-control" content="must-revalidate" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="pragma" content="no-cache" />
    <link rel="shortcut icon" href="${contextPath}/assets/img/favicon.png">
    
    <!-- Fonts and icons -->
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <!-- FontAwesome -->
    <link href="${contextPath}/assets/fonts/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/assets/css/flatpickr.min.css">
    
    <!-- Bootstrap5 -->
    <link href="${contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- Bootstrap Select Picker -->
    <link href="${contextPath}/assets/vendor/bootstrap-selectpicker/bootstrap-select.min.css" rel="stylesheet" type="text/css">
    <!-- DataTable css -->
    <link href="${contextPath}/assets/vendor/datatable/datatables.min.css" rel="stylesheet" />
    <!-- Datepicker css -->
    <link href="${contextPath}/assets/vendor/datepicker/jquery.datepick.css" rel="stylesheet" />
    <link rel="stylesheet" href="${contextPath}/assets/vendor/jquery_autocomplete/jquery.autocomplete.css" />
    <!-- Custom CSS -->
    <link href="${contextPath}/assets/css/style.css" rel="stylesheet" type="text/css">
    <link href="${contextPath}/assets/vendor/magnific-popup/magnific-popup.css" rel="stylesheet" />
    <link rel="stylesheet" href="${contextPath}/assets/owlslider/owl.carousel.min.css">
    <link rel="stylesheet" href="${contextPath}/assets/owlslider/owl.theme.default.min.css">
    <link rel="stylesheet" href="${contextPath}/assets/vendor/full-calender/fullcalendar.min.css">
    <link rel="stylesheet" href="${contextPath}/assets/css/bootstrap-datetimepicker.min.css" />
    <link rel="stylesheet" href="${contextPath}/assets/vendor/select2/select2.min.css" />

    <!-- START -->
    <!-- This Area is Only For Project specific CSS, PluginCss, style -->
    <!-- END -->
</head>
<style>
    .bootbox-cancel{
        color: #fff;
        background-color: #dc3545;
        border-color: #dc3545;
    }
</style>
<body class="body" id="body">
    <div class="wrapper">
        <%@ include file="/WEB-INF/views/newLoader.jsp" %>
        <tiles:insertAttribute name="menu" />
        <div class="main" id="main">
            <tiles:insertAttribute name="header" />
            <main class="content pb-3">
                <%@ include file="/WEB-INF/views/message.jsp"%>
                <tiles:insertAttribute name="body" />
            </main>
        </div>
    </div>
    
    <form id="moduleForm" action="${contextPath}/moduleDirectory" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <input type="hidden" name="moduleCode" id="moduleId" />
    </form>
   
    <form action="${contextPath}/document/viewDocuments" method="get" id="documentFormView" enctype="multipart/form-data" target="_blank">
        <input type="hidden" name="moduleName" id="moduleName"/>
        <input type="hidden" name="filePath" id="filePath"/>
    </form>

    <!-- ✅ ALL OTHER SCRIPTS - NOW SAFE (jQuery already loaded) -->
    <!-- Bootstrap bundle js -->
    <script src="${contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Bootstrap Select Picker -->
    <script src="${contextPath}/assets/vendor/bootstrap-selectpicker/bootstrap-select.min.js"></script>
    <!-- DataTable js -->
    <script src="${contextPath}/assets/vendor/datatable/datatables.min.js"></script>
    <!-- Datepicker js -->
    <script src="${contextPath}/assets/vendor/datepicker/jquery.plugin.min.js"></script>
    <script src="${contextPath}/assets/vendor/datepicker/jquery.datepick.min.js"></script>
    <script src="${contextPath}/assets/js/moment.js"></script>
    <script src="${contextPath}/assets/js/flatpickr.js"></script>
    
    <!-- Ck Editor JS -->
    <script src="${contextPath}/assets/vendor/ckeditor/ckeditor.js"></script>
    <script src="${contextPath}/assets/vendor/jquery_autocomplete/jquery.autocomplete.min.js"></script>
    
    <!-- Custom JS -->
    <script>window.ctxPath='${contextPath}';</script>
    <script src="${contextPath}/assets/js/custom.js"></script>
    <script src="${contextPath}/assets/utilsJs/commonFunctions.js"></script>
    <script src="${contextPath}/assets/utilsJs/helperFunctionsScripts.js"></script>
    <script src="${contextPath}/assets/utilsJs/validate.js"></script>
    <script src="${contextPath}/assets/utilsJs/common-utils.js"></script>
    <script src="${contextPath}/assets/vendor/magnific-popup/jquery.magnific-popup.js"></script>
    <script src="${contextPath}/assets/owlslider/owl.carousel.min.js"></script>
    <script src="${contextPath}/assets/vendor/full-calender/fullcalendar.min.js"></script>
    
    <!-- Encryption Scripts -->
    <script src="${contextPath}/assets/js/encrypt/AesUtil.js"></script>
    <script src="${contextPath}/assets/js/encrypt/aes.js"></script>
    <script src="${contextPath}/assets/js/encrypt/pbkdf2.js"></script>
    <script src="${contextPath}/assets/js/encrypt/lbase.js"></script>
    <script>window.ctxPath='${contextPath}';</script>
    
    <!-- FAMS MODULE -->
    <script src="${contextPath}/assets/js/fams/common.js"></script>
    <script src="${contextPath}/assets/js/fams/common-utils.js"></script>
    <script src="${contextPath}/assets/js/fams/invoice.js"></script>
    <script src="${contextPath}/assets/js/fams/party-master.js"></script>
    <script src="${contextPath}/assets/js/fams/ipmsJs/ipms.js"></script>
    <!-- FAMS MODULE END -->
    <script src="${contextPath}/assets/vendor/select2/select2.min.js"></script>
    <script src="${contextPath}/assets/js/bams/common-function.js"></script>
   
    <!-- YOUR EXISTING SCRIPTS - NOW SAFE -->
    <script>
    var contextPath = '${pageContext.request.contextPath}';
        function Module(){
           let currentModuleCode = localStorage.getItem('currentModuleCode');
           $('#moduleId').val(enc_password(currentModuleCode));
           $('#moduleForm').submit();
        }
    </script>
    <script>
    $(document).ready(function() {
        // Ensure carousel works even if dynamic elements are added
        $(document).on('shown.bs.modal', '#addOtherItemModal', function() {
            $(".banner_sec_sldrs,.imageSlider").owlCarousel({
                items: 1,
                loop: true,
                nav: false,
                dots: false,
                autoplay: true,
                autoplayTimeout: 3000,
                autoplayHoverPause: false,
                responsive: {
                    0: { items: 1 },
                    600: { items: 1 },
                    1000: { items: 1 }
                }
            });
        });
    });
    </script>
    <script>
        flatpickr(".timePicker", {
            enableTime: true,
            noCalendar: true,
            dateFormat: "h:i K",
            time_24hr: false
        });
        $(document).ready(function() {
            var table = $('.DataTable').DataTable({
                responsive: true,
                scrollX: true,
                scrollCollapse: true,
                fixedHeader: true,
                dom: '<"top-toolbar"lf>rtip',
            });
            if ($('.table-responsive').length) {
                $(".top-toolbar").insertBefore(".table-responsive");
            } else {
                $(".top-toolbar").insertBefore(".table");
            }
        });
        function backToModule(){
           let currentModuleCode = localStorage.getItem('currentModuleCode');
           $('#moduleId').val(enc_password(currentModuleCode));
           $('#moduleForm').submit();
        }
    </script>
    <script>
     $(document).ready(function() {
        $('#calendar').fullCalendar({
         header: {
             left: 'prev,next today',
             center: 'title',
             right: 'month,basicWeek,basicDay'
         },
         eventRender: function(event, element) {
             var titleElement = element.find('.fc-title');
             if (titleElement.length) {
                 titleElement.attr('title', event.description || event.title);
             }
         }
        })
     });
    </script>
    
    <!-- Global Input Sanitizer -->
    <script>
    (function() {
      const BLOCKED_CHARS = /[<>\/\\]/g;
      function sanitizeInput(value) {
        return value.replace(BLOCKED_CHARS, "");
      }
      function attachFilter(el) {
        el.addEventListener("input", function () {
          this.value = sanitizeInput(this.value);
        });
        el.addEventListener("paste", function (e) {
          e.preventDefault();
          let text = (e.clipboardData || window.clipboardData).getData("text");
          this.value += sanitizeInput(text);
        });
      }
      document.addEventListener("DOMContentLoaded", function () {
        document.querySelectorAll("input, textarea").forEach(attachFilter);
        const observer = new MutationObserver(mutations => {
          mutations.forEach(m => {
            m.addedNodes.forEach(node => {
              if (node.tagName === "INPUT" || node.tagName === "TEXTAREA") {
                attachFilter(node);
              }
              if (node.querySelectorAll) {
                node.querySelectorAll("input, textarea").forEach(attachFilter);
              }
            });
          });
        });
        observer.observe(document.body, { childList: true, subtree: true });
      });
    })();
    </script>

    <!-- Form Encryption Handler -->
    <script>
    (function ($) {
        $(document).ready(function () {
            $("form").on("submit", function () {
                let $form = $(this);
                if ($form.find("input[name='cipherText']").length > 0) {
                    $form.find("input[name], select[name], textarea[name]").each(function () {
                        let fieldName = $(this).attr("name");
                        let fieldType = $(this).attr("type");
                       
                        if (
                            fieldName !== "cipherText" &&
                            !fieldName.startsWith("_csrf") &&
                            fieldType !== "file" &&
                            !fieldName.startsWith("currentWorkflowStageParam") &&
                            !fieldName.startsWith("specificUserParam") &&
                            fieldName !== "hdnButtonCode" &&
                            fieldName !== "fileName" &&
                            fieldName !== "moduleName"
                        ) {
                            $(this).removeAttr("name");
                        }
                    });
                }
            });
        });
    })(jQuery);
    </script>

    <!-- Utility Functions -->
    <script>
    function convertFormToJSONArray(form) {
        const array = form;
        const json = {};
        $.each(array, function () {
          var contentCheck = this.name;
          if (json[this.name]) {
            if (!json[this.name].push) {
              json[this.name] = [json[this.name]];
            }
            json[this.name].push(this.value || "");
          } else if (contentCheck.includes("Array")) {
            json[this.name] = [this.value];
          } else {
            json[this.name] = this.value || "";
          }
        });
        return json;
    }
    function showLoader() {
      $(".loader-div").css("display", "flex");
    }
    function hideLoader() {
      $(".loader-div").css("display", "none");
    }
    $(document).ready(function() {
       $('.select2').select2();
    });
    </script>
</body>
</html>