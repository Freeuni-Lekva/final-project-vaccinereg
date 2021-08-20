<%@ page import="java.time.LocalDateTime" %>
<% String center = (String)request.getServletContext().getAttribute("center");%>
<% String vaccine = (String)request.getServletContext().getAttribute("vaccine");%>
<% LocalDateTime localDateTime = (LocalDateTime) request.getServletContext().getAttribute("time");%>
<% String dateTime = localDateTime.toString().replace('T', ' '); %>
<html>
<head>
    <title>Cancel</title>
</head>
<body>
<h2>Are you sure you want to cancel your reservation?</h2>
<table>
    <tr>
        <td>
            Your reservation:
        </td>
    </tr>
    <tr>
        <td>Center: <%= center%></td>
    </tr>
    <tr>
        <td>
            Vaccine: <%= vaccine%>
        </td>
    </tr>
    <tr>
        <td>
            Time: <%= dateTime%>
        </td>
    </tr>
    <tr>
        <td>
            <form action="/complete-cancel" method="post">
                <input type="submit" value="Cancel reservation" />
            </form>"
        </td>
    </tr>
</table>
</body>
</html>