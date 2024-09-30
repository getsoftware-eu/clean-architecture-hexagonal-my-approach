Use Case layer (Interactors)
- It does not contain business-(entity)-logik (=inner rules in Domains). 
- But should contain interactor-logik (extern actions with Entities-Aggregates).
    - and we didn’t use any spring annotation in our business.

UseCases should use port.out.iPortService (port."in" interface for UseCases, port."out" interface for used Repositories)