package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import java.time.LocalDateTime;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserEntity;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserFactoryAggregator;

/**
 * eugen:
 * Слой of "application rules" (Use Cases). 
 * 
 * TODO: should it be a private class?
 * 
 * "use cases" are the rules related to the automatization of our system
 * In Clean Architecture, we call them Interactors.
 */
public class UserRegisterInteractor implements UserInputBoundary
{

    final UserRegisterDsGateway userDsGateway;
    final UserOutputPresenter userOutputPresenter;
    final UserFactoryAggregator userFactoryAggregator;

    public UserRegisterInteractor(UserRegisterDsGateway userRegisterDfGateway, UserOutputPresenter userOutputPresenter,
        UserFactoryAggregator userFactoryAggregator) {
        this.userDsGateway = userRegisterDfGateway;
        this.userOutputPresenter = userOutputPresenter;
        this.userFactoryAggregator = userFactoryAggregator;
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
    public UserResponseModel create(UserRequestModel requestModel) {
        //A1
        if (userDsGateway.existsByName(requestModel.getName())) {
            return userOutputPresenter.prepareFailView("User already exists.");
        }
        //Domain creation
        UserEntity userEntity = userFactoryAggregator.create(requestModel.getName(), requestModel.getPassword());
        if (!userEntity.passwordIsValid()) {
            return userOutputPresenter.prepareFailView("User password must have more than 5 characters.");
        }
        //A3
        LocalDateTime now = LocalDateTime.now();
        UserDsRequestModel userDsModel = new UserDsRequestModel(userEntity.getName(), userEntity.getPassword(), now);

        userDsGateway.save(userDsModel);

        // ResponseModel
        UserResponseModel accountResponseModel = new UserResponseModel(userEntity.getName(), now.toString());
        return userOutputPresenter.prepareSuccessView(accountResponseModel);
    }
}