package train.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import train.entity.TrainType;


@Repository
public interface TrainTypeRepository extends JpaRepository<TrainType, String> {
    TrainType findByTrainTypeId(String trainTypeId);
}
