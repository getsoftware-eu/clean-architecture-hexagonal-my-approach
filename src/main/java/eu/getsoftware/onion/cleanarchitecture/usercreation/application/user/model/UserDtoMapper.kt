package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserDTO
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.domainservice.IEntityMapper
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapperEntity
import org.mapstruct.*

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE /*, uses = [IUserRepository::class]*/)
interface UserDtoMapper : IEntityMapper<UserDataMapperEntity, UserDsRequestApplicationModelDTO> {
    
//    override fun toEntityById(id: Long?): IUserEntity

    @Mapping(target = "login", source = "name")
    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
    fun toResponseDTO(entity: IUserEntity?): UserResponseApplicationModelDTO?    
    
    @Mapping(target = "login", source = "name")
    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
    fun toResponseDTOFromRequest(input: UserDsRequestApplicationModelDTO?): UserResponseApplicationModelDTO?
    
    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
    fun toDsRequestDTO(entity: IUserEntity?): UserDsRequestApplicationModelDTO?

    @Named("mapWithoutData")
    @Mapping(target = "saStatus", ignore = true)
    fun updateFromDtoIgnoringSomeFields(entityDTO: UserResponseApplicationModelDTO?, @MappingTarget entity: IUserEntity?)

    fun updateAllFromDto(entityDTO: UserDsRequestApplicationModelDTO?, @MappingTarget entity: IUserEntity?)
}