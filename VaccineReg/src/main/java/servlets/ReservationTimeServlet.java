package servlets;

import daos.LocationDAO;
import daos.VaccineCenterDAO;
import entities.VaccineCenter;
import utils.Pair;
import utils.Times;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationTimeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocationDAO locationDAO = (LocationDAO) req.getServletContext().getAttribute("locationDAO");
        VaccineCenterDAO vaccineCenterDAO = (VaccineCenterDAO) req.getServletContext().getAttribute("vaccineCenterDAO");
        String region = req.getParameter("region");
        req.getServletContext().setAttribute("region", region);
        String city = req.getParameter("city");
        req.getServletContext().setAttribute("city", city);
        String district = req.getParameter("district");
        req.getServletContext().setAttribute("district", district);
        String center = req.getParameter("center");
        req.getServletContext().setAttribute("center", center);
        String vacAmount = req.getParameter("vacAmount");
        req.getServletContext().setAttribute("vacAmount", vacAmount);
        String date = req.getParameter("date");
        req.getServletContext().setAttribute("date", date);
        /** This part had to be changed because of the intellij issue

         List<String> dateTimes = locationDAO.getAvailableTimes(vacAmount,locationDAO.getIdByVaccineAndCenter( vaccineCenterDAO.getIdByCenterName(center), vacAmount), date, center);
         req.getServletContext().setAttribute("dateTimes", dateTimes);
         */
        List<String> times = utils.Times.generate();
        /**
         List<String> dateTimes = new ArrayList<>();
         for(String s : times){
         dateTimes.add(date + " " + s);
         }*/
        req.getServletContext().setAttribute("time", times);
        req.getRequestDispatcher("WEB-INF/reservation-time.jsp").forward(req, resp);
    }
}
