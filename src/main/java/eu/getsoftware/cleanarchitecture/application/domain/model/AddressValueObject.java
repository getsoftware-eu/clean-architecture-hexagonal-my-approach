package eu.getsoftware.cleanarchitecture.application.domain.model;

import jakarta.persistence.Embeddable;
import lombok.*;

/**
 * Eugen: maybe nur ValueObject is record
 */
@Value
//@Getter
//@NoArgsConstructor
@AllArgsConstructor
public class AddressValueObject
{
    String street = "";
    String city = "";
}
