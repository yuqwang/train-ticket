package admintravel.async;


import admintravel.entity.AdminTrip;
import admintravel.service.AdminTravelServiceImpl;
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

import java.util.ArrayList;
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
    public Future<Response<ArrayList<AdminTrip>>> getTravels(HttpHeaders headers) throws InterruptedException {
        /*********************** Fault Reproduction - Error Process Seq *************************/
        double op = new Random().nextDouble();
        if(op < 1.0){
            AsyncTask.LOGGER.info("[Admin Travel Service] Delay Process，Wrong Admin Travel Process");
            Thread.sleep(4000);
        } else {
            AsyncTask.LOGGER.info("[Admin Travel Service] Normal Process，Normal Admin Travel Process");
        }
        AsyncTask.LOGGER.info("[Admin Travel Service][Get Travels] Getting....");

        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response<ArrayList<AdminTrip>>> re = restTemplate.exchange(
                "http://ts-travel-service:12346/api/v1/travelservice/admin_trip",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Response<ArrayList<AdminTrip>>>() {
                });
        return new AsyncResult<>(re.getBody());
    }


    @Async("myAsync")
    public Future<Response<ArrayList<AdminTrip>>> getTravel2s(HttpHeaders headers){
        AsyncTask.LOGGER.info("[Admin Travel Service][Get Travel2s] Getting....");
        HttpEntity requestEntity2 = new HttpEntity(headers);
        ResponseEntity<Response<ArrayList<AdminTrip>>> re2 = restTemplate.exchange(
                "http://ts-travel2-service:16346/api/v1/travel2service/admin_trip",
                HttpMethod.GET,
                requestEntity2,
                new ParameterizedTypeReference<Response<ArrayList<AdminTrip>>>() {
                });
        return new AsyncResult<>(re2.getBody());
    }
}