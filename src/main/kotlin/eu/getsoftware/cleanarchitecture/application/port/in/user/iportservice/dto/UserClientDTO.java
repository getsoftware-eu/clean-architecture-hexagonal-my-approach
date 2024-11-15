package eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;

/**
 * eu: here I define DTO directly in application.port package
 * 
 * RESPONSE Representation:
 * every response has generated login of requester (session)??? and creationTime
 * @param login
 */
public record UserClientDTO(
      UserDomainId domainId,
      String login,
      String name,
      String creationTime
) implements IUserDomainResponseDTO {
    
    public UserClientDTO withCreationTime(String newCreationTimeStr)
    {
        return new UserClientDTO(domainId, login, name, newCreationTimeStr);
    }

    @Override
    public boolean isPasswordValid() {
        return true;
    }
}
