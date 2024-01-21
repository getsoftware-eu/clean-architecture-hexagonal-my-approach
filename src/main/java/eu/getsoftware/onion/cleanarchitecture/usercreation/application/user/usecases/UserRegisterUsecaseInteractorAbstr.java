package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.usecases;

import java.time.LocalDateTime;

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
    private final IUserFactoryAggregate<T, Z> userFactory;
    private final IUserOutputApplicationPresenter userOutputApplicationPresenter;
    private final IEntityMapper<T, Z> userDtoMapper;
    private final IRegisterService<T, Z> userRegisterDsGatewayService;

    /**
     * Мы еще не знаем, с какими типами мы вызовем этот абстрактный  usecase класс, поэтому все типы через T, Z 
     * @param userOutputApplicationPresenter
     * @param userFactory
     * @param userDsRequestMapper
     * @param userRegisterDsGatewayService
     */
    public UserRegisterUsecaseInteractorAbstr(
//            IUserRegisterApplicationDsGatewayService userRegisterDfGateway, 
            IUserOutputApplicationPresenter userOutputApplicationPresenter,
            IUserFactoryAggregate<T, Z> userFactory,
            IEntityMapper<T, Z> userDsRequestMapper,
            IRegisterService<T, Z> userRegisterDsGatewayService) {
//        this.userDsGatewayService = userRegisterDfGateway;
        this.userOutputApplicationPresenter = userOutputApplicationPresenter;
        this.userFactory = userFactory;
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
     * Ds - to lower layers(?)
     * 
     * @param userRequestDTO (session requester)
     * @return
     */
    @Override
    public UserResponseApplicationModelDTO create(UserRequestApplicationModelDTO userRequestDTO) {
        //A1 - ONLY DTOs
        if (userRegisterDsGatewayService.existsByName(userRequestDTO.name())) {
            return userOutputApplicationPresenter.prepareFailView("User already exists.");
        }
        //A2 Domain creation (if domain was local temporally entity class)
        // Frage: just use local domainDTO and then persist it to lower layer???
        T domainUserEntity = userFactory.create(userRequestDTO.name(), userRequestDTO.password());
        if (!domainUserEntity.isPasswordValid()) { //check if domain inner-consistency exception
            return userOutputApplicationPresenter.prepareFailView("User password must have more than 5 characters.");
        }
        //A3 domain is correct, we can send it to lower layer for persist
        Z userDsModelDTO = userDtoMapper.toDsRequestDTO(domainUserEntity);
        assert userDsModelDTO != null;
        //saving domain in lowing layer (with JPA)
        userRegisterDsGatewayService.persistFromDTO(userDsModelDTO);

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