import junit.framework.TestCase;
import entities.User;

import java.time.LocalDate;

public class UserTest extends TestCase {


    public void testBasic(){
        User a = new User(12345, "test", "test2",
                        "m", LocalDate.of(2001, 01, 01), "mail", "pass");

        assertEquals(a.getPrivateNum(), 12345);
        assertEquals(a.getName(), "test");
        assertEquals(a.getLastName(), "test2");
        assertEquals(a.getGender(), "m");
        assertEquals(a.getBirthDate(), LocalDate.of(2001, 01, 01));
        assertEquals(a.getAge(), 20);
        assertEquals(a.getEmail(), "mail");
        assertEquals(a.getPassword(), "pass");
        assertEquals(a.getAdmin(), false);
    }


    public void testNull(){
        User a = new User(12345, null, null,
                        null, null, null, null);

        assertEquals(a.getPrivateNum(), 12345);
        assertEquals(a.getName(), null);
        assertEquals(a.getLastName(), null);
        assertEquals(a.getGender(), null);
        assertEquals(a.getBirthDate(), null);
        assertEquals(a.getAge(), -1);
        assertEquals(a.getEmail(), null);
        assertEquals(a.getPassword(), null);
        assertEquals(a.getAdmin(), false);
    }


    public void testSetAdmin(){
        User a = new User(12345, "test", "test2",
                        "m", LocalDate.of(1951, 11, 24), "mail", "pass");

        assertEquals(a.getPrivateNum(), 12345);
        assertEquals(a.getName(), "test");
        assertEquals(a.getLastName(), "test2");
        assertEquals(a.getGender(), "m");
        assertEquals(a.getBirthDate(), LocalDate.of(1951, 11, 24));
        assertEquals(a.getAge(), 69);
        assertEquals(a.getEmail(), "mail");
        assertEquals(a.getPassword(), "pass");

        assertEquals(a.getAdmin(), false);
        a.setAdmin(true);
        assertEquals(a.getAdmin(), true);
        a.setAdmin(false);
        assertEquals(a.getAdmin(), false);
    }


    public void testEquals(){
        User a = new User(1, "name1", "lastname1",
                "f", LocalDate.of(2001, 01, 01), "test1@freeuni.edu.ge", "test1");
        User b = new User(2, "name1", "lastname1",
                "f", LocalDate.of(2001, 01, 01), "test2@freeuni.edu.ge", "test1");

        assertFalse(a.equals(b));
        assertFalse(b.equals(a));

        assertTrue(a.equals(new User(1, "name1", "lastname1",
                "f", LocalDate.of(2001, 01, 01), "test1@freeuni.edu.ge", "test1")));
        assertTrue(b.equals(new User(2, "name1", "lastname1",
                "f", LocalDate.of(2001, 01, 01), "test2@freeuni.edu.ge", "test1")));
    }
}
