package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.service

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDtoMapper
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.domainservice.IRegisterService
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapperEntity
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.repository.JpaUserRepository
import org.springframework.stereotype.Service

@Service
class UserRegService(
    val userRepository: JpaUserRepository,
    mapper: UserDtoMapper,
    override val assetClass: Class<UserDataMapperEntity> = UserDataMapperEntity::class.java
): IRegisterService<UserDataMapperEntity, UserDsRequestApplicationModelDTO>(mapper, userRepository) {

    fun existsByName(name: String?): Boolean {
        return userRepository.findByName(name).isPresent
    } 

}