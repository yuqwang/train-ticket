package collector.mq;

import collector.config.StateQueues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class StateRabbitReceive {

    private static final Logger logger = LoggerFactory.getLogger(StateRabbitReceive.class);

    @RabbitListener(queues = StateQueues.queueName)
    public void process(String payload) {
        logger.info(payload);
    }
}
