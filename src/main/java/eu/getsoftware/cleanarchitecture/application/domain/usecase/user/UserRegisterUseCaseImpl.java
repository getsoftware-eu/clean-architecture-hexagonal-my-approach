package eu.getsoftware.cleanarchitecture.application.domain.usecase.user;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.AddressValueObjectMapper;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.UserDtoMapper;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.service.UserOutRepositoryQueryAdapter;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.service.UserOutRepositoryUpdateAdapter;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainFactory;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserRegisterRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase.UserRegisterUseCase;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
@Service
@RequiredArgsConstructor
public class UserRegisterUseCaseImpl implements UserRegisterUseCase
{
    private final UserOutRepositoryQueryAdapter userOutRepositoryQueryAdapter;
    private final UserOutRepositoryUpdateAdapter userOutRepositoryUpdateAdapter;
    private final IUserResponseDTOPortPresenter userResponseDTOPortPresenter;
    private final UserDtoMapper userDtoMapper;
    private final AddressValueObjectMapper addressValueObjectMapper;

//    UserDomainFactory userDomainFactory = new UserDomainFactory();

//    RegisterUserUseCaseImpl(UserGatewayService userGatewayService, IUserResponseDTOPortPresenter userResponseDTOPortPresenter, UserDtoMapper userDtoMapper)
//    {
//        this.userGatewayService = userGatewayService;
//        this.userResponseDTOPortPresenter = userResponseDTOPortPresenter;
//        this.userDtoMapper = userDtoMapper;
//    }
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
    public UserClientDTO execute(@Validated UserRegisterRequestUseCaseDTO requestUserDto) {

        //requestUserDto.validateBusinessLogic(); - intern
        
        if (userOutRepositoryQueryAdapter.findByField("name", requestUserDto.name()).isPresent()) {
            return userResponseDTOPortPresenter.prepareFailView("User with name " + requestUserDto.name() + " already exists.");
        }

        UserRootDomainEntity userDomainEntity = UserDomainFactory.create(requestUserDto.name(), requestUserDto.email() , requestUserDto.password());

        if (!userDomainEntity.isPasswordValid())
            return userResponseDTOPortPresenter.prepareFailView("User password must have more than 5 characters.");

        //A3 domain is correct, we can send it to lower layer for persist
        userOutRepositoryUpdateAdapter.convertAndPersist(userDomainEntity);

        return userDtoMapper.toDto(userDomainEntity);
                
//        return formatModelDTOForClientView(clientResponseDTO);
    }

    
}
