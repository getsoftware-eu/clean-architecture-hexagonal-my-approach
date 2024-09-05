package eu.getsoftware.cleanarchitecture.application.domain.usecase.user.iusecase;

import eu.getsoftware.cleanarchitecture.application.domain.usecase.user.dto.RequestUserUseCaseDTO;

//TODO IT IS NOT A application-layer service, its dummy interface for infustructure layer.
//eu: Where is business logik here? Where is separation and lucken fullung???

//TODO : is it only application Interface for hidden useCase logik implementation??
//TODO eu: where is abstract usecase actions, that will be lower filled with missing technical details??
//TODO hier I see only interface, not hidden abstract usecase for application layer

/**
 * will be implemented in infrastructure Layer!!! (application-layer service)
 * 'JpaUserRegisterApplicationService'
 */
public interface IUserRegisterUseCase
{
    boolean existsByName(String identifier);
    
    //eu: this dto is internal to useCase and not be returned outside(?)
    RequestUserUseCaseDTO getById(long id);

    void save(RequestUserUseCaseDTO requestModel);
}
