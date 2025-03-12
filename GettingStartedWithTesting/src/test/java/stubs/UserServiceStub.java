package stubs;

import service.stubs.UserService;

public class UserServiceStub implements UserService {
    @Override
    public boolean isValidUser(String email) {
        // Apenas usuários específicos são considerados válidos
        return email.equals("felipe@example.com");
    }
}
