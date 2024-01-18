package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.usecases;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserInputApplicationBoundary;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserRegisterApplicationDsGatewayService;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserOutputApplicationPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntityDataRules;
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
@Service
class UserRegisterApplicationInteractorImpl implements IUserInputApplicationBoundary
{
    private final IUserRegisterApplicationDsGatewayService userDsGatewayService;
    private final IUserFactoryAggregate userFactoryAggregate;
    private final IUserOutputApplicationPresenter userOutputApplicationPresenter;
    
    public UserRegisterApplicationInteractorImpl(IUserRegisterApplicationDsGatewayService userRegisterDfGateway, IUserOutputApplicationPresenter userOutputApplicationPresenter,
                                                 IUserFactoryAggregate userFactoryAggregate) {
        this.userDsGatewayService = userRegisterDfGateway;
        this.userOutputApplicationPresenter = userOutputApplicationPresenter;
        this.userFactoryAggregate = userFactoryAggregate;
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
        if (userDsGatewayService.existsByName(requestModel.name())) {
            return userOutputApplicationPresenter.prepareFailView("User already exists.");
        }
        //Domain creation
        IUserEntityDataRules userEntity = userFactoryAggregate.create(requestModel.name(), requestModel.password());
        if (!userEntity.passwordIsValid()) {
            return userOutputApplicationPresenter.prepareFailView("User password must have more than 5 characters.");
        }
        //A3
        LocalDateTime now = LocalDateTime.now();
        UserDsRequestApplicationModelDTO userDsModelDTO = new UserDsRequestApplicationModelDTO(userEntity.getName(), userEntity.getPassword(), now);

        userDsGatewayService.save(userDsModelDTO);

        // ResponseModel != userDTO (UserDsRequestApplicationModelDTO)
        UserResponseApplicationModelDTO accountResponseModel = new UserResponseApplicationModelDTO(userDsModelDTO.name(), LocalDateTime.now().toString());
        return userOutputApplicationPresenter.prepareSuccessView(accountResponseModel);
    }
    
    @Override 
    public UserResponseApplicationModelDTO findById(UserRequestApplicationModelDTO requestModel, long userId)
    {
        if (! userDsGatewayService.existsByName(requestModel.name())) {
            return userOutputApplicationPresenter.prepareFailView("User not exists.");
        }
        
        UserDsRequestApplicationModelDTO userEntityDTO = userDsGatewayService.getById(userId);
        
        // ResponseModel
        UserResponseApplicationModelDTO accountResponseModel = new UserResponseApplicationModelDTO(userEntityDTO.name(), LocalDateTime.now().toString());
        return userOutputApplicationPresenter.prepareSuccessView(accountResponseModel);
    }
}