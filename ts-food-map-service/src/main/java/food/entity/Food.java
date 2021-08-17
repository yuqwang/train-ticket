package food.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class Food implements Serializable{

    private String foodName;
    @Column(name = "food_price")
    private double price;
    public Food(){
        //Default Constructor
    }

}
