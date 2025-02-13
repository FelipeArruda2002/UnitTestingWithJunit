package service.mockito;

public interface PaymentService {
    boolean charge(String userId, double amount);
}
