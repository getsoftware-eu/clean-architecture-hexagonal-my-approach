package eu.getsoftware.cleanarchitecture.application.domain.model;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainRequestDTO
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO

/**
 * Only setter methods. Only RequestDTO
 */
public interface IDomainRegisterDTOGateway<T: IUserDomainEntity, I : IUserDomainRequestDTO, O : IUserDomainResponseDTO> {
    
    fun createEntityFromDTO(userRequestDTO: I): T 
    
    fun saveFromDTO(userRequestDTO: I) 

    fun getModelDTOById(id: Long): O? 

    fun existsByName(name: String): Boolean
}
