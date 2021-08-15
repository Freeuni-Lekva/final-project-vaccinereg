package listeners;


import daos.LocationDAO;
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
        LocationDAO locationDAO = new LocationDAO();
        servletContextEvent.getServletContext().setAttribute("usersDAO", dao);
        servletContextEvent.getServletContext().setAttribute("reservationsDAO", reservationsDAO);
        servletContextEvent.getServletContext().setAttribute("vaccineCenterDAO", vaccineCenterDAO);
        servletContextEvent.getServletContext().setAttribute("locationDAO", locationDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}