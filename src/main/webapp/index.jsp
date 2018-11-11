<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="content/css/style.css"/>
    <link rel="stylesheet" href="content/css/modal.css"/>
    <title>Speed</title>
</head>

<body>
<div id="connectModal" class="modal">
    <div class="modal-content">
        <p>Waiting for Other Player to Join<span class="one">.</span><span class="two">.</span><span class="three">.</span></p>
    </div>
</div>
<div id="stalemateModal" class="modal">
    <div class="modal-content">
        <p>Waiting for Other Player<span class="one">.</span><span class="two">.</span><span class="three">.</span></p>
    </div>
</div>
<div id="dcModal" class="modal">
    <div class="modal-content">
        <p>Waiting for Other Player Rejoin<span class="one">.</span><span class="two">.</span><span class="three">.</span></p>
    </div>
</div>
<div id="resultModal" class="modal">
    <div class="modal-content">
        <p>Lose</p>
    </div>
</div>
<div>
    <table>
        <tr>
            <th><p id="remaining1"></p></th>
            <th><p id="remaining2"></p></th>
        </tr>
    </table>
    <br>
</div>
<div class="cardsTop">
    <span class="deckOpponent"><img id="opD" class="smallCard" src="/content/images/gray_back.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="divSmall"><img id="op1" class="smallCard" src="/content/images/gray_back.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="divSmall"><img id="op2" class="smallCard" src="/content/images/gray_back.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="divSmall"><img id="op3" class="smallCard" src="/content/images/gray_back.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="divSmall"><img id="op4" class="smallCard" src="/content/images/gray_back.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="left"><img id="op5" class="smallCard" src="/content/images/gray_back.png" alt="back" align="left" draggable="false" ondragstart="return false;"></span>
</div>
<br>
<div id="mid" class="cardsMid">
    <span class="divLarge"><img id="s1" class="card" src="/content/images/gray_back.png" alt="backLarge" draggable="false" ondragstart="return false;"></span>
    <span class="divLarge"><img id="fc1" class="card" src="/content/images/gray_back.png" alt="backLarge" draggable="false" ondragstart="return false;"></span>
    <span class="divLarge"><img id="fc2" class="card" src="/content/images/gray_back.png" alt="backLarge" draggable="false" ondragstart="return false;"></span>
    <span class="divLarge"><img id="s2" class="card" src="/content/images/gray_back.png" alt="backLarge" draggable="false" ondragstart="return false;"></span>
</div>
<br>
<div id="pl" class="cardsBot">
    <span class="deckPlayer"><img id="plD" class="smallCard" src="/content/images/gray_back.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="divSmall"><img id="pl5" class="smallCard" src="/content/images/gray_back.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="divSmall"><img id="pl4" class="smallCard" src="/content/images/gray_back.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="divSmall"><img id="pl3" class="smallCard" src="/content/images/gray_back.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="divSmall"><img id="pl2" class="smallCard" src="/content/images/gray_back.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class=" right"><img id="pl1" class="smallCard" src="/content/images/gray_back.png" alt="back" draggable="false" ondragstart="return false;"></span>
</div>
<br>
<button id="stalemate">Stalemate</button>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="content/js/game.js"></script>
</body>
</html>