docker rm -f mysqldb
timeout /t 10 /nobreak > NUL
docker rm -f app
timeout /t 10 /nobreak > NUL
docker run -d -p 3307:3306 --name mysqldb --env-file mysqlenv mysql
timeout /t 10 /nobreak > NUL
docker network connect spring-network mysqldb
timeout /t 10 /nobreak > NUL
docker run -d -p 9090:8080 --name app --net spring-network --env-file appenv app
timeout /t 10 /nobreak > NUL
docker logs mysqldb
timeout /t 10 /nobreak > NUL
docker logs app
