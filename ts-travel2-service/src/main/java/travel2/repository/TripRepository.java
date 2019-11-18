package travel2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import travel2.entity.Trip;
import travel2.entity.TripId;

import java.util.ArrayList;

@Repository
public interface TripRepository extends JpaRepository<Trip, TripId> {

    Trip findByTripId(String tripId);

    void deleteByTripId(String tripId);

    ArrayList<Trip> findAll();

    ArrayList<Trip> findByRouteId(String routeId);
}
