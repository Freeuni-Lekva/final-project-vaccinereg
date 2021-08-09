<html>
    <head>
        <title>Register</title>
    </head>
    <body>
        <h1>Please try again</h1>
        <p>A user with either your private number or e-mail already exists. Please try again</p>

        <form action="/register" method="post">
            <table>

                <tr><td>Private number:</td>
                    <td><input type="tel" name="private_num" required
                        pattern="[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"
                        placeholder="01234567890"/></td></tr>

                <tr><td>Name:</td>
                    <td><input type="text" name="name" required/></td></tr>

                <tr><td>Last name:</td>
                    <td><input type="text" name="last_name" required/></td></tr>

                <tr><td>Gender:</td>
                    <td>
                        <input type="radio" name="gender" value="m" required/> Male
                        <input type="radio" name="gender" value="f" required/> Female
                    </td></tr>

                <tr><td>Birth Date:</td>
                    <td><input type="date" name="birth_date" id="bday_input" required/></td></tr>

                <tr><td>E-mail:</td>
                    <td><input type="email" name="email" required/></td></tr>

                <tr><td>Password:</td>
                    <td><input type="password" name="password" required minlength="4"/></td>
                    <td><input type="submit" value = "Register" /></td></tr>

            </table>
        </form>
        <script>
            document.getElementById('bday_input').value = new Date().toISOString().substring(0, 10);
        </script>
    </body>
</html>
