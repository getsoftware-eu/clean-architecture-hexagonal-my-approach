package eu.getsoftware.cleanarchitecture.users.domain.model.mapper

import eu.getsoftware.cleanarchitecture.users.domain.model.IUserDTO
import eu.getsoftware.cleanarchitecture.users.domain.model.IUserDomain
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto.ResponseUserPortDTO
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.Named

/**
 * central generic Interface for mapping Entity to Dto in lower layers 
 */
interface IDomainMapper<T: IUserDomain, Z : IUserDTO>{

    fun toEntityById(id: Long?): T

    fun toDTO(entity: T?): Z?
//    fun toDTO(entity: IUserEntity?): Z?
//    @Mapping(target = "login", source = "name")
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
//    fun toResponseDTOFromRequest(input: UserDsRequestApplicationModelDTO?): UserResponseApplicationModelDTO?

    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
    fun toDsRequestDTO(entity: T?): Z?

    fun toResponseDTOFromRequest(dto: Z) : ResponseUserPortDTO
    
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