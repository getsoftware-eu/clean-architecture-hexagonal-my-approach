package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapper;

@Repository
interface JpaUserRepository extends JpaRepository<UserDataMapper, String> {
}
