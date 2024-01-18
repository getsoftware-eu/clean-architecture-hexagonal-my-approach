package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.domainservice

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserDTO
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserRepository
import org.springframework.data.repository.CrudRepository

abstract class IRegisterService<T: IUserEntity, Z: IUserDTO>(
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

    @Throws(InstantiationException::class, IllegalAccessException::class)
    inline fun createInstance(assetClass: Class<T>): T {
        return assetClass.getDeclaredConstructor().newInstance()
    }
}