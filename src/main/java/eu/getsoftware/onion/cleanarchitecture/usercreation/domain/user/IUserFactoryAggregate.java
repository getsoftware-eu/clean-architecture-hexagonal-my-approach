package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user;

/**
 * Eugen: 
 * We created a user factory method because of two reasons:
 *
 * To stock to the stable abstractions principle 
 * and to ISOLATE the user creation.
 */
public interface IUserFactoryAggregate<T extends IUserEntity/*, Z extends IUserDTO*/>
{
    T create(String name, String password);
}
