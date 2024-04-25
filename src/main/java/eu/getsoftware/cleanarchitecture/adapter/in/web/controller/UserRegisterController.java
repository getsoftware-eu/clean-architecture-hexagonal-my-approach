package eu.getsoftware.cleanarchitecture.adapter.in.web.controller;

import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.usecases.IUserInputPortUseCase;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto.RequestUserPortDTO;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto.ResponseUserPortDTO;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.usecases.impl.UserInputPortUseCaseImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/register/user")
public class UserRegisterController {

    final IUserInputPortUseCase userInputUseCase;

    UserRegisterController(
        UserInputPortUseCaseImpl userUsecases
    ) {
        this.userInputUseCase = userUsecases;
    }

    @ApiOperation(value = "creates a new user from client DTO", produces = "application/json")
    @PostMapping("/put")
    ResponseUserPortDTO create(@RequestBody RequestUserPortDTO requestModel) {
        
        ResponseUserPortDTO responseDTO = userInputUseCase.create(requestModel);
        
        return responseDTO; //ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    
    @GetMapping("/{userId}")
    public ResponseUserPortDTO findById(@RequestBody RequestUserPortDTO requestModel, @PathVariable long userId) {
        return userInputUseCase.findById(requestModel, userId); //.orElseThrow(() -> new UserNotFoundException(id));
    }
}
