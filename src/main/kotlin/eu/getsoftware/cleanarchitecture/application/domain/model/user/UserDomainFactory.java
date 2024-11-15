package eu.getsoftware.cleanarchitecture.application.domain.model.user;

import eu.getsoftware.cleanarchitecture.application.domain.model.AddressValueObject;

public class UserDomainFactory /*implements IUserDomainFactory<UserRootDomainEntity>*/ {
    
    public static UserRootDomainEntity create(String name, String email, String password) {
        
        AddressValueObject address = new AddressValueObject()
                                .withCity("city")
                                .withStreet("street");
        
        return create(name, email, password, address);
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
