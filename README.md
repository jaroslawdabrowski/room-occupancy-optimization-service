# Room Occupancy Optimization Service

### How to run
Build
>mvn clean install

and Run
>java -jar target/room-occupancy-optimization-service-0.0.1-SNAPSHOT.jar

Requires java 17

### How to run tests
>mvn clean test

### Solution description
- Solution is running using Spring Boot. 
- H2 in memory database has been used as a storage of guests data. 
- No data is being loaded while running the app.
- Test data will load while running the e2e tests: [RoomOccupancyOptimizationServiceApplicationTests.java](src%2Ftest%2Fjava%2Fcom%2Fjarek%2Froomoccupancyopt%2FRoomOccupancyOptimizationServiceApplicationTests.java)
- Persistence layer is utilizing Spring Data JPA,
- Validation API has been introduced to avoid input of invalid data,
- Liquibase is responsible for DB migration,
- Rest API has been exposed and documented using Open API specification,
- Lombok allows to reduce amount of boilerplate code,
- Hexagonal architecture has been used as a way to define things like: model, structure, communication between components,
- There is some over-architecting which is not required for such a simple service, but the goal was to show my approach of working on a bigger projects,
- Some of the parts of the system does not follow hexagonal architecture in 100% as it could be an overkill,

### TODO
- Store calculation response in DB, so that it can be read afterward,
- Use real database in production code,
- Add API for adding guests,
- Use HATEOS,
- Do not test all possible business cases in [RoomOccupancyOptimizationServiceApplicationTests.java](src%2Ftest%2Fjava%2Fcom%2Fjarek%2Froomoccupancyopt%2FRoomOccupancyOptimizationServiceApplicationTests.java) as it has been already covered in [RoomOccupancyCalculationServiceTest.java](src%2Ftest%2Fjava%2Fcom%2Fjarek%2Froomoccupancyopt%2Fbusiness%2FRoomOccupancyCalculationServiceTest.java),
- Add security,
- Dockerize,
