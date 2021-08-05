package listeners;


import vaccinereg.UsersDAO;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        UsersDAO dao = new UsersDAO();
        servletContextEvent.getServletContext().setAttribute("usersDAO", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}