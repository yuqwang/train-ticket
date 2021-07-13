package cancel.async;

import cancel.entity.Order;
import cancel.entity.OrderStatus;
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

import java.util.Random;
import java.util.concurrent.Future;

import static cancel.service.CancelServiceImpl.getAuthorizationHeadersFrom;

/**
 * @author fdse
 */
@Component
public class AsyncTask {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTask.class);

    @Async("myAsync")
    public Future<Response> cancelFromOrder(Order order, HttpHeaders headers) throws InterruptedException {
        /*********************** Fault Reproduction - Error Process Seq *************************/
        double op = new Random().nextDouble();
        if (op < 1.0) {
            AsyncTask.LOGGER.info("[Cancel Order Service] Delay Process，Wrong Cancel Process");
            Thread.sleep(4000);
        } else {
            AsyncTask.LOGGER.info("[Cancel Order Service] Normal Process，Normal Cancel Process");
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
    public Future<Boolean> drawBackMoney(String money, String userId, HttpHeaders headers) throws InterruptedException {


        AsyncTask.LOGGER.info("[Draw Back Money] Draw back money...");

        HttpHeaders newHeaders = getAuthorizationHeadersFrom(headers);
        HttpEntity requestEntity = new HttpEntity(newHeaders);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-inside-payment-service:18673/api/v1/inside_pay_service/inside_payment/drawback/" + userId
                        + "/" + money,
                HttpMethod.GET,
                requestEntity,
                Response.class);
        Response result = re.getBody();

        return new AsyncResult<>(result.getStatus() == 1);

    }
}
