# My vision of Clean (onion) Architecture implementation

"Clean" (Layered) Architecture is another way to achieve <b>separation of concerns</b>, <b>encapsulation</b> and <b>decoupling</b> by grouping code units based on their domain and functional role within the application. 

One of the main principle of this architecture is:
<b>We should never mix business-logik and application-logik within a service class</b>.
This is because, it becomes practically impossible to separate these two types of logic, once they are implemented (mixed) together.

## My Approach to implementing Onion "Clean-Architecture" with Java (Domain-driven Design, 'Hexagon' Architecture) 
 
![cross the architectonic boundaries](/docs/img/onion1.JPG)


### Layer Structure:
- <b>Domain</b> Layer
  - All inner-<b>business logic</b> is contained here (only fields, accessors and aggregate methods and operations)
    - Example: Consistency rules like 'isPasswordIsValid(): >5' digits... (enforcing the aggregate’s business rules)
  - Private entities, events, inner operations and public Aggregat-Roots (as entry points)
  - <u>My approach: The interface (I-Domains) of entities with (consistency) methods on these entity-projections (+ generic<T, Z> in the service: only conditions between local i-entities).</u>
- <b>Application</b> (abstract) Use-Case Layer
  - <b>Separation of usecase-logik-methods from technical (low-level) service-help-methods</b>
  - <u>My approach: Abstract<T, Z> <b>Use Cases</b> (<b>Interactors</b> with I-Domains: create Entity, findByName, 'as a role X, I except special behavior'...)</u>
    - 1.define (or use same level-) DTOs (with other layers)
    - 2.define own (or use same level-) IGateway (technical interface-service-methods) 
    - Example: 'UserRegisterInteractor': injects und uses a (low-level) implementation of its own defined IGateway
  - Does not contain inner-business-logic (=inner rules within Domains). But should contain interactor-logik (extern actions with Entity-Aggregates).
  - No spring annotation are used in our business logic.
- <b>Infrastructure</b> (backend) Layer
  - Implementation of IGateway (technical helper-services, defined in the upper layer)
    - Example: 'JpaUserRegisterApplicationService'
  - <u>My approach: <b>Calling Use-Case</b>+ constructor-injection of own ServiceImpl in the abstract Usecase</u>
    - setting (higher) generics with concrete (low-level) <T: EntityImpl, Z: DTOImpl>
  - Provides the technical capabilities that <b>support</b> the layers above, such as persistence or messaging.
  - MVC, interface adapters
- <b>Adapter</b> Layer
  - <b>in</b> adapters:
    - specific user request (Controller Adapters)
  - <b>out</b> adapters:
    - persistence details + mapping (Infrastructure Adapters)
    - Presenter (ViewModel Adapters)
- Extra "Main" (<b>Config</b>) Package
  - SpringConfig //as Martin Fowler describes in his book
  - scans for persistence beans in the external "adapter" package

- ![cca with spring boot](/docs/img/boot-cca.jpg)

- ![structure](/docs/img/onion0.jpg)

### Special architecture decisions
- In the configuration, I specified that Spring scans persistence beans in the external "Adapter" package.
- The requestDTO contains values with its own @Validation (so parameter-validation is not performed on the server, which is hidden from the user).

### My differences to the base article:
- My modifications and corrections
    - 4 basic Layers ("Domain", "Application", "Infrastructure", "Main") for the project package hierarchy. 
    - I believe Aggregates (DDD) should serve as an EntryPoint to Domain-Module (not just as a Factory)
    - Domain-Entities should not be accessible outside of the domain Layer.
    - Use records (Java 17) as Value-Objects
    - <b>ArchUnit</b> for enforce DDD Onion Architecture rules.
    - Spring Config from the "Main" layer injects DI Classes (architectonic boundary)
    - Inject Interactor as a Boundary-Interface


- ![onion layers](/docs/img/onion3.JPG)
- ![onion layers interactions](/docs/img/onion2.JPG)
- 
## Alternative layers structure and encapsulation

- ![alternative layers encapsulation](/docs/img/other_layers.JPG)
- ![DDD layers](/docs/img/ddd_layers.jpg)

### Used articles as a basis:

- [Clean Architecture with Spring Boot](https://www.baeldung.com/spring-boot-clean-architecture)
- [Architecture rules & ArchUnit check](https://www.jvt.me/posts/2022/01/28/spring-boot-onion-architecture/)
