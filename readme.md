# Quarkus and Java 21

How to run Quarkus in Java 21 with Shenandoah GC. 

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