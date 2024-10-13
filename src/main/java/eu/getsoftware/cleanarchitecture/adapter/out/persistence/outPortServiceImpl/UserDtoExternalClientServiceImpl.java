package eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl;

import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.RequestUserAppDTOMapper;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import eu.getsoftware.cleanarchitecture.application.domain.model.mapper.IDomainMapper;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.DomainEntityDTOServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.domain.model.modelInnerService.DomainEntityGatewayServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainFactory;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;
import org.springframework.stereotype.Service;

@Service
public class UserDtoExternalClientServiceImpl extends DomainEntityDTOServiceAbstr<UserMappedEntity, UserRequestUseCaseDTO, UserResponseClientDTO> {

    /**
     * From here I set the CONCRETE Generics T, Z types to abstract layer
     * @param domainMapper
     * @param domainEntityGatewayService
     */
    public UserDtoExternalClientServiceImpl(
            IDomainMapper<UserMappedEntity, UserRequestUseCaseDTO, UserResponseClientDTO> domainMapper,
            DomainEntityGatewayServiceAbstr<UserMappedEntity> domainEntityGatewayService 
        ) {
        super(domainMapper, domainEntityGatewayService);
    }
    
}
