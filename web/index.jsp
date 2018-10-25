<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style.css"/>
    <link rel="stylesheet" href="modal.css"/>
    <title>Welcome</title>
</head>
<body>
<div id="stalemateModal" class="modal">
    <div class="modal-content">
        Waiting for other player<span class="one">.</span><span class="two">.</span><span class="three">.</span>
    </div>
</div>
<div style="text-align: center">
    <h1>Welcome to Speed</h1>
    <button onclick="testStalemate();">stale</button>
    <button onclick="testPlaceMessage();">place</button>
    <button id="unhide">unhide</button>
</div>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="game.js"></script>

</html>