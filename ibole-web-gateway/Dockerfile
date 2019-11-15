FROM java:8
VOLUME /tmp
COPY target/ibole-web-gateway-1.0.0.jar ibole-web-gateway.jar
RUN bash -c "touch /ibole-web-gateway.jar"
EXPOSE 8080
ENV JAVA_OPTS "-server -Xmx128m -Xms128m -Xmn36m"
ENTRYPOINT exec java $JAVA_OPTS -jar ibole-web-gateway.jar