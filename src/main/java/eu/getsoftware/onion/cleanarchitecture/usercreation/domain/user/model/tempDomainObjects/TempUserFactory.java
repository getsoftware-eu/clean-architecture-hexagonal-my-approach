package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.tempDomainObjects;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserFactoryAggregate;

public class TempUserFactory implements IUserFactoryAggregate<TempUserObject> {
    @Override
    public TempUserObject create(String name, String password) {

        TempUserObject user = new TempUserObject();
        user.setInitValues(name);
        user.setPassword(password);
        
        TempAddressValueObject address = new TempAddressValueObject("street", "city");
        user.setAddress(address);
        
        return user;
    }
}
