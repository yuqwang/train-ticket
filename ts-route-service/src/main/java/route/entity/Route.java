package route.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @Column(name = "routeId")
    private String id;

    @ElementCollection
    private List<String> stations;

    @ElementCollection
    private List<Integer> distances;

    @Column(name = "startingStationId")
    private String startStationId;

    private String terminalStationId;

    public Route() {
        //Default Constructor
    }
}
