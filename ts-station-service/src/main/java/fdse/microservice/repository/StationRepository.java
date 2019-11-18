package fdse.microservice.repository;

import fdse.microservice.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, String> {

    Station findByName(String name);

    Station findStationById(String id);

    List<Station> findAll();
}