//package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model
//
//import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity
//import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model.domainservice.IEntityMapper
//import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapperEntity
//import org.mapstruct.*
//
//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE /*, uses = [IUserRepository::class]*/)
//interface UserResponseMapper : IEntityMapper<UserDataMapperEntity, UserResponseApplicationModelDTO> {
//    
////    override fun toEntityById(id: Long?): IUserEntity
//
//    @Mapping(target = "login", source = "name")
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
//    fun toDTO(entity: IUserEntity?): UserResponseApplicationModelDTO?    
//    
//    @Mapping(target = "login", source = "name")
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
//    fun toResponseDTOFromRequest(input: UserDsRequestApplicationModelDTO?): UserResponseApplicationModelDTO?
//    
//    @Named("mapWithoutData")
//    @Mapping(target = "saStatus", ignore = true)
//    fun updateFromDtoIgnoringSomeFields(entityDTO: UserResponseApplicationModelDTO?, @MappingTarget entity: IUserEntity?)
//
//    fun updateAllFromDto(entityDTO: UserDsRequestApplicationModelDTO?, @MappingTarget entity: IUserEntity?)
//}