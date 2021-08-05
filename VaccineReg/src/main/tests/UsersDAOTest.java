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
        stmt = con.prepareStatement("CREATE TABLE " + testTable + " (" +
                "private_num 	BIGINT 		PRIMARY KEY, \n" +
                "name 			CHAR(64) 	NOT NULL, \n" +
                "last_name 		CHAR(64) 	NOT NULL, \n" +
                "gender 			CHAR(1)		NOT NULL, \n" +
                "age 			INT 		NOT NULL, \n" +
                "email 			CHAR(128) 	NOT NULL UNIQUE, \n" +
                "password 		CHAR(64) 	NOT NULL, \n" +
                "is_admin		BOOLEAN		NOT NULL, \n" +
                "vaccination_count INT       NOT NULL \n" +

                //TODO: vaccination_id
        ");");

        stmt.execute();
        super.setUp();
    }


    public void testConstructor(){
        try{
            UsersDAO dao = new UsersDAO();
        }catch (Exception ignored){
            fail();
        }
    }


    // tests add method, the method should not add users with same id/email
    public void testAdd(){
        UsersDAO dao = new UsersDAO(testTable);

        boolean success = dao.addUser(new User(1, "name1", "lastname1",
                "f", 45, "test1@freeuni.edu.ge", "test1"));
        assertTrue(success);

        // user with same id
        success = dao.addUser(new User(1, "name2", "lastname2",
                "f", 45, "test2@freeuni.edu.ge", "test2"));
        assertFalse(success);

        // user with same email
        success = dao.addUser(new User(12, "name2", "lastname2",
                "m", 45, "test1@freeuni.edu.ge", "test2"));
        assertFalse(success);

        // new user with new id and email
        success = dao.addUser(new User(121, "name2", "lastname2",
                "m", 45, "newmail@freeuni.edu.ge", "test2"));
        assertTrue(success);
    }


    // tests getUserByPrivateNum method, the method should return null for unregistered users
    public void testFindByPrivateNum(){
        UsersDAO dao = new UsersDAO(testTable);

        User a = new User(1, "name1", "lastname1",
                "f", 45, "test1@freeuni.edu.ge", "test1");
        User b = new User(2, "name1", "lastname1",
                "f", 45, "test2@freeuni.edu.ge", "test1");
        dao.addUser(a);
        dao.addUser(b);


        assertTrue(a.equals(dao.getUserByPrivateNum(1)));
        assertTrue(b.equals(dao.getUserByPrivateNum(2)));

        assertFalse(a.equals(dao.getUserByPrivateNum(2)));
        assertFalse(b.equals(dao.getUserByPrivateNum(1)));

        assertNull(dao.getUserByPrivateNum(123124124));
    }


    // tests getUserByEmail method, the method should return null for unregistered users
    public void testFindByEmail(){
        UsersDAO dao = new UsersDAO(testTable);

        User a = new User(1, "name1", "lastname1",
                "f", 45, "test1@freeuni.edu.ge", "test1");
        User b = new User(2, "name1", "lastname1",
                "f", 45, "test2@freeuni.edu.ge", "test1");
        dao.addUser(a);
        dao.addUser(b);


        assertTrue(a.equals(dao.getUserByEmail("test1@freeuni.edu.ge")));
        assertTrue(b.equals(dao.getUserByEmail("test2@freeuni.edu.ge")));

        assertFalse(a.equals(dao.getUserByEmail("test2@freeuni.edu.ge")));
        assertFalse(b.equals(dao.getUserByEmail("test1@freeuni.edu.ge")));

        assertNull(dao.getUserByEmail("garbage"));
    }

}