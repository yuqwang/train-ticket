package consign.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsignRecord {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    private String orderId;
    private String accountId;
    private String handleDate;
    private String targetDate;
    @Column(name = "from_place")
    private String from;
    @Column(name = "to_place")
    private String to;
    private String consignee;
    private String phone;
    private double weight;
    private double price;

}
