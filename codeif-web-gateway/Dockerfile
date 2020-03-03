FROM java:8
WORKDIR /service
COPY target/codeif-web-gateway-1.0.0.jar codeif-web-gateway.jar
RUN bash -c "touch /codeif-web-gateway.jar"
EXPOSE 8080
ENV JAVA_OPTS "-server -Xmx128m -Xms128m -Xmn36m -DACTIVE_PROFILE=prod"
ENTRYPOINT exec java $JAVA_OPTS -jar /service/codeif-web-gateway.jar