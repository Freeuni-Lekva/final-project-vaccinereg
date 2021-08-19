<%@ page import="entities.VaccineCenter" %>
<%@ page import="java.util.List" %>
<%@ page import="daos.VaccineCenterDAO" %>
<%@ page import="databaseconfigs.DB" %>
<%@ page import="utils.Pair" %>
<%@ page import="java.time.LocalDate" %>

<% String reg = (String) request.getServletContext().getAttribute("region");%>
<% String city = (String) request.getServletContext().getAttribute("city");%>
<% String dis = (String) request.getServletContext().getAttribute("district");%>
<% String cen = (String) request.getServletContext().getAttribute("center");%>
<% String vacAmount = (String) request.getServletContext().getAttribute("vacAmount");%>
<html>
<head>
    <title>Reservation</title>
</head>
<body>
<h2>Step 6</h2>
<form action="/reserve-time" method="post">

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
                District:
            </td>
            <td>
                <select name="district" id="dis">
                    <option value="<%=dis %>"><%=dis %></option>
                </select>


            </td>

        </tr>
        <tr>
            <td>
                Center:
            </td>
            <td>
                <select name="center" id="cen">
                    <option value="<%=cen %>"><%=cen %></option>
                </select>


            </td>

        </tr>
        <tr>
            <td>
                Vaccine:
            </td>
            <td>
                <select name="vacAmount" id="vac">
                    <option value="<%=vacAmount %>"><%=vacAmount %></option>
                </select>


            </td>

        </tr>
        <tr>
            <td>
                Choose A Date:
            </td>
            <td>
                <input type="date" name="date" min="<%= LocalDate.now().plusDays(1)%>" placeholder="<%= LocalDate.now().plusDays(1)%>">
            </td>
            <td>
                <input type="submit" value="Choose">
            </td>
        </tr>
    </table>
</form>
</body>
</html>