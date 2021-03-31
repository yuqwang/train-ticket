package fdse.microservice.repository;

import fdse.microservice.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StationRepository extends JpaRepository<Station, Long> {

    Station findByName(String name);

    Station findById(String id);

    @Override
    List<Station> findAll();
}
