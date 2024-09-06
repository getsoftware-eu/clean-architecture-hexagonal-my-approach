package eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService

import eu.getsoftware.cleanarchitecture.common.error.UserNotFoundException
import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDTO
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomain
import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainRepository
import java.util.*

/**
 * domain - because only entity service.
 * 
 * its internal business conditions, that have to be internal treaten
 */
abstract class RegisterEntityServiceAbstr<T: IUserDomain, Z : IUserDTO>(
    val domainMapper: IDomainMapper<T, Z>,
    val domainRepository: IDomainRepository<T, Long>,
) {

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
    
    fun persistFromDTO(userDTO: Z): T {
        val entity : T = createInstance(assetClass)
        domainMapper.updateAllFromDto(userDTO, entity)
//        asset.saStatus = StatusEnum.NEU
        //asset.owner = userRepository.getReferenceById(ownerId)
        domainRepository.save(entity)
        return entity
    }      
    
    fun persist(entity: T): T {
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

    fun getById(id: Long): T? {
        val optionalUser: Optional<T> = domainRepository.findById(id)

        if (optionalUser.isPresent) {
            val user = optionalUser.get()
//            val dto: Z? = entityMapper.toDsRequestDTO(user)
            //            return new UserDsRequestApplicationModelDTO(user.getName(), user.getPassword(), user.getCreationTime());
            return user
        } else throw UserNotFoundException(id)
    }  
    
    fun getDTOById(id: Long): Z? {
        val user = getById(id)
        return domainMapper.toDsRequestDTO(user)
    }
    
    abstract fun existsByName(name: String): Boolean
    
    @Throws(InstantiationException::class, IllegalAccessException::class)
    inline fun createInstance(assetClass: Class<T>): T {
        return assetClass.getDeclaredConstructor().newInstance()
    }
}