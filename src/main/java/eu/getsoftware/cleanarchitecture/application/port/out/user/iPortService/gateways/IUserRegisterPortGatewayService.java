package eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService.gateways;

import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainRegisterDTOGateway;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;

/**
 * eu: out.port can use entity, not only DTO (out port used only local im DDD)
 */
public interface IUserRegisterPortGatewayService extends IDomainRegisterDTOGateway<IUserDomainEntity, UserRequestUseCaseDTO, UserResponseClientDTO> {
    
    boolean userExistsByName(String name);

}
