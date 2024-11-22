package eu.getsoftware.cleanarchitecture.application.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

/**
 * Eugen: maybe nur ValueObject is record
 */
//@Value(staticConstructor="from")
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public record AddressValueObject(
        @NotEmpty String street,
        @NotEmpty String city
) {

    @JsonCreator
    // Spring должен десериализовать JSON в объект UserDomainId. Используйте библиотеку Jackson, которая по умолчанию интегрирована в Spring Boot:
    public AddressValueObject {
    }

    public static AddressValueObject from(@NotEmpty String city, @NotEmpty String street) {
        return new AddressValueObject(city, street);
    }
}
