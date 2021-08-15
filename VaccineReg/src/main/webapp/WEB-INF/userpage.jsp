<%@ page import = "java.util.*" %>
<%@ page import = "entities.User" %>
<%@ page import = "daos.UsersDAO" %>

<html>
    <head>
        <%  User user = (User) request.getSession().getAttribute("user"); %>

        <title>Welcome</title>
    </head>
    <body>

        <h1>Welcome <%= request.getAttribute("privatenum") %></h1>
        
        <h1>Welcome <%= user.getName() %> <%= user.getLastName() %>! </h1>

        <!-- Printing out reservation info -->
        <%
            // 0 because NULL in the table defaults to 0
            if (user.getReservationId() == 0){
                out.println(
                "<p>" +
                    "You are not currently registered for a vaccine. " +
                    "If you want to register for a vaccine, press the \"Register for a vaccine\" button." +
                "</p>"
                );

                out.println(
                "<form action=\"/reserve\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Register for a vaccine\" />" +
                "</form>"
                );
            }
            else{
                out.println(
                "<p>" +
                    "You are already registered for a vaccine. " +
                    "If you want to cancel your reservation, press the \"Cancel reservation\" button." +
                "</p>"
                );

                out.println(
                "<form action=\"/cancel\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Cancel reservation\" />" +
                "</form>"
                );
            }
        %>


        <!-- Admin button UNCOMMENT WHEN ADMIN FUNCTIONALITY IS ADDED
        <%
            if (user.getAdmin()){
                out.println(
                "<form action=\"/admin\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Manage website\" />" +
                "</form>"
                );
            }
        %>
        -->

      
    <form action="/statistics" method="get">
        <input type="submit" value="statistics">
    </form>

    </body>
</html>