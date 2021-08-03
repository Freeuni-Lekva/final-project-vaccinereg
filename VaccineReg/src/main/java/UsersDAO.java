import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsersDAO {
    private String tableName = "users";
    private BasicDataSource ds;


    public UsersDAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
            Class.forName("com.mysql.jdbc.Driver");
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
                    "INSERT INTO TABLE " + tableName + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");

            stmt.setLong(1, user.getPrivateNum());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getGender());
            stmt.setInt(5, user.getAge());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getPassword());
            stmt.setBoolean(8, user.isAdmin());
            // TODO: registration_id

            stmt.execute();
            con.close();

            return true;
        } catch (Exception ignored) {}

        return false;
    }


    // TODO: make user admin function
    private boolean makeUserAdmin(double privateNum){
        return false;
    }
}
