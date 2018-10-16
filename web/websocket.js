var ws;

function connect() {
    var host = document.location.host;
    var pathname = document.location.pathname;
    var wstype = document.location.protocol === "http:" ? "ws://" : "wss://";

    ws = new WebSocket(wstype + host  + pathname + "chat");

    ws.onmessage = function(event) {
        var log = document.getElementById("log");
        console.log(event.data);
        var message = JSON.parse(event.data);
        log.innerHTML += message.from + " : " + message.content + "\n";
    };
}

function send() {
    var content = document.getElementById("msg").value;
    var json = JSON.stringify({
        "content":content
    });

    ws.send(json);
}