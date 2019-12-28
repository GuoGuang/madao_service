FROM java:8
WORKDIR /service
COPY target/ibole-web-gateway-1.0.0.jar ibole-web-gateway.jar
RUN bash -c "touch /ibole-web-gateway.jar"
EXPOSE 8080
ENV JAVA_OPTS "-server -Xmx128m -Xms128m -Xmn36m -DACTIVE_PROFILE=prod"
ENTRYPOINT exec java $JAVA_OPTS -jar /service/ibole-web-gateway.jar