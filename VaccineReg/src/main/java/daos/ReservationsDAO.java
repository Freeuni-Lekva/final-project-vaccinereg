package daos;

import databaseconfigs.DB;
import entities.Reservation;
import entities.User;
import org.apache.commons.dbcp.BasicDataSource;
import utils.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class ReservationsDAO {
    private String reservationsTableName = "reservations";
    private String usersTableName = "users";
    private String amountsTableName = "location_vaccine_amounts";
    private String centersTableName = "vaccine_centers";
    private BasicDataSource ds;

    public ReservationsDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ignored) {}

        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
        ds.setUsername(DB.username);
        ds.setPassword(DB.password);
    }

    public ReservationsDAO(String reservationsTableName, String usersTableName, String amountsTableName, String centersTableName){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ignored) {}

        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
        ds.setUsername(DB.username);
        ds.setPassword(DB.password);

        this.reservationsTableName = reservationsTableName;
        this.usersTableName = usersTableName;
        this.amountsTableName = amountsTableName;
        this.centersTableName = centersTableName;
    }

    /**
     * Adds reservation to the database
     * @param reservation
     * @return returns true if entry was added
     */
    public boolean addReservation(Reservation reservation){
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO " + reservationsTableName + " VALUES (?, ?, ?, ?, ?);");
            stmt.setLong(1, reservation.getId());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(reservation.getReservationTime()));
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(reservation.getVaccinationTime()));
            stmt.setLong(4, reservation.getLocation_vaccine_amount_id());
            if (reservation.getUser_id() != null){
                stmt.setLong(5, reservation.getUser_id());
            } else{
                stmt.setNull(5 , Types.NULL);
            }

            stmt.execute();
            con.close();

            return true;
        } catch (Exception ignored) {
        }

        return false;
    }

    /**
     * Returns the count of every reservation during the last specified seconds
     * @param seconds
     * @return Returns the pair of counts for reservations(first) and vaccinations(second).
     */
    public Pair<Integer, Integer> getAllReservationsCountByTime(long seconds) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement resStmt = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " " +
                            "WHERE reservation_time >= DATE_SUB(NOW(), interval " + seconds + " second );");
            ResultSet resRes = resStmt.executeQuery();
            resRes.next();
            PreparedStatement vaxStmr = con.prepareStatement(
                    "SELECT Count(*) " +
                            "FROM " + reservationsTableName + " " +
                            "WHERE vaccination_time >= DATE_SUB(NOW(), interval " + seconds + " second );");
            ResultSet vaxRes = vaxStmr.executeQuery();
            vaxRes.next();
            Pair<Integer, Integer> p = new Pair<>(resRes.getInt(1), vaxRes.getInt(1));
            con.close();
            return p;
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Returns the count of reservations by gender during the last specified seconds.
     * @param gender
     * @param seconds
     * @return Returns the pair of counts for reservations(first) and vaccinations(second).
     */
    public Pair<Integer, Integer> getCountByGenderByTime(String gender, long seconds) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement resStmt = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + usersTableName + " u on r.user_id = u.private_num " +
                            "WHERE (gender = \"" + gender + "\") " +
                            "and (reservation_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet resRes = resStmt.executeQuery();
            resRes.next();
            PreparedStatement vaxStmr = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + usersTableName + " u on r.user_id = u.private_num " +
                            "WHERE (gender = \"" + gender + "\") " +
                            "and (vaccination_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet vaxRes = vaxStmr.executeQuery();
            vaxRes.next();
            Pair<Integer, Integer> p = new Pair<>(resRes.getInt(1), vaxRes.getInt(1));
            con.close();
            return p;
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Returns the count of reservations by region during the last specified seconds.
     * @param region
     * @param seconds
     * @return Returns the pair of counts for reservations(first) and vaccinations(second).
     */
    public Pair<Integer, Integer> getCountByRegionByTime(String region, long seconds) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement resStmt = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + amountsTableName + " lva on lva.id = r.location_vaccine_amount_id " +
                            "JOIN " + centersTableName + " vc on vc.id = lva.vaccine_center_id " +
                            "WHERE (region_name = \"" + region + "\") " +
                            "and (reservation_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet resRes = resStmt.executeQuery();
            resRes.next();
            PreparedStatement vaxStmr = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + amountsTableName + " lva on lva.id = r.location_vaccine_amount_id " +
                            "JOIN " + centersTableName + " vc on vc.id = lva.vaccine_center_id " +
                            "WHERE (region_name = \"" + region + "\") " +
                            "and (vaccination_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet vaxRes = vaxStmr.executeQuery();
            vaxRes.next();
            Pair<Integer, Integer> p = new Pair<>(resRes.getInt(1), vaxRes.getInt(1));
            con.close();
            return p;
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Returns the count of reservations by age range during the last specified seconds.
     * @param min
     * @param max
     * @param seconds
     * @return Returns the pair of counts for reservations(first) and vaccinations(second).
     */
    public Pair<Integer, Integer> getCountByAgeByTime(int min, int max, long seconds) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement resStmt = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + usersTableName + " u on r.user_id = u.private_num " +
                            "WHERE (FLOOR(DATEDIFF(NOW(), birth_date) / 365.25) between " + min + " AND " + max + ") " +
                            "and (reservation_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet resRes = resStmt.executeQuery();
            resRes.next();
            PreparedStatement vaxStmr = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + usersTableName + " u on r.user_id = u.private_num " +
                            "WHERE (FLOOR(DATEDIFF(NOW(), birth_date) / 365.25) between " + min + " AND " + max + ") " +
                            "and (vaccination_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet vaxRes = vaxStmr.executeQuery();
            vaxRes.next();
            Pair<Integer, Integer> p = new Pair<>(resRes.getInt(1), vaxRes.getInt(1));
            con.close();
            return p;
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Returns the count of reservations by gender and region during the last specified seconds.
     * @param gender
     * @param region
     * @param seconds
     * @return Returns the pair of counts for reservations(first) and vaccinations(second).
     */
    public Pair<Integer, Integer> getCountByGenderAndRegionByTime(String gender, String region, long seconds) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement resStmt = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + usersTableName + " u on r.user_id = u.private_num " +
                            "JOIN " + amountsTableName + " lva on lva.id = r.location_vaccine_amount_id " +
                            "JOIN " + centersTableName + " vc on vc.id = lva.vaccine_center_id " +
                            "WHERE (gender = \"" + gender + "\") " +
                            "and (region_name = \"" + region + "\") " +
                            "and (reservation_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet resRes = resStmt.executeQuery();
            resRes.next();
            PreparedStatement vaxStmr = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + usersTableName + " u on r.user_id = u.private_num " +
                            "JOIN " + amountsTableName + " lva on lva.id = r.location_vaccine_amount_id " +
                            "JOIN " + centersTableName + " vc on vc.id = lva.vaccine_center_id " +
                            "WHERE (gender = \"" + gender + "\") " +
                            "and (region_name = \"" + region + "\") " +
                            "and (vaccination_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet vaxRes = vaxStmr.executeQuery();
            vaxRes.next();
            Pair<Integer, Integer> p = new Pair<>(resRes.getInt(1), vaxRes.getInt(1));
            con.close();
            return p;
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Returns the count of reservations by gender and age range during the last specified seconds.
     * @param gender
     * @param min
     * @param max
     * @param seconds
     * @return Returns the pair of counts for reservations(first) and vaccinations(second).
     */
    public Pair<Integer, Integer> getCountByGenderAndAgeByTime(String gender, int min, int max, long seconds) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement resStmt = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + usersTableName + " u on r.user_id = u.private_num " +
                            "WHERE (gender = \"" + gender + "\") " +
                            "and (FLOOR(DATEDIFF(NOW(), birth_date) / 365.25) between " + min + " AND " + max + ") " +
                            "and (reservation_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet resRes = resStmt.executeQuery();
            resRes.next();
            PreparedStatement vaxStmr = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + usersTableName + " u on r.user_id = u.private_num " +
                            "WHERE (gender = \"" + gender + "\") " +
                            "and (FLOOR(DATEDIFF(NOW(), birth_date) / 365.25) between " + min + " AND " + max + ") " +
                            "and (vaccination_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet vaxRes = vaxStmr.executeQuery();
            vaxRes.next();
            Pair<Integer, Integer> p = new Pair<>(resRes.getInt(1), vaxRes.getInt(1));
            con.close();
            return p;
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Returns the count of reservations by region and age range during the last specified seconds.
     * @param region
     * @param min
     * @param max
     * @param seconds
     * @return Returns the pair of counts for reservations(first) and vaccinations(second).
     */
    public Pair<Integer, Integer> getCountByRegionAndAgeByTime(String region, int min, int max, long seconds) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement resStmt = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + usersTableName + " u on r.user_id = u.private_num " +
                            "JOIN " + amountsTableName + " lva on lva.id = r.location_vaccine_amount_id " +
                            "JOIN " + centersTableName + " vc on vc.id = lva.vaccine_center_id " +
                            "WHERE (region_name = \"" + region + "\") " +
                            "and (FLOOR(DATEDIFF(NOW(), birth_date) / 365.25) between " + min + " AND " + max + ") " +
                            "and (reservation_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet resRes = resStmt.executeQuery();
            resRes.next();
            PreparedStatement vaxStmr = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + usersTableName + " u on r.user_id = u.private_num " +
                            "JOIN " + amountsTableName + " lva on lva.id = r.location_vaccine_amount_id " +
                            "JOIN " + centersTableName + " vc on vc.id = lva.vaccine_center_id " +
                            "WHERE (region_name = \"" + region + "\") " +
                            "and (FLOOR(DATEDIFF(NOW(), birth_date) / 365.25) between " + min + " AND " + max + ") " +
                            "and (vaccination_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet vaxRes = vaxStmr.executeQuery();
            vaxRes.next();
            Pair<Integer, Integer> p = new Pair<>(resRes.getInt(1), vaxRes.getInt(1));
            con.close();
            return p;
        } catch (Exception ignored) {
            return null;
        }
    }

    /**
     * Returns the count of reservations by gender, region and age range during the last specified seconds.
     * @param gender
     * @param region
     * @param min
     * @param max
     * @param seconds
     * @return Returns the pair of counts for reservations(first) and vaccinations(second).
     */
    public Pair<Integer, Integer> getCountByGenderAndRegionAndAgeByTime(String gender, String region, int min, int max, long seconds) {
        try {
            Connection con = ds.getConnection();
            PreparedStatement resStmt = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + usersTableName + " u on r.user_id = u.private_num " +
                            "JOIN " + amountsTableName + " lva on lva.id = r.location_vaccine_amount_id " +
                            "JOIN " + centersTableName + " vc on vc.id = lva.vaccine_center_id " +
                            "WHERE (gender = \"" + gender + "\") " +
                            "and (region_name = \"" + region + "\") " +
                            "and (FLOOR(DATEDIFF(NOW(), birth_date) / 365.25) between " + min + " AND " + max + ") " +
                            "and (reservation_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet resRes = resStmt.executeQuery();
            resRes.next();
            PreparedStatement vaxStmr = con.prepareStatement(
                    "SELECT COUNT(*) " +
                            "FROM " + reservationsTableName + " r " +
                            "JOIN " + usersTableName + " u on r.user_id = u.private_num " +
                            "JOIN " + amountsTableName + " lva on lva.id = r.location_vaccine_amount_id " +
                            "JOIN " + centersTableName + " vc on vc.id = lva.vaccine_center_id " +
                            "WHERE (gender = \"" + gender + "\") " +
                            "and (region_name = \"" + region + "\") " +
                            "and (FLOOR(DATEDIFF(NOW(), birth_date) / 365.25) between " + min + " AND " + max + ") " +
                            "and (vaccination_time >= DATE_SUB(NOW(), interval " + seconds + " second ));");
            ResultSet vaxRes = vaxStmr.executeQuery();
            vaxRes.next();
            Pair<Integer, Integer> p = new Pair<>(resRes.getInt(1), vaxRes.getInt(1));
            con.close();
            return p;
        } catch (Exception ignored) {
            return null;
        }
    }


    /**
     * Gets the upcoming vaccination details for the given user.
     * Returns null if not found.
     * @param u
     * @return
     */
    public Reservation getNextVaccination(User u){

        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * " +
                            "FROM " + reservationsTableName + " " +
                            "WHERE user_id = ? " +
                            "AND NOW() < vaccination_time " +
                            "ORDER BY vaccination_time DESC " +
                            "LIMIT 1;");
            stmt.setLong(1, u.getPrivateNum());
            ResultSet res = stmt.executeQuery();

            if(!res.next()) {
                con.close();
                return null;
            }

            Reservation ans = new Reservation(
                    res.getLong(1),
                    res.getTimestamp(2).toLocalDateTime(),
                    res.getTimestamp(3).toLocalDateTime(),
                    res.getLong(4),
                    res.getLong(5));

            con.close();
            return ans;

        } catch (Exception ignored) {
            return null;
        }
    }
}
