package eu.getsoftware.cleanarchitecture.application.domain.usecase.user;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelinnerservice.DomainEntityGatewayServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelinnerservice.DomainEntityDTOServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserResponseClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.IUserExternalClientUseCase;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.gateways.IPersistHelperPortService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
@Service
@RequiredArgsConstructor
public class UserExternalClientUseCaseImpl implements IUserExternalClientUseCase
{
    private final DomainEntityGatewayServiceAbstr<UserMappedDBEntity> userDomainPersistService;
    private final DomainEntityDTOServiceAbstr<UserMappedDBEntity, UserRequestUseCaseDTO, UserResponseClientDTO> userDTOToExternalClientService;
    private final IUserResponseDTOPortPresenter<UserResponseClientDTO> userResponseDTOPortPresenter;
    private final IPersistHelperPortService<UserMappedDBEntity> userPersistHelperPortService;

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
     *  Frage: just use here local UserDomainEntity!! and if ok - then persist it to lower layer???
     *  Answer: NO, REASON - all domain consistency logik bereits in interface methods (isPasswordValid()). 
     *  So Domain check is done, so we can use Generics low-types directly!!
     *
     * @return
     */
    public UserResponseClientDTO registerNewUser(UserRequestUseCaseDTO requestUserDto){

        if (userDomainPersistService.existsByName(requestUserDto.name())) {
            return userResponseDTOPortPresenter.prepareFailView("User already exists.");
        }

        UserMappedDBEntity newUserEntity = userPersistHelperPortService.createCustomEntityWithProps(requestUserDto);

        if (!newUserEntity.isPasswordValid())
            return userResponseDTOPortPresenter.prepareFailView("User password must have more than 5 characters.");

        //A3 domain is correct, we can send it to lower layer for persist
        userDomainPersistService.saveEntity(newUserEntity); //only save request, no response!!!

        UserResponseClientDTO clientResponseDTO = userDTOToExternalClientService.convertToResponseDTO(newUserEntity);
                
        return formatModelDTOForClientView(clientResponseDTO);
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
