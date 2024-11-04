package eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.user;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.EntityIdentifier;

import java.util.UUID;

/**
 * Value Object for DomainEntityId
 * @param value
 */

public record UserId(
    String value
) implements EntityIdentifier {
    
   public UserId(UUID userId)
    {
        this(userId.toString());
    }
}
