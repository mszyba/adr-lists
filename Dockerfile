FROM openjdk:11
ADD target/adr-list-mysql.jar adr-list-mysql.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "adr-list-mysql.jar"]

##### Stage 1: Build the application
#FROM openjdk:11
#
## Set the current working directory inside the image
#WORKDIR /app
#
## Copy maven executable to the image
#COPY . .
#
## Package the application
#RUN ./mvnw clean package -DskipTests
#ENTRYPOINT ["java", "-jar", "target/adr-list-mysql.jar"]





#
#
##### Stage 1: Build the application
#FROM openjdk:11
#
## Set the current working directory inside the image
#WORKDIR /app
#
## Copy maven executable to the image
#COPY . .
#
## Package the application
#RUN ./mvnw package -DskipTests
#ENTRYPOINT ["java", "-jar", "target/adr-list-mysql.jar"]

#
##### Stage 2: A minimal docker image with command to run the app
#FROM openjdk:11-jre-alpine
#
#ARG DEPENDENCY=/app/target/dependency
#
## Copy project dependencies from the build stage
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#
#ENTRYPOINT ["java","-cp","app:app/lib/*","eu.michalszyba.adrlist.AdrListApplication"]
