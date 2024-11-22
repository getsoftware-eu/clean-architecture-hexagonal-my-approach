package eu.getsoftware.cleanarchitecture.adapter.in.web.controller;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserUpdateRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.IRegisterUserUseCase;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserRegisterRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.IUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/api/v1/register/user")
@RequiredArgsConstructor
public class UserRegisterController {

    private final IRegisterUserUseCase registerUserInputUseCase;
    private final IUserUseCase userInputUseCase;

//    UserRegisterController(IRegisterUserUseCase registerUserInputUseCase,  IUserUseCase userInputUseCase) {
//        this.registerUserInputUseCase = registerUserInputUseCase;
//        this.userInputUseCase = userInputUseCase;
//    }
    
//    @Operation(summary  = "creates a new user from client DTO", produces = "application/json")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved greeting"),
//            @ApiResponse(responseCode = "400", description = "Invalid input provided")
//    })
//    @PostMapping("/put")
//    UserClientDTO create(@Valid @RequestBody UserRegisterRequestUseCaseDTO requestModel) {
//        
//        UserClientDTO responseDTO = registerUserInputUseCase.execute(requestModel);
//        
//        return responseDTO; //ResponseEntity.status(HttpStatus.OK).body(responseDTO);
//    }
//    
//    @GetMapping("/name")
//    public UserClientDTO findByName(@Valid @RequestBody UserClientDTO requestModel) {
//        return userInputUseCase.findExistingUserByName(requestModel); //.orElseThrow(() -> new UserNotFoundException(id));
//    }      
    
//    @GetMapping("/{userId}")
//    public UserClientDTO findById(@Valid @RequestBody String domainId) {
//        return userInputUseCase.findExistingUserByDomainId(domainId); //.orElseThrow(() -> new UserNotFoundException(id));
//    }  
    
//    @PutMapping("/{userId}")
//    public UserClientDTO update(@Valid @RequestBody UserUpdateRequestUseCaseDTO requestModel) {
//        return userInputUseCase.updateExistingUser(requestModel); //.orElseThrow(() -> new UserNotFoundException(id));
//    }

    @GetMapping("/demo")
    UserClientDTO createDemo() {

        System.out.println("uraaa");

        UserRegisterRequestUseCaseDTO sampleRequestDTO = new UserRegisterRequestUseCaseDTO(1, "name", "user", "e@ma.il","password2", "-", null);

        UserClientDTO responseDTO = registerUserInputUseCase.execute(sampleRequestDTO);

        return responseDTO; //ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
