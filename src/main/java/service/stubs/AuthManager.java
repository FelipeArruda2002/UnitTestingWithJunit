package service.stubs;

public class AuthManager {
    private UserService userService;

    public AuthManager(UserService userService) {
        this.userService = userService;
    }

    public boolean login(String email) {
        return userService.isValidUser(email);
    }
}
