package daos;

import databaseconfigs.DB;
import entities.VaccineCenter;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaccineCenterDAO {
    private String tableName = "vaccine_centers";
    private BasicDataSource ds;

    public VaccineCenterDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            ds = new BasicDataSource();
            ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
            ds.setUsername(DB.username);
            ds.setPassword(DB.password);
        } catch (Exception ignored) {}
    }

    public VaccineCenterDAO(String tableName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            ds = new BasicDataSource();
            ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
            ds.setUsername(DB.username);
            ds.setPassword(DB.password);
            this.tableName = tableName;
        } catch (Exception ignored) {}
    }

    public void addVaccineCenter(VaccineCenter vaccineCenter) throws SQLException {

        Connection con = ds.getConnection();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO " + tableName + " (region_name, city_name, district_name, people_limit, center_name)" + " VALUES (?, ?, ?, ?, ?);");
        stmt.setString(1, vaccineCenter.getRegionName());
        stmt.setString(2, vaccineCenter.getCityName());
        stmt.setString(3, vaccineCenter.getDistrictName());
        stmt.setInt(4, vaccineCenter.getPeopleLimitPerVaccineAtSameTime());
        stmt.setString(5 , vaccineCenter.getCenterName());
        stmt.execute();
        con.close();

    }

    public VaccineCenter getVaccineCenterById(Long id) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE id = ?;");
            stmt.setLong(1, id);
            ResultSet res = stmt.executeQuery();
            if (!res.next()) {
                return null;
            }
            VaccineCenter vaccineCenter = new VaccineCenter(res.getLong(1), res.getString(2), res.getString(3), res.getString(4) , res.getInt(6) , res.getString(5));
            con.close();
            return vaccineCenter;
        } catch (Exception ignored) {
            return null;
        }
    }

    public List<VaccineCenter> getAllVaccineCenters() {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM " + tableName + " ;");
            ResultSet res = stmt.executeQuery();

            List<VaccineCenter> result = new ArrayList<>();
            while (res.next()) {
                result.add(new VaccineCenter(res.getLong(1) , res.getString(2) , res.getString(3) , res.getString(4) , res.getInt(6) , res.getString(5)));
            }
            con.close();
            return result;
        } catch (Exception ignored) {
            return null;
        }
    }

    public List<VaccineCenter> getVaccineCenterByRegionName(String regionName) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE region_name = ?;");
            stmt.setString(1, regionName);
            ResultSet res = stmt.executeQuery();

            List<VaccineCenter> result = new ArrayList<>();
            while (res.next()) {
                result.add(new VaccineCenter(res.getLong(1) , res.getString(2) , res.getString(3) , res.getString(4) , res.getInt(6) , res.getString(5)));
            }
            con.close();
            return result;
        } catch (Exception ignored) {
            return null;
        }
    }

    public List<VaccineCenter> getVaccineCenterByCityName(String cityName) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE city_name = ?;");
            stmt.setString(1, cityName);
            ResultSet res = stmt.executeQuery();

            List<VaccineCenter> result = new ArrayList<>();
            while (res.next()) {
                result.add(new VaccineCenter(res.getLong(1) , res.getString(2) , res.getString(3) , res.getString(4) , res.getInt(6) , res.getString(5)));
            }
            con.close();
            return result;
        } catch (Exception ignored) {
            return null;
        }
    }

    public List<VaccineCenter> getVaccineCenterByDistrictName(String districtName) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM " + tableName + " WHERE district_name = ?;");
            stmt.setString(1, districtName);
            ResultSet res = stmt.executeQuery();

            List<VaccineCenter> result = new ArrayList<>();
            while (res.next()) {
                result.add(new VaccineCenter(res.getLong(1) , res.getString(2) , res.getString(3) , res.getString(4) , res.getInt(6) , res.getString(5)));
            }
            con.close();
            return result;
        } catch (Exception ignored) {
            return null;
        }
    }

    public void deleteVaccineCenterById(Long id) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM " + tableName + " WHERE id = ?;");
            stmt.setLong(1, id);
            stmt.execute();
            con.close();
        } catch (Exception ignored) {

        }
    }

    public void updateVaccineCenterRegionName(Long id , String regionName) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE " + tableName + " SET region_name = ? WHERE id = ?;");
            stmt.setString(1, regionName);
            stmt.setLong(2, id);
            stmt.execute();
            con.close();
        } catch (Exception ignored) {

        }
    }

    public void updateVaccineCenterCityName(Long id , String cityName) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE " + tableName + " SET city_name = ? WHERE id = ?;");
            stmt.setString(1, cityName);
            stmt.setLong(2, id);
            stmt.execute();
            con.close();
        } catch (Exception ignored) {

        }
    }

    public void updateVaccineCenterDistrictName(Long id , String districtName) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE " + tableName + " SET district_name = ? WHERE id = ?;");
            stmt.setString(1, districtName);
            stmt.setLong(2, id);
            stmt.execute();
            con.close();
        } catch (Exception ignored) {

        }
    }


}
