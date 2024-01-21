package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

import java.time.LocalDateTime;

/**
 * RESPONSE Representation:
 * every response has generated login of requester (session)??? and creationTime
 * @param login
 * @param creationTimeStr
 */
public record UserResponseApplicationModelDTO(
      String login,
      String creationTimeStr
){
    public UserResponseApplicationModelDTO withCreationTime(String newCreationTimeStr)
    {
        return new UserResponseApplicationModelDTO(login, newCreationTimeStr);
    }
}
