package inside_payment.async;

import java.util.concurrent.Future;

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
    public Future<Boolean> sendAsyncCallToPaymentService(OutsidePaymentInfo outsidePaymentInfo, HttpHeaders headers) throws InterruptedException{
        System.out.println("[Inside Payment Service][Async Task] Begin.");

//        Boolean value = restTemplate.getForObject("http://rest-service-external:16100/greet", Boolean.class);

        HttpEntity orderOtherEntity = new HttpEntity(null,headers);
        ResponseEntity<Boolean> taskUpdateOrder = restTemplate.exchange(
                "http://rest-service-external:16100/greet",
                HttpMethod.GET,
                orderOtherEntity,
                Boolean.class);
        Boolean value = taskUpdateOrder.getBody();

        return new AsyncResult<>(value);
    }
    
}  
