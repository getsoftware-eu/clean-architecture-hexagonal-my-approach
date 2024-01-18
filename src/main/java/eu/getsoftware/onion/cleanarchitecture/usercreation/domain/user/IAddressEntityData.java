package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.UserAddresValueObject;

/**
 * Eugen: maybe nur ValueObject is record
 */
public interface IAddressEntityData
{
    String street();

    String city();
}
