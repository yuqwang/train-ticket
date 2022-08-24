package collector.mq;

import collector.config.StateQueues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class StateRabbitReceive {

    private static final Logger logger = LoggerFactory.getLogger(StateRabbitReceive.class);

    @Autowired
    private RestTemplate restTemplate;

    @RabbitListener(queues = StateQueues.queueName)
    public void process(String payload) {
        logger.info(payload);
    }
}
