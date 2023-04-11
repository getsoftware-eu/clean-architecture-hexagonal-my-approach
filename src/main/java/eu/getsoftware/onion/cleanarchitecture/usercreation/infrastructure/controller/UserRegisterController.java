package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserInputApplicationBoundary;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.error.UserNotFoundException;
import io.swagger.annotations.ApiOperation;

@RestController("/api/v1/register/user")
public class UserRegisterController {

    final IUserInputApplicationBoundary userInput;
    private UserRegisterController jpaUserRepository;
    
    UserRegisterController(IUserInputApplicationBoundary accountGateway) {
        this.userInput = accountGateway;
    }

    @ApiOperation(value = "creates a new user from client DTO", produces = "application/json")
    @PostMapping("/put")
    UserResponseApplicationModelDTO create(@RequestBody UserRequestApplicationModelDTO requestModel) {
        
        UserResponseApplicationModelDTO responseDTO = userInput.create(requestModel);
        
        return responseDTO; //ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    
    @GetMapping("/{id}")
    public UserResponseApplicationModelDTO findById(@RequestBody UserRequestApplicationModelDTO requestModel, @PathVariable long userId) {
        
        return userInput.findById(requestModel, userId);
                //.orElseThrow(() -> new UserNotFoundException(id));
    }
}
