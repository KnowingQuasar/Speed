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
        case "lose":
            alert("lose");
            break;
        case "win":
            alert("win");
            break;
        case "dc":
            alert("dc");
            break;
    }
};

function testStalemate() {
    ws.send(JSON.stringify({ "action" : "stalemate" }));
}

function testPlaceMessage() {
    ws.send(JSON.stringify({ "action" : "place", "card" : "0D" }));
}

