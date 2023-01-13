FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
ENV JAVA_OPTS=""

# ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.systems.central.EasyBackWeb.RestauranteWebApplication"]
# ENTRYPOINT ["java","-cp","app:app/lib/*","com.consiti.Application"]