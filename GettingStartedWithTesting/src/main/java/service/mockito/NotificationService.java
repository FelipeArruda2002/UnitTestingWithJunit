package service.mockito;

import java.util.List;

public class NotificationService {
    private final EmailService emailService;

    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendWelcomeEmails(List<String> emails) {
        if (emails == null || emails.isEmpty()) {
            throw new IllegalArgumentException("Email list cannot be empty");
        }
        emailService.sendBulkEmails(emails);
    }
}
