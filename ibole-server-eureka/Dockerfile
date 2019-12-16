FROM java:8
WORKDIR /service
ADD pom.xml pom1.xml
RUN bash -c "touch /server-eureka.jar"
EXPOSE 5000
ENV JAVA_OPTS "-server -Xmx128m -Xms128m -Xmn36m"
ENTRYPOINT exec java $JAVA_OPTS -jar /service/server-eureka.jar
