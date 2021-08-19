<%@ page import="entities.VaccineCenter" %>
<%@ page import="java.util.List" %>
<%@ page import="daos.VaccineCenterDAO" %>
<%@ page import="databaseconfigs.DB" %>

<% List<String> districts = (List<String>) request.getServletContext().getAttribute("districts");%>
<% String reg = (String) request.getServletContext().getAttribute("region");%>
<% String city = (String) request.getServletContext().getAttribute("city");%>
<html>
<head>
    <title>Reservation</title>
</head>
<body>
<h2>Step 3</h2>
<form action="/reserve-center" method="post">

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
                City:
            </td>
            <td>
                <select name="city" id="city">
                    <option value="<%=city %>"><%=city %></option>
                </select>


            </td>

        </tr>
        <tr>
            <td>
                Choose A District:
            </td>
            <td>
                <select name="district" id="dis">
                    <%  for(String s : districts){ %>
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