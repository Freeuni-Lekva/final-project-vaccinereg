package daos;

import com.mysql.cj.result.LocalDateTimeValueFactory;
import databaseconfigs.DB;
import entities.*;
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


    public LocationVaccineAmount getLocationVaccineAmount(long id) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE id = ?;");

            stmt.setLong(1, id);
            ResultSet res = stmt.executeQuery();
            if (!res.next()) return null;
            LocationVaccineAmount ans = new LocationVaccineAmount(
                    res.getLong(1),
                    res.getLong(2),
                    res.getString(3),
                    res.getInt(4));
            con.close();
            return ans;
        } catch (Exception ignored) {
            return null;
        }
    }

    public List<Pair<String, Integer> >getVaccineAmountsListForLocation(Long id){
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


    /** This part had to be taken out because an issue with intellij, for some reason it refuses
     * to do more than 7 iterations of checking if the time is available.

     public List<String> getAvailableTimes(String vaccineName, Long id, String date, String centerName){

     try {
     Connection con = ds.getConnection();
     List<String> generatedTimes = Times.generate();

     List<String> allTimes = new ArrayList<>();
     for(String s : generatedTimes){
     allTimes.add(date + " " + s);

     }
     List<String> availableTimes = new ArrayList<>();
     for(String s : allTimes){
     PreparedStatement stmt = con.prepareStatement(
     "SELECT  people_limit_per_vaccine_at_same_time " +
     "FROM vaccine_centers v " +
     "WHERE (v.center_name = \"" + centerName +"\") ;" );

     ResultSet res = stmt.executeQuery();
     int limit;
     if(res.next()){
     limit = res.getInt(1);
     } else limit = 0;

     PreparedStatement stmt1 = con.prepareStatement(
     "SELECT Count(*) " +
     "FROM reservations " +
     "WHERE (location_vaccine_amount_id = \"" + id + "\") " +
     "AND (vaccination_time = \"" + s + "\") ;" );


     ResultSet res1 = stmt1.executeQuery();

     int reserves;
     if(res1.next()){
     reserves = res1.getInt(1);
     } else reserves = 0;

     if(limit > reserves) availableTimes.add(s);
     }
     con.close();
     return availableTimes;
     } catch (Exception ignored) {
     return null;
     }

     }
     */
    public boolean checkAvailibility(String vaccineName, Long id, String date, String time, String centerName){
        try {
            Connection con = ds.getConnection();
            String dateTime = date + " " + time;
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT  people_limit " +
                            "FROM vaccine_centers v " +
                            "WHERE (v.center_name = \"" + centerName +"\") ;" );

            ResultSet res = stmt.executeQuery();
            int limit;
            if(res.next()){
                limit = res.getInt(1);
            } else limit = 0;

            PreparedStatement stmt1 = con.prepareStatement(
                    "SELECT Count(*) " +
                            "FROM reservations " +
                            "WHERE (location_vaccine_amount_id = \"" + id + "\") " +
                            "AND (vaccination_time = \"" + dateTime + "\") ;" );


            ResultSet res1 = stmt1.executeQuery();

            int reserves;
            if(res1.next()){
                reserves = res1.getInt(1);
            } else reserves = 0;

            if(limit > reserves) return true;
            return false;
        } catch (Exception ignored){
            return false;
        }
    }



    public Long getIdByVaccineAndCenter(Long center_id, String vaccine){

        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT id " +
                            "FROM location_vaccine_amounts  " +
                            "WHERE (vaccine_center_id = \"" + center_id + "\")" +
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

    }


    public void setVaccineAmount(long id, int amount) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE " + tableName + " SET amount = ? WHERE id = ?;");

            stmt.setInt(1, amount);
            stmt.setLong(2, id);

            stmt.execute();
            con.close();
        } catch (Exception ignored) {

        }
    }

    public void setVaccineAmount(LocationVaccineAmount v, int amount) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE " + tableName + " SET amount = ? WHERE id = ?;");

            stmt.setInt(1, amount);
            stmt.setLong(2, v.getId());
            stmt.execute();
            con.close();
            v.setAmount(amount);
        } catch (Exception ignored) {

        }
    }


}

