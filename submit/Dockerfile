FROM ubuntu:latest

RUN apt-get update && \
    apt-get install -y vim git openjdk-17-jdk maven curl nodejs npm

WORKDIR /app

RUN git clone https://github.com/assylzhanb/course-reviewer.git
RUN git pull
RUN git restore .

RUN mvn clean package

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]