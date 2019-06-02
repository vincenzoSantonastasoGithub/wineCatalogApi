FROM openjdk:8-jdk-alpine

COPY build/libs/winesCatalogApi-0.0.1-SNAPSHOT.war /winesCatalogApi.war

CMD ["java", "-jar", "winesCatalogApi.war"]