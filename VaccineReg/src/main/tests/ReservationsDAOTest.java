import daos.UsersDAO;
import daos.VaccineCenterDAO;
import databaseconfigs.DB;
import entities.User;
import entities.VaccineCenter;
import junit.framework.TestCase;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReservationsDAOTest extends TestCase {

    public static final String testTableReservations = "reservation_test_table";
    public static final String testTableAmounts = "amount_test_table";
    public static final String testTableCenters = "centers_test_table";
    public static final String testTableUsers = "users_test_table";


    protected void setUp() throws Exception {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
        ds.setUsername(DB.username);
        ds.setPassword(DB.password);

        Connection con = ds.getConnection();
        dropTables(con);
        createTables(con);
        con.close();
        addUsers();
        addCenters();
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
        ds.setUsername(DB.username);
        ds.setPassword(DB.password);

        Connection con = ds.getConnection();
        dropTables(con);
        con.close();
        super.setUp();
    }

    public void createTables(Connection con) throws SQLException {
        createTestTableCenters(con);
        createTestTableUsers(con);
        createTestTableAmounts(con);
        createTestTableReservations(con);
    }

    public void createTestTableCenters(Connection con) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("CREATE TABLE " + testTableCenters + " (" +
                "id 	        BIGINT 		PRIMARY KEY, \n" +
                "region_name 	CHAR(64) 	NOT NULL, \n" +
                "city_name 		CHAR(64) 	NOT NULL, \n" +
                "district_name 	CHAR(64)	NOT NULL, \n" +
                "people_limit 	INT 		\n" +
                ");");
        stmt.execute();
        // !!!! Don't close the connection
    }

    public void createTestTableUsers(Connection con) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("CREATE TABLE " + testTableUsers + " (" +
                "private_num 	BIGINT 		PRIMARY KEY, \n" +
                "name 			CHAR(64) 	NOT NULL, \n" +
                "last_name 		CHAR(64) 	NOT NULL, \n" +
                "gender 		CHAR(1)		NOT NULL, \n" +
                "birth_date 	DATE 		NOT NULL, \n" +
                "email 			CHAR(128) 	NOT NULL UNIQUE, \n" +
                "password 		CHAR(64) 	NOT NULL, \n" +
                "is_admin		BOOLEAN		NOT NULL \n" +
                ");");
        stmt.execute();
        // !!!! Don't close the connection
    }

    public void createTestTableAmounts(Connection con) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("CREATE TABLE " + testTableAmounts + " (" +
                "id 	            BIGINT 		PRIMARY KEY, \n" +
                "vaccine_center_id 	BIGINT 	    NOT NULL, \n" +
                "vaccine_name 		CHAR(64) 	NOT NULL, \n" +
                "amount 	        INT, 		\n" +
                "FOREIGN KEY (vaccine_center_id) REFERENCES " + testTableCenters + " (id) \n" +
                ");");
        stmt.execute();
        // !!!! Don't close the connection
    }

    public void createTestTableReservations(Connection con) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("CREATE TABLE " + testTableReservations + " (" +
                "id 	                    BIGINT 		PRIMARY KEY, \n" +
                "reservation_time 	        DATETIME 	NOT NULL, \n" +
                "vaccination_time 	        DATETIME 	NOT NULL, \n" +
                "location_vaccine_amount_id BIGINT, 		\n" +
                "user_id                    BIGINT,         \n" +
                "FOREIGN KEY (location_vaccine_amount_id) REFERENCES " + testTableAmounts + " (id), \n" +
                "FOREIGN KEY (user_id) REFERENCES " + testTableUsers + " (private_num) \n" +
                ");");

        stmt.execute();
        // !!!! Don't close the connection
    }

    public void addCenters() {
        VaccineCenterDAO dao = new VaccineCenterDAO(testTableCenters);
        dao.addVaccineCenter(new VaccineCenter(1L, "Tbilisi", "Tbilisi", "Saburtalo", 400));
        dao.addVaccineCenter(new VaccineCenter(2L, "Batumi", "Adjara", "idk", 350));
        dao.addVaccineCenter(new VaccineCenter(3L, "Kutaisi", "Imereti", "idk2", 300));
    }

    public void addUsers() {
        UsersDAO dao = new UsersDAO(testTableUsers);
        dao.addUser(new User(11123145512L, "name1", "lastname1", "m",
                LocalDate.of(2001, 6, 23), "test1@freeuni.edu.ge", "test1", true));
        dao.addUser(new User(22123145512L, "name2", "lastname2", "f",
                LocalDate.of(1980, 8, 15), "test2@freeuni.edu.ge", "test2", false));
        dao.addUser(new User(29129149512L, "name3", "lastname3", "m",
                LocalDate.of(1995, 11, 1), "test3@freeuni.edu.ge", "test3", false));
        dao.addUser(new User(36591826412L, "name4", "lastname4", "m",
                LocalDate.of(1997, 3, 21), "test4@freeuni.edu.ge", "test4", true));
        dao.addUser(new User(23345567712L, "name5", "lastname5", "f",
                LocalDate.of(1986, 10, 7), "test5@freeuni.edu.ge", "test5", false));
        dao.addUser(new User(98876654412L, "name6", "lastname6", "m",
                LocalDate.of(1990, 1, 27), "test6@freeuni.edu.ge", "test6", false));
    }

    public void addAmounts() {

    }

    public void dropTables(Connection con) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("DROP TABLE IF EXISTS " + DB.database + "." + testTableReservations + ";");
        stmt.execute();
        stmt = con.prepareStatement("DROP TABLE IF EXISTS " + DB.database + "." + testTableAmounts + ";");
        stmt.execute();
        stmt = con.prepareStatement("DROP TABLE IF EXISTS " + DB.database + "." + testTableCenters + ";");
        stmt.execute();
        stmt = con.prepareStatement("DROP TABLE IF EXISTS " + DB.database + "." + testTableUsers + ";");
        stmt.execute();
        // !!! Don't close the connection
    }


    public void test() {
        assertEquals(1, 2/2);
    }

}
