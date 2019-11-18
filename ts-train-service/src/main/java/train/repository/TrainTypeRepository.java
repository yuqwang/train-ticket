package train.repository;

import org.springframework.data.repository.CrudRepository;
import train.entity.TrainType;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainTypeRepository extends CrudRepository<TrainType,String>{

    Optional<TrainType> findById(String id);
    List<TrainType> findAll();
    //void save(TrainType trainType);
    void deleteById(String id);
    //void update();
}
