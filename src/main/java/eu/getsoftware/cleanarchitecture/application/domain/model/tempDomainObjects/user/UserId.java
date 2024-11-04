package eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.user;

import java.util.UUID;

/**
 * Value Object for DomainEntityId
 * @param userId
 */

public record UserId(
    String userId
) {
    
   public UserId(UUID userId)
    {
        this(userId.toString());
    }
}
