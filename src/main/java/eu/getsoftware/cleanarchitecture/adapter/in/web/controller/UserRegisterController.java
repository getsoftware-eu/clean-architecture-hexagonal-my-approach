package eu.getsoftware.cleanarchitecture.adapter.in.web.controller;

import eu.getsoftware.cleanarchitecture.application.port.user.in.iservice.IUserInputPortService;
import eu.getsoftware.cleanarchitecture.application.port.user.in.dto.RequestUserPortDTO;
import eu.getsoftware.cleanarchitecture.application.port.user.out.ResponseUserPortDTO;
import eu.getsoftware.cleanarchitecture.application.domain.service.user.impl.UserInputPortServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/register/user")
public class UserRegisterController {

    final IUserInputPortService userInputUseCase;

    UserRegisterController(
        UserInputPortServiceImpl userUsecases
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
