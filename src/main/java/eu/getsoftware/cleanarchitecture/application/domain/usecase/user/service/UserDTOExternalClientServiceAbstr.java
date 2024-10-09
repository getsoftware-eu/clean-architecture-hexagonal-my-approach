package eu.getsoftware.cleanarchitecture.application.domain.usecase.user.service;

import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.user.TempUserFactory;
import eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.user.TempUserObject;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainRequestDTO;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainFactory;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.IUserDTOExternalClientHelperService;
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
public abstract class UserDTOExternalClientServiceAbstr<T extends IUserDomainEntity, I extends IUserDomainRequestDTO, O extends IUserDomainResponseDTO> implements IUserDTOExternalClientHelperService<T, I, O>
{
    private final IUserDomainFactory<T/*, Z*/> userDomainFactory;
    private final TempUserFactory tempModelUserFactory = new TempUserFactory();
    private final IDomainMapper<T, I, O> userDomainDtoMapper;
    
    /**
     * Мы еще не знаем, с какими типами мы вызовем этот абстрактный  usecase класс, поэтому все типы через T, Z 
     * @param userDomainFactory
     * @param userRequestAppDTOMapper
     */
    public UserDTOExternalClientServiceAbstr(
            IUserDomainFactory<T/*, Z*/> userDomainFactory,
            IDomainMapper<T, I, O> userRequestAppDTOMapper
    ) {
        this.userDomainFactory = userDomainFactory;
        this.userDomainDtoMapper = userRequestAppDTOMapper;
    }

//    @NotNull
    @Override //eu: never direct convert requestDTO to responseDTO ! 
    public O convertToResponseDTO(T entity) {
        return userDomainDtoMapper.toResponseDTO(entity);
    }

    
//    @NotNull
//    @Override //eu: never direct convert requestDTO to responseDTO ! 
//    public O convertToResponseDTO(I userRequestDTO) {
//        O accountResponseModel = userDomainDtoMapper.toResponseDTO(userRequestDTO);
//        return accountResponseModel;
//    }
    
//    @Override
//    @Nullable
//    public I convertToRequestDTO(T entity) {
//        return userDomainDtoMapper.toDsRequestDTO(entity);
//    }

    @Override
    public T createNewEntity(UserRequestUseCaseDTO userRequestDTO) {
        
//        if(useLocaldomainObjectInspiteOfGenericEntity()) {
//            //A2.1 temp Domain creation (if domain was local temporally entity class)
//            return tempModelUserFactory.create(userRequestDTO.name(), userRequestDTO.password());
//        }
//        else 
        {
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