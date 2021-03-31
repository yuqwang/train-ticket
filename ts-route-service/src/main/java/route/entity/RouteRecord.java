package route.entity;

import lombok.Data;

import java.util.List;

/**
 * @author fdse
 */
@Data
public class RouteRecord {

    private String id;

    private List<String> stations;

    private List<Integer> distances;

    private String startStationId;

    private String terminalStationId;

    public RouteRecord(Route route) {
        this.id = route.getId();
        this.stations = route.getStations();
        this.distances = route.getDistances();
        this.startStationId = route.getStartStationId();
        this.terminalStationId = route.getTerminalStationId();
    }
}
