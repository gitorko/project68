# Project 68

Spring Boot Micrometer - Prometheus, Wavefront

[https://gitorko.github.io/spring-boot-micrometer/](https://gitorko.github.io/spring-boot-micrometer/)

### Version

Check version

```bash
$java --version
openjdk 17.0.3 2022-04-19 LTS
```

### Dev

To run code.

```bash
./gradlew clean build
./gradlew bootRun
```

## Prometheus

Update the target ip-address in the prometheus.yml file, don't use localhost when using docker container

To start the prometheus docker instance build the docker image & run the image.

```bash
cd project68
docker build -f docker/Dockerfile --force-rm -t my-prometheus .
docker run -p 9090:9090 my-prometheus
```

## Grafana

To start the grafana docker instance run the command.

```bash
docker run --name grafana -d -p 3000:3000 grafana/grafana
```
