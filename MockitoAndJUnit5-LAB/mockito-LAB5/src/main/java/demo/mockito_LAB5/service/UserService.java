package demo.mockito_LAB5.service;

import demo.mockito_LAB5.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUserById(Long id) {
        return new User(id, "MockName");
    }

    public User createUser(User user) {
        return new User(99L, user.getName()); // สมมติ return ID ใหม่
    }

    public User updateUser(Long id, User user) {
        return new User(id, user.getName() + "_updated");
    }

    public void deleteUser(Long id) {
        // ไม่มีอะไร เพราะ mock
    }

}
