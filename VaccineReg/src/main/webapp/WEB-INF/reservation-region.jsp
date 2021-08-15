<%@ page import="entities.VaccineCenter" %>
<%@ page import="java.util.List" %>
<%@ page import="daos.VaccineCenterDAO" %>

<% List<VaccineCenter> centers = (List<VaccineCenter>) request.getServletContext().getAttribute("centers");%>

<html>
    <head>
        <title>Reservation</title>
    </head>
    <body>
        <h1>temporary reservation page</h1>
        <form action="/reservation" method="post">
            <table>
                <tr>
                    <td>
                        Region:
                    </td>
                    <td>
                        <select name="region" id="reg">
                            <option value="n">(default)</option>
                            <%  for(VaccineCenter center : centers){ %>
                            <option value=<%=center.getRegionName()%>><%= center.getRegionName()%></option>
                            <% } %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Choose">
                    </td>
                </tr>

            </table>
        </form>
    </body>
</html>