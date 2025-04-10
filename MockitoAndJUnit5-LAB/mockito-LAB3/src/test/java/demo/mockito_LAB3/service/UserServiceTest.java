package demo.mockito_LAB3.service;

import demo.mockito_LAB3.model.User;
import demo.mockito_LAB3.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    void testCreateUser_CaptureSavedUser() {

        // Arrange
        String name = "Bob";

        // Act
        userService.createUser(name);

        // Assert
        Mockito.verify(userRepository).save(userCaptor.capture());
        User captured = userCaptor.getValue();

        assertEquals("Bob", captured.getName());
        assertNull(captured.getId());  // เพราะเราให้ id = null ตอนสร้าง
    }
}


