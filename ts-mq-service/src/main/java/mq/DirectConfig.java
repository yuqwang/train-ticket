package mq;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DirectConfig {

    @Bean
    public Queue directQueue(){
        return new Queue("direct",false); //队列名字，是否持久化
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct",false,false);//交换器名称、是否持久化、是否自动删除
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("direct");
    }

}