package eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.UserEntityMapper;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.GenericRepositoryAdapter;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAdapter
        extends GenericRepositoryAdapter<UserRootDomainEntity, UserMappedDBEntity, UserDomainId> {

    public UserRepositoryAdapter(UserJpaRepository repository, UserEntityMapper mapper) {
        super(repository, mapper);
    }
}
