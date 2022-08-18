package food_delivery.controller;


import edu.fudan.common.util.Response;
import food_delivery.entity.DeliveryInfo;
import food_delivery.entity.FoodDeliveryOrder;
import food_delivery.entity.SeatInfo;
import food_delivery.entity.TripOrderInfo;
import food_delivery.service.FoodDeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public HttpEntity createFoodDeliveryOrder(@RequestBody FoodDeliveryOrder fd, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Create Food Delivery Order]");
        return ok(foodDeliveryService.createFoodDeliveryOrder(fd, headers));
//        Response response =foodDeliveryService.createFoodDeliveryOrder(fd, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/orders/d/{orderId}")
    public HttpEntity deleteFoodDeliveryOrder(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Delete Food Delivery Order]");
        return ok(foodDeliveryService.deleteFoodDeliveryOrder(orderId, headers));
//        Response response =foodDeliveryService.deleteFoodDeliveryOrder(orderId, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/orders/{orderId}")
    public HttpEntity getFoodDeliveryOrderById(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Get Food Delivery Order By Id]");
        return ok(foodDeliveryService.getFoodDeliveryOrderById(orderId, headers));
//        Response response =foodDeliveryService.getFoodDeliveryOrderById(orderId, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/orders/all")
    public HttpEntity getAllFoodDeliveryOrders(@RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Get All Food Delivery Orders]");
        return ok(foodDeliveryService.getAllFoodDeliveryOrders(headers));
//        Response response =foodDeliveryService.getAllFoodDeliveryOrders(headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/orders/store/{storeId}")
    public HttpEntity getFoodDeliveryOrderByStoreId(@PathVariable String storeId, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Get Food Delivery Order By StoreId]");
        return ok(foodDeliveryService.getFoodDeliveryOrderByStoreId(storeId, headers));
//        Response response =foodDeliveryService.getFoodDeliveryOrderByStoreId(storeId, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/orders/tripid")
    public HttpEntity updateTripId(@RequestBody TripOrderInfo tripOrderInfo, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Update Trip Id]");
        return ok(foodDeliveryService.updateTripId(tripOrderInfo, headers));
//        Response response =foodDeliveryService.updateTripId(tripOrderInfo, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/orders/seatno")
    public HttpEntity updateSeatNo(@RequestBody SeatInfo seatInfo, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Update Seat No]");
        return ok(foodDeliveryService.updateSeatNo(seatInfo, headers));
//        Response response =foodDeliveryService.updateSeatNo(seatInfo, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/orders/dtime")
    public HttpEntity updateDeliveryTime(@RequestBody DeliveryInfo deliveryInfo, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[Food Delivery Service][Update Delivery Time]");
        return ok(foodDeliveryService.updateDeliveryTime(deliveryInfo, headers));
//        Response response =foodDeliveryService.updateDeliveryTime(deliveryInfo, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }
}
