<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="content/css/style.css"/>
    <link rel="stylesheet" href="content/css/modal.css"/>
    <title>Chat</title>
</head>

<body>

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
    <span class="divLarge"><img id="fc1" class="card" src="/content/images/1H.png" alt="backLarge" draggable="false" ondragstart="return false;"></span>
    <span class="divLarge"><img id="fc2" class="card" src="/content/images/2H.png" alt="backLarge" draggable="false" ondragstart="return false;"></span>
    <%--<span class="divLarge"><img class="card" src="/content/images/green.PNG" alt="backLarge" draggable="false" ondragstart="return false;"></span>--%>
    <span class="divLarge"><img id="s2" class="card" src="/content/images/gray_back.png" alt="backLarge" draggable="false" ondragstart="return false;"></span>
</div>
<br>
<div id="pl" class="cardsBot">
    <span class=" right"><img id="pl1" class="smallCard" src="/content/images/3H.png" alt="back" align="right" draggable="false" ondragstart="return false;"></span>
    <span class="divSmall"><img id="pl2" class="smallCard" src="/content/images/4C.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="divSmall"><img id="pl3" class="smallCard" src="/content/images/5D.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="divSmall"><img id="pl4" class="smallCard" src="/content/images/6H.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="divSmall"><img id="pl5" class="smallCard" src="/content/images/7H.png" alt="back" draggable="false" ondragstart="return false;"></span>
    <span class="deckPlayer"><img id="plD" class="smallCard" src="/content/images/gray_back.png" alt="back" draggable="false" ondragstart="return false;"></span>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="content/js/game.js"></script>
</body>
</html>