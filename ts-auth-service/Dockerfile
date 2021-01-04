FROM codewisdom/java8-skywalking-agent:v2

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/ts-auth-service-1.0.jar /app/
ENV SW_AGENT_NAME=ts-auth-service
CMD ["java", "-Xmx200m", "-javaagent:/skywalking/agent/skywalking-agent.jar", "-jar", "/app/ts-auth-service-1.0.jar"]

EXPOSE 12349