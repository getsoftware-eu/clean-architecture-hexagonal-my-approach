package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.usecases;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserInputApplicationBoundary;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterApplicationDsGateway;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserOutputApplicationPresenter;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserEntity;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserFactoryAggregate;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.error.UserNotFoundException;

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
    private final UserRegisterApplicationDsGateway userDsGateway;
    private final UserFactoryAggregate userFactoryAggregate;
    private final UserOutputApplicationPresenter userOutputApplicationPresenter;
    
    public UserRegisterApplicationInteractorImpl(UserRegisterApplicationDsGateway userRegisterDfGateway, UserOutputApplicationPresenter userOutputApplicationPresenter,
        UserFactoryAggregate userFactoryAggregate) {
        this.userDsGateway = userRegisterDfGateway;
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
        if (userDsGateway.existsByName(requestModel.name())) {
            return userOutputApplicationPresenter.prepareFailView("User already exists.");
        }
        //Domain creation
        UserEntity userEntity = userFactoryAggregate.create(requestModel.name(), requestModel.password());
        if (!userEntity.passwordIsValid()) {
            return userOutputApplicationPresenter.prepareFailView("User password must have more than 5 characters.");
        }
        //A3
        LocalDateTime now = LocalDateTime.now();
        UserDsRequestApplicationModelDTO userDsModelDTO = new UserDsRequestApplicationModelDTO(userEntity.getName(), userEntity.getPassword(), now);

        userDsGateway.save(userDsModelDTO);

        // ResponseModel != userDTO (UserDsRequestApplicationModelDTO)
        UserResponseApplicationModelDTO accountResponseModel = new UserResponseApplicationModelDTO(userDsModelDTO.name(), LocalDateTime.now().toString());
        return userOutputApplicationPresenter.prepareSuccessView(accountResponseModel);
    }
    
    @Override 
    public UserResponseApplicationModelDTO findById(UserRequestApplicationModelDTO requestModel, long userId)
    {
        if (! userDsGateway.existsByName(requestModel.name())) {
            return userOutputApplicationPresenter.prepareFailView("User not exists.");
        }
        
        UserDsRequestApplicationModelDTO userEntityDTO = userDsGateway.getById(userId);
        
        // ResponseModel
        UserResponseApplicationModelDTO accountResponseModel = new UserResponseApplicationModelDTO(userEntityDTO.name(), LocalDateTime.now().toString());
        return userOutputApplicationPresenter.prepareSuccessView(accountResponseModel);
    }
}