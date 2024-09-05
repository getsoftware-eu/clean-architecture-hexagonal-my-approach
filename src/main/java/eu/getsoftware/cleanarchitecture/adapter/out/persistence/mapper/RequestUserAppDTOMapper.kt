package eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomain
import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity
import eu.getsoftware.cleanarchitecture.application.domain.usecase.user.dto.RequestUserUseCaseDTO
import eu.getsoftware.cleanarchitecture.application.port.user.out.dto.ResponseUserPortDTO
import org.mapstruct.*

@Mapper(componentModel = "spring" /* eu: MapStruct now creates a Spring-bean*/, unmappedTargetPolicy = ReportingPolicy.IGNORE /*, uses = [IUserRepository::class]*/)
interface RequestUserAppDTOMapper : IDomainMapper<UserMappedEntity, RequestUserUseCaseDTO> {
    
//    override fun toEntityById(id: Long?): IUserEntity

    @Mapping(target = "login", source = "name")
    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
    fun toResponseDTO(entity: IUserDomain?): ResponseUserPortDTO?    
    
//    @Mapping(target = "login", source = "name")
//    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
//    fun toResponseDTOFromRequest(input: IUserDTO?): UserResponseApplicationModelDTO?
    
    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now()")
    fun toDsRequestDTO(entity: IUserDomain?): RequestUserUseCaseDTO?

    @Named("mapWithoutData")
    @Mapping(target = "saStatus", ignore = true)
    fun updateFromDtoIgnoringSomeFields(entityDTO: ResponseUserPortDTO?, @MappingTarget entity: IUserDomain?)

    fun updateAllFromDto(entityDTO: RequestUserUseCaseDTO?, @MappingTarget entity: IUserDomain?)
}