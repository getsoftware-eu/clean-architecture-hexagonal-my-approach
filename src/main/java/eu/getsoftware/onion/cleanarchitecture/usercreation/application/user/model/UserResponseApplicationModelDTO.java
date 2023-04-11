package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

public record UserResponseApplicationModelDTO(

      String login,
      String creationTime
){
    public UserResponseApplicationModelDTO withCreationTime(String newCreationTime)
    {
        return new UserResponseApplicationModelDTO(login, newCreationTime);
    }
}
