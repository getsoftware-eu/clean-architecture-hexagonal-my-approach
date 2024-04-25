package eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.service

import eu.getsoftware.cleanarchitecture.users.domain.service.RegisterServiceAbstr
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity
import eu.getsoftware.cleanarchitecture.users.domain.model.IDomainRepository
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.app.dto.RequestUserAppDTO
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.RequestUserAppDTOMapper
import org.springframework.stereotype.Service

/**
 * Difference to Struktura: 
 * 1. we define IService in iDomain
 * 2. use service.methods() in UseCases (usecase level)
 * 3. we implement and inject serviceImpl erst in infrastucture(backend)
 * 
 * In Struktura:
 * 1. 'StrukturaServices' have abstract<Generic> and (low) assetsServiceImpl levels
 * 2. But 'StrukturaServices' contains useCase-logik + serviceMethods() together!
 * because serviceMethods() are for (Sa, Sb) UNIQUE and these methods() external called from (abstract)controller as part of 'StrukturaServices' contract //no need for multi-layer usage
 */
@Service
class UserRegisterPortServiceImpl(
    val userRepository: IDomainRepository<UserMappedEntity, Long>,
    mapper: RequestUserAppDTOMapper,
    override val assetClass: Class<UserMappedEntity> = UserMappedEntity::class.java
): RegisterServiceAbstr<UserMappedEntity, RequestUserAppDTO>(mapper, userRepository) {

    override fun existsByName(name: String): Boolean {
        return userRepository.findByName(name).isPresent
    }

}