package service.mockito;

import java.util.List;

public interface EmailService {
    void sendOrderConfirmation(String email);

    void sendBulkEmails(List<String> emails);
}