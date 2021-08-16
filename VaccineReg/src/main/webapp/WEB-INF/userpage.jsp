<%@ page import = "java.util.*" %>
<%@ page import = "entities.User" %>
<%@ page import = "daos.ReservationsDAO" %>

<html>
    <head>
        <%  User user = (User) request.getSession().getAttribute("user");
            ReservationsDAO resDAO = (ReservationsDAO) request.getServletContext().getAttribute("reservationsDAO"); %>

        <title>Welcome</title>
    </head>
    <body>

        <h1>Welcome <%= request.getAttribute("privatenum") %></h1>
        
        <h1>Welcome <%= user.getName() %> <%= user.getLastName() %>! </h1>

        <!-- Printing out reservation info -->
        <%
            if (resDAO.getNextVaccination(user) == null){
                out.println(
                "<p>" +
                    "You are not currently registered for a vaccine. " +
                    "If you want to register for a vaccine, press the \"Register for a vaccine\" button." +
                "</p>"
                );

                out.println(
                "<form action=\"/reserve-region\" method=\"post\">" +
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


        <%
            if (user.getAdmin()){
                out.println(
                "<form action=\"/admin\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Manage website\" />" +
                "</form>"
                );
            }
        %>

      
    <form action="/statistics" method="get">
        <input type="submit" value="statistics">
    </form>

    </body>
</html>