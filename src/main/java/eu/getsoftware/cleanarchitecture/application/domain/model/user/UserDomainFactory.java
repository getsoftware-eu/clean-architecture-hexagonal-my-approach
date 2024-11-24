package eu.getsoftware.cleanarchitecture.application.domain.model.user;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.AddressValueObjectMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.address.AddressValueObject;

public class UserDomainFactory /*implements IUserDomainFactory<UserRootDomainEntity>*/ {

    public static UserRootDomainEntity create(String name, String email, String password) {
        
//        AddressValueObject address = AddressValueObject.from("city", "street");

//        AddressDBEmbeddable dbEntity = addressValueObjectMapper.toDb(address);
//        AddressValueObject valueObject = addressValueObjectMapper.toDomain(dbEntity);
        
        return create(name, email, password, null);
    }
    
    public static UserRootDomainEntity create(String name, String email, String password, AddressValueObject address) {
        
        UserRootDomainEntity user = UserRootDomainEntity.create(name, password);

        if(email!=null) user.setEmail(email); 
        
        if(address!=null) user.changeAddress(address);
        
        //Aggreate has no setters for these fields!
//        user.setInitValues(Map.ofEntries(Map.entry("name", name), Map.entry("password", password)));
        
        return user;
    }
}
