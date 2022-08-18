package fdse.microservice.controller;

import edu.fudan.common.entity.Travel;
import edu.fudan.common.util.Response;
import fdse.microservice.service.BasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author Chenjie
 * @date 2017/6/6.
 */
@RestController
@RequestMapping("/api/v1/basicservice")

public class BasicController {

    @Autowired
    BasicService service;

    private static final Logger logger = LoggerFactory.getLogger(BasicController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [ Basic Service ] !";
    }

    @PostMapping(value = "/basic/travel")
    public HttpEntity queryForTravel(@RequestBody Travel info, @RequestHeader HttpHeaders headers) {
        // TravelResult
        logger.info("[queryForTravel][Query for travel][Travel: {}]", info.toString());
        return ok(service.queryForTravel(info, headers));
//        Response response = service.queryForTravel(info, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return badRequest().body(response);
    }

    @PostMapping(value = "/basic/travels")
    public HttpEntity queryForTravels(@RequestBody List<Travel> infos, @RequestHeader HttpHeaders headers) {
        // TravelResult
        logger.info("[queryForTravels][Query for travels][Travels: {}]", infos);
        return ok(service.queryForTravels(infos, headers));
//        Response response = service.queryForTravels(infos, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return badRequest().body(response);
    }

    @GetMapping(value = "/basic/{stationName}")
    public HttpEntity queryForStationId(@PathVariable String stationName, @RequestHeader HttpHeaders headers) {
        // String id
        logger.info("[queryForStationId][Query for stationId by stationName][stationName: {}]", stationName);
        return ok(service.queryForStationId(stationName, headers));
//        Response response = service.queryForStationId(stationName, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

}
