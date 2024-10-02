package eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService.gateways;

import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainPersistGatewayService;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomain;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iUseCase.dto.RequestUserUseCaseDTO;

public interface IRegisterUserPortGatewayService extends IDomainPersistGatewayService<IUserDomain, RequestUserUseCaseDTO> {
    
    boolean existsByName(String name);

}
