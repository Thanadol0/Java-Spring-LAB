package demo.mockito_LAB1.service;

import demo.mockito_LAB1.model.User;
import demo.mockito_LAB1.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserName(Long id) {
        User user = userRepository.findById(id);
        return user != null ? user.getName() : "Unknown";
    }
}
