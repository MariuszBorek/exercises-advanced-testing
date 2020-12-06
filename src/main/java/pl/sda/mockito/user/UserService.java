package pl.sda.mockito.user;

public interface UserService {

    User getUserById(final Long id);

    User createUser(final User user);
}
