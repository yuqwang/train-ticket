FROM registry.cn-hangzhou.aliyuncs.com/lavenpok/skywalking-agent:1.0

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/ts-order-other-service-1.0.jar /app/
ENV SW_AGENT_NAME=ts-order-other-service
CMD ["java", "-Xmx200m", "-javaagent:/skywalking/agent/skywalking-agent.jar", "-jar", "/app/ts-order-other-service-1.0.jar"]
#CMD java $JAVA_OPTIONS -jar /app/ts-order-other-service-1.0.jar
EXPOSE 12032