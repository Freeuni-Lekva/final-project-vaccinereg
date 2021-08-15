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
                    "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?, ?, ?);");

            stmt.setLong(1, user.getPrivateNum());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getGender());
            stmt.setDate(5, java.sql.Date.valueOf(user.getBirthDate()));
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getPassword());
            stmt.setBoolean(8, user.getAdmin());

            stmt.execute();
            con.close();

            return true;
        } catch (Exception ignored) {}

        return false;
    }


    /**
     * Finds user with the given private number
     * @param privateNum
     * @return tmp.User class if found, null if not
     */
    public User getUser(long privateNum){
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE private_num = ?;");

            stmt.setLong(1, privateNum);
            ResultSet res = stmt.executeQuery();

            User ans = getUserResult(res);
            con.close();
            return ans;

        } catch (Exception ignored) {
            return null;
        }
    }


    /**
     * Finds user with the given email
     * @param email
     * @return tmp.User class if found, null if not
     */
    public User getUser(String email){
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE email = ?;");

            stmt.setString(1, email);
            ResultSet res = stmt.executeQuery();

            User ans = getUserResult(res);
            con.close();
            return ans;

        } catch (Exception ignored) {
            return null;
        }
    }


    /**
     * Builds a user object from the given result set
     * @param res
     * @return
     */
    private User getUserResult(ResultSet res){
        try {
            // Return null if result was not found
            if (!res.next()) return null;

            return new User(res.getLong("private_num"),
                    res.getString("name"),
                    res.getString("last_name"),
                    res.getString("gender"),
                    res.getDate("birth_date").toLocalDate(),
                    res.getString("email"),
                    res.getString("password"),
                    res.getBoolean("is_admin")
            );
        }
        catch(Exception e){
            return null;
        }
    }


    /**
     * Changes user's is_admin value in the database.
     * Also changes the parameter u's isAdmin value.
     * @param u
     * @param isAdmin
     */
    public void setUserIsAdmin(User u, boolean isAdmin){
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE " + tableName + " SET is_admin = ? WHERE private_num = ?;");

            stmt.setBoolean(1, isAdmin);
            stmt.setLong(2, u.getPrivateNum());

            stmt.execute();
            con.close();

            u.setAdmin(isAdmin);

        } catch (Exception ignored) {

        }
    }


    /**
     * Changes user's is_admin value in the database
     * @param privateNum
     * @param isAdmin
     */
    public void setUserIsAdmin(Long privateNum, boolean isAdmin){
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE " + tableName + " SET is_admin = ? WHERE private_num = ?;");

            stmt.setBoolean(1, isAdmin);
            stmt.setLong(2, privateNum);

            stmt.execute();
            con.close();

        } catch (Exception ignored) {

        }
    }


    /**
     * Changes user's is_admin value in the database
     * @param email
     * @param isAdmin
     */
    public void setUserIsAdmin(String email, boolean isAdmin){
        try {
            Connection con = ds.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "UPDATE " + tableName + " SET is_admin = ? WHERE email = ?;");

            stmt.setBoolean(1, isAdmin);
            stmt.setString(2, email);

            stmt.execute();
            con.close();

        } catch (Exception ignored) {

        }
    }
}
