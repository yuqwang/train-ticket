package consignprice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author fdse
 */
@Data
@AllArgsConstructor
@Entity
public class ConsignPrice {
    @Id
    private String id;
    @Column(name = "consign_index")
    private int index;
    private double initialWeight;
    private double initialPrice;
    private double withinPrice;
    private double beyondPrice;

    public ConsignPrice(){
        //Default Constructor
    }

}
