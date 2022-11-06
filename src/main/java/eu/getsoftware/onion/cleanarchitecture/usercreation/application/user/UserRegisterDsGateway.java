package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestModel;

public interface UserRegisterDsGateway {
    boolean existsByName(String identifier);

    void save(UserDsRequestModel requestModel);
}
