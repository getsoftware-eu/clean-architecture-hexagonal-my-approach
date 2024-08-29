package eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper

import eu.getsoftware.cleanarchitecture.users.domain.model.IUserDomain
import eu.getsoftware.cleanarchitecture.users.domain.model.mapper.IDomainMapper
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.app.dto.RequestUserAppDTO
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto.ResponseUserPortDTO
import org.mapstruct.*

@Mapper(componentModel = "spring" /* eu: MapStruct now creates a Spring-bean*/, unmappedTargetPolicy = ReportingPolicy.IGNORE /*, uses = [IUserRepository::class]*/)
interface RequestUserAppDTOMapper : IDomainMapper<UserMappedEntity, RequestUserAppDTO> {
    
//    override fun toEntityById(id: Long?): IUserEntity

    @Mapping(target = "login", source = "name")
    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
    fun toResponseDTO(entity: IUserDomain?): ResponseUserPortDTO?    
    
//    @Mapping(target = "login", source = "name")
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
//    fun toResponseDTOFromRequest(input: IUserDTO?): UserResponseApplicationModelDTO?
    
    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
    fun toDsRequestDTO(entity: IUserDomain?): RequestUserAppDTO?

    @Named("mapWithoutData")
    @Mapping(target = "saStatus", ignore = true)
    fun updateFromDtoIgnoringSomeFields(entityDTO: ResponseUserPortDTO?, @MappingTarget entity: IUserDomain?)

    fun updateAllFromDto(entityDTO: RequestUserAppDTO?, @MappingTarget entity: IUserDomain?)
}