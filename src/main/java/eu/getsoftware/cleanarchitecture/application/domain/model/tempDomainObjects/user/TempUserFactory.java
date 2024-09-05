package eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.user;


import eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.TempAddressValueObject;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserFactory;

public class TempUserFactory implements IUserFactory<TempUserObject> {
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
