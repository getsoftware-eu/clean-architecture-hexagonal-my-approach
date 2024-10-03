package eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity
import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity
import eu.getsoftware.cleanarchitecture.application.port.`in`.user.iPortService.dto.UserResponseClientDTO
import eu.getsoftware.cleanarchitecture.application.port.`in`.user.iPortService.dto.UserRequestUseCaseDTO
import org.mapstruct.*

@Mapper(componentModel = "spring" /* eu: MapStruct now creates a Spring-bean*/, unmappedTargetPolicy = ReportingPolicy.IGNORE /*, uses = [IUserRepository::class]*/)
interface RequestUserAppDTOMapper : IDomainMapper<UserMappedEntity, UserRequestUseCaseDTO> {
    
//    override fun toEntityById(id: Long?): IUserEntity

    @Mapping(target = "login", source = "name")
    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
    fun toResponseDTO(entity: IUserDomainEntity?): UserResponseClientDTO?    
    
//    @Mapping(target = "login", source = "name")
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
//    fun toResponseDTOFromRequest(input: IUserDTO?): UserResponseApplicationModelDTO?
    
    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
    fun toDsRequestDTO(entity: IUserDomainEntity?): UserRequestUseCaseDTO?

    @Named("mapWithoutData")
    @Mapping(target = "saStatus", ignore = true)
    fun updateFromDtoIgnoringSomeFields(entityDTO: UserResponseClientDTO?, @MappingTarget entity: IUserDomainEntity?)

    fun updateAllFromDto(entityDTO: UserRequestUseCaseDTO?, @MappingTarget entity: IUserDomainEntity?)
}