FROM java:8-jre
ENV APP_NAME=ts-admin-user-service-1.0
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

COPY ./jacocoagent.jar /app/
ADD ./target/ts-admin-user-service-1.0.jar /app/
ENTRYPOINT exec java -javaagent:/app/jacocoagent.jar=includes=adminuser.*,output=tcpserver,port=6305,address=0.0.0.0 -Xmx200m -jar /app/${APP_NAME}.jar
#CMD ["java", "-Xmx200m",  "-jar", "/app/ts-admin-user-service-1.0.jar"]

EXPOSE 6305
EXPOSE 16115