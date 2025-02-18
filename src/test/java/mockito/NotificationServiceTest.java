package mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import service.mockito.EmailService;
import service.mockito.NotificationService;

import java.util.Arrays;
import java.util.List;

public class NotificationServiceTest {

    EmailService emailService;
    NotificationService notificationService;

    @BeforeEach
    void setup() {
        emailService = Mockito.mock(EmailService.class);
        notificationService = new NotificationService(emailService);
    }

    @Test
    void should_SendWelcomeEmails_when_EmailListIsNotNullOrEmpty() {
        List<String> emails = Arrays.asList("felipe@gmail.com", "aline@gmail.com", "day@gmail.com", "dani@gmail.com");

        notificationService.sendWelcomeEmails(emails);

        ArgumentCaptor<List<String>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(emailService).sendBulkEmails(argumentCaptor.capture());

        List<String> capturedEmails = argumentCaptor.getValue();

        Assertions.assertEquals(emails, capturedEmails);
    }

}
