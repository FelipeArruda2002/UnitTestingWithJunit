import org.junit.jupiter.api.*;

@Order(1)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpleMathTestWithOrdering {

    @Test
    @Order(4)
    void testC() {
        System.out.println("Running test C...");
    }

    @Test
    @Order(1)
    void testB() {
        System.out.println("Running test B...");
    }

    @Test
    @Order(3)
    void testD() {
        System.out.println("Running test D...");
    }

    @Test
    @Order(2)
    void testA() {
        System.out.println("Running test A...");
    }


}
