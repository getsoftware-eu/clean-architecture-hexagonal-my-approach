package eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository 
public interface JpaUserRepository extends IDomainRepository<UserMappedEntity, Long>, JpaRepository<UserMappedEntity, Long> {

    Optional<UserMappedEntity> findByName(String name);
}
