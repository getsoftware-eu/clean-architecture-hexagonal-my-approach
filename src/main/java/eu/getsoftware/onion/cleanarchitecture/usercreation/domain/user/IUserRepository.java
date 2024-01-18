package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user;

/**
 * In domain-driven design, each aggregate root has a repository. But as we said, the domain model should not be familiar with any infrastructure mechanisms. That’s why we don’t put repository implementations in the domain model but the infrastructure layer.
 * In the domain model, we put only the contracts (interfaces) of the repositories. 
 *
 * Maybe interface for Repository is redundant, because we should not use repository directly and should make all actions via extra entityService (implemented in infrastructure-layer)!
 */
public interface IUserRepository {
    IUserEntityData findByName(String name);
}
