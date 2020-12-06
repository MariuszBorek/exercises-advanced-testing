package pl.sda.mockito.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final long IDENTIFIER = 1L;
    private static final User USER = new User(IDENTIFIER, "Jan", "Kowalski");

    @Mock
    private UserValidator userValidator;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userServiceImpl; // musi być implementacja interface!

    // metoda robi to samo co adnotacja @InjectMocks powyżej ale trzeba sie posłużyć interface private UserService userService;
//    @BeforeEach //
//    public void before() {
//        userService = new UserServiceImpl(userRepository, userValidator);
//    }

    @Test
    public void shouldGetUserById() {
        // given
        when(userRepository.findById(IDENTIFIER)).thenReturn(Optional.of(USER));

        // when
        User actualUser = userServiceImpl.getUserById(IDENTIFIER);

        // then
        Assertions.assertThat(actualUser).isEqualTo(USER);
        verify(userRepository).findById(IDENTIFIER);
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(userValidator);
    }

    @Test
    public void shouldThrowNoSuchExceptionWhenUserDoesNotExists() {
        // given
        when(userRepository.findById(IDENTIFIER)).thenReturn(Optional.empty());

        // when
        Assertions.assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> userServiceImpl.getUserById(IDENTIFIER));

        // then
        verify(userRepository).findById(IDENTIFIER);
        verifyNoMoreInteractions(userRepository);
        verifyNoInteractions(userValidator);
    }

    @Test
    public void shouldCreateUser() {
        // given
        when(userValidator.isUserValid(USER)).thenReturn(true);
        when(userRepository.addUser(USER)).thenReturn(USER);

        // when
        User actualUser = userServiceImpl.createUser(USER);

        // then
        Assertions.assertThat(actualUser).isEqualTo(USER);
        verify(userValidator).isUserValid(USER);
        verifyNoMoreInteractions(userValidator);
        verify(userRepository).addUser(USER);
        verifyNoMoreInteractions(userRepository);
    }

}