package cancel.async;

import java.util.Random;
import java.util.concurrent.Future;
import cancel.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component  
public class AsyncTask {
    
    @Autowired
	private RestTemplate restTemplate;

    @Async("myAsync")
    public Future<ChangeOrderResult> updateOtherOrderStatusToCancel(ChangeOrderInfo info, HttpHeaders headers) throws InterruptedException{

        Thread.sleep(3000);

        System.out.println("[Cancel Order Service][Change Order Status]");
//        ChangeOrderResult result = restTemplate.postForObject(
//                "http://ts-order-other-service:12032/orderOther/update",info,ChangeOrderResult.class);



        HttpEntity requestEntity = new HttpEntity(info, headers);
        headers.add("Cookie","jichao=dododo");
        ResponseEntity<ChangeOrderResult> re = restTemplate.exchange(
                "http://ts-order-other-service:12032/orderOther/update",
                HttpMethod.POST,
                requestEntity,
                ChangeOrderResult.class);
        ChangeOrderResult result = re.getBody();


        return new AsyncResult<>(result);

    }

    @Async("mySimpleAsync")
    public Future<Boolean> drawBackMoneyForOrderCancel(String money, String userId,String orderId, String loginToken, HttpHeaders headers) throws InterruptedException{

        /*********************** Fault Reproduction - Error Process Seq *************************/
        double op = new Random().nextDouble();
        if(op < 0.999){
            System.out.println("[Cancel Order Service] Delay Process，Wrong Cancel Process");
            Thread.sleep(8000);
        } else {
            System.out.println("[Cancel Order Service] Normal Process，Normal Cancel Process");
        }




//        //1.Search Order Info
//        System.out.println("[Cancel Order Service][Get Order] Getting....");
//        GetOrderByIdInfo getOrderInfo = new GetOrderByIdInfo();
//        getOrderInfo.setOrderId(orderId);
//        GetOrderResult cor = restTemplate.postForObject(
//                "http://ts-order-other-service:12032/orderOther/getById/"
//                ,getOrderInfo,GetOrderResult.class);
//        Order order = cor.getOrder();
//        //2.Change order status to cancelling
//        order.setStatus(OrderStatus.Canceling.getCode());
//        ChangeOrderInfo changeOrderInfo = new ChangeOrderInfo();
//        changeOrderInfo.setOrder(order);
//        changeOrderInfo.setLoginToken(loginToken);
//        ChangeOrderResult changeOrderResult = restTemplate.postForObject("http://ts-order-other-service:12032/orderOther/update",changeOrderInfo,ChangeOrderResult.class);
//        if(changeOrderResult.isStatus() == false){
//            System.out.println("[Cancel Order Service]Unexpected error");
//        }
//        //3.do drawback money
////        System.out.println("[Cancel Order Service][Draw Back Money] Draw back money...");
////        DrawBackInfo info = new DrawBackInfo();
////        info.setMoney(money);
////        info.setUserId(userId);
////        String result = restTemplate.postForObject("http://ts-inside-payment-service:18673/inside_payment/drawBack",info,String.class);
////        if(result.equals("true")){
////            return new AsyncResult<>(true);
////        }else{
////            return new AsyncResult<>(false);
////        }



        DrawbackAndCancel info = new DrawbackAndCancel(userId,money,orderId,loginToken);
        //3.do drawback money
//        System.out.println("[Cancel Order Service][Draw Back Money] Draw back money...");
//        String result = restTemplate.postForObject(
//                "http://ts-inside-payment-service:18673/inside_payment/drawBackAndCancel",info,String.class);

        HttpEntity requestEntity = new HttpEntity(info, headers);
        headers.add("Cookie","jichao=dododo");
        ResponseEntity<String> re = restTemplate.exchange(
                "http://ts-inside-payment-service:18673/inside_payment/drawBackAndCancel",
                HttpMethod.POST,
                requestEntity,
                String.class);
        String result = re.getBody();

        if(result.equals("true")){
            return new AsyncResult<>(true);
        }else{
            return new AsyncResult<>(false);
        }
        /*****************************************************************************/
    }
}  
