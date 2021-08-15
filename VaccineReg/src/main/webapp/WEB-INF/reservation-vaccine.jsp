<%@ page import="entities.VaccineCenter" %>
<%@ page import="java.util.List" %>
<%@ page import="daos.VaccineCenterDAO" %>
<%@ page import="databaseconfigs.DB" %>
<%@ page import="utils.Pair" %>

<% List<Pair<String, Integer> > vacAmounts = (List<Pair<String, Integer> >) request.getServletContext().getAttribute("vacAmounts");%>
<% String reg = (String) request.getServletContext().getAttribute("region");%>
<% String city = (String) request.getServletContext().getAttribute("city");%>
<% String dis = (String) request.getServletContext().getAttribute("district");%>
<% String cen = (String) request.getServletContext().getAttribute("center");%>
<html>
<head>
    <title>Reservation</title>
</head>
<body>
<h2>Step 4</h2>
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
                District:
            </td>
            <td>
                <select name="center" id="cen">
                    <option value="<%=cen %>"><%=cen %></option>
                </select>


            </td>

        </tr>
        <tr>
            <td>
                Choose A Center:
            </td>
            <td>
                <select name="vaccines" id="vac">
                    <%  for(Pair<String, Integer> p : vacAmounts){ %>
                    <option value=<%=p.getFirst()%>><%=p.getFirst()%>  (<%=p.getSecond()%>)</option>
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