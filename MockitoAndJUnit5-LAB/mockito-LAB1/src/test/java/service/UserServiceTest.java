package service;

import demo.mockito_LAB1.model.User;
import demo.mockito_LAB1.repository.UserRepository;
import demo.mockito_LAB1.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserName_WhenUserExists() {

        // Arrange
        User mockUser = new User(1L, "Alice");
        when(userRepository.findById(1L)).thenReturn(mockUser);

        // Act
        String result = userService.getUserName(1L);

        // Assert
        assertEquals("Alice", result);
    }

    @Test
    void testGetUserName_WhenUserNotFound() {
        when(userRepository.findById(2L)).thenReturn(null);

        String result = userService.getUserName(2L);

        assertEquals("Unknown", result);
    }

}


