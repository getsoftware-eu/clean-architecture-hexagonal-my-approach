package eu.getsoftware.cleanarchitecture.adapter.out.persistence.model;

import eu.getsoftware.cleanarchitecture.users.domain.model.IUserFactory;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.service.UserRegisterPortServiceImpl;
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
public class UserEntityFactory/*<T extends UserDataMapperEntity, Z implements UserDsRequestApplicationModelDTO>*/ 
        implements IUserFactory<UserMappedEntity/*, UserDsRequestApplicationModelDTO*/>
{
    private final UserRegisterPortServiceImpl userRegisterPortServiceImpl;

    Class<UserMappedEntity> assetClass;

    @Override
    public UserMappedEntity create(String name, String password) {

        UserMappedEntity entity = userRegisterPortServiceImpl.createEntity(name);
        entity.setPassword(password);
        
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
