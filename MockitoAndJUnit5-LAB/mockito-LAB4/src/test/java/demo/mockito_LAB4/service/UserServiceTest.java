package demo.mockito_LAB4.service;

import demo.mockito_LAB4.model.User;
import demo.mockito_LAB4.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    void testRegister_SpySaveUser() {
        // Arrange
        doNothing().when(userService).saveUser(any());

        // Act
        userService.register("Thanadol");

        // Assert ว่า generateUsername ยังทำงานจริง
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userService).saveUser(captor.capture());

        User saved = captor.getValue();
        assertEquals("thanadol_dev", saved.getName());
    }
}
