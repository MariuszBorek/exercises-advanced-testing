package pl.sda.mockito.user;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserServiceImpl(final UserRepository userRepository, final UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public User getUserById(final Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User createUser(final User user) {
        if (userValidator.isUserValid(user)) {
            return userRepository.addUser(user);
        }
        throw new IllegalArgumentException("User is invalid");
    }
}
