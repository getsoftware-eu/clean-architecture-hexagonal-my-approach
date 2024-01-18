package eu.getsoftware.onion.cleanarchitecture.usercreation.application.user;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModelDTO;

/**
 * will be implemented in infrastructure Layer!!! (application-layer service)
 * 'JpaUserRegisterApplicationService'
 */
public interface IUserRegisterApplicationDsGatewayService
{
    boolean existsByName(String identifier);
    
    UserDsRequestApplicationModelDTO getById(long id);

    void save(UserDsRequestApplicationModelDTO requestModel);
}
