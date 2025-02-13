package mockito;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.mockito.EmailService;
import service.mockito.OrderProcessor;

public class OrderProcessorTest {

    EmailService emailService;
    OrderProcessor orderProcessor;

    @BeforeEach
    void setup() {
        emailService = Mockito.mock(EmailService.class);
        orderProcessor = new OrderProcessor(emailService);
    }

    @Test
    void should_SendEmail_When_OrderIsProcessed() {
        orderProcessor.processOrder("felipe@gmail.com");

        Mockito.verify(emailService, Mockito.times(1)).sendOrderConfirmation("felipe@gmail.com");
    }

    @Test
    void should_NotSendEmail_When_OrderIsCancelled() {
        orderProcessor.cancelOrder("felipe@gmail.com");

        Mockito.verify(emailService, Mockito.never()).sendOrderConfirmation("felipe@gmail.com");
    }

    @Test
    void should_ThrowException_When_EmailServiceFails() {
        IllegalArgumentException actual = Assertions.assertThrows(IllegalArgumentException.class, () -> orderProcessor.processOrder(""));

        Assertions.assertEquals("Email cannot be empty", actual.getMessage());

        Mockito.verify(emailService, Mockito.never()).sendOrderConfirmation(Mockito.anyString());
    }
}
