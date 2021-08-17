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
public class FoodStore {

    @Id
    @Column(name = "store_id")
    private String id;

    @NotNull
    private String stationId;

    private String storeName;

    @Column(name = "store_telephone")
    private String telephone;

    private String businessTime;

    private double deliveryFee;

    @ElementCollection(targetClass = Food.class, fetch = FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "store_id"))
    @Column(name = "store_food_list")
    private List<Food> foodList;

    public FoodStore(){
        //Default Constructor
        this.stationId = "";
    }

}
