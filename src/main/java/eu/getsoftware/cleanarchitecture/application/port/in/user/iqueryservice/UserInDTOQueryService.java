package eu.getsoftware.cleanarchitecture.application.port.in.user.iqueryservice;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserClientDTO;

/**
 * Команды (Commands) идут через UseCase.
 * Запросы (Queries) идут через QueryService.
 */
public interface UserInDTOQueryService {
    
    UserClientDTO findExistingUserByName(String searchName);
    UserClientDTO findExistingUserByDomainId(UserDomainId userId);
}
