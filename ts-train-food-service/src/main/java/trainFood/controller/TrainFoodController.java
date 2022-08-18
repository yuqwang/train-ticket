package trainFood.controller;

import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public HttpEntity getAllTrainFood(@RequestHeader HttpHeaders headers) {
        TrainFoodController.LOGGER.info("[Food Map Service][Get All TrainFoods]");
        return ok(trainFoodService.listTrainFood(headers));
//        Response response =trainFoodService.listTrainFood(headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/trainfoods/{tripId}")
    public HttpEntity getTrainFoodOfTrip(@PathVariable String tripId, @RequestHeader HttpHeaders headers) {
        TrainFoodController.LOGGER.info("[Food Map Service][Get TrainFoods By TripId]");
        return ok(trainFoodService.listTrainFoodByTripId(tripId, headers));
//        Response response =trainFoodService.listTrainFoodByTripId(tripId, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
}
