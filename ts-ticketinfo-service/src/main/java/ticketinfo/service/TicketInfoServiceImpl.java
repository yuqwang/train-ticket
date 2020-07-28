package ticketinfo.service;

import com.chuan.methodenhancer.aop.HeaderBuilder;
import edu.fudan.common.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ticketinfo.entity.Travel;

/**
 * Created by Chenjie Xu on 2017/6/6.
 */
@ComponentScan(basePackages = { "com.chuan.methodenhancer.aop" })
@Service
public class TicketInfoServiceImpl implements TicketInfoService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HeaderBuilder headerBuilder;

    @Override
    public Response queryForTravel(Travel info, HttpHeaders headers) {
        HttpEntity requestEntity = new HttpEntity(info, headerBuilder.constructHeader(headers));
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-basic-service:15680/api/v1/basicservice/basic/travel",
                HttpMethod.POST,
                requestEntity,
                Response.class);
        return re.getBody();
    }

    @Override
    public Response queryForStationId(String name, HttpHeaders headers) {
        HttpEntity requestEntity = new HttpEntity(headerBuilder.constructHeader(headers));
        ResponseEntity<Response> re = restTemplate.exchange(
                "http://ts-basic-service:15680/api/v1/basicservice/basic/" + name,
                HttpMethod.GET,
                requestEntity,
                Response.class);

        return re.getBody();
    }
}
