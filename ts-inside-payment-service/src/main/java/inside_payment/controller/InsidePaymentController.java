package inside_payment.controller;

import inside_payment.entity.*;
import inside_payment.service.InsidePaymentService;
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
@RequestMapping("/api/v1/inside_pay_service")
public class InsidePaymentController {

    @Autowired
    public InsidePaymentService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(InsidePaymentController.class);

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ InsidePayment Service ] !";
    }

    @PostMapping(value = "/inside_payment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "PaymentInfo",dataType = "PaymentInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "Error. Order status Not allowed to Pay."),
            @ApiResponse(code = 200, message = "Payment Success")
    })
    public HttpEntity pay(@RequestBody PaymentInfo info, @RequestHeader HttpHeaders headers) {
        InsidePaymentController.LOGGER.info("[pay][Inside Payment Service.Pay][Pay for: {}]", info.getOrderId());
        return ok(service.pay(info, headers));
    }

    @PostMapping(value = "/inside_payment/account")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "AccountInfo",dataType = "AccountInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Create Account Success")
    })
    public HttpEntity createAccount(@RequestBody AccountInfo info, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[createAccount][Create account][accountInfo: {}]", info);
        return ok(service.createAccount(info, headers));
    }

    @GetMapping(value = "/inside_payment/{userId}/{money}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId",dataType = "String", paramType = "path",required = true),
            @ApiImplicitParam(name = "money", value = "money",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Add Money Success",response = String.class)
    })
    public HttpEntity addMoney(@PathVariable String userId, @PathVariable
            String money, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[addMoney][add money][userId: {}, money: {}]", userId, money);
        return ok(service.addMoney(userId, money, headers));
    }

    @GetMapping(value = "/inside_payment/payment")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Query Payment Success",response = Payment.class,responseContainer = "List")
    })
    public HttpEntity queryPayment(@RequestHeader HttpHeaders headers) {
        LOGGER.info("[queryPayment][query payment]");
        return ok(service.queryPayment(headers));
    }

    @GetMapping(value = "/inside_payment/account")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success",response = Balance.class,responseContainer = "List")
    })
    public HttpEntity queryAccount(@RequestHeader HttpHeaders headers) {
        LOGGER.info("[queryAccount][query account]");
        return ok(service.queryAccount(headers));
    }

    @GetMapping(value = "/inside_payment/drawback/{userId}/{money}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId",dataType = "String", paramType = "path",required = true),
            @ApiImplicitParam(name = "money", value = "money",dataType = "String", paramType = "path",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Draw Back Money Success")
    })
    public HttpEntity drawBack(@PathVariable String userId, @PathVariable String money, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[drawBack][draw back payment][userId: {}, money: {}]", userId, money);
        return ok(service.drawBack(userId, money, headers));
    }

    @PostMapping(value = "/inside_payment/difference")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "PaymentInfo",dataType = "PaymentInfo", paramType = "body",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pay Difference Success")
    })
    public HttpEntity payDifference(@RequestBody PaymentInfo info, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[payDifference][pay difference]");
        return ok(service.payDifference(info, headers));
    }

    @GetMapping(value = "/inside_payment/money")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Query Money Success")
    })
    public HttpEntity queryAddMoney(@RequestHeader HttpHeaders headers) {
        LOGGER.info("[queryAddMoney][query add money]");
        return ok(service.queryAddMoney(headers));
    }

}
