package food.controller;

import food.entity.StationFoodStore;
import food.service.StationFoodService;
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
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = StationFoodStore.class,responseContainer = "List")
    })
    public HttpEntity getAllFoodStores(@RequestHeader HttpHeaders headers) {
        StationFoodController.LOGGER.info("[Food Map Service][Get All FoodStores]");
        return ok(stationFoodService.listFoodStores(headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/stationfoodstores/{stationId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stationName", value = "stationName",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = StationFoodStore.class,responseContainer = "List")
    })
    public HttpEntity getFoodStoresOfStation(@PathVariable String stationName, @RequestHeader HttpHeaders headers) {
        StationFoodController.LOGGER.info("[Food Map Service][Get FoodStores By StationName]");
        return ok(stationFoodService.listFoodStoresByStationName(stationName, headers));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/stationfoodstores")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stationNameList", value = "stationNameList",dataType = "String",allowMultiple = true,paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = StationFoodStore.class,responseContainer = "List")
    })
    public HttpEntity getFoodStoresByStationNames(@RequestBody List<String> stationNameList) {
        StationFoodController.LOGGER.info("[Food Map Service][Get FoodStores By StationNames]");
        return ok(stationFoodService.getFoodStoresByStationNames(stationNameList));
    }

    @GetMapping("/stationfoodstores/bystoreid/{stationFoodStoreId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stationFoodStoreId", value = "stationFoodStoreId",dataType = "String",paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success",response = StationFoodStore.class,responseContainer = "List")
    })
    public HttpEntity getFoodListByStationFoodStoreId(@PathVariable String stationFoodStoreId, @RequestHeader HttpHeaders headers) {
        StationFoodController.LOGGER.info("[Food Map Service][Get Foodlist By stationFoodStoreId]");
        return ok(stationFoodService.getStaionFoodStoreById(stationFoodStoreId));
    }
}
