package eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserUpdateRequestUseCaseDTO;

/**
 * eu: in.port only DTO, no inner entity
 * 
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
public interface IUserUseCase
{
    UserClientDTO updateExistingUser(UserUpdateRequestUseCaseDTO requestModel);
	
	UserClientDTO findExistingUserByName(UserClientDTO requestModel);
	
    UserClientDTO findExistingUserByDomainId(UserDomainId userId);
}
