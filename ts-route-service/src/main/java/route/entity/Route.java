package route.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author fdse
 */
@Data
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

    @Id
    @Column(name = "route_id")
    private String id;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "route_id"))
    private List<String> stations;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "route_id"))
    private List<Integer> distances;

    @Column(name = "starting_station_id")
    private String startStationId;

    private String terminalStationId;

    public Route() {
        //Default Constructor
    }
}
