package com.trainticket.controller;

import com.trainticket.entity.Money;
import com.trainticket.entity.Payment;
import com.trainticket.service.PaymentService;
import edu.fudan.common.entity.TravelResult;
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
 * @author Chenjie
 * @date 2017/4/7
 */
@RestController
@RequestMapping("/api/v1/paymentservice")
public class PaymentController {

    @Autowired
    PaymentService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ Payment Service ] !";
    }

    @PostMapping(path = "/payment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "Payment",dataType = "Payment", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pay Success")
    })
    public HttpEntity pay(@RequestBody Payment info, @RequestHeader HttpHeaders headers) {
        PaymentController.LOGGER.info("[pay][Pay][PaymentId: {}]", info.getId());
        return ok(service.pay(info, headers));
    }

    @PostMapping(path = "/payment/money")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "Payment",dataType = "Payment", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Add Money Success",response = Money.class)
    })
    public HttpEntity addMoney(@RequestBody Payment info, @RequestHeader HttpHeaders headers) {
        PaymentController.LOGGER.info("[addMoney][Add money][PaymentId: {}]", info.getId());
        return ok(service.addMoney(info, headers));
    }

    @GetMapping(path = "/payment")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Query Success",response = Payment.class,responseContainer = "List")
    })
    public HttpEntity query(@RequestHeader HttpHeaders headers) {
        PaymentController.LOGGER.info("[query][Query payment]");
        return ok(service.query(headers));
    }
}
