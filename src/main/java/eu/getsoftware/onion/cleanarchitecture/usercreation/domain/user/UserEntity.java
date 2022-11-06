package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user;

/**
 * Eugen: maybe nur ValueObject is record
 */
public interface UserEntity
{
    boolean passwordIsValid();

    String getName();

    String getPassword();
}
