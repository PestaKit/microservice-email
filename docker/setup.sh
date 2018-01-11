#!/bin/bash

# Execute a Docker image for maven to create jar from source files
cd ../swagger/spring-server
docker run -it --rm --name my-maven-project -v "$PWD":/usr/src/mymaven -w /usr/src/mymaven maven:3.2-jdk-7 mvn clean install

# # Backup fo docker-compose.yml
cd ../../docker
cp docker-compose.yml docker-compose.yml.old

read -p 'Email Address: ' email
sed -i.bak "s|\${USERNAME}|${email}|g" docker-compose.yml
read -sp 'Password: ' password
sed -i.bak "s|\${PASSWORD}|${password}|g" docker-compose.yml
read -p 'Server SMTP: ' server_smtp
sed -i.bak "s|\${SMTPHOST}|${server_smtp}|g" docker-compose.yml

# Execute a Docker Compose to run the spring server
cp ../swagger/spring-server/target/swagger-spring-1.0.0.jar java/
docker-compose up -d --build && mv docker-compose.yml.old docker-compose.yml && yes | rm docker-compose.yml.bak
