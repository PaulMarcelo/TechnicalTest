# TechnicalTest
Prueba tecnica Paul Yaguachi
1.-Base de datos: 
- Postgresql
- Script:  BaseDatos.sql

2.- Crear imagen:
- docker pull postgres
3.- Contenedor:
- docker run --name my-postgres -e POSTGRES_USER=paul -e POSTGRES_PASSWORD=password -e POSTGRES_DB=mypauldb -p 5432:5432 -d postgres

4.- Crear red para DB:
- docker network create my-network
- docker network connect my-network my-postgres

5.-Proyectos spring boot:
- customerperson
- transactionsaccount

6.- Compilar archivo  .jar:
En la raiz de cada proyecto spring boot, ejecutar:
./gradlew bootJar

7.- Crear imagenes:
- docker build -t customerperson:1.0.0 .
- docker build -t transactionsaccount:1.0.0 .

8.- Levantar contenedores:
-  docker run --name customer-app --network my-network -p 8082:8082 customerperson:1.0.0
-  docker run --name transactions-app --network my-network -p 8081:8081 transactionsaccount:1.0.0

9.- Archivo endpoints Postman:
- Endpoints-TechnicalTest-Paul-Yaguachi.json





