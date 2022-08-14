package food.controller;

import edu.fudan.common.util.Response;
import food.service.StationFoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/stationfoodservice")
public class StationFoodController {

    @Autowired
    StationFoodService stationFoodService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StationFoodController.class);

    @GetMapping(path = "/stationfoodstores/welcome")
    public String home() {
        return "Welcome to [ Food store Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/stationfoodstores")
    public HttpEntity getAllFoodStores(@RequestHeader HttpHeaders headers) {
        StationFoodController.LOGGER.info("[Food Map Service][Get All FoodStores]");
//        return ok(stationFoodService.listFoodStores(headers));
        Response response =stationFoodService.listFoodStores(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/stationfoodstores/{stationId}")
    public HttpEntity getFoodStoresOfStation(@PathVariable String stationName, @RequestHeader HttpHeaders headers) {
        StationFoodController.LOGGER.info("[Food Map Service][Get FoodStores By StationName]");
//        return ok(stationFoodService.listFoodStoresByStationName(stationName, headers));
        Response response =stationFoodService.listFoodStoresByStationName(stationName, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/stationfoodstores")
    public HttpEntity getFoodStoresByStationNames(@RequestBody List<String> stationNameList) {
        StationFoodController.LOGGER.info("[Food Map Service][Get FoodStores By StationNames]");
//        return ok(stationFoodService.getFoodStoresByStationNames(stationNameList));
        Response response =stationFoodService.getFoodStoresByStationNames(stationNameList);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/stationfoodstores/bystoreid/{stationFoodStoreId}")
    public HttpEntity getFoodListByStationFoodStoreId(@PathVariable String stationFoodStoreId, @RequestHeader HttpHeaders headers) {
        StationFoodController.LOGGER.info("[Food Map Service][Get Foodlist By stationFoodStoreId]");
//        return ok(stationFoodService.getStaionFoodStoreById(stationFoodStoreId));
        Response response =stationFoodService.getStaionFoodStoreById(stationFoodStoreId);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }
}
