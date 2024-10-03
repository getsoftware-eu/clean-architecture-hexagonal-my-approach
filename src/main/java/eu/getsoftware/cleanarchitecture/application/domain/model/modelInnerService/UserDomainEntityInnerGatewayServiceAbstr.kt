package eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService

import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainDTOGateway
import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainEntityGateway
import eu.getsoftware.cleanarchitecture.common.error.UserNotFoundException
import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainDTO
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity
import java.util.*

/**
 * domain - because only entity service.
 * 
 * gateway - interface to get and persist entity to repository
 * 
 * its internal business conditions, that have to be internal treaten
 */
abstract class UserDomainEntityInnerGatewayServiceAbstr<T: IUserDomainEntity, Z : IUserDomainDTO>(
    val domainMapper: IDomainMapper<T, Z>,
    val domainRepository: IDomainEntityGateway<T, Long>,
) : IDomainDTOGateway<T, Z> {

    abstract val assetClass: Class<T>
    
    fun createEntity(name: String): T {
        val entity: T  
        try {
            entity = createInstance(assetClass);
            entity.setInitValues(name)
        } catch (e: Exception) {
            throw RuntimeException(e);
        }
        
        return entity
    }
    
    override fun persistFromDTO(userDTO: Z): T {
        val entity : T = createInstance(assetClass)
        domainMapper.updateAllFromDto(userDTO, entity)
//        asset.saStatus = StatusEnum.NEU
        //asset.owner = userRepository.getReferenceById(ownerId)
        return persistEntity(entity)
    }      
    
    fun persistEntity(entity: T): T {
        domainRepository.save(entity)
        return entity
    }  
    
//    fun save(entity: T): T {
//        val entity : T = createInstance(assetClass)
//        entityMapper.updateAllFromDto(userDTO, entity)
////        asset.saStatus = StatusEnum.NEU
//        //asset.owner = userRepository.getReferenceById(ownerId)
//        entityRepository.save(entity)
//        return entity
//    }

    fun findById(id: Long): T {
        val optionalUser: Optional<T> = domainRepository.findById(id)

        if (optionalUser.isPresent) {
            val user = optionalUser.get()
//            val dto: Z? = entityMapper.toDsRequestDTO(user)
            //            return new UserDsRequestApplicationModelDTO(user.getName(), user.getPassword(), user.getCreationTime());
            return user
        } else throw UserNotFoundException(id)
    }  
    
    override fun getModelDTOById(id: Long): Z? {
        val user = findById(id)
        return domainMapper.toDsRequestDTO(user)
    }

    override abstract fun existsByName(name: String): Boolean
    
    @Throws(InstantiationException::class, IllegalAccessException::class)
    inline fun createInstance(assetClass: Class<T>): T {
        return assetClass.getDeclaredConstructor().newInstance()
    }
}