//package eu.getsoftware.cleanarchitecture.application.domain.model.modelinnerservice
//
//import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper
//import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity
//import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainRequestDTO
//import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainResponseDTO
//
///**
// * domain DTO - because only entity service!
// */
//abstract class DomainEntityDTOServiceAbstr<T: UserRootDomainEntity, I : IUserDomainRequestDTO, O : IUserDomainResponseDTO>(
//    private val domainMapper: IDomainMapper<T, I, O>,
//    private val domainRegisterDTOGateway: DbEntityGatewayServiceAbstr<T>,
//) 
//{
//
//    //override 
//    fun createEntityFromDTO(userRequestDTO: I): T {
//        val entity : T = domainRegisterDTOGateway.createEntity(userRequestDTO.name())
//        domainMapper.updateAllFromDto(userRequestDTO, entity)
//        return entity
//    }
//    
//    //override 
//    fun saveFromDTO(userRequestDTO : I) {
//        val entity : T = createEntityFromDTO(userRequestDTO)
//        domainRegisterDTOGateway.saveEntity(entity)
//    }
//    
//    //override 
//    fun getModelDTOById(id: Long): O? {
//        val user = domainRegisterDTOGateway.findEntityById(id)
//        return domainMapper.toResponseDTO(user)
//    }
//
//    fun convertToResponseDTO(entity: T): O? {
//        return domainMapper.toResponseDTO(entity)
//    }
//
//   
//}