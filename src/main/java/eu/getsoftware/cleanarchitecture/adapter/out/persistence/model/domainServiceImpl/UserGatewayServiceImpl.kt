package eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.domainServiceImpl

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository.JpaUserRepository
import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.DomainEntityGatewayServiceAbstr
import org.springframework.stereotype.Service

//TODO eu: is it application-layer usecase or just implementing infrastruktur-layer with missing technical details???

/**
 * ALL LOGIC is now up in DOMAIN layer!!! Je hocher, desto besser!!!!
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
class UserGatewayServiceImpl (
    val userRepository: JpaUserRepository,
    override val assetClass: Class<UserMappedDBEntity> = UserMappedDBEntity::class.java
): DomainEntityGatewayServiceAbstr<UserMappedDBEntity>(userRepository) {

    //eu: smells like technical implementation
    
    override fun existsByName(name: String): Boolean {
        return userRepository.findByName(name).isPresent
    }

    /**
     * User TempEntity or just domainInterface
     */
    override fun useLocaldomainObjectInspiteOfGenericEntity(): Boolean {
        return false
    }

}