package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import java.util.Optional;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserResponseApplicationModelDTO;
import eu.getsoftware.onion.cleanarchitecture.usercreation.domain.user.UserEntity;

public interface UserRegisterApplicationDsGateway
{
    boolean existsByName(String identifier);
    
    UserDsRequestApplicationModelDTO getById(long id);

    void save(UserDsRequestApplicationModelDTO requestModel);
}
