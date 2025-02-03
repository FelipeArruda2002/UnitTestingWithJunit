import com.felipearruda.math.SimpleMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SimpleMathParameterizedTest {

    static SimpleMath simpleMath;

    @BeforeAll
    static void setup() {
        simpleMath = new SimpleMath();
    }

    @ParameterizedTest
    @MethodSource()
    public void parameterized_multiplication_test(Double firstNumber, Double secondNumber, Double expected) {
        double actual = simpleMath.multiplication(firstNumber, secondNumber);

        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> parameterized_multiplication_test() {
        return Stream.of(
                Arguments.of(10d, 20d, 200d),
                Arguments.of(5d, 5d, 25d),
                Arguments.of(7d, 7d, 49d),
                Arguments.of(10d, 3d, 30d),
                Arguments.of(25d, 5d, 125d)
        );
    }
}
