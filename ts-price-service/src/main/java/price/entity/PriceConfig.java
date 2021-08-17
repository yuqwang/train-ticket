package price.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
//import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;
import java.util.UUID;

/**
 * @author fdse
 */
@Data
@AllArgsConstructor
//@Document(collection="price_config")
@Entity
@Table(schema = "ts-price-mysql")
public class PriceConfig {

    @Id
    @Column(name = "price_config_id")
    private String id;

    @Column(name = "train_type_id")
    private String trainType;

    private String routeId;

    private double basicPriceRate;

    private double firstClassPriceRate;

    public PriceConfig() {
        //Empty Constructor
    }

}
