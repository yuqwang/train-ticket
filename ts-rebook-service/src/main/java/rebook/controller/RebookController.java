package rebook.controller;

import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rebook.entity.RebookInfo;
import rebook.service.RebookService;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/rebookservice")
public class RebookController {

    @Autowired
    RebookService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(RebookController.class);

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ Rebook Service ] !";
    }

    @PostMapping(value = "/rebook/difference")
    public HttpEntity payDifference(@RequestBody RebookInfo info,
                                    @RequestHeader HttpHeaders headers) {
        RebookController.LOGGER.info("[payDifference][Pay difference][OrderId: {}]",info.getOrderId());
//        return ok(service.payDifference(info, headers));
        Response response =service.payDifference(info, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/rebook")
    public HttpEntity rebook(@RequestBody RebookInfo info, @RequestHeader HttpHeaders headers) {
        RebookController.LOGGER.info("[rebook][Rebook][OrderId: {}, Old Trip Id: {}, New Trip Id: {}, Date: {}, Seat Type: {}]", info.getOrderId(), info.getOldTripId(), info.getTripId(), info.getDate(), info.getSeatType());
//        return ok(service.rebook(info, headers));
        Response response =service.rebook(info, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

}
