FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/ms-customers-products-overview-*-SNAPSHOT.jar
COPY ${JAR_FILE} ms-customers-products-overview.jar
RUN addgroup -S bootcampgroup && adduser -S bootcampuser -G bootcampgroup
RUN mkdir -p /opt/logs/ms-customers-products-overview
RUN chown -R bootcampuser:bootcampgroup /opt/logs/ms-customers-products-overview
USER bootcampuser:bootcampgroup
ENTRYPOINT ["java", "-jar", "/ms-customers-products-overview.jar"]