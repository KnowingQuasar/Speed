var ws = new WebSocket(("http:" ? "ws://" : "wss://") + document.location.host + "/game");

ws.onmessage = function (event) {
    console.log(event.data);
    var bs = JSON.parse(event.data);

    switch(bs.action) {
        case "bs":
            alert("bs");
            break;
        case "doofus":
            alert("doofus");
            break;
    }
};

function sendM(c){
    ws.send(JSON.stringify({ "action" : "place", card : c.toString()}));
}