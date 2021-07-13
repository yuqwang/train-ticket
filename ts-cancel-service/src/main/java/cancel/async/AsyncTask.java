package cancel.async;

import cancel.entity.*;
import cancel.service.CancelServiceImpl;
import edu.fudan.common.util.Response;
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

import java.util.concurrent.Future;

import static cancel.service.CancelServiceImpl.getAuthorizationHeadersFrom;


/** 
 * Asynchronous Tasks 
 * @author Xu 
 * 
 */  
@Component
public class AsyncTask {
    
    @Autowired
	private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTask.class);

    @Async("myAsync")
    public Future<Response> cancelling(Order order, HttpHeaders headers) throws InterruptedException{

        if(Math.random() < 0.6){
            Thread.sleep(4000);
        }

        AsyncTask.LOGGER.info("[Change Order Status] Changing...");
        order.setStatus(OrderStatus.CANCEL.getCode());
        // add authorization header
        HttpHeaders newHeaders = getAuthorizationHeadersFrom(headers);
        HttpEntity requestEntity = new HttpEntity(order, newHeaders);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-order-service:12031/api/v1/orderservice/order",
                HttpMethod.PUT,
                requestEntity,
                Response.class);

        ;
        return new AsyncResult<>(re.getBody());
    }


    @Async("myAsync")
    public Future<Boolean> drawBackMoney(String money, String userId, HttpHeaders headers) throws InterruptedException{
        Thread.sleep(2000);
        //3.执行退款

        AsyncTask.LOGGER.info("[Draw Back Money] Draw back money...");

        HttpHeaders newHeaders = getAuthorizationHeadersFrom(headers);
        HttpEntity requestEntity = new HttpEntity(newHeaders);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-inside-payment-service:18673/api/v1/inside_pay_service/inside_payment/drawback/" + userId + "/" + money,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        Response result = re.getBody();

        return new AsyncResult<>(result.getStatus() == 1);
    }
      
}  
