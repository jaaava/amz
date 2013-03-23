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

var nextData = "";
var prevData = "";

loadBoth()
function loadAjaxNext() {
    document.getElementById("info").innerHTML = "_";
    if(nextData = ""){ page++; }
    $("#targetNext").load("http://localhost:9000/find?cat=" + cat + "&keyword=" + keyword + "&page=" + (page) + " #result", function () {
        nextData = this.innerHTML;
        document.getElementById("info").innerHTML += ":" + (page+1) + " laetud";
    });
}
function loadAjaxPrev() {
    document.getElementById("info").innerHTML = "_";
    if(prevData = ""){ page--; }
    $("#targetPrev").load("http://localhost:9000/find?cat=" + cat + "&keyword=" + keyword + "&page=" + (page) + " #result", function () {
        prevData = this.innerHTML;
        document.getElementById("info").innerHTML += ":" + (page-1) + " laetud";
    });
}

function next() {
    document.getElementById("result").innerHTML = nextData;
    page++;
    loadBoth()
}
function prev() {
    document.getElementById("result").innerHTML = prevData;
    page--;
    loadBoth()
}

function loadBoth(){
    loadAjaxNext();
    loadAjaxPrev();
}
