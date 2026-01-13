# Docker file i docker compose per desplegar la app en remot, d'un sol cop

## 1. Preparació 
Per engegar els contenidors amb la vostra app en un jar:
### Arxius necessaris
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
      - mariadb
    networks:
      - xarxa1
    ports:
      - "8080:8080"
      DATABASE_URL: ${DATABASE_URL:-jdbc:mariadb://mariadb:3306/mydb}
      MONGODBCONNECTION: ${MONGODBCONNECTION} 

  mariadb:
    image: mariadb:11.4
    container_name: mariadb
    restart: unless-stopped
    environment:
      MARIADB_ROOT_PASSWORD: P@ssw0rd
      MARIADB_DATABASE: mydb  #DB inicial
    volumes:
      - mariadb_data:/var/lib/mysql
    networks:
      - xarxa1
    ports:
      - "3306:3306"

networks:
  xarxa1:
    name: xarxa1

volumes:
  mariadb_data:
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
