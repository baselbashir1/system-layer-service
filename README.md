# System Layer Service

- Receives requests forwarded from Aggregator.
- Optionally adds headers or correlation IDs.
- Forwards the request asynchronously to the Backend System.
- Returns the asynchronous response to the Aggregator.

## Endpoints
- /layer/{id} (GET) – Get bundle by ID
- /layer (POST) – Create bundle
- /layer/{id} (PATCH) – Update bundle
- /layer/{id} (DELETE) – Delete bundle

## Tech Stack
- Spring Boot 3.5.4
- Java 21
- WebFlux
- Correlation ID propagation
- Lombok
- ControllerAdvice for exception handling

## Build & Run
```bash
./mvnw clean package
java -jar target/systemlayer-0.0.1-SNAPSHOT.war