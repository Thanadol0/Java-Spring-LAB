package demo.mockito_LAB3.service;

import demo.mockito_LAB3.model.User;
import demo.mockito_LAB3.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(@NonNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String name) {
        User user = new User(null, name);
        userRepository.save(user);
    }

}

