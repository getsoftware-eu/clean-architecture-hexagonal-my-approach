package eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService;

import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.user.TempUserFactory;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainDTO;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainFactory;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.IUserDTOExternalClientService;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * eugen:
 * Layer of application port.in (Service). 
 * 
 * "use cases" are the rules related to the automatization of our system
 * In Clean Architecture, we call them Interactors.
 */
//@Service
public abstract class UserDTOExternalClientServiceAbstr<T extends IUserDomainEntity, Z extends IUserDomainDTO> implements IUserDTOExternalClientService<T, Z>
{
    private final IUserDomainFactory<T/*, Z*/> userDomainFactory;
    private final TempUserFactory tempModelUserFactory = new TempUserFactory();
    private final IDomainMapper<T, Z> userDomainDtoMapper;
    
    /**
     * Мы еще не знаем, с какими типами мы вызовем этот абстрактный  usecase класс, поэтому все типы через T, Z 
     * @param userDomainFactory
     * @param userRequestAppDTOMapper
     */
    public UserDTOExternalClientServiceAbstr(
            IUserDomainFactory<T/*, Z*/> userDomainFactory,
            IDomainMapper<T, Z> userRequestAppDTOMapper
    ) {
        this.userDomainFactory = userDomainFactory;
        this.userDomainDtoMapper = userRequestAppDTOMapper;
    }

    @NotNull
    @Override
    public UserResponseClientDTO convertToClientDTO(Z userModelDTO) {
        UserResponseClientDTO accountResponseModel = userDomainDtoMapper.toResponseDTOFromRequest(userModelDTO);
        return accountResponseModel;
    }
    
    @Override
    @Nullable
    public Z convertToModelDTO(T entity) {
        return userDomainDtoMapper.toDsRequestDTO(entity);
    }

    @Override
    public IUserDomainEntity createNewEntity(UserRequestUseCaseDTO userRequestDTO) {
        
        if(useLocaldomainObjectInspiteOfGenericEntity()) {
            //A2.1 temp Domain creation (if domain was local temporally entity class)
            return tempModelUserFactory.create(userRequestDTO.name(), userRequestDTO.password());
        }
        else {
            //A2.2 direct domain generics creation
            // Frage: just use here local tempUserObject!! and if ok - then persist it to lower layer???
            // Answer: NO, REASON - all domain consistency logik bereits in interface methods (isPasswordValid()). Domain check is done, so we can use Generics low-types directly!!
            return userDomainFactory.create(userRequestDTO.name(), userRequestDTO.password());
        }
    }

    public abstract Boolean useLocaldomainObjectInspiteOfGenericEntity();
    
//    @NotNull
//    private UserAsClientResponseDTO getUserDTO_clientFormatted(T userEntity) {
//        Z userEntityDTO = userDomainDtoMapper.toDTO(userEntity);
//        // IUserDTO userEntityDTO = userRegisterDsGatewayService.getById(userId);
//        // UserDsRequestApplicationModelDTO userEntityDTO2 = userEntityDTO;
//
//        // ResponseModel
//        UserAsClientResponseDTO accountResponseModel = new UserAsClientResponseDTO(userEntityDTO.name(), LocalDateTime.now().toString(), userEntityDTO.name(), userEntityDTO.password());
////        return userResponseDTOPortPresenter.prepareSuccessView(accountResponseModel);
//    }
}