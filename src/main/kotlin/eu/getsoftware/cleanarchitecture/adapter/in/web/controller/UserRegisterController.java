package eu.getsoftware.cleanarchitecture.adapter.in.web.controller;

import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.IUserExternalClientUseCase;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserResponseClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserRequestUseCaseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/register/user")
@RequiredArgsConstructor
public class UserRegisterController {

    private final IUserExternalClientUseCase userInputUseCase;

//    @Operation(summary  = "creates a new user from client DTO", produces = "application/json")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved greeting"),
//            @ApiResponse(responseCode = "400", description = "Invalid input provided")
//    })
    @PostMapping("/put")
    UserResponseClientDTO create(@RequestBody UserRequestUseCaseDTO requestModel) {
        
        UserResponseClientDTO responseDTO = userInputUseCase.registerNewUser(requestModel);
        
        return responseDTO; //ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
    
    @GetMapping("/{userId}")
    public UserResponseClientDTO findById(@RequestBody UserRequestUseCaseDTO requestModel, @PathVariable long userId) {
        return userInputUseCase.findExistingUserById(requestModel, userId); //.orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/demo")
    UserResponseClientDTO createDemo() {

        UserRequestUseCaseDTO sampleRequestDTO = new UserRequestUseCaseDTO(1, "name", "user", "password", "-");

        UserResponseClientDTO responseDTO = userInputUseCase.registerNewUser(sampleRequestDTO);

        return responseDTO; //ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
