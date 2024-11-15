//package eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl;
//
//import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.UserEntityMapper;
//import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
//import eu.getsoftware.cleanarchitecture.application.domain.model.modelinnerservice.DbEntityGatewayServiceAbstr;
//import eu.getsoftware.cleanarchitecture.application.domain.model.user.UserRootDomainEntity;
//import eu.getsoftware.cleanarchitecture.application.port.out.user.iportservice.gateways.IDomainPersistPortService;
//import lombok.RequiredArgsConstructor;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserPersistHelperPortServiceImpl implements IDomainPersistPortService<UserRootDomainEntity, UserMappedDBEntity> {
//    
//    private final DbEntityGatewayServiceAbstr<UserMappedDBEntity> userDomainPersistService;
//
//    /**
//     * any custom logik, that is too low for domain layer and a UseCase...
//     */
//    @NotNull
//    @Override
//    public void persistToDb(UserRootDomainEntity domainEntity) {
//
//        UserMappedDBEntity dbEntity = UserEntityMapper.toDBEntity(domainEntity);
//        
//        userDomainPersistService.saveEntity(dbEntity);
//    }
//}
