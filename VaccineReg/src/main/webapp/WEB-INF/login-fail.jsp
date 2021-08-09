<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Please try again</h1>

        <p>Either your username or password is incorrect. Please try again</p>

        <form action="/login" method="post">
            <table>
                <tr><td>E-mail:</td>
                    <td><input type="email" name="email" value="<%= request.getAttribute("email_typed") %>"/></td></tr>
                <tr><td>Password:</td>
                    <td><input type="password" name="password" /></td>
                    <td><input type="submit" value = "Login" /></td></tr>
            </table>
        </form>

    </body>
</html>