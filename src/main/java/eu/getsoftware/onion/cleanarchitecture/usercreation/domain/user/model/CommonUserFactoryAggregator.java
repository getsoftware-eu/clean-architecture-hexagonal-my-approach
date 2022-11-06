package eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.model;

import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserEntity;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserFactoryAggregator;

/**
 * Eugen: external AccessPoint aks AGGREGATOR or (EntityRoot, хотя у нас здесь нет daten)
 * 
 * Eugen: anstatt Factory, könnte als Builder implementiert! Oder als domainService!!
 */
public class CommonUserFactoryAggregator implements UserFactoryAggregator
{
    @Override
    public UserEntity create(String name, String password) {
        return new CommonUserEntityEntity(name, password);
    }
}
