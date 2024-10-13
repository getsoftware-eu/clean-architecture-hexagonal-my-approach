package eu.getsoftware.cleanarchitecture.application.domain.usecase.user;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.DomainEntityGatewayServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.DomainEntityDTOServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iUseCase.IUserExternalClientUseCase;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
@RequiredArgsConstructor
public class UserExternalClientUseCaseImpl implements IUserExternalClientUseCase
{
    private final DomainEntityGatewayServiceAbstr<UserMappedEntity> userDomainPersistService;
    private final DomainEntityDTOServiceAbstr<UserMappedEntity, UserRequestUseCaseDTO, UserResponseClientDTO> userDTOToExternalClientService;
    private final IUserResponseDTOPortPresenter<UserResponseClientDTO> userResponseDTOPortPresenter;

    /**
     * Eugen:
     * Слой of "application rules" (Use Cases). 
     *
     * The system receives the (session)user name and password, 
     * A1: validates if the user doesn't exist,  (check DTO)
     * A3: and saves the new user ENTITY along with the creation time
     * (Use Cases be in different formats: as use cases or stories. We'll use a storytelling phrase:)
     *
     * Ds - (Data Source=DTO) to lower layers(?)
     *
     *  Frage: just use here local tempUserObject!! and if ok - then persist it to lower layer???
     *  Answer: NO, REASON - all domain consistency logik bereits in interface methods (isPasswordValid()). 
     *  So Domain check is done, so we can use Generics low-types directly!!
     *
     * @return
     */
    public UserResponseClientDTO registerNewUser(UserRequestUseCaseDTO requestUserDto){

        if (userDomainPersistService.existsByName(requestUserDto.name())) {
            return userResponseDTOPortPresenter.prepareFailView("User already exists.");
        }

        UserMappedEntity newUserEntity = createUserEntity(requestUserDto);

        if (!newUserEntity.isPasswordValid())
            return userResponseDTOPortPresenter.prepareFailView("User password must have more than 5 characters.");

        //A3 domain is correct, we can send it to lower layer for persist
        userDomainPersistService.saveEntity(newUserEntity); //only save request, no response!!!

        UserResponseClientDTO clientResponseDTO = userDTOToExternalClientService.convertToResponseDTO(newUserEntity);
                
        return formatModelDTOForClientView(clientResponseDTO);
    }

    @NotNull
    private UserMappedEntity createUserEntity(UserRequestUseCaseDTO requestUserDto) {
        UserMappedEntity newUserEntity = userDomainPersistService.createEntity(requestUserDto.name());
        newUserEntity.setPassword(requestUserDto.password());
//        userDomainPersistService.saveEntity(newUserEntity);
        return newUserEntity;
    }

    @Override 
    public UserResponseClientDTO findExistingUserById(UserRequestUseCaseDTO requestModel, long userId)
    {
        if (! userDomainPersistService.existsByName(requestModel.name())) {
            return userResponseDTOPortPresenter.prepareFailView("User not exists.");
        }

        UserResponseClientDTO userResponseDTO = userDTOToExternalClientService.getModelDTOById(userId);

        return formatModelDTOForClientView(userResponseDTO);
    }

    private UserResponseClientDTO formatModelDTOForClientView(UserResponseClientDTO responseDTO) {

//        IUserDomainResponseDTO clientResponseDTO = userDTOToExternalClientService.convertToResponseDTO(requestDTO);
        return userResponseDTOPortPresenter.prepareSuccessView(responseDTO);
    }
}
