# My vision of Clean Architecture (Hexagonal + DDD) implementation

<b>Hexagonal</b> Architecture is focused on managing the boundaries between an application and the outside world. 

Inside the 'hexagon', we can apply Domain-Driven Design (DDD) or a layered (Onion) architecture.

In this project, I use a mix of simplified Domain-Driven Design (DDD) with <b>Clean</b>-Architecture (The 'infrastructure' and 'use case' logic is grouped into separate layers).

DDD helps maintain boundaries between domains.

<b>"Clean" (Layered)</b> Architecture is another way to achieve <b>separation of concerns</b>, <b>encapsulation</b> and <b>decoupling</b> by grouping code units based on their domain and functional role within the application. 

One of the main principle of this architecture is:
<b>We should never mix business-logik("useCase") and application-logik("infrastructure") within a one service class</b>.
This is because, it becomes practically impossible to separate these two types of logic, once they are implemented (mixed) together.

## My Approach to implement domain-centric architecture: 
###  Clean-Architecture (Onion) with Java (+ Domain-driven Design, + 'Hexagon' Architecture) 

![structure](/docs/img/onion2Tree.jpg)

### Layer Structure:
1. <b>Application</b> - the boundary between the core application logic and the external world.
   - <b>Domain.model</b> layer
     - All inner-<b>business logic</b> is contained here (only fields, accessors and aggregate methods and operations)
       - Example: Consistency rules like 'isPasswordIsValid(): >5' digits... (enforcing the aggregate’s business rules)
     - Private entities, events, inner operations and public Aggregat-Roots (as entry points)
     - <u>My approach: The interface (I-Domains) of entities with (consistency) methods on these entity-projections (+ generic<T, Z> in the service: only conditions between local i-entities).</u>
   - <b>UseCase</b> (abstract) layer
     - <b>Separation of usecase-logik-methods from technical (low-level) service-help-methods</b>
     - <u>My approach: Abstract<T, Z> <b>Use Cases</b> (<b>Interactors</b> with I-Domains: create Entity, findByName, 'as a role X, I except special behavior'...)</u>
       - 1.define (or use same level-) DTOs (with other layers)
       - 2.define own (or use same level-) IGateway (technical interface-service-methods) 
       - Example: 'UserRegisterInteractor': injects und uses a (low-level) implementation of its own defined IGateway
     - Does not contain inner-business-logic (=inner rules within domain.module). But should contain interactor-logik (extern actions with entity-aggregates).
     - No spring annotation are used in our business logic (wearing with spring config).
   - <b>Infrastructure</b> (backend impl.) layer : At this point, we finished all our business. Now, let’s start plugging in our details:
     - Implementation of IGateway (technical persist-services, defined in the upper (useCase) layer)
       - Example: 'JpaUserPersistApplicationService'
     - <u>My approach: <b>abstract GatewayService</b> that use IRepository for the common CRUD methods</u>
       - setting (higher) generics with concrete (low-level) <T: EntityImpl, Z: DTOImpl>
     - Provides the technical capabilities that <b>support</b> the layers above, such as persistence or messaging.
     - Implementation of portServices 
   - <b>Port</b> interfaces for adapters (public boundaries)
     - interfaces of portServices for the outside world (adapters)
     - DTOs for outside world requests and responses
2. <b>Adapter</b> layer
   - <b>in</b> adapters:
     - specific user request (Controller Adapters)
   - <b>out</b> adapters:
     - persistence serviceImpl + entityRepository + mapping (@Entity as Adapter)
     - Presenter (ViewModel Adapters)
3. Extra "Main" (<b>Config</b>) package
   - SpringConfig classes are divided separately for the 'application', 'useCases' and 'infrastructure' layers.
     - f.e. scans for persistence beans in the external "adapter.out" package



![cca with spring boot](/docs/img/boot-cca2.jpg)


### Special architecture decisions
- <b>Domain layer:</b> organized by domain packages. Example: "users.domain"
- <b>Port Layer:</b> The (command) requestDTO contains values with its own @Validation (so parameter-validation is not performed on the server, which is hidden from the user).
- <b>Infrastructure Layer:</b> separately naming PortServices, example:"UserPortService.java" (from the name we can recognize, we implement port interface). Other internal "services" will be used here too.
- <b>UseCase Layer:</b> example "onlineUseCase.java" (from the name we can recognize, we define a useCase and it belongs to "useCase" Layer)
- <b>Main (Config) Layer:</b> I specified that Spring scans persistence beans in the external "adapter.out.persistence" package.

### My differences to the base article:
- My modifications and corrections
    - 5 basic Layers ("domain.model", "domain.usecase", "domain.infrastructure", "application.port", "adapter", "Main") for the project package hierarchy. 
    - I believe Aggregates (DDD) should serve as an EntryPoint to Domain-Module (not just as a Factory)
    - Use records (Java 17) as Value-Objects
    - <b>ArchUnit</b> for enforce DDD Onion Architecture rules.
    - Spring Config from the "Main" layer injects DI Classes (assembling project respecting architectonic boundary)
    - Inject (port)Service as a Boundary-Interface


- ![onion layers](/docs/img/clean.jpg)
- ![onion layers](/docs/img/cleanarch.jpg)
- ![onion layers interactions](/docs/img/onion3Layers.JPG)
- ![cross the architectonic boundaries](/docs/img/onion1.JPG)

## Alternative layers structure and encapsulation

- ![alternative layers encapsulation](/docs/img/other_layers.JPG)
- ![DDD layers](/docs/img/ddd_layers.jpg)

### Used articles as a basis:

- [Clean Architecture with Spring Boot](https://www.baeldung.com/spring-boot-clean-architecture)
- [Architecture rules & ArchUnit check](https://www.jvt.me/posts/2022/01/28/spring-boot-onion-architecture/)
- [Get Your Hands Dirty on Clean Architecture](https://github.com/thombergs/buckpal/tree/master)
