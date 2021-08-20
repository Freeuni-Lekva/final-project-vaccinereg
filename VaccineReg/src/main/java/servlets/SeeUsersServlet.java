package servlets;

import daos.ReservationsDAO;
import daos.UsersDAO;
import entities.Reservation;
import entities.User;
import utils.Pair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/see-users")
public class SeeUsersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("user") != null){
            User loggedInUser = (User) session.getAttribute("user");
            if(loggedInUser.getAdmin()){
                UsersDAO dao1 = (UsersDAO) httpServletRequest.getServletContext().getAttribute("usersDAO");
                ReservationsDAO dao2 = (ReservationsDAO) httpServletRequest.getServletContext().getAttribute("reservationsDAO");
                List<User> everyNonUser = dao1.getEveryNonAdminUser();
                List<Reservation> reservations = new ArrayList<>();
                for(User u : everyNonUser){
                    try {
                        reservations.add(dao2.getReservationByUserId(u.getPrivateNum()));
                    } catch (Exception ignored) {

                    }
                }
                List<Pair<User , Reservation>> finalResult = new ArrayList<>();
                for(int i = 0 ; i < everyNonUser.size() ; i++){
                    finalResult.add(new Pair<>(everyNonUser.get(i) , reservations.get(i)));
                }
                httpServletRequest.setAttribute("users" , finalResult);
                httpServletRequest.getRequestDispatcher("/WEB-INF/see-users.jsp").forward(httpServletRequest, httpServletResponse);
            }else{
                httpServletResponse.getWriter().println("You are not authorized to view this page");
            }
        }else{
            httpServletResponse.getWriter().println("You are not authorized to view this page");
        }
    }
}
