FROM java:8
WORKDIR /service
COPY target/ibole-authentication-server-1.0.0.jar authentication-server.jar
RUN bash -c "touch /authentication-server.jar"
EXPOSE 8090
ENV JAVA_OPTS="-server -Xmx128m -Xms128m -Xmn36m -DACTIVE_PROFILE=prod"
ENTRYPOINT ["java ${JAVA_OPTS}","-jar","/service/authentication-server.jar"]

