var ws = new WebSocket(("http:" ? "ws://" : "wss://") + document.location.host + "/game");
var stalemateModal = $("#stalemateModal");

ws.onmessage = function (event) {
    console.log(event.data);
    var bs = JSON.parse(event.data);

    switch (bs.action) {
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
        case "open":
            alert("open");
            break;
        case "close":
            alert("close");
            break;
    }
};

$('#unhide').click(function () {
    stalemateModal.show();
});

window.onclick = function(ev) {
    if($(ev).target === stalemateModal)
        hideStalemate();
};

function hideStalemate(){
    stalemateModal.hide();
}

function testStalemate() {
    ws.send(JSON.stringify({'action': 'stalemate'}));
}

function testPlaceMessage() {
    ws.send(JSON.stringify({'action': 'place', 'card': '0D'}));
}

