package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.domainservice

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserDTO
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.Named

interface IEntityMapper<T: IUserEntity, Z : IUserDTO/* : IUserDTO*/>{

    fun toEntityById(id: Long?): T

    fun toDTO(entity: T?): Z?
    fun toDTO(entity: IUserEntity?): Z?
//    @Mapping(target = "login", source = "name")
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
//    fun toResponseDTOFromRequest(input: UserDsRequestApplicationModelDTO?): UserResponseApplicationModelDTO?

    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
    fun toDsRequestDTO(entity: T?): Z?

    
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