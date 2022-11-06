package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDsRequestModel {

    String name;
    String password;
    LocalDateTime creationTime;

    public UserDsRequestModel(String name, String password, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
    }
}
