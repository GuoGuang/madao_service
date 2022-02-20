FROM java:8
WORKDIR /service
COPY target/madao-server-config-1.0.0.jar server-config.jar
RUN bash -c "touch /server-config.jar"
EXPOSE 9009
ENV JAVA_OPTS="-server -Xmx128m -Xms128m -Xmn36m -DACTIVE_PROFILE=prod -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/service/log/dump.hprof -XX:+PrintGCDetails -XX:ErrorFile=/service/log/dump-error.log"
ENTRYPOINT exec java $JAVA_OPTS -jar /service/server-config.jar
