package travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import travel.entity.Trip;
import travel.entity.TripId;

import java.util.ArrayList;

@Repository
public interface TripRepository extends JpaRepository<Trip,TripId> {

    Trip findByTripId(String tripId);

    void deleteByTripId(String tripId);

    ArrayList<Trip> findAll();

    ArrayList<Trip> findByRouteId(String routeId);
}
