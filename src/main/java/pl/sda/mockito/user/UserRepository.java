package pl.sda.mockito.user;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(final Long id);

    User addUser(final User user);
}
