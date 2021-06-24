FROM registry.cn-hangzhou.aliyuncs.com/lavenpok/skywalking-agent:1.0

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/ts-admin-order-service-1.0.jar /app/
ENV SW_AGENT_NAME=ts-admin-order-service
CMD ["java", "-Xmx200m", "-javaagent:/skywalking/agent/skywalking-agent.jar", "-jar", "/app/ts-admin-order-service-1.0.jar"]

EXPOSE 16112