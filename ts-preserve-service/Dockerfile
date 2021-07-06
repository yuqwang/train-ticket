FROM registry.cn-hangzhou.aliyuncs.com/lavenpok/skywalking-agent:1.0

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/ts-preserve-service-1.0.jar /app/
ENV SW_AGENT_NAME=ts-preserve-service
#CMD ["java", "$JAVA_OPTIONS", "-javaagent:/skywalking/agent/skywalking-agent.jar", "-jar", "/app/ts-preserve-service-1.0.jar"]
CMD java $JAVA_OPTIONS -javaagent:/skywalking/agent/skywalking-agent.jar -jar /app/ts-preserve-service-1.0.jar

EXPOSE 14568