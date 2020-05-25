cd rbc
docker build -t image_rbc .
docker run -p 8081:8080 -t image_rbc

http://localhost:8081/rbc/


cd weather
docker build -t image_weather .
docker run -p 8082:8080 -t image_weather

http://localhost:8082/weather/





curl http://localhost:8081/rbc/ -s
curl http://localhost:8082/weather/ -s