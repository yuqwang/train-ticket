FROM hechuan73/openjdk8-stress


ADD ./target/ts-ticketinfo-service-1.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/ts-ticketinfo-service-1.0.jar"]

EXPOSE 15681