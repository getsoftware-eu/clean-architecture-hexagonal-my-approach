package eu.getsoftware.cleanarchitecture.application.domain.model.user;

import eu.getsoftware.cleanarchitecture.application.domain.model.address.AddressValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Domain Entity with business logik!
 * Only here is whole abstract business rules (isPasswordValid), not in implementing entity!!
 */
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDomainEntity
{
    // --- Fields for Entity!! ---
    protected UserDomainId domainEntityId;
    protected String name;
    protected String password;
    protected LocalDateTime creationTime;
    protected AddressValueObject address;
    
    /**
     * eu: JE HOCHER VALIDATION TO MODEL, DESTO MEHR WERDEN ES automatisch benutzen.
     * VALIDATAION in einem Service ist nur begrenzt nutzbar!!!
     * 
     * we use not ANEMIC, but RICH model here (Entity methods with business logik)
     * therefore Business logik is here, not in JPAEntity, that implement this interface and it's business rules!!!
     * @return high consistency rule about password
     */
    public boolean isPasswordValid() {
        return password != null && password.length() > 5;
    }

    public void setInitValues(String name) {
        setInitValues(name, "initpassword");
    }
    
    public void setInitValues(String name, String password) {
        this.name = name;
        this.creationTime = LocalDateTime.now();

        this.password = password;
        
        if(!isPasswordValid())
        {
            throw new IllegalArgumentException("password " + password);
        }
        
        //TODO eu: I can't send domain rabbitMQ notification in domain-interface, only in this impl Class!
        //domainEventsProducerService.sendDomainNotification(convertToUserDomainDTO(this), DomainTopicEvent.USER_CREATED);

    }
    
}
