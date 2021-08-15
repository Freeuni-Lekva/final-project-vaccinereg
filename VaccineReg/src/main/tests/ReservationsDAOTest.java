import daos.LocationDAO;
import daos.ReservationsDAO;
import daos.UsersDAO;
import daos.VaccineCenterDAO;
import databaseconfigs.DB;
import entities.LocationVaccineAmount;
import entities.Reservation;
import entities.User;
import entities.VaccineCenter;
import junit.framework.TestCase;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        addAmounts();
        addReservations();
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
                LocalDate.now().minusYears(20), "test1@freeuni.edu.ge", "test1", true));
        dao.addUser(new User(22123145512L, "name2", "lastname2", "f",
                LocalDate.now().minusYears(41), "test2@freeuni.edu.ge", "test2", false));
        dao.addUser(new User(29129149512L, "name3", "lastname3", "m",
                LocalDate.now().minusYears(26), "test3@freeuni.edu.ge", "test3", false));
        dao.addUser(new User(36591826412L, "name4", "lastname4", "m",
                LocalDate.now().minusYears(24), "test4@freeuni.edu.ge", "test4", true));
        dao.addUser(new User(23345567712L, "name5", "lastname5", "f",
                LocalDate.now().minusYears(35), "test5@freeuni.edu.ge", "test5", false));
        dao.addUser(new User(98876654412L, "name6", "lastname6", "m",
                LocalDate.now().minusYears(31), "test6@freeuni.edu.ge", "test6", false));
    }

    public void addAmounts() {
        LocationDAO dao = new LocationDAO(testTableAmounts);
        dao.addLocationVaccineAmount(new LocationVaccineAmount(1L, 1L, "ABC", 300));
        dao.addLocationVaccineAmount(new LocationVaccineAmount(2L, 1L, "XYZ", 100));
        dao.addLocationVaccineAmount(new LocationVaccineAmount(3L, 2L, "ABC", 200));
        dao.addLocationVaccineAmount(new LocationVaccineAmount(4L, 2L, "XYZ", 150));
        dao.addLocationVaccineAmount(new LocationVaccineAmount(5L, 3L, "ABC", 250));
        dao.addLocationVaccineAmount(new LocationVaccineAmount(6L, 3L, "XYZ", 50));
    }

    public void addReservations() {
        ReservationsDAO dao = new ReservationsDAO(testTableReservations, testTableUsers, testTableAmounts, testTableCenters);
        dao.addReservation(new Reservation(1L, LocalDateTime.now().minusDays(3),
                LocalDateTime.now().minusDays(3), 1L, 11123145512L));
        dao.addReservation(new Reservation(2L, LocalDateTime.now().minusDays(3),
                LocalDateTime.now().minusDays(3), 2L, 22123145512L));
        dao.addReservation(new Reservation(3L, LocalDateTime.now().minusDays(3),
                LocalDateTime.now().minusDays(3), 3L, 29129149512L));
        dao.addReservation(new Reservation(4L, LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(2), 4L, 36591826412L));
        dao.addReservation(new Reservation(5L, LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(2), 5L, 23345567712L));
        dao.addReservation(new Reservation(6L, LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(2), 6L, 98876654412L));
        dao.addReservation(new Reservation(7L, LocalDateTime.now().minusHours(6),
                LocalDateTime.now().minusHours(6), 1L, 11123145512L));
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


    public void testAddReservation() {
        ReservationsDAO dao = new ReservationsDAO(testTableReservations, testTableUsers, testTableAmounts, testTableCenters);

        boolean success = dao.addReservation(new Reservation(17L, LocalDateTime.now(), LocalDateTime.now(), 5L, 11123145512L));
        assertTrue(success);

        success = dao.addReservation(new Reservation(19L, LocalDateTime.now(), LocalDateTime.now(), 5L, 11123145512L));
        assertTrue(success);

        success = dao.addReservation(new Reservation(19L, LocalDateTime.now(), LocalDateTime.now(), 2L, 11123145512L));
        assertFalse(success);

        success = dao.addReservation(new Reservation(19L, LocalDateTime.now(), LocalDateTime.now(), 2L, 1L));
        assertFalse(success);
    }

    public void testGetAllReservationsCountByTime() {
        ReservationsDAO dao = new ReservationsDAO(testTableReservations, testTableUsers, testTableAmounts, testTableCenters);
        assertEquals(0, dao.getAllReservationsCountByTime(3600).getFirst().intValue());
        assertEquals(0, dao.getAllReservationsCountByTime(3600).getSecond().intValue());
        assertEquals(1, dao.getAllReservationsCountByTime(24 * 3600).getFirst().intValue());
        assertEquals(1, dao.getAllReservationsCountByTime(24 * 3600).getSecond().intValue());
        assertEquals(7, dao.getAllReservationsCountByTime(7 * 24 * 3600).getFirst().intValue());
        assertEquals(7, dao.getAllReservationsCountByTime(7 * 24 * 3600).getSecond().intValue());
        assertEquals(7, dao.getAllReservationsCountByTime(30 * 24 * 3600).getFirst().intValue());
        assertEquals(7, dao.getAllReservationsCountByTime(30 * 24 * 3600).getSecond().intValue());
    }

    public void testGetCountByGenderByTime() {
        ReservationsDAO dao = new ReservationsDAO(testTableReservations, testTableUsers, testTableAmounts, testTableCenters);
        assertEquals(0, dao.getCountByGenderByTime("m", 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByGenderByTime("f", 3600).getSecond().intValue());
        assertEquals(1, dao.getCountByGenderByTime("m", 24 * 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByGenderByTime("f", 24 * 3600).getSecond().intValue());
        assertEquals(5, dao.getCountByGenderByTime("m", 7 * 24 * 3600).getFirst().intValue());
        assertEquals(2, dao.getCountByGenderByTime("f", 7 * 24 * 3600).getSecond().intValue());
        assertEquals(5, dao.getCountByGenderByTime("m", 30 * 24 * 3600).getFirst().intValue());
        assertEquals(2, dao.getCountByGenderByTime("f", 30 * 24 * 3600).getSecond().intValue());
    }

    public void testGetCountByRegionByTime() {
        ReservationsDAO dao = new ReservationsDAO(testTableReservations, testTableUsers, testTableAmounts, testTableCenters);
        assertEquals(0, dao.getCountByRegionByTime("Tbilisi", 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByRegionByTime("Batumi", 3600).getSecond().intValue());
        assertEquals(1, dao.getCountByRegionByTime("Tbilisi", 24 * 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByRegionByTime("Kutaisi", 24 * 3600).getSecond().intValue());
        assertEquals(2, dao.getCountByRegionByTime("Batumi", 7 * 24 * 3600).getFirst().intValue());
        assertEquals(3, dao.getCountByRegionByTime("Tbilisi", 7 * 24 * 3600).getSecond().intValue());
        assertEquals(2, dao.getCountByRegionByTime("Kutaisi", 30 * 24 * 3600).getFirst().intValue());
        assertEquals(3, dao.getCountByRegionByTime("Tbilisi", 30 * 24 * 3600).getSecond().intValue());
    }

    public void testGetCountByAgeByTime() {
        ReservationsDAO dao = new ReservationsDAO(testTableReservations, testTableUsers, testTableAmounts, testTableCenters);
        assertEquals(0, dao.getCountByAgeByTime(15, 22, 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByAgeByTime(25, 30, 3600).getSecond().intValue());
        assertEquals(1, dao.getCountByAgeByTime(19, 25, 24 * 3600).getFirst().intValue());
        assertEquals(1, dao.getCountByAgeByTime(10, 100, 24 * 3600).getSecond().intValue());
        assertEquals(6, dao.getCountByAgeByTime(2, 37, 7 * 24 * 3600).getFirst().intValue());
        assertEquals(1, dao.getCountByAgeByTime(38, 45, 7 * 24 * 3600).getSecond().intValue());
        assertEquals(2, dao.getCountByAgeByTime(18, 23, 30 * 24 * 3600).getFirst().intValue());
        assertEquals(7, dao.getCountByAgeByTime(1, 100, 30 * 24 * 3600).getSecond().intValue());
    }

    public void testGetCountByGenderAndRegionByTime() {
        ReservationsDAO dao = new ReservationsDAO(testTableReservations, testTableUsers, testTableAmounts, testTableCenters);
        assertEquals(0, dao.getCountByGenderAndRegionByTime("m", "Tbilisi", 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByGenderAndRegionByTime("f", "Batumi", 3600).getSecond().intValue());
        assertEquals(1, dao.getCountByGenderAndRegionByTime("m", "Tbilisi", 24 * 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByGenderAndRegionByTime("f", "Kutaisi", 24 * 3600).getSecond().intValue());
        assertEquals(2, dao.getCountByGenderAndRegionByTime("m", "Batumi", 7 * 24 * 3600).getFirst().intValue());
        assertEquals(1, dao.getCountByGenderAndRegionByTime("f", "Tbilisi", 7 * 24 * 3600).getSecond().intValue());
        assertEquals(1, dao.getCountByGenderAndRegionByTime("m", "Kutaisi", 30 * 24 * 3600).getFirst().intValue());
        assertEquals(1, dao.getCountByGenderAndRegionByTime("f", "Tbilisi", 30 * 24 * 3600).getSecond().intValue());
    }

    public void testGetCountByGenderAndAgeByTime() {
        ReservationsDAO dao = new ReservationsDAO(testTableReservations, testTableUsers, testTableAmounts, testTableCenters);
        assertEquals(0, dao.getCountByGenderAndAgeByTime("m", 15, 22, 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByGenderAndAgeByTime("f", 25, 30, 3600).getSecond().intValue());
        assertEquals(1, dao.getCountByGenderAndAgeByTime("m", 19, 25, 24 * 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByGenderAndAgeByTime("f", 10, 100, 24 * 3600).getSecond().intValue());
        assertEquals(5, dao.getCountByGenderAndAgeByTime("m", 2, 37, 7 * 24 * 3600).getFirst().intValue());
        assertEquals(1, dao.getCountByGenderAndAgeByTime("f", 38, 45, 7 * 24 * 3600).getSecond().intValue());
        assertEquals(2, dao.getCountByGenderAndAgeByTime("m", 18, 23, 30 * 24 * 3600).getFirst().intValue());
        assertEquals(2, dao.getCountByGenderAndAgeByTime("f", 1, 100, 30 * 24 * 3600).getSecond().intValue());
    }

    public void testGetCountByRegionAndAgeByTime() {
        ReservationsDAO dao = new ReservationsDAO(testTableReservations, testTableUsers, testTableAmounts, testTableCenters);
        assertEquals(0, dao.getCountByRegionAndAgeByTime("Tbilisi", 15, 22, 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByRegionAndAgeByTime("Kutaisi", 25, 30, 3600).getSecond().intValue());
        assertEquals(1, dao.getCountByRegionAndAgeByTime("Tbilisi", 19, 25, 24 * 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByRegionAndAgeByTime("Batumi", 10, 100, 24 * 3600).getSecond().intValue());
        assertEquals(2, dao.getCountByRegionAndAgeByTime("Kutaisi", 2, 37, 7 * 24 * 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByRegionAndAgeByTime("Batumi", 38, 45, 7 * 24 * 3600).getSecond().intValue());
        assertEquals(2, dao.getCountByRegionAndAgeByTime("Tbilisi", 18, 23, 30 * 24 * 3600).getFirst().intValue());
        assertEquals(3, dao.getCountByRegionAndAgeByTime("Tbilisi", 1, 100, 30 * 24 * 3600).getSecond().intValue());
    }

    public void testGetCountByGenderAndRegionAndAgeByTime() {
        ReservationsDAO dao = new ReservationsDAO(testTableReservations, testTableUsers, testTableAmounts, testTableCenters);
        assertEquals(0, dao.getCountByGenderAndRegionAndAgeByTime("m", "Tbilisi", 15, 22, 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByGenderAndRegionAndAgeByTime("f", "Kutaisi", 25, 30, 3600).getSecond().intValue());
        assertEquals(1, dao.getCountByGenderAndRegionAndAgeByTime("m", "Tbilisi", 19, 25, 24 * 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByGenderAndRegionAndAgeByTime("f", "Batumi", 10, 100, 24 * 3600).getSecond().intValue());
        assertEquals(1, dao.getCountByGenderAndRegionAndAgeByTime("m", "Kutaisi", 2, 37, 7 * 24 * 3600).getFirst().intValue());
        assertEquals(0, dao.getCountByGenderAndRegionAndAgeByTime("f", "Batumi", 38, 45, 7 * 24 * 3600).getSecond().intValue());
        assertEquals(2, dao.getCountByGenderAndRegionAndAgeByTime("m", "Tbilisi", 18, 23, 30 * 24 * 3600).getFirst().intValue());
        assertEquals(1, dao.getCountByGenderAndRegionAndAgeByTime("f", "Tbilisi", 1, 100, 30 * 24 * 3600).getSecond().intValue());
    }

    public void testGetNextVaccination() {
        ReservationsDAO reservationsDAO = new ReservationsDAO(testTableReservations, testTableUsers, testTableAmounts, testTableCenters);
        UsersDAO usersDAO = new UsersDAO(testTableUsers);
        reservationsDAO.addReservation(new Reservation(8L, LocalDateTime.now().plusDays(2),
                LocalDateTime.now().minusHours(2), 1L, 11123145512L));
        assertEquals(8L, reservationsDAO.getNextVaccination(usersDAO.getUser(11123145512L)).getId().longValue());
        assertNull(reservationsDAO.getNextVaccination(usersDAO.getUser(22123145512L)));
    }

    public void testConstructor() {
        try {
            ReservationsDAO dao = new ReservationsDAO();
        } catch (Exception ignored) {
            fail();
        }
    }

}
