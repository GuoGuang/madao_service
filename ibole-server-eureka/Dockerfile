
FROM java:8
VOLUME /tmp
RUN bash ls
RUN bash pwd
COPY ./ibole-server-eureka/target/ibole-server-eureka-1.0.0.jar server-eureka.jar
RUN bash -c "touch /server-eureka.jar"
EXPOSE 5000
ENV JAVA_OPTS "-server -Xmx128m -Xms128m -Xmn36m"
ENTRYPOINT exec java $JAVA_OPTS -jar server-eureka.jar
