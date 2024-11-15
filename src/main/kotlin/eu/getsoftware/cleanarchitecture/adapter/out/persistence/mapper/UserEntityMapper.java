package eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.EntityGenericMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper(componentModel = "spring")
public interface UserEntityMapper extends EntityGenericMapper<UserRootDomainEntity, UserMappedDBEntity> {

    @Override
//    @Mapping(target = "password", ignore = true)
    UserRootDomainEntity toDomain(UserMappedDBEntity entity);
    
    // Специальный метод с @Named для частичного маппинга
    @Named("mapWithoutData")
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserRootDomainEntity mapWithoutData(UserMappedDBEntity entity);
}

 