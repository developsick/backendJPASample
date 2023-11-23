#FROM gradle:jdk17
#
#ENV USE_PROFILE=prd
#ENV TZ=Asia/Seoul
#ENV APP_HOME=/apps
#
#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#COPY settings.gradle .
#COPY src src
#
#RUN chmod +x ./gradlew
#RUN ./gradlew clean build
#
#ARG JAR_FILE_PATH=./build/libs/zcoa-ams-0.0.1-SNAPSHOT.jar
#WORKDIR $APP_HOME
#COPY $JAR_FILE_PATH app.jar
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-Dspring.profiles.active=${USE_PROFILE}","-jar","app.jar"]


FROM openjdk:17

VOLUME ["/var/log"]
ENV APP_HOME=/apps
ENV USE_PROFILE=prd
ENV TZ=Asia/Seoul

ARG JAR_FILE_PATH=./build/libs/zcoa-ams-0.0.1-SNAPSHOT.jar
WORKDIR $APP_HOME
COPY $JAR_FILE_PATH app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${USE_PROFILE}","-jar","app.jar"]