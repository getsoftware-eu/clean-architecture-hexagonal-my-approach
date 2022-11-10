package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModel;

public interface UserRegisterApplicationDsGateway
{
    boolean existsByName(String identifier);

    void save(UserDsRequestApplicationModel requestModel);
}
