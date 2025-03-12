package mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.mockito.PaymentProcessor;
import service.mockito.PaymentService;

public class PaymentProcessorTest {

    PaymentProcessor paymentProcessor;
    PaymentService paymentService;

    @BeforeEach
    void setup() {
        paymentService = Mockito.mock(PaymentService.class);
        paymentProcessor = new PaymentProcessor(paymentService);
    }

    @Test
    void chargeSuccessfully_When_TheAmountIsGreaterThanZero() {
        Mockito.when(paymentService.charge("Felipe", 100))
                .thenReturn(true);

        boolean actual = paymentProcessor.processPayment("Felipe", 100);

        Assertions.assertTrue(actual, "Expected payment to succeed");
    }

    @Test
    void chargeUnsuccessfully_When_TheAmountIsLessThanOrEqualToZero() {
        IllegalArgumentException actual = Assertions.assertThrows(IllegalArgumentException.class,
                () ->  paymentProcessor.processPayment("Felipe", -1));

        Assertions.assertEquals("Amount must be greater than zero", actual.getMessage());
    }
}
