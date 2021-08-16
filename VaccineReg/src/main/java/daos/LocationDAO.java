package daos;

import databaseconfigs.DB;
import entities.LocationVaccineAmount;
import entities.User;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
