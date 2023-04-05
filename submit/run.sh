#!/bin/bash
git clone https://github.com/assylzhanb/course-reviewer.git
cd course-reviewer
mvn install
java -jar target/cse364-project-1.0-SNAPSHOT-jar-with-dependencies.jar