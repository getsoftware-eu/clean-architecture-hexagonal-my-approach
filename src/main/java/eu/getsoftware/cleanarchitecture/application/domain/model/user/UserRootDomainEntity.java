package eu.getsoftware.cleanarchitecture.application.domain.model.user;

import eu.getsoftware.cleanarchitecture.application.domain.model.AddressValueObject;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Domain Entity with business logik!
 * eu: We need setters for MapStruct or for Builder... :((
 * Only here is whole abstract business rules (isPasswordValid), not in implementing entity!!
 */
@Getter
//@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class UserRootDomainEntity /*implements IRootDomainEntity*/
{
    // --- Fields for Entity!! ---
    protected final UserDomainId domainEntityId;
    
    public UserRootDomainEntity(UserDomainId domainEntityId)
    {
        this.domainEntityId = domainEntityId;
    }
    
    @NotNull
    protected String name;
    protected String password;
    protected String email;
    public LocalDateTime creationTime;
    protected AddressValueObject address;

    public String getEmail() {
        return email;
    }

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

    // Статический фабричный метод
    public static UserRootDomainEntity create(String name, String password) {
        UserRootDomainEntity entity = new UserRootDomainEntity(UserDomainId.generate());

        entity.setName(name);
        entity.setPassword(password);

        entity.creationTime = LocalDateTime.now();
        
        return entity;
    }

    public void setName(String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        
        this.name = name;
    }

    public void setEmail(String newEmail) {
        if (newEmail == null || newEmail.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = newEmail;
    }
    

    public void setPassword(String newPassword) {
        if (newPassword == null || newPassword.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        this.password = newPassword;
    }

//    @Override
    public void setInitValues(Map<String, String> fieldToValues) {
        
        if(fieldToValues.containsKey("name"))
            setName(fieldToValues.get("name"));
        
        if(fieldToValues.containsKey("password"))
            setPassword(fieldToValues.get("password"));

        if(!isPasswordValid())
        {
            throw new IllegalArgumentException("password " + password);
        }
        
        //TODO eu: I can't send domain rabbitMQ notification in domain-interface, only in this impl Class!
        //domainEventsProducerService.sendDomainNotification(convertToUserDomainDTO(this), DomainTopicEvent.USER_CREATED);

    }

    public void changeAddress(AddressValueObject address) {

        if (address == null 
                || address.getCity().isEmpty()) {
            throw new IllegalArgumentException("address not valid");
        }
       
        this.address = address;
    }
}
