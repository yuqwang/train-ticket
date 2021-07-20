package plan.controller;

import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import plan.entity.RoutePlanInfo;
import plan.service.RoutePlanService;

import java.util.concurrent.ExecutionException;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/routeplanservice")
public class RoutePlanController {

    @Autowired
    private RoutePlanService routePlanService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RoutePlanController.class);

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ RoutePlan Service ] !";
    }

    @PostMapping(value = "/routePlan/cheapestRoute")
    public HttpEntity getCheapestRoutes(@RequestBody RoutePlanInfo info, @RequestHeader HttpHeaders headers){
        RoutePlanController.LOGGER.info("[Get Cheapest Routes] From: {} To: {} Num: {} Date: {}", info.getFormStationName(), info.getToStationName(), + info.getNum(), info.getTravelDate());
        try{
            RoutePlanController.LOGGER.info("[Get Cheapest Route] Verify Success");
            return ok(routePlanService.searchCheapestResult(info, headers));
        }catch (Exception e){
            RoutePlanController.LOGGER.error(e.getMessage());
            return ok(new Response<>(1, "error", e.getMessage()));
        }
    }

    @PostMapping(value = "/routePlan/quickestRoute")
    public HttpEntity getQuickestRoutes(@RequestBody RoutePlanInfo info, @RequestHeader HttpHeaders headers) {
        RoutePlanController.LOGGER.info("[Get Quickest Routes] From: {} To: {} Num: {} Date: {}", info.getFormStationName(), info.getToStationName(), info.getNum(), info.getTravelDate());
        try{
            RoutePlanController.LOGGER.info("[Get Quickest Routes] Verify Success");
            return ok(routePlanService.searchQuickestResult(info, headers));
        }catch (Exception e){
            RoutePlanController.LOGGER.error(e.getMessage());
            return ok(new Response<>(1, "error", e.getMessage()));
        }
    }

    @PostMapping(value = "/routePlan/minStopStations")
    public HttpEntity getMinStopStations(@RequestBody RoutePlanInfo info, @RequestHeader HttpHeaders headers) {
        RoutePlanController.LOGGER.info("[Get Min Stop Stations] From: {} To: {} Num: {} Date: {}", info.getFormStationName(), info.getToStationName(), info.getNum(), info.getTravelDate());
        return ok(routePlanService.searchMinStopStations(info, headers));
    }

}
