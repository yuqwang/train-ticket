package admintravel.service;

import admintravel.async.AsyncTask;
import admintravel.entity.AdminTrip;
import admintravel.entity.TravelInfo;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author fdse
 */
@Service
public class AdminTravelServiceImpl implements AdminTravelService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AsyncTask asyncTask;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminTravelServiceImpl.class);

    @Override
    public Response getAllTravels(HttpHeaders headers) throws InterruptedException, ExecutionException {
        Response<ArrayList<AdminTrip>> result = new Response<>();
        ArrayList<AdminTrip> trips = new ArrayList<>();

        AdminTravelServiceImpl.LOGGER.info("[Get All Travels]");
        /*********************** Fault Reproduction - Error Process Seq *************************/
        // 1. get travels from ts-travel-service
        Future<Response<ArrayList<AdminTrip>>> getTravelsFuture = asyncTask.getTravels(headers);
        // 2. get travel2s from ts-travel2-service
        Future<Response<ArrayList<AdminTrip>>> getTravel2sFuture = asyncTask.getTravel2s(headers);
        boolean travelFlag= false, travel2Flag = false;
        while(!getTravelsFuture.isDone()||!getTravel2sFuture.isDone()){
            // wait for all task done.
            if(!travelFlag && getTravelsFuture.isDone()){
                result = getTravelsFuture.get();
                if (result.getStatus() == 1) {
                    ArrayList<AdminTrip> adminTrips = result.getData();
                    AdminTravelServiceImpl.LOGGER.info("[Get Travel From ts-travel-service successfully!]");
                    trips.addAll(adminTrips);
                } else {
                    AdminTravelServiceImpl.LOGGER.error("[Get Travel From ts-travel-service fail!]");
                }
                travelFlag = true;
            }
            if(!travel2Flag && getTravel2sFuture.isDone()){
                result = getTravel2sFuture.get();
                if (result.getStatus() == 1) {
                    AdminTravelServiceImpl.LOGGER.info("[Get Travel From ts-travel2-service successfully!]");
                    ArrayList<AdminTrip> adminTrips = result.getData();
                    trips.addAll(adminTrips);
                } else {
                    AdminTravelServiceImpl.LOGGER.error("[Get Travel From ts-travel2-service fail!]");
                }
                travel2Flag = true;
            }
        }

        /*
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response<ArrayList<AdminTrip>>> re = restTemplate.exchange(
                "http://ts-travel-service:12346/api/v1/travelservice/admin_trip",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Response<ArrayList<AdminTrip>>>() {
                });
        result = re.getBody();

        if (result.getStatus() == 1) {
            ArrayList<AdminTrip> adminTrips = result.getData();
            AdminTravelServiceImpl.LOGGER.info("[Get Travel From ts-travel-service successfully!]");
            trips.addAll(adminTrips);
        } else {
            AdminTravelServiceImpl.LOGGER.error("[Get Travel From ts-travel-service fail!]");
        }

        HttpEntity requestEntity2 = new HttpEntity(headers);
        ResponseEntity<Response<ArrayList<AdminTrip>>> re2 = restTemplate.exchange(
                "http://ts-travel2-service:16346/api/v1/travel2service/admin_trip",
                HttpMethod.GET,
                requestEntity2,
                new ParameterizedTypeReference<Response<ArrayList<AdminTrip>>>() {
                });
        result = re2.getBody();

        if (result.getStatus() == 1) {
            AdminTravelServiceImpl.LOGGER.info("[Get Travel From ts-travel2-service successfully!]");
            ArrayList<AdminTrip> adminTrips = result.getData();
            trips.addAll(adminTrips);
        } else {
            AdminTravelServiceImpl.LOGGER.error("[Get Travel From ts-travel2-service fail!]");
        }

         */
        result.setData(trips);

        return result;
    }

    @Override
    public Response addTravel(TravelInfo request, HttpHeaders headers) {
        Response result;
        String requestUrl;
        if (request.getTrainTypeId().charAt(0) == 'G' || request.getTrainTypeId().charAt(0) == 'D') {
            requestUrl = "http://ts-travel-service:12346/api/v1/travelservice/trips";
        } else {
            requestUrl = "http://ts-travel2-service:16346/api/v1/travel2service/trips";
        }
        HttpEntity requestEntity = new HttpEntity(request, headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                requestUrl,
                HttpMethod.POST,
                requestEntity,
                Response.class);
        result = re.getBody();

        if (result.getStatus() == 1) {
            AdminTravelServiceImpl.LOGGER.info("Admin add new travel");
            return new Response<>(1, "[Admin add new travel]", null);
        } else {
            AdminTravelServiceImpl.LOGGER.error("Admin add new travel failed, trip id: {}", request.getTripId());
            return new Response<>(0, "Admin add new travel failed", null);
        }
    }

    @Override
    public Response updateTravel(TravelInfo request, HttpHeaders headers) {
        Response result;

        String requestUrl = "";
        if (request.getTrainTypeId().charAt(0) == 'G' || request.getTrainTypeId().charAt(0) == 'D') {
            requestUrl = "http://ts-travel-service:12346/api/v1/travelservice/trips";
        } else {
            requestUrl = "http://ts-travel2-service:16346/api/v1/travel2service/trips";
        }
        HttpEntity requestEntity = new HttpEntity(request, headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                requestUrl,
                HttpMethod.PUT,
                requestEntity,
                Response.class);

        result = re.getBody();
        if (result.getStatus() != 1)  {
            AdminTravelServiceImpl.LOGGER.info("[Admin update travel failed]");
            return new Response<>(0, "Admin update travel failed", null);
        }

        AdminTravelServiceImpl.LOGGER.info("[Admin update travel success]");
        return result;
    }

    @Override
    public Response deleteTravel(String tripId, HttpHeaders headers) {

        Response result;
        String requestUtl = "";
        if (tripId.charAt(0) == 'G' || tripId.charAt(0) == 'D') {
            requestUtl = "http://ts-travel-service:12346/api/v1/travelservice/trips/" + tripId;
        } else {
            requestUtl = "http://ts-travel2-service:16346/api/v1/travel2service/trips/" + tripId;
        }
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response> re = restTemplate.exchange(
                requestUtl,
                HttpMethod.DELETE,
                requestEntity,
                Response.class);

        result = re.getBody();
        if (result.getStatus() != 1) {
            AdminTravelServiceImpl.LOGGER.error("Admin delete travel failed, trip id: {}", tripId);
            return new Response<>(0, "Admin delete travel failed", null);
        }

        AdminTravelServiceImpl.LOGGER.info("Admin delete travel success, trip id: {}", tripId);
        return result;
    }
}
