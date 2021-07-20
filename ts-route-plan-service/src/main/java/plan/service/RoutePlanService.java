package plan.service;

import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;
import plan.entity.RoutePlanInfo;

import java.util.concurrent.ExecutionException;

/**
 * @author fdse
 */
public interface RoutePlanService {

    /**
     * search the cheapest result with route plan info
     *
     * @param info route plan info
     * @param headers headers
     * @return Response
     */
    Response searchCheapestResult(RoutePlanInfo info, HttpHeaders headers) throws InterruptedException, ExecutionException;

    /**
     * search the quickest result with route plan info
     *
     * @param info route plan info
     * @param headers headers
     * @return Response
     */
    Response searchQuickestResult(RoutePlanInfo info, HttpHeaders headers) throws InterruptedException, ExecutionException;

    /**
     * search min stop-station with route plan info
     *
     * @param info route plan info
     * @param headers headers
     * @return Response
     */
    Response searchMinStopStations(RoutePlanInfo info, HttpHeaders headers);

}
