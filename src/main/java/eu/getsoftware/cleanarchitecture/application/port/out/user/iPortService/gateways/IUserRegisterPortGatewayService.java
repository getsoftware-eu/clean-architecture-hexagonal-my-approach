package eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService.gateways;

import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainDTOGateway;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;

public interface IUserRegisterPortGatewayService extends IDomainDTOGateway<IUserDomainEntity, UserRequestUseCaseDTO> {
    
    boolean userExistsByName(String name);

}
