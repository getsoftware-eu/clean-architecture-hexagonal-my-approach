package eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.EntityIdentifier;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainEntity;

public interface DomainEntityService<I extends EntityIdentifier> {

    UserDomainEntity recreateDomainEntity(I domainEntityId);
}
