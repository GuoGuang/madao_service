FROM openjdk:17-alpine
WORKDIR /service
COPY target/madao-service-base-1.0.0.jar service-base.jar
# RUN bash -c "touch /service-base.jar"
RUN /bin/sh -c "touch /service-base.jar"
EXPOSE 9008
ENV JAVA_OPTS "-server -Xmx300m -Xms300m -Xmn68m -DACTIVE_PROFILE=prod -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/service/log/dump.hprof -XX:ErrorFile=/service/log/dump-error.log"
ENTRYPOINT exec java $JAVA_OPTS -jar /service/service-base.jar
