//package eu.getsoftware.cleanarchitecture.adapter.out.persistence.model;
//
//import eu.getsoftware.cleanarchitecture.application.domain.model.AddressDomain;
//import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainFactory;
//import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.domainserviceimpl.UserGatewayServiceImpl;
//import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainEntity;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//
///**
// * In the best-case scenario, a factory should receive plain DTO (or just primitive types) and contains as little as possible logic.
// * Factory pattern should always create objects in a valid state.
// * 
// * why here no (password) validation???
// * 
// * Set Generics with layer concrete types: <T = UserDataMapperEntity, Z = UserDsRequestApplicationModelDTO>
// * 
// * Eugen: external AccessPoint aks AGGREGATOR or (EntityRoot, хотя у нас здесь нет daten)
// * 
// * Eugen: anstatt Factory, könnte als Builder implementiert! Oder als domainService!!
// */
//@Component
//@AllArgsConstructor
//public class UserEntityFactory implements IUserDomainFactory<UserDomainEntity>
//{
//    private final UserGatewayServiceImpl userGatewayServiceImpl;
//
//    Class<UserMappedDBEntity> assetClass;
//
//    @Override
//    public UserDomainEntity create(String name, String password) {
//
//        AddressDomain address = AddressDomain.builder()
//                                .city("ffm")
//                                .street("strasse")
//                                .build();
//
//        UserDomainEntity entity = UserDomainEntity.builder().address(address).build();
//        entity.setInitValues(name, password); 
//        
//        return entity;
//    }
//
////    @Throws(exceptionClasses = InstantiationException.class, IllegalAccessException.class)
////    T createInstance(Class<T> assetClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
////        return assetClass.getDeclaredConstructor().newInstance();
////    }
//
//
//}
