package user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import user.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

//    @Query("select u from User u where u.userName = :userName")
    User findByUserName(String userName);

//    @Query("select u from User u where u.userId = :userId")
    User findByUserId(String userId);


//    void deleteByUserId(UUID userId);
}
