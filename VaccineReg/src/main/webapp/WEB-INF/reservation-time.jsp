<%@ page import="entities.VaccineCenter" %>
<%@ page import="java.util.List" %>
<%@ page import="daos.VaccineCenterDAO" %>
<%@ page import="databaseconfigs.DB" %>
<%@ page import="utils.Pair" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>

<% String reg = (String) request.getServletContext().getAttribute("region");%>
<% String city = (String) request.getServletContext().getAttribute("city");%>
<% String dis = (String) request.getServletContext().getAttribute("district");%>
<% String cen = (String) request.getServletContext().getAttribute("center");%>
<% String vacAmount = (String) request.getServletContext().getAttribute("vacAmount");%>
<% String date = (String) request.getServletContext().getAttribute("date");%>
<% List<String> times = (List<String>) request.getServletContext().getAttribute("time"); %>


<html>
<head>
    <title>Reservation</title>
</head>
<body>
<h2>Step 6</h2>
<form action="/reserve-check" method="post">

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
                Date:
            </td>
            <td>
                <select name="date" id="date">
                    <option value="<%=date %>"><%=date %></option>
                </select>


            </td>

        </tr>
        <tr>
            <td>
                Time:
            </td>
            <td>
                <select name="time" id="time">
                    <%  for(String s : times){ %>
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