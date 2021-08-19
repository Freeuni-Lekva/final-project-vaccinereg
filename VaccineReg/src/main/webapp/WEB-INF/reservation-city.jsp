<%@ page import="entities.VaccineCenter" %>
<%@ page import="java.util.List" %>
<%@ page import="daos.VaccineCenterDAO" %>
<%@ page import="databaseconfigs.DB" %>

<% List<String> cities = (List<String>) request.getServletContext().getAttribute("cities");%>
<% String reg = (String) request.getServletContext().getAttribute("region");%>
<html>
<head>
    <title>Reservation</title>
</head>
<body>
<h2>Step 2</h2>
<form action="/reserve-district" method="post">

    <table>
        <tr>
            <td>
                Region:
            </td>
            <td>
                <select name="region" id="reg">
                    <option value="<%=reg %>"><%=reg %></option>
                </select>


            </td>

        </tr>
        <tr>
            <td>
                Choose A City:
            </td>
            <td>
                <select name="city" id="cit">
                    <%  for(String s : cities){ %>
                    <option value=<%=s%>><%= s%></option>
                    <% } %>
                </select>
            </td>
            <td>
                <input type="submit" value="Choose">
            </td>
        </tr>
    </table>
</form>
</body>
</html>