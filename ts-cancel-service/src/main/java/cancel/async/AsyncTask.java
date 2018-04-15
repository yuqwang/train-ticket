package cancel.async;

import java.util.Random;
import java.util.concurrent.Future;
import cancel.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component  
public class AsyncTask {
    
    @Autowired
	private RestTemplate restTemplate;


    @Async("myAsync")
    public Future<ChangeOrderResult> updateOrderStatusToCancel(ChangeOrderInfo info, HttpHeaders headers) throws InterruptedException{
        Thread.sleep(3000);
        System.out.println("[Cancel Order Service][Change High-Speed-Order Status]");
        HttpEntity requestEntity = new HttpEntity(info, headers);
        ResponseEntity<ChangeOrderResult> re = restTemplate.exchange(
                "http://ts-order-service:12031/order/update",
                HttpMethod.POST,
                requestEntity,
                ChangeOrderResult.class);
        return new AsyncResult<>(re.getBody());
    }

    @Async("myAsync")
    public Future<ChangeOrderResult> updateOrderStatusToCancelV2(AsyncSendToCancelOrderInfo info, HttpHeaders headers) throws InterruptedException{
        Thread.sleep(3000);
        System.out.println("[Cancel Order Service][Change High-Speed-Order Status]");
        HttpEntity requestEntity = new HttpEntity(info, headers);
        ResponseEntity<ChangeOrderResult> re = restTemplate.exchange(
                "http://ts-order-service:12031/order/cancelOrder",
                HttpMethod.POST,
                requestEntity,
                ChangeOrderResult.class);
        System.out.println("ts-order-service返回");
        return new AsyncResult<>(re.getBody());
    }

    @Async("myAsync")
    public Future<ChangeOrderResult> updateOtherOrderStatusToCancel(ChangeOrderInfo info, HttpHeaders headers) throws InterruptedException{
        Thread.sleep(3000);
        System.out.println("[Cancel Order Service][Change Normal-Order Status]");
        HttpEntity requestEntity = new HttpEntity(info, headers);
        ResponseEntity<ChangeOrderResult> re = restTemplate.exchange(
                "http://ts-order-other-service:12032/orderOther/update",
                HttpMethod.POST,
                requestEntity,
                ChangeOrderResult.class);
        return new AsyncResult<>(re.getBody());
    }

    @Async("myAsync")
    public Future<ChangeOrderResult> updateOtherOrderStatusToCancelV2(AsyncSendToCancelOrderInfo info, HttpHeaders headers) throws InterruptedException{
        Thread.sleep(3000);
        System.out.println("[Cancel Order Service][Change Normal-Order Status]");
        HttpEntity requestEntity = new HttpEntity(info, headers);
        ResponseEntity<ChangeOrderResult> re = restTemplate.exchange(
                "http://ts-order-other-service:12032/orderOther/cancelOrder",
                HttpMethod.POST,
                requestEntity,
                ChangeOrderResult.class);
        System.out.println("ts-order-other-service返回");
        return new AsyncResult<>(re.getBody());
    }

    @Async("mySimpleAsync")
    public Future<Boolean> drawBackMoneyForOrderCancel(String money, String userId,String orderId, String loginToken, HttpHeaders headers) throws InterruptedException{
        double op = new Random().nextDouble();
        if(op < 0.9999){
            System.out.println("[Cancel Order Service] Delay Process，Wrong Cancel Process");
            Thread.sleep(8000);
        } else {
            System.out.println("[Cancel Order Service] Normal Process，Normal Cancel Process");
        }

        DrawbackAndCancel info = new DrawbackAndCancel(userId,money,orderId,loginToken);
        HttpEntity requestEntity = new HttpEntity(info, headers);
        ResponseEntity<String> re = restTemplate.exchange(
                "http://ts-inside-payment-service:18673/inside_payment/drawBackAndCancel",
                HttpMethod.POST,
                requestEntity,
                String.class);
        String result = re.getBody();
        System.out.println("ts-inside-payment-service返回");
        return new AsyncResult<>(result.equals("true"));
    }


}  
