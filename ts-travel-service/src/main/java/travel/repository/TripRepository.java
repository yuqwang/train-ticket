package travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import travel.entity.Trip;

import java.util.ArrayList;

/**
 * @author fdse
 */
@Component
public interface TripRepository extends JpaRepository<Trip,Integer> {

    ArrayList<Trip> findByTripId(String tripId);

    void deleteByTripId(String tripId);

//    @Override
//    ArrayList<Trip> findAll();

    ArrayList<Trip> findByRouteId(String routeId);
}
