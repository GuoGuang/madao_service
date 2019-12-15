FROM java:8
#VOLUME /tmpppp

RUN mkdir -p /service
WORKDIR /service
RUN pwd
RUN ls
COPY ibole-server-eureka/target/ibole-service-base-1.0.0.jar service-base.jar
RUN bash -c "touch /service-base.jar"
EXPOSE 9008
ENV JAVA_OPTS "-server -Xmx300m -Xms300m -Xmn68m"
ENTRYPOINT exec java $JAVA_OPTS -jar service-base.jar
