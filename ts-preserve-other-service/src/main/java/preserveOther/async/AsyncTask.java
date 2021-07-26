package preserveOther.async;


import edu.fudan.common.util.Response;
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
import preserveOther.entity.Contacts;
import preserveOther.service.PreserveOtherServiceImpl;

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
    public Future<Response> checkSecurity(String accountId, HttpHeaders httpHeaders) throws InterruptedException{
        /*********************** Fault Reproduction - Error Process Seq *************************/
        double op = new Random().nextDouble();
        if(op < 1.0){
            AsyncTask.LOGGER.error("[Preserve Other Service] Delay Process，Wrong Preserve Other Process");
            Thread.sleep(4000);
        } else {
            AsyncTask.LOGGER.info("[Preserve Other Service] Normal Process，Normal Preserve Other Process");
        }
        AsyncTask.LOGGER.info("[Preserve Other Service][Check Account Security] Checking....");

        HttpEntity requestCheckResult = new HttpEntity(httpHeaders);
        ResponseEntity<Response> reCheckResult = restTemplate.exchange(
                "http://ts-security-service:11188/api/v1/securityservice/securityConfigs/" + accountId,
                HttpMethod.GET,
                requestCheckResult,
                Response.class);

        return new AsyncResult<>(reCheckResult.getBody());
    }

    @Async("myAsync")
    public Future<Response<Contacts>> getContactsById(String contactsId, HttpHeaders httpHeaders) {
        AsyncTask.LOGGER.info("[Preserve Other Service][Get Contacts By Id is] Getting....");

        HttpEntity requestGetContactsResult = new HttpEntity(httpHeaders);
        ResponseEntity<Response<Contacts>> reGetContactsResult = restTemplate.exchange(
                "http://ts-contacts-service:12347/api/v1/contactservice/contacts/" + contactsId,
                HttpMethod.GET,
                requestGetContactsResult,
                new ParameterizedTypeReference<Response<Contacts>>() {
                });

        return new AsyncResult<>(reGetContactsResult.getBody());
    }
}