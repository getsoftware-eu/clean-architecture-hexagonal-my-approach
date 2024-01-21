package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserFactoryAggregate;
import kotlin.jvm.Throws;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

/**
 * In the best-case scenario, a factory should receive plain DTO (or just primitive types) and contains as little as possible logic.
 * Factory pattern should always create objects in a valid state.
 * 
 * Eugen: external AccessPoint aks AGGREGATOR or (EntityRoot, хотя у нас здесь нет daten)
 * 
 * Eugen: anstatt Factory, könnte als Builder implementiert! Oder als domainService!!
 */
@Component
public class UserFactoryAggregateImpl<T extends UserDataMapperEntity, Z extends UserDsRequestApplicationModelDTO> implements IUserFactoryAggregate<T, Z>
{
    Class<T> assetClass;

    @Override
    public Optional<T> create(String name, String password) {
        T entity = null;
        try {
            entity = createInstance(assetClass);
            entity.setName(name);
            entity.setPassword(password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.of(entity);
    }

//    @Throws(exceptionClasses = InstantiationException.class, IllegalAccessException.class)
    T createInstance(Class<T> assetClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return assetClass.getDeclaredConstructor().newInstance();
    }


}
