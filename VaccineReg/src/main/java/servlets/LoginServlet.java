package servlets;
import entities.User;
import daos.UsersDAO;
import utils.HashPassword;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null){
            req.getRequestDispatcher("WEB-INF/adminpage.jsp").forward(req , resp);
        }else {
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
        }
    }


    @Override
    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException
    {
        // get dao and user parameters
        UsersDAO dao = (UsersDAO) req.getServletContext().getAttribute("usersDAO");
        String email = req.getParameter("email");
        String password= HashPassword.getHash(req.getParameter("password"));


        // try to log in
        User user = dao.getUserByEmail(email);
        if(user == null){
            req.setAttribute("email_typed", email);
            req.getRequestDispatcher("WEB-INF/login-fail.jsp").forward(req, resp);
        }
        else{
            if ( user.getPassword().equals(password) ){
                req.getSession().setAttribute("user", user);
                if(!user.getAdmin()) {
                    req.getRequestDispatcher("WEB-INF/userpage.jsp").forward(req, resp);
                }else{
                    req.getRequestDispatcher("WEB-INF/adminpage.jsp").forward(req , resp);
                }
            }
            else{
                req.setAttribute("email_typed", email);
                req.getRequestDispatcher("WEB-INF/login-fail.jsp").forward(req, resp);
            }
        }
    }
}
