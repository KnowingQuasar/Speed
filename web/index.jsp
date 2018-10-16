<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style.css"/>
    <title>Welcome</title>
</head>
<body>
    <div style="text-align: center">
        <h1>Welcome to Speed</h1>
        <form action="${pageContext.request.contextPath}/game.jsp">
            <input type="submit" value="Create"/>
        </form>
        <form action="https://en.wikipedia.org/wiki/Speed_(card_game)" target="_blank">
            <input type="submit" value="Rules"/>
        </form>
    </div>
</body>

<script src="websocket.js"></script>

</html>