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

public class CancelServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReservationsDAO reservationDAO = (ReservationsDAO) req.getServletContext().getAttribute("reservationsDAO");
        VaccineCenterDAO vaccineCenterDAO = (VaccineCenterDAO) req.getServletContext().getAttribute("vaccineCenterDAO");
        LocationDAO locationDAO = (LocationDAO) req.getServletContext().getAttribute("locationDAO");
        User user = (User) req.getSession().getAttribute("user");
        Reservation reservation = reservationDAO.getNextVaccination(user);
        req.getServletContext().setAttribute("time", reservation.getVaccinationTime());
        LocationVaccineAmount lva = locationDAO.getLocationVaccineAmount(reservation.getLocation_vaccine_amount_id());
        req.getServletContext().setAttribute("vaccine", lva.getVaccineName());
        VaccineCenter vc = vaccineCenterDAO.getVaccineCenterById(lva.getVaccineCenterId());
        String center = vc.getCenterName() + ", " + vc.getDistrictName() +
                ", " + vc.getCityName() + ", " + vc.getRegionName();
        req.getServletContext().setAttribute("center", center);

        req.getRequestDispatcher("/WEB-INF/cancel-reservation.jsp").forward(req, resp);
    }
}
