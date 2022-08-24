package collector.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoverageQueues {

    public final static String queueName = "coverage";

    @Bean
    public Queue coverageQueue() {
        return new Queue(queueName);
    }
}
