package eu.getsoftware.cleanarchitecture.application.domain.infrastructure.portService;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDTO;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomain;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserFactory;
import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.PersistEntityGatewayServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.user.TempUserFactory;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.IUserInputPortService;
import eu.getsoftware.cleanarchitecture.application.port.out.user.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.RequestUserPortDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.ResponseUserPortDTO;

import java.time.LocalDateTime;

/**
 * eugen:
 * WRONG: Слой of "application rules" (Use Cases). 
 * Слой of "technical rules" (Service). 
 * TODO: should it be a private class?
 * 
 * "use cases" are the rules related to the automatization of our system
 * In Clean Architecture, we call them Interactors.
 */
//@Service
public abstract class UserInputDTOPortServiceAbstr<T extends IUserDomain, Z extends IUserDTO> implements IUserInputPortService
{
    private final IUserFactory<T/*, Z*/> userFactory;
    private final TempUserFactory tempModelUserFactory = new TempUserFactory();
    private final IUserResponseDTOPortPresenter userResponseDTOPortPresenter;
    private final IDomainMapper<T, Z> userDtoMapper;
    private final PersistEntityGatewayServiceAbstr<T, Z> userPersistService;
    
    /**
     * Мы еще не знаем, с какими типами мы вызовем этот абстрактный  usecase класс, поэтому все типы через T, Z 
     * @param userResponseDTOPortPresenter
     * @param userFactory
     * @param userRequestAppDTOMapper
     * @param userPersistService
     */
    public UserInputDTOPortServiceAbstr(
//            IUserRegisterApplicationDsGatewayService userRegisterDfGateway, 
            IUserFactory<T/*, Z*/> userFactory,
            IDomainMapper<T, Z> userRequestAppDTOMapper,
            PersistEntityGatewayServiceAbstr<T, Z> userPersistService,
            IUserResponseDTOPortPresenter userResponseDTOPortPresenter
    ) {
//        this.userDsGatewayService = userRegisterDfGateway;
        this.userFactory = userFactory;
        this.userDtoMapper = userRequestAppDTOMapper;
        this.userPersistService = userPersistService;
        this.userResponseDTOPortPresenter = userResponseDTOPortPresenter;
    }
    
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
     * @param userRequestDTO (session requester)
     * @return
     */
    @Override
    public ResponseUserPortDTO create(RequestUserPortDTO userRequestDTO) {
        //A1 - ONLY DTOs
        if (userPersistService.existsByName(userRequestDTO.name())) {
            return userResponseDTOPortPresenter.prepareFailView("User already exists.");
        }
        //-------------------------
        if(useLocaldomainObjectInspiteOfGenericEntity()) {
            //A2.1 temp Domain creation (if domain was local temporally entity class)
            IUserDomain tempDomainEntity = tempModelUserFactory.create(userRequestDTO.name(), userRequestDTO.password());
            if (!tempDomainEntity.isPasswordValid()) { //check if domain inner-consistency exception
                return userResponseDTOPortPresenter.prepareFailView("User password must have more than 5 characters.");
            }
        }
        //A2.2 direct domain generics creation
        // Frage: just use here local tempUserObject!! and if ok - then persist it to lower layer???
        // Answer: NO, REASON - all domain consistency logik bereits in interface methods (isPasswordValid()). Domain check is done, so we can use Generics low-types directly!!
        T domainUserEntity = userFactory.create(userRequestDTO.name(), userRequestDTO.password());
        if (!domainUserEntity.isPasswordValid()) { //check if domain inner-consistency exception
            return userResponseDTOPortPresenter.prepareFailView("User password must have more than 5 characters.");
        }
        //-------------------------
        //A3 domain is correct, we can send it to lower layer for persist
        Z userDsModelDTO = userDtoMapper.toDsRequestDTO(domainUserEntity);
        //A4 USER MANIPULATES DTO
        assert userDsModelDTO != null;
//        var userDsModelDTO2 = userDsModelDTO.withPassword("pas2");
        //saving domain in lowing layer (with JPA)
        userPersistService.persistFromDTO(userDsModelDTO);

        // ResponseModel != userDTO (UserDsRequestApplicationModelDTO)
        ResponseUserPortDTO accountResponseModel = userDtoMapper.toResponseDTOFromRequest(userDsModelDTO);
//        UserResponseApplicationModelDTO accountResponseModel = new UserResponseApplicationModelDTO(userDsModelDTO.name(), LocalDateTime.now().toString());
        return userResponseDTOPortPresenter.prepareSuccessView(accountResponseModel);
    }

    public abstract Boolean useLocaldomainObjectInspiteOfGenericEntity();
    
    @Override 
    public ResponseUserPortDTO findById(RequestUserPortDTO requestModel, long userId)
    {
        if (! userPersistService.existsByName(requestModel.name())) {
            return userResponseDTOPortPresenter.prepareFailView("User not exists.");
        }

        T userEntity = userPersistService.getById(userId);
        Z userEntityDTO = userDtoMapper.toDTO(userEntity);
//        IUserDTO userEntityDTO = userRegisterDsGatewayService.getById(userId);
//        UserDsRequestApplicationModelDTO userEntityDTO2 = userEntityDTO;
        
        // ResponseModel
        ResponseUserPortDTO accountResponseModel = new ResponseUserPortDTO(userEntityDTO.getName(), LocalDateTime.now().toString());
        return userResponseDTOPortPresenter.prepareSuccessView(accountResponseModel);
    }
}