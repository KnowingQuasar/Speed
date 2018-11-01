var ws = new WebSocket((document.location.protocol === "http:" ? "ws://" : "wss://") + document.location.host + "/game");
var modal = $("#modal");
var clickedCard;

ws.onmessage = function (event) {
    console.log(event.data);
    let bs = JSON.parse(event.data);

    switch (bs.action) {
        case "bs":
            modal.hide();
            console.log("Received board state");
            clickedCard = null;
            ReloadBoard(bs);
            break;
        case "doofus":
            console.log("Received doofus message");
            if (clickedCard) {
                $('.selected').removeClass('selected');
                clickedCard.addClass('invalid');
            }
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
            modal.show();
            break;
        case "close":
            modal.hide();
            break;
    }
};

$('#pl').click(function (e) {
    if (!$(e.target).hasClass('smallCard') || $(e.target).attr('id') === 'plD')
        return;
    clickedCard = $(e.target);
    selectCard();
});

$('#mid').click(function () {
    if (clickedCard) {
        $('.selected').removeClass('selected');
        let card = $(clickedCard).attr('src').substring('/content/images/'.length, clickedCard.attr('src').lastIndexOf('.'));
        console.log("Sending " + card);
        ws.send(JSON.stringify({'action': 'place', 'card': card}));
    }
});

$(window).click(function (e) {
    if ($(e.target).is('#modal'))
        hideStalemate();
});

function ReloadBoard(bs) {
    if (bs.remaining[0] === 0) {
        $('#plD').src = '/content/images/green.PNG';
    }
    if (bs.remaining[1] === 0) {
        $('#opD').src = '/content/images/green.PNG';
    }
    $('#fc1').attr('src', `/content/images/${bs.fc[0]}.png`);
    $('#fc2').attr('src', `/content/images/${bs.fc[1]}.png`);
    let i = 0;
    $('#pl > span').each(function () {
        if ($(this).hasClass('deckPlayer'))
            return;
        $(this.firstChild).attr('src', `/content/images/${bs.hand[i]}.png`).attr('id', bs.hand[i]);
        i++;
    });
}

function selectCard() {
    $('.invalid').removeClass('invalid');
    $('.selected').removeClass('invalid').removeClass('selected');
    clickedCard.addClass('selected');
}

function hideStalemate() {
    modal.hide();
}

$('#stalemate').click(function() {
    ws.send(JSON.stringify({'action': 'stalemate'}));
});

function testPlaceMessage() {
    ws.send(JSON.stringify({'action': 'place', 'card': '0D'}));
}

