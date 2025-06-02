package eu.getsoftware.cleanarchitecture.application.port.in.user.iusecase;

import eu.getsoftware.cleanarchitecture.application.domain.model.address.AddressValueObject;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserClientDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserUpdateRequestUseCaseDTO;

/**
 * eu: in.port only DTO, no inner entity
 * Команды (Commands) идут через UseCase.
 * 
 * DTO creation for Grenzen
 * The boundaries are contracts defining how components can interact. 
 * The input boundary exposes our use case to outer layers:
 */
public interface UserCrudUseCase
{
    UserClientDTO updateExistingUser(UserUpdateRequestUseCaseDTO requestModel);
    
    UserClientDTO updateUserAddress(UserDomainId userDomainId, AddressValueObject addressValueObject);
}
