package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapperEntity;

@Repository 
public interface JpaUserRepository extends JpaRepository<UserDataMapperEntity, String> {
}
