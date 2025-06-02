package eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper;

import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.DtoGenericMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.dto.UserClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.*;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper extends DtoGenericMapper<UserRootDomainEntity, UserClientDTO> 
{

//    @Override
    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now().toString()")
    UserClientDTO toDto(UserRootDomainEntity domain);

//    @Mapping(source = "schadenAussenwirkungId", target = "schadenAussenwirkung")
//    @Mapping(source = "haufSchadenAussenwirkungId", target = "haufSchadenAussenwirkung")
//    @Mapping(source = "schadenFinanziellId", target = "schadenFinanziell")
//    @Mapping(source = "haufSchadenFinanziellId", target = "haufSchadenFinanziell")
//    @Mapping(source = "schadenGeschaeftssteuerungId", target = "schadenGeschaeftssteuerung")
//    @Mapping(source = "haufSchadenGeschaeftssteuerungId", target = "haufSchadenGeschaeftssteuerung")
//    @Mapping(source = "schadenVorschriftenVerletzungId", target = "schadenVorschriftenVerletzung")
//    @Mapping(source = "haufSchadenVorschriftenVerletzungId", target = "haufSchadenVorschriftenVerletzung")
//    fun updateFromDto(formDto: BiaFormDTO?, @MappingTarget bia: Bia?)
}

