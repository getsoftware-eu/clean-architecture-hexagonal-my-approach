# My vision of Clean (onion) Architecture implementation

"Clean" (Layered) Architecture is another way to achieve <b>separation of concerns</b>, <b>encapsulation</b> and <b>decoupling</b>, by grouping code units based on their domain and functional role within the application. 

One of the main property of this architecture:
<b>We should never mix business-logik and application-logik in one service class</b>.
This is because it becomes practically impossible to separate the two logics once they are implemented (mixed) together.

## My view of implementing an Onion "Clean-Architecture" with Java (Domain-driven Design, 'Hexagon' architecture) 
 
![cross the architectonic boundaries](/docs/img/onion1.JPG)


### Layer Structure:
- <b>Domain</b> Layer
  - All inner-<b>business logic</b> is here (only fields and accessors and Aggregate Methods and Operations)
    - e.g. Consistency Rules: isPasswordIsValid(): >5 digits... (enforcing the aggregate’s business rules.)
  - Private Entities, Events, Inner-Operations and public Aggregat-Roots (entry point)
  - <u>My view: Interface (I-Domains) of Entities with (consistency) methods on this entity-projections (+ generics<T, Z> service: only conditions between local i-entities).</u>
- <b>Application</b> (abstract) Use-Case Layer
  - <b>Separation of usecase-logik-methods from technical (low-level) service-help-methods</b>
  - <u>My view: Abstract<T, Z> <b>Use Cases</b> (<b>Interactors</b> with I-Domains: create Entity, findByName, 'as a role X, I except special behavior'...)</u>
    - 1.define (or use same level-) DTOs (with other layers)
    - 2.define own (or use same level-) IGateway (technical interface-service-methods) 
    - e.g. 'UserRegisterInteractor': injects und uses (low) implementation of own defined IGateway
  - It does not contain inner-business-logic (=inner rules in Domains). But should contain interactor-logik (extern actions with Entities-Aggregates).
  - and we didn’t use any spring annotation in our business.
- <b>Infrastructure</b> (backend) Layer
  - Implementation of IGateway (technical help-services, defined in upper layer)
    - e.g. 'JpaUserRegisterApplicationService'
  - <u>My view: <b>Calling Use-Case</b>+konstruktor-injection of own ServiceImpl in abstract Usecase</u>
    - setting (higher) generics with concrete (low) <T: EntityImpl, Z: DTOImpl>
  - The technical capabilities that <b>support</b> the layers above, ie. persistence or messaging.
  - MVC, interface adapters
- Extra "Main" (<b>Config</b>) Package
  - SpringConfig //as Martin Fowler describes in his book

- ![cca with spring boot](/docs/img/boot-cca.jpg)

- ![structure](/docs/img/onion0.jpg)

### My differences to base article:
- My changes and corrections
    - 4 basic Layers ("Domain", "Application", "Infrastructure", "Main") for Project Package-Hierarchie 
    - I believe, Aggregates (DDD) should be an EntryPoint to Domain.Module (not just a Factory)
    - Domain-Entities should be not accessible outside of the domain Layer
    - Records (Java17) as valueObjects
    - <b>ArchUnit</b> for insuring DDD Onion Architecture Rules
    - Spring Config from "Main" layer injects DI Classes (architectonic border)
    - Inject Interactor as Boundary-Interface


- ![onion layers](/docs/img/onion3.JPG)
- ![onion layers interactions](/docs/img/onion2.JPG)
- 
## Alternative layers structure and encapsulation

- ![alternative layers encapsulation](/docs/img/other_layers.JPG)
- ![DDD layers](/docs/img/ddd_layers.jpg)

### Used articles as a basis:

- [Clean Architecture with Spring Boot](https://www.baeldung.com/spring-boot-clean-architecture)
- [Architecture rules & ArchUnit check](https://www.jvt.me/posts/2022/01/28/spring-boot-onion-architecture/)
