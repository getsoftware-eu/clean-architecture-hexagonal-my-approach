package eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl

import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.RegisterEntityServiceAbstr
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity
import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainRepository
import eu.getsoftware.cleanarchitecture.application.port.`in`.user.iUseCase.dto.RequestUserUseCaseDTO
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.RequestUserAppDTOMapper
import eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService.IRegisterUserPortService
import org.springframework.stereotype.Service

//TODO eu: is it application-layer usecase or just implementing infrastruktur-layer with missing technical details???

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
class RegisterUserPortServiceImpl (
    val userRepository: IDomainRepository<UserMappedEntity, Long>,
    mapper: RequestUserAppDTOMapper,
    override val assetClass: Class<UserMappedEntity> = UserMappedEntity::class.java
): IRegisterUserPortService, RegisterEntityServiceAbstr<UserMappedEntity, RequestUserUseCaseDTO>(mapper, userRepository) {

    //eu: smells like technical implementation
    
    override fun existsByName(name: String): Boolean {
        return userRepository.findByName(name).isPresent
    }

}