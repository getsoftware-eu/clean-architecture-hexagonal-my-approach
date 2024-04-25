package eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.usecases.impl;

import eu.getsoftware.cleanarchitecture.users.domain.model.IUserFactory;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.app.dto.RequestUserAppDTO;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.model.UserMappedEntity;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.service.UserRegisterPortServiceImpl;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.dto.IUserResponseDTOPortPresenter;
import eu.getsoftware.cleanarchitecture.adapter.out.persistence.mapper.RequestUserAppDTOMapper;
import org.springframework.stereotype.Service;

@Service
public class UserInputPortUseCaseImpl extends UserInputPortUseCaseAbstr<UserMappedEntity, RequestUserAppDTO> {

    /**
     * From here I set the CONCRETE Generics T, Z types to abstract layer
     * @param userResponseDTOPortPresenter
     * @param userFactory
     * @param requestUserAppDTOMapper
     * @param userRegisterPortService
     */
    public UserInputPortUseCaseImpl(
            IUserFactory<UserMappedEntity/*, UserRequestAppDTO*/> userFactory,
            RequestUserAppDTOMapper requestUserAppDTOMapper,
            UserRegisterPortServiceImpl userRegisterPortService,
            IUserResponseDTOPortPresenter userResponseDTOPortPresenter) {
        super(userFactory, requestUserAppDTOMapper, userRegisterPortService, userResponseDTOPortPresenter);
    }

    @Override
    public Boolean useLocaldomainObjectInspiteOfGenericEntity() {
        return false;
    }
}
