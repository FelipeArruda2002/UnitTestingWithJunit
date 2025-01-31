import com.felipearruda.math.SimpleMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SimpleMathTest {

    @Test
    public void testSum() {
        SimpleMath simpleMath = new SimpleMath();

        Double actual = simpleMath.sum(10.0, 10.0);

        Assertions.assertEquals(20, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    public void testSubtraction() {
        SimpleMath simpleMath = new SimpleMath();

        Double actual = simpleMath.subtraction(10.0, 10.0);

        Assertions.assertEquals(0, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    public void testMultiplication() {
        SimpleMath simpleMath = new SimpleMath();

        Double actual = simpleMath.multiplication(10.0, 5.0);

        Assertions.assertEquals(50, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    public void testDivision() {
        SimpleMath simpleMath = new SimpleMath();

        Double actual = simpleMath.division(10.0, 2.0);

        Assertions.assertEquals(5, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    public void testMean() {
        SimpleMath simpleMath = new SimpleMath();

        Double actual = simpleMath.mean(10.0, 2.0);

        Assertions.assertEquals(6, actual, () -> "Failed test did not produce the expected result");
    }

    @Test
    public void testSquareRoot() {
        SimpleMath simpleMath = new SimpleMath();

        Double actual = simpleMath.squareRoot(9.0);

        Assertions.assertEquals(3, actual, () -> "Failed test did not produce the expected result");
    }

}
