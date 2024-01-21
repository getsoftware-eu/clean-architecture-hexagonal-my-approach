package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.service;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.IUserRegisterApplicationDsGatewayService;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * No own methods, just implement interface contract
 * 
 * no creation hier, controller direct to application layer (registerGateway)
 */
@Service
@AllArgsConstructor
class UserRegisterApplicationDsGatewayService /*extends IRegisterService<UserDataMapperEntity, UserDsRequestApplicationModelDTO>*/ implements IUserRegisterApplicationDsGatewayService
{
    private final UserRegService userRegService;

    @Override
    public boolean existsByName(String name) {
        return userRegService.existsByName(name);
    }    
    
    @Override
    public UserDsRequestApplicationModelDTO getById(long id) {
        return userRegService.getDTOById(id);
    }
    
    @Override
    public void save(UserDsRequestApplicationModelDTO requestModel) {
        userRegService.persistFromDTO(requestModel);
    }

//    @Override
//    public Class<UserDataMapperEntity> getAssetClass() {
//        return UserDataMapperEntity.class;
//    }
}
