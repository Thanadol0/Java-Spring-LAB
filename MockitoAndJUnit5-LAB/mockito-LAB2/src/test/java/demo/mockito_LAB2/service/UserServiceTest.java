package demo.mockito_LAB2.service;

import demo.mockito_LAB2.exception.UserNotFoundException;
import demo.mockito_LAB2.model.User;
import demo.mockito_LAB2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserName_WhenUserExists() {
        User user = new User(1L, "Alice");
        when(userRepository.findById(1L)).thenReturn(user);

        String result = userService.getUserName(1L);
        assertEquals("Alice", result);
    }

    @Test
    void testGetUserName_WhenUserNotFound_ThrowsException() {

        when(userRepository.findById(2L)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userService.getUserName(2L));

    }
}
