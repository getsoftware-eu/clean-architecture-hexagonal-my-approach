package eu.getsoftware.cleanarchitecture.application.domain.model.modelinnerservice

import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IMappedDBEntity
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * domain - because only entity service!
 * eu: For example, ON new foreign DOMAIN-EVENT => FIND(!!) AND UPDATE OWN LOCAL DOMAIN-Entity 
 * 
 * eu: This abstract helper Service use internal Entity, that is not defined in interface!!!
 * 
 * gateway - interface to get and persist entity to repository
 * 
 * its internal business conditions, that have to be internal treaten
 */
public abstract class GenericRepositoryServiceAbstr<T: IMappedDBEntity>(
    private val domainRepository: JpaRepository<T, Long>,
) 
{

    abstract val assetClass: Class<T>
    
    fun createEntity(name: String): T {
        val entity: T  
        try {
            entity = createInstance(assetClass);
//            entity.setInitValues(name)
        } catch (e: Exception) {
            throw RuntimeException(e);
        }
        
        return entity
    }
    
    //override 
    fun saveEntity(entity: T) {
        domainRepository.save(entity)
    }    
    
//    fun save(entity: T): T {
//        val entity : T = createInstance(assetClass)
//        entityMapper.updateAllFromDto(userDTO, entity)
////        asset.saStatus = StatusEnum.NEU
//        //asset.owner = userRepository.getReferenceById(ownerId)
//        entityRepository.save(entity)
//        return entity
//    }

    fun findEntityById(id: Long): T {
        val optionalUser: Optional<T> = domainRepository.findById(id)

        if (optionalUser.isPresent) {
            val user = optionalUser.get()
//            val dto: Z? = entityMapper.toDsRequestDTO(user)
            //            return new UserDsRequestApplicationModelDTO(user.getName(), user.getPassword(), user.getCreationTime());
            return user
        } else throw EntityNotFoundException("Id not found " + id)
    }  
    
    //override
    abstract fun existsByName(name: String): Boolean
    
    @Throws(InstantiationException::class, IllegalAccessException::class)
    inline fun createInstance(assetClass: Class<T>): T {
        return assetClass.getDeclaredConstructor().newInstance()
    }

    abstract fun useLocaldomainObjectInspiteOfGenericEntity(): Boolean
}