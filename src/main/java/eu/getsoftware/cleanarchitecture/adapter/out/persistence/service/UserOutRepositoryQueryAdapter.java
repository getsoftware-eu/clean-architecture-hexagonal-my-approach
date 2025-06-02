package eu.getsoftware.cleanarchitecture.adapter.out.persistence.service;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.UserEntityMapper;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository.UserJpaRepository;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import eu.getsoftware.cleanarchitecture.application.domain.usecase.user.service.GenericOutRepositoryQueryAdapter;
import org.springframework.stereotype.Component;

@Component
public class UserOutRepositoryQueryAdapter
        extends GenericOutRepositoryQueryAdapter<UserRootDomainEntity, UserMappedDBEntity, UserDomainId> {

    public UserOutRepositoryQueryAdapter(UserJpaRepository repository, UserEntityMapper mapper) {
        super(repository, mapper);
    }
}
