package eu.getsoftware.cleanarchitecture.users.domain.model.tempDomainObjects;


import eu.getsoftware.cleanarchitecture.users.domain.model.IUserFactory;

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
