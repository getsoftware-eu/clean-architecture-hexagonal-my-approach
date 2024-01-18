package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntity;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserFactoryAggregate;
import org.springframework.stereotype.Component;

/**
 * In the best-case scenario, a factory should receive plain DTO (or just primitive types) and contains as little as possible logic.
 * Factory pattern should always create objects in a valid state.
 * 
 * Eugen: external AccessPoint aks AGGREGATOR or (EntityRoot, хотя у нас здесь нет daten)
 * 
 * Eugen: anstatt Factory, könnte als Builder implementiert! Oder als domainService!!
 */
@Component
public class UserFactoryAggregateImpl implements IUserFactoryAggregate
{
    @Override
    public IUserEntity create(String name, String password) {
        var entity = new UserDataMapperEntity();
        entity.setName(name);
        entity.setPassword(password);
        return entity;
    }
    
}
