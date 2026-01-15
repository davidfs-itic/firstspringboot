# Dockerfile i docker-compose per desplegar la app en remot

## 1. Preparació 
Per engegar els contenidors amb la vostra app en un jar:

- Cal tenir els tres arxius al servidor:
    - springbootapp.jar
    - dockerbuild
    - docker-compose.yaml

### Arxiu springbootapp.jar
Des del vostre ordinador, podeu copiar remotament l'arxiu jar

```bash
scp springbootapp.jar usuari@ip_servidor:./
```

### Arxiu **dockerbuild**
Creeu el vostre arxiu dockerbuild al servidor remot
```
FROM openjdk:17-alpine
RUN addgroup -S docker && adduser -S appuser -G docker
USER appuser
WORKDIR /home/appuser
COPY springbootapp.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```



### Arxiu **docker-compose.yaml**
Creeu el vostre arxiu docker-compose.yaml al servidor remot
```docker
services:
  springboot-app:
    container_name: sbapp
    build:
      context: .
      dockerfile: dockerbuild
    depends_on:
      - mysql
    networks:
      - xarxa1
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_USERNAME: user
      SPRING_DATASOURCE_PASSWORD: 1234
      SPRING_DATASOURCE_URL: jdbc:mysql://mysqldb:3306/demo

  mysql:
    image: mysql:9.5.0
    container_name: mysqldb
    restart: unless-stopped
    environment:
      MYSQL_ROOT_PASSWORD: 1234
      MYSQL_DATABASE: demo
      MYSQL_USER: user
      MYSQL_PASSWORD: 1234
    volumes:
      - mysql_data:/var/lib/mysql
    networks: 
      - xarxa1
    ports:
      - "3306:3306"
networks:
  xarxa1:
    name: xarxa1

volumes:
  mysql_data: 
```

## 2. Execució:

En la carpeta on estiguin els tres arxius:

Engegar contenidors (cada vegada que es canvii el codi font)
```bash
docker-compose up -d --build
```

Aturar els contenidors
```bash
docker-compose down
```

## 3. Crear instància Oracle i obrir ports:
[https://davidfs-itic.github.io/davidfs-itic/IoT/oracle/](https://davidfs-itic.github.io/davidfs-itic/IoT/oracle/)