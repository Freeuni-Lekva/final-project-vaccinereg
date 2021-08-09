<html>
    <head>
        <title>Register</title>
    </head>
    <body>
        <h1>Create a new account</h1>
        <p>Please enter your details</p>

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
                    <td><input type="date" name="birth_date" id="bday_input"/></td></tr>

                <tr><td>E-mail:</td>
                    <td><input type="email" name="email" required/></td></tr>

                <tr><td>Password:</td>
                    <td><input type="password" name="password" required minlength="4"/></td>
                    <td><input type="submit" value = "Register" id="input_button" /></td></tr>

            </table>
        </form>
        <script>
            let bday = document.getElementById("bday_input");
            let button = document.getElementById("input_button");
            bday.addEventListener("change", Handler);
            document.getElementById('bday_input').value = new Date().toISOString().substring(0, 10);

            function Handler() {
                if (document.getElementById("bday_input").value.length === 10) {
                    button.disabled = false;
                } else {
                    button.disabled = true;
                }
            }

        </script>
    </body>
</html>
