package daos;

import databaseconfigs.DB;
import entities.LocationVaccineAmount;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;

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


}
