package foodsearch.controller;

import edu.fudan.common.util.JsonUtils;
import foodsearch.entity.*;
import foodsearch.mq.RabbitSend;
import foodsearch.service.FoodService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/foodservice")
public class FoodController {

    @Autowired
    FoodService foodService;

    @Autowired
    RabbitSend sender;

    private static final Logger LOGGER = LoggerFactory.getLogger(FoodController.class);

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ Food Service ] !";
    }

    @GetMapping(path = "/test_send_delivery")
    public boolean test_send_delivery() {
        Delivery delivery = new Delivery();
        delivery.setFoodName("HotPot");
        delivery.setOrderId(UUID.randomUUID());
        delivery.setStationName("Shang Hai");
        delivery.setStoreName("MiaoTing Instant-Boiled Mutton");

        String deliveryJson = JsonUtils.object2Json(delivery);
        sender.send(deliveryJson);
        return true;
    }

    @GetMapping(path = "/orders")
    public HttpEntity findAllFoodOrder(@RequestHeader HttpHeaders headers) {
        FoodController.LOGGER.info("[Food Service]Try to Find all FoodOrder!");
        return ok(foodService.findAllFoodOrder(headers));
    }

    @PostMapping(path = "/orders")
    public HttpEntity createFoodOrder(@RequestBody FoodOrder addFoodOrder, @RequestHeader HttpHeaders headers) {
        FoodController.LOGGER.info("[createFoodOrder][Try to Create a FoodOrder!]");
        return ok(foodService.createFoodOrder(addFoodOrder, headers));
    }

    @PostMapping(path = "/createOrderBatch")
    public HttpEntity createFoodBatches(@RequestBody List<FoodOrder> foodOrderList, @RequestHeader HttpHeaders headers) {
        FoodController.LOGGER.info("[createFoodBatches][Try to Create Food Batches!]");
        return ok(foodService.createFoodOrdersInBatch(foodOrderList, headers));
    }


    @PutMapping(path = "/orders")
    public HttpEntity updateFoodOrder(@RequestBody FoodOrder updateFoodOrder, @RequestHeader HttpHeaders headers) {
        FoodController.LOGGER.info("[updateFoodOrder][Try to Update a FoodOrder!]");
        return ok(foodService.updateFoodOrder(updateFoodOrder, headers));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(path = "/orders/{orderId}")
    public HttpEntity deleteFoodOrder(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        FoodController.LOGGER.info("[deleteFoodOrder][Try to Cancel a FoodOrder!]");
        return ok(foodService.deleteFoodOrder(orderId, headers));
    }

    @GetMapping(path = "/orders/{orderId}")
    public HttpEntity findFoodOrderByOrderId(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        FoodController.LOGGER.info("[findFoodOrderByOrderId][Try to Find FoodOrder By orderId!][orderId: {}]", orderId);
        return ok(foodService.findByOrderId(orderId, headers));
    }

    // This relies on a lot of other services, not completely modified
    @GetMapping(path = "/foods/{date}/{startStation}/{endStation}/{tripId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "date",dataType = "String", paramType = "path",required = true,defaultValue = "1367629200000"),
            @ApiImplicitParam(name = "startStation", value = "startStation",dataType = "String", paramType = "path",required = true,defaultValue = "Shang Hai"),
            @ApiImplicitParam(name = "endStation", value = "endStation",dataType = "String", paramType = "path",required = true,defaultValue = "Tai Yuan"),
            @ApiImplicitParam(name = "tripId", value = "tripId",dataType = "String", paramType = "path",required = true,defaultValue = "G1234")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Get the Get Food Request Failed!"),
            @ApiResponse(code = 0, message = "Get All Food Failed",response = AllTripFood.class),
            @ApiResponse(code = 1, message = "Get All Food Success",response = AllTripFood.class)
    })
    public HttpEntity getAllFood(@PathVariable String date, @PathVariable String startStation,
                                 @PathVariable String endStation, @PathVariable String tripId,
                                 @RequestHeader HttpHeaders headers) {
        FoodController.LOGGER.info("[getAllFood][Get Food Request!]");
        return ok(foodService.getAllFood(date, startStation, endStation, tripId, headers));
    }

}
