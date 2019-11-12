package auth.repository;

import auth.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    void deleteByUserId(UUID userId);

    /**
     * Mock for fault injection: get user by username
     * @param username username
     * @return the target user if it exists, if not, return null.
     */
    @Query("{ 'username': ?0 }")
    Optional<User> getUser(String username);
}
