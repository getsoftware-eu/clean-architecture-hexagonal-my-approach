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
@Mapper//(uses = AddressValueObjectMapper.class)
public interface UserEntityMapper extends EntityGenericMapper<UserRootDomainEntity, UserMappedDBEntity> {

    @Override
//    @Mapping(target = "password", ignore = true)
    UserRootDomainEntity toDomain(UserMappedDBEntity entity);
    
    // Специальный метод с @Named для частичного маппинга
    @Named("mapWithoutData")
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserRootDomainEntity mapWithoutData(UserMappedDBEntity entity);

//    @Mapping(source = "addressJson", target = "address", qualifiedByName = "dbToAddress")
//    UserRootDomainEntity mapToDomainEntity(UserMappedDBEntity dbEntity);
    
//    @Named("addressToDb")
//    static AddressDBEmbeddable addressToDB(AddressValueObject address) {
//        return address != null ? address.toDb() : null;
//    }
//
//    @Named("dbToAddress")
//    static AddressValueObject dbToAddress(AddressDBEmbeddable json) {
//        return json != null ? AddressValueObject.fromDb(json) : null;
//    }
}

 