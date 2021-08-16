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
            <td>People limit per vaccine: <c:out value="${center.getPeopleLimitPerVaccineAtSameTime()}"/> ---- </td>
            <td>Center name: <c:out value="${center.getCenterName()}"/> ---- </td>
        </tr>
    </c:forEach>
</table>

<br>
<br>
<br>

<form action = "/add-vaccine-center" method="post">
    <table>
    <tr><td>Region Name: </td><td><input type = "text" name = "region-name" required></td></tr>
    <tr><td>City Name: </td><td><input type = "text" name = "city-name" required></td></tr>
    <tr><td>District Name: </td><td><input type = "text" name = "district-name" required></td></tr>
    <tr><td>People limit per vaccine: </td><td><input type = "text" name = "limit" required></td></tr>
    <tr><td>Center name: </td><td><input type = "text" name = "center-name" required></td></tr>
    <td><input type = "submit" value = "add"></td></tr>
    </table>
</form>

<form action = "/login" method="get">
    <input type ="submit" value = "go back">
</form>
</body>
</html>