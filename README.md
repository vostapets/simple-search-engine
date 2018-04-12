# Simple search engine application

## Build application
```bash
mvn clean install
```

## Simple search server
### Run application
```bash
java -jar simple-search-server/target/simple-search-server-1.0.jar 
```
### Put documents into the search engine by key
```bash
curl -i -H "Accept: application/json" -H "Content-type: application/json" -X POST -d 'document data' http://localhost:8080/document
```

### Example of puts some documents
```bash
curl -i -H "Accept: application/json" -H "Content-type: application/json" -X POST -d 'aaa bbb ccc' http://localhost:8080/document
HTTP/1.1 201 
Location: http://localhost:8080/document/fb82247d-7299-402f-a94b-bdd35e5b8dfd
Content-Length: 0
Date: Thu, 12 Apr 2018 05:19:31 GMT
curl -i -H "Accept: application/json" -H "Content-type: application/json" -X POST -d 'aaa bbb' http://localhost:8080/document
HTTP/1.1 201 
Location: http://localhost:8080/document/6c2b5c54-d98a-4982-9274-000ef11354d0
Content-Length: 0
Date: Thu, 12 Apr 2018 05:20:14 GMT
curl -i -H "Accept: application/json" -H "Content-type: application/json" -X POST -d 'aaa ccc' http://localhost:8080/document
HTTP/1.1 201 
Location: http://localhost:8080/document/c8842c68-1512-4634-8197-d84d132ab459
Content-Length: 0
Date: Thu, 12 Apr 2018 05:20:51 GMT
```

## Get document by key
```bash
curl -X GET http://localhost:8080/document/{id}
```

## Example of gets some documents
```bash
curl -X GET http://localhost:8080/document/fb82247d-7299-402f-a94b-bdd35e5b8dfd
aaa bbb ccc
curl -X GET http://localhost:8080/document/6c2b5c54-d98a-4982-9274-000ef11354d0
aaa bbb
curl -X GET http://localhost:8080/document/c8842c68-1512-4634-8197-d84d132ab459
aaa ccc
```

## Search on a string of tokens to return keys of all documents that contain all tokens in the set
#### Request example
[http://localhost:8080/search?token=aa&token=bb](http://localhost:8080/search?token=aa&token=bb)
#### Response example 
```
["aaa bbb ccc","aaa bbb"]
```

## Simple search client
### Run application
```bash
java -jar simple-search-client/target/simple-search-client-1.0.jar 
```

### Example of output
```log
2018-04-12 09:22:37.829  INFO 30737 --- [           main] o.o.sse.server.client.ClientApplication  : Started ClientApplication in 1.482 seconds (JVM running for 1.949)
2018-04-12 09:22:37.831  INFO 30737 --- [           main] o.o.sse.server.client.ClientApplication  : Client application example hard-coded behaviour.
2018-04-12 09:22:37.837  INFO 30737 --- [           main] o.o.sse.server.client.ClientApplication  : Store document aaa bbb ccc.
2018-04-12 09:22:38.212  INFO 30737 --- [           main] o.o.sse.server.client.ClientApplication  : Get document by uri http://localhost:8080/document/23815394-c3a4-40c7-a9d3-e59e3f5a85d4.
2018-04-12 09:22:38.272  INFO 30737 --- [           main] o.o.sse.server.client.ClientApplication  : Result of get aaa bbb ccc.
2018-04-12 09:22:38.272  INFO 30737 --- [           main] o.o.sse.server.client.ClientApplication  : Store more documents...
2018-04-12 09:22:38.315  INFO 30737 --- [           main] o.o.sse.server.client.ClientApplication  : Search by tokens: aa, bb.
2018-04-12 09:22:38.361  INFO 30737 --- [           main] o.o.sse.server.client.ClientApplication  : Search result ["aaa bbb ccc","aaa bbb"].
2018-04-12 09:22:38.361  INFO 30737 --- [           main] o.o.sse.server.client.ClientApplication  : Search by tokens: cc, bb.
2018-04-12 09:22:38.367  INFO 30737 --- [           main] o.o.sse.server.client.ClientApplication  : Search result ["aaa bbb ccc"].
2018-04-12 09:22:38.368  INFO 30737 --- [           main] o.o.sse.server.client.ClientApplication  : Search by tokens: dd, bb.
2018-04-12 09:22:38.372  INFO 30737 --- [           main] o.o.sse.server.client.ClientApplication  : Search result [].
```