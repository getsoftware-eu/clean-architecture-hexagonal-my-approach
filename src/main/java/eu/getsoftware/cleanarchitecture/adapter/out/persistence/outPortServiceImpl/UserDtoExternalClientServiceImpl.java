package eu.getsoftware.cleanarchitecture.adapter.out.persistence.outPortServiceImpl;

import eu.getsoftware.cleanarchitecture.application.domain.usecase.user.service.UserDTOExternalClientServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserDomainFactory;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserRequestUseCaseDTO;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.RequestUserAppDTOMapper;
import eu.getsoftware.cleanarchitecture.application.port.in.user.iPortService.dto.UserResponseClientDTO;
import org.springframework.stereotype.Service;

@Service
public class UserDtoExternalClientServiceImpl extends UserDTOExternalClientServiceAbstr<UserMappedEntity, UserRequestUseCaseDTO, UserResponseClientDTO> {

    /**
     * From here I set the CONCRETE Generics T, Z types to abstract layer
     * @param userFactory
     * @param requestUserAppDTOMapper
     */
    public UserDtoExternalClientServiceImpl(
            IUserDomainFactory<UserMappedEntity> userFactory,
            RequestUserAppDTOMapper requestUserAppDTOMapper
        ) {
        super(userFactory, requestUserAppDTOMapper);
    }

    @Override
    public Boolean useLocaldomainObjectInspiteOfGenericEntity() {
        return false;
    }
}
