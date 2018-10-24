<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style.css"/>
    <link rel="stylesheet" href="modal.css"/>
    <title>Welcome</title>
</head>
<body>
    <div style="text-align: center">
        <div id="stalemateModal" class="modal">
            <div class="modal-content">
                <p>Waiting for other player...</p>
            </div>
        </div>
        <h1>Welcome to Speed</h1>
        <button onclick="testStalemate();">stale</button>
        <button onclick="testPlaceMessage();">place</button>
        <button id="unhide">unhide</button>
    </div>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script src="game.js"></script>

</html>