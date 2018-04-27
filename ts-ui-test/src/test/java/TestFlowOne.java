import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestFlowOne {
    @BeforeClass
    public void setUp() throws Exception {
        //do nothing
    }

    @Test
    public void testLogin()throws Exception{

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity requestEntity = new HttpEntity(null, new HttpHeaders());
        ResponseEntity<Boolean> re = restTemplate.exchange(
                "http://10.141.212.21:30085/cancelOrder/5ad7750b-a68b-49c0-a8c0-32776b067703",
                HttpMethod.GET,
                requestEntity,
                Boolean.class);

        if(re.getStatusCodeValue() == 200){
            throw new Exception("Issue");
        }else{
            Assert.assertEquals(1,0);
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
    }
}
