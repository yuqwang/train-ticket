package mq;

 import org.springframework.amqp.rabbit.annotation.RabbitHandler;
 import org.springframework.amqp.rabbit.annotation.RabbitListener;
 import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "direct")
public class Receiver {

    @RabbitHandler
    public void handler(String message){
        System.out.println("接收消息："+message);
    }

}
