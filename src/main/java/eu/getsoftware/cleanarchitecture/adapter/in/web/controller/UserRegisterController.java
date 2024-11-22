package eu.getsoftware.cleanarchitecture.adapter.in.web.controller;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserUpdateRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.IRegisterUserUseCase;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserRegisterRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.IUserUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/api/v1/register/user")
@RequiredArgsConstructor
public class UserRegisterController {

    private final IRegisterUserUseCase registerUserInputUseCase;
    private final IUserUseCase userInputUseCase;
    
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
    @GetMapping("/name")
    public UserClientDTO findByName(@NotEmpty @PathVariable String name) {
        return userInputUseCase.findExistingUserByName(name); //.orElseThrow(() -> new UserNotFoundException(id));
    }      
    
    @GetMapping("/{userId}")
    public UserClientDTO findById(@Valid @RequestBody UserDomainId domainId) {
        return userInputUseCase.findExistingUserByDomainId(domainId); //.orElseThrow(() -> new UserNotFoundException(id));
    }  
    
    @PostMapping("/{userId}")
    public UserClientDTO update(@Valid @ModelAttribute UserUpdateRequestUseCaseDTO requestModel, @PathVariable(value = "domainId", required = false) UserDomainId domainId) {
        return userInputUseCase.updateExistingUser(requestModel); //.orElseThrow(() -> new UserNotFoundException(id));
    }
    
    @PutMapping("/")
    public UserClientDTO create(@Valid @ModelAttribute UserUpdateRequestUseCaseDTO requestModel) {
        return userInputUseCase.updateExistingUser(requestModel); //.orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/demo")
    ResponseEntity<UserClientDTO> createDemo() {

        System.out.println("uraaa");

        UserRegisterRequestUseCaseDTO sampleRequestDTO = new UserRegisterRequestUseCaseDTO(1, "asas", "usdsdser", "e@madd.il","password3", "-", null);

        UserClientDTO responseDTO = registerUserInputUseCase.execute(sampleRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
