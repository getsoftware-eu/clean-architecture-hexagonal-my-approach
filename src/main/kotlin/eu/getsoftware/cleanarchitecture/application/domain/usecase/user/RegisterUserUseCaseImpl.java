package eu.getsoftware.cleanarchitecture.application.domain.usecase.user;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.UserDtoMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainFactory;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserRegisterRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserUpdateRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.IRegisterUserUseCase;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.gateways.UserGatewayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
@Service
@RequiredArgsConstructor
public class RegisterUserUseCaseImpl implements IRegisterUserUseCase
{
    private final UserGatewayService userGatewayService;
    private final IUserResponseDTOPortPresenter<UserClientDTO> userResponseDTOPortPresenter;
    private final UserDtoMapper userDtoMapper;

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
    public UserClientDTO execute(UserRegisterRequestUseCaseDTO requestUserDto) {

        requestUserDto.validateBusinessLogic();
        
        if (userGatewayService.findByField("name", requestUserDto.name()).isPresent()) {
            return userResponseDTOPortPresenter.prepareFailView("User with name " + requestUserDto.name() + " already exists.");
        }

        UserRootDomainEntity userDomainEntity = UserDomainFactory.create(requestUserDto.name(), requestUserDto.email() , requestUserDto.password());

        if (!userDomainEntity.isPasswordValid())
            return userResponseDTOPortPresenter.prepareFailView("User password must have more than 5 characters.");

        //A3 domain is correct, we can send it to lower layer for persist
        userGatewayService.saveToDb(userDomainEntity);

        UserClientDTO clientResponseDTO = userDtoMapper.toDto(userDomainEntity);
                
        return formatModelDTOForClientView(clientResponseDTO);
    }

    private UserClientDTO formatModelDTOForClientView(UserClientDTO responseDTO) {

//        IUserDomainResponseDTO clientResponseDTO = userDTOToExternalClientService.convertToResponseDTO(requestDTO);
        return userResponseDTOPortPresenter.prepareSuccessView(responseDTO);
    }
}
