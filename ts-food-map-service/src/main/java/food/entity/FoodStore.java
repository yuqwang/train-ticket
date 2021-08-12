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
public class FoodStore {

    @Id
    private UUID id;

    @NotNull
    private String stationId;

    private String storeName;

    private String telephone;

    private String businessTime;

    private double deliveryFee;

    @ElementCollection(targetClass = Food.class)
    private List<Food> foodList;

    public FoodStore(){
        //Default Constructor
        this.stationId = "";
    }

}
