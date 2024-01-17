package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

/**
 * RESPONSE Representation:
 * every response has generated login of requester (session)??? and creationTime
 * @param login
 * @param creationTime
 */
public record UserResponseApplicationModelDTO(
      String login,
      String creationTime
){
    public UserResponseApplicationModelDTO withCreationTime(String newCreationTime)
    {
        return new UserResponseApplicationModelDTO(login, newCreationTime);
    }
}
