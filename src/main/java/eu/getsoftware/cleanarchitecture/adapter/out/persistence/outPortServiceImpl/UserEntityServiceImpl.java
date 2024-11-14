package eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository.JpaUserRepository;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainFactory;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService.DomainEntityService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserEntityServiceImpl implements DomainEntityService<UserDomainId> {

    private final UserDomainFactory userDomainFactory;
    private final JpaUserRepository jpaUserRepository;
    
    public UserMappedDBEntity getDbEntity(UserDomainId domainId){

        return jpaUserRepository.findByDomainEntityId(domainId)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    public UserDomainEntity recreateDomainEntity(UserDomainId domainId){

        UserMappedDBEntity userDbProjection = getDbEntity(domainId);

        return userDomainFactory.getEntityBuilder(userDbProjection.getDomainEntityId())
                .name(userDbProjection.getName())
                .password(userDbProjection.getPassword())
                .creationTime(userDbProjection.getCreationTime())
                .build();
    }
}
