package hamcrest;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MatchersTest {


    @Test
    void testHamcrestMatchers() {
        List<String> family = Arrays.asList("Aline","Amado","Dani", "Dayane", "Lia", "Oseias");

        MatcherAssert.assertThat(family, Matchers.hasSize(6));
        MatcherAssert.assertThat(family, Matchers.hasItems("Aline","Amado","Dani", "Dayane", "Lia", "Oseias"));
        MatcherAssert.assertThat(family, Matchers.everyItem(Matchers.containsStringIgnoringCase("a")));

        MatcherAssert.assertThat("", Matchers.is(Matchers.emptyString()));
        MatcherAssert.assertThat(null, Matchers.is(Matchers.emptyOrNullString()));

        Integer [] myArray = {7, 15, 25};
        MatcherAssert.assertThat(myArray, Matchers.arrayWithSize(3));
        MatcherAssert.assertThat(myArray, Matchers.arrayContaining(7,15, 25));
        MatcherAssert.assertThat(myArray, Matchers.arrayContainingInAnyOrder(25, 15, 7));

    }
}
