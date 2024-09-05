package eu.getsoftware.cleanarchitecture.application.domain.service.user.impl;

import eu.getsoftware.cleanarchitecture.application.domain.model.user.IUserFactory;
import eu.getsoftware.cleanarchitecture.application.domain.service.user.UserInputPortServiceAbstr;
import eu.getsoftware.cleanarchitecture.application.domain.usecase.user.dto.RequestUserUseCaseDTO;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import eu.getsoftware.cleanarchitecture.application.port.user.out.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.RequestUserAppDTOMapper;
import org.springframework.stereotype.Service;

@Service
public class UserInputPortServiceImpl extends UserInputPortServiceAbstr<UserMappedEntity, RequestUserUseCaseDTO> {

    /**
     * From here I set the CONCRETE Generics T, Z types to abstract layer
     * @param userResponseDTOPortPresenter
     * @param userFactory
     * @param requestUserAppDTOMapper
     * @param userRegisterPortService
     */
    public UserInputPortServiceImpl(
            IUserFactory<UserMappedEntity/*, UserRequestAppDTO*/> userFactory,
            RequestUserAppDTOMapper requestUserAppDTOMapper,
            RegisterUserPortServiceImpl userRegisterPortService,
            IUserResponseDTOPortPresenter userResponseDTOPortPresenter) {
        super(userFactory, requestUserAppDTOMapper, userRegisterPortService, userResponseDTOPortPresenter);
    }

    @Override
    public Boolean useLocaldomainObjectInspiteOfGenericEntity() {
        return false;
    }
}
