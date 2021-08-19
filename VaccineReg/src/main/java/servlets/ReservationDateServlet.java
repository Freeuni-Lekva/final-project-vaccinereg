package servlets;

import daos.LocationDAO;
import daos.VaccineCenterDAO;
import entities.VaccineCenter;
import utils.Pair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReservationDateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String region = req.getParameter("region");
        req.getServletContext().setAttribute("region", region);
        String city = req.getParameter("city");
        req.getServletContext().setAttribute("city", city);
        String district = req.getParameter("district");
        req.getServletContext().setAttribute("district", district);
        String center = req.getParameter("center");
        req.getServletContext().setAttribute("center", center);
        String vacAmount = req.getParameter("vaccines");
        req.getServletContext().setAttribute("vacAmount", vacAmount);
        req.getRequestDispatcher("WEB-INF/reservation-date.jsp").forward(req, resp);
    }
}
