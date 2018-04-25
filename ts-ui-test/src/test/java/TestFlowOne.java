import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by ZDH on 2017/7/19.
 */
public class TestFlowOne {
    @BeforeClass
    public void setUp() throws Exception {
        //do nothing
    }

    @Test
    public void testLogin()throws Exception{

        RestTemplate restTemplate = new RestTemplate();
        //然后向AdminOrderService发送请求，给与权限
        HttpEntity requestEntity = new HttpEntity(null, new HttpHeaders());
        ResponseEntity<Boolean> re = restTemplate.exchange(
                "http://10.141.212.21:30085/cancelOrder/5ad7750b-a68b-49c0-a8c0-32776b067703",
                HttpMethod.GET,
                requestEntity,
                Boolean.class);

        if(re.getStatusCodeValue() == 200){
            //这是抛出OOM的情况
        }else{
            //这是service被kill的情况
        }








    }






    @AfterClass
    public void tearDown() throws Exception {
    }
}
