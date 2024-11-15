package eu.getsoftware.cleanarchitecture.application.domain.model;

import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * Eugen: maybe nur ValueObject is record
 */
@Value
//@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AddressValueObject
{
    @With String street = "";
    @With String city = "";
}
