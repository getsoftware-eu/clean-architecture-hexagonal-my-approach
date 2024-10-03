//package eu.getsoftware.cleanarchitecture.application.domain.infrastructure.service;
//
//import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
//import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.IUserDomainPortService;
//import eu.getsoftware.cleanarchitecture.application.port.out.user.iPortService.gateways.IUserRegisterPortGatewayService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//
////TODO port это infrastruktur layer (technical impl) or just boundary обслуживание *(aka dto service?)
//
///**
// * 
// * no creation hier, controller direct to application layer (registerGateway)
// * 
// * eu: now is anemic struktur, no extra interaktor logik here!
// * 
// * Eu ask: how can I protect this UseCaseImpl from other Layers (and force interface access), it is package private, but in spring context it is a public bean ??????
// * - maybe I have to wear this implementation manual in @Configuration (and not declare @Service for s auto-bean-package-scan ???)
// */
//@Service
//@AllArgsConstructor
///*public restrict??*/ class UserCRUDServiceImpl /*extends IRegisterService<UserDataMapperEntity, UserDsRequestApplicationModelDTO>*/ implements IUserDomainPortService
//{
//    private final IUserRegisterPortGatewayService userRegisterPortGatewayService;
//    
////    boolean someAbstratMethod(String identifier){
////
////        if(identifier==null)
////            abstractAction1();
////        if(identifier.length()>10)
////            abstractAction2();
////        
////        return true;
////    };
////
////    private void abstractAction2() {
////    }
////
////    private void abstractAction1() {
////        
////    }
//    
//    @Override
//    public boolean existsByName(String name) {
//        return userRegisterPortGatewayService.userExistsByName(name);
//    }    
//    
//    @Override
//    public UserRequestUseCaseDTO findDTOById(long id) {
//        return userRegisterPortGatewayService.getDTOById(id);
//    }
//    
//    @Override
//    public void saveDTO(UserRequestUseCaseDTO requestModel) {
//        userRegisterPortGatewayService.persistFromDTO(requestModel);
//    }
//
////    @Override
////    public Class<UserDataMapperEntity> getAssetClass() {
////        return UserDataMapperEntity.class;
////    }
//}
