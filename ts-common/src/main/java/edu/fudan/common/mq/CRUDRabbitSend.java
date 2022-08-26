package edu.fudan.common.mq;


import edu.fudan.common.config.CRUDQueues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CRUDRabbitSend {

    @Autowired
    private AmqpTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CRUDRabbitSend.class);

    public void send(String val) {
        logger.info("send info to mq:" + val);
        this.rabbitTemplate.convertAndSend(CRUDQueues.queueName, val);
    }

}
