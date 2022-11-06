package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseModel;

/**
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
public interface UserInputBoundary {
    UserResponseModel create(UserRequestModel requestModel);
}
