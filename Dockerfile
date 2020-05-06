FROM openjdk:11
COPY . /usr/src/back-end-test-southsystem
WORKDIR /usr/src/back-end-test-southsystem
RUN ./gradlew build
