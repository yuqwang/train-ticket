package consignprice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author fdse
 */
@Data
@AllArgsConstructor
@Entity
@Table(schema = "ts-consign-price-mysql")
public class ConsignPrice {
    @Id
    @Column(name = "consign_price_id")
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
