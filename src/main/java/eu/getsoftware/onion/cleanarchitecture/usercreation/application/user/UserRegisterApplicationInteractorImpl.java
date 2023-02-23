package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import java.time.LocalDateTime;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserEntity;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserFactoryAggregate;

/**
 * eugen:
 * Слой of "application rules" (Use Cases). 
 * 
 * TODO: should it be a private class?
 * 
 * "use cases" are the rules related to the automatization of our system
 * In Clean Architecture, we call them Interactors.
 */
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
     * The system receives the user name and password, 
     * A1: validates if the user doesn't exist, 
     * A3: and saves the new user along with the creation time
     * (Use Cases be in different formats: as use cases or stories. We'll use a storytelling phrase:)
     * 
     * @param requestModel
     * @return
     */
    @Override
    public UserResponseApplicationModelDTO create(UserRequestApplicationModelDTO requestModel) {
        //A1
        if (userDsGateway.existsByName(requestModel.getName())) {
            return userOutputApplicationPresenter.prepareFailView("User already exists.");
        }
        //Domain creation
        UserEntity userEntity = userFactoryAggregate.create(requestModel.getName(), requestModel.getPassword());
        if (!userEntity.passwordIsValid()) {
            return userOutputApplicationPresenter.prepareFailView("User password must have more than 5 characters.");
        }
        //A3
        LocalDateTime now = LocalDateTime.now();
        UserDsRequestApplicationModelDTO userDsModel = new UserDsRequestApplicationModelDTO(userEntity.getName(), userEntity.getPassword(), now);

        userDsGateway.save(userDsModel);

        // ResponseModel
        UserResponseApplicationModelDTO accountResponseModel = new UserResponseApplicationModelDTO(userEntity.getName(), now.toString());
        return userOutputApplicationPresenter.prepareSuccessView(accountResponseModel);
    }
}