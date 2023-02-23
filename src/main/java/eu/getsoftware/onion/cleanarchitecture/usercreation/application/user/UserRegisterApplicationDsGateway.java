package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;

public interface UserRegisterApplicationDsGateway
{
    boolean existsByName(String identifier);

    void save(UserDsRequestApplicationModelDTO requestModel);
}
