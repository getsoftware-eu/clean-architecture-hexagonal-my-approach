package eu.getsoftware.cleanarchitecture.adapter.in.web.controller;

import eu.getsoftware.cleanarchitecture.application.port.in.user.iUseCase.IUserExternalClientUseCase;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/register/user")
@RequiredArgsConstructor
public class UserRegisterController {

    final IUserExternalClientUseCase userInputUseCase;

    @ApiOperation(value = "creates a new user from client DTO", produces = "application/json")
    @PostMapping("/put")
    UserResponseClientDTO create(@RequestBody UserRequestUseCaseDTO requestModel) {
        
        UserResponseClientDTO responseDTO = userInputUseCase.registerNewUser(requestModel);
        
        return responseDTO; //ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    
    @GetMapping("/{userId}")
    public UserResponseClientDTO findById(@RequestBody UserRequestUseCaseDTO requestModel, @PathVariable long userId) {
        return userInputUseCase.findExistingUserById(requestModel, userId); //.orElseThrow(() -> new UserNotFoundException(id));
    }
}
