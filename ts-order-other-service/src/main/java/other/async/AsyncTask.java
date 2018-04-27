package other.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import other.domain.Order;
import other.domain.QueryOrderResult;
import other.repository.OrderOtherRepository;

import java.util.ArrayList;
import java.util.concurrent.Future;

@Component  
public class AsyncTask {

    @Autowired
    private OrderOtherRepository orderOtherRepository;

    public static int count = 0;

    @Async("myAsync")
    public Future<QueryOrderResult> viewAllOrderAsync(){
        count++;
        System.out.println("[Enter View All Order Async] Count:" + count);
        try{
            Thread.sleep(10000);
            ArrayList<Order> orders = orderOtherRepository.findAll();
            QueryOrderResult result = new QueryOrderResult(true,"Success.",orders);
            count--;
            System.out.println("[Exit View All Order Async] Count:" + count);
            return new AsyncResult<>(result);
        }catch(Exception e){
            count--;
            System.out.println("[Exit View All Order Async] Exception Count:" + count);
            return null;
        }

    }


}  
