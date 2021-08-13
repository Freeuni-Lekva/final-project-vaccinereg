<html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        <h1>Welcome <%= request.getAttribute("privatenum") %></h1>
        <form action="/statistics" method="get">
            <input type="submit" value="statistics">
        </form>

    </body>
</html>