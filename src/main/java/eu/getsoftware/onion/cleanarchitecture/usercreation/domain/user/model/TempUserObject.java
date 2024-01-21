package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Eugen:
 * Layer of "business rules" + Entity (data):
 * 
 * Package PRIVACY: should be not public OUTSIDE PACKAGE
 * 
 */
@Getter
class TempUserObject implements IUserEntity
{
    String name;
    String password;
    LocalDateTime creationTime;
    TempAddressValueObject address;

    @Override
    public void setInitValues(String name) {
        this.name = name;
        this.creationTime = LocalDateTime.now();
    }
}
