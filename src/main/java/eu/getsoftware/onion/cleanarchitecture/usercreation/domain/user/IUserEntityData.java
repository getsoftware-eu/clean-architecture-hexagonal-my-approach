package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.UserAddresValueObject;

/**
 * Eugen: maybe nur ValueObject is record
 */
public interface IUserEntityData
{
    String getName();

    String getPassword();
    UserAddresValueObject getAddress();
}
