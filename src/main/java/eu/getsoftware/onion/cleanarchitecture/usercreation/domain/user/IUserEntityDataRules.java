package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user;

/**
 * extra interface layer for internal entity business rules!!!
 */
public interface IUserEntityDataRules extends IUserEntityData
{
    /**
     * not anemic, but rich model here (Entity methods with business logik)
     * therefore, JPAEntity cannot simple implement this interface!!!
     * @return
     */
    boolean passwordIsValid();

}
