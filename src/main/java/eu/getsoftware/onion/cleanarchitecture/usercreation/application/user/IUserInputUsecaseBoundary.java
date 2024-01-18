package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;

/**
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
public interface IUserInputUsecaseBoundary
{
    UserResponseApplicationModelDTO create(UserRequestApplicationModelDTO requestModel);
	
	UserResponseApplicationModelDTO findById(UserRequestApplicationModelDTO requestModel, long userId);
}
