package daos;

import entities.User;
import databaseconfigs.DB;
import org.apache.commons.dbcp.BasicDataSource;
import java.sql.*;
import java.time.LocalDate;


public class UsersDAO {
    private String tableName = "users";
    private BasicDataSource ds;


    public UsersDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ignored) {}

        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
        ds.setUsername(DB.username);
        ds.setPassword(DB.password);
    }


    // used for testing
    public UsersDAO(String tableName){
        this.tableName = tableName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ignored) {}

        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
        ds.setUsername(DB.username);
        ds.setPassword(DB.password);
    }


    /**
     * Adds user to the database
     * @param user
     * @return returns true if successful, false if not
     */
    public boolean addUser(User user){
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");

            stmt.setLong(1, user.getPrivateNum());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getGender());
            stmt.setDate(5, java.sql.Date.valueOf(user.getBirthDate()));
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getPassword());
            stmt.setBoolean(8, user.getAdmin());
            if(user.getReservationId() != null){
                stmt.setLong(9, user.getReservationId());
            }else{
                stmt.setNull(9 , Types.NULL);
            }

            stmt.execute();
            con.close();

            return true;
        } catch (Exception ignored) {}

        return false;
    }


    // TODO??: make user admin function
    /*private boolean makeUserAdmin(double privateNum){
        return false;
    }*/


    /**
     * Finds user with the given private number
     * @param privateNum
     * @return tmp.User class if found, null if not
     */
    public User getUserByPrivateNum(long privateNum){
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE id = ?;");

            stmt.setLong(1, privateNum);
            ResultSet res = stmt.executeQuery();

            return getUserResult(res);
        } catch (Exception ignored) {
            return null;
        }
    }


    /**
     * Finds user with the given email
     * @param email
     * @return tmp.User class if found, null if not
     */
    public User getUserByEmail(String email){
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE email = ?;");

            stmt.setString(1, email);
            ResultSet res = stmt.executeQuery();

            return getUserResult(res);
        } catch (Exception ignored) {
            return null;
        }
    }


    private User getUserResult(ResultSet res) throws SQLException {
        // Return null if result was not found
        if( !res.next() ) return null;

        return new User(res.getLong("private_num"),
                res.getString("name"),
                res.getString("last_name"),
                res.getString("gender"),
                res.getDate("birth_date").toLocalDate(),
                res.getString("email"),
                res.getString("password"),
                res.getBoolean("is_admin"),
                res.getLong("vaccination_count")
        );
    }


}
