package mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import service.mockito.Utility;

public class UtilityTest {

    @Test
    void shouldReturnFormattedMessageWhenGivenNameAndNumber() {
        try (MockedStatic<Utility> utility = Mockito.mockStatic(Utility.class)) {
            utility.when(() -> Utility.formatMessage("Felipe", 1)).thenReturn("Felipe 1");

            String result = Utility.formatMessage("Felipe", 1);

            Assertions.assertEquals("Felipe 1", result);
        }
    }
}
