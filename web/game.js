var ws = new WebSocket(("http:" ? "ws://" : "wss://") + document.location.host + "/game");
var stalemateModal = $("#stalemateModal");

ws.onmessage = function (event) {
    console.log(event.data);
    var bs = JSON.parse(event.data);

    switch (bs.action) {
        case "bs":
            console.log("bs");
            break;
        case "doofus":
            console.log("doofus");
            break;
        case "lose":
            console.log("lose");
            break;
        case "win":
            console.log("win");
            break;
        case "dc":
            console.log("dc");
            break;
        case "open":
            stalemateModal.show();
            break;
        case "close":
            stalemateModal.hide();
            break;
    }
};

$('#unhide').click(function () {
    stalemateModal.show();
});

$(window).click(function (ev) {
    if ($(ev.target).is('#stalemateModal'))
        hideStalemate();
});

function hideStalemate() {
    stalemateModal.hide();
}

function testStalemate() {
    ws.send(JSON.stringify({'action': 'stalemate'}));
}

function testPlaceMessage() {
    ws.send(JSON.stringify({'action': 'place', 'card': '0D'}));
}

