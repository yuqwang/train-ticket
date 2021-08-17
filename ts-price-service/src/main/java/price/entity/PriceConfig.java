package price.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
//import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author fdse
 */
@Data
@AllArgsConstructor
//@Document(collection="price_config")
@Entity
public class PriceConfig {

    @Id
    private String id;

    private String trainType;

    private String routeId;

    private double basicPriceRate;

    private double firstClassPriceRate;

    public PriceConfig() {
        //Empty Constructor
    }

}
