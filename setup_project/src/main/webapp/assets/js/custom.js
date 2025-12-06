 $(document).ready(function() {
    // Datatable with Export btn
    $('.exportbtn').DataTable({
        dom: 'lBfrtip',
        responsive: true,
        pageLength: 10,
        // lengthMenu: [0, 5, 10, 20, 50, 100, 200, 500],
    });

   $('.datepicker_con>input').datepick({
		 dateFormat: 'dd/mm/yyyy', 
         showOnFocus: true,
		 showTrigger: '<button type="button" class="trigger">' +
		 '<i class="fa fa-calendar"></i></button>',
	});
	
	$('.recdatepicker_con>input').datepick({
		 dateFormat: 'dd/mm/yyyy', 
         showOnFocus: true,
         minDate: '0',
		 showTrigger: '<button type="button" class="trigger">' +
		 '<i class="fa fa-calendar"></i></button>',
	});

    activeMenu();
   
});


function showLoader() {
    // add css display flex in loader-div class
    $(".loader-div").css("display", "flex");

}
function hideLoader() {
    // remove css display flex in loader-div class
    $(".loader-div").css("display", "none");
}

// destroy select2 plugin
function refreshSelect2Plugin(){
    $('.selectpicker').select2('destroy').select2();
}

function refreshSelectpicker(){
    $('.selectpicker').selectpicker('refresh');
}



function refreshAllSelectOption(){
    try {
        refreshSelect2Plugin();
    } catch (error) {
        console.log("Error in refreshSelect2Plugin");
    }
    // refresh selectpicker
    try {
        refreshSelectpicker();
    } catch (error) {
        console.log("Error in refreshSelectpicker");
    }
}

// active menu parent ul adding a class 'show'
function activeMenu(){
    // get active li which has active class 'activeMenu'
    var activeLi = $('.activeMenu');
    // get its parent ul
    var parentUl = activeLi.parent('ul');
    // add class 'show' to parent ul if it has not class 'show'
    if(!parentUl.hasClass('show')){
        parentUl.addClass('show');
    }
}



