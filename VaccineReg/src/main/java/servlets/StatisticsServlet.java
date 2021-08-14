package servlets;

import daos.ReservationsDAO;
import daos.VaccineCenterDAO;
import entities.VaccineCenter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StatisticsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException, IOException
    {
        VaccineCenterDAO vaccineCenterDAO = (VaccineCenterDAO) req.getServletContext().getAttribute("vaccineCenterDAO");
        List<VaccineCenter> centers = vaccineCenterDAO.getAllVaccineCenters();
        req.getServletContext().setAttribute("centers", centers);
        req.getRequestDispatcher("WEB-INF/statistics-filter.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException
    {
        String gender = req.getParameter("gender");
        String region = req.getParameter("region");
        int minAge = Integer.parseInt(req.getParameter("minage"));
        int maxAge = Integer.parseInt(req.getParameter("maxage"));

        if (minAge > maxAge) {
            req.getRequestDispatcher("WEB-INF/statistics-fail.jsp").forward(req, resp);
        } else {
            ReservationsDAO reservationsDAO = (ReservationsDAO) req.getServletContext().getAttribute("reservationsDAO");
            // rhour - reservation count for the last hour
            // vhour - vaccination count for the last hour
            // n is the default value for the filter
            if (!gender.equals("n") && !region.equals("n")) {
                req.setAttribute("rhour", reservationsDAO.getCountByGenderAndRegionAndAgeByTime(gender, region, minAge, maxAge, 3600).getFirst());
                req.setAttribute("rday", reservationsDAO.getCountByGenderAndRegionAndAgeByTime(gender, region, minAge, maxAge, 24 * 3600).getFirst());
                req.setAttribute("rweek", reservationsDAO.getCountByGenderAndRegionAndAgeByTime(gender, region, minAge, maxAge, 7 * 24 * 3600).getFirst());
                req.setAttribute("rmonth", reservationsDAO.getCountByGenderAndRegionAndAgeByTime(gender, region, minAge, maxAge, 30 * 24 * 3600).getFirst());
                req.setAttribute("vhour", reservationsDAO.getCountByGenderAndRegionAndAgeByTime(gender, region, minAge, maxAge, 3600).getSecond());
                req.setAttribute("vday", reservationsDAO.getCountByGenderAndRegionAndAgeByTime(gender, region, minAge, maxAge, 24 * 3600).getSecond());
                req.setAttribute("vweek", reservationsDAO.getCountByGenderAndRegionAndAgeByTime(gender, region, minAge, maxAge, 7 * 24 * 3600).getSecond());
                req.setAttribute("vmonth", reservationsDAO.getCountByGenderAndRegionAndAgeByTime(gender, region, minAge, maxAge, 30 * 24 * 3600).getSecond());
            } else if (!gender.equals("n")) {
                req.setAttribute("rhour", reservationsDAO.getCountByGenderAndAgeByTime(gender, minAge, maxAge, 3600).getFirst());
                req.setAttribute("rday", reservationsDAO.getCountByGenderAndAgeByTime(gender, minAge, maxAge, 24 * 3600).getFirst());
                req.setAttribute("rweek", reservationsDAO.getCountByGenderAndAgeByTime(gender, minAge, maxAge, 7 * 24 * 3600).getFirst());
                req.setAttribute("rmonth", reservationsDAO.getCountByGenderAndAgeByTime(gender, minAge, maxAge, 30 * 24 * 3600).getFirst());
                req.setAttribute("vhour", reservationsDAO.getCountByGenderAndAgeByTime(gender, minAge, maxAge, 3600).getSecond());
                req.setAttribute("vday", reservationsDAO.getCountByGenderAndAgeByTime(gender, minAge, maxAge, 24 * 3600).getSecond());
                req.setAttribute("vweek", reservationsDAO.getCountByGenderAndAgeByTime(gender, minAge, maxAge, 7 * 24 * 3600).getSecond());
                req.setAttribute("vmonth", reservationsDAO.getCountByGenderAndAgeByTime(gender, minAge, maxAge, 30 * 24 * 3600).getSecond());
            } else if (!region.equals("n")) {
                req.setAttribute("rhour", reservationsDAO.getCountByRegionAndAgeByTime(region, minAge, maxAge, 3600).getFirst());
                req.setAttribute("rday", reservationsDAO.getCountByRegionAndAgeByTime(region, minAge, maxAge, 24 * 3600).getFirst());
                req.setAttribute("rweek", reservationsDAO.getCountByRegionAndAgeByTime(region, minAge, maxAge, 7 * 24 * 3600).getFirst());
                req.setAttribute("rmonth", reservationsDAO.getCountByRegionAndAgeByTime(region, minAge, maxAge, 30 * 24 * 3600).getFirst());
                req.setAttribute("vhour", reservationsDAO.getCountByRegionAndAgeByTime(region, minAge, maxAge, 3600).getSecond());
                req.setAttribute("vday", reservationsDAO.getCountByRegionAndAgeByTime(region, minAge, maxAge, 24 * 3600).getSecond());
                req.setAttribute("vweek", reservationsDAO.getCountByRegionAndAgeByTime(region, minAge, maxAge, 7 * 24 * 3600).getSecond());
                req.setAttribute("vmonth", reservationsDAO.getCountByRegionAndAgeByTime(region, minAge, maxAge, 30 * 24 * 3600).getSecond());
            } else {
                req.setAttribute("rhour", reservationsDAO.getCountByAgeByTime(minAge, maxAge, 3600).getFirst());
                req.setAttribute("rday", reservationsDAO.getCountByAgeByTime(minAge, maxAge, 24 * 3600).getFirst());
                req.setAttribute("rweek", reservationsDAO.getCountByAgeByTime(minAge, maxAge, 7 * 24 * 3600).getFirst());
                req.setAttribute("rmonth", reservationsDAO.getCountByAgeByTime(minAge, maxAge, 30 * 24 * 3600).getFirst());
                req.setAttribute("vhour", reservationsDAO.getCountByAgeByTime(minAge, maxAge, 3600).getSecond());
                req.setAttribute("vday", reservationsDAO.getCountByAgeByTime(minAge, maxAge, 24 * 3600).getSecond());
                req.setAttribute("vweek", reservationsDAO.getCountByAgeByTime(minAge, maxAge, 7 * 24 * 3600).getSecond());
                req.setAttribute("vmonth", reservationsDAO.getCountByAgeByTime(minAge, maxAge, 30 * 24 * 3600).getSecond());
            }
            req.getRequestDispatcher("WEB-INF/statistics.jsp").forward(req, resp);
        }
    }
}
