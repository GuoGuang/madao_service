FROM java:8
VOLUME /tmp
COPY target/youyd-server-config-1.0.0.jar server-config.jar
RUN bash -c "touch /server-config.jar"
EXPOSE 9009
ENV JAVA_OPTS="-server -Xmx128m -Xms128m -Xmn128m"
ENTRYPOINT ["java ${JAVA_OPTS}","-jar","server-config.jar"]