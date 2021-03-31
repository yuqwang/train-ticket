package route.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import route.entity.Route;

import java.util.ArrayList;

/**
 * @author fdse
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    /**
     * find route by id
     *
     * @param id id
     * @return Route
     */
    Route findById(String id);

    /**
     * find all routes
     *
     * @return ArrayList<Route>
     */
    @Override
    ArrayList<Route> findAll();

    /**
     * remove route via id
     *
     * @param id id
     */
    void removeRouteById(String id);

    /**
     * return route with id from StartStationId to TerminalStationId
     *
     * @param startingId Start Station Id
     * @param terminalId Terminal Station Id
     * @return ArrayList<Route>
     */
    ArrayList<Route> findByStartStationIdAndTerminalStationId(String startingId, String terminalId);
}
