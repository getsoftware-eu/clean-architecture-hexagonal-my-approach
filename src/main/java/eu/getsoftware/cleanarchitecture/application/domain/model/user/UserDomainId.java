package eu.getsoftware.cleanarchitecture.application.domain.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

/**
 * Value Object for DomainEntityId
 */
//@Value(staticConstructor="of") 
//@RequiredArgsConstructor(staticName="of")
public record UserDomainId(
        String uuidValue
) implements EntityIdentifier 
{

    @JsonCreator // Spring должен десериализовать JSON в объект UserDomainId. Используйте библиотеку Jackson, которая по умолчанию интегрирована в Spring Boot:
    public UserDomainId(String uuidValue) {
        if (uuidValue == null || uuidValue.isEmpty()) {
            throw new IllegalArgumentException("uuidValue cannot be null or empty");
        }
        this.uuidValue = uuidValue;
    }
    
    public static UserDomainId from(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("UserDomainId cannot be null or empty");
        }
        return new UserDomainId(value);
    }

    public static UserDomainId from(@NotNull UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("UserDomainId cannot be null or empty");
        }
        return new UserDomainId(uuid.toString());
    }

    public static UserDomainId generate() {
        return new UserDomainId(UUID.randomUUID().toString());
    }

    //@Override
//    public String getUuidValue() {
//        return this.uuidValue;
//    }
}
