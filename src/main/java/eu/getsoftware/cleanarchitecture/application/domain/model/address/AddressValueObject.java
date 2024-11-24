package eu.getsoftware.cleanarchitecture.application.domain.model.address;

import jakarta.validation.constraints.NotEmpty;

/**
 * Eugen: maybe nur ValueObject is record
 */
//@Value(staticConstructor="from")
public record AddressValueObject(
        @NotEmpty String street,
        @NotEmpty String city
) {

    public static AddressValueObject from(@NotEmpty String city, @NotEmpty String street) {
        return new AddressValueObject(city, street);
    }
}
