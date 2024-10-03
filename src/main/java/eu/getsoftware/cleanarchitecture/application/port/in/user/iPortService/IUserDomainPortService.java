//package eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService;
//
//import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
//
////TODO IT IS NOT A application-layer service, its dummy interface for infustructure layer.
////eu: Where is business logik here? Where is separation and lucken fullung???
//
////TODO : is it only application Interface for hidden useCase logik implementation??
////TODO eu: where is abstract usecase actions, that will be lower filled with missing technical details??
////TODO hier I see only interface, not hidden abstract usecase for application layer
//
///**
// * will be implemented in infrastructure Layer!!! (application-layer service)
// * 'JpaUserRegisterApplicationService'
// */
//public interface IUserDomainPortService
//{
//    boolean existsByName(String identifier);
//
//    //eu: this dto is internal to useCase and not be returned outside(?)
//    UserRequestUseCaseDTO findDTOById(long id);
//
//    void saveDTO(UserRequestUseCaseDTO requestModel);
//}
