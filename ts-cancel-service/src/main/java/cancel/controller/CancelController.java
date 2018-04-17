package cancel.controller;

import cancel.async.AsyncTask;
import cancel.domain.CalculateRefundResult;
import cancel.domain.CancelOrderInfo;
import cancel.domain.CancelOrderResult;
import cancel.domain.VerifyResult;
import cancel.service.CancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.Future;

@RestController
public class CancelController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    CancelService cancelService;

    @Autowired
    private AsyncTask asyncTask;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/welcome", method = RequestMethod.GET)
    public String calculate(@RequestHeader HttpHeaders headers) throws Exception{
        headers.add("Cookie","jichao=dododo");
        Future<String> task1 = asyncTask.helloOrderService(headers);
        Future<String> task2 = asyncTask.helloOrderOtherService(headers);
        Future<String> task3 = asyncTask.helloInsidePaymentService(headers);
        while(!(task1.isDone() && task2.isDone() && task3.isDone())){
            //wait until all do
        }
        return "complete";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/cancelCalculateRefund", method = RequestMethod.POST)
    public CalculateRefundResult calculate(@RequestBody CancelOrderInfo info, @RequestHeader HttpHeaders headers){
        System.out.println("[Cancel Order Service][Calculate Cancel Refund] OrderId:" + info.getOrderId());
        return cancelService.calculateRefund(info, headers);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/cancelOrder", method = RequestMethod.POST)
    public CancelOrderResult cancelTicket(@RequestBody CancelOrderInfo info, @RequestHeader HttpHeaders headers) throws Exception{
        String loginToken = "admin";
        String loginId = "4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f";
        return cancelService.cancelOrderVersion2(info,loginToken,loginId,headers);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/cancelOrder/{orderId}", method = RequestMethod.GET)
    public CancelOrderResult cancelTicketDoGet(@PathVariable String orderId, @RequestHeader HttpHeaders headers) throws Exception{
        String loginToken = "admin";
        String loginId = "4d2a46c7-71cb-4cf1-b5bb-b68406d9da6f";

        CancelOrderInfo info = new CancelOrderInfo();
        info.setOrderId(orderId);

        return cancelService.cancelOrderVersion2(info,loginToken,loginId,headers);
    }
}
