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
@Table(schema = "ts-food-map-mysql")
public class TrainFood {

    @Id
    @Column(name = "train_food_id")
    private String id;

    @NotNull
    private String tripId;

    @ElementCollection(targetClass = Food.class, fetch = FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "train_food_id"))
    @Column(name = "train_food_list")
    private List<Food> foodList;

    public TrainFood(){
        //Default Constructor
        this.tripId = "";
    }

}
