package daos;

import com.mysql.cj.result.LocalDateTimeValueFactory;
import databaseconfigs.DB;
import entities.LocationVaccineAmount;
import entities.VaccineCenter;
import org.apache.commons.dbcp.BasicDataSource;
import utils.Pair;
import utils.Times;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {
    private String tableName = "location_vaccine_amounts";
    private BasicDataSource ds;

    public LocationDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ignored) {}

        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
        ds.setUsername(DB.username);
        ds.setPassword(DB.password);
    }


    // used for testing
    public LocationDAO(String tableName){
        this.tableName = tableName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ignored) {}

        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
        ds.setUsername(DB.username);
        ds.setPassword(DB.password);
    }

    public boolean addLocationVaccineAmount(LocationVaccineAmount vaccine) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO " + tableName + " VALUES (?, ?, ?, ?);");
            stmt.setLong(1, vaccine.getId());
            stmt.setLong(2, vaccine.getVaccineCenterId());
            stmt.setString(3, vaccine.getVaccineName());
            stmt.setInt(4, vaccine.getAmount());
            stmt.execute();
            con.close();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public List<Pair<String, Integer> >getVaccineAmountsForLocation(Long id){
        List<Pair<String, Integer> > result = new ArrayList<Pair<String, Integer> >();
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT vaccine_name, amount " +
                            "FROM location_vaccine_amounts l " +
                            "WHERE (vaccine_center_id = \"" + id + "\") " );
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result.add(new Pair<>(res.getString(1), res.getInt(2)));
            }
            con.close();
            return result;
        } catch (Exception ignored) {
            return null;
        }
    }

    // NEEDS TO BE FIXED
    public List<String> getAvailableTimes(String vaccineName, Long id, String date){
        /**
        List<String> posTimes = Times.generate();
        List<String> posDateTimes = new ArrayList<>();

        for(String s : posTimes){
            LocalDateTime tmp = LocalDateTime.of(Integer.parseInt(date.substring(0,4)),
                    Integer.parseInt(date.substring(5,7)), Integer.parseInt(date.substring(8,10)),
                    Integer.parseInt(s.substring(0,2)), Integer.parseInt(s.substring(3)));

            String a =tmp.toString().substring(0, 10);
            String b = tmp.toString().substring(11);
            String c = a + " " + b + ":00";
            posDateTimes.add(c);
        }

        List<String> results = new ArrayList<>();
        try {
            Connection con = ds.getConnection();
            for(String tmp : posDateTimes) {
                PreparedStatement stmt = con.prepareStatement(
                        "SELECT Count(*) " +
                                "FROM reservations r " +
                                "WHERE (location_vaccine_amount_id = \"" + id + "\") " +
                                "AND (vaccination_time = \"" + java.sql.Date.valueOf(tmp) +"\")");

                ResultSet res = stmt.executeQuery();
                if(res.next()){
                    PreparedStatement stmt1 = con.prepareStatement(
                            "SELECT  people_limit_per_vaccine_at_same_time " +
                                    "FROM location_vaccine_amounts l " +
                                    "JOIN vaccine_centers v  " +
                                    "ON (l.vaccine_center_id = v.id)" +
                                    "WHERE (v.id = \"" + 1 + "\") ");

                    ResultSet res1 = stmt1.executeQuery();
                    if(res1.next()){
                        if(res1.getInt(1) > res.getInt(1)){
                            results.add(tmp);
                        }
                    }
                }
            }
            con.close();
            return results;
        } catch (Exception ignored) {
            return null;
        }
         */
    }

    // NEEDS TO BE FIXED
    public Long getIdByVaccineAndCenter(Long center_id, String vaccine){
        /**
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT id " +
                            "FROM location_vaccine_amounts  " +
                            "WHERE (vaccine_center_id = \"" + center_id + "\") +" +
                            "AND (vaccine_name = \"" + vaccine +"\") " );
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                Long result = new Long(res.getLong(1));
                con.close();
                return result;
            }
            con.close();
            return null;
        } catch (Exception ignored) {
            return null;
        }
        */

    }



}