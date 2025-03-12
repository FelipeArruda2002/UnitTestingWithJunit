package service.mockito;

public class OrderProcessor {
    private final EmailService emailService;

    public OrderProcessor(EmailService emailService) {
        this.emailService = emailService;
    }

    public void processOrder(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        emailService.sendOrderConfirmation(email);
    }

    public void cancelOrder(String email) {
        // Nenhuma ação deve ser tomada
    }
}