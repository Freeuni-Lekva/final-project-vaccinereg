package servlets;

import vaccinereg.User;
import vaccinereg.UsersDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }


    @Override
    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException
    {
        UsersDAO dao = (UsersDAO) req.getServletContext().getAttribute("usersDAO");
        String email = req.getParameter("email");
        String password= req.getParameter("password");

        User user = dao.getUserByEmail(email);
        if(user == null){
            req.getRequestDispatcher("WEB-INF/login-fail.jsp").forward(req, resp);
        }
        else{
            if ( user.getPassword().equals(password) ){
                req.setAttribute("privatenum", user.getPrivateNum());
                req.getRequestDispatcher("WEB-INF/userpage.jsp").forward(req, resp);
            }
            else{
                req.getRequestDispatcher("WEB-INF/login-fail.jsp").forward(req, resp);
            }
        }

        /*AccountManager manager = (AccountManager) req.getServletContext().getAttribute("manager");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ( manager.userExists(username) ){
            if ( manager.matchesPassword(username, password) ){
                req.setAttribute("name", username);
                req.getRequestDispatcher("WEB-INF/welcome.jsp").forward(req, resp);
            }
            else{
                req.getRequestDispatcher("WEB-INF/login-fail.jsp").forward(req, resp);
            }
        }
        else{
            req.getRequestDispatcher("WEB-INF/login-fail.jsp").forward(req, resp);
        }*/
    }
}