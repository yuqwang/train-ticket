package trainFood.controller;

import edu.fudan.common.entity.Food;
import edu.fudan.common.entity.TravelResult;
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
import trainFood.entity.TrainFood;
import trainFood.service.TrainFoodService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/trainfoodservice")
public class TrainFoodController {

    @Autowired
    TrainFoodService trainFoodService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainFoodController.class);

    @GetMapping(path = "/trainfoods/welcome")
    public String home() {
        return "Welcome to [ Train Food Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/trainfoods")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = TrainFood.class,responseContainer = "List")
    })
    public HttpEntity getAllTrainFood(@RequestHeader HttpHeaders headers) {
        TrainFoodController.LOGGER.info("[Food Map Service][Get All TrainFoods]");
        return ok(trainFoodService.listTrainFood(headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/trainfoods/{tripId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tripId", value = "tripId",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Query For Station Id",response = Food.class,responseContainer = "List")
    })
    public HttpEntity getTrainFoodOfTrip(@PathVariable String tripId, @RequestHeader HttpHeaders headers) {
        TrainFoodController.LOGGER.info("[Food Map Service][Get TrainFoods By TripId]");
        return ok(trainFoodService.listTrainFoodByTripId(tripId, headers));
    }
}
