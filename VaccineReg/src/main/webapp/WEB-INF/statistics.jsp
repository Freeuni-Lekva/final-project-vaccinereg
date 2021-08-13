<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Time</td>
        <td>Registered</td>
        <td>Vaccinated</td>
    </tr>
    <tr>
        <td>Last Hour</td>
        <td><%=request.getAttribute("rhour")%></td>
        <td><%=request.getAttribute("vhour")%></td>
    </tr>
    <tr>
        <td>Last 24hrs</td>
        <td><%=request.getAttribute("rday")%></td>
        <td><%=request.getAttribute("vday")%></td>
    </tr>
    <tr>
        <td>Last 7 Days</td>
        <td><%=request.getAttribute("rweek")%></td>
        <td><%=request.getAttribute("vweek")%></td>
    </tr>
    <tr>
        <td>Last 30 Days</td>
        <td><%=request.getAttribute("rmonth")%></td>
        <td><%=request.getAttribute("vmonth")%></td>
    </tr>
</table>
</body>
</html>
