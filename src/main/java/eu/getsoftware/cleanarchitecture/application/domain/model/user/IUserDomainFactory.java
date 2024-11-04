package eu.getsoftware.cleanarchitecture.application.domain.model.user;

import lombok.Builder;

import java.util.UUID;

/**
 * Eugen: 
 * We created a user factory method because of two reasons:
 *
 * To stock to the stable abstractions principle 
 * and to ISOLATE the user creation.
 */
public interface IUserDomainFactory<T extends IUserDomainEntity/*, Z extends IUserDTO*/>
{
    T create(String name, String password);

//    <B extends Object, I extends EntityIdentifier> B getEntityBuilder(I domainEntityId);
}
