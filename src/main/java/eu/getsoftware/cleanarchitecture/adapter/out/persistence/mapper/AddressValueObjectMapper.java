package eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.AddressDBEmbeddable;
import eu.getsoftware.cleanarchitecture.application.domain.model.address.AddressValueObject;
import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.EntityGenericMapper;
import org.mapstruct.Mapper;

/**
 * unmappedTargetPolicy = ReportingPolicy.IGNORE - eu: не забудешь новый field
 */
@Mapper//(componentModel = "spring")
public interface AddressValueObjectMapper extends EntityGenericMapper<AddressValueObject, AddressDBEmbeddable> {

    @Override
//    @Mapping(target = "password", ignore = true)
    AddressValueObject toDomain(AddressDBEmbeddable entity);
    
    // Специальный метод с @Named для частичного маппинга
//    @Named("mapWithoutData")
//    @Mapping(target = "email", ignore = true)
//    @Mapping(target = "password", ignore = true)
//    AddressValueObject mapWithoutData(AddressDBEmbeddable entity);
}

 