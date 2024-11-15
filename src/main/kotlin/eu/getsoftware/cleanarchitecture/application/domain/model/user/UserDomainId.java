package eu.getsoftware.cleanarchitecture.application.domain.model.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

/**
 * Value Object for DomainEntityId
 */
@Value
@Getter
public class UserDomainId implements EntityIdentifier {

    @NonNull
    String uuidValue;
    
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
}
