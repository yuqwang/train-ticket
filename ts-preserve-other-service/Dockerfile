FROM apache/skywalking-java-agent:8.5.0-jdk8

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/ts-preserve-other-service-1.0.jar /app/
CMD ["java", "-Xmx200m",  "-jar", "/app/ts-preserve-other-service-1.0.jar"]

EXPOSE 14569