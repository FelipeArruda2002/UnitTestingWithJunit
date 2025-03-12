package mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import service.mockito.Order;
import service.mockito.OrderService;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderServiceTest {

    private  OrderService orderService;
    private final UUID defaultUuid = UUID.fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac");
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2025, 3, 6, 11, 47);

    @BeforeEach
    void createOrderService() {
        orderService = new OrderService();
    }

    @Test
    void shouldIncludeRandomOrderIdWhenParentOrderExists() {
        try (MockedStatic<UUID> uuid = Mockito.mockStatic(UUID.class)) {
            uuid.when(UUID::randomUUID).thenReturn(defaultUuid);

            Order result = orderService.createOrder("MacBook Pro", 2L, null);

            Assertions.assertEquals(defaultUuid.toString(), result.getId());
        }
    }

    @Test
    void shouldIncludeCurrentTimeWhenCreatingANewOrder() {
        try (MockedStatic<LocalDateTime> localDateTimeMocked = Mockito.mockStatic(LocalDateTime.class)){
            localDateTimeMocked.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            Order result = orderService.createOrder("Iphone 13", 1L, "7");

            Assertions.assertEquals(defaultLocalDateTime, result.getCreationDate());
        }
    }

}
