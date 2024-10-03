package eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService;

import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainDTOGateway;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainDTO;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iUseCase.IUserExternalClientUseCase;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import lombok.RequiredArgsConstructor;

/**
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
@RequiredArgsConstructor
public class UserExternalClientUseCaseImpl implements IUserExternalClientUseCase
{
    private final IUserDTOExternalClientService<IUserDomainEntity, IUserDomainDTO> userDTOToExternalClientService;
    private final IDomainDTOGateway<IUserDomainEntity, IUserDomainDTO> userDomainEntityService;
    private final IUserResponseDTOPortPresenter userResponseDTOPortPresenter;

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
    public UserResponseClientDTO registerNewUser(UserRequestUseCaseDTO requestModel){

        if (userDomainEntityService.existsByName(requestModel.name())) {
            return userResponseDTOPortPresenter.prepareFailView("User already exists.");
        }

        IUserDomainEntity newUserEntity = userDTOToExternalClientService.createNewEntity(requestModel);
        
        if (!newUserEntity.isPasswordValid())
            return userResponseDTOPortPresenter.prepareFailView("User password must have more than 5 characters.");

        IUserDomainDTO newUserDomainDTO = userDTOToExternalClientService.convertToModelDTO(newUserEntity);
        
        assert newUserDomainDTO != null;

//        if(newUserDomainDTO != null){
//            throw new RuntimeException("error creation")
//        }

        //A3 domain is correct, we can send it to lower layer for persist
        userDomainEntityService.persistFromDTO(newUserDomainDTO);

        return formatModelDTOForClientView(newUserDomainDTO);
    }

    @Override 
    public UserResponseClientDTO findExistingUserById(UserRequestUseCaseDTO requestModel, long userId)
    {
        if (! userDomainEntityService.existsByName(requestModel.name())) {
            return userResponseDTOPortPresenter.prepareFailView("User not exists.");
        }

        IUserDomainDTO userDomainDTO =  userDomainEntityService.getModelDTOById(userId);

        return formatModelDTOForClientView(userDomainDTO);
    }

    private UserResponseClientDTO formatModelDTOForClientView(IUserDomainDTO newUserDomainDTO) {

        UserResponseClientDTO clientResponseDTO = userDTOToExternalClientService.convertToClientDTO(newUserDomainDTO);
        return userResponseDTOPortPresenter.prepareSuccessView(clientResponseDTO);
    }
}
