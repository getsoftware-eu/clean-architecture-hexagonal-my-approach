package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.usecases;

import java.time.LocalDateTime;
import java.util.Optional;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.*;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.domainservice.IEntityMapper;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.domainservice.IRegisterService;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserInputUsecaseBoundary;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserOutputApplicationPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserFactoryAggregate;

/**
 * eugen:
 * Слой of "application rules" (Use Cases). 
 * 
 * TODO: should it be a private class?
 * 
 * "use cases" are the rules related to the automatization of our system
 * In Clean Architecture, we call them Interactors.
 */
//@Service
public abstract class UserRegisterUsecaseInteractorAbstr<T extends IUserEntity, Z extends IUserDTO> implements IUserInputUsecaseBoundary
{
//    private final IUserRegisterApplicationDsGatewayService userDsGatewayService;
    private final IUserFactoryAggregate<T, Z> userFactoryAggregate;
    private final IUserOutputApplicationPresenter userOutputApplicationPresenter;
    private final IEntityMapper<T, Z> userDtoMapper;
    private final IRegisterService<T, Z> userRegisterDsGatewayService;

    /**
     * Мы еще не знаем, с какими типами мы вызовем этот абстрактный  usecase класс, поэтому все типы через T, Z 
     * @param userOutputApplicationPresenter
     * @param userFactoryAggregate
     * @param userDsRequestMapper
     * @param userRegisterDsGatewayService
     */
    public UserRegisterUsecaseInteractorAbstr(
//            IUserRegisterApplicationDsGatewayService userRegisterDfGateway, 
            IUserOutputApplicationPresenter userOutputApplicationPresenter,
            IUserFactoryAggregate<T, Z> userFactoryAggregate,
            IEntityMapper<T, Z> userDsRequestMapper,
            IRegisterService<T, Z> userRegisterDsGatewayService) {
//        this.userDsGatewayService = userRegisterDfGateway;
        this.userOutputApplicationPresenter = userOutputApplicationPresenter;
        this.userFactoryAggregate = userFactoryAggregate;
        this.userDtoMapper = userDsRequestMapper;
        this.userRegisterDsGatewayService = userRegisterDsGatewayService;
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
     * @param requestModel (session requester)
     * @return
     */
    @Override
    public UserResponseApplicationModelDTO create(UserRequestApplicationModelDTO requestModel) {
        //A1 - ONLY DTOs
        if (userRegisterDsGatewayService.existsByName(requestModel.name())) {
            return userOutputApplicationPresenter.prepareFailView("User already exists.");
        }
        //Domain creation
        T userEntity = userFactoryAggregate.create(requestModel.name(), requestModel.password());
        if (!userEntity.isPasswordValid()) {
            return userOutputApplicationPresenter.prepareFailView("User password must have more than 5 characters.");
        }
        //A3
//        IUserDTO userDsModelDTO = userDtoMapper.toDsRequestDTO(userEntity);
        Z userDsModelDTO = userDtoMapper.toDsRequestDTO(userEntity);
//        var userDTO = (IUserDTO) userDsModelDTO;
//        IRegisterService<T = UserDataMapperEntity, Z = UserDsRequestApplicationModelDTO>
//        Z saveDTo = (Z) userDsModelDTO;
        assert userDsModelDTO != null;
        userRegisterDsGatewayService.save(userDsModelDTO);

        // ResponseModel != userDTO (UserDsRequestApplicationModelDTO)
        UserResponseApplicationModelDTO accountResponseModel = userDtoMapper.toResponseDTOFromRequest(userDsModelDTO);
//        UserResponseApplicationModelDTO accountResponseModel = new UserResponseApplicationModelDTO(userDsModelDTO.name(), LocalDateTime.now().toString());
        return userOutputApplicationPresenter.prepareSuccessView(accountResponseModel);
    }
    
    @Override 
    public UserResponseApplicationModelDTO findById(UserRequestApplicationModelDTO requestModel, long userId)
    {
        if (! userRegisterDsGatewayService.existsByName(requestModel.name())) {
            return userOutputApplicationPresenter.prepareFailView("User not exists.");
        }

        T userEntity = userRegisterDsGatewayService.getById(userId);
        Z userEntityDTO = userDtoMapper.toDTO(userEntity);
//        IUserDTO userEntityDTO = userRegisterDsGatewayService.getById(userId);
//        UserDsRequestApplicationModelDTO userEntityDTO2 = userEntityDTO;
        
        // ResponseModel
        UserResponseApplicationModelDTO accountResponseModel = new UserResponseApplicationModelDTO(userEntityDTO.getName(), LocalDateTime.now().toString());
        return userOutputApplicationPresenter.prepareSuccessView(accountResponseModel);
    }
}