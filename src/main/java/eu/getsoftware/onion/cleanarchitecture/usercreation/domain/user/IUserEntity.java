package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user;

import java.time.LocalDateTime;

/**
 * Domain Entity with business logik!
 * Only here is whole abstract business rules (isPasswordValid), not in implementing entity!!
 */
public interface IUserEntity
{
    /**
     * we use not ANEMIC, but RICH model here (Entity methods with business logik)
     * therefore Business logik is here, not in JPAEntity, that implement this interface and it's business rules!!!
     * @return high consistency rule about password
     */
    default boolean isPasswordValid() {
        var password = getPassword();
        return password != null && password.length() > 5;
    }
    
    // --- Fields for Entity!! ---
    
    String getName();
    String getPassword();
    IAddressEntity getAddress();
    LocalDateTime getCreationTime();
}
