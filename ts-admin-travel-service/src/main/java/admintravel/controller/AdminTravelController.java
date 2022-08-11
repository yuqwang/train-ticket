package admintravel.controller;

import admintravel.service.AdminTravelService;
import edu.fudan.common.entity.AdminTrip;
import edu.fudan.common.entity.TravelInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = AdminTrip.class,responseContainer = "ArrayList"),
            @ApiResponse(code = 0, message = "No Content")
    })
    public HttpEntity getAllTravels(@RequestHeader HttpHeaders headers) {
        logger.info("[getAllTravels][Get all travels]");
        return ok(adminTravelService.getAllTravels(headers));
    }

    @PostMapping(value = "/admintravel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value = "TravelInfo",dataType = "TravelInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "[Admin add new travel]"),
            @ApiResponse(code = 0, message = "Admin add new travel failed")
    })
    public HttpEntity addTravel(@RequestBody TravelInfo request, @RequestHeader HttpHeaders headers) {
        logger.info("[addTravel][Add travel][trip id: {}, train type name: {}, form station {} to station {}, login id: {}]",
                request.getTripId(), request.getTrainTypeName(), request.getStartStationName(), request.getStationsName(), request.getLoginId());
        return ok(adminTravelService.addTravel(request, headers));
    }

    @PutMapping(value = "/admintravel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value = "TravelInfo",dataType = "TravelInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Admin update travel failed"),
            @ApiResponse(code = 200, message = "Create trip.")
    })
    public HttpEntity updateTravel(@RequestBody TravelInfo request, @RequestHeader HttpHeaders headers) {
        logger.info("[updateTravel][Update travel][trip id: {}, train type id: {}, form station {} to station {}, login id: {}]",
                request.getTripId(), request.getTrainTypeName(), request.getStartStationName(), request.getStationsName(), request.getLoginId());
        return ok(adminTravelService.updateTravel(request, headers));
    }

    @DeleteMapping(value = "/admintravel/{tripId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tripId", value = "tripId",dataType = "String", paramType = "path",required = true,defaultValue = "G1234")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Admin delete travel failed"),
            @ApiResponse(code = 200, message = "Delete trip.",response = String.class),
            @ApiResponse(code = 0, message = "Trip doesn't exist.",response = String.class)
    })
    public HttpEntity deleteTravel(@PathVariable String tripId, @RequestHeader HttpHeaders headers) {
        logger.info("[deleteTravel][Delete travel][trip id: {}]", tripId);
        return ok(adminTravelService.deleteTravel(tripId, headers));
    }

}
