package eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository 
public interface JpaUserRepository extends IDomainEntityGateway<UserMappedEntity, Long>, JpaRepository<UserMappedEntity, Long> {

    Optional<UserMappedEntity> findByName(String name);

    Optional<UserMappedEntity> findByDomainEntityId(UUID domainEntityId);
}
