FROM openjdk:17-alpine
WORKDIR /service
COPY target/madao-web-gateway-1.0.0.jar madao-web-gateway.jar
# RUN bash -c "touch /madao-web-gateway.jar"
RUN /bin/sh -c "touch /madao-web-gateway.jar"
EXPOSE 8080
ENV JAVA_OPTS "-server -Xmx128m -Xms128m -Xmn36m -DACTIVE_PROFILE=prod -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/service/log/dump.hprof -XX:ErrorFile=/service/log/dump-error.log"
ENTRYPOINT exec java $JAVA_OPTS -jar /service/madao-web-gateway.jar