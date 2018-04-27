package inside_payment.async;

import java.util.concurrent.Future;

import inside_payment.domain.ChangeOrderInfo;
import inside_payment.domain.ChangeOrderResult;
import inside_payment.domain.OutsidePaymentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());  
    
    @Autowired
	private RestTemplate restTemplate;

    @Async("mySimpleAsync")
    public Future<Boolean> sendAsyncCallToPaymentService(OutsidePaymentInfo outsidePaymentInfo) throws InterruptedException{
        System.out.println("[Inside Payment Service][Async Task] Begin.");
        Boolean value = restTemplate.getForObject("http://rest-service-external:16100/greet", Boolean.class);
        return new AsyncResult<>(value);
    }


    @Async("mySimpleAsync")
    public Future<ChangeOrderResult> sendAsyncCallToChangeOrder(String orderId, HttpHeaders httpHeaders){
        HttpEntity cancelOrderEntity = new HttpEntity(null,httpHeaders);
        ResponseEntity<ChangeOrderResult> taskCancelOrder = restTemplate.exchange(
                "http://ts-order-other-service:12032/orderOther/cancelling/" + orderId,
                HttpMethod.GET,
                cancelOrderEntity,
                ChangeOrderResult.class);
        ChangeOrderResult changeOrderResult = taskCancelOrder.getBody();
        return new AsyncResult<>(changeOrderResult);
    }

//    @Async("mySimpleAsync")
//    public Future<ChangeOrderResult> sendAsyncCallToChangeConsign(ChangeOrderInfo changeOrderInfo, HttpHeaders httpHeaders){
//        HttpEntity cancelOrderEntity = new HttpEntity(changeOrderInfo,httpHeaders);
//        ResponseEntity<ChangeOrderResult> taskCancelOrder = restTemplate.exchange(
//                "http://ts-order-other-service:12032/orderOther/update",
//                HttpMethod.POST,
//                cancelOrderEntity,
//                ChangeOrderResult.class);
//        ChangeOrderResult changeOrderResult = taskCancelOrder.getBody();
//        return new AsyncResult<>(changeOrderResult);
//    }


    @Async("mySimpleAsync")
    public Future<Boolean> sendAsyncCallConsignDrawback(String orderId,HttpHeaders httpHeaders){
        HttpEntity cancelOrderEntity = new HttpEntity(null,httpHeaders);
        ResponseEntity<Boolean> taskCancelOrder = restTemplate.exchange(
                "http://ts-consign-service:16111/consign/drawback/" + orderId,
                HttpMethod.GET,
                cancelOrderEntity,
                Boolean.class);
        boolean result = taskCancelOrder.getBody();
        return new AsyncResult<>(result);
    }

    @Async("mySimpleAsync")
    public Future<String> checkConsignPriceService(HttpHeaders httpHeaders){
        System.out.println("checkConsignPriceService");
        HttpEntity cancelOrderEntity = new HttpEntity(null,httpHeaders);
        ResponseEntity<String> taskCheckConsignPrice = restTemplate.exchange(
                "http://ts-consign-price-service:16110/consignPrice/welcome",
                HttpMethod.GET,
                cancelOrderEntity,
                String.class);
        return new AsyncResult<>(taskCheckConsignPrice.getBody());
    }
}  
