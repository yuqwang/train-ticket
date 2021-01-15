FROM codewisdom/java8-skywalking-agent:v2.2

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/ts-consign-service-1.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/ts-consign-service-1.0.jar"]

EXPOSE 16111