package order.repository;

import order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    Order findOrderByOrderId(String id);

    ArrayList<Order> findOrderByAccountId(String accountId);

    ArrayList<Order> findOrderByTravelDateAndTrainNumber(Date travelDate, String trainNumber);

    void deleteOrderByOrderId(String id);
}
