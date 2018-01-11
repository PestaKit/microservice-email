FROM anapsix/alpine-java
MAINTAINER Tano Iannetta <tano.iannetta@heig-vd.ch>
RUN apk add --no-cache bash
COPY wait_for_it.sh /opt/
COPY swagger-spring-1.0.0.jar /home/swagger-spring-1.0.0.jar
CMD ["java","-jar","/home/swagger-spring-1.0.0.jar"]

