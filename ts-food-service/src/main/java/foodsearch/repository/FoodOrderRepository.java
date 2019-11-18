package foodsearch.repository;

import foodsearch.entity.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, String> {

    FoodOrder findFoodOrderById(String id);

    FoodOrder findFoodOrderByOrderId(String orderId);

    void deleteFoodOrderByOrderId(String id);

}
