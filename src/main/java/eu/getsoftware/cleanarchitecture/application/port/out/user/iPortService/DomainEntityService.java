package eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity;

import java.util.UUID;

public interface DomainEntityService<T extends IUserDomainEntity> {

    T recreateDomainEntity(UUID domainEntityId);
}
