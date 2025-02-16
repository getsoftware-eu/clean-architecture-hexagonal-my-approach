package eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository 
public interface UserJpaRepository extends JpaRepository<UserMappedDBEntity, Long> {

    Optional<UserMappedDBEntity> findByName(String name);

    Optional<UserMappedDBEntity> findByDomainEntityId(UserDomainId domainEntityId);
}
