package travel2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Trip")
public class Trip {

    @Id
    @Column(name = "userID")
    @GeneratedValue(generator = "IdStrategy")
    @GenericGenerator(name = "IdStrategy", strategy = "assigned")
    private String tripId;

    private String trainTypeId;

    private String routeId;

    private String startingStationId;

    private String stationsId;

    private String terminalStationId;

    private Date startingTime;


    private Date endTime;

    public Trip(String tripId, String trainTypeId, String startingStationId, String stationsId, String terminalStationId, Date startingTime, Date endTime) {
        this.tripId = tripId;
        this.trainTypeId = trainTypeId;
        this.startingStationId = startingStationId;
        this.stationsId = stationsId;
        this.terminalStationId = terminalStationId;
        this.startingTime = startingTime;
        this.endTime = endTime;
    }

    public Trip(String tripId, String trainTypeId, String routeId) {
        this.tripId = tripId;
        this.trainTypeId = trainTypeId;
        this.routeId = routeId;
    }

}
