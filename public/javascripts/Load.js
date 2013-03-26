var page = 0;
var cat = "";
var keyword = "";

getURLparams();
function getURLparams() {
    var absURL = document.URL;
    var relURL = absURL.substr(absURL.indexOf("?") + 1);
    var params = relURL.split("&");

    cat = params[0].substr(params[0].indexOf("=") + 1);
    keyword = params[1].substr(params[1].indexOf("=") + 1);
    page = parseInt(params[2].substr(params[2].indexOf("=") + 1));
}


var pageData = new Array();
pageData[0] = "NULL";
var tempPage = page + 1;
$(document).ready(function () {
    pageData[0] = document.getElementById("result").innerHTML;
    document.getElementById("info").innerHTML += pageData.length + ", ";
});

function load() {
    $("#target").load("http://localhost:9000/find?cat=" + cat + "&keyword=" + keyword + "&page=" + (tempPage) + " #result", function () {
        pageData.push(this.innerHTML);
        alert(this.innerHTML);
        tempPage++;
        document.getElementById("info").innerHTML += pageData.length + ", ";
        if (tempPage == 11) return;
        load();
    });
}

function changePage(pageNr) {
    var result = document.getElementById("result");
    if (pageNr > 0 && pageNr <= 10) {
        if (typeof pageData[page - 1] == "undefined") {
            result.innerHTML = "Loading results...";
        } else {
            result.innerHTML = pageData[page - 1];
        }
    }
    else if (pageNr <= 0) {
        alert("Page number cannot be less than 0");
        page++;
    }
    else {
        alert("You are on page " + page + ". There are no more pages..")
        page--;
    }
}
