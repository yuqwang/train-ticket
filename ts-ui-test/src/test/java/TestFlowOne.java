import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Created by ZDH on 2017/7/19.
 */
public class TestFlowOne {

    @BeforeClass
    public void setUp() {
    }
    @Test
    public void testLogin() {

        RestTemplate restTemplate = new RestTemplate();
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setStartingPlace("Shang Hai");
        queryInfo.setEndPlace("Su Zhou");
        queryInfo.setDepartureTime(new Date("2018-04-28"));

        try{
            restTemplate.postForObject("",queryInfo,ArrayList<TripResponse>)
        }

    }

    @AfterClass
    public void tearDown() {

    }
}
