package eu.getsoftware.cleanarchitecture.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Eugen: maybe nur ValueObject is record
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressValueObject
{
    protected String street;

    protected String city;
}
