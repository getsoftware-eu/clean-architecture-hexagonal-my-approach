package eu.getsoftware.cleanarchitecture.application.port.in.user.dto;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;

/**
 * eu: here I define DTO directly in application.port package
 * 
 * RESPONSE Representation:
 * every response has generated login of requester (session)??? and creationTime
 */
public record UserClientDTO(
      UserDomainId domainEntityId,
      String name,
      String creationTime
) implements IUserDomainResponseDTO {
    
    public UserClientDTO withCreationTime(String newCreationTimeStr)
    {
        return new UserClientDTO(domainEntityId, name, newCreationTimeStr);
    }

    @Override
    public boolean isPasswordValid() {
        return true;
    }
}
