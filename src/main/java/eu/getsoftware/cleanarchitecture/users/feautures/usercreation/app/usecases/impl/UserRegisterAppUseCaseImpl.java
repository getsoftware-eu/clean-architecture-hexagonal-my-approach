package eu.getsoftware.cleanarchitecture.users.feautures.usercreation.app.usecases.impl;

import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.port.service.UserRegisterPortServiceImpl;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.app.usecases.IUserRegisterAppUseCase;
import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.app.dto.RequestUserAppDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * No own methods, just implement interface contract
 * 
 * no creation hier, controller direct to application layer (registerGateway)
 */
@Service
@AllArgsConstructor
class UserRegisterAppUseCaseImpl /*extends IRegisterService<UserDataMapperEntity, UserDsRequestApplicationModelDTO>*/ implements IUserRegisterAppUseCase
{
    private final UserRegisterPortServiceImpl userRegisterPortServiceImpl;

    @Override
    public boolean existsByName(String name) {
        return userRegisterPortServiceImpl.existsByName(name);
    }    
    
    @Override
    public RequestUserAppDTO getById(long id) {
        return userRegisterPortServiceImpl.getDTOById(id);
    }
    
    @Override
    public void save(RequestUserAppDTO requestModel) {
        userRegisterPortServiceImpl.persistFromDTO(requestModel);
    }

//    @Override
//    public Class<UserDataMapperEntity> getAssetClass() {
//        return UserDataMapperEntity.class;
//    }
}
