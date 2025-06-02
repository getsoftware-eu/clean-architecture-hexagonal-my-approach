package eu.getsoftware.cleanarchitecture.adapter.in.web.controller;

import eu.getsoftware.cleanarchitecture.application.domain.model.address.AddressValueObject;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserUpdateRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iqueryservice.UserInDTOQueryService;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.UserRegisterUseCase;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserRegisterRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.UserUpdateUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserCrudController {

    private final UserRegisterUseCase registerUserUseCase;
    private final UserUpdateUseCase userInputUseCase;
    private final UserInDTOQueryService userInDTOQueryService;

    @PutMapping
    public UserClientDTO create(@Valid @RequestBody UserRegisterRequestUseCaseDTO requestModel) {
        return registerUserUseCase.execute(requestModel); //.orElseThrow(() -> new EntityNotFoundException(id));
    }
    
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
        return userInDTOQueryService.findExistingUserByName(name); //.orElseThrow(() -> new EntityNotFoundException(id));
    }

    /**
     * eu: find query - only via service, not via useCase!!!
     * 
     * @param domainId
     * @return
     */
    @GetMapping("/{userId}")
    public UserClientDTO findById(@PathVariable @Valid UserDomainId domainId) {
        return userInDTOQueryService.findExistingUserByDomainId(domainId); //.orElseThrow(() -> new EntityNotFoundException(id));
    }

    /**
     * eu: updates - via useCase
     * @param requestModel
     * @param domainId
     * @return
     */
    @PostMapping("/{userId}")
    public UserClientDTO update(@RequestBody @Valid UserUpdateRequestUseCaseDTO requestModel, 
                                @PathVariable(value = "domainId", required = false) UserDomainId domainId) {
        return userInputUseCase.updateExistingUser(requestModel); //.orElseThrow(() -> new EntityNotFoundException(id));
    }
    
 

    @PatchMapping("/{userId}/address")
    public UserClientDTO updateAddress(
            @PathVariable @Valid UserDomainId userId,
            @Valid @RequestBody AddressValueObject address) {
        return userInputUseCase.updateUserAddress(userId, address);
    }

    @GetMapping("/demo")
    ResponseEntity<UserClientDTO> createDemo() {

        System.out.println("uraaa");

        UserRegisterRequestUseCaseDTO sampleRequestDTO = new UserRegisterRequestUseCaseDTO(1, "asas", "usdsdser", "e@madd.il","password3", "-", null);

        UserClientDTO responseDTO = registerUserUseCase.execute(sampleRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
