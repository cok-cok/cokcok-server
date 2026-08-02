FROM amazoncorretto:21

WORKDIR /cokcok

COPY build/libs/*SNAPSHOT.jar cokcok.jar

ENTRYPOINT ["java", "-jar", "cokcok.jar"]