package food.repository;

import food.entity.FoodStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FoodStoreRepository extends CrudRepository<FoodStore, String> {

    FoodStore findById(UUID id);

    List<FoodStore> findByStationId(String stationId);
    List<FoodStore> findByStationIdIn(List<String> stationIds);


    @Override
    List<FoodStore> findAll();

    void deleteById(UUID id);
}
