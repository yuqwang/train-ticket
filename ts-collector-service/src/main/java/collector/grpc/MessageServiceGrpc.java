package collector.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *定义服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: justtest.proto")
public class MessageServiceGrpc {

  private MessageServiceGrpc() {}

  public static final String SERVICE_NAME = "protocol.MessageService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<CoverageRequest,
      ResponseMessage> METHOD_UPLOAD_COVERAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protocol.MessageService", "uploadCoverage"),
          io.grpc.protobuf.ProtoUtils.marshaller(CoverageRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(ResponseMessage.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<StateRequest,
      ResponseMessage> METHOD_UPLOAD_STATE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "protocol.MessageService", "uploadState"),
          io.grpc.protobuf.ProtoUtils.marshaller(StateRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(ResponseMessage.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MessageServiceStub newStub(io.grpc.Channel channel) {
    return new MessageServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MessageServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MessageServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static MessageServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MessageServiceFutureStub(channel);
  }

  /**
   * <pre>
   *定义服务
   * </pre>
   */
  public static abstract class MessageServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *发送Coverage
     * </pre>
     */
    public void uploadCoverage(CoverageRequest request,
                               io.grpc.stub.StreamObserver<ResponseMessage> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPLOAD_COVERAGE, responseObserver);
    }

    /**
     */
    public void uploadState(StateRequest request,
                            io.grpc.stub.StreamObserver<ResponseMessage> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPLOAD_STATE, responseObserver);
    }

    @Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_UPLOAD_COVERAGE,
            asyncUnaryCall(
              new MethodHandlers<
                CoverageRequest,
                ResponseMessage>(
                  this, METHODID_UPLOAD_COVERAGE)))
          .addMethod(
            METHOD_UPLOAD_STATE,
            asyncUnaryCall(
              new MethodHandlers<
                StateRequest,
                ResponseMessage>(
                  this, METHODID_UPLOAD_STATE)))
          .build();
    }
  }

  /**
   * <pre>
   *定义服务
   * </pre>
   */
  public static final class MessageServiceStub extends io.grpc.stub.AbstractStub<MessageServiceStub> {
    private MessageServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessageServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MessageServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessageServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *发送Coverage
     * </pre>
     */
    public void uploadCoverage(CoverageRequest request,
                               io.grpc.stub.StreamObserver<ResponseMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPLOAD_COVERAGE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void uploadState(StateRequest request,
                            io.grpc.stub.StreamObserver<ResponseMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPLOAD_STATE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *定义服务
   * </pre>
   */
  public static final class MessageServiceBlockingStub extends io.grpc.stub.AbstractStub<MessageServiceBlockingStub> {
    private MessageServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessageServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MessageServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessageServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *发送Coverage
     * </pre>
     */
    public ResponseMessage uploadCoverage(CoverageRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPLOAD_COVERAGE, getCallOptions(), request);
    }

    /**
     */
    public ResponseMessage uploadState(StateRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPLOAD_STATE, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *定义服务
   * </pre>
   */
  public static final class MessageServiceFutureStub extends io.grpc.stub.AbstractStub<MessageServiceFutureStub> {
    private MessageServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessageServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected MessageServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessageServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *发送Coverage
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<ResponseMessage> uploadCoverage(
        CoverageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPLOAD_COVERAGE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ResponseMessage> uploadState(
        StateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPLOAD_STATE, getCallOptions()), request);
    }
  }

  private static final int METHODID_UPLOAD_COVERAGE = 0;
  private static final int METHODID_UPLOAD_STATE = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MessageServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(MessageServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_COVERAGE:
          serviceImpl.uploadCoverage((CoverageRequest) request,
              (io.grpc.stub.StreamObserver<ResponseMessage>) responseObserver);
          break;
        case METHODID_UPLOAD_STATE:
          serviceImpl.uploadState((StateRequest) request,
              (io.grpc.stub.StreamObserver<ResponseMessage>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_UPLOAD_COVERAGE,
        METHOD_UPLOAD_STATE);
  }

}
