package plan.async;


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
import plan.entity.TripInfo;
import plan.entity.TripResponse;
import plan.service.RoutePlanServiceImpl;

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
    public Future<ArrayList<TripResponse>> getTripFromHighSpeedTravelService(TripInfo info, HttpHeaders headers) throws InterruptedException {
        /*********************** Fault Reproduction - Error Process Seq *************************/
        double op = new Random().nextDouble();
        if(op < 1.0){
            AsyncTask.LOGGER.error("[Route Plan Service] Delay Process，Wrong Route Plan Process");
            Thread.sleep(4000);
        } else {
            AsyncTask.LOGGER.info("[Route Plan Service] Normal Process，Normal Route Plan Process");
        }
        AsyncTask.LOGGER.info("[Route Plan Service][Get Trip From High Speed Travel Service] Getting....");

        HttpEntity requestEntity = new HttpEntity(info, null);

        ResponseEntity<Response<ArrayList<TripResponse>>> re = restTemplate.exchange(
                "http://ts-travel-service:12346/api/v1/travelservice/trips/left",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Response<ArrayList<TripResponse>>>() {
                });

        ArrayList<TripResponse> tripResponses = re.getBody().getData();
        AsyncTask.LOGGER.info("[Route Plan Get Trip][Size] {}", tripResponses.size());
        return new AsyncResult<>(tripResponses);
    }


    @Async("myAsync")
    public Future<ArrayList<TripResponse>> getTripFromNormalTravelService(TripInfo info, HttpHeaders headers) {
        AsyncTask.LOGGER.info("[Route Plan Service][Get Trip From Normal Travel Service] Getting....");
        HttpEntity requestEntity = new HttpEntity(info, null);

        ResponseEntity<Response<ArrayList<TripResponse>>> re = restTemplate.exchange(
                "http://ts-travel2-service:16346/api/v1/travel2service/trips/left",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Response<ArrayList<TripResponse>>>() {
                });
        ArrayList<TripResponse> list = re.getBody().getData();
        AsyncTask.LOGGER.info("[Route Plan Get TripOther][Size] {}", list.size());
        return new AsyncResult<>(list);
    }

}
