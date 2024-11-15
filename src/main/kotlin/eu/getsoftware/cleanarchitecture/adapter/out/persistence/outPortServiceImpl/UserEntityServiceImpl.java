//package eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl;
//
//import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.UserEntityMapper;
//import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
//import eu.getsoftware.cleanarchitecture.adapter.out.persistence.repository.UserJpaRepository;
//import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainFactory;
//import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserDomainId;
//import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
//import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.DomainEntityService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserEntityServiceImpl implements DomainEntityService<UserDomainId, UserRootDomainEntity> {
//
//    private final UserEntityMapper userEntityMapper;
//    private final UserJpaRepository jpaUserRepository;
//
//    public UserRootDomainEntity recreateDomainEntity(UserDomainId domainId){
//
//        UserMappedDBEntity userDbProjection = getDbEntity(domainId);
//        
//        //eu: manual wrong Mapping!!!!!!!!!!!
////        return userDomainFactory.getEntityBuilder(userDbProjection.getDomainEntityId())
////                .name(userDbProjection.getName())
////                .password(userDbProjection.getPassword())
////                .creationTime(userDbProjection.getCreationTime())
////                .build();
//
//        return userEntityMapper.toDomain(userDbProjection);
//    }
//
//    private UserMappedDBEntity getDbEntity(UserDomainId domainId){
//        return jpaUserRepository.findByDomainEntityId(domainId).orElseThrow(() -> new RuntimeException("user not found"));
//    }
//}
