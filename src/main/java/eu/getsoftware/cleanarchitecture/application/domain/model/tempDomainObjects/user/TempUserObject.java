package eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.user;

import eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.TempAddressValueObject;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Eugen:
 * Layer of "business rules" + Entity (data):
 * 
 * Package PRIVACY: should be not public OUTSIDE PACKAGE
 * 
 */
@Getter
@Setter
public class TempUserObject implements IUserDomainEntity
{
    String name;
    String password;
    LocalDateTime creationTime;
    TempAddressValueObject address;

    @Override
    public void setInitValues(String name) {
        this.name = name;
        this.creationTime = LocalDateTime.now();

        //TODO eu: I can't send domain rabbitMQ notification in domain-interface, only in this impl Class!
        //domainEventsProducerService.sendDomainNotification(convertToUserDomainDTO(this), DomainTopicEvent.USER_CREATED);
        
    }
    
    //TODO @Value userId with own validation(?)
}
