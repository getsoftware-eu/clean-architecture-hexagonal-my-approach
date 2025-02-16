package eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.UserEntityMapper;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAdapter
        extends GenericRepositoryJpaAdapter<UserRootDomainEntity, UserMappedDBEntity, UserDomainId> {

    public UserRepositoryAdapter(UserJpaRepository repository, UserEntityMapper mapper) {
        super(repository, mapper);
    }
}
