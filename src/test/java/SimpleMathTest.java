import com.felipearruda.math.SimpleMath;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SimpleMathTest {

    static SimpleMath simpleMath;

    @BeforeAll
    static void beforeAllMethod() {
        System.out.println("Running before all method....");
    }

    @AfterAll
    static void afterAllMethod() {
        System.out.println("Running after all method....");
    }

    @BeforeEach
    void setup() {
        simpleMath = new SimpleMath();
        System.out.println("Running before each method....");
    }

    @AfterEach
    void afterEachMethod() {
        System.out.println("Running after each method....");
    }

    @Test
    @DisplayName("Sum Test(10 + 10) should return 20")
    public void testSum() {
        Double actual = simpleMath.sum(10.0, 10.0);

        Assertions.assertEquals(20, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    @DisplayName("Subtraction test (10 - 10) should return 0")
    public void testSubtraction() {
        Double actual = simpleMath.subtraction(10.0, 10.0);

        Assertions.assertEquals(0, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    @DisplayName("Multiplication test (10 x 5) should return 50")
    public void testMultiplication() {
        Double actual = simpleMath.multiplication(10.0, 5.0);

        Assertions.assertEquals(50, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    @DisplayName("Division test(10/2) should return 5")
    public void testDivision() {
        Double actual = simpleMath.division(10.0, 2.0);

        Assertions.assertEquals(5, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    @DisplayName("Mean test((10+2)/2) should return 6")
    public void testMean() {
        Double actual = simpleMath.mean(10.0, 2.0);

        Assertions.assertEquals(6, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    @DisplayName("Testing the square root of 9 should return 3")
    public void testSquareRoot() {
        Double actual = simpleMath.squareRoot(9.0);

        Assertions.assertEquals(3, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    @Disabled
    public void testDisabled() {
    }

    @Test
    @DisplayName("Division by zero should return an Arithmetic Exception")
    public void testDivisionByZero() {
        String expectedMessage = "Impossible to division by zero!";

        ArithmeticException arithmeticException = Assertions.assertThrows(ArithmeticException.class,
                () -> simpleMath.division(10.0, 0.0), "Division by zero did not return an arithmetic exception");

        Assertions.assertEquals(expectedMessage, arithmeticException.getMessage(), "Unexpected execution message");
    }

    @Test
    public void testComparingArrays() {
        int [] numbers = {2,4,5,3,1};
        int [] expectedArray = {1, 2, 3, 4, 5};

        Arrays.sort(numbers);


        Assertions.assertArrayEquals(expectedArray, numbers);
    }

    @Test
    //@Timeout(1)
    @Timeout(value = 1, unit = TimeUnit.MILLISECONDS)
    public void performanceTest() {
        long [] numbers = {1};
        for (long i = 0; i <= 100000000l; i++) {
            numbers[0] = i;
        }
    }

}
