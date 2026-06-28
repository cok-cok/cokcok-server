FROM amazoncorretto:21

WORKDIR /cokcok

COPY build/libs/*SNAPSHOT.jar momentaid.jar

ENTRYPOINT ["java", "-jar", "cokcok.jar"]