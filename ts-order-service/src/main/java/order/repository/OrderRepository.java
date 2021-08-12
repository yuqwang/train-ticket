package order.repository;

import order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * @author fdse
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    Order findById(UUID id);

    @Override
    ArrayList<Order> findAll();

    ArrayList<Order> findByAccountId(UUID accountId);

    ArrayList<Order> findByTravelDateAndTrainNumber(Date travelDate,String trainNumber);

    void deleteById(UUID id);
}
