<%@ page import="java.util.List" %>
<%@ page import="entities.VaccineCenter" %>
<% List<VaccineCenter> centers = (List<VaccineCenter>) request.getServletContext().getAttribute("centers");%>
<html>
    <head>
        <title>Statistics</title>
    </head>

    <body>
        <form action="/statistics" method="post">
            <table>
                <tr>
                    <td>Gender:</td>
                    <td>
                        <select name="gender">
                            <option value="n">(default)</option>
                            <option value="m">m</option>
                            <option value="f">f</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Region:</td>
                    <td>
                        <select name="region">
                            <option value="n">(default)</option>
                            <%  for(VaccineCenter center : centers){ %>
                            <option value=<%=center.getRegionName()%>><%= center.getRegionName()%></option>
                            <% } %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Min age:</td>
                    <td><input type="number" min="0" max="100" placeholder="0" name="minage" required></td>
                </tr>
                <tr>
                    <td>Max age:</td>
                    <td><input type="number" min="0" max="100" placeholder="100" name="maxage" required></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Filter"></td>
                </tr>
            </table>
        </form>

    </body>
</html>