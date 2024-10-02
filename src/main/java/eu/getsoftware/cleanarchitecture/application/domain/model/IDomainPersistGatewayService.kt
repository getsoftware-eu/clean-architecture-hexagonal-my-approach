package eu.getsoftware.cleanarchitecture.application.domain.model;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDTO
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomain

public interface IDomainPersistGatewayService<T: IUserDomain, Z : IUserDTO> {
    
    fun createEntity(name: String): T 

    fun persistFromDTO(userDTO: Z): T 

    fun persist(entity: T): T 

    fun getById(id: Long): T? 

    fun getDTOById(id: Long): Z? 

//    abstract fun existsByName(name: String): Boolean

//    @Throws(InstantiationException::class, IllegalAccessException::class)
//    fun createInstance(assetClass: Class<T>): T
}
