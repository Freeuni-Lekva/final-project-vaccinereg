import junit.framework.TestCase;
import utils.Pair;

public class PairTest extends TestCase {

    public void testBasic() {
        Pair<String, String> p1 = new Pair<>("a", "b");
        Pair<String, String> p2 = new Pair<>();
        Pair<?, ?> p3;

        assertFalse(p1.equals(p2));
        assertEquals("a", p1.getFirst());
        assertEquals("b", p1.getSecond());
        p2.setFirst(p1.getFirst());
        p2.setSecond(p1.getSecond());
        assertEquals(p1, p2);
    }

    public void testPairs() {
        Pair<Pair<String, String>, String> p1 = new Pair<>(new Pair<>("a", "c"), "c");
        Pair<Pair<String, String>, String> p2 = new Pair<>();
        Pair<Pair<String, String>, String> p3 = new Pair<>(new Pair<>(), "c");

        p2.setFirst(p1.getFirst());
        p2.setSecond(p1.getSecond());
        assertEquals(p1, p2);
        assertFalse(p1.equals(p3));
        assertEquals(p1.getFirst().getSecond(), p1.getSecond());

    }

}
