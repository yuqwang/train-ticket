package other.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import other.entity.Order;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Date;

@Repository
public interface OrderOtherRepository extends JpaRepository<Order, String> {

    Order findOrderById(String id);

    ArrayList<Order> findOrderByAccountId(String accountId);

    ArrayList<Order> findOrderByTravelDateAndTrainNumber(Date travelDate, String trainNumber);

    void deleteOrderById(String id);
}
