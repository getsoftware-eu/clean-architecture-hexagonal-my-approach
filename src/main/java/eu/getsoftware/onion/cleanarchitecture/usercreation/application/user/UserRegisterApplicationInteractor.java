package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import java.time.LocalDateTime;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestApplicationModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModel;
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
public class UserRegisterApplicationInteractor implements UserInputApplicationBoundary
{
    private final UserRegisterApplicationDsGateway userDsGateway;
    private final UserFactoryAggregate userFactoryAggregate;
    private final UserOutputApplicationPresenter userOutputApplicationPresenter;
    
    public UserRegisterApplicationInteractor(UserRegisterApplicationDsGateway userRegisterDfGateway, UserOutputApplicationPresenter userOutputApplicationPresenter,
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
    public UserResponseApplicationModel create(UserRequestApplicationModel requestModel) {
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
        UserDsRequestApplicationModel userDsModel = new UserDsRequestApplicationModel(userEntity.getName(), userEntity.getPassword(), now);

        userDsGateway.save(userDsModel);

        // ResponseModel
        UserResponseApplicationModel accountResponseModel = new UserResponseApplicationModel(userEntity.getName(), now.toString());
        return userOutputApplicationPresenter.prepareSuccessView(accountResponseModel);
    }
}