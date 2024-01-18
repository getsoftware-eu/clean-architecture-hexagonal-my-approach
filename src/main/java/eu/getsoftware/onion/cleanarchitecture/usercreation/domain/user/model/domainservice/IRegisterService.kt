package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.domainservice

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserDTO
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.error.UserNotFoundException
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapperEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

abstract class IRegisterService<T: IUserEntity, Z : IUserDTO/* : IUserDTO*/>(
    val entityMapper: IEntityMapper<T, Z>,
    val entityRepository: CrudRepository<T, Long>,
) {

    abstract val assetClass: Class<T>
    
    fun save(userDTO: Z): T {
        val entity : T =  createInstance(assetClass)
        entityMapper.updateAllFromDto(userDTO, entity)
//        asset.saStatus = StatusEnum.NEU
        //asset.owner = userRepository.getReferenceById(ownerId)
        entityRepository.save(entity)
        return entity
    }

    fun getById(id: Long): T? {
        val optionalUser: Optional<T> = entityRepository.findById(id)

        if (optionalUser.isPresent) {
            val user = optionalUser.get()
//            val dto: Z? = entityMapper.toDsRequestDTO(user)
            //            return new UserDsRequestApplicationModelDTO(user.getName(), user.getPassword(), user.getCreationTime());
            return user
        } else throw UserNotFoundException(id)
    }
    
    abstract fun existsByName(name: String): Boolean
    
    @Throws(InstantiationException::class, IllegalAccessException::class)
    inline fun createInstance(assetClass: Class<T>): T {
        return assetClass.getDeclaredConstructor().newInstance()
    }
}