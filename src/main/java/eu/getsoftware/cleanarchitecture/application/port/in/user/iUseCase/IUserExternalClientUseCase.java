package eu.getsoftware.cleanarchitecture.application.port.in.user.iUseCase;

import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;

/**
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
public interface IUserExternalClientUseCase
{
    UserResponseClientDTO registerNewUser(UserRequestUseCaseDTO requestModel);
	
	UserResponseClientDTO findExistingUserById(UserRequestUseCaseDTO requestModel, long userId);
}
