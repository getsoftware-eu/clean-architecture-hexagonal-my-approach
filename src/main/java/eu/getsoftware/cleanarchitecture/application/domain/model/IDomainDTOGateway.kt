package eu.getsoftware.cleanarchitecture.application.domain.model;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainDTO
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity

public interface IDomainDTOGateway<T: IUserDomainEntity, Z : IUserDomainDTO> {
    
    fun persistFromDTO(userDTO: Z): T 

    fun getModelDTOById(id: Long): Z? 

    fun existsByName(name: String): Boolean

//    @Throws(InstantiationException::class, IllegalAccessException::class)
//    fun createInstance(assetClass: Class<T>): T
}
