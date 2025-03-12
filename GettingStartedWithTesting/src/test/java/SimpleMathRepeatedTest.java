import com.felipearruda.math.SimpleMath;
import org.junit.jupiter.api.*;

@Order(2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleMathRepeatedTest {

    static SimpleMath simpleMath;

    StringBuilder sb = new StringBuilder("");

    @BeforeEach
    public void setup() {
        simpleMath = new SimpleMath();
    }

    @RepeatedTest(value = 3, name = "{displayName}. Repetition "
    + "{currentRepetition} of {totalRepetitions}!")
    @DisplayName("Division by zero should return an Arithmetic Exception")
    public void testDivisionByZero() {
        String expectedMessage = "Impossible to division by zero!";

        sb.append("1");

        System.out.println("Actual value = " + sb.toString());

        ArithmeticException arithmeticException = Assertions.assertThrows(ArithmeticException.class,
                () -> simpleMath.division(10.0, 0.0), "Division by zero did not return an arithmetic exception");

        Assertions.assertEquals(expectedMessage, arithmeticException.getMessage(), "Unexpected execution message");
    }
}
