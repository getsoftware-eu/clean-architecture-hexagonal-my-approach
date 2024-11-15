package eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper;

import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.DtoGenericMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.*;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper(componentModel = "spring")
public interface UserDtoMapper extends DtoGenericMapper<UserRootDomainEntity, UserClientDTO> {

    @Override
    @Mapping(target = "creationTime", defaultValue = "LocalDateTime.now().toString()")
    UserClientDTO toDto(UserRootDomainEntity domain);
}

 