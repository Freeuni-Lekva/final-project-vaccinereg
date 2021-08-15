<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page isELIgnored="false" %>

<html>
    <body>
    <table>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>Private number: <c:out value="${user.getFirst().getPrivateNum()}"/> ---- </td>
                <td>Name: <c:out value="${user.getFirst().getName()}"/> ---- </td>
                <td>Last name: <c:out value="${user.getFirst().getLastName()}"/> ---- </td>
                <td>Gender: <c:out value="${user.getFirst().getGender()}"/> ---- </td>
                <td>Birth date: <c:out value="${user.getFirst().getBirthDate()}"/> ---- </td>
                <td>Email: <c:out value="${user.getFirst().getEmail()}"/></td>
            </tr>
            <tr>
                <c:choose>
                    <c:when test="${user.getSecond() != null}">
                        <c:choose>
                            <c:when test="${user.getSecond().getId() != null}">
                                <td>Reservation id: <c:out value="${user.getSecond().getId()}"/> ---- </td>
                            </c:when>
                            <c:otherwise>
                                <td>Reservation id: not set ---- </td>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <td>Reservation id: not set ---- </td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${user.getSecond() != null}">
                        <c:choose>
                            <c:when test="${user.getSecond().getVaccinationTime() != null}">
                                <td>Reservation id: <c:out value="${user.getSecond().getVaccinationTime()}"/> ---- </td>
                            </c:when>
                            <c:otherwise>
                                <td>Vaccination time: not set ---- </td>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <td>Vaccination time: not set ---- </td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${user.getSecond() != null}">
                        <c:choose>
                            <c:when test="${user.getSecond().getReservationTime() != null}">
                                <td>Reservation id: <c:out value="${user.getSecond().getReservationTime()}"/> ---- </td>
                            </c:when>
                            <c:otherwise>
                                <td>Reservation time: not set ---- </td>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <td>Reservation time: not set ---- </td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${user.getSecond() != null}">
                        <c:choose>
                            <c:when test="${user.getSecond().getLocation_vaccine_amount_id() != null}">
                                <td>Reservation id: <c:out value="${user.getSecond().getLocation_vaccine_amount_id()}"/> ---- </td>
                            </c:when>
                            <c:otherwise>
                                <td>Location vaccine amount id: not set ---- </td>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <td>Location vaccine amount id: not set</td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
    <form action = "/login" method="get">
        <input type ="submit" value = "go back">
    </form>
    </body>
</html>