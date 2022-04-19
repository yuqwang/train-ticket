package user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import user.entity.User;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author fdse
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'IDe': ?0 }")
    ArrayList<User> findAllById(String id);

    @Query("{ 'UserNamee': ?0 }")
    User findByUserName(String userName);

//    User findByUserNamee(String userName);
    @Query("{ 'UserIde': ?0 }")
    User findByUserId(UUID userId);

    void deleteByUserId(UUID userId);
}
