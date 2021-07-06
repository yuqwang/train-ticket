FROM registry.cn-hangzhou.aliyuncs.com/lavenpok/skywalking-agent:1.0

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/ts-food-service-1.0.jar /app/
ENV SW_AGENT_NAME=ts-food-service
CMD ["java", "$JAVA_OPTIONS", "-javaagent:/skywalking/agent/skywalking-agent.jar", "-jar", "/app/ts-food-service-1.0.jar"]

EXPOSE 18856