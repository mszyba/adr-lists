FROM openjdk:11
ADD target/adr-list.jar adr-list.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "adr-list.jar"]
