package eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.user;


import eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.TempAddressValueObject;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.EntityIdentifier;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainFactory;
import lombok.Builder;

import java.util.UUID;

public class TempUserFactory implements IUserDomainFactory<TempUserObject> {
    
    @Override
    public TempUserObject create(String name, String password) {

        TempUserObject user = new TempUserObject(new UserId(UUID.randomUUID()));
        user.setInitValues(name);
        user.setPassword(password);
        
        TempAddressValueObject address = new TempAddressValueObject("street", "city");
        user.setAddress(address);
        
        return user;
    }

    public TempUserObject.TempUserObjectBuilder getEntityBuilder(UserId domainEntityId){
        return TempUserObject.builder().domainEntityId(domainEntityId);
    }
    
}
