package collector.client;


import collector.grpc.CoverageRequest;
import collector.grpc.MessageServiceGrpc;

import collector.grpc.ResponseMessage;
import collector.grpc.StateRequest;
import collector.mq.StateRabbitReceive;
import edu.fudan.common.util.CoverageMessage;
import edu.fudan.common.util.JsonUtils;
import edu.fudan.common.util.StateMessage;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 客户端
 */
public class MessageClient {
    private final ManagedChannel channel;
    private final MessageServiceGrpc.MessageServiceBlockingStub blockingStub;
    private static MessageClient client;
    private static final Logger logger = LoggerFactory.getLogger(MessageClient.class);

    //    private static final String host = "127.0.0.1";
//    private static final int ip = 50051;
    public static MessageClient getInstance(String host, int port) {
        if (client == null) {
            client = new MessageClient(host, port);
        }
        return client;
    }

    private MessageClient(String host, int port) {
        //usePlaintext表示明文传输，否则需要配置ssl
        //channel  表示通信通道
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        //存根
        blockingStub = MessageServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        client = null;
    }

    public void uploadCoverage(String coverage) {
        CoverageMessage coverageMessage = JsonUtils.json2Object(coverage, CoverageMessage.class);//CoverageRequest.newBuilder().setName(name).build();
        CoverageRequest request = CoverageRequest.newBuilder().setUuid(coverageMessage.getUuid())
                .setSpanId(coverageMessage.getSpanId()).setService(coverageMessage.getService())
                .setData(coverageMessage.getData().toString()).build();
        ResponseMessage response = blockingStub.uploadCoverage(request);
        logger.info("接收到覆盖率返回值：" + response.getMessage());
    }

    public void uploadState(String state) {
        StateMessage stateMessage = JsonUtils.json2Object(state, StateMessage.class);//StateRequest.newBuilder().setName(name).build();
        if (stateMessage.getData() == null)
            stateMessage.setData("null");
        StateRequest request = StateRequest.newBuilder().setUuid(stateMessage.getUuid())
                .setSpanId(stateMessage.getSpanId()).setService(stateMessage.getService())
                .setMethod(stateMessage.getMethod()).setData(stateMessage.getData().toString()).build();
        ResponseMessage response = blockingStub.uploadState(request);
        logger.info("接收到状态返回值：" + response.getMessage());
    }

//    public static void main(String[] args) throws InterruptedException {
//        MessageClient client = new MessageClient(host, ip);
//        String cov = JsonUtils.object2Json(new CoverageMessage<>("111", 1, "post", "2222"));
//        String state = "{\"uuid\":\"7b6034983fdf4e358af9f9629f9b1ef6.87.16619364354000001\",\"spanId\":0,\"method\":\"POST\",\"service\":\"ts-seat-service\",\"data\":1073741823}";
//        client.uploadCoverage(cov);
//        client.uploadState(state);
//        client.shutdown();
//    }
}