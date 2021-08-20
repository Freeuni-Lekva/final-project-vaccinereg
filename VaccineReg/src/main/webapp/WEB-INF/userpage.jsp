<%@ page import = "java.util.*" %>
<%@ page import = "entities.User" %>
<%@ page import = "daos.ReservationsDAO" %>
<%@ page import="daos.LocationDAO" %>
<%@ page import="entities.VaccineCenter" %>
<%@ page import="daos.VaccineCenterDAO" %>
<%@ page import="entities.Reservation" %>
<%@ page import="entities.LocationVaccineAmount" %>

<html>
    <head>
        <%  User user = (User) request.getSession().getAttribute("user");
            ReservationsDAO resDAO = (ReservationsDAO) request.getServletContext().getAttribute("reservationsDAO");
            LocationDAO locationDAO = (LocationDAO) request.getServletContext().getAttribute("locationDAO");
            VaccineCenterDAO vaccineCenterDAO = (VaccineCenterDAO) request.getServletContext().getAttribute("vaccineCenterDAO");%>

        <title>Welcome</title>
    </head>
    <body>

        
        <h1>Welcome <%= user.getName() %> <%= user.getLastName() %>! </h1>

        <!-- Printing out reservation info -->
        <%
            if (resDAO.getNextVaccination(user) == null){
                out.println(
                "<p>" +
                    "You are not currently registered for a vaccine. " +
                    "If you want to register for a vaccine, press the \"Register for a vaccine\" button." +
                "</p>"
                );

                out.println(
                "<form action=\"/reserve-region\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Register for a vaccine\" />" +
                "</form>"
                );
            }
            else{
                Reservation reservation = resDAO.getNextVaccination(user);
                LocationVaccineAmount lva =locationDAO.getLocationVaccineAmount(reservation.getLocation_vaccine_amount_id());
                VaccineCenter center = vaccineCenterDAO.getVaccineCenterById(lva.getVaccineCenterId());

                String time = reservation.getVaccinationTime().toString().replace('T', ' ');
                String vaccine = lva.getVaccineName();
                String centerLoc = center.getCenterName() + ", " +center.getDistrictName()   + ", " +
                        center.getCityName() + ", " + center.getRegionName();

                out.println(
                "<p>" +
                    "You are already registered for a vaccine. " +
                    "If you want to cancel your reservation, press the \"Cancel reservation\" button." +
                "</p>" +
                        "<h3>" + "Reservation info: " + "</h3>" +
                        "<p>" + "Center: " + centerLoc + "</p>" +
                        "<p>" + "Vaccine: " + vaccine + "</p>" +
                        "<p>" + "Time: " + time + "</p>"
                );

                out.println(
                "<form action=\"/cancel\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Cancel reservation\" />" +
                "</form>"
                );
            }
        %>


        <%
            if (user.getAdmin()){
                out.println(
                "<form action=\"/admin\" method=\"post\">" +
                    "<input type=\"submit\" value=\"Manage website\" />" +
                "</form>"
                );
            }
        %>

      
    <form action="/statistics" method="get">
        <input type="submit" value="statistics">
    </form>

    </body>
</html>