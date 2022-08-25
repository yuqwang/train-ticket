package collector.mq;

import collector.config.CoverageQueues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Component
public class CoverageRabbitReceive {

    private static final Logger logger = LoggerFactory.getLogger(CoverageRabbitReceive.class);

    @RabbitListener(queues = CoverageQueues.queueName)
    public void process(String payload) {
        logger.info(payload);
    }
}
