package foodsearch.repository;

import foodsearch.entity.FoodOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodOrderRepository extends CrudRepository<FoodOrder, String> {

    FoodOrder findById(String id);

    FoodOrder findByOrderId(String orderId);

    @Override
    List<FoodOrder> findAll();

    void deleteById(String id);

    void deleteFoodOrderByOrderId(String id);

}
