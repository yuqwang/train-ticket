package route.controller;

import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import route.entity.RouteInfo;
import route.service.RouteService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/routeservice")
public class RouteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteController.class);
    @Autowired
    private RouteService routeService;

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ Route Service ] !";
    }

    @PostMapping(path = "/routes")
    public ResponseEntity<Response> createAndModifyRoute(@RequestBody RouteInfo createAndModifyRouteInfo, @RequestHeader HttpHeaders headers) {
        RouteController.LOGGER.info("[createAndModify][Create route][start: {}, end: {}]", createAndModifyRouteInfo.getStartStation(),createAndModifyRouteInfo.getEndStation());
        return ok(routeService.createAndModify(createAndModifyRouteInfo, headers));
//        Response response =routeService.createAndModify(createAndModifyRouteInfo, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/routes/{routeId}")
    public HttpEntity deleteRoute(@PathVariable String routeId, @RequestHeader HttpHeaders headers) {
        RouteController.LOGGER.info("[deleteRoute][Delete route][RouteId: {}]", routeId);
        return ok(routeService.deleteRoute(routeId, headers));
//        Response response =routeService.deleteRoute(routeId, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/routes/{routeId}")
    public HttpEntity queryById(@PathVariable String routeId, @RequestHeader HttpHeaders headers) {
        RouteController.LOGGER.info("[getRouteById][Query route by id][RouteId: {}]", routeId);
        return ok(routeService.getRouteById(routeId, headers));
//        Response response =routeService.getRouteById(routeId, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/routes/byIds")
    public HttpEntity queryByIds(@RequestBody List<String> routeIds, @RequestHeader HttpHeaders headers) {
        RouteController.LOGGER.info("[getRouteById][Query route by id][RouteId: {}]", routeIds);
        return ok(routeService.getRouteByIds(routeIds, headers));
//        Response response =routeService.getRouteByIds(routeIds, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/routes")
    public HttpEntity queryAll(@RequestHeader HttpHeaders headers) {
        RouteController.LOGGER.info("[getAllRoutes][Query all routes]");
        return ok(routeService.getAllRoutes(headers));
//        Response response =routeService.getAllRoutes(headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/routes/{start}/{end}")
    public HttpEntity queryByStartAndTerminal(@PathVariable String start,
                                              @PathVariable String end,
                                              @RequestHeader HttpHeaders headers) {
        RouteController.LOGGER.info("[getRouteByStartAndEnd][Query routes][start: {}, end: {}]", start, end);
        return ok(routeService.getRouteByStartAndEnd(start, end, headers));
//        Response response =routeService.getRouteByStartAndEnd(start, end, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

}