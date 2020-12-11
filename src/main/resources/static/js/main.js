document.addEventListener("DOMContentLoaded", function() {
    let shortenUnit = new ShortenURLUnit();
    shortenUnit.shortenUrl();
});

function ShortenURLUnit(){}
ShortenURLUnit.prototype = {
    shortenUrl : function(){
        let urlText = document.getElementById('url-input');
        let urlButton = document.getElementById('url-button');
        let result =  document.getElementById('result').lastChild.previousSibling;

        var httpRequest = new XMLHttpRequest();
        urlButton.addEventListener('click', function(){
            httpRequest.open("POST", "/short?originURL="+urlText.value,false);
            httpRequest.send();
            result.value = "hi";
            if (httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200 ) {
            }
        })
    }
}