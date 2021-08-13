package food.repository;

import food.entity.FoodStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodStoreRepository extends CrudRepository<FoodStore, String> {

    FoodStore findById(String id);

    List<FoodStore> findByStationId(String stationId);
    List<FoodStore> findByStationIdIn(List<String> stationIds);


    @Override
    List<FoodStore> findAll();

    void deleteById(String id);
}
