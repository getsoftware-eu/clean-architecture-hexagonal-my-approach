package eu.getsoftware.cleanarchitecture.application.domain.model.mapper

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainDTO
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainEntity
import eu.getsoftware.cleanarchitecture.application.port.`in`.user.iPortService.dto.UserResponseClientDTO
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.Named

/**
 * central generic Interface for mapping Entity to Dto in lower layers 
 */
interface IDomainMapper<T: IUserDomainEntity, Z : IUserDomainDTO>{

    fun toEntityById(id: Long?): T

    fun toDTO(entity: T?): Z?
//    fun toDTO(entity: IUserEntity?): Z?
//    @Mapping(target = "login", source = "name")
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
//    fun toResponseDTOFromRequest(input: UserDsRequestApplicationModelDTO?): UserResponseApplicationModelDTO?

    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
    fun toDsRequestDTO(entity: T?): Z?

    fun toResponseDTOFromRequest(dto: Z) : UserResponseClientDTO
    
    fun toListDTO(assigmentFiles: List<T>): List<Z>
    
    fun updateAllFromDto(assetFormDto: Z?, @MappingTarget asset: T?)

    @Named("mapWithoutData")
    @Mapping(target = "saStatus", ignore = true)
    fun updateFromDtoIgnoringSomeFields(assetDto: Z?, @MappingTarget asset: T?)

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "vertreter", ignore = true)
    @Mapping(target = "editor", ignore = true)
    fun updateFromDtoWithoutUsers(assetFormDto: Z?, @MappingTarget asset: T?)

//    @IterableMapping(qualifiedByName = ["mapWithoutData"])
//    fun updateListFromDtoIgnoringSomeFields(assigmentFileDTOs: List<Z?>): List<T?>


}