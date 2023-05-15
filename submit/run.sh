#!/bin/bash
git clone https://github.com/assylzhanb/course-reviewer.git
cd course-reviewer
git checkout milestone2
mvn clean install jacoco:report
mvn spring-boot:run
# mvn install
# java -jar target/cse364-project-1.0-SNAPSHOT-jar-with-dependencies.jar