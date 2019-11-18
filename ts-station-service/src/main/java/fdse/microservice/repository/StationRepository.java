package fdse.microservice.repository;

import fdse.microservice.entity.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StationRepository extends CrudRepository<Station, String> {

    Station findByName(String name);

    Optional<Station> findById(String id);

    List<Station> findAll();
}