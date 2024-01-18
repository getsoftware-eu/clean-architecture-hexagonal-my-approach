package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user;

/**
 * In domain-driven design, each aggregate root has a repository. But as we said, the domain model should not be familiar with any infrastructure mechanisms. That’s why we don’t put repository implementations in the domain model but the infrastructure layer.
 * In the domain model, we put only the contracts (interfaces) of the repositories. 
 */
public interface IUserRepository {
    IUserEntityData findByName(String name);
}
