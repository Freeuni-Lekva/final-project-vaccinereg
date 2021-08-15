<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page isELIgnored="false" %>

<html>
<body>
<table>
    <c:forEach items="${vaccineCenters}" var="center">
        <tr>
            <td>Id: <c:out value="${center.getId()}"/> ---- </td>
            <td>Region name: <c:out value="${center.getRegionName()}"/> ---- </td>
            <td>City name: <c:out value="${center.getCityName()}"/> ---- </td>
            <td>District name: <c:out value="${center.getDistrictName()}"/> ---- </td>
            <td>People limit per vaccine: <c:out value="${center.getPeopleLimitPerVaccineAtSameTime()}"/></td>
        </tr>
    </c:forEach>
</table>

<br>
<br>
<br>

<form action = "/add-vaccine-center" method="post">
    Region Name: <input type = "text" name = "region-name" required>
    City Name: <input type = "text" name = "city-name" required>
    District Name: <input type = "text" name = "district-name" required>
    People limit per vaccine: <input type = "text" name = "limit" required>
    <input type = "submit" value = "add">
</form>

<form action = "/login" method="get">
    <input type ="submit" value = "go back">
</form>
</body>
</html>