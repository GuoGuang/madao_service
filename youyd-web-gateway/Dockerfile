FROM java:8
VOLUME /tmp
COPY target/youyd-web-gateway-1.0.0.jar youyd-web-gateway.jar
RUN bash -c "touch /youyd-web-gateway.jar"
EXPOSE 8080
ENV JAVA_OPTS "-server -Xmx128m -Xms128m -Xmn128m"
ENTRYPOINT exec java $JAVA_OPTS -jar youyd-web-gateway.jar