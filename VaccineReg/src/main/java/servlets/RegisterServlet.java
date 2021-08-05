package servlets;

import vaccinereg.User;
import vaccinereg.UsersDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException, IOException
    {
        req.getRequestDispatcher("WEB-INF/register.jsp").forward(req, resp);
    }


    @Override
    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp) throws ServletException, IOException
    {
        User user = new User(
                Long.parseLong(req.getParameter("private_num")),
                req.getParameter("name"),
                req.getParameter("last_name"),
                req.getParameter("gender"),
                Integer.parseInt(req.getParameter("age")),
                req.getParameter("email"),
                req.getParameter("password")
        );


        UsersDAO dao = (UsersDAO) req.getServletContext().getAttribute("usersDAO");
        boolean success = dao.addUser(user);

        if(success){
            req.setAttribute("privatenum", user.getPrivateNum());
            req.getRequestDispatcher("WEB-INF/userpage.jsp").forward(req, resp);
        }
        else{
            req.getRequestDispatcher("WEB-INF/register-fail.jsp").forward(req, resp);
        }
    }
}