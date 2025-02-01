import com.felipearruda.math.SimpleMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {

    @Test
    @DisplayName("Sum Test(10 + 10) should return 20")
    public void testSum() {
        SimpleMath simpleMath = new SimpleMath();

        Double actual = simpleMath.sum(10.0, 10.0);

        Assertions.assertEquals(20, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    @DisplayName("Subtraction test (10 - 10) should return 0")
    public void testSubtraction() {
        SimpleMath simpleMath = new SimpleMath();

        Double actual = simpleMath.subtraction(10.0, 10.0);

        Assertions.assertEquals(0, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    @DisplayName("Multiplication test (10 x 5) should return 50")
    public void testMultiplication() {
        SimpleMath simpleMath = new SimpleMath();

        Double actual = simpleMath.multiplication(10.0, 5.0);

        Assertions.assertEquals(50, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    @DisplayName("Division test(10/2) should return 5")
    public void testDivision() {
        SimpleMath simpleMath = new SimpleMath();

        Double actual = simpleMath.division(10.0, 2.0);

        Assertions.assertEquals(5, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    @DisplayName("Mean test((10+2)/2) should return 6")
    public void testMean() {
        SimpleMath simpleMath = new SimpleMath();

        Double actual = simpleMath.mean(10.0, 2.0);

        Assertions.assertEquals(6, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    @DisplayName("Testing the square root of 9 should return 3")
    public void testSquareRoot() {
        SimpleMath simpleMath = new SimpleMath();

        Double actual = simpleMath.squareRoot(9.0);

        Assertions.assertEquals(3, actual, () -> "Failed test did not produce the expected result");
    }

}
