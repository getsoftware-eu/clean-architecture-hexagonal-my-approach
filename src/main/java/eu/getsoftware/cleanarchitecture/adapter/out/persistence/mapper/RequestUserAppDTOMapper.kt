//package eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper
//
//import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity
//import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper
//import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity
//import eu.getsoftware.cleanarchitecture.application.port.`in`.user.iportservice.dto.UserResponseClientDTO
//import eu.getsoftware.cleanarchitecture.application.port.`in`.user.iportservice.dto.UserRequestUseCaseDTO
//import org.mapstruct.*
//
//@Mapper(componentModel = "spring" /* eu: MapStruct now creates a Spring-bean*/, unmappedTargetPolicy = ReportingPolicy.IGNORE /*, uses = [IUserRepository::class]*/)
//interface RequestUserAppDTOMapper : IDomainMapper<UserMappedDBEntity, UserRequestUseCaseDTO, UserResponseClientDTO> {
//    
////    override fun toEntityById(id: Long?): IUserEntity
//
//    @Mapping(target = "login", source = "name")
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
//    fun toResponseDTO(entity: UserRootDomainEntity?): UserResponseClientDTO?    
//    
////    @Mapping(target = "login", source = "name")
////    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
////    fun toResponseDTOFromRequest(input: IUserDTO?): UserResponseApplicationModelDTO?
//    
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
//    fun toDsRequestDTO(entity: UserRootDomainEntity?): UserRequestUseCaseDTO?
//
//    @Named("mapWithoutData")
//    @Mapping(target = "saStatus", ignore = true)
//    fun updateFromDtoIgnoringSomeFields(entityDTO: UserResponseClientDTO?, @MappingTarget entity: UserRootDomainEntity?)
//
//    fun updateAllFromDto(entityDTO: UserRequestUseCaseDTO?, @MappingTarget entity: UserRootDomainEntity?)
//}