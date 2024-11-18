package eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.gateways;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository.UserRepositoryAdapter;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.GenericService;
import org.springframework.stereotype.Service;

@Service
public class UserGatewayService extends GenericService<UserRootDomainEntity, UserDomainId> {
    
    public UserGatewayService(UserRepositoryAdapter repositoryAdapter) {
        super(repositoryAdapter);
    }
}
