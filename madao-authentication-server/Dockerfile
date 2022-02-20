FROM java:8
WORKDIR /service
COPY target/madao-authentication-server-1.0.0.jar authentication-server.jar
RUN bash -c "touch /authentication-server.jar"
EXPOSE 8090
ENV JAVA_OPTS="-server -Xmx128m -Xms128m -Xmn36m -DACTIVE_PROFILE=prod -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/service/log/dump.hprof -XX:+PrintGCDetails -XX:ErrorFile=/service/log/dump-error.log"
ENTRYPOINT exec java $JAVA_OPTS -jar /service/authentication-server.jar