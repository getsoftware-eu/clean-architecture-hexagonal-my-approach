package eu.getsoftware.cleanarchitecture.adapter.out.persistence.model;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainFactory;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.domainServiceImpl.UserGatewayServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * In the best-case scenario, a factory should receive plain DTO (or just primitive types) and contains as little as possible logic.
 * Factory pattern should always create objects in a valid state.
 * 
 * why here no (password) validation???
 * 
 * Set Generics with layer concrete types: <T = UserDataMapperEntity, Z = UserDsRequestApplicationModelDTO>
 * 
 * Eugen: external AccessPoint aks AGGREGATOR or (EntityRoot, хотя у нас здесь нет daten)
 * 
 * Eugen: anstatt Factory, könnte als Builder implementiert! Oder als domainService!!
 */
@Component
@AllArgsConstructor
public class UserEntityFactory/*<T extends UserDataMapperEntity, Z implements UserDsRequestApplicationModelDTO>*/ 
        implements IUserDomainFactory<UserMappedDBEntity>
{
    private final UserGatewayServiceImpl userGatewayServiceImpl;

    Class<UserMappedDBEntity> assetClass;

    @Override
    public UserMappedDBEntity create(String name, String password) {

        UserMappedDBEntity entity = userGatewayServiceImpl.createEntity(name);
        entity.setInitValues(name, password); //TODO where password validation? why validation in useCase and not here in creation?????
        
        //sun address creation and other consistency logik
        AddressMappedEntity address = new AddressMappedEntity();
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
