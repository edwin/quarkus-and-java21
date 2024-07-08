# Quarkus and Java 21

How to run Quarkus in Java 21 with Shenandoah GC. 

## Images
Providing two different dockerfiles ( `Dockerfile` and `minimal.Dockerfile` ), with each having a different base image.

## Image Difference
The difference is only in `minimal.Dockerfile` we do manual package installation using `microdnf` which shows a great flexibility when extending the base image.

## How to Test

```
$ curl -kv http://localhost:8080/api/students?page=0

*   Trying [::1]:8080...
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080
> GET /api/students?page=0 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
>
< HTTP/1.1 200 OK
< Content-Type: application/json;charset=UTF-8
< content-length: 59
<
* Connection #0 to host localhost left intact
{"result":[{"id":2,"name":"pepe"},{"id":1,"name":"edwin"}]}     
```

```
$ curl -kv -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" \ 
    -d '{ "name": "edwin" }' http://localhost:8080/api/students
    
*   Trying [::1]:8080...
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080
> POST /api/students HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
> Content-Type: application/json
> Cache-Control: no-cache
> Content-Length: 19
>
< HTTP/1.1 201 Created
< Content-Type: application/json;charset=UTF-8
< content-length: 34
<
* Connection #0 to host localhost left intact
{"result":{"id":1,"name":"edwin"}}          
```

## Image Vulnerabilities
At the current time (05 July 2024), both `ubi9/openjdk-21-runtime:1.20` and `ubi9/ubi-minimal:9.4` images gives no CRITICAL nor HIGH vulnerabilities. Scan is done by using Aquasecurity Trivy 
```
Total: 62 (UNKNOWN: 0, LOW: 40, MEDIUM: 22, HIGH: 0, CRITICAL: 0)
```

## Blog Post
```
https://edwin.baculsoft.com/2024/07/creating-java-21-runtime-on-top-of-an-ubi-base-image/
```