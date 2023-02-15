# my-onion-clean-architecture

## My view of implementing an Onion "Clean-Architecture" with Java (Domain-driven Design, 'Hexagon' architecture) 

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
- ![onion layers](/docs/img/onion3.JPG)
- ![onion layers interactions](/docs/img/onion2.JPG)
- ![alternative layers encapsulation](/docs/img/onion4.JPG)

einzigen Eintrittspunkt in das Package, sodass alles andere als protected gekenn-
zeichnet werden kann. Der große Nachteil hierbei ist, dass nichts anderes in der

Codebasis außerhalb dieses Pakets auf Informationen zugreifen kann, die sich auf
Bestellungen (Orders) beziehen, es sei denn, es durchläuft den Controller – und
das kann entweder wünschenswert sein oder auch nicht.
Beim Ports and Adapters-Ansatz weisen die Schnittstellen OrdersService und
Orders eingehende Abhängigkeiten von anderen Packages auf, deshalb müssen

sie als public gekennzeichnet werden. Auch hier gilt, dass die Implementierungs-
klassen zur Laufzeit paketgeschützt sein und mit Dependency Injection versehen

werden können.

Und schließlich hat die OrdersComponent-Schnittstelle beim Package by Compo-
nent-Ansatz eine eingehende Abhängigkeit vom Controller,

### Used Article as a basis:

- [Clean Architecture with Spring Boot](https://www.baeldung.com/spring-boot-clean-architecture)
