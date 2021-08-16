package servlets;

import daos.VaccineCenterDAO;
import entities.User;
import entities.VaccineCenter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-vaccine-center")
public class AddVaccineCenterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("user") != null){
            User loggedInUser = (User) session.getAttribute("user");
            if(loggedInUser.getAdmin()){
                VaccineCenterDAO dao = (VaccineCenterDAO) httpServletRequest.getServletContext().getAttribute("vaccineCenterDAO");

                VaccineCenter vaccineCenter = new VaccineCenter();
                vaccineCenter.setRegionName(httpServletRequest.getParameter("region-name"));
                vaccineCenter.setCityName(httpServletRequest.getParameter("city-name"));
                vaccineCenter.setDistrictName(httpServletRequest.getParameter("district-name"));
                vaccineCenter.setPeopleLimitPerVaccineAtSameTime(Integer.parseInt(httpServletRequest.getParameter("limit")));
                vaccineCenter.setCenterName(httpServletRequest.getParameter("center-name"));

                dao.addVaccineCenter(vaccineCenter);

                httpServletRequest.setAttribute("vaccineCenters" , dao.getAllVaccineCenters());
                httpServletRequest.getRequestDispatcher("/WEB-INF/vaccine-centers.jsp").forward(httpServletRequest, httpServletResponse);
            }else{
                httpServletResponse.getWriter().println("You are not authorized to view this page");
            }
        }else{
            httpServletResponse.getWriter().println("You are not authorized to view this page");
        }
    }
}
