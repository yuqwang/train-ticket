package execute.async;


import edu.fudan.common.util.Response;
import execute.entity.Order;
import execute.serivce.ExecuteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.Future;


/**
 * @author fdse
 */
@Component
public class AsyncTask {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTask.class);

    @Async("myAsync")
    public Future<Response<Order>> getOrderByIdFromOrder(String orderId, HttpHeaders headers) throws InterruptedException {
        /*********************** Fault Reproduction - Error Process Seq *************************/
        double op = new Random().nextDouble();
        if(op < 1.0){
            AsyncTask.LOGGER.info("[Execute Service] Delay Process，Wrong Execute Process");
            Thread.sleep(4000);
        } else {
            AsyncTask.LOGGER.info("[Execute Service] Normal Process，Normal Execute Process");
        }
        AsyncTask.LOGGER.info("[Execute Service][Get Order] Getting....");

        headers = null;
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response<Order>> re = restTemplate.exchange(
                "http://ts-order-service:12031/api/v1/orderservice/order/" + orderId,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Response<Order>>() {
                });

        return new AsyncResult<>(re.getBody());
    }

    @Async("myAsync")
    public Future<Response> executeOrder(String orderId, int status, HttpHeaders headers) {
        AsyncTask.LOGGER.info("[Execute Service][Execute Order] Executing....");
        headers = null;
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-order-service:12031/api/v1/orderservice/order/status/" + orderId + "/" + status,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        return new AsyncResult<>(re.getBody());
    }
}
