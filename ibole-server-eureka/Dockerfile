FROM java:8
#VOLUME /tmpppp
RUN mkdir -p /service
WORKDIR /service
RUN ls -a
COPY ./target/ibole-server-eureka-1.0.0.jar server-eureka.jar
RUN bash -c "touch /server-eureka.jar"
EXPOSE 5000
ENV JAVA_OPTS "-server -Xmx128m -Xms128m -Xmn36m"
ENTRYPOINT exec java $JAVA_OPTS -jar server-eureka.jar
