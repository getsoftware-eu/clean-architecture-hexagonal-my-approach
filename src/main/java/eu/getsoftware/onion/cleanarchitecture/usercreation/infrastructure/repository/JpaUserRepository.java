package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.repository;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapperEntity;

import java.util.Optional;

@Repository 
public interface JpaUserRepository extends IUserRepository<UserDataMapperEntity, Long>, JpaRepository<UserDataMapperEntity, Long> {

    Optional<UserDataMapperEntity> findByName(String name);
}
