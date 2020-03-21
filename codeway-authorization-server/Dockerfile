FROM java:8
WORKDIR /service
COPY target/codeway-authorization-server-1.0.0.jar authorization-server.jar
RUN bash -c "touch /authorization-server.jar"
EXPOSE 8091
ENV JAVA_OPTS="-server -Xmx128m -Xms128m -Xmn36m -DACTIVE_PROFILE=prod"
ENTRYPOINT exec java $JAVA_OPTS -jar /service/authorization-server.jar

