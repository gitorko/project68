# Project 68

Spring Boot Micrometer - Prometheus, Wavefront

* Note the path for yml file needs to be an absolute path

```bash
docker build -f docker/Dockerfile --force-rm -t my-prometheus .
docker run -p 9090:9090 my-prometheus
```

