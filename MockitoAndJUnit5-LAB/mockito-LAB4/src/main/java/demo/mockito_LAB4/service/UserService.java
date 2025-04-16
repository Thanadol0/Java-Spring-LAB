package demo.mockito_LAB4.service;

import demo.mockito_LAB4.model.User;
import demo.mockito_LAB4.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String name) {
        String generatedName = generateUsername(name);
        User user = new User(null, generatedName);
        saveUser(user); // <-- อยาก spy ตรงนี้
    }

    public String generateUsername(String name) {
        return name.toLowerCase() + "_dev";
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
