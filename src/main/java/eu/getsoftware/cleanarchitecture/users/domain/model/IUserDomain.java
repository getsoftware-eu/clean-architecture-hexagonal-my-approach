package eu.getsoftware.cleanarchitecture.users.domain.model;

import java.time.LocalDateTime;

/**
 * Domain Entity with business logik!
 * Only here is whole abstract business rules (isPasswordValid), not in implementing entity!!
 */
public interface IUserDomain
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

    void setInitValues(String name);
    // --- Fields for Entity!! ---
    
    String getName();
    String getPassword();
    IAddressDomain getAddress();
    LocalDateTime getCreationTime();
}
