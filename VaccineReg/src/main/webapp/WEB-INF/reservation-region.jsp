<%@ page import="entities.VaccineCenter" %>
<%@ page import="java.util.List" %>
<%@ page import="daos.VaccineCenterDAO" %>

<% List<VaccineCenter> centers = (List<VaccineCenter>) request.getServletContext().getAttribute("centers");%>

<html>
    <head>
        <title>Reservation Region</title>
    </head>
    <body>
        <h2>Step 1</h2>
        <form action="/reserve-city" method="post">
            <table>
                <tr>
                    <td>
                        Choose A Region:
                    </td>
                    <td>
                        <select name="region" id="reg">
                            <%  for(VaccineCenter center : centers){ %>
                            <option value=<%=center.getRegionName()%>><%= center.getRegionName()%></option>
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