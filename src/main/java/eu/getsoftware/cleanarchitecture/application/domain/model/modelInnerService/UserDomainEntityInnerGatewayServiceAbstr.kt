package eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService

import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainRegisterDTOGateway
import eu.getsoftware.cleanarchitecture.application.domain.model.IDomainEntityGateway
import eu.getsoftware.cleanarchitecture.common.error.UserNotFoundException
import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainRequestDTO
import java.util.*

/**
 * domain - because only entity service.
 * 
 * eu: This abstract helper Service use internal Entity, that is not defined in interface!!!
 * 
 * gateway - interface to get and persist entity to repository
 * 
 * its internal business conditions, that have to be internal treaten
 */
abstract class UserDomainEntityInnerGatewayServiceAbstr<T: IUserDomainEntity, I : IUserDomainRequestDTO, O : IUserDomainResponseDTO>(
    val domainMapper: IDomainMapper<T, I, O>,
    val domainRepository: IDomainEntityGateway<T, Long>,
) : IDomainRegisterDTOGateway<T, I, O> {

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
    
    override fun createEntityFromDTO(userRequestDTO: I): T {
        val entity : T = createEntity(userRequestDTO.name())
        domainMapper.updateAllFromDto(userRequestDTO, entity)
        return entity
    }      
    
    fun persistEntity(entity: T): T {
        domainRepository.save(entity)
        return entity
    }    
    
    override fun saveFromDTO(userRequestDTO : I) {
        val entity : T = createEntityFromDTO(userRequestDTO)
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
        } else throw UserNotFoundException(id)
    }  
    
    override fun getModelDTOById(id: Long): O? {
        val user = findEntityById(id)
        return domainMapper.toResponseDTO(user)
    }

    override abstract fun existsByName(name: String): Boolean
    
    @Throws(InstantiationException::class, IllegalAccessException::class)
    inline fun createInstance(assetClass: Class<T>): T {
        return assetClass.getDeclaredConstructor().newInstance()
    }
}