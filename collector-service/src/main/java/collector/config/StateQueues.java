package collector.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StateQueues {

    public final static String queueName = "state";

    @Bean
    public Queue stateQueue() {
        return new Queue(queueName);
    }
}
