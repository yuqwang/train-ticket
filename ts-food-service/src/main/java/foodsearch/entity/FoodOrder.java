package foodsearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(schema = "ts-food-mysql")
public class FoodOrder {

    @Id
    @Column(name = "food_order_id")
    private String id;

    private String orderId;

    //1:train food;2:food store
    private int foodType;

    private String stationName;

    private String storeName;

    private String foodName;

    @Column(name = "food_order_price")
    private double price;

    public FoodOrder(){
        //Default Constructor
    }

}
