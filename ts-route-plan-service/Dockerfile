FROM registry.cn-hangzhou.aliyuncs.com/lavenpok/skywalking-agent:1.0

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/ts-route-plan-service-1.0.jar /app/
ENV SW_AGENT_NAME=ts-route-plan-service
CMD ["java", "-Xmx200m", "-javaagent:/skywalking/agent/skywalking-agent.jar", "-jar", "/app/ts-route-plan-service-1.0.jar"]

EXPOSE 14578