# my-onion-clean-architecture

## My view of implementing an onion clean-architecture with Java (Domain-driven Design, 'Hexagon' architecture) 

- Domain Layer (private Entities and public Aggregate)
- Application Layer (Use Cases)
- Infrastructure Layer (MVC, interface adapter)
- Extra Main Package (SpringConfig //as Martin Fowler describes in his book)

### My differences to base article:
- My changes and corrections
    - 4 Layers Package-Hierarchie
    - I believe, Aggregates (DDD) should be an EntryPoint to Domain.Module (not just Factory)
    - Domain-Entities should be not accessible outside of the domain Layer
    - Records (Java17) as valueObjects
    - ArchUnit for insuring DDD Onion Architecture Rules
    - Spring Config from Main injects DI Classes (architectonic border)
        - Inject Interactor as Boundary-Interface

- ![cross the architectonic boundaries](/docs/img/onion1.JPG)
- ![onion layers interactions](/docs/img/onion2.JPG)


### Used Article as a basis:

- [Clean Architecture with Spring Boot](https://www.baeldung.com/spring-boot-clean-architecture)
