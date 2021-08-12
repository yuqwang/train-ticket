package security.repository;

//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import security.entity.SecurityConfig;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author fdse
 */
@Repository
public interface SecurityRepository extends CrudRepository<SecurityConfig,String> {

//    @Query("{ 'name': ?0 }")
    SecurityConfig findByName(String name);

//    @Query("{ 'id': ?0 }")
    SecurityConfig findById(String id);

    @Override
    ArrayList<SecurityConfig> findAll();

    @Transactional
    void deleteById(String id);
}
