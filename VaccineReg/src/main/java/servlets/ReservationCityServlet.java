package servlets;

import daos.VaccineCenterDAO;
import entities.VaccineCenter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReservationCityServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VaccineCenterDAO vaccineCenterDAO = (VaccineCenterDAO) req.getServletContext().getAttribute("vaccineCenterDAO");
        List<VaccineCenter> centers = vaccineCenterDAO.getAllVaccineCenters();
        String region = req.getParameter("region");
        req.getServletContext().setAttribute("region", region);
        List<String> cities = vaccineCenterDAO.getCityByRegionName(region);
        req.getServletContext().setAttribute("cities", cities);
        req.getRequestDispatcher("WEB-INF/reservation-city.jsp").forward(req, resp);
    }
}
