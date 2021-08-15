package servlets;

import daos.VaccineCenterDAO;
import entities.VaccineCenter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ReservationRegionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VaccineCenterDAO vaccineCenterDAO = (VaccineCenterDAO) req.getServletContext().getAttribute("vaccineCenterDAO");
        List<VaccineCenter> centers = vaccineCenterDAO.getAllVaccineCenters();
        req.getServletContext().setAttribute("centers", centers);
        req.getRequestDispatcher("WEB-INF/reservation-region.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String region = req.getParameter("region");
        req.setAttribute("region", region);
        //req.getRequestDispatcher("WEB-INF/reservation-city.jsp").forward(req, resp);
    }

}
