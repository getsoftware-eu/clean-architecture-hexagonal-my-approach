package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequestModel {

    String name;
    String password;

    public UserRequestModel() {
        super();
    }

    public UserRequestModel(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }

}
