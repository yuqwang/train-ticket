package foodsearch.repository;

import foodsearch.entity.FoodOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FoodOrderRepository extends CrudRepository<FoodOrder, String> {

    FoodOrder findById(UUID id);

    FoodOrder findByOrderId(UUID orderId);

    @Override
    List<FoodOrder> findAll();

    void deleteById(UUID id);

    void deleteFoodOrderByOrderId(UUID id);

}
