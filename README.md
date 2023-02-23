# my-onion-clean-architecture

"Clean" (Layered) Architecture is another way to provide for <b>separation of concerns</b>, <b>encapsulation</b> and <b>decoupling</b>, by grouping code units by their domain and functional role within the application.

## My view of implementing an Onion "Clean-Architecture" with Java (Domain-driven Design, 'Hexagon' architecture) 
 
![cross the architectonic boundaries](/docs/img/onion1.JPG)

### Architecture Structure
- <b>Domain</b> Layer
  - All business logic is here. 
  - Private Entities, Events and public Aggregates
- <b>Application</b> Layer
  - Use Cases (Interactors)
  - It does not contain business logic 
- <b>Infrastructure</b> Layer
  - The technical capabilities that support the layers above, ie. persistence or messaging.
  - MVC, interface adapters
- Extra "Main" (<b>Config</b>) Package
  - SpringConfig //as Martin Fowler describes in his book

- ![structure](/docs/img/onion0.jpg)

### My differences to base article:
- My changes and corrections
    - 4 Layers Package-Hierarchie
    - I believe, Aggregates (DDD) should be an EntryPoint to Domain.Module (not just Factory)
    - Domain-Entities should be not accessible outside of the domain Layer
    - Records (Java17) as valueObjects
    - ArchUnit for insuring DDD Onion Architecture Rules
    - Spring Config from Main injects DI Classes (architectonic border)
        - Inject Interactor as Boundary-Interface


- ![onion layers](/docs/img/onion3.JPG)
- ![onion layers interactions](/docs/img/onion2.JPG)
- Alternative layers structure and encapsulation
- ![alternative layers encapsulation](/docs/img/other_layers.JPG)
- ![DDD layers](/docs/img/ddd_layers.jpg)

### Used articles as a basis:

- [Clean Architecture with Spring Boot](https://www.baeldung.com/spring-boot-clean-architecture)
- [Architecture rules & ArchUnit check](https://www.jvt.me/posts/2022/01/28/spring-boot-onion-architecture/)
