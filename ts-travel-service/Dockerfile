FROM hechuan73/openjdk8-stress


ADD ./target/ts-travel-service-1.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/ts-travel-service-1.0.jar"]

EXPOSE 12346