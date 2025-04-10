package demo.mockito_LAB2.service;

import demo.mockito_LAB2.exception.UserNotFoundException;
import demo.mockito_LAB2.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import demo.mockito_LAB2.model.User;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(@NonNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserName(@NonNull Long id) {
        return Optional.ofNullable(userRepository.findById(id))
                .map(User::getName)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }


}
