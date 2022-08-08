package adminorder.controller;

import edu.fudan.common.entity.*;
import adminorder.service.AdminOrderService;
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

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/adminorderservice")
public class AdminOrderController {

    @Autowired
    AdminOrderService adminOrderService;

    private static final Logger logger = LoggerFactory.getLogger(AdminOrderController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [Admin Order Service] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/adminorder")
    @ApiResponse(code = 1, message = "Get the orders successfully!",response = Order.class,responseContainer = "ArrayList")
    public HttpEntity getAllOrders(@RequestHeader HttpHeaders headers) {
        logger.info("[getAllOrders][Get all orders][getAllOrders]");
        return ok(adminOrderService.getAllOrders(headers));
    }

    @PostMapping(value = "/adminorder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value = "Order",dataType = "Order", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Order already exist"),
            @ApiResponse(code = 1, message = "Add new Order Success", response = Order.class)
    })
    public HttpEntity addOrder(@RequestBody Order request, @RequestHeader HttpHeaders headers) {
        logger.info("[addOrder][Add new order][AccountID: {}]", request.getAccountId());
        return ok(adminOrderService.addOrder(request, headers));
    }

    @PutMapping(value = "/adminorder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", value = "Order",dataType = "Order", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Order Not Found, Can't update"),
            @ApiResponse(code = 1, message = "Admin Update Order Success",response = Order.class)
    })
    public HttpEntity updateOrder(@RequestBody Order request, @RequestHeader HttpHeaders headers) {
        logger.info("[updateOrder][Update order][AccountID: {}, OrderId: {}]", request.getAccountId(), request.getId());
        return ok(adminOrderService.updateOrder(request, headers));
    }

    @DeleteMapping(value = "/adminorder/{orderId}/{trainNumber}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true,defaultValue = "5ad7750b-a68b-49c0-a8c0-32776b067703"),
            @ApiImplicitParam(name = "trainNumber", value = "trainNumber",dataType = "String", paramType = "path",required = true,defaultValue = "G1237")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Order Not Exist."),
            @ApiResponse(code = 1, message = "Delete Order Success",response = Order.class)
    })
    public HttpEntity deleteOrder(@PathVariable String orderId, @PathVariable String trainNumber, @RequestHeader HttpHeaders headers) {
        logger.info("[deleteOrder][Delete order][OrderId: {}, TrainNumber: {}]", orderId, trainNumber);
        return ok(adminOrderService.deleteOrder(orderId, trainNumber, headers));
    }

}
