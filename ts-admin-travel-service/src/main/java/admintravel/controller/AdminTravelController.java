package admintravel.controller;

import admintravel.service.AdminTravelService;
import edu.fudan.common.entity.TravelInfo;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/admintravelservice")
public class AdminTravelController {
    @Autowired
    AdminTravelService adminTravelService;

    private static final Logger logger = LoggerFactory.getLogger(AdminTravelController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [ AdminTravel Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/admintravel")
    public HttpEntity getAllTravels(@RequestHeader HttpHeaders headers) {
        logger.info("[getAllTravels][Get all travels]");
//        return ok(adminTravelService.getAllTravels(headers));
        Response response = adminTravelService.getAllTravels(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/admintravel")
    public HttpEntity addTravel(@RequestBody TravelInfo request, @RequestHeader HttpHeaders headers) {
        logger.info("[addTravel][Add travel][trip id: {}, train type name: {}, form station {} to station {}, login id: {}]",
                request.getTripId(), request.getTrainTypeName(), request.getStartStationName(), request.getStationsName(), request.getLoginId());
//        return ok(adminTravelService.addTravel(request, headers));
        Response response = adminTravelService.addTravel(request, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @PutMapping(value = "/admintravel")
    public HttpEntity updateTravel(@RequestBody TravelInfo request, @RequestHeader HttpHeaders headers) {
        logger.info("[updateTravel][Update travel][trip id: {}, train type id: {}, form station {} to station {}, login id: {}]",
                request.getTripId(), request.getTrainTypeName(), request.getStartStationName(), request.getStationsName(), request.getLoginId());
//        return ok(adminTravelService.updateTravel(request, headers));
        Response response = adminTravelService.updateTravel(request, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @DeleteMapping(value = "/admintravel/{tripId}")
    public HttpEntity deleteTravel(@PathVariable String tripId, @RequestHeader HttpHeaders headers) {
        logger.info("[deleteTravel][Delete travel][trip id: {}]", tripId);
//        return ok(adminTravelService.deleteTravel(tripId, headers));
        Response response = adminTravelService.deleteTravel(tripId, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

}
