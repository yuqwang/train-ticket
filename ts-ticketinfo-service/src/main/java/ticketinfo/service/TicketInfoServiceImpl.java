package ticketinfo.service;

import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ticketinfo.entity.Travel;

import java.util.ArrayList;

/**
 * Created by Chenjie Xu on 2017/6/6.
 */
@Service
public class TicketInfoServiceImpl implements TicketInfoService {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketInfoServiceImpl.class);

//    申请内存引发OOM
    void anomaly(){
        LOGGER.info("start allocate");
        ArrayList<byte[]> list =new ArrayList<>(500);
        for (int i = 0; i < 200; i++) {
            list.add(new byte[1024*1024]);
        }
    }

    @Override
    public Response queryForTravel(Travel info, HttpHeaders headers) {
        anomaly();

        HttpEntity requestEntity = new HttpEntity(info, null);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-basic-service:15680/api/v1/basicservice/basic/travel",
                HttpMethod.POST,
                requestEntity,
                Response.class);
        return re.getBody();
    }

    @Override
    public Response queryForStationId(String name, HttpHeaders headers) {
        anomaly();

        HttpEntity requestEntity = new HttpEntity(null);
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-basic-service:15680/api/v1/basicservice/basic/" + name,
                HttpMethod.GET,
                requestEntity,
                Response.class);

        return re.getBody();
    }
}
