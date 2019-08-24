FROM java:8
VOLUME /tmp
COPY target/youyd-authorization-server-1.0.0.jar authorization-server.jar
RUN bash -c "touch /authorization-server.jar"
EXPOSE 8091
ENV JAVA_OPTS="-server -Xmx128m -Xms128m -Xmn128m"
ENTRYPOINT ["java ${JAVA_OPTS}","-jar","authorization-server.jar"]

