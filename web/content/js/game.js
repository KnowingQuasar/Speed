var ws = new WebSocket((document.location.protocol === "http:" ? "ws://" : "wss://") + document.location.host + "/game");
var connectModal = $("#connectModal");
var stalemateModal = $('#stalemateModal');
var dcModal = $('#dcModal');
var resultModal = $('#resultModal');
var clickedCard;

ws.onmessage = function (event) {
    console.log(event.data);
    let msg = JSON.parse(event.data);

    switch (msg.action) {
        case "bs":
            connectModal.hide();
            dcModal.hide();
            console.log("Received board state");
            clickedCard = null;
            ReloadBoard(msg);
            break;
        case "doofus":
            console.log("Received doofus message");
            if (clickedCard) {
                $('.selected').removeClass('selected');
                clickedCard.addClass('invalid');
            }
            break;
        case "lose":
            console.log("Received lose message");
            resultModal.show();
            break;
        case "win":
            console.log("Received win message");
            resultModal.css("color", "green").show();
            break;
        case "dc":
            console.log("Received dc message");
            dcModal.show();
            break;
        case "open":
            console.log("Received open stalemate message");
            stalemateModal.show();
            break;
        case "close":
            console.log("Received close stalemate message");
            stalemateModal.hide();
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
        let card = clickedCard.attr('src').substring('/content/images/'.length, clickedCard.attr('src').lastIndexOf('.'));
        console.log("Sending " + card);
        ws.send(JSON.stringify({'action': 'place', 'card': card}));
    }
});

$(window).click(function (e) {
    if ($(e.target).is('#connectModal'))
        hideStalemate();
});

function ReloadBoard(bs) {
    if (bs.remaining[0] === 0) {
        $('#plD').src = '/content/images/green.PNG';
    }
    if (bs.remaining[1] === 0) {
        $('#opD').src = '/content/images/green.PNG';
    }
    $('#remaining1').text('Total 1: ' + bs.remaining[0]);
    $('#remaining2').text('Total 2: ' + bs.remaining[1]);
    $('#fc1').attr('src', `/content/images/${bs.fc[0]}.png`);
    $('#fc2').attr('src', `/content/images/${bs.fc[1]}.png`);
    let i = 0;
    let j = bs.hand.length;
    $('#pl > span').each(function () {
        if ($(this).hasClass('deckPlayer'))
            return;
        if(i < j)
            $(this.firstChild).attr('src', `/content/images/${bs.hand[i]}.png`);
        else
            $(this.firstChild).attr('src', '/content/images/green.PNG');
        i++;
    });
}

function selectCard() {
    $('.invalid').removeClass('invalid');
    $('.selected').removeClass('invalid').removeClass('selected');
    clickedCard.addClass('selected');
}

function hideStalemate() {
    connectModal.hide();
}

$('#stalemate').click(function() {
    $('#stalemate').disable();
    ws.send(JSON.stringify({'action': 'stalemate'}));
});

function testPlaceMessage() {
    ws.send(JSON.stringify({'action': 'place', 'card': '0D'}));
}

