import junit.framework.TestCase;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsersDAOTest extends TestCase {
    public static final String testTable = "user_test_table";

    protected void setUp() throws Exception {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
        ds.setUsername(DB.username);
        ds.setPassword(DB.password);

        // Drop test table if it exists
        Connection con = ds.getConnection();
        PreparedStatement stmt = con.prepareStatement("DROP TABLE IF EXISTS " + DB.database + "." + testTable + ";");
        stmt.execute();

        // Create a new test table
        stmt = con.prepareStatement("CREATE TABLE" + testTable + " (" +
                "private_num 	BIGINT 		PRIMARY KEY, \n" +
                "name 			CHAR(64) 	NOT NULL, \n" +
                "last_name 		CHAR(64) 	NOT NULL, \n" +
                "gender 			CHAR(1)		NOT NULL, \n" +
                "age 			INT 		NOT NULL, \n" +
                "email 			CHAR(128) 	NOT NULL UNIQUE, \n" +
                "password 		CHAR(64) 	NOT NULL, \n" +
                "is_admin		BOOLEAN		NOT NULL \n" +

                //TODO: vaccination_id
        ");");

        stmt.execute();
        super.setUp();
    }


    public void testBasic(){

    }
}
