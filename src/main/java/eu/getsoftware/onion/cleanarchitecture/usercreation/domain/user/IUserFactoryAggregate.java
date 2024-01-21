package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

/**
 * Eugen: 
 * We created a user factory method because of two reasons:
 *
 * To stock to the stable abstractions principle 
 * and to isolate the user creation.
 */
public interface IUserFactoryAggregate<T extends IUserEntity, Z extends IUserDTO>
{
    Optional<T> create(String name, String password);
}
