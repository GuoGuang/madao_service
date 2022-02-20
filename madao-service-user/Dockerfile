FROM java:8
WORKDIR /service
COPY target/madao-service-user-1.0.0.jar service-user.jar
RUN bash -c "touch /service-user.jar"
EXPOSE 9007 9099
ENV JAVA_OPTS="-server -Xmx300m -Xms300m -Xmn68m -DACTIVE_PROFILE=prod -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/service/log/dump.hprof -XX:+PrintGCDetails -XX:ErrorFile=/service/log/dump-error.log"
ENTRYPOINT exec java $JAVA_OPTS -jar /service/service-user.jar

