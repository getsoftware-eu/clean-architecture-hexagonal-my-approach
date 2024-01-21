package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.tempDomainObjects;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IAddressEntity;

/**
 * Value objects are model elements that do not have a specific identification. 
 * Their identification is based on the comparison (by value) OF ALL THEIR ATTRIBUTES!!!
 * They also can contain business logic. Value objects mainly live as attributes of other entities.
 * A good example of a value object is the delivery address of the Order entity.
 */
public record TempAddressValueObject(
        String street, 
        String city
) implements IAddressEntity {

    @Override public String getStreet() {return street;}

    @Override public String getCity() {return city;}
}
