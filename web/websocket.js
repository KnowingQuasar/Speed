var ws = new WebSocket(("http:" ? "ws://" : "wss://") + document.location.host + "/game");
ws.onmessage = function (event) {
    var log = document.getElementById("log");
    console.log(event.data);
    var bs = JSON.parse(event.data);
};