package eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository.JpaUserRepository;
import eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.user.TempUserFactory;
import eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.user.TempUserObject;
import eu.getsoftware.cleanarchitecture.application.domain.model.tempDomainObjects.user.UserId;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService.DomainEntityService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UserEntityServiceImpl implements DomainEntityService<TempUserObject> {

    private final TempUserFactory tempUserFactory;
    private final JpaUserRepository jpaUserRepository;

    public TempUserObject recreateDomainEntity(UUID domainId){

        UserMappedEntity dbEntityProjection = jpaUserRepository.findByDomainEntityId(domainId)
                .orElseThrow(() -> new RuntimeException("user not found"));
         
        return tempUserFactory.getEntityBuilder(new UserId(domainId))
                .name(dbEntityProjection.getName())
                .password(dbEntityProjection.getPassword())
                .creationTime(dbEntityProjection.getCreationTime())
                .build();
   
    }
    
}
