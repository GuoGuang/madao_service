FROM java:8
WORKDIR /service
CMD echo "测试"
CMD pwd
CMD find / -name eureka
ADD target/ibole-server-eureka-1.0.0.jar server-eureka.jar
RUN bash -c "touch /server-eureka.jar"
EXPOSE 5000
ENV JAVA_OPTS "-server -Xmx128m -Xms128m -Xmn36m"
ENTRYPOINT exec java $JAVA_OPTS -jar server-eureka.jar
