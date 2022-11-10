package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserInputApplicationBoundary;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestApplicationModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModel;

@RestController 
public class UserRegisterController {

    final UserInputApplicationBoundary userInput;

    UserRegisterController(UserInputApplicationBoundary accountGateway) {
        this.userInput = accountGateway;
    }

    @PostMapping("/user")
    UserResponseApplicationModel create(@RequestBody UserRequestApplicationModel requestModel) {
        return userInput.create(requestModel);
    }
}
