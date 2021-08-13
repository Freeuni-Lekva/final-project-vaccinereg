package listeners;


import daos.ReservationsDAO;
import daos.UsersDAO;
import daos.VaccineCenterDAO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        UsersDAO dao = new UsersDAO();
        ReservationsDAO reservationsDAO = new ReservationsDAO();
        VaccineCenterDAO vaccineCenterDAO = new VaccineCenterDAO();
        servletContextEvent.getServletContext().setAttribute("usersDAO", dao);
        servletContextEvent.getServletContext().setAttribute("reservationsDAO", reservationsDAO);
        servletContextEvent.getServletContext().setAttribute("vaccineCenterDAO", vaccineCenterDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}