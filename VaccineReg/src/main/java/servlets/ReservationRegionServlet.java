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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VaccineCenterDAO vaccineCenterDAO = (VaccineCenterDAO) req.getServletContext().getAttribute("vaccineCenterDAO");
        List<String> regions = vaccineCenterDAO.getAllRegions();
        req.getServletContext().setAttribute("regions", regions);
        req.getRequestDispatcher("WEB-INF/reservation-region.jsp").forward(req, resp);
    }

}
