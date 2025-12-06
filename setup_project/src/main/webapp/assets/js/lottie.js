var path = window.location.pathname;
var pathParts = path.split('/');
// The context path is typically the first part after the domain
var cntxPath = pathParts[1];
// Construct the complete URL for the animation data
var animationData = window.location.origin + '/' + cntxPath + '/assets/css/lottie/loaders/lq.json';

const animationContainer = document.getElementById('lottie-loader');
// Load the animation
const animation = lottie.loadAnimation({
    container: animationContainer,
    renderer: 'svg',
    loop: true,
    autoplay: true,
    path: animationData,
    backgroundColor: 'transparent',
});


$(document).ready(function() {
    new DataTable('.datatable', {
        "pageLength": 10
    });
});


function showLottie(){
        $(".loader-div").css("display", "flex");
    }
function hideLottie(){
    $(".loader-div").css("display", "none");
}



// on load
$(document).ready(function() {
    hideLottie();
});

$(document).on('submit', 'form', function() {
    debugger;
    showLottie();
});

window.onbeforeunload = function() {
    showLottie();
};

// after dwonload a file hide loader
window.addEventListener("focus", function(event) {
    hideLottie();
}, false);





// on any ajax request add loader and remove loader on complete
$(document).ajaxStart(function() {
    showLottie();
});

$(document).ajaxComplete(function() {
    hideLottie();
});

$(document).ajaxError(function() {
    hideLottie();
});

$(document).ajaxSuccess(function() {
    hideLottie();
});

$(document).ajaxSend(function() {
    hideLottie();
});

$(document).ajaxStop(function() {
    hideLottie();
});


