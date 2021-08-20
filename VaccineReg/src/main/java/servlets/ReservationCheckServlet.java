package servlets;

import com.mysql.cj.result.LocalDateTimeValueFactory;
import daos.LocationDAO;
import daos.ReservationsDAO;
import daos.VaccineCenterDAO;
import entities.Reservation;
import entities.User;

import javax.servlet.Registration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ReservationCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocationDAO locationDAO = (LocationDAO) req.getServletContext().getAttribute("locationDAO");
        VaccineCenterDAO vaccineCenterDAO = (VaccineCenterDAO) req.getServletContext().getAttribute("vaccineCenterDAO");
        String region = req.getParameter("region");
        req.getServletContext().setAttribute("region", region);
        String city = req.getParameter("city");
        req.getServletContext().setAttribute("city", city);
        String district = req.getParameter("district");
        req.getServletContext().setAttribute("district", district);
        String center = req.getParameter("center");
        req.getServletContext().setAttribute("center", center);
        String vacAmount = req.getParameter("vacAmount");
        req.getServletContext().setAttribute("vacAmount", vacAmount);
        String date = req.getParameter("date");
        req.getServletContext().setAttribute("date", date);
        String time = req.getParameter("time");
        User user = (User) req.getSession().getAttribute("user");
        ReservationsDAO reservationsDAO = (ReservationsDAO) req.getServletContext().getAttribute("reservationsDAO");

        if(user.getAge()<16){
            req.getServletContext().setAttribute("errorMessage", "you have to be at least 16 to get the vaccine" );
            req.getRequestDispatcher("WEB-INF/reservation-fail.jsp").forward(req, resp);
        } else if(reservationsDAO.getReservationsCountByUserId(user.getPrivateNum())==2){
            req.getServletContext().setAttribute("errorMessage", time + " you are already registered for two injections");
            req.getRequestDispatcher("WEB-INF/reservation-fail.jsp").forward(req, resp);
        } else if(!locationDAO.checkAvailibility(vacAmount,  locationDAO.getIdByVaccineAndCenter( vaccineCenterDAO.getIdByCenterName(center), vacAmount), date, time,  center)) {
            List<String> times = utils.Times.generate();
            req.getServletContext().setAttribute("times", times);
            req.getServletContext().setAttribute("errorMessage", time + " is unavailable");
            req.getRequestDispatcher("WEB-INF/reservation-fail.jsp").forward(req, resp);
        }  else if(reservationsDAO.getReservationsCountByUserId(user.getPrivateNum())==1){
            Reservation first = reservationsDAO.getReservationByUserId(user.getPrivateNum());
            LocalDateTime minimumForSecond = first.getReservationTime().plusDays(21);
            LocalDateTime second = LocalDateTime.of(Integer.valueOf(date.substring(0,4)),Integer.valueOf(date.substring(5,7)) ,
                    Integer.valueOf(date.substring(8)) ,Integer.valueOf(time.substring(0,2)) ,Integer.valueOf(time.substring(3,5)));
            if (second.compareTo(minimumForSecond) < 0){
                List<String> times = utils.Times.generate();
                req.getServletContext().setAttribute("times", times);
                req.getServletContext().setAttribute("errorMessage", time + " you can get the second injection only after at least 21 days has passed from the first");
                req.getRequestDispatcher("WEB-INF/reservation-fail.jsp").forward(req, resp);
            }
        } else {
            LocalDateTime vaccinationTime = LocalDateTime.of(Integer.valueOf(date.substring(0,4)),Integer.valueOf(date.substring(5,7)) ,
                    Integer.valueOf(date.substring(8)) ,Integer.valueOf(time.substring(0,2)) ,Integer.valueOf(time.substring(3,5)));
            if(reservationsDAO.addReservationNoId(
                    new Reservation(vaccinationTime,
                    LocalDateTime.now(),
                    locationDAO.getIdByVaccineAndCenter(vaccineCenterDAO.getIdByCenterName(center),vacAmount ),
                    user.getPrivateNum())
            )){
                Long locationId = locationDAO.getIdByVaccineAndCenter(vaccineCenterDAO.getIdByCenterName(center),vacAmount);
                int amount = locationDAO.getLocationVaccineAmount(locationId).getAmount();
                locationDAO.setVaccineAmount(locationId, amount--);
            }

            req.getRequestDispatcher("WEB-INF/userpage.jsp").forward(req, resp);
        }




    }
}
