function loadGetMsg() {
    let nameVar = document.getElementById("textInputGet").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("getResult").innerHTML = this.responseText;
    }
    xhttp.open("GET", "/app?name=" + nameVar);
    xhttp.send();
}

function loadPostMsg() {
    let nameVar = document.getElementById("textInputPost").value;
    let url = "/app/hellopost?name=" + nameVar;

    fetch(url, {method: 'POST'})
        .then(response => response.text())
        .then(text => document.getElementById("postResult").innerHTML = text); 
}

function loadHelloMsg() {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("hello").innerHTML = this.responseText;
    }
    xhttp.open("GET", "/app/hello");
    xhttp.send();
}

function loadPiMsg() {
    let url = "/app/pi";

    fetch(url, {method: 'GET'})
        .then(response => response.text())
        .then(text => document.getElementById("pi").innerHTML = text); 
}