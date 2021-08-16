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
import java.util.List;

@WebServlet("/vaccine-center")
public class VaccineCenterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("user") != null){
            User loggedInUser = (User) session.getAttribute("user");
            if(loggedInUser.getAdmin()){
                VaccineCenterDAO dao = (VaccineCenterDAO) httpServletRequest.getServletContext().getAttribute("vaccineCenterDAO");
                List<VaccineCenter> everyVaccineCenter = dao.getAllVaccineCenters();
                httpServletRequest.setAttribute("vaccineCenters" , everyVaccineCenter);
                httpServletRequest.getRequestDispatcher("/WEB-INF/vaccine-centers.jsp").forward(httpServletRequest, httpServletResponse);
            }else{
                httpServletResponse.getWriter().println("You are not authorized to view this page");
            }
        }else{
            httpServletResponse.getWriter().println("You are not authorized to view this page");
        }
    }
}
