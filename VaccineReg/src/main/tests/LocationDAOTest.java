import daos.LocationDAO;
import databaseconfigs.DB;
import entities.LocationVaccineAmount;
import junit.framework.TestCase;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LocationDAOTest extends TestCase {
    public static final String testTableAmounts = "amount_test_table";

    @Override
    protected void setUp() throws Exception {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
        ds.setUsername(DB.username);
        ds.setPassword(DB.password);

        Connection con = ds.getConnection();
        PreparedStatement stmt = con.prepareStatement("DROP TABLE IF EXISTS " + DB.database + "." + testTableAmounts + ";");
        stmt.execute();

        stmt = con.prepareStatement("CREATE TABLE " + testTableAmounts + " (" +
                "id 	            BIGINT 		PRIMARY KEY, \n" +
                "vaccine_center_id 	BIGINT 	    NOT NULL, \n" +
                "vaccine_name 		CHAR(64) 	NOT NULL, \n" +
                "amount 	        INT 		\n" +
                ");");
        stmt.execute();

        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://" + DB.server + "/" + DB.database);
        ds.setUsername(DB.username);
        ds.setPassword(DB.password);

        Connection con = ds.getConnection();
        PreparedStatement stmt = con.prepareStatement("DROP TABLE IF EXISTS " + DB.database + "." + testTableAmounts + ";");
        stmt.execute();
        con.close();
        super.setUp();
    }

    public void testAdd() {
        LocationDAO dao = new LocationDAO(testTableAmounts);
        boolean success = dao.addLocationVaccineAmount(new LocationVaccineAmount(1L, 1L, "ABC", 300));
        assertTrue(success);
        success = dao.addLocationVaccineAmount(new LocationVaccineAmount(2L, 1L, "XYZ", 100));
        assertTrue(success);
        success = dao.addLocationVaccineAmount(new LocationVaccineAmount(1L, 2L, "XYZ", 150));
        assertFalse(success);
        success = dao.addLocationVaccineAmount(new LocationVaccineAmount(2L, 3L, "ABC", 250));
        assertFalse(success);
    }

    public void testGetByID() {
        LocationDAO dao = new LocationDAO(testTableAmounts);
        LocationVaccineAmount a = new LocationVaccineAmount(1L, 1L, "ABC", 300);
        LocationVaccineAmount b = new LocationVaccineAmount(2L, 1L, "XYZ", 100);
        LocationVaccineAmount c = new LocationVaccineAmount(1L, 1L, "ABC", 300);
        dao.addLocationVaccineAmount(a);
        dao.addLocationVaccineAmount(b);

        assertEquals(a, dao.getLocationVaccineAmount(1L));
        assertFalse(a.equals(dao.getLocationVaccineAmount(2L)));
        assertEquals(b, dao.getLocationVaccineAmount(2L));
        assertFalse(b.equals(dao.getLocationVaccineAmount(1L)));
        assertNull(dao.getLocationVaccineAmount(3L));
    }

    public void testSetAmount() {
        LocationDAO dao = new LocationDAO(testTableAmounts);
        LocationVaccineAmount a = new LocationVaccineAmount(1L, 1L, "ABC", 300);
        LocationVaccineAmount b = new LocationVaccineAmount(2L, 1L, "XYZ", 100);
        dao.addLocationVaccineAmount(a);
        dao.addLocationVaccineAmount(b);

        dao.setVaccineAmount(1L, 299);
        assertEquals(299, dao.getLocationVaccineAmount(1L).getAmount().intValue());
        dao.setVaccineAmount(a.getId(), 298);
        assertEquals(298, dao.getLocationVaccineAmount(a.getId()).getAmount().intValue());

        dao.setVaccineAmount(b, 99);
        assertEquals(99, dao.getLocationVaccineAmount(b.getId()).getAmount().intValue());
        dao.setVaccineAmount(b, 98);
        assertEquals(98, dao.getLocationVaccineAmount(b.getId()).getAmount().intValue());

    }

    public void testConstructor() {
        try {
            LocationDAO dao = new LocationDAO();
        } catch (Exception ignored) {
            fail();
        }
    }
}
