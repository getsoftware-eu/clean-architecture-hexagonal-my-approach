package eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.service;

import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.model.UserDsRequestApplicationModel;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.model.UserDataMapperEntity;
import eu.getsoftware.onion.cleanarchitecture.usercreation.infrastructure.repository.JpaUserRepository;
import eu.getsoftware.onion.cleanarchitecture.usercreation.application.user.UserRegisterApplicationDsGateway;
import lombok.RequiredArgsConstructor;

/**
 * No own methods, just implement interface contract
 * 
 * no creation hier, controller direct to application layer (registerGateway)
 */
@RequiredArgsConstructor
class JpaUserRegisterApplicationService implements UserRegisterApplicationDsGateway
{
    private final JpaUserRepository repository;

    @Override
    public boolean existsByName(String name) {
        return repository.existsById(name);
    }
    
    @Override
    public void save(UserDsRequestApplicationModel requestModel) {
        UserDataMapperEntity accountDataMapper = new UserDataMapperEntity(requestModel.getName(), requestModel.getPassword(), requestModel.getCreationTime());
        repository.save(accountDataMapper);
    }
}
