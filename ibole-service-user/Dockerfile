FROM java:8
WORKDIR /service
COPY target/ibole-service-user-1.0.0.jar service-user.jar
RUN bash -c "touch /service-user.jar"
EXPOSE 9007
ENV JAVA_OPTS="-server -Xmx300m -Xms300m -Xmn68m -DACTIVE_PROFILE=prod"
ENTRYPOINT exec java $JAVA_OPTS -jar /service/service-user.jar

