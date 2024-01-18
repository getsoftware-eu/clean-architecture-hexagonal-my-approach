//package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user
//
//import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO
//import ftek.struktura.asset.common.domain.IAsset
//import org.mapstruct.Mapping
//import org.mapstruct.MappingTarget
//import org.mapstruct.Named
//
//interface IUserFormMapper<T: IUserEntity, Z: UserResponseApplicationModelDTO>{
//
//    fun toEntityById(id: Long?): T
//
//    fun toDTO(entity: T?): Z?
//    fun toListDTO(assigmentFiles: List<T>): List<Z>
//    
//    fun updateAllFromDto(assetFormDto: Z?, @MappingTarget asset: T?)
//
//    @Named("mapWithoutData")
//    @Mapping(target = "saStatus", ignore = true)
//    fun updateFromDtoIgnoringSomeFields(assetDto: Z?, @MappingTarget asset: T?)
//
//    @Mapping(target = "owner", ignore = true)
//    @Mapping(target = "vertreter", ignore = true)
//    @Mapping(target = "editor", ignore = true)
//    fun updateFromDtoWithoutUsers(assetFormDto: Z?, @MappingTarget asset: T?)
//
////    @IterableMapping(qualifiedByName = ["mapWithoutData"])
////    fun updateListFromDtoIgnoringSomeFields(assigmentFileDTOs: List<Z?>): List<T?>
//
//
//}