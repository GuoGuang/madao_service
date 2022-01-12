FROM java:8
WORKDIR /service
COPY target/madao-service-article-1.0.0.jar service-article.jar
RUN bash -c "touch /service-article.jar"
EXPOSE 9003
ENV JAVA_OPTS="-server -Xmx300m -Xms300m -Xmn68m -DACTIVE_PROFILE=prod -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/service/log/dump.hprof -XX:+PrintGCDetails -XX:ErrorFile=/service/log/dump-error.log"
ENTRYPOINT exec java $JAVA_OPTS -jar /service/service-article.jar