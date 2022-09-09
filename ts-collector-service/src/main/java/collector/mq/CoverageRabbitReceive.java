package collector.mq;

import collector.client.MessageClient;
import collector.config.CoverageQueues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class CoverageRabbitReceive {

    private static final Logger logger = LoggerFactory.getLogger(CoverageRabbitReceive.class);
    @Value("${grpc.host}")
    private String host;
    @Value("${grpc.port}")
    private int port;
    private MessageClient client;

    @RabbitListener(queues = CoverageQueues.queueName)
    public void process(String payload) {
        client.uploadCoverage(payload);
//        client.shutdown();
    }

    @PostConstruct
    public void init() {
        client = MessageClient.getInstance(host, port);
    }


    @PreDestroy
    public void destroy() throws Exception {
        client.shutdown();
    }
}
