package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserFactoryAggregate;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.service.UserRegService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * In the best-case scenario, a factory should receive plain DTO (or just primitive types) and contains as little as possible logic.
 * Factory pattern should always create objects in a valid state.
 * 
 * Set Generics with layer concrete types: <T = UserDataMapperEntity, Z = UserDsRequestApplicationModelDTO>
 * 
 * Eugen: external AccessPoint aks AGGREGATOR or (EntityRoot, хотя у нас здесь нет daten)
 * 
 * Eugen: anstatt Factory, könnte als Builder implementiert! Oder als domainService!!
 */
@Component
@AllArgsConstructor
public class UserFactoryAggregateImpl/*<T extends UserDataMapperEntity, Z implements UserDsRequestApplicationModelDTO>*/ 
        implements IUserFactoryAggregate<UserDataMapperEntity, UserDsRequestApplicationModelDTO>
{
    private final UserRegService userRegService;

    Class<UserDataMapperEntity> assetClass;

    @Override
    public UserDataMapperEntity create(String name, String password) {

        UserDataMapperEntity entity = userRegService.createEntity(name);
        entity.setPassword(password);
        
        //sun address creation and other consistency logik
        AddressDataMapperEntity address = new AddressDataMapperEntity();
        entity.setAddress(address);
//        T entity = null;
//        try {
//            entity = createInstance(assetClass);
//            entity.setName(name);
//            entity.setPassword(password);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return entity;
//        return Optional.of(entity);
    }

//    @Throws(exceptionClasses = InstantiationException.class, IllegalAccessException.class)
//    T createInstance(Class<T> assetClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        return assetClass.getDeclaredConstructor().newInstance();
//    }


}
