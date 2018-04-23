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

        //注意把这里换成你的集群的ip
        CancelOrderResult result = restTemplate.getForObject(
                "http://10.141.212.21:30085/cancelOrder/5ad7750b-a68b-49c0-a8c0-32776b067703",
                CancelOrderResult.class);

        System.out.println("[Message]" + result.getMessage());
        System.out.println("[Status]" + result.isStatus());

        //错误返回：
        // {
        //    "status": false,
        //    "message": "[Error Process Seq]"
        //}
        //正常返回：
        //{
        //    "status": true,
        //    "message": "Success.Processes Seq."
        //}
        //其他返回均不合法

        Assert.assertEquals(result.isStatus() && result.getMessage().contains("Success.Processes Seq"),true);
    }


    @AfterClass
    public void tearDown() throws Exception {
    }
}
