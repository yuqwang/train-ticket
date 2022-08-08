package cancel.controller;

import cancel.service.CancelService;
import edu.fudan.common.util.Response;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/cancelservice")
public class CancelController {

    @Autowired
    CancelService cancelService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelController.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [ Cancel Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/cancel/refound/{orderId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true,defaultValue = "fa9bd99c-2f28-45a9-9d87-7630caaccc6a")
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "Success. Refoud 0"),
            @ApiResponse(code = 1, message = "Success. ",response = String.class),
            @ApiResponse(code = 0, message = "Order Status Cancel Not Permitted, Refound error")
    })
    public HttpEntity calculate(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        CancelController.LOGGER.info("[calculate][Calculate Cancel Refund][OrderId: {}]", orderId);
        return ok(cancelService.calculateRefund(orderId, headers));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/cancel/{orderId}/{loginId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId",dataType = "String", paramType = "path",required = true,defaultValue = "fa9bd99c-2f28-45a9-9d87-7630caaccc6a"),
            @ApiImplicitParam(name = "loginId", value = "loginId",dataType = "String", paramType = "path",required = true,defaultValue = "4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f")
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "Success."),
            @ApiResponse(code = 0,message = "Order Status Cancel Not Permitted")
    })
    public HttpEntity cancelTicket(@PathVariable String orderId, @PathVariable String loginId,
                                   @RequestHeader HttpHeaders headers) {
        CancelController.LOGGER.info("[cancelTicket][Cancel Ticket][info: {}]", orderId);
        try {
            CancelController.LOGGER.info("[cancelTicket][Cancel Ticket, Verify Success]");
            return ok(cancelService.cancelOrder(orderId, loginId, headers));
        } catch (Exception e) {
            CancelController.LOGGER.error(e.getMessage());
            return ok(new Response<>(1, "error", null));
        }
    }

}
