package other.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TripOrder")
public class Order implements Serializable {

    private static final long serialVersionUID = -4633061421496522408L;

    @Id
    @Column(name = "orderId")
    @GeneratedValue(generator = "IdStrategy")
    @GenericGenerator(name = "IdStrategy", strategy = "assigned")
    private String id;

    @Column
    private Date boughtDate;

    @Column
    private Date travelDate;

    @Column
    private Date travelTime;

    //Which Account Bought it
    @Column
    private String accountId;

    //Tickets bought for whom....
    @Column
    private String contactsName;

    @Column
    private int documentType;

    @Column
    private String contactsDocumentNumber;

    @Column
    private String trainNumber;

    @Column
    private int coachNumber;

    @Column
    private int seatClass;

    @Column
    private String seatNumber;

    @Column(name = "FromPlace")
    private String from;

    @Column(name = "ToPlace")
    private String to;

    @Column(name = "OrderStatus")
    private int status;

    @Column
    private String price;
}
