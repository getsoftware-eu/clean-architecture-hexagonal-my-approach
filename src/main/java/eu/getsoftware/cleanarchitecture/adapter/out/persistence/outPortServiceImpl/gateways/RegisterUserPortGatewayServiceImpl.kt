package eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl.gateways

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.RequestUserAppDTOMapper
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository.JpaUserRepository
import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.PersistEntityGatewayServiceAbstr
import eu.getsoftware.cleanarchitecture.application.port.`in`.user.iUseCase.dto.RequestUserUseCaseDTO
import org.springframework.stereotype.Service

//TODO eu: is it application-layer usecase or just implementing infrastruktur-layer with missing technical details???

/**
 * Gateway serviceImpl, that call repository methods to get and persist entity
 * 
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
class RegisterUserPortGatewayServiceImpl (
//    val userRepository: IDomainRepository<UserMappedEntity, Long>,
    val userRepository: JpaUserRepository,
    mapper: RequestUserAppDTOMapper,
    override val assetClass: Class<UserMappedEntity> = UserMappedEntity::class.java
): 
//    IRegisterUserPortGatewayService, 
    PersistEntityGatewayServiceAbstr<UserMappedEntity, RequestUserUseCaseDTO>(mapper, userRepository) {

    //eu: smells like technical implementation
    
    override fun existsByName(name: String): Boolean {
        return userRepository.findByName(name).isPresent
    }

}