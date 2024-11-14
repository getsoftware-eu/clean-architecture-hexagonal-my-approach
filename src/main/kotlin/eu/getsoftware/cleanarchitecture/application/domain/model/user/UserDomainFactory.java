package eu.getsoftware.cleanarchitecture.application.domain.model.user;



import eu.getsoftware.cleanarchitecture.application.domain.model.AddressValueObject;

import java.util.UUID;

public class UserDomainFactory implements IUserDomainFactory<UserDomainEntity> {
    
    @Override
    public UserDomainEntity create(String name, String password) {
        
        AddressValueObject address = AddressValueObject.builder()
                .street("street")
                .city("city")
                .build();
        
        return create(name, password, address);
    }
    
    public UserDomainEntity create(String name, String password, AddressValueObject address) {
        
        UserDomainEntity user = UserDomainEntity.builder()
                .domainEntityId(new UserDomainId(UUID.randomUUID()))
                .address(address)
                .build();
        
        //Aggreate has no setters for these fields!
        user.setInitValues(name, password);
        
        return user;
    }

    public UserDomainEntity.UserDomainEntityBuilder getEntityBuilder(UserDomainId domainEntityId){
        return UserDomainEntity.builder().domainEntityId(domainEntityId);
    }
    
}
