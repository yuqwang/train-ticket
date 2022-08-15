package inside_payment.controller;

import edu.fudan.common.util.Response;
import inside_payment.entity.*;
import inside_payment.service.InsidePaymentService;
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
    public HttpEntity pay(@RequestBody PaymentInfo info, @RequestHeader HttpHeaders headers) {
        InsidePaymentController.LOGGER.info("[pay][Inside Payment Service.Pay][Pay for: {}]", info.getOrderId());
//        return ok(service.pay(info, headers));
        Response response =service.pay(info, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/inside_payment/account")
    public HttpEntity createAccount(@RequestBody AccountInfo info, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[createAccount][Create account][accountInfo: {}]", info);
//        return ok(service.createAccount(info, headers));
        Response response =service.createAccount(info, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/inside_payment/{userId}/{money}")
    public HttpEntity addMoney(@PathVariable String userId, @PathVariable
            String money, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[addMoney][add money][userId: {}, money: {}]", userId, money);
//        return ok(service.addMoney(userId, money, headers));
        Response response =service.addMoney(userId, money, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/inside_payment/payment")
    public HttpEntity queryPayment(@RequestHeader HttpHeaders headers) {
        LOGGER.info("[queryPayment][query payment]");
//        return ok(service.queryPayment(headers));
        Response response =service.queryPayment(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/inside_payment/account")
    public HttpEntity queryAccount(@RequestHeader HttpHeaders headers) {
        LOGGER.info("[queryAccount][query account]");
//        return ok(service.queryAccount(headers));
        Response response =service.queryAccount(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/inside_payment/drawback/{userId}/{money}")
    public HttpEntity drawBack(@PathVariable String userId, @PathVariable String money, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[drawBack][draw back payment][userId: {}, money: {}]", userId, money);
//        return ok(service.drawBack(userId, money, headers));
        Response response =service.drawBack(userId, money, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/inside_payment/difference")
    public HttpEntity payDifference(@RequestBody PaymentInfo info, @RequestHeader HttpHeaders headers) {
        LOGGER.info("[payDifference][pay difference]");
//        return ok(service.payDifference(info, headers));
        Response response =service.payDifference(info, headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/inside_payment/money")
    public HttpEntity queryAddMoney(@RequestHeader HttpHeaders headers) {
        LOGGER.info("[queryAddMoney][query add money]");
//        return ok(service.queryAddMoney(headers));
        Response response =service.queryAddMoney(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

}
