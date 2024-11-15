//package eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.domainserviceimpl;
//
//import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedDBEntity;
//import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper;
//import eu.getsoftware.cleanarchitecture.application.domain.model.modelinnerservice.DomainEntityDTOServiceAbstr;
//import eu.getsoftware.cleanarchitecture.application.domain.model.modelinnerservice.DbEntityGatewayServiceAbstr;
//import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserRequestUseCaseDTO;
//import eu.getsoftware.cleanarchitecture.application.port.in.user.iportservice.dto.UserResponseClientDTO;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDTOServiceImpl extends DomainEntityDTOServiceAbstr<UserMappedDBEntity, UserRequestUseCaseDTO, UserResponseClientDTO> {
//
//    /**
//     * 
//     * ALL LOGIC is now up in DOMAIN layer!!! Je hocher, desto besser!!!!
//     *  
//     * From here I set the CONCRETE Generics T, Z types to abstract layer
//     * @param domainMapper
//     * @param domainEntityGatewayService
//     */
//    public UserDTOServiceImpl(
//            IDomainMapper<UserMappedDBEntity, UserRequestUseCaseDTO, UserResponseClientDTO> domainMapper,
//            DbEntityGatewayServiceAbstr<UserMappedDBEntity> domainEntityGatewayService 
//        ) {
//        super(domainMapper, domainEntityGatewayService);
//    }
//    
//}
