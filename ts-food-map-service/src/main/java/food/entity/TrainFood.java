package food.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainFood {

    @Id
    private String id;

    @NotNull
    private String tripId;

    @ElementCollection(targetClass = Food.class, fetch = FetchType.EAGER)
    private List<Food> foodList;

    public TrainFood(){
        //Default Constructor
        this.tripId = "";
    }

}
