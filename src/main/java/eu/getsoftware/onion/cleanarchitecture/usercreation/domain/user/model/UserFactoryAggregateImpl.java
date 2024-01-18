package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserEntityDataRules;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.IUserFactoryAggregate;

/**
 * In the best-case scenario, a factory should receive plain DTO (or just primitive types) and contains as little as possible logic.
 * Factory pattern should always create objects in a valid state.
 * 
 * Eugen: external AccessPoint aks AGGREGATOR or (EntityRoot, хотя у нас здесь нет daten)
 * 
 * Eugen: anstatt Factory, könnte als Builder implementiert! Oder als domainService!!
 */
public class UserFactoryAggregateImpl implements IUserFactoryAggregate
{
    @Override
    public IUserEntityDataRules create(String name, String password) {
        return new UserEntityImpl(name, password);
    }
    
}
