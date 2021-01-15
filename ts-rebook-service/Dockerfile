FROM codewisdom/java8-skywalking-agent:v2.2

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/ts-rebook-service-1.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/ts-rebook-service-1.0.jar"]

EXPOSE 18886