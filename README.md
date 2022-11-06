# my-onion-clean-architecture

## My opinion on Onion CleanArchitecture

- Domain Layer (private Entity and public Aggregates)
- Application Layer (Use Cases)
- Infrastructure Layer (Spring Boot classes)
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


### Used Article as a basis:

- [Clean Architecture with Spring Boot](https://www.baeldung.com/spring-boot-clean-architecture)
