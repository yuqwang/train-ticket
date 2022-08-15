package execute.controller;

import edu.fudan.common.util.Response;
import execute.serivce.ExecuteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/executeservice")
public class ExecuteControlller {

    @Autowired
    private ExecuteService executeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteControlller.class);

    @GetMapping(path = "/welcome")
    public String home(@RequestHeader HttpHeaders headers) {
        return "Welcome to [ Execute Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/execute/execute/{orderId}")
    public HttpEntity executeTicket(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        ExecuteControlller.LOGGER.info("[executeTicket][Execute][Id: {}]", orderId);
        // null
//        return ok(executeService.ticketExecute(orderId, headers));
        Response response =executeService.ticketExecute(orderId, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/execute/collected/{orderId}")
    public HttpEntity collectTicket(@PathVariable String orderId, @RequestHeader HttpHeaders headers) {
        ExecuteControlller.LOGGER.info("[collectTicket][Collect][Id: {}]", orderId);
        // null
//        return ok(executeService.ticketCollect(orderId, headers));
        Response response =executeService.ticketCollect(orderId, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

}
