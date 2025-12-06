
function encryptFormData(form) {
    var secretKey = "1234567891234567"; // Use a secure method to manage keys
    var formData = new FormData(form);
    var jsonObject = {};
    formData.forEach((value, key) => {
        // jsonObject[key] = value;
        if (key.includes('.')) {
            // Split the key by dot
            let keys = key.split('.');
            // Create the nested object structure
            if (!jsonObject[keys[0]]) {
                jsonObject[keys[0]] = {};
            }
            jsonObject[keys[0]][keys[1]] = value;
        } else {
            jsonObject[key] = value;
        }
    });
    var encryptedData = encryptAES(JSON.stringify(jsonObject), secretKey);
    return encryptedData;
}

document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('.dataEncryption');
    if (form) {
        form.onsubmit = function(event) {
            event.preventDefault();
            var encryptedData = encryptFormData(this);
            var hiddenInput = document.createElement("input");
            hiddenInput.type = "hidden";
            hiddenInput.name = "encryptedData";
            hiddenInput.value = encryptedData;
            this.appendChild(hiddenInput);
            this.submit();
        };
    }
});


document.addEventListener('DOMContentLoaded', function () {
    var form = document.querySelector('.url-encryption');
    if (form) {
        form.onsubmit = function(event) {
       
    event.preventDefault(); // Prevent default form submission
    const secretKey = "1234567891234567";
    const form = this; // Reference to the form
    const url = new URL(form.action, window.location.origin);
    const formData = new FormData(form);
    let queryString = "";
    for (const [key, value] of formData.entries()) {
        queryString =key + "=" + value + "&";
    }
    queryString = queryString.slice(0, -1); // Remove trailing '&'
    const encryptedData = encryptAES(queryString, secretKey);
    const encQuery = '?encQuery=' + encryptedData;
    window.location.href = url.origin + url.pathname + encQuery;
        };
    }
});

//encryption for query paramater

 $('a.encrypt-query').click(function(event) {
	event.preventDefault(); // Prevent the default link action
	var secretKey = "1234567891234567";
	const href = $(this).attr('href');
	const url = new URL(href, window.location.origin);
	const queryString = url.search;
	const encryptedData = encryptAES(queryString, secretKey);
	const encQuery = '?encQuery=' + encryptedData; // Encode encrypted data
	const orgUrl = url.href;
	const newHref = orgUrl.replace(queryString, encQuery);
	window.location.href = newHref;
}); 

function encryptAES(data, secretKey) {
    // Create a key for AES encryption
    var key = CryptoJS.enc.Utf8.parse(secretKey); // Secret key should be 16 bytes for AES
    var iv = CryptoJS.enc.Utf8.parse('1234567890123456'); // 16-byte IV (initialization vector)
    
    // Encrypt the data
    var encryptedData = CryptoJS.AES.encrypt(data, key, { iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7 });

    // Return the encrypted data as a Base64 encoded string
    return encryptedData.toString();
}
