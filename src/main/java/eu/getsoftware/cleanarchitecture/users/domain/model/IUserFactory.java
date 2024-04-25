package eu.getsoftware.cleanarchitecture.users.domain.model;

/**
 * Eugen: 
 * We created a user factory method because of two reasons:
 *
 * To stock to the stable abstractions principle 
 * and to ISOLATE the user creation.
 */
public interface IUserFactory<T extends IUserDomain/*, Z extends IUserDTO*/>
{
    T create(String name, String password);
}
