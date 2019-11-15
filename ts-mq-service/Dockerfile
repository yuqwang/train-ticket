FROM hechuan73/openjdk8-stress


ADD ./target/ts-mq-service-0.1.0.jar /app/
CMD ["java", "-Xmx700m", "-jar", "/app/ts-mq-service-0.1.0.jar"]
EXPOSE 18391