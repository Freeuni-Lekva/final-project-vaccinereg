package servlets;

import daos.VaccineCenterDAO;
import entities.VaccineCenter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReservationDistrictServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VaccineCenterDAO vaccineCenterDAO = (VaccineCenterDAO) req.getServletContext().getAttribute("vaccineCenterDAO");
        String region = req.getParameter("region");
        req.getServletContext().setAttribute("region", region);
        String city = req.getParameter("city");
        req.getServletContext().setAttribute("city", city);
        List<String> districts = vaccineCenterDAO.getDistrictByCityName(city);
        req.getServletContext().setAttribute("districts", districts);
        req.getRequestDispatcher("WEB-INF/reservation-district.jsp").forward(req, resp);
    }
}
