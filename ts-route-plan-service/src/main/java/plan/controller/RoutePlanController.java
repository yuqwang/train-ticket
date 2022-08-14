package plan.controller;

import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.fudan.common.entity.RoutePlanInfo;
import plan.service.RoutePlanService;

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
    public HttpEntity getCheapestRoutes(@RequestBody RoutePlanInfo info, @RequestHeader HttpHeaders headers) {
        RoutePlanController.LOGGER.info("[searchCheapestResult][Get Cheapest Routes][From: {}, To: {}, Num: {}, Date: {}]", info.getStartStation(), info.getEndStation(), + info.getNum(), info.getTravelDate());
//        return ok(routePlanService.searchCheapestResult(info, headers));
        Response response =routePlanService.searchCheapestResult(info, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/routePlan/quickestRoute")
    public HttpEntity getQuickestRoutes(@RequestBody RoutePlanInfo info, @RequestHeader HttpHeaders headers) {
        RoutePlanController.LOGGER.info("[searchQuickestResult][Get Quickest Routes][From: {}, To: {}, Num: {}, Date: {}]", info.getStartStation(), info.getEndStation(), info.getNum(), info.getTravelDate());
//        return ok(routePlanService.searchQuickestResult(info, headers));
        Response response =routePlanService.searchQuickestResult(info, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/routePlan/minStopStations")
    public HttpEntity getMinStopStations(@RequestBody RoutePlanInfo info, @RequestHeader HttpHeaders headers) {
        RoutePlanController.LOGGER.info("[searchMinStopStations][Get Min Stop Stations][From: {}, To: {}, Num: {}, Date: {}]", info.getStartStation(), info.getEndStation(), info.getNum(), info.getTravelDate());
//        return ok(routePlanService.searchMinStopStations(info, headers));
        Response response =routePlanService.searchMinStopStations(info, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

}
