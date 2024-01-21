package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.domainservice

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserDTO
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.error.UserNotFoundException
import org.springframework.data.repository.CrudRepository
import java.util.*

abstract class IRegisterService<T: IUserEntity, Z : IUserDTO/* : IUserDTO*/>(
    val entityMapper: IEntityMapper<T, Z>,
    val entityRepository: CrudRepository<T, Long>,
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
        entityMapper.updateAllFromDto(userDTO, entity)
//        asset.saStatus = StatusEnum.NEU
        //asset.owner = userRepository.getReferenceById(ownerId)
        entityRepository.save(entity)
        return entity
    }      
    
    fun persist(entity: T): T {
        entityRepository.save(entity)
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
        val optionalUser: Optional<T> = entityRepository.findById(id)

        if (optionalUser.isPresent) {
            val user = optionalUser.get()
//            val dto: Z? = entityMapper.toDsRequestDTO(user)
            //            return new UserDsRequestApplicationModelDTO(user.getName(), user.getPassword(), user.getCreationTime());
            return user
        } else throw UserNotFoundException(id)
    }  
    
    fun getDTOById(id: Long): Z? {
        val user = getById(id)
        return entityMapper.toDsRequestDTO(user)
    }
    
    abstract fun existsByName(name: String): Boolean
    
    @Throws(InstantiationException::class, IllegalAccessException::class)
    inline fun createInstance(assetClass: Class<T>): T {
        return assetClass.getDeclaredConstructor().newInstance()
    }
}