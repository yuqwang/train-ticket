package edu.fudan.common.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CovQueues {

    public final static String queueName = "coverage";

    @Bean
    public Queue covQueue() {
        return new Queue(queueName);
    }
}
