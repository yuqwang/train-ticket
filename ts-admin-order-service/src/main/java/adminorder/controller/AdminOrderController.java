package adminorder.controller;

import edu.fudan.common.entity.*;
import adminorder.service.AdminOrderService;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.badRequest;
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
    public HttpEntity getAllOrders(@RequestHeader HttpHeaders headers) {
        logger.info("[getAllOrders][Get all orders][getAllOrders]");
//        return ok(adminOrderService.getAllOrders(headers));
        Response response = adminOrderService.getAllOrders(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @PostMapping(value = "/adminorder")
    public HttpEntity addOrder(@RequestBody Order request, @RequestHeader HttpHeaders headers) {
        logger.info("[addOrder][Add new order][AccountID: {}]", request.getAccountId());
//        return ok(adminOrderService.addOrder(request, headers));
        Response response = adminOrderService.addOrder(request, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @PutMapping(value = "/adminorder")
    public HttpEntity updateOrder(@RequestBody Order request, @RequestHeader HttpHeaders headers) {
        logger.info("[updateOrder][Update order][AccountID: {}, OrderId: {}]", request.getAccountId(), request.getId());
//        return ok(adminOrderService.updateOrder(request, headers));
        Response response = adminOrderService.updateOrder(request, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

    @DeleteMapping(value = "/adminorder/{orderId}/{trainNumber}")
    public HttpEntity deleteOrder(@PathVariable String orderId, @PathVariable String trainNumber, @RequestHeader HttpHeaders headers) {
        logger.info("[deleteOrder][Delete order][OrderId: {}, TrainNumber: {}]", orderId, trainNumber);
//        return ok(adminOrderService.deleteOrder(orderId, trainNumber, headers));
        Response response = adminOrderService.deleteOrder(orderId, trainNumber, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return badRequest().body(response);
    }

}
