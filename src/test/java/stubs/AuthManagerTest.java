package stubs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.stubs.AuthManager;
import service.stubs.UserService;

public class AuthManagerTest {

    UserService userService;
    AuthManager authManager;

    @BeforeEach
    void setup() {
        userService = new UserServiceStub();
        authManager = new AuthManager(userService);
    }

    @Test
    void login_when_EmailIsValid_ShouldReturnTrue() {
        boolean login = authManager.login("felipe@example.com");

        Assertions.assertTrue(login, "Expected login to succeed for a valid email");
    }

    @Test
    void login_when_EmailIsInvalid_ShouldReturnFalse() {
        boolean login = authManager.login("dani@example.com");

        Assertions.assertFalse(login, "Expected login to fail for an invalid email");
    }
}
