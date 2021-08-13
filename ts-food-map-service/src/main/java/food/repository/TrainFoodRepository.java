package food.repository;


import food.entity.TrainFood;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainFoodRepository extends CrudRepository<TrainFood, String> {

    TrainFood findById(String id);

    @Override
    List<TrainFood> findAll();

    List<TrainFood> findByTripId(String tripId);

    void deleteById(String id);
}
