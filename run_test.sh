#!/bin/bash
./gradlew clean build
java -jar build/libs/mars_rover.jar -f test.txt
exit 0
