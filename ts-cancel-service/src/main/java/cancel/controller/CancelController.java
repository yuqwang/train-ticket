package cancel.controller;

import cancel.service.CancelService;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.badRequest;
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
    public HttpEntity calculate(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        CancelController.LOGGER.info("[calculate][Calculate Cancel Refund][OrderId: {}]", orderId);
//        return ok(cancelService.calculateRefund(orderId, headers));
        Response response = cancelService.calculateRefund(orderId, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/cancel/{orderId}/{loginId}")
    public HttpEntity cancelTicket(@PathVariable String orderId, @PathVariable String loginId,
                                   @RequestHeader HttpHeaders headers) {

        CancelController.LOGGER.info("[cancelTicket][Cancel Ticket][info: {}]", orderId);
        try {
            CancelController.LOGGER.info("[cancelTicket][Cancel Ticket, Verify Success]");
//            return ok(cancelService.cancelOrder(orderId, loginId, headers));
            Response response = cancelService.cancelOrder(orderId, loginId, headers);
            if (response.getStatus() == 1)
                return ok(response);
            else
                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            CancelController.LOGGER.error(e.getMessage());
            return badRequest().body(new Response<>(1, "error", e.getMessage()));
//            return ok(new Response<>(1, "error", null));
        }
    }

}
