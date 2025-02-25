package mockito;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import service.mockito.EmailService;
import service.mockito.OrderProcessor;

@ExtendWith(MockitoExtension.class)
public class OrderProcessorTest {

    @Mock
    EmailService emailService;
    @InjectMocks
    OrderProcessor orderProcessor;

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
