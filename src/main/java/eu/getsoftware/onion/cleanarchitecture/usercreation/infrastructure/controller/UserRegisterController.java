package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserInputBoundary;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseModel;

@RestController public
class UserRegisterController {

    final UserInputBoundary userInput;

    UserRegisterController(UserInputBoundary accountGateway) {
        this.userInput = accountGateway;
    }

    @PostMapping("/user")
    UserResponseModel create(@RequestBody UserRequestModel requestModel) {
        return userInput.create(requestModel);
    }
}
