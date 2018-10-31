var ws = new WebSocket((document.location.protocol === "http:" ? "ws://" : "wss://") + document.location.host + "/game");
var stalemateModal = $("#stalemateModal");
var clickedCard;

ws.onmessage = function (event) {
    console.log(event.data);
    let bs = JSON.parse(event.data);

    switch (bs.action) {
        case "bs":
            console.log("Received board state");
            ReloadBoard(bs);
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

$('#pl').click(function (e) {
    if(!$(e.target).hasClass('smallCard') || $(e.target).attr('id') === 'plD')
        return;
    clickedCard = $(e.target);
    selectCard();
});

$('#mid').click(function () {
    if (clickedCard) {
        $('.selected').removeClass('selected');
        let card = $(clickedCard).attr('id');
        console.log("Sending " + card);
        ws.send(JSON.stringify({'action': 'place', 'card': card}));
    }
});

$(window).click(function (e) {
    if ($(e.target).is('#stalemateModal'))
        hideStalemate();
});

function ReloadBoard(bs) {
    if(bs.remaining[0] === 0){
        $('#plD').src = '/content/images/green.PNG';
    }
    if(bs.remaining[1] === 0) {
        $('#opD').src = '/content/images/green.PNG';
    }
    $('#fc1').attr('src', '/content/images/' + bs.fc[0] + '.png').attr('id', bs.fc[0]);
    $('#fc2').attr('src', '/content/images/' + bs.fc[1] + '.png').attr('id', bs.fc[1]);
    let i = 0;
    $('#pl > span').each(function() {
        if($(this).hasClass('deckPlayer'))
            return;
        $(this.firstChild).attr('src', '/content/images/' + bs.hand[i] + '.png' ).attr('id', bs.hand[i]);
        i++;
    });
}

function selectCard() {
    $('.selected').removeClass('selected');
    $(clickedCard).addClass('selected');
}

function hideStalemate() {
    stalemateModal.hide();
}

function testStalemate() {
    ws.send(JSON.stringify({'action': 'stalemate'}));
}

function testPlaceMessage() {
    ws.send(JSON.stringify({'action': 'place', 'card': '0D'}));
}

