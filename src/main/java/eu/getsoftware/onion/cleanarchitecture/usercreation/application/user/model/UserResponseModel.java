package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponseModel {

    String login;
    String creationTime;

    public UserResponseModel(String login, String creationTime) {
        this.login = login;
        this.creationTime = creationTime;
    }
}
