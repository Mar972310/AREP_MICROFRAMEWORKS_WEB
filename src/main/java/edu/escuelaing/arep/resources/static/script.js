function loadPostMsg() {
    let nameVar = document.getElementById("textInputPost").value;
    let url = "/app/hellopost?name=" + nameVar;

    fetch(url, { method: 'POST' })
        .then(response => response.json())  
        .then(data => {
            
            document.getElementById("postResult").innerHTML = data.response;
        });
}

function loadGetMsg() {
    let nameVar = document.getElementById("textInputGet").value;
    let url = "/app/helloget?name=" + nameVar;

    fetch(url, { method: 'GET' })
        .then(response => response.json())  
        .then(data => {
            
            document.getElementById("getResult").innerHTML = data.response;
        });
}

function loadHelloMsg() {
    let url = "/app/hello";
    fetch(url, { method: 'GET' })
        .then(response => response.json())  
        .then(data => {
            
            document.getElementById("static1").innerHTML = data.response;
        });
}

function loadPiMsg() {
    let url = "/app/pi";
    fetch(url, { method: 'GET' })
        .then(response => response.json())  
        .then(data => {
            
            document.getElementById("static2").innerHTML = data.response;
        });
}
