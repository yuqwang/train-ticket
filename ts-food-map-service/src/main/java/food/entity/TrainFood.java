package food.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainFood {

    @Id
    private UUID id;

    @NotNull
    private String tripId;

    @ElementCollection(targetClass = Food.class)
    private List<Food> foodList;

    public TrainFood(){
        //Default Constructor
        this.tripId = "";
    }

}
