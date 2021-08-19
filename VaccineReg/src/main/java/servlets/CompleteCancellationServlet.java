package servlets;


import daos.LocationDAO;
import daos.ReservationsDAO;
import daos.VaccineCenterDAO;
import entities.LocationVaccineAmount;
import entities.Reservation;
import entities.User;
import entities.VaccineCenter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompleteCancellationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReservationsDAO reservationDAO = (ReservationsDAO) req.getServletContext().getAttribute("reservationsDAO");
        VaccineCenterDAO vaccineCenterDAO = (VaccineCenterDAO) req.getServletContext().getAttribute("vaccineCenterDAO");
        LocationDAO locationDAO = (LocationDAO) req.getServletContext().getAttribute("locationDAO");
        User user = (User) req.getSession().getAttribute("user");
        Reservation reservation = reservationDAO.getNextVaccination(user);
        reservationDAO.deleteReservation(reservation.getId());

        req.getRequestDispatcher("/WEB-INF/userpage.jsp").forward(req, resp);
    }
}
