package eu.getsoftware.cleanarchitecture.users.feautures.usercreation.app.usecases;

import eu.getsoftware.cleanarchitecture.users.feautures.usercreation.app.dto.RequestUserAppDTO;

/**
 * will be implemented in infrastructure Layer!!! (application-layer service)
 * 'JpaUserRegisterApplicationService'
 */
public interface IUserRegisterAppUseCase
{
    boolean existsByName(String identifier);
    
    RequestUserAppDTO getById(long id);

    void save(RequestUserAppDTO requestModel);
}
