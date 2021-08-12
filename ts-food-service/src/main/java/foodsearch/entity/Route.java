package foodsearch.entity;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

    @Id
    private String id;

    private List<String> stations;

    private List<Integer> distances;

    private String startStationId;

    private String terminalStationId;

    public Route() {
        //Default Constructor
    }

}
