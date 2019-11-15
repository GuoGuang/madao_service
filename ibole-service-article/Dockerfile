FROM java:8
VOLUME /tmp
COPY target/ibole-service-article-1.0.0.jar service-article.jar
RUN bash -c "touch /service-article.jar"
EXPOSE 9003
ENV JAVA_OPTS="-server -Xmx300m -Xms300m -Xmn68m"
ENTRYPOINT exec java $JAVA_OPTS -jar service-article.jar