package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user;

public interface IUserEntity
{
    String getName();

    String getPassword();
    IAddressEntity getAddress();
    
    String getCreationTime();

    /**
     * not anemic, but rich model here (Entity methods with business logik)
     * therefore Business logik here, not in JPAEntity, that implement this interface!!!
     * @return
     */
    default  boolean passwordIsValid() {
        var password = getPassword();
        return password != null && password.length() > 5;
    }
}
