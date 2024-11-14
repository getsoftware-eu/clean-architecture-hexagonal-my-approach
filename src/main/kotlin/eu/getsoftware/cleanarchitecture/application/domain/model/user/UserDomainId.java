package eu.getsoftware.cleanarchitecture.application.domain.model.user;

import java.util.UUID;

/**
 * Value Object for DomainEntityId
 * @param uuidValue
 */

public record UserDomainId(
    String uuidValue
) implements EntityIdentifier {
    
   public UserDomainId(UUID userId)
    {
        this(userId.toString());
    }

    public UserDomainId { //Eu: primary constructor!!! without parameters
        if (uuidValue == null || uuidValue.isBlank()) {
            throw new IllegalArgumentException("uuidValue cannot be null or blank");
        }
    }
}
