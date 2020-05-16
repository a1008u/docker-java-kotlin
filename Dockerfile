FROM openjdk:8
COPY ./src/main/java/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java","Main"]
