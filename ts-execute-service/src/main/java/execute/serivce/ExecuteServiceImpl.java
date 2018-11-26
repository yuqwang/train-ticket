package execute.serivce;

import execute.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class ExecuteServiceImpl implements ExecuteService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public TicketExecuteResult ticketExecute(TicketExecuteInfo info, HttpHeaders headers) {
        //1.获取订单信息
        GetOrderByIdInfo getOrderByIdInfo = new GetOrderByIdInfo();
        getOrderByIdInfo.setOrderId(info.getOrderId());

        ModifyOrderStatusInfo executeInfo1 = new ModifyOrderStatusInfo();
        executeInfo1.setOrderId(info.getOrderId());
        executeInfo1.setStatus(OrderStatus.USED.getCode());

        // seq fault
        List<GetOrderResult> orderResults = new ArrayList<>();
        List<ModifyOrderStatusResult> orderStatusResults = new ArrayList<>();
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        getOrderByIdFromOrder(getOrderByIdInfo, headers, orderResults, futures);
        executeOrder(executeInfo1, headers, orderResults, orderStatusResults, futures);

        futures.forEach(x -> x.join());

        GetOrderResult resultFromOrder = orderResults.get(0);
        ModifyOrderStatusResult resultExecute1 = orderStatusResults.get(0);

        TicketExecuteResult result = new TicketExecuteResult();
        Order order;
        if (resultFromOrder.isStatus() == true) {
            order = resultFromOrder.getOrder();
            //2.检查订单是否可以进站
            if (order.getStatus() != OrderStatus.COLLECTED.getCode()) {
                result.setStatus(false);
                result.setMessage("Order Status Wrong");
                return result;
            }
            //3.确认进站 请求修改订单信息

            if (resultExecute1.isStatus() == true) {
                result.setStatus(true);
                result.setMessage("Success.");
                return result;
            } else {
                result.setStatus(false);
                result.setMessage(resultExecute1.getMessage());
                return result;
            }
        } else {
            resultFromOrder = getOrderByIdFromOrderOther(getOrderByIdInfo, headers);
            if (resultFromOrder.isStatus() == true) {
                order = resultFromOrder.getOrder();
                //2.检查订单是否可以进站
                if (order.getStatus() != OrderStatus.COLLECTED.getCode()) {
                    result.setStatus(false);
                    result.setMessage("Order Status Wrong");
                    return result;
                }
                //3.确认进站 请求修改订单信息
                ModifyOrderStatusInfo executeInfo = new ModifyOrderStatusInfo();
                executeInfo.setOrderId(info.getOrderId());
                executeInfo.setStatus(OrderStatus.USED.getCode());
                ModifyOrderStatusResult resultExecute = executeOrderOther(executeInfo, headers);
                if (resultExecute.isStatus() == true) {
                    result.setStatus(true);
                    result.setMessage("Success.");
                    return result;
                } else {
                    result.setStatus(false);
                    result.setMessage(resultExecute.getMessage());
                    return result;
                }
            } else {
                result.setStatus(false);
                result.setMessage("Order Not Found");
                return result;
            }
        }
    }

    @Override
    public TicketExecuteResult ticketCollect(TicketExecuteInfo info, HttpHeaders headers) {
        //1.获取订单信息
        GetOrderByIdInfo getOrderByIdInfo = new GetOrderByIdInfo();
        getOrderByIdInfo.setOrderId(info.getOrderId());
        GetOrderResult resultFromOrder = getOrderByIdFromOrder(getOrderByIdInfo, headers);
        TicketExecuteResult result = new TicketExecuteResult();
        Order order;
        if (resultFromOrder.isStatus() == true) {
            order = resultFromOrder.getOrder();
            //2.检查订单是否可以进站
            if (order.getStatus() != OrderStatus.PAID.getCode()) {
                result.setStatus(false);
                result.setMessage("Order Status Wrong");
                return result;
            }
            //3.确认进站 请求修改订单信息
            ModifyOrderStatusInfo executeInfo = new ModifyOrderStatusInfo();
            executeInfo.setOrderId(info.getOrderId());
            executeInfo.setStatus(OrderStatus.COLLECTED.getCode());
            ModifyOrderStatusResult resultExecute = executeOrder(executeInfo, headers);
            if (resultExecute.isStatus() == true) {
                result.setStatus(true);
                result.setMessage("Success.");
                return result;
            } else {
                result.setStatus(false);
                result.setMessage(resultExecute.getMessage());
                return result;
            }
        } else {
            resultFromOrder = getOrderByIdFromOrderOther(getOrderByIdInfo, headers);
            if (resultFromOrder.isStatus() == true) {
                order = resultFromOrder.getOrder();
                //2.检查订单是否可以进站
                if (order.getStatus() != OrderStatus.PAID.getCode()) {
                    result.setStatus(false);
                    result.setMessage("Order Status Wrong");
                    return result;
                }
                //3.确认进站 请求修改订单信息
                ModifyOrderStatusInfo executeInfo = new ModifyOrderStatusInfo();
                executeInfo.setOrderId(info.getOrderId());
                executeInfo.setStatus(OrderStatus.COLLECTED.getCode());
                ModifyOrderStatusResult resultExecute = executeOrderOther(executeInfo, headers);
                if (resultExecute.isStatus() == true) {
                    result.setStatus(true);
                    result.setMessage("Success.");
                    return result;
                } else {
                    result.setStatus(false);
                    result.setMessage(resultExecute.getMessage());
                    return result;
                }
            } else {
                result.setStatus(false);
                result.setMessage("Order Not Found");
                return result;
            }
        }
    }


    private void executeOrder(ModifyOrderStatusInfo info, HttpHeaders headers,
                              List<GetOrderResult> orderResults, List<ModifyOrderStatusResult>
                                      orderStatusResults, List<CompletableFuture<Void>> futures) {

        System.out.println("[Execute Service][Execute Order] Executing....");
        System.out.println(orderResults.get(0));
        HttpEntity<ModifyOrderStatusInfo> requestEntity = new HttpEntity<>(info, headers);
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ResponseEntity<ModifyOrderStatusResult> re = restTemplate.exchange(
                    "http://ts-order-service:12031/order/modifyOrderStatus",
                    HttpMethod.POST,
                    requestEntity,
                    ModifyOrderStatusResult.class);
            return re.getBody();
        }).thenAccept(orderStatusResults::add);

        futures.add(future);
    }

    private ModifyOrderStatusResult executeOrder(ModifyOrderStatusInfo info, HttpHeaders headers) {

        System.out.println("[Execute Service][Execute Order] Executing....");
        HttpEntity requestEntity = new HttpEntity(info, headers);
        ResponseEntity<ModifyOrderStatusResult> re = restTemplate.exchange(
                "http://ts-order-service:12031/order/modifyOrderStatus",
                HttpMethod.POST,
                requestEntity,
                ModifyOrderStatusResult.class);
        ModifyOrderStatusResult cor = re.getBody();
//        ModifyOrderStatusResult cor = restTemplate.postForObject(
//                "http://ts-order-service:12031/order/modifyOrderStatus"
//                ,info,ModifyOrderStatusResult.class);
        return cor;
    }


    private ModifyOrderStatusResult executeOrderOther(ModifyOrderStatusInfo info, HttpHeaders headers) {
        System.out.println("[Execute Service][Execute Order] Executing....");
        HttpEntity requestEntity = new HttpEntity(info, headers);
        ResponseEntity<ModifyOrderStatusResult> re = restTemplate.exchange(
                "http://ts-order-other-service:12032/order/modifyOrderStatus",
                HttpMethod.POST,
                requestEntity,
                ModifyOrderStatusResult.class);
        ModifyOrderStatusResult cor = re.getBody();
//        ModifyOrderStatusResult cor = restTemplate.postForObject(
//                "http://ts-order-other-service:12032/order/modifyOrderStatus"
//                ,info,ModifyOrderStatusResult.class);
        return cor;
    }

    private void getOrderByIdFromOrder(GetOrderByIdInfo info, HttpHeaders headers, List<GetOrderResult>
            orderResults, List<CompletableFuture<Void>> futures) {
        System.out.println("[Execute Service][Get Order] Getting....");
        HttpEntity<GetOrderByIdInfo> requestEntity = new HttpEntity<>(info, headers);

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ResponseEntity<GetOrderResult> re = restTemplate.exchange(
                    "http://ts-order-service:12031/order/getById/",
                    HttpMethod.POST,
                    requestEntity,
                    GetOrderResult.class);
            return re.getBody();
        }).thenAccept(orderResults::add);

        futures.add(future);
    }

    private GetOrderResult getOrderByIdFromOrder(GetOrderByIdInfo info, HttpHeaders headers) {
        System.out.println("[Execute Service][Get Order] Getting....");
        HttpEntity requestEntity = new HttpEntity(info, headers);
        ResponseEntity<GetOrderResult> re = restTemplate.exchange(
                "http://ts-order-service:12031/order/getById/",
                HttpMethod.POST,
                requestEntity,
                GetOrderResult.class);
        GetOrderResult cor = re.getBody();
//        GetOrderResult cor = restTemplate.postForObject(
//                "http://ts-order-service:12031/order/getById/"
//                ,info,GetOrderResult.class);
        return cor;
    }

    private GetOrderResult getOrderByIdFromOrderOther(GetOrderByIdInfo info, HttpHeaders headers) {
        System.out.println("[Execute Service][Get Order] Getting....");
        HttpEntity requestEntity = new HttpEntity(info, headers);
        ResponseEntity<GetOrderResult> re = restTemplate.exchange(
                "http://ts-order-other-service:12032/orderOther/getById/",
                HttpMethod.POST,
                requestEntity,
                GetOrderResult.class);
        GetOrderResult cor = re.getBody();
//        GetOrderResult cor = restTemplate.postForObject(
//                "http://ts-order-other-service:12032/orderOther/getById/"
//                ,info,GetOrderResult.class);
        return cor;
    }

}
