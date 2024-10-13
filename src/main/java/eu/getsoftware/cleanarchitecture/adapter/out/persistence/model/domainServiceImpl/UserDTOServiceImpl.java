package eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.domainServiceImpl;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.DomainEntityDTOServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.DomainEntityGatewayServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;
import org.springframework.stereotype.Service;

@Service
public class UserDTOServiceImpl extends DomainEntityDTOServiceAbstr<UserMappedEntity, UserRequestUseCaseDTO, UserResponseClientDTO> {

    /**
     * 
     * ALL LOGIC is now up in DOMAIN layer!!! Je hocher, desto besser!!!!
     *  
     * From here I set the CONCRETE Generics T, Z types to abstract layer
     * @param domainMapper
     * @param domainEntityGatewayService
     */
    public UserDTOServiceImpl(
            IDomainMapper<UserMappedEntity, UserRequestUseCaseDTO, UserResponseClientDTO> domainMapper,
            DomainEntityGatewayServiceAbstr<UserMappedEntity> domainEntityGatewayService 
        ) {
        super(domainMapper, domainEntityGatewayService);
    }
    
}
