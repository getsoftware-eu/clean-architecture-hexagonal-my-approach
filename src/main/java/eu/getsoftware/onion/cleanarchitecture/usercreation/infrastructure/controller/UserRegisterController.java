package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserInputApplicationBoundary;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;

@RestController 
public class UserRegisterController {

    final IUserInputApplicationBoundary userInput;

    UserRegisterController(IUserInputApplicationBoundary accountGateway) {
        this.userInput = accountGateway;
    }

    @PostMapping("/user")
    UserResponseApplicationModelDTO create(@RequestBody UserRequestApplicationModelDTO requestModel) {
        return userInput.create(requestModel);
    }
}
