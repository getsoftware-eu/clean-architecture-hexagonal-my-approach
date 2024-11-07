package eu.getsoftware.cleanarchitecture.application.domain.model.user;

import eu.getsoftware.cleanarchitecture.application.domain.model.IAddressDomain;

import java.time.LocalDateTime;

/**
 * Domain Entity with business logik!
 * Only here is whole abstract business rules (isPasswordValid), not in implementing entity!!
 */
public interface IUserDomainEntity
{
    /**
     * eu: JE HOCHER VALIDATION TO MODEL, DESTO MEHR WERDEN ES automatisch benutzen.
     * VALIDATAION in einem Service ist nur begrenzt nutzbar!!!
     * 
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

    EntityIdentifier getDomainEntityId();
    String getName();
    String getPassword();
    IAddressDomain getAddress();
    LocalDateTime getCreationTime();
}
