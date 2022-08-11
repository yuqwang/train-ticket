package food_delivery.controller;


import edu.fudan.common.entity.TravelResult;
import edu.fudan.common.util.Response;
import food_delivery.entity.DeliveryInfo;
import food_delivery.entity.FoodDeliveryOrder;
import food_delivery.entity.SeatInfo;
import food_delivery.entity.TripOrderInfo;
import food_delivery.service.FoodDeliveryService;
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

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/fooddeliveryservice")
public class FoodDeliveryController {

    @Autowired
    private FoodDeliveryService foodDeliveryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FoodDeliveryController.class);

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ food delivery service ] !";
    }


    @PostMapping("/orders")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fd", value = "fd",dataType = "FoodDeliveryOrder", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = FoodDeliveryOrder.class)
    })
    public HttpEntity createFoodDeliveryOrder(@RequestBody FoodDeliveryOrder fd, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Create Food Delivery Order]");
        return ok(foodDeliveryService.createFoodDeliveryOrder(fd, headers));
    }

    @DeleteMapping("/orders/d/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Delete Food Delivery Order",response = String.class)
    })
    public HttpEntity deleteFoodDeliveryOrder(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Delete Food Delivery Order]");
        return ok(foodDeliveryService.deleteFoodDeliveryOrder(orderId, headers));
    }

    @GetMapping("/orders/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get Food Delivery Order By Id",response = FoodDeliveryOrder.class)
    })
    public HttpEntity getFoodDeliveryOrderById(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Get Food Delivery Order By Id]");
        return ok(foodDeliveryService.getFoodDeliveryOrderById(orderId, headers));
    }

    @GetMapping("/orders/all")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get Food Delivery Order",response = FoodDeliveryOrder.class,responseContainer = "List")
    })
    public HttpEntity getAllFoodDeliveryOrders(@RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Get All Food Delivery Orders]");
        return ok(foodDeliveryService.getAllFoodDeliveryOrders(headers));
    }

    @GetMapping("/orders/store/{storeId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "storeId",dataType = "String", paramType = "path",required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get Food Delivery Order By Id",response = FoodDeliveryOrder.class,responseContainer = "List")
    })
    public HttpEntity getFoodDeliveryOrderByStoreId(@PathVariable String storeId, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Get Food Delivery Order By StoreId]");
        return ok(foodDeliveryService.getFoodDeliveryOrderByStoreId(storeId, headers));
    }

    @PutMapping("/orders/tripid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tripOrderInfo", value = "tripOrderInfo",dataType = "TripOrderInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = FoodDeliveryOrder.class)
    })
    public HttpEntity updateTripId(@RequestBody TripOrderInfo tripOrderInfo, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Update Trip Id]");
        return ok(foodDeliveryService.updateTripId(tripOrderInfo, headers));
    }

    @PutMapping("/orders/seatno")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seatInfo", value = "SeatInfo",dataType = "SeatInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = FoodDeliveryOrder.class)
    })
    public HttpEntity updateSeatNo(@RequestBody SeatInfo seatInfo, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Update Seat No]");
        return ok(foodDeliveryService.updateSeatNo(seatInfo, headers));
    }

    @PutMapping("/orders/dtime")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deliveryInfo", value = "DeliveryInfo",dataType = "DeliveryInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = FoodDeliveryOrder.class)
    })
    public HttpEntity updateDeliveryTime(@RequestBody DeliveryInfo deliveryInfo, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Update Delivery Time]");
        return ok(foodDeliveryService.updateDeliveryTime(deliveryInfo, headers));
    }
}
