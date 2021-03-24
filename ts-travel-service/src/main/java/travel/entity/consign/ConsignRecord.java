package travel.entity.consign;

import lombok.Data;

import javax.persistence.*;

/**
 * @author fdse
 */
@Data
@Entity
public class ConsignRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
