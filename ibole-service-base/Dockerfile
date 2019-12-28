FROM java:8
WORKDIR /service
COPY target/ibole-service-base-1.0.0.jar service-base.jar
RUN bash -c "touch /service-base.jar"
EXPOSE 9008
ENV JAVA_OPTS "-server -Xmx300m -Xms300m -Xmn68m -DACTIVE_PROFILE=prod"
ENTRYPOINT exec java $JAVA_OPTS -jar /service/service-base.jar
