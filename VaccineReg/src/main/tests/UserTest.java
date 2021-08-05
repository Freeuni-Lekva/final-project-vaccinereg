import junit.framework.TestCase;

public class UserTest extends TestCase {


    public void testBasic(){
        User a = new User(12345, "test", "test2",
                        "m", 1, "mail", "pass");

        assertEquals(a.getPrivateNum(), 12345);
        assertEquals(a.getName(), "test");
        assertEquals(a.getLastName(), "test2");
        assertEquals(a.getGender(), "m");
        assertEquals(a.getAge(), 1);
        assertEquals(a.getEmail(), "mail");
        assertEquals(a.getPassword(), "pass");
        assertEquals(a.isAdmin(), false);
        assertEquals(0, a.getVaccinationCount());
        // TODO: vaccination id
    }


    public void testNull(){
        User a = new User(12345, null, null,
                        null, 1, null, null);

        assertEquals(a.getPrivateNum(), 12345);
        assertEquals(a.getName(), null);
        assertEquals(a.getLastName(), null);
        assertEquals(a.getGender(), null);
        assertEquals(a.getAge(), 1);
        assertEquals(a.getEmail(), null);
        assertEquals(a.getPassword(), null);
        assertEquals(a.isAdmin(), false);
    }


    public void testSetAdmin(){
        User a = new User(12345, "test", "test2",
                        "m", 1, "mail", "pass");

        assertEquals(a.getPrivateNum(), 12345);
        assertEquals(a.getName(), "test");
        assertEquals(a.getLastName(), "test2");
        assertEquals(a.getGender(), "m");
        assertEquals(a.getAge(), 1);
        assertEquals(a.getEmail(), "mail");
        assertEquals(a.getPassword(), "pass");

        assertEquals(a.isAdmin(), false);
        a.setAdmin(true);
        assertEquals(a.isAdmin(), true);
        a.setAdmin(false);
        assertEquals(a.isAdmin(), false);
    }


    public void testSetVaccinationCount(){
        User a = new User(12345, "test", "test2",
                "m", 1, "mail", "pass");

        assertEquals(0, a.getVaccinationCount());
        a.setVaccinationCount(20);
        assertEquals(20, a.getVaccinationCount());
        a.setVaccinationCount(0);
        assertEquals(0, a.getVaccinationCount());
    }


    public void testEquals(){
        User a = new User(1, "name1", "lastname1",
                "f", 45, "test1@freeuni.edu.ge", "test1");
        User b = new User(2, "name1", "lastname1",
                "f", 45, "test2@freeuni.edu.ge", "test1");

        assertFalse(a.equals(b));
        assertFalse(b.equals(a));

        assertTrue(a.equals(new User(1, "name1", "lastname1",
                "f", 45, "test1@freeuni.edu.ge", "test1")));
        assertTrue(b.equals(new User(2, "name1", "lastname1",
                "f", 45, "test2@freeuni.edu.ge", "test1")));
    }
}
