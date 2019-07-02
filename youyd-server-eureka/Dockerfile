FROM java:8
VOLUME /tmp
COPY target/youyd-server-eureka-1.0.0.jar server-eureka.jar
RUN bash -c "touch /server-eureka.jar"
EXPOSE 5000
ENTRYPOINT ["java","-jar","server-eureka.jar"]