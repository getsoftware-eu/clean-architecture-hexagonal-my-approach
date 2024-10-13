package eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO;

/**
 * eu: here I define DTO directly in application.port package
 * 
 * RESPONSE Representation:
 * every response has generated login of requester (session)??? and creationTime
 * @param login
 * @param creationTimeStr
 */
public record UserResponseClientDTO(
      long entityId,
      String login,
      String name,
      String creationTimeStr
) implements IUserDomainResponseDTO {
    
    public UserResponseClientDTO withCreationTime(String newCreationTimeStr)
    {
        return new UserResponseClientDTO(entityId, login, name, newCreationTimeStr);
    }

    @Override
    public boolean isPasswordValid() {
        return true;
    }
}
