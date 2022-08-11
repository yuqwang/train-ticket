package waitorder.controller;


import edu.fudan.common.entity.Contacts;
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
import waitorder.entity.WaitListOrder;
import waitorder.entity.WaitListOrderVO;
import waitorder.service.WaitListOrderService;


import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/waitorderservice")
public class WaitListOrderController {

    @Autowired
    private WaitListOrderService waitListOrderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitListOrderController.class);

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ Wait Order Service ] !";
    }

    @PostMapping(path = "/order")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "createOrder",value = "createOrder",dataType = "WaitListOrderVO", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Thread Start Success"),
            @ApiResponse(code = 400, message = "cache to database failed", response = WaitListOrder.class,responseContainer = "Response")
    })
    public HttpEntity createNewOrder(@RequestBody WaitListOrderVO createOrder, @RequestHeader HttpHeaders headers) {
        WaitListOrderController.LOGGER.info("[createWaitOrder][Create Wait Order][from {} to {} at {}]", createOrder.getFrom(), createOrder.getTo(), createOrder.getDate());
        return ok(waitListOrderService.create(createOrder, headers));
    }

    @GetMapping(path = "/orders")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = WaitListOrder.class,responseContainer = "List")
    })
    public HttpEntity getAllOrders(@RequestHeader HttpHeaders headers){
        LOGGER.info("[getAllOrders][Get All Orders]");
        return ok(waitListOrderService.getAllOrders(headers));
    }

    @GetMapping(path = "/waitlistorders")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = WaitListOrder.class,responseContainer = "List")
    })
    public HttpEntity getWaitListOrders(@RequestHeader HttpHeaders headers){
        LOGGER.info("[getWaitListOrders][Get All Wait List Orders]");
        return ok(waitListOrderService.getAllWaitListOrders(headers));
    }


}
