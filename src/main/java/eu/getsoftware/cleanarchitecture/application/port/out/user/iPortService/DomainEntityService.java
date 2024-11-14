package eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.EntityIdentifier;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainEntity;

import java.util.UUID;

public interface DomainEntityService<I extends EntityIdentifier> {

    UserDomainEntity recreateDomainEntity(I domainEntityId);
}
