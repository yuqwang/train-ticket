package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import user.entity.User;

import java.util.UUID;

/**
 * @author fdse
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUserName(String userName);

    User findByUserId(UUID userId);

    void deleteByUserId(UUID userId);
}
